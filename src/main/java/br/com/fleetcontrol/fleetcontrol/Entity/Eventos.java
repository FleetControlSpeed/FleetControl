package br.com.fleetcontrol.fleetcontrol.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Entity
@NoArgsConstructor
@Table(name = "tb_Evento", schema = "public")
public class Eventos extends AbstractEntity{
    @Getter
    @Setter
    @ManyToOne
    @NotNull
    @NotEmpty
    @JoinColumn(name = "usuario_id",nullable = false)
    private Usuario usuario;
    @Getter
    @Setter
    @NotNull
    @NotEmpty
    @Column(name = "data_evento",nullable = false)
    private LocalDateTime dataEvento;
    @Getter
    @Setter
    @NotNull
    @NotEmpty
    @Column(name = "local_partida",nullable = false)
    private String localPartida;
    @Getter
    @Setter
    @NotNull
    @NotEmpty
    @Column(name = "local_destino",nullable = false)
    private String localDestino;
    @Getter
    @Setter
    @NotNull
    @NotEmpty
    @Column(name = "observacao")
    private String observacao;

    @Getter
    @Setter
    @NotNull
    @NotEmpty
    @Column(name = "retorno")
    private String retorno;
    @Getter
    @Setter
    @NotNull
    @NotEmpty
    @ManyToOne
    @JoinColumn(name = "veiculo_id",nullable = false)
    private Veiculo veiculo;

}
