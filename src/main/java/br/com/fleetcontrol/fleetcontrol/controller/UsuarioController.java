package br.com.fleetcontrol.fleetcontrol.controller;
import br.com.fleetcontrol.fleetcontrol.dto.UsuarioDTO;
import br.com.fleetcontrol.fleetcontrol.dto.UsuarioConverter;
import br.com.fleetcontrol.fleetcontrol.entity.Usuario;
import br.com.fleetcontrol.fleetcontrol.repository.UsuarioRepository;
import br.com.fleetcontrol.fleetcontrol.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/condutores")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioservice;
    @Autowired
    private UsuarioRepository usuarioRepository;
    private static final String ERROR_MESSAGE_PREFIX = "Error: ";

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> listaId(@PathVariable(value = "id") Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        UsuarioDTO usuarioDTO = UsuarioConverter.toDTO(usuario);
        return ResponseEntity.ok(usuarioDTO);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<UsuarioDTO>> listar() {
        List<Usuario> listaUsuarios = usuarioservice.listar();
        List<UsuarioDTO> listaUsuariosDTO = UsuarioConverter.toDTOList(listaUsuarios);
        return ResponseEntity.ok(listaUsuariosDTO);
    }

    @GetMapping("/listarPorAtivo")
    public ResponseEntity<List<UsuarioDTO>> listarPorAtivo(@PathVariable boolean ativo) {
        List<Usuario> listaAtivo = usuarioRepository.findByAtivo(ativo);
        List<UsuarioDTO> listaAtivoDTO = UsuarioConverter.toDTOList(listaAtivo);

        return ResponseEntity.ok(listaAtivoDTO);
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
    public ResponseEntity<String> desativar(@Valid @RequestParam("id") Long idUsuario) {
        try{
            usuarioservice.desativar(idUsuario);
            return ResponseEntity.ok("Usuario desativado com sucesso!");

        } catch (Exception e){
            return ResponseEntity.badRequest().body(ERROR_MESSAGE_PREFIX + e.getMessage());
        }
    }

    @PutMapping("/ativar")
    public ResponseEntity<String> ativar(@Valid @RequestParam("id") Long idUsuario){
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
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ERROR_MESSAGE_PREFIX + e.getMessage());
        }
    }
}
