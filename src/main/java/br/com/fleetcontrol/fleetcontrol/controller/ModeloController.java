package br.com.fleetcontrol.fleetcontrol.controller;


import br.com.fleetcontrol.fleetcontrol.dto.ModeloConverter;
import br.com.fleetcontrol.fleetcontrol.dto.ModeloDTO;

import br.com.fleetcontrol.fleetcontrol.entity.Modelo;

import br.com.fleetcontrol.fleetcontrol.service.ModeloService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
@RequestMapping(value = "/api/modelo")
public class ModeloController {

    /*
    {
    "id": 1,
    "cadastro": "2023-05-27T22:44:35.287413",
    "edicao": null,
    "ativo": true,
    "nome": "HRV",
    "marca": "HONDA"
    }
     */

    @Autowired
    private ModeloService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable("id") final Long id) {
        try {
            Modelo modelo = service.buscarPorId(id);
            ModeloDTO DTO = ModeloConverter.toDTO(modelo);
            return ResponseEntity.ok(DTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }


    @GetMapping(value = "/listar")
    public ResponseEntity<?> listar() {
        try {
            return ResponseEntity.ok(service.listar());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    @GetMapping(value = "/listarPorAtivo")
    public ResponseEntity<?> listarPorAtivo() {
        try {
            return ResponseEntity.ok(service.listarPorAtivo());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@Valid @RequestBody final ModeloDTO cadastro) {
        try {
            service.cadastrar(cadastro);
            return ResponseEntity.ok("modelo cadastrado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }



    @PutMapping(value = "/editar")
    public ResponseEntity<?> editar(@Valid @RequestParam("id") final Long id, @RequestBody final Modelo editor) {
        try {
            service.editar(id, editor);
            return ResponseEntity.ok("Modelos atualizada com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    @PutMapping(value = "/desativar")
    public ResponseEntity<?> desativar(@Valid @RequestParam("id") final Long id) {
        try {
            service.desativar(id);
            return ResponseEntity.ok("Modelos desativada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    @PutMapping(value = "/ativar")
    public ResponseEntity<?> ativar(@Valid @RequestParam("id") final Long id) {
        try {
            service.ativar(id);
            return ResponseEntity.ok("Modelos ativada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping(value = "/deletar")
    private ResponseEntity<?> deletar(@Valid @RequestParam("id") final long id) {
        try {
            service.desativar(id);
            return ResponseEntity.ok("Registro deletado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }
}