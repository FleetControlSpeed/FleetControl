package br.com.fleetcontrol.fleetcontrol.controller;

import br.com.fleetcontrol.fleetcontrol.entity.Empresas;
import br.com.fleetcontrol.fleetcontrol.repository.EmpresasRepository;
import br.com.fleetcontrol.fleetcontrol.service.EmpresasService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/empresas")
public class EmpresasController {

    /*
    {
    "id": 1,
    "cadastro": "2023-05-27T22:48:58.594611",
    "edicao": null,
    "ativo": true,
    "nome": "Casa do Pedro",
    "endereco": "Rua Belmiro numero 2",
    "cep": "85859340"
    }
     */

    @Autowired
    private EmpresasService empresasService;
    @Autowired
    private EmpresasRepository empresasRepository;


    @GetMapping(value = "/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable("id") final Long id) {
            final Empresas empresas = this.empresasRepository.findById(id).orElse(null);
            return ResponseEntity.ok(empresas);

    }


    @PostMapping
    public ResponseEntity<?> cadastrar(@Valid @RequestBody final Empresas empresas) {
        try {
            this.empresasRepository.save(empresas);
            return ResponseEntity.ok("Empresa cadastrado com sucesso!");

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error" + e.getMessage());
        }
    }
    @PutMapping(value = "/editar")
    public ResponseEntity<?> editar(@Valid @RequestParam("id") final Long id, @RequestBody final Empresas empresasNovo) {
        try {
            empresasService.editar(id, empresasNovo);
            return ResponseEntity.ok("Evento atualizado com sucesso!");

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error" + e.getMessage());
        }
    }
    @PutMapping(value = "/desativar")
    public ResponseEntity<?> deletar(@Valid @RequestParam("id") final Long id){
        try {
            empresasService.desativar(id);
            return ResponseEntity.ok("Empresa deletada com sucesso!");

        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error" + e.getMessage());
        }
    }

    @DeleteMapping(value = "/deletar")
    private ResponseEntity<?> deletar(@Valid @RequestParam("id") final long id){
        try {
            empresasService.deletar(id);
            return ResponseEntity.ok("Registro deletado com sucesso!");

        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error" + e.getMessage());
        }
    }
}
