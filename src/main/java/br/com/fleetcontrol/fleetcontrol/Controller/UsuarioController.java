package br.com.fleetcontrol.fleetcontrol.Controller;

import br.com.fleetcontrol.fleetcontrol.Entity.Usuario;
import br.com.fleetcontrol.fleetcontrol.Entity.Veiculo;
import br.com.fleetcontrol.fleetcontrol.Repository.UsuarioRepository;
import br.com.fleetcontrol.fleetcontrol.Repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping(value = "/api/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<?> CadastroUsuario(@RequestParam("id") final Usuario usuario){
        try{
            this.usuarioRepository.save(usuario);
            return ResponseEntity.ok("Usuario salvo com sucesso");
        }catch(Exception e ){
            return ResponseEntity.badRequest().body("erro de inserção de usuario");

        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> FindByIdRequest(@PathVariable("id") final Long id){
        final Usuario usuario = this.usuarioRepository.findById(id).orElse(null);
        return usuario == null
                ? ResponseEntity.badRequest().body("Nenhum veiculo cadastrado")
                : ResponseEntity.ok(usuario);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> EditarVeiculo(@RequestParam ("id") final Long id, @RequestBody final Veiculo veiculo){
        try{
            final Usuario usuarioBanco = this.usuarioRepository.findById(id).orElse(null);
            if(usuarioBanco == null  || !usuarioBanco.getId().equals(usuarioBanco.getId())){
                throw new RuntimeException("Usuario inserida erroneamente");
            }
            this.usuarioRepository.save(usuarioBanco);
            return ResponseEntity.ok("Usuario editada com sucesso");

        }catch(DataIntegrityViolationException e){
            return ResponseEntity.internalServerError().body("Error"+e.getCause().getCause().getMessage());
        }catch(RuntimeException e){
            return ResponseEntity.internalServerError().body("Error"+e.getMessage());
        }

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarVeiculo(@RequestParam("id") final Long id) {

        final Usuario usuarioBanco = this.usuarioRepository.findById(id).orElse(null);
        this.usuarioRepository.delete(usuarioBanco);
        if(usuarioBanco == null){
            usuarioBanco.setAtivo(false);
            this.usuarioRepository.save(usuarioBanco);
        }
        return ResponseEntity.ok("usuario deletado com sucesso");

    }
}