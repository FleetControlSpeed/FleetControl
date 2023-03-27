package br.com.fleetcontrol.fleetcontrol.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Entity
@Table(name = "tb_eventos", schema = "public")
public class Eventos extends AbstractEntity{
    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "tipo_eventos_id",nullable = false)
    private TipoDeEventos tipoDeEventos;
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "usuario_id",nullable = false)
    private Usuario usuario;
    @Getter
    @Setter
    @Column(name = "data_evento",nullable = false)
    private LocalDateTime dataEvento;
    @Getter
    @Setter
    @Column(name = "LocalPartida",nullable = false)
    private String localPartida;
    @Getter
    @Setter
    @Column(name = "LocalDestino",nullable = false)
    private String localDestino;
    @Getter
    @Setter
    @Column(name = "observacao")
    private String observacao;
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "Veiculo_id",nullable = false)
    private Veiculo veiculo;
    @Getter
    @Setter
    @Column(name = "Retorno")
    private String retorno;
}
