package br.com.fleetcontrol.fleetcontrol.controller;

import br.com.fleetcontrol.fleetcontrol.dto.EmpresaConverter;
import br.com.fleetcontrol.fleetcontrol.dto.EmpresasDTO;
import br.com.fleetcontrol.fleetcontrol.dto.MultaConverter;
import br.com.fleetcontrol.fleetcontrol.dto.MultaDTO;
import br.com.fleetcontrol.fleetcontrol.entity.Empresas;
import br.com.fleetcontrol.fleetcontrol.repository.MultaRepository;
import br.com.fleetcontrol.fleetcontrol.service.MultaService;
import br.com.fleetcontrol.fleetcontrol.entity.Multa;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/multa")
@CrossOrigin(origins = "http://localhost:4200")
public class MultaController {
    @Autowired
    private MultaService multaService;
    @Autowired
    private MultaRepository multaRepository;

    @GetMapping("/{id}")
    public ResponseEntity<MultaDTO> listaId(@PathVariable(value = "id") Long id) {
        Multa multa = multaRepository.findById(id).orElse(null);
        if (multa == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        MultaDTO multaDTO = MultaConverter.toDTO(multa);
        return ResponseEntity.ok(multaDTO);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<MultaDTO>> listar() {
        List<Multa> listaMultas = multaService.listar();
        List<MultaDTO> listaMultasDTO = MultaConverter.toDTOList(listaMultas);
        return ResponseEntity.ok(listaMultasDTO);
    }
    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrar(@RequestBody MultaDTO multaDTO) {
        try {
            Multa multa = MultaConverter.toEntity(multaDTO);
            this.multaService.cadastrar(multa);
            return ResponseEntity.ok("Cadastro feito com sucesso");
        } catch (DataIntegrityViolationException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("ERRO: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/editar")
    public ResponseEntity<?> atualizar(@Valid @RequestParam("id") Long idMulta, @RequestBody Multa multaNovo) {
        try{
            multaService.atualizar(idMulta,multaNovo);
            return ResponseEntity.ok("Multa alterada com sucesso!");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error" + e.getMessage());
        }
    }


    @PutMapping("/desativar")
    public ResponseEntity<?> desativar(@Valid @RequestParam("id") Long idMulta) {
        try{
            multaService.desativar(idMulta);
            return ResponseEntity.ok("Multa desativada com sucesso!");

        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error" + e.getMessage());
        }
    }
    @PutMapping("/ativar")
    public ResponseEntity<?> ativar(@Valid @RequestParam("id") Long idMulta) {
        try{
            multaService.ativar(idMulta);
            return ResponseEntity.ok("Multa ativada com sucesso!");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error" + e.getMessage());
        }
    }

}
