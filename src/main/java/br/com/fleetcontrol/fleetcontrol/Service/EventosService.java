package br.com.fleetcontrol.fleetcontrol.Service;

import br.com.fleetcontrol.fleetcontrol.Entity.Eventos;
import br.com.fleetcontrol.fleetcontrol.Repository.EventosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
    @Author: Pedro Henrique Vieira
    Date: 07/05/2023
 */

@Service
public class EventosService {

    @Autowired
    private EventosRepository repository;

    public Eventos buscarPorId(Long id) {

        if (id == 0) {
            throw new RuntimeException(", por favor, informe um valor valido!");

        } else if (repository.findById(id).isEmpty()) {
            throw new RuntimeException(", não foi possivel localizar o condutor informado!");

        } else {
            return repository.findById(id).orElse(null);
        }
    }

    public List<Eventos> listar() {
        if (repository.findAll().isEmpty()) {
            throw new RuntimeException(", banco de dados não possui eventos cadastrados!");

        } else {
            return repository.findAll();
        }
    }

    public List<Eventos> listarPorAtivo() {
        if (repository.buscarPorAtivo().isEmpty()) {
            throw new RuntimeException(", banco de dados não possui eventos ativos!");

        } else {
            return repository.buscarPorAtivo();
        }
    }

    public Eventos salvar(Eventos eventos) {
            return this.repository.save(eventos);
    }

    public void editar(Long id, Eventos eventosNovo){
        final Eventos eventosBanco = this.buscarPorId(id);

        if(eventosBanco == null || !eventosBanco.getId().equals(eventosNovo.getId())){
            throw new RuntimeException(", não foi possivel identificar o evento informado!");

        } else {
            this.salvar(eventosNovo);
        }
    }

    public void desativar(Long id) {
        Eventos eventos = buscarPorId(id);

        if (!eventos.isAtivo()) {
            throw new RuntimeException(", evento informado já esta desativado!");

        } else {
            repository.desativar(id);
        }
    }

    public void ativar(Long id) {
        Eventos eventos = buscarPorId(id);

        if (eventos.isAtivo()) {
            throw new RuntimeException(", evento informado já esta ativado!");
        } else {
            repository.ativar(id);
        }
    }
}
