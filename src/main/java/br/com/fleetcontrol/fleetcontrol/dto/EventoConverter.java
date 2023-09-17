package br.com.fleetcontrol.fleetcontrol.dto;

import br.com.fleetcontrol.fleetcontrol.entity.Eventos;

import java.util.List;
import java.util.stream.Collectors;

public class EventoConverter {

    public static EventoDTO toDTO(Eventos evento) {
        EventoDTO eventoDTO = new EventoDTO();
        eventoDTO.setUsuario(evento.getUsuario());
        eventoDTO.setVeiculo(evento.getVeiculo());
        eventoDTO.setDataEvento(evento.getDataEvento());
        eventoDTO.setLocalPartida(evento.getLocalPartida());
        eventoDTO.setLocalDestino(evento.getLocalDestino());
        eventoDTO.setObservacao(evento.getObservacao());
        eventoDTO.setRetorno(evento.getRetorno());
        return eventoDTO;
    }

    public static Eventos toEntity(EventoDTO eventoDTO) {
        Eventos evento = new Eventos();
        evento.setUsuario(eventoDTO.getUsuario());
        evento.setVeiculo(eventoDTO.getVeiculo());
        evento.setDataEvento(eventoDTO.getDataEvento());
        evento.setLocalPartida(eventoDTO.getLocalPartida());
        evento.setLocalDestino(eventoDTO.getLocalDestino());
        evento.setObservacao(eventoDTO.getObservacao());
        evento.setRetorno(eventoDTO.getRetorno());
        return evento;
    }

    public static List<EventoDTO> toDTOList(List<Eventos> eventos) {
        return eventos.stream()
                .map(EventoConverter::toDTO)
                .collect(Collectors.toList());
    }
}
