package br.com.fleetcontrol.fleetcontrol.controller;

import br.com.fleetcontrol.fleetcontrol.dto.EventoConverter;
import br.com.fleetcontrol.fleetcontrol.dto.EventoDTO;
import br.com.fleetcontrol.fleetcontrol.entity.Eventos;
import br.com.fleetcontrol.fleetcontrol.repository.EventosRepository;
import br.com.fleetcontrol.fleetcontrol.service.EventosService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseBody
@RequestMapping(value = "/api/eventos")
public class EventosController {
    @Autowired
    private EventosService service;
    @Autowired
    private EventosRepository eventosRepository;

    private static final String ERROR_MESSAGE_PREFIX = "Error: ";
    @GetMapping("/{id}")
    public ResponseEntity<EventoDTO> listaId(@PathVariable(value = "id") Long id) {
        Eventos eventos = eventosRepository.findById(id).orElse(null);
        if (eventos == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        EventoDTO eventoDTO = EventoConverter.toDTO(eventos);
        return ResponseEntity.ok(eventoDTO);
    }


    @GetMapping("/lista")
    public ResponseEntity<List<EventoDTO>> lista() {
        List<Eventos> listaEventos = service.listartudo();
        List<EventoDTO> listaEventosDTO = EventoConverter.toDTOList(listaEventos);
        return ResponseEntity.ok(listaEventosDTO);
    }
    @GetMapping("/listarPorAtivo")
    public ResponseEntity<List<EventoDTO>> listarPorAtivo(@PathVariable boolean ativo) {
        List<Eventos> listaAtivo = eventosRepository.findByAtivo(ativo);
        List<EventoDTO> listaAtivoDTO = EventoConverter.toDTOList(listaAtivo);

        return ResponseEntity.ok(listaAtivoDTO);
    }

    @PostMapping
    public ResponseEntity<String> cadastrar(@Valid @RequestBody final EventoDTO cadastro) {
        try {
            service.cadastrar(cadastro);
            return ResponseEntity.ok("Evento cadastrado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ERROR_MESSAGE_PREFIX + e.getMessage());
        }
    }



    @PutMapping(value = "/editar")
    public ResponseEntity<String> editar(@Valid @RequestParam("id") final Long id, @RequestBody final Eventos editor) {
        try {
            service.editar(id, editor);
            return ResponseEntity.ok("Eventos atualizada com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ERROR_MESSAGE_PREFIX + e.getMessage());
        }
    }

    @PutMapping(value = "/desativar")
    public ResponseEntity<String> desativar(@Valid @RequestParam("id") final Long id) {
        try {
            service.desativar(id);
            return ResponseEntity.ok("Eventos desativada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ERROR_MESSAGE_PREFIX + e.getMessage());
        }
    }

    @PutMapping(value = "/ativar")
    public ResponseEntity<String> ativar(@Valid @RequestParam("id") final Long id) {
        try {
            service.ativar(id);
            return ResponseEntity.ok("Eventos ativada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ERROR_MESSAGE_PREFIX + e.getMessage());
        }
    }

    @DeleteMapping(value = "/deletar")
    public ResponseEntity<String> deletar(@Valid @RequestParam("id") final long id) {
        try {
            service.desativar(id);
            return ResponseEntity.ok("Registro deletado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ERROR_MESSAGE_PREFIX + e.getMessage());
        }
    }
}