package br.com.fleetcontrol.fleetcontrol.service;

import br.com.fleetcontrol.fleetcontrol.entity.Usuario;
import br.com.fleetcontrol.fleetcontrol.repository.UsuarioRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/*
    @Author: Cristovão Martins
 */
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuariorepository;

    public Usuario buscarPorId(Long id) {
        if (id == 0) {
            throw new RuntimeException(", por favor, informe um valor valido!");

        } else if (usuariorepository.findById(id).isEmpty()) {
            throw new RuntimeException(", não foi possivel localizar o usuario informado!");

        } else {
            return usuariorepository.findById(id).orElse(null);
        }
    }

    public List<Usuario> listaCompleta() {
        if(usuariorepository.findAll().isEmpty()){
            throw new RuntimeException(", não foi possivel localizar nenhum usuario cadastrado!");
        }
        return usuariorepository.findAll();
    }

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
}