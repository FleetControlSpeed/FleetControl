package br.com.fleetcontrol.fleetcontrol.entity;

import br.com.fleetcontrol.fleetcontrol.entity.enums.Cor;
import br.com.fleetcontrol.fleetcontrol.entity.enums.Tipo;
import br.com.fleetcontrol.fleetcontrol.validation.constraints.Placa;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

@Entity
@NoArgsConstructor
@Table(name = "tb_veiculo", schema = "public")
@Audited
@AuditTable(value = "tb_veiculo_audit", schema = "audit")
public class Veiculo extends AbstractEntity {
    @Getter @Setter
    @NotNull(message = "Modelo é um campo obrigatorio!")
    @ManyToOne
    @JoinColumn(name = "modelo",nullable = false)
    private Modelo modelo;

    @Getter @Setter
    @NotNull(message = "Placa é um campo obrigatorio!")
    @Column(name = "placa",nullable = false,unique = true)
    private String placa;

    @Getter @Setter
    @NotNull(message = "Ano é um campo obrigatorio!")
    @Min(value = 1950, message = "Ano não pode ser menor que 1950!")
    @Column(name = "ano",nullable = false)
    private int ano;

    @Getter @Setter
    @NotNull(message = "Cor é um campo obrigatorio!")
    @Enumerated(EnumType.STRING)
    private Cor cor;

    @Getter @Setter
    @NotNull(message = "Km é um campo obrigatorio!")
    @Min(value = 0, message = "Km não pode ser menor que 0!")
    @Column(name = "km",nullable = false)
    private Long km;

    @Getter @Setter
    @NotNull(message = "Tipo é um campo obrigatorio!")
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo",nullable = false)
    private Tipo tipo;
}