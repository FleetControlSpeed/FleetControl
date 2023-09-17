package br.com.fleetcontrol.fleetcontrol.dto;

import br.com.fleetcontrol.fleetcontrol.entity.Empresas;
import br.com.fleetcontrol.fleetcontrol.entity.Usuario;
import br.com.fleetcontrol.fleetcontrol.entity.Veiculo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class EventoDTO {


    @Getter @Setter
    private Usuario usuario;
    @Getter @Setter
    private Veiculo veiculo;
    @Getter @Setter
    private LocalDateTime dataEvento;
    @Getter @Setter
    private Empresas localPartida;
    @Getter @Setter
    private Empresas localDestino;
    @Getter @Setter
    private String observacao;
    @Getter @Setter
    private LocalDateTime retorno;
    public EventoDTO() {
    }

    public EventoDTO(Usuario usuario, Veiculo veiculo, LocalDateTime dataEvento, Empresas localPartida, Empresas localDestino, String observacao, LocalDateTime retorno) {
        this.usuario = usuario;
        this.veiculo = veiculo;
        this.dataEvento = dataEvento;
        this.localPartida = localPartida;
        this.localDestino = localDestino;
        this.observacao = observacao;
        this.retorno = retorno;
    }
}
