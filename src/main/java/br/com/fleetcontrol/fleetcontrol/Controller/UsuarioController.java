package br.com.fleetcontrol.fleetcontrol.Controller;

import br.com.fleetcontrol.fleetcontrol.Entity.Usuario;
import br.com.fleetcontrol.fleetcontrol.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/condutores")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") final Long id){
        final Usuario usuario = this.usuarioService.findById(id);
        return usuario == null
                ? ResponseEntity.badRequest().body("Nenhum valor encontrado.")
                : ResponseEntity.ok(usuario);
    }

    @GetMapping("/lista")
    public ResponseEntity<?> listaCompleta(){
        return ResponseEntity.ok(this.usuarioService.listaCompleta());
    }

    @GetMapping("/lista/ativos")
    public ResponseEntity<?> listaAtivos(){
        return ResponseEntity.ok(this.usuarioService.listaUsuariosAtivos());
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody final Usuario usuario){
        try{
            this.usuarioService.cadastrar(usuario);
            return ResponseEntity.ok().body("Sucesso!, Usuario Cadastrado!");
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity<?> atualizar(
            @PathVariable Long idUsuario,
            @RequestBody Usuario usuario
    ) {
        this.usuarioService.atualizar(idUsuario, usuario);
        return ResponseEntity.ok().body("Usuario atualizado com sucesso!");
    }


    @PutMapping("/desativar/{idUsuario}")
    public ResponseEntity<?> desativar(
            @PathVariable Long idUsuario
    ){
        this.usuarioService.desativar(idUsuario);
        return ResponseEntity.ok().body("Usuario desativado com sucesso!");
    }

}
