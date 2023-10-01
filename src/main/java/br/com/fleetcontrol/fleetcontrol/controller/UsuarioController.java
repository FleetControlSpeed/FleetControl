package br.com.fleetcontrol.fleetcontrol.controller;

import br.com.fleetcontrol.fleetcontrol.dto.EmpresaConverter;
import br.com.fleetcontrol.fleetcontrol.dto.EmpresasDTO;
import br.com.fleetcontrol.fleetcontrol.dto.UsuarioConverter;
import br.com.fleetcontrol.fleetcontrol.dto.UsuarioDTO;
import br.com.fleetcontrol.fleetcontrol.entity.Empresas;
import br.com.fleetcontrol.fleetcontrol.entity.Usuario;
import br.com.fleetcontrol.fleetcontrol.repository.UsuarioRepository;
import br.com.fleetcontrol.fleetcontrol.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> listaId(@PathVariable(value = "id") Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        UsuarioDTO usuarioDTO = UsuarioConverter.toDTO(usuario);
        return ResponseEntity.ok(usuarioDTO);
    }

    @GetMapping(value = "/listar")
    public ResponseEntity<?> listar() {
        try {
            return ResponseEntity.ok(usuarioservice.listar());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
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

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrar(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            Usuario usuario = UsuarioConverter.toEntity(usuarioDTO);
            this.usuarioservice.cadastrar(usuario);
            return ResponseEntity.ok("Cadastro feito com sucesso");
        } catch (DataIntegrityViolationException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("ERRO: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/put/id/{id}")
    public ResponseEntity<String> atualizar(@PathVariable Long id, @RequestBody UsuarioDTO dto) {
        try {
            Usuario usuarioAtualizado = UsuarioConverter.toEntity(dto);
            this.usuarioservice.atualizar(id, usuarioAtualizado);
            return ResponseEntity.ok().body("Atualizado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PutMapping("/desativar")
    public ResponseEntity<?> desativar(@Valid @RequestParam("id") Long idUsuario) {
        try{
            usuarioservice.desativar(idUsuario);
            return ResponseEntity.ok("Usuario desativado com sucesso!");

        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
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
    public ResponseEntity<String> deletar(@Valid @RequestParam("id") final long id) {
        try {
            usuarioservice.deletar(id);
            return ResponseEntity.ok("Registro deletado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }
}
