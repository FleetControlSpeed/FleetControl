package br.com.fleetcontrol.fleetcontrol.Controller;

import br.com.fleetcontrol.fleetcontrol.Entity.Veiculo;
import br.com.fleetcontrol.fleetcontrol.Repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping(value = "/api/veiculo")
public class VeiculoController {
    @Autowired
    private VeiculoRepository veiculoRepository;

    @PostMapping
    public ResponseEntity<?> CadastroVeiculo(@RequestParam("id") final Veiculo veiculo ){
        try{
            this.veiculoRepository.save(veiculo);
            return ResponseEntity.ok("Veiculo salvo com sucesso");
        }catch(Exception e ){
            return ResponseEntity.badRequest().body("erro de inserção de veiculo");

        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> FindByIdRequest(@PathVariable("id") final Long id){
        final Veiculo veiculo = this.veiculoRepository.findById(id).orElse(null);
        return veiculo == null
                ? ResponseEntity.badRequest().body("Nenhum veiculo cadastrado")
                : ResponseEntity.ok(veiculo);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> EditarVeiculo(@RequestParam ("id") final Long id, @RequestBody final Veiculo veiculo){
        try{
            final Veiculo veiculoBanco = this.veiculoRepository.findById(id).orElse(null);
            if(veiculoBanco == null  || !veiculoBanco.getId().equals(veiculoBanco.getId())){
                throw new RuntimeException("Erro de inserção de veiculo ");
            }
            this.veiculoRepository.save(veiculo);
            return ResponseEntity.ok("Veiculo editado");

        }catch(DataIntegrityViolationException e){
            return ResponseEntity.internalServerError().body("Error"+e.getCause().getCause().getMessage());
        }catch(RuntimeException e){
            return ResponseEntity.internalServerError().body("Error"+e.getMessage());
        }

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarVeiculo(@RequestParam("id") final Long id) {

        final Veiculo veiculoBanco = this.veiculoRepository.findById(id).orElse(null);
        this.veiculoRepository.delete(veiculoBanco);
        if(veiculoBanco == null){
            veiculoBanco.setAtivo(false);
            this.veiculoRepository.save(veiculoBanco);
        }
        return ResponseEntity.ok("veiculo deletado com sucesso");

    }
}


