package br.com.fleetcontrol.fleetcontrol.service;

import br.com.fleetcontrol.fleetcontrol.entity.Eventos;
import br.com.fleetcontrol.fleetcontrol.repository.EventosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
    @Author: Pedro Henrique Vieira
    Date: 07/05/2023
    Last att: 27/05/2023
 */

@Service
public class EventosService {

    @Autowired
    private EventosRepository repository;

    public Eventos buscarPorId(Long id) {

        if (id == 0) {
            throw new RuntimeException(", por favor, informe um valor valido!");

        } else if (repository.findById(id).isEmpty()) {
            throw new RuntimeException(", não foi possivel localizar o evento informado!");

        } else {
            return repository.findById(id).orElse(null);
        }
    }

    public List<Eventos> listar() {
        if (repository.findAll().isEmpty()) {
            throw new RuntimeException(", não foi possivel localizar nenhum evento cadastrado!");

        } else {
            return repository.findAll();
        }
    }

    public List<Eventos> listarPorAtivo() {
        if (repository.buscarPorAtivo().isEmpty()) {
            throw new RuntimeException(", não foi possivel localizar nenhum evento ativo cadastrado!");

        } else {
            return repository.buscarPorAtivo();
        }
    }

    @Transactional
    public Eventos salvar(Eventos eventos) {
            return this.repository.save(eventos);
    }

    @Transactional
    public void editar(Long id, Eventos eventosNovo){
        final Eventos eventosBanco = this.buscarPorId(id);

        if(eventosBanco == null || !eventosBanco.getId().equals(eventosNovo.getId())){
            throw new RuntimeException(", não foi possivel identificar o evento informado!");

        } else {
            salvar(eventosNovo);
        }
    }

    @Transactional
    public void desativar(Long id) {
        Eventos eventos = buscarPorId(id);

        if (!eventos.isAtivo()) {
            throw new RuntimeException(", evento informado já esta desativado!");

        } else {
            repository.desativar(id);
        }
    }

    @Transactional
    public void ativar(Long id) {
        Eventos eventos = buscarPorId(id);

        if (eventos.isAtivo()) {
            throw new RuntimeException(", evento informado já esta ativado!");
        } else {
            repository.ativar(id);
        }
    }
}
