package br.com.fleetcontrol.fleetcontrol.service;

import br.com.fleetcontrol.fleetcontrol.entity.Modelo;
import br.com.fleetcontrol.fleetcontrol.repository.ModeloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModeloService {

    @Autowired
    public ModeloRepository modelorepository;
    
    public Modelo buscarPorId(Long id) {
        if (id == 0) {
            throw new RuntimeException(", por favor, informe um valor valido!");

        } else if (modelorepository.findById(id).isEmpty()) {
            throw new RuntimeException(", não foi possivel localizar o modelo informado");

        } else {
            return modelorepository.findById(id).orElse(null);
        }
    }

    public List<Modelo> listar() {
        if (modelorepository.findAll().isEmpty()) {
            throw new RuntimeException(", não foi possivel localizar nenhum modelo cadastrado!");

        } else {
            return modelorepository.findAll();
        }
    }

    public void editar(Long id, Modelo modeloNovo){
        final Modelo modeloBanco = this.buscarPorId(id);

        if(modeloBanco == null || !modeloBanco.getId().equals(modeloBanco.getId())){
            throw new RuntimeException(", não foi possivel identificar o modelo informado!");

        } else {
            this.salvar(modeloNovo);
        }
    }

    public Modelo salvar(Modelo modelo) {
            return modelorepository.save(modelo);
    }

    public void desativar(Long id) {
        Modelo modelo  = buscarPorId(id);

        if (!modelo.isAtivo()) {
            throw new RuntimeException(", modelo informado já esta desativado!");

        } else {
            modelorepository.desativar(id);
        }
    }

    public void ativar(Long id) {
        Modelo modelo  = buscarPorId(id);

        if (modelo.isAtivo()) {
            throw new RuntimeException(", modelo informado já esta ativado!");

        } else {
            modelorepository.ativar(id);
        }
    }
}