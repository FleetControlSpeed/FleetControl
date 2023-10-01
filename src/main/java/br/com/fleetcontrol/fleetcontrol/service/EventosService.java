package br.com.fleetcontrol.fleetcontrol.service;

import br.com.fleetcontrol.fleetcontrol.dto.EventoConverter;
import br.com.fleetcontrol.fleetcontrol.dto.EventoDTO;
import br.com.fleetcontrol.fleetcontrol.entity.Empresas;
import br.com.fleetcontrol.fleetcontrol.entity.Eventos;
import br.com.fleetcontrol.fleetcontrol.repository.EventosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EventosService {



    @Autowired
    private EventosRepository repository;

    public Eventos buscarPorId(Long id) {

        if (id == 0) {
            throw new RuntimeException("Por favor, informe um valor válido!");
        } else if (repository.findById(id).isEmpty()) {
            throw new RuntimeException("Não foi possível localizar o evento informado!");
        } else {
            return repository.findById(id).orElse(null);
        }
    }

    public List<Eventos> listar() {
        if (repository.findAll().isEmpty()) {
            throw new RuntimeException("Não foi possível localizar nenhum evento cadastrado!");
        } else {
            return repository.findAll();
        }
    }

    public List<Eventos> listarPorAtivo() {
        if (repository.buscarPorAtivo().isEmpty()) {
            throw new RuntimeException("Não foi possível localizar nenhum evento ativo cadastrado!");
        } else {
            return repository.buscarPorAtivo();
        }
    }

    @Transactional
    public Eventos salvar(Eventos eventos) {

        return repository.save(eventos);
    }
    @Transactional(rollbackFor = Exception.class)
    public void cadastrar(final EventoDTO eventoDTO) {
        Eventos evento = EventoConverter.toEntity(eventoDTO);
        repository.save(evento);
    }


    @Transactional
    public void editar(Long id, Eventos eventosNovo) {
        final Eventos eventosBanco = this.buscarPorId(id);

        if (eventosBanco == null || !eventosBanco.getId().equals(eventosNovo.getId())) {
            throw new RuntimeException("Não foi possível identificar o evento informado!");
        } else {
            salvar(eventosNovo);
        }
    }

    @Transactional
    public void desativar(Long id) {
        Eventos eventos = buscarPorId(id);

        if (!eventos.isAtivo()) {
            throw new RuntimeException("Evento informado já está desativado!");
        } else {
            repository.desativar(id);
        }
    }

    @Transactional
    public void ativar(Long id) {
        Eventos eventos = buscarPorId(id);

        if (eventos.isAtivo()) {
            throw new RuntimeException("Evento informado já está ativado!");
        } else {
            repository.ativar(id);
        }
    }


}
