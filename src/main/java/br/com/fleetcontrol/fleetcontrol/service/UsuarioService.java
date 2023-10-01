package br.com.fleetcontrol.fleetcontrol.service;

import br.com.fleetcontrol.fleetcontrol.entity.Empresas;
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

    public List<Usuario> listar() {
        if (usuariorepository.findAll().isEmpty()) {
            throw new RuntimeException("Não foi possível localizar nenhuma empresa cadastrada!");
        } else {
            return usuariorepository.findAll();
        }
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
    public Usuario cadastrar(Usuario usuario) {
            return usuariorepository.save(usuario);
    }

    public Usuario atualizar(Long id, Usuario usuarioAtualizado) {
        Usuario usuarioExistente = usuariorepository.findById(id).orElse(null);
        if (usuarioExistente == null) {
            return null;
        } else {
            usuarioExistente.setEmail(usuarioAtualizado.getEmail());
            usuarioExistente.setUsuario(usuarioAtualizado.getUsuario());
            usuarioExistente.setSenha(usuarioAtualizado.getSenha());
            usuarioExistente.setCargo(usuarioAtualizado.getCargo());
            usuarioExistente.setPrimeiroNome(usuarioAtualizado.getPrimeiroNome());
            usuarioExistente.setSobrenome(usuarioAtualizado.getSobrenome());
            usuarioExistente.setCpf(usuarioAtualizado.getCpf());
            usuarioExistente.setTelefone(usuarioAtualizado.getTelefone());
            usuarioExistente.setDataNascimento(usuarioAtualizado.getDataNascimento());
            usuarioExistente.setEndereco(usuarioAtualizado.getEndereco());
            return usuariorepository.save(usuarioExistente);
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