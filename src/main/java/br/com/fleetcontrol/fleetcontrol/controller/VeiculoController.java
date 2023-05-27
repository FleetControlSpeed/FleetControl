package br.com.fleetcontrol.fleetcontrol.controller;

import br.com.fleetcontrol.fleetcontrol.entity.Veiculo;
import br.com.fleetcontrol.fleetcontrol.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping(value = "/api/veiculo")
public class VeiculoController {
    @Autowired
    private VeiculoService veiculoService;

    @PostMapping
    public ResponseEntity<?> CadastroVeiculo(@RequestParam("id") final Veiculo veiculo) {
        try {
            this.veiculoService.salvar(veiculo);
            return ResponseEntity.ok("Veiculo salvo com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("erro de inserção de veiculo");

        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> FindByIdRequest(@PathVariable("id") final Long id) {
        final Veiculo veiculo = this.veiculoService.buscarPorId(id);
        return veiculo == null
                ? ResponseEntity.badRequest().body("Nenhum veiculo cadastrado")
                : ResponseEntity.ok(veiculo);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> EditarVeiculo(@RequestParam("id") final Long id, @RequestBody final Veiculo veiculo) {
        try {
            final Veiculo veiculoBanco = this.veiculoService.buscarPorId(id);
            if (veiculoBanco == null || !veiculoBanco.getId().equals(veiculoBanco.getId())) {
                throw new RuntimeException("Erro de inserção de veiculo ");
            }
            this.veiculoService.salvar(veiculo);
            return ResponseEntity.ok("Veiculo editado");

        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.internalServerError().body("Error" + e.getCause().getCause().getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body("Error" + e.getMessage());
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
}


