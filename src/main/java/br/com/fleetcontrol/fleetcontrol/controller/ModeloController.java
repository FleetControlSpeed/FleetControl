package br.com.fleetcontrol.fleetcontrol.controller;

import br.com.fleetcontrol.fleetcontrol.dto.ModeloConverter;
import br.com.fleetcontrol.fleetcontrol.dto.ModeloDTO;
import br.com.fleetcontrol.fleetcontrol.entity.Modelo;
import br.com.fleetcontrol.fleetcontrol.repository.ModeloRepository;
import br.com.fleetcontrol.fleetcontrol.service.ModeloService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/modelo")
@CrossOrigin(origins = "http://localhost:4200")
public class ModeloController {
    @Autowired
    private ModeloService service;
    @Autowired
    private ModeloRepository modeloRepository;
    private static final String ERROR_MESSAGE_PREFIX = "Error: ";


    @GetMapping("/{id}")
    public ResponseEntity<ModeloDTO> listaId(@PathVariable(value = "id") Long id) {
        Modelo modelo = modeloRepository.findById(id).orElse(null);
        if (modelo == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        ModeloDTO modeloDTO = ModeloConverter.toDTO(modelo);
        return ResponseEntity.ok(modeloDTO);
    }


    @GetMapping("/listar")
    public ResponseEntity<List<ModeloDTO>> listar() {
        List<Modelo> listaModelos = service.listar();
        List<ModeloDTO> listaModelosDTO = ModeloConverter.toDTOList(listaModelos);
        return ResponseEntity.ok(listaModelosDTO);
    }

    @GetMapping("/listarPorAtivo")
    public ResponseEntity<List<ModeloDTO>> listarPorAtivo(@PathVariable boolean ativo) {
        List<Modelo> listaAtivo = modeloRepository.findByAtivo(ativo);
        List<ModeloDTO> listaAtivoDTO = ModeloConverter.toDTOList(listaAtivo);

        return ResponseEntity.ok(listaAtivoDTO);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrar(@RequestBody ModeloDTO modeloDTO) {
        try {
            Modelo modelo = ModeloConverter.toEntity(modeloDTO);
            this.service.cadastrar(modelo);
            return ResponseEntity.ok("Cadastro feito com sucesso");
        } catch (DataIntegrityViolationException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("ERRO: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



    @PutMapping("/put/id/{id}")
    public ResponseEntity<String> atualizar(@PathVariable Long id, @RequestBody ModeloDTO dto) {
        try {
            Modelo modeloAtualizado = ModeloConverter.toEntity(dto);
            this.service.atualizar(id, modeloAtualizado);
            return ResponseEntity.ok().body("Atualizado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/desativar")
    public ResponseEntity<String> desativar(@Valid @RequestParam("id") final Long id) {
        try {
            service.desativar(id);
            return ResponseEntity.ok("Modelos desativada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ERROR_MESSAGE_PREFIX + e.getMessage());
        }
    }

    @PutMapping(value = "/ativar")
    public ResponseEntity<String> ativar(@Valid @RequestParam("id") final Long id) {
        try {
            service.ativar(id);
            return ResponseEntity.ok("Modelos ativada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ERROR_MESSAGE_PREFIX + e.getMessage());
        }
    }

    @DeleteMapping(value = "/deletar")
    public ResponseEntity<String> deletar(@Valid @RequestParam("id") final long id) {
        try {
            service.deletar(id);
            return ResponseEntity.ok("Registro deletado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ERROR_MESSAGE_PREFIX + e.getMessage());
        }
    }
}

