package br.com.fleetcontrol.fleetcontrol.controller;

import br.com.fleetcontrol.fleetcontrol.dto.EmpresaConverter;
import br.com.fleetcontrol.fleetcontrol.dto.EmpresasDTO;
import br.com.fleetcontrol.fleetcontrol.dto.EventoConverter;
import br.com.fleetcontrol.fleetcontrol.dto.EventoDTO;
import br.com.fleetcontrol.fleetcontrol.entity.Empresas;
import br.com.fleetcontrol.fleetcontrol.entity.Eventos;
import br.com.fleetcontrol.fleetcontrol.repository.EventosRepository;
import br.com.fleetcontrol.fleetcontrol.service.EventosService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    "cadastro": "2023-05-28T19:40:32.380161",
    "edicao": null,
    "ativo": true,
    "usuario": {
        "id": 1,
        "cadastro": "2023-05-28T19:33:44.514023",
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
        "cadastro": "2023-05-27T22:46:03.14222",
        "edicao": null,
        "ativo": true,
        "modelo": {
            "id": 1,
            "cadastro": "2023-05-28T19:34:12.724773",
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
        "cadastro": "2023-05-28T19:39:43.137577",
        "edicao": null,
        "ativo": true,
        "nome": "Casa do Pedro",
        "endereco": "Rua Belmiro numero 2",
        "cep": "85859-340"
    },
    "localDestino": {
        "id": 1,
        "cadastro": "2023-05-28T19:39:43.137577",
        "edicao": null,
        "ativo": true,
        "nome": "Casa do Pedro",
        "endereco": "Rua Belmiro numero 2",
        "cep": "85859-340"
    },
    "observacao": null,
    "retorno": null
}
     */

    @Autowired
    private EventosService eventosService;
    @Autowired
    private EventosRepository eventosRepository;
    @GetMapping("/{id}")
    public ResponseEntity<EventoDTO> listaId(@PathVariable(value = "id") Long id) {
        Eventos eventos = eventosRepository.findById(id).orElse(null);
        if (eventos == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        EventoDTO eventoDTO = EventoConverter.toDTO(eventos);
        return ResponseEntity.ok(eventoDTO);
    }




    @GetMapping(value = "/listar")
    public ResponseEntity<?> listar() {
        try {
            return ResponseEntity.ok(eventosService.listar());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    @GetMapping(value = "/listarPorAtivo")
    public ResponseEntity<?> listarPorAtivo() {
        try {
            return ResponseEntity.ok(eventosService.listarPorAtivo());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@Valid @RequestBody final EventoDTO cadastro) {
        try {
            eventosService.cadastrar(cadastro);
            return ResponseEntity.ok("Evento cadastrado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }



    @PutMapping(value = "/editar")
    public ResponseEntity<?> editar(@Valid @RequestParam("id") final Long id, @RequestBody final Eventos editor) {
        try {
            eventosService.editar(id, editor);
            return ResponseEntity.ok("Eventos atualizada com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    @PutMapping(value = "/desativar")
    public ResponseEntity<?> desativar(@Valid @RequestParam("id") final Long id) {
        try {
            eventosService.desativar(id);
            return ResponseEntity.ok("Eventos desativada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    @PutMapping(value = "/ativar")
    public ResponseEntity<?> ativar(@Valid @RequestParam("id") final Long id) {
        try {
            eventosService.ativar(id);
            return ResponseEntity.ok("Eventos ativada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping(value = "/deletar")
    private ResponseEntity<?> deletar(@Valid @RequestParam("id") final long id) {
        try {
            eventosService.desativar(id);
            return ResponseEntity.ok("Registro deletado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }
}