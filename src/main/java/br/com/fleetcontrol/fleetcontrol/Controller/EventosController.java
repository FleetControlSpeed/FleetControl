package br.com.fleetcontrol.fleetcontrol.Controller;

import br.com.fleetcontrol.fleetcontrol.Entity.Eventos;
import br.com.fleetcontrol.fleetcontrol.Entity.Modelo;
import br.com.fleetcontrol.fleetcontrol.Repository.EventosRepository;
import br.com.fleetcontrol.fleetcontrol.Repository.ModeloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping(value = "/api/eventos")
public class EventosController {
    @Autowired
    private EventosRepository eventosRepository;

    @PostMapping
    public ResponseEntity<?> CadastroEventos(@RequestParam("id") final Eventos eventos) {
        try {
            this.eventosRepository.save(eventos);
            return ResponseEntity.ok("Evento salvo com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("erro de inserção de evento");

        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> FindByIdRequest(@PathVariable("id") final Long id) {
        final Eventos eventos = this.eventosRepository.findById(id).orElse(null);
        return eventos == null
                ? ResponseEntity.badRequest().body("Nenhum evento cadastrado")
                : ResponseEntity.ok(eventos);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> EditarEventos(@RequestParam("id") final Long id, @RequestBody final Modelo modelo) {
        try {
            final Eventos eventosBanco = this.eventosRepository.findById(id).orElse(null);
            if (eventosBanco == null || !eventosBanco.getId().equals(eventosBanco.getId())) {
                throw new RuntimeException("Evento inserido erroneamente");
            }
            this.eventosRepository.save(eventosBanco);
            return ResponseEntity.ok("Evento editado com sucesso");

        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.internalServerError().body("Error" + e.getCause().getCause().getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body("Error" + e.getMessage());
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarEventos(@RequestParam("id") final Long id) {

        final Eventos eventosBanco = this.eventosRepository.findById(id).orElse(null);
        this.eventosRepository.delete(eventosBanco);
        if (eventosBanco == null) {
            eventosBanco.setAtivo(false);
            this.eventosRepository.save(eventosBanco);
        }
        return ResponseEntity.ok("evento deletado com sucesso");

    }
}