package br.com.fleetcontrol.fleetcontrol.service;


import br.com.fleetcontrol.fleetcontrol.dto.ModeloConverter;
import br.com.fleetcontrol.fleetcontrol.dto.ModeloDTO;
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
        } else if (repository.findById(id).isEmpty()) {
            throw new RuntimeException("Não foi possível localizar o modelo informado!");
        } else {
            return repository.findById(id).orElse(null);
        }
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
    public void cadastrar(final ModeloDTO DTO) {
        Modelo modelo = ModeloConverter.toEntity(DTO);
        repository.save(modelo);
    }


    @Transactional
    public void editar(Long id, Modelo Novo) {
        final Modelo Banco = this.buscarPorId(id);

        if (Banco == null || !Banco.getId().equals(Banco.getId())) {
            throw new RuntimeException("Não foi possível identificar o modelo informado!");
        } else {
            salvar(Novo);
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