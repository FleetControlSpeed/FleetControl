package br.com.fleetcontrol.fleetcontrol.service;

import br.com.fleetcontrol.fleetcontrol.entity.Modelo;
import br.com.fleetcontrol.fleetcontrol.repository.ModeloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/*
 Ademar Ramalho
 */

@Service
public class ModeloService {

    @Autowired
    public ModeloRepository modelorepository;


    //verifica por id
    public Modelo buscarPorId(Long id) {
        if (id == 0) {
            throw new RuntimeException("insira um id valido");

        } else if (modelorepository.findById(id).isEmpty()) {
            throw new RuntimeException("nao aceitamos id's em branco ou repitido");

        } else {
            return modelorepository.findById(id).orElse(null);
        }
    }



    //verifica se existem modelos cadastrados na listagem
    public List<Modelo> listar() {
        if (modelorepository.findAll().isEmpty()) {
            throw new RuntimeException(",não existem modelos cadastrados!");

        } else {
            return modelorepository.findAll();
        }
    }

    //verifica se é possível editar o modelo informado
    public void editar(Long id, Modelo modeloNovo){
        final Modelo modeloBanco = this.buscarPorId(id);

        if(modeloBanco == null || !modeloBanco.getId().equals(modeloBanco.getId())){
            throw new RuntimeException(", não foi possivel identificar o modelo informado!");

        } else {
            this.salvar(modeloNovo);
        }
    }

    //verifica se é possivel salvar modelos ao banco
    public Modelo salvar(Modelo modelo) {

        if (modelo.getMarca() == null) {
            throw new RuntimeException("marca nao inserida, insira uma marca");

        } else if (modelo.getNome() == null) {
            throw new RuntimeException("nome nao identificado");

        } else {
            return modelorepository.save(modelo);
        }

    }

    public void desativar(Long id) {
        Modelo modelo  = buscarPorId(id);

        if (!modelo.isAtivo()) {
            throw new RuntimeException(", modelo informado já esta desativado!");

        } else {
            modelorepository.desativar(id);
        }
    }
}