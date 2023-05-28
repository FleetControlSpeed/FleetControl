package br.com.fleetcontrol.fleetcontrol.service;

import br.com.fleetcontrol.fleetcontrol.entity.Veiculo;
import br.com.fleetcontrol.fleetcontrol.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Veiculo salvar(Veiculo veiculo) {
        return this.repository.save(veiculo);
    }

    public void editar(Long id, Veiculo veiculoNovo){
        final Veiculo veiculoBanco= this.buscarPorId(id);

        if(veiculoBanco == null || !veiculoBanco.getId().equals(veiculoNovo.getId())){
            throw new RuntimeException(", não foi possivel identificar o veiculo informado!");

        } else {
            this.salvar(veiculoNovo);
        }
    }

    public void desativar(Long id) {
        Veiculo veiculo = buscarPorId(id);

        if (!veiculo.isAtivo()) {
            throw new RuntimeException(", veiculo informado já esta desativado!");

        } else {
            repository.desativar(id);
        }
    }

    public void ativar(Long id) {
        Veiculo veiculo = buscarPorId(id);

        if (veiculo.isAtivo()) {
            throw new RuntimeException(", veiculo informado já esta ativado!");

        } else {
            repository.ativar(id);
        }
    }
}