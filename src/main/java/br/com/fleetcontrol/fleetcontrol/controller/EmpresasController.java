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

import java.util.List;

@RestController
@RequestMapping(value = "/api/empresas")
public class EmpresasController {
    @Autowired
    private EmpresasService empresasService;
    @Autowired
    private EmpresasRepository empresasRepository;
    private static final String ERROR_MESSAGE_PREFIX = "Error: ";

    @GetMapping("/{id}")
    public ResponseEntity<EmpresasDTO> listaId(@PathVariable(value = "id") Long id) {
        Empresas empresas = empresasRepository.findById(id).orElse(null);
        if (empresas == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        EmpresasDTO empresasDTO = EmpresaConverter.toDTO(empresas);
        return ResponseEntity.ok(empresasDTO);
    }
    @GetMapping("/listar")
    public ResponseEntity<List<EmpresasDTO>> listar() {
        List<Empresas> listaEmpresas = empresasService.listar();
        List<EmpresasDTO> listaEmpresasDTO = EmpresaConverter.toDTOList(listaEmpresas);
        return ResponseEntity.ok(listaEmpresasDTO);
    }
    @GetMapping("/listarPorAtivo")
    public ResponseEntity<List<EmpresasDTO>> listarPorAtivo(@PathVariable boolean ativo) {
        List<Empresas> listaAtivo = empresasRepository.findByAtivo(ativo);
        List<EmpresasDTO> listaAtivoDTO = EmpresaConverter.toDTOList(listaAtivo);

        return ResponseEntity.ok(listaAtivoDTO);
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

    @PutMapping("/put/id/{id}")
    public ResponseEntity<String> atualizar(@PathVariable Long id, @RequestBody EmpresasDTO dto) {
        try {
            Empresas empresaAtualizada = EmpresaConverter.toEntity(dto);
            this.empresasService.atualizar(id, empresaAtualizada);
            return ResponseEntity.ok().body("Atualizado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/desativar")
    public ResponseEntity<String> desativar(@Valid @RequestParam("id") final Long id) {
        try {
            empresasService.desativar(id);
            return ResponseEntity.ok("Empresa desativada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ERROR_MESSAGE_PREFIX + e.getMessage());
        }
    }

    @PutMapping(value = "/ativar")
    public ResponseEntity<String> ativar(@Valid @RequestParam("id") final Long id) {
        try {
            empresasService.ativar(id);
            return ResponseEntity.ok("Empresa ativada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ERROR_MESSAGE_PREFIX + e.getMessage());
        }
    }

    @DeleteMapping(value = "/deletar")
    public ResponseEntity<String> deletar(@Valid @RequestParam("id") final long id) {
        try {
            empresasService.deletar(id);
            return ResponseEntity.ok("Registro deletado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ERROR_MESSAGE_PREFIX + e.getMessage());
        }
    }
}

