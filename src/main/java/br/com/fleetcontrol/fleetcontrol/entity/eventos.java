package br.com.fleetcontrol.fleetcontrol.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import java.time.LocalDateTime;

/*
    @Author: Pedro Henrique Vieira
    Date: 07/05/2023
 */

@Entity
@Table(name = "tb_evento", schema = "public")
@Audited
@AuditTable(value = "tb_evento_audit", schema = "audit")
public class eventos extends abstractEntity {

    @Getter @Setter
    @NotNull(message = ", usuario é um campo obrigatorio!")
    @ManyToOne
    @JoinColumn(name = "usuario_id",nullable = false)
    private Usuario usuario;

    @Getter @Setter
    @NotBlank(message = ", data do evento é um campo obrigatorio!")
    @Column(name = "data_evento",nullable = false)
    private LocalDateTime dataEvento;

    @Getter @Setter
    @NotNull(message = ", local de partida é um campo obrigatorio!")
    @NotBlank(message = ", local de partida nulo ou invalido!")
    @Column(name = "local_partida",nullable = false)
    private String localPartida;

    @Getter @Setter
    @NotNull(message = ", local de destino é um campo obrigatorio!")
    @NotBlank(message = ", local de destino nulo ou invalido!")
    @Column(name = "local_destino",nullable = false)
    private String localDestino;

    @Getter @Setter
    @Column(name = "observacao")
    private String observacao;

    @Getter @Setter
    @NotNull(message = ", veiculo é um campo obrigatorio!")
    @ManyToOne
    @JoinColumn(name = "veiculo_id",nullable = false)
    private Veiculo veiculo;

    @Getter @Setter
    @Column(name = "retorno")
    private String retorno;
}
