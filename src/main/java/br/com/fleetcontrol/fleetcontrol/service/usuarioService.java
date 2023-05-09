package br.com.fleetcontrol.fleetcontrol.service;

import br.com.fleetcontrol.fleetcontrol.entity.usuario;
import br.com.fleetcontrol.fleetcontrol.repository.usuarioRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/*
    @Author: Cristovão Martins
 */
@NoArgsConstructor
@Service
public class usuarioService {

    @Autowired
    private usuarioRepository usuariorepository;

    @Transactional
    public usuario cadastrar(usuario usuario) {
        if(usuario.getUsuario().trim().isEmpty()){
            throw  new RuntimeException("Erro usuario Nulo!");
        }else{
            return this.usuariorepository.save(usuario);
        }
    }
    public List<usuario> listaCompleta() {
        return this.usuariorepository.findAll();
    }

    public usuario findById(Long id) {
        return this.usuariorepository.findById(id).orElse(new usuario());
    }
    @Transactional
    public void atualizar(Long id, usuario usuario) {
        if(id == usuario.getId()) {
            this.usuariorepository.save(usuario);
        }else{
            throw new RuntimeException("Erro id não existe para ser atualizado!");
        }
    }

    @Transactional
    public void desativar(Long id){
        var usuario = this.usuariorepository.findById(id);
        if (id == usuario.get().getId()) {
            this.usuariorepository.desativar(id);
        }else{
            throw new RuntimeException("Erro id não existe!");
        }
    }

    public List<usuario> listaUsuariosAtivos(){
            return this.usuariorepository.UsuariosAtivos();
    }
}
