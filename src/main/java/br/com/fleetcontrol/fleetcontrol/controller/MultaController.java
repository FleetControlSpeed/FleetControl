package br.com.fleetcontrol.fleetcontrol.controller;

import br.com.fleetcontrol.fleetcontrol.entity.Multa;
import br.com.fleetcontrol.fleetcontrol.entity.Usuario;
import br.com.fleetcontrol.fleetcontrol.service.MultaService;
import br.com.fleetcontrol.fleetcontrol.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/api/multa")
public class MultaController {
    @Autowired
    private MultaService multaService;

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(multaService.buscarPorId(id));
    }

    @GetMapping("/listar")
    public ResponseEntity<Page<Multa>> listar(Pageable pageable) {
        try{
            Page<Multa> multas =  multaService.listaCompleta(pageable);
            return ResponseEntity.ok(multas);
        }catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public ResponseEntity<?> cadastrar(@Valid @RequestBody Multa multa) {
        multa = multaService.salvar(multa);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(multa.getId()).toUri();
        return ResponseEntity.created(uri).body(multa);
    }

    @PutMapping("/editar")
    public ResponseEntity<?> atualizar(@Valid @RequestParam("id") Long idMulta, @RequestBody Multa multaNovo) {
        try{
            multaService.atualizar(idMulta,multaNovo);
            return ResponseEntity.ok("Multa alterada com sucesso!");

        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error" + e.getMessage());
        }
    }


    @PutMapping("/desativar")
    public ResponseEntity<?> desativar(@Valid @RequestParam("id") Long idMulta) {
        try{
            multaService.desativar(idMulta);
            return ResponseEntity.ok("Multa desativada com sucesso!");

        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error" + e.getMessage());
        }
    }

    @PutMapping("/ativar")
    public ResponseEntity<?> ativar(@Valid @RequestParam("id") Long idMulta) {
        try{
            multaService.ativar(idMulta);
            return ResponseEntity.ok("Multa ativada com sucesso!");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error" + e.getMessage());
        }
    }

}
