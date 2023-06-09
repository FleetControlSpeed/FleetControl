package br.com.fleetcontrol.fleetcontrol.service;

import br.com.fleetcontrol.fleetcontrol.entity.Eventos;
import br.com.fleetcontrol.fleetcontrol.entity.Usuario;
import br.com.fleetcontrol.fleetcontrol.repository.UsuarioRepository;
import br.com.fleetcontrol.fleetcontrol.service.exceptions.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
/*
    @Author: Cristovão Martins
 */
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuariorepository;

    @Transactional(readOnly = true)
    public Usuario buscarPorId(Long id) {
        Usuario usuario = usuariorepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Usuario não encontrado!"));
        return usuario;
    }

    @Transactional(readOnly = true)
    public Page<Usuario> listaCompleta(Pageable pageable) {
        Page<Usuario> resultado = usuariorepository.findAll(pageable);
        return resultado;
    }

    @Transactional(readOnly = true)
    public List<Usuario> listaUsuariosAtivos() {
        if (usuariorepository.usuariosAtivos().isEmpty()) {
            throw new RuntimeException(", não foi possivel localizar usuarios ativos!");

        } else {
            return usuariorepository.usuariosAtivos();
        }
    }

    @Transactional
    public Usuario salvar(Usuario usuario) {
            return usuariorepository.save(usuario);
    }

    @Transactional
    public void atualizar(Long id, Usuario usuarioNovo) {
        Usuario usuarioBanco = buscarPorId(id);

        if(usuarioNovo == null || !usuarioNovo.getId().equals(usuarioBanco.getId())){
            throw new RuntimeException(", não foi possivel identificar o usuario informado!");

        } else {
            salvar(usuarioNovo);
        }
    }

    @Transactional
    public void desativar(Long id){
        Usuario usuarioBanco = buscarPorId(id);

        if(!usuarioBanco.isAtivo()){
            throw new RuntimeException(", usuario informado já esta desativado!");

        } else {
            usuariorepository.desativar(id);
        }
    }

    @Transactional
    public void ativar(Long id){
        Usuario usuarioBanco = buscarPorId(id);

        if(usuarioBanco.isAtivo()){
            throw new RuntimeException(", usuario informado já esta ativado!");

        } else {
            usuariorepository.ativar(id);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void deletar(Long id){
        Usuario usuario = buscarPorId(id);

        List<Eventos> usuarios = usuariorepository.buscaUsuarioPorEvento(id);

        if(usuarios.isEmpty()){
            this.usuariorepository.deleteById(id);

        }else if(!usuariorepository.existsById(id)){
            throw new ResourceNotFoundException("Usuario não encontrado!");
        } else {
            usuariorepository.desativar(usuario.getId());
            throw new RuntimeException(", usuario possui eventos cadastrados ativos, usuario desativado!");
        }
    }
}