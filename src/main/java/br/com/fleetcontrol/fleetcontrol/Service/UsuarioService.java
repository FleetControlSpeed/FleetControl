package br.com.fleetcontrol.fleetcontrol.Service;

import br.com.fleetcontrol.fleetcontrol.Entity.Usuario;
import br.com.fleetcontrol.fleetcontrol.Repository.UsuarioRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@NoArgsConstructor
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario cadastrar(Usuario usuario) {
        if(usuario.getUsuario().trim().isEmpty()){
            throw  new RuntimeException("Erro usuario Nulo!");
        }else{
            return this.usuarioRepository.save(usuario);
        }
    }
    public List<Usuario> listaCompleta() {
        return this.usuarioRepository.findAll();
    }

    public Usuario findById(Long id) {
        return this.usuarioRepository.findById(id).orElse(new Usuario());
    }
    @Transactional
    public void atualizar(Long id, Usuario usuario) {
        if(id == usuario.getId()) {
            this.usuarioRepository.save(usuario);
        }
    }

    @Transactional
    public void desativar(Long id){
        var usuario = this.usuarioRepository.findById(id);
        if (id == usuario.get().getId()) {
            this.usuarioRepository.desativar(id);
        }
    }

    public List<Usuario> listaUsuariosAtivos(){
            return this.usuarioRepository.UsuariosAtivos();
    }
}
