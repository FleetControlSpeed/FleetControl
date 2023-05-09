package br.com.fleetcontrol.fleetcontrol.controller;

import br.com.fleetcontrol.fleetcontrol.entity.veiculo;
import br.com.fleetcontrol.fleetcontrol.repository.veiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping(value = "/api/veiculo")
public class veiculoController {
    @Autowired
    private veiculoRepository veiculorepository;

    @PostMapping
    public ResponseEntity<?> CadastroVeiculo(@RequestParam("id") final veiculo veiculo ){
        try{
            this.veiculorepository.save(veiculo);
            return ResponseEntity.ok("Veiculo salvo com sucesso");
        }catch(Exception e ){
            return ResponseEntity.badRequest().body("erro de inserção de veiculo");

        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> FindByIdRequest(@PathVariable("id") final Long id){
        final veiculo veiculo = this.veiculorepository.findById(id).orElse(null);
        return veiculo == null
                ? ResponseEntity.badRequest().body("Nenhum veiculo cadastrado")
                : ResponseEntity.ok(veiculo);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> EditarVeiculo(@RequestParam ("id") final Long id, @RequestBody final veiculo veiculo){
        try{
            final br.com.fleetcontrol.fleetcontrol.entity.veiculo veiculoBanco = this.veiculorepository.findById(id).orElse(null);
            if(veiculoBanco == null  || !veiculoBanco.getId().equals(veiculoBanco.getId())){
                throw new RuntimeException("Erro de inserção de veiculo ");
            }
            this.veiculorepository.save(veiculo);
            return ResponseEntity.ok("Veiculo editado");

        }catch(DataIntegrityViolationException e){
            return ResponseEntity.internalServerError().body("Error"+e.getCause().getCause().getMessage());
        }catch(RuntimeException e){
            return ResponseEntity.internalServerError().body("Error"+e.getMessage());
        }

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarVeiculo(@RequestParam("id") final Long id) {

        final veiculo veiculoBanco = this.veiculorepository.findById(id).orElse(null);
        this.veiculorepository.delete(veiculoBanco);
        if(veiculoBanco == null){
            veiculoBanco.setAtivo(false);
            this.veiculorepository.save(veiculoBanco);
        }
        return ResponseEntity.ok("veiculo deletado com sucesso");

    }
}


