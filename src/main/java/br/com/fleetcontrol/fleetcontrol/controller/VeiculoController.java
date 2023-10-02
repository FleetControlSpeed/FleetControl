package br.com.fleetcontrol.fleetcontrol.controller;

import br.com.fleetcontrol.fleetcontrol.dto.EmpresaConverter;
import br.com.fleetcontrol.fleetcontrol.dto.EmpresasDTO;
import br.com.fleetcontrol.fleetcontrol.dto.VeiculoConverter;
import br.com.fleetcontrol.fleetcontrol.dto.VeiculoDTO;
import br.com.fleetcontrol.fleetcontrol.entity.Empresas;
import br.com.fleetcontrol.fleetcontrol.entity.Veiculo;
import br.com.fleetcontrol.fleetcontrol.repository.VeiculoRepository;
import br.com.fleetcontrol.fleetcontrol.service.VeiculoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
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
    @Autowired
    private VeiculoRepository veiculoRepository;


    @GetMapping("/{id}")
    public ResponseEntity<VeiculoDTO> listaId(@PathVariable(value = "id") Long id) {
        Veiculo veiculo = veiculoRepository.findById(id).orElse(null);
        if (veiculo == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        VeiculoDTO veiculoDTO = VeiculoConverter.toDTO(veiculo);
        return ResponseEntity.ok(veiculoDTO);
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listar(){
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

    @PutMapping("/put/id/{id}")
    public ResponseEntity<String> atualizar(@PathVariable Long id, @RequestBody VeiculoDTO dto) {
        try {
            Veiculo veiculoAtualizado = VeiculoConverter.toEntity(dto);
            this.veiculoService.atualizar(id, veiculoAtualizado);
            return ResponseEntity.ok().body("Atualizado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrar(@RequestBody VeiculoDTO veiculoDTO) {
        try {
            Veiculo veiculo = VeiculoConverter.toEntity(veiculoDTO);
            this.veiculoService.cadastrar(veiculo);
            return ResponseEntity.ok("Cadastro feito com sucesso");
        } catch (DataIntegrityViolationException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("ERRO: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/desativar")
    public ResponseEntity<?> desativar(@Valid @RequestParam("id") final Long id) {
        try {
            veiculoService.desativar(id);
            return ResponseEntity.ok("Evento desativado com sucesso!");

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PutMapping(value = "/ativar")
    public ResponseEntity<?> ativar(@Valid @RequestParam("id") final Long id){
        try {
            veiculoService.ativar(id);
            return ResponseEntity.ok("Veiculo ativado com sucesso!");

        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error" + e.getMessage());
        }
    }

    @DeleteMapping(value = "/deletar")
    private ResponseEntity<?> deletar(@Valid @RequestParam("id") final long id){
        try {
            veiculoService.deletar(id);
            return ResponseEntity.ok("Registro deletado com sucesso!");

        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}


