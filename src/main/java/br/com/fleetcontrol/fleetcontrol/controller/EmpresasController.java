package br.com.fleetcontrol.fleetcontrol.controller;

import br.com.fleetcontrol.fleetcontrol.entity.Empresas;
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

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable("id") final Long id) {
        try{
            return ResponseEntity.ok(empresasService.buscarPorId(id));

        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error" + e.getMessage());
        }
    }

    @GetMapping(value = "/listar")
    public ResponseEntity<?> listar(){
        try {
            return ResponseEntity.ok(empresasService.listar());

        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error" + e.getMessage());
        }
    }

    @GetMapping(value = "/listarPorAtivo")
    public ResponseEntity<?> listarPorAtivo(){
        try {
            return ResponseEntity.ok(empresasService.listarPorAtivo());

        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error" + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@Valid @RequestBody final Empresas empresas) {
        try {
            empresasService.cadastrar(empresas);
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
    public ResponseEntity<?> desativar(@Valid @RequestParam("id") final Long id){
        try {
            empresasService.desativar(id);
            return ResponseEntity.ok("Empresa desativada com sucesso!");

        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error" + e.getMessage());
        }
    }

    @PutMapping(value = "/ativar")
    public ResponseEntity<?> ativar(@Valid @RequestParam("id") final Long id){
        try {
            empresasService.ativar(id);
            return ResponseEntity.ok("Empresa ativada com sucesso!");

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
