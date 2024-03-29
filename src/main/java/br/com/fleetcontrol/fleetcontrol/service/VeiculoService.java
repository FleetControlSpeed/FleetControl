package br.com.fleetcontrol.fleetcontrol.service;

import br.com.fleetcontrol.fleetcontrol.entity.Empresas;
import br.com.fleetcontrol.fleetcontrol.entity.Eventos;
import br.com.fleetcontrol.fleetcontrol.entity.Modelo;
import br.com.fleetcontrol.fleetcontrol.entity.Veiculo;
import br.com.fleetcontrol.fleetcontrol.entity.enums.Cor;
import br.com.fleetcontrol.fleetcontrol.entity.enums.Tipo;
import br.com.fleetcontrol.fleetcontrol.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VeiculoService {
    @Autowired
    private VeiculoRepository repository;
    public Veiculo buscarPorId(Long id) {

        if (id == 0) {
            throw new RuntimeException(", por favor, informe um valor valido!");

        } else if (repository.findById(id).isEmpty()) {
            throw new RuntimeException(", não foi possivel localizar o veiculo informado!");

        } else {
            return repository.findById(id).orElse(null);
        }
    }

    public List<Veiculo> listar() {
        if (repository.findAll().isEmpty()) {
            throw new RuntimeException(", não foi possivel localizar nenhum veiculo cadastrado!");

        } else {
            return repository.findAll();
        }
    }

    public List<Veiculo> listarPorAtivo() {
        if (repository.buscarPorAtivo().isEmpty()) {
            throw new RuntimeException(", não foi possui localizar veiculos ativos cadastrados!");

        } else {
            return repository.buscarPorAtivo();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public Veiculo cadastrar(Veiculo cadastrar) {
        return this.repository.save(cadastrar);
    }

    public Veiculo atualizar(Long id, Veiculo veiculoAtualizado) {
        Veiculo veiculoExistente = repository.findById(id).orElse(null);
        if (veiculoExistente == null) {
            return null;
        } else {
            veiculoExistente.setModelo(veiculoAtualizado.getModelo());
            veiculoExistente.setPlaca(veiculoAtualizado.getPlaca());
            veiculoExistente.setAno(veiculoAtualizado.getAno());
            veiculoExistente.setCor(veiculoAtualizado.getCor());
            veiculoExistente.setKm(veiculoAtualizado.getKm());
            veiculoExistente.setTipo(veiculoAtualizado.getTipo());
            return repository.save(veiculoExistente);
        }
    }
    @Transactional
    public void desativar(Long id) {
        Veiculo veiculo = buscarPorId(id);

        if (!veiculo.isAtivo()) {
            throw new RuntimeException(", veiculo informado já esta desativado!");

        } else {
            repository.desativar(id);
        }
    }

    @Transactional
    public void ativar(Long id) {
        Veiculo veiculo = buscarPorId(id);

        if (veiculo.isAtivo()) {
            throw new RuntimeException(", veiculo informado já esta ativado!");

        } else {
            repository.ativar(id);
        }
    }
    @Transactional
    public void deletar(Long id){
        Veiculo veiculo = buscarPorId(id);

        List<Eventos> veiculos = repository.buscaVeiculoPorEvento(id);

        if(veiculos.isEmpty()){
            this.repository.deleteById(id);

        } else {
            repository.desativar(veiculo.getId());
            throw new RuntimeException(", veiculo possui eventos cadastrados ativos, veiculo desativado!");
        }
    }
}