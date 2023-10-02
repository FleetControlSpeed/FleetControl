package br.com.fleetcontrol.fleetcontrol.controller;

import br.com.fleetcontrol.fleetcontrol.entity.Usuario;
import br.com.fleetcontrol.fleetcontrol.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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
            return ResponseEntity.ok(usuarioservice.buscarPorId(id));
    }

    @GetMapping("/listar")
    public ResponseEntity<Page<Usuario>> listar(Pageable pageable) {
        try{
            Page<Usuario> usuarios =  usuarioservice.listaCompleta(pageable);
            return ResponseEntity.ok(usuarios);
        }catch (RuntimeException e){
            return ResponseEntity.notFound().build();
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
    public ResponseEntity<?> cadastrar(@Valid @RequestBody Usuario usuario) {
        usuario = usuarioservice.salvar(usuario);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(usuario);
    }

    @PutMapping("/editar")
    public ResponseEntity<?> atualizar(@Valid @RequestParam("id") Long idUsuario, @RequestBody Usuario usuarioNovo) {
        try{
            usuarioservice.atualizar(idUsuario,usuarioNovo);
            return ResponseEntity.ok("Usuario alterado com sucesso!");

        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error" + e.getMessage());
        }
    }


    @PutMapping("/desativar")
    public ResponseEntity<?> desativar(@Valid @RequestParam("id") Long idUsuario) {
        try{
            usuarioservice.desativar(idUsuario);
            return ResponseEntity.ok("Usuario desativado com sucesso!");

        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error" + e.getMessage());
        }
    }

    @PutMapping("/ativar")
    public ResponseEntity<?> ativar(@Valid @RequestParam("id") Long idUsuario){
        try{
            usuarioservice.ativar(idUsuario);
            return ResponseEntity.ok("Usuario ativado com sucesso!");

        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error" + e.getMessage());
        }
    }

    @DeleteMapping(value = "/deletar")
    private ResponseEntity<?> deletar(@Valid @RequestParam("id") final long id){
        usuarioservice.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
