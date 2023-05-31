package br.com.fleetcontrol.fleetcontrol.controller;

import br.com.fleetcontrol.fleetcontrol.entity.Eventos;
import br.com.fleetcontrol.fleetcontrol.service.EventosService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
    @Author: Pedro Henrique Vieira
    Date: 07/05/2023
 */

@RestController
@ResponseBody
@RequestMapping(value = "/api/eventos")
public class EventosController {

    /*
{
    "id": 1,
    "cadastro": "2023-05-29T22:11:07.047194",
    "edicao": null,
    "ativo": true,
    "usuario": {
        "id": 1,
        "cadastro": "2023-05-29T22:10:14.247152",
        "edicao": null,
        "ativo": true,
        "email": "pedrohenri1606@gmail.com",
        "usuario": "pedro",
        "senha": "123",
        "cargo": "ADMINISTRADOR",
        "primeiroNome": "Pedro",
        "sobrenome": "Henrique",
        "cpf": "10250870975",
        "telefone": "45998265476",
        "dataNascimento": "29/07/2003",
        "endereco": "Rua Belmiro numero 2"
    },
    "veiculo": {
        "id": 1,
        "cadastro": "2023-05-29T22:09:41.805183",
        "edicao": null,
        "ativo": true,
        "modelo": {
            "id": 1,
            "cadastro": "2023-05-29T22:09:35.50122",
            "edicao": null,
            "ativo": true,
            "nome": "HRV",
            "marca": "HONDA"
        },
        "placa": "RHT-5F18",
        "ano": 2022,
        "cor": "VERMELHO",
        "km": 10000,
        "tipo": "CARRO"
    },
    "dataEvento": "2023-05-28T19:40:32.380161",
    "localPartida": {
        "id": 1,
        "cadastro": "2023-05-29T22:09:24.438476",
        "edicao": null,
        "ativo": true,
        "nome": "Casa do Pedro",
        "endereco": "Rua Belmiro numero 2",
        "cep": "85859-340"
    },
    "localDestino": {
        "id": 1,
        "cadastro": "2023-05-29T22:09:24.438476",
        "edicao": null,
        "ativo": true,
        "nome": "Casa do Pedro",
        "endereco": "Rua Belmiro numero 2",
        "cep": "85859-340"
    },
    "observacao": null,
    "kmFinal": null,
    "kmTotal": null,
    "retorno": null
}
     */

    @Autowired
    private EventosService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable("id") final Long id) {
        try {
            final Eventos eventos = service.buscarPorId(id);
            return ResponseEntity.ok(eventos);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error" + e.getMessage());
        }
    }

    @GetMapping(value = "/listar")
    public ResponseEntity<?> listar(){
        try{
            return ResponseEntity.ok(service.listar());

        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error" + e.getMessage());
        }
    }

    @GetMapping(value = "/listarPorAtivo")
    public ResponseEntity<?> listarPorAtivo(){
        try{
            return ResponseEntity.ok(service.listarPorAtivo());

        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error" + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@Valid @RequestBody final Eventos eventos) {
        try {
            this.service.salvar(eventos);
            return ResponseEntity.ok("Evento cadastrado com sucesso!");

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error" + e.getMessage());
        }
    }

    @PutMapping(value = "/editar")
    public ResponseEntity<?> editar(@Valid @RequestParam("id") final Long id, @RequestBody final Eventos eventosNovo) {
        try {
            service.editar(id, eventosNovo);
            return ResponseEntity.ok("Evento atualizado com sucesso!");

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error" + e.getMessage());
        }
    }

    @PutMapping(value = "/desativar")
    public ResponseEntity<?> desativar(@Valid @RequestParam("id") final Long id){
        try {
            service.desativar(id);
            return ResponseEntity.ok("Evento desativado com sucesso!");

        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error" + e.getMessage());
        }
    }

    @PutMapping(value = "/ativar")
    public ResponseEntity<?> ativar(@Valid @RequestParam("id") final Long id){
        try {
            service.ativar(id);
            return ResponseEntity.ok("Evento ativado com sucesso!");

        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error" + e.getMessage());
        }
    }

    @PutMapping(value = "/fechar")
    public ResponseEntity<?> fechar(@Valid @RequestParam("id") final Long id, @RequestBody final Eventos evento){
        try {
            service.fechar(id,evento);
            return ResponseEntity.ok("Evento fechado com sucesso, por favor, finalize o evento para receber o comprovante!");

        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error" + e.getMessage());
        }
    }

    @PutMapping(value = "/finalizar")
    public ResponseEntity<?> finalizar(@Valid @RequestParam("id") final Long id){
        try {
            service.finalizar(id);
            return ResponseEntity.ok(service.finalizar(id));

        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error" + e.getMessage());
        }
    }
}