package br.com.fleetcontrol.fleetcontrol.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/*
    Autor: Pedro Henrique
    data: 24/03/2023
    git: https://github.com/PedroHenri1606

 */

@Entity
@Table(name = "tb_eventos")
public class Eventos extends AbstractEntity{
    @Getter @Setter @NotNull
    @ManyToOne
    @JoinColumn(name = "tipos_eventos_id")
    private TipoDeEventos tipoDeEventos;
    @Getter @Setter @NotNull
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @Getter @Setter @NotNull
    @Column(name = "data_evento")
    private LocalDateTime dataEvento;
    @Getter @Setter @NotNull
    @Column(name = "local_partida")
    private String localPartida;
    @Getter @Setter @NotNull
    @Column(name = "local_destino")
    private String localDestino;
    @Getter @Setter
    @Column(name = "observacao")
    private String observacao;
    @Getter @Setter
    @Column(name = "veiculo")
    private Veiculo veiculo;
    @Getter @Setter
    @Column(name = "retorno")
    private LocalDateTime retorno;
}
