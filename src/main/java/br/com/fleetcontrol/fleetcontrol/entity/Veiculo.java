package br.com.fleetcontrol.fleetcontrol.entity;

import br.com.fleetcontrol.fleetcontrol.entity.enums.Cor;
import br.com.fleetcontrol.fleetcontrol.entity.enums.Tipo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
    @NotBlank(message = "Placa é um campo obrigatorio!")
    @Column(name = "placa",nullable = false,unique = true)
    private String placa;
    @Getter @Setter
    @NotNull(message = "Ano é um campo obrigatorio!")
    @Column(name = "ano",nullable = false)
    private int ano;
    @Getter @Setter
    @NotNull(message = "Cor é um campo obrigatorio!")
    @Enumerated(EnumType.STRING)
    private Cor cor;
    @Getter @Setter
    @NotNull(message = "Km é um campo obrigatorio!")
    @Column(name = "km",nullable = false)
    private Long km;
    @Getter @Setter
    @NotNull(message = "Tipo é um campo obrigatorio!")
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo",nullable = false)
    private Tipo tipo;
}