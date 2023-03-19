package br.com.fleetcontrol.fleetcontrol.Entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class Eventos extends AbstractEntity{
    @Getter
    @Setter
    private TipoDeEventos tipoDeEventos;
    @Getter
    @Setter
    private Usuario usuario;
    @Getter
    @Setter
    private LocalDateTime dataEvento;
    @Getter
    @Setter
    private String localPartida;
    @Getter
    @Setter
    private String localDestino;
    @Getter
    @Setter
    private String observacao;
    @Getter
    @Setter
    private Veiculo veiculo;
    @Getter
    @Setter
    private String retorno;
}
