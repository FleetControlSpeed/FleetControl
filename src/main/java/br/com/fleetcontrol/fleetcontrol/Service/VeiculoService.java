package br.com.fleetcontrol.fleetcontrol.Service;

import br.com.fleetcontrol.fleetcontrol.Entity.Veiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeiculoService {
    @Autowired
    private br.com.fleetcontrol.fleetcontrol.Repository.VeiculoRepository repository;
    public Veiculo buscarPorId(Long id) {

        if (id == 0) {
            throw new RuntimeException(", por favor, informe um valor valido!");

        } else if (repository.findById(id).isEmpty()) {
            throw new RuntimeException(", não foi possivel localizar o condutor informado!");

        } else {
            return repository.findById(id).orElse(null);
        }
    }

    public List<Veiculo> listar() {
        if (repository.findAll().isEmpty()) {
            throw new RuntimeException(", banco de dados não possui veiculos cadastrados!");

        } else {
            return repository.findAll();
        }
    }

    public List<Veiculo> listarPorAtivo() {
        if (repository.buscarPorAtivo().isEmpty()) {
            throw new RuntimeException(", banco de dados não possui Veiculos ativos!");

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
            throw new RuntimeException(", não foi possivel identificar o evento informado!");

        } else {
            this.salvar(veiculoNovo);
        }
    }

    public void desativar(Long id) {
        Veiculo veiculo = buscarPorId(id);

        if (!veiculo.isAtivo()) {
            throw new RuntimeException(", evento informado já esta desativado!");

        } else {
            repository.desativar(id);
        }
    }

    public void ativar(Long id) {
        Veiculo veiculo = buscarPorId(id);

        if (veiculo.isAtivo()) {
            throw new RuntimeException(", evento informado já esta ativado!");
        } else {
            repository.ativar(id);
        }
    }
}
