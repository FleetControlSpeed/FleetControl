package br.com.fleetcontrol.fleetcontrol.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
public class Eventos extends AbstractEntity {

    @Getter @Setter
    @NotNull(message = "Usuario é um campo obrigatorio!")
    @ManyToOne
    @JoinColumn(name = "usuario_id",nullable = false)
    private Usuario usuario;

    @Getter @Setter
    @NotNull(message = "Veiculo é um campo obrigatorio!")
    @ManyToOne
    @JoinColumn(name = "veiculo_id",nullable = false)
    private Veiculo veiculo;

    @Getter @Setter
    @NotNull(message = "Data do evento é um campo obrigatorio!")
    @NotBlank(message = "Data do evento nula ou invalida!")
    @Column(name = "data_evento",nullable = false)
    private LocalDateTime dataEvento;

    @ManyToOne
    @Getter @Setter
    @NotNull(message = "Local de partida é um campo obrigatorio!")
    @NotBlank(message = "Local de partida nulo ou invalido!")
    @JoinColumn(name = "empresa_partida_id",nullable = false)
    private Empresas localPartida;

    @ManyToOne
    @Getter @Setter
    @NotNull(message = "Local de destino é um campo obrigatorio!")
    @NotBlank(message = "Local de destino nulo ou invalido!")
    @JoinColumn(name = "empresa_destino_id",nullable = false)
    private Empresas localDestino;

    @Getter @Setter
    @Column(name = "observacao")
    private String observacao;

    @Getter @Setter
    @Column(name = "retorno")
    private LocalDateTime retorno;
}
