package br.com.fleetcontrol.fleetcontrol.controller;

import br.com.fleetcontrol.fleetcontrol.entity.Usuario;
import br.com.fleetcontrol.fleetcontrol.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/condutores")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioservice;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") final Long id){
        final Usuario usuario = this.usuarioservice.findById(id);
        return usuario == null
                ? ResponseEntity.badRequest().body("Nenhum valor encontrado.")
                : ResponseEntity.ok(usuario);
    }

    @GetMapping("/lista")
    public ResponseEntity<?> listaCompleta(){
        return ResponseEntity.ok(this.usuarioservice.listaCompleta());
    }

    @GetMapping("/lista/ativos")
    public ResponseEntity<?> listaAtivos(){
        return ResponseEntity.ok(this.usuarioservice.listaUsuariosAtivos());
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody final Usuario usuario){
        try{
            this.usuarioservice.cadastrar(usuario);
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
        this.usuarioservice.atualizar(idUsuario, usuario);
        return ResponseEntity.ok().body("Usuario atualizado com sucesso!");
    }


    @PutMapping("/desativar/{idUsuario}")
    public ResponseEntity<?> desativar(
            @PathVariable Long idUsuario
    ){
        this.usuarioservice.desativar(idUsuario);
        return ResponseEntity.ok().body("Usuario desativado com sucesso!");
    }

}