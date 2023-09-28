package br.com.fleetcontrol.fleetcontrol.service;


import br.com.fleetcontrol.fleetcontrol.entity.Empresas;
import br.com.fleetcontrol.fleetcontrol.entity.Modelo;
import br.com.fleetcontrol.fleetcontrol.entity.Veiculo;
import br.com.fleetcontrol.fleetcontrol.repository.ModeloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ModeloService {

    @Autowired
    private ModeloRepository repository;

    public Modelo buscarPorId(Long id) {
        if (id == 0) {
            throw new RuntimeException("Por favor, informe um valor válido!");
        }
        Modelo modelo = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Não foi possível localizar a empresa informada!"));
        return modelo;
    }

    public List<Modelo> listar() {
        if (repository.findAll().isEmpty()) {
            throw new RuntimeException("Não foi possível localizar nenhum modelo cadastrado!");
        } else {
            return repository.findAll();
        }
    }

    public List<Modelo> listarPorAtivo() {
        if (repository.modelosAtivos().isEmpty()) {
            throw new RuntimeException("Não foi possível localizar nenhum modelo ativo cadastrado!");
        } else {
            return repository.modelosAtivos();
        }
    }

    @Transactional
    public Modelo salvar(Modelo modelos) {
        return repository.save(modelos);
    }


    @Transactional(rollbackFor = Exception.class)
    public Modelo cadastrar(Modelo cadastrar) {
        return this.repository.save(cadastrar);
    }


    public Modelo atualizar(Long id, Modelo modeloAtualizado) {
        Modelo modeloExistente = repository.findById(id).orElse(null);
        if (modeloExistente == null) {
            return null;
        } else {
            modeloExistente.setNome(modeloAtualizado.getNome());
            modeloExistente.setMarca(modeloAtualizado.getMarca());
            return repository.save(modeloExistente);
        }
    }

    @Transactional
    public void desativar(Long id) {
        Modelo modelos = buscarPorId(id);

        if (!modelos.isAtivo()) {
            throw new RuntimeException("Modelo informado já está desativado!");
        } else {
            repository.desativar(id);
        }
    }

    @Transactional
    public void ativar(Long id) {
        Modelo modelos = buscarPorId(id);

        if (modelos.isAtivo()) {
            throw new RuntimeException("modelo informado já está ativado!");
        } else {
            repository.ativar(id);
        }
    }

    @Transactional
    public void deletar(Long id){
        Modelo modelo = buscarPorId(id);
        List<Veiculo> veiculos = repository.buscaModeloPorVeiculo(id);
        if(veiculos.isEmpty()){
            repository.deleteById(id);
        } else {
            repository.desativar(modelo.getId());
            throw new RuntimeException(", modelo possui veiculos cadastrados ativos, modelo desativado!");
        }
    }
}