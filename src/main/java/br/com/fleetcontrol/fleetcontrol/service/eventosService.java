package br.com.fleetcontrol.fleetcontrol.service;

import br.com.fleetcontrol.fleetcontrol.entity.eventos;
import br.com.fleetcontrol.fleetcontrol.repository.eventosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
    @Author: Pedro Henrique Vieira
    Date: 07/05/2023
 */

@Service
public class eventosService {

    @Autowired
    private eventosRepository repository;

    public eventos buscarPorId(Long id) {

        if (id == 0) {
            throw new RuntimeException(", por favor, informe um valor valido!");

        } else if (repository.findById(id).isEmpty()) {
            throw new RuntimeException(", não foi possivel localizar o condutor informado!");

        } else {
            return repository.findById(id).orElse(null);
        }
    }

    public List<eventos> listar() {
        if (repository.findAll().isEmpty()) {
            throw new RuntimeException(", banco de dados não possui eventos cadastrados!");

        } else {
            return repository.findAll();
        }
    }

    public List<eventos> listarPorAtivo() {
        if (repository.buscarPorAtivo().isEmpty()) {
            throw new RuntimeException(", banco de dados não possui eventos ativos!");

        } else {
            return repository.buscarPorAtivo();
        }
    }

    public eventos salvar(eventos eventos) {

        if (eventos.getUsuario() == null) {
            throw new RuntimeException(", usuario é um campo obrigatorio!");

        } else if (eventos.getDataEvento() == null) {
            throw new RuntimeException(", data do evento é um campo obrigatorio!");

        } else if (eventos.getLocalPartida() == null) {
            throw new RuntimeException(", local de partida é um campo obrigatorio!");

        } else if (eventos.getLocalPartida().isBlank()) {
            throw new RuntimeException(", local de partida nulo ou invalido!");

        } else if (eventos.getLocalDestino() == null) {
            throw new RuntimeException(", local de destino é um campo obrigatorio!");

        } else if (eventos.getLocalDestino().isBlank()) {
            throw new RuntimeException(", local de destino nulo ou invalido!");

        } else if (eventos.getVeiculo() == null) {
            throw new RuntimeException(", veiculo é um campo obrigatorio!");

        } else {
            return this.repository.save(eventos);
    }
    }
    public void editar(Long id, eventos eventosNovo){
        final eventos eventosBanco = this.buscarPorId(id);

        if(eventosBanco == null || !eventosBanco.getId().equals(eventosNovo.getId())){
            throw new RuntimeException(", não foi possivel identificar o evento informado!");

        } else {
            this.salvar(eventosNovo);
        }
    }

    public void desativar(Long id) {
        eventos eventos = buscarPorId(id);

        if (!eventos.isAtivo()) {
            throw new RuntimeException(", evento informado já esta desativado!");

        } else {
            repository.desativar(id);
        }
    }

    public void ativar(Long id) {
        eventos eventos = buscarPorId(id);

        if (eventos.isAtivo()) {
            throw new RuntimeException(", evento informado já esta ativado!");
        } else {
            repository.ativar(id);
        }
    }
}
