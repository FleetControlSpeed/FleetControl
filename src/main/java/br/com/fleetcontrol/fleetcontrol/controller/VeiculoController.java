package br.com.fleetcontrol.fleetcontrol.controller;

import br.com.fleetcontrol.fleetcontrol.entity.Veiculo;
import br.com.fleetcontrol.fleetcontrol.service.VeiculoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping(value = "/api/veiculo")
public class VeiculoController {

    /*
    {
    "id": 1,
    "cadastro": "2023-05-27T22:46:03.14222",
    "edicao": null,
    "ativo": true,
    "modelo": {
        "id": 1,
        "cadastro": "2023-05-27T22:44:35.287413",
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
    }
     */

    @Autowired
    private VeiculoService veiculoService;

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable("id") final Long id) {
        try{
            return ResponseEntity.ok(veiculoService.buscarPorId(id));

        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error" + e.getMessage());
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listarVeiculos(){
        try {
            return ResponseEntity.ok(veiculoService.listar());

        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error" + e.getMessage());
        }
    }

    @GetMapping("/listarPorAtivo")
    public ResponseEntity<?> listarPorAtivo(){
        try {
            return ResponseEntity.ok(veiculoService.listarPorAtivo());

        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error" + e.getMessage());
        }
    }

    @PutMapping("/editar")
    public ResponseEntity<?> editarVeiculo(@RequestParam("id") final Long id, @RequestBody final Veiculo veiculoNovo) {
        try{
            veiculoService.editar(id,veiculoNovo);
            return ResponseEntity.ok("Veiculo alterado com sucesso!");

        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error" + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> cadastrarVeiculo(@Valid @RequestBody final Veiculo veiculo) {
        try {
            veiculoService.salvar(veiculo);
            return ResponseEntity.ok("Veiculo cadastrado com sucesso!");

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error" + e.getMessage());

        }
    }

    @PutMapping(value = "/desativar")
    public ResponseEntity<?> desativar(@RequestParam("id") final Long id) {
        try {
            veiculoService.desativar(id);
            return ResponseEntity.ok("Evento desativado com sucesso!");

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error" + e.getMessage());
        }
    }

    @PutMapping(value = "/ativar")
    public ResponseEntity<?> ativar(@RequestParam("id") final Long id){
        try {
            veiculoService.ativar(id);
            return ResponseEntity.ok("Veiculo ativado com sucesso!");

        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error" + e.getMessage());
        }
    }
}


