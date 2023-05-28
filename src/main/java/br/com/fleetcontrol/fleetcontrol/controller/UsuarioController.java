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
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        try {
            return ResponseEntity.ok(usuarioservice.buscarPorId(id));

        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error" + e.getMessage());
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listar() {
        try {
            return ResponseEntity.ok(this.usuarioservice.listaCompleta());

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error" + e.getMessage());
        }
    }

    @GetMapping("/listar/ativos")
    public ResponseEntity<?> listarPorAtivo() {
        try {
            return ResponseEntity.ok(this.usuarioservice.listaUsuariosAtivos());

        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error" + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@Valid @RequestBody final Usuario usuario) {
        try {
            usuarioservice.salvar(usuario);
            return ResponseEntity.ok().body("Usuario cadastrado com sucesso!");

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error" + e.getMessage());
        }
    }

    @PutMapping("/editar")
    public ResponseEntity<?> atualizar(@Valid @RequestParam Long idUsuario, @RequestBody Usuario usuarioNovo) {
        try{
            usuarioservice.atualizar(idUsuario,usuarioNovo);
            return ResponseEntity.ok("Usuario alterado com sucesso!");

        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error" + e.getMessage());
        }
    }


    @PutMapping("/desativar")
    public ResponseEntity<?> desativar(@Valid @RequestParam Long idUsuario) {
        try {
            usuarioservice.desativar(idUsuario);
            return ResponseEntity.ok().body("Usuario desativado com sucesso!");

        } catch (Exception e) {
            throw new RuntimeException("Error" + e.getMessage());
        }
    }

    @PutMapping("/ativar")
    public ResponseEntity<?> ativar(@Valid @RequestParam Long idUsuario) {
        try {
            usuarioservice.ativar(idUsuario);
            return ResponseEntity.ok().body("Usuario ativado com sucesso!");

        } catch (Exception e) {
            throw new RuntimeException("Error" + e.getMessage());
        }
    }

    @DeleteMapping(value = "/deletar")
    private ResponseEntity<?> deletar(@Valid @RequestParam("id") final long id){
        try {
            usuarioservice.deletar(id);
            return ResponseEntity.ok("Registro deletado com sucesso!");

        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error" + e.getMessage());
        }
    }
}
