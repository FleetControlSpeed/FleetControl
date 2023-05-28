package br.com.fleetcontrol.fleetcontrol.controller;

import br.com.fleetcontrol.fleetcontrol.entity.Usuario;
import br.com.fleetcontrol.fleetcontrol.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/condutores")
public class UsuarioController {

    /*
    {
    "id": 1,
    "cadastro": "2023-05-27T22:37:03.119999",
    "edicao": null,
    "ativo": true,
    "email": "pedrohenri1606@gmail.com",
    "usuario": "pedro",
    "senha": "123",
    "cargo": "ADMINISTRADOR",
    "primeiroNome": "Pedro",
    "sobrenome": "Henrique",
    "cpf": "10250870975",
    "telefone": "45 998265476",
    "dataNascimento": "29/07/2003",
    "endereco": "Rua Belmiro numero 2",
    "eventos": null
    }
     */

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
    public ResponseEntity<?> cadastrar(@Valid @RequestBody final Usuario usuario){
        try{
            usuarioservice.cadastrar(usuario);
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
