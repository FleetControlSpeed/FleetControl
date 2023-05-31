package br.com.fleetcontrol.fleetcontrol.entity;

import jakarta.persistence.*;
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

    @ManyToOne
    @Getter @Setter
    @NotNull(message = "Local de partida é um campo obrigatorio!")
    @JoinColumn(name = "empresa_partida_id",nullable = false)
    private Empresas localPartida;

    @ManyToOne
    @Getter @Setter
    @NotNull(message = "Local de destino é um campo obrigatorio!")
    @JoinColumn(name = "empresa_destino_id",nullable = false)
    private Empresas localDestino;

    @Getter @Setter
    @Column(name  = "observacao")
    private String observacao;

    @Getter @Setter
    @Column(name = "km_final")
    private Long kmFinal;

    @Getter @Setter
    @Column(name = "km_total")
    private Long kmTotal;

    @Getter @Setter
    @Column(name = "data_inicio",nullable = false)
    private LocalDateTime dataInicio;

    @Getter @Setter
    @Column(name = "data_retorno")
    private LocalDateTime dataRetorno;

    @Getter @Setter
    @Column(name = "periodo_evento")
    private Long periodo;

    @PrePersist
    public void prePersist(){
        this.setObservacao("Sem observações!");
        this.setDataInicio(LocalDateTime.now());
        this.getVeiculo().setAtivo(false);
    }

    @Override
    public String toString() {
        return  ("---------------------------------------------" + "\n") +
                ("-                FleetControl               -" + "\n") +
                ("---------------------------------------------" + "\n") +
                ("Saida: " + this.getDataInicio().getDayOfMonth() + "/" + this.getDataInicio().getMonthValue() + "/" + this.getDataInicio().getYear() + " | " + this.getDataInicio().getHour() + ":" + this.getDataInicio().getMinute() + ":" + this.getDataInicio().getSecond()  + "\n") +
                ("Retorno: " + this.getDataRetorno().getDayOfMonth() + "/" + this.getDataRetorno().getMonthValue() + "/" + this.getDataRetorno().getYear() + " | " + this.getDataRetorno().getHour() + ":" + this.getDataRetorno().getMinute() + ":" + this.getDataRetorno().getSecond()  + "\n") +
                ("Condutor: " + this.getUsuario().getPrimeiroNome() + " " + this.getUsuario().getSobrenome() + "\n") +
                ("Veiculo: " + this.getVeiculo().getModelo().getNome() + " / Placa: " + this.getVeiculo().getPlaca() + "\n") +
                ("---------------------------------------------" + "\n") +
                ("-              DADOS DO EVENTO              -" + "\n") +
                ("---------------------------------------------" + "\n") +
                ("Período do evento: " + (this.getPeriodo()/60/60) + " horas" + "\n") +
                ("Quilometragem inical: " + (this.getKmFinal() - this.getKmTotal()) + "\n") +
                ("Quilometragem final: " + this.getKmFinal() + "\n") +
                ("Distancia percorrida: " + this.getKmTotal() + "\n") +
                ("---------------------------------------------" + "\n") +
                ("-               *OBSERVAÇÕES*               -" + "\n") +
                (""+           this.getObservacao() +         "" + "\n") +
                ("---------------------------------------------" + "\n") +
                ("-                *COMPROVANTE*              -" + "\n") +
                ("-          *PARA FINS DE CONFERENCIA*       -" + "\n") +
                ("---------------------------------------------" + "\n");
    }
}
