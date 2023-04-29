package br.com.fleetcontrol.fleetcontrol.Controller;

import br.com.fleetcontrol.fleetcontrol.Entity.Modelo;
import br.com.fleetcontrol.fleetcontrol.Entity.Usuario;
import br.com.fleetcontrol.fleetcontrol.Entity.Veiculo;
import br.com.fleetcontrol.fleetcontrol.Repository.ModeloRepository;
import br.com.fleetcontrol.fleetcontrol.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping(value = "/api/modelo")
public class ModeloController {
    @Autowired
    private ModeloRepository modeloRepository;

    @PostMapping
    public ResponseEntity<?> CadastroModelo(@RequestParam("id") final Modelo modelo){
        try{
            this.modeloRepository.save(modelo);
            return ResponseEntity.ok("Modelo salvo com sucesso");
        }catch(Exception e ){
            return ResponseEntity.badRequest().body("erro de inserção de modelo");

        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> FindByIdRequest(@PathVariable("id") final Long id){
        final Modelo modelo = this.modeloRepository.findById(id).orElse(null);
        return modelo == null
                ? ResponseEntity.badRequest().body("Nenhum veiculo cadastrado")
                : ResponseEntity.ok(modelo);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> EditarModelo(@RequestParam ("id") final Long id, @RequestBody final Modelo modelo ){
        try{
            final Modelo modeloBanco = this.modeloRepository.findById(id).orElse(null);
            if(modeloBanco == null  || !modeloBanco.getId().equals(modeloBanco.getId())){
                throw new RuntimeException("Modelo inserido erroneamente");
            }
            this.modeloRepository.save(modeloBanco);
            return ResponseEntity.ok("Modelo editado com sucesso");

        }catch(DataIntegrityViolationException e){
            return ResponseEntity.internalServerError().body("Error"+e.getCause().getCause().getMessage());
        }catch(RuntimeException e){
            return ResponseEntity.internalServerError().body("Error"+e.getMessage());
        }

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarModelo(@RequestParam("id") final Long id) {

        final Modelo modeloBanco = this.modeloRepository.findById(id).orElse(null);
        this.modeloRepository.delete(modeloBanco);
        if(modeloBanco == null){
            modeloBanco.setAtivo(false);
            this.modeloRepository.save(modeloBanco);
        }
        return ResponseEntity.ok("modelo deletado com sucesso");

    }

}
