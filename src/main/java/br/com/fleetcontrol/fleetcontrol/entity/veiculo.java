package br.com.fleetcontrol.fleetcontrol.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

@Entity
@NoArgsConstructor
@Table(name = "tb_veiculo", schema = "public")
@Audited
public class veiculo extends abstractEntity {
    @Getter
    @Setter
    @NotNull
    @NotEmpty
    @ManyToOne
    @JoinColumn(name = "modelo",nullable = false)
    private br.com.fleetcontrol.fleetcontrol.entity.modelo modelo;
    @Getter
    @Setter
    @NotNull
    @NotEmpty
    @Column(name = "placa",nullable = false,unique = true)
    private String placa;
    @Getter
    @Setter
    @NotNull
    @NotEmpty
    @Column(name = "ano",nullable = false)
    private int ano;
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private br.com.fleetcontrol.fleetcontrol.entity.enums.cor cor;
    @Getter
    @Setter
    @NotNull
    @NotEmpty
    @Column(name = "km",nullable = false)
    private Long km;
    @Getter
    @Setter
    @NotNull
    @NotEmpty
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo",nullable = false)
    private br.com.fleetcontrol.fleetcontrol.entity.enums.tipo tipo;
}