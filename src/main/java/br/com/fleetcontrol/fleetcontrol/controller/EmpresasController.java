package br.com.fleetcontrol.fleetcontrol.controller;

import br.com.fleetcontrol.fleetcontrol.dto.EmpresaConverter;
import br.com.fleetcontrol.fleetcontrol.dto.EmpresasDTO;
import br.com.fleetcontrol.fleetcontrol.entity.Empresas;
import br.com.fleetcontrol.fleetcontrol.repository.EmpresasRepository;
import br.com.fleetcontrol.fleetcontrol.service.EmpresasService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
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
    @GetMapping("/{id}")
    public ResponseEntity<EmpresasDTO> listaId(@PathVariable(value = "id") Long id) {
        Empresas empresas = empresasRepository.findById(id).orElse(null);
        if (empresas == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        EmpresasDTO empresasDTO = EmpresaConverter.toDTO(empresas);
        return ResponseEntity.ok(empresasDTO);
    }




    @GetMapping(value = "/listar")
    public ResponseEntity<?> listar() {
        try {
            return ResponseEntity.ok(empresasService.listar());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    @GetMapping(value = "/listarPorAtivo")
    public ResponseEntity<?> listarPorAtivo() {
        try {
            return ResponseEntity.ok(empresasService.listarPorAtivo());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrar(@RequestBody EmpresasDTO empresasDTO) {
        try {
            Empresas empresas = EmpresaConverter.toEntity(empresasDTO);
            this.empresasService.cadastrar(empresas);
            return ResponseEntity.ok("Cadastro feito com sucesso");
        } catch (DataIntegrityViolationException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("ERRO: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }







    @PutMapping(value = "/editar")
    public ResponseEntity<?> editar(@Valid @RequestParam("id") final Long id, @RequestBody final Empresas empresasNovo) {
        try {
            empresasService.editar(id, empresasNovo);
            return ResponseEntity.ok("Empresa atualizada com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    @PutMapping(value = "/desativar")
    public ResponseEntity<?> desativar(@Valid @RequestParam("id") final Long id) {
        try {
            empresasService.desativar(id);
            return ResponseEntity.ok("Empresa desativada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    @PutMapping(value = "/ativar")
    public ResponseEntity<?> ativar(@Valid @RequestParam("id") final Long id) {
        try {
            empresasService.ativar(id);
            return ResponseEntity.ok("Empresa ativada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping(value = "/deletar")
    private ResponseEntity<?> deletar(@Valid @RequestParam("id") final long id) {
        try {
            empresasService.deletar(id);
            return ResponseEntity.ok("Registro deletado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }
}

