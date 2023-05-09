package br.com.fleetcontrol.fleetcontrol.controller;

import br.com.fleetcontrol.fleetcontrol.entity.Eventos;
import br.com.fleetcontrol.fleetcontrol.service.EventosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/*
    @Author: Pedro Henrique Vieira
    Date: 07/05/2023
 */

@Controller
@ResponseBody
@RequestMapping(value = "/api/eventos")
public class EventosController {
    @Autowired
    private EventosService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable("id") final Long id) {
        try {
            final Eventos eventos = service.buscarPorId(id);
            return ResponseEntity.ok(eventos);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error " + e.getMessage());
        }
    }

    @GetMapping(value = "/listar")
    public ResponseEntity<?> listar(){
        try{
            return ResponseEntity.ok(service.listar());

        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error " + e.getMessage());
        }
    }

    @GetMapping(value = "/listarPorAtivo")
    public ResponseEntity<?> listarPorAtivo(){
        try{
            return ResponseEntity.ok(service.listarPorAtivo());

        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestParam("id") final Eventos eventos) {
        try {
            this.service.salvar(eventos);
            return ResponseEntity.ok("Evento cadastrado com sucesso!");

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error" + e.getMessage());
        }
    }

    @PutMapping(value = "/editar")
    public ResponseEntity<?> editar(@RequestParam("id") final Long id, @RequestBody final Eventos eventosNovo) {
        try {
            service.editar(id, eventosNovo);
            return ResponseEntity.ok("Evento atualizado com sucesso!");

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error" + e.getMessage());
        }
    }

    @PutMapping(value = "/desativar")
    public ResponseEntity<?> desativar(@RequestParam("id") final Long id){
        try {
            service.desativar(id);
            return ResponseEntity.ok("Evento desativado com sucesso!");

        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error" + e.getMessage());
        }
    }

    @PutMapping(value = "/ativar")
    public ResponseEntity<?> ativar(@RequestParam("id") final Long id){
        try {
            service.ativar(id);
            return ResponseEntity.ok("Evento ativado com sucesso!");

        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error " + e.getMessage());
        }
    }
}