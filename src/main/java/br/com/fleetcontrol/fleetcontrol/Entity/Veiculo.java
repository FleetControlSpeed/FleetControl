package br.com.fleetcontrol.fleetcontrol.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@NoArgsConstructor
@Table(name = "tb_veiculo", schema = "public")
public class Veiculo extends AbstractEntity{
    @Getter
    @Setter
    @NotNull
    @NotEmpty
    @ManyToOne
    @JoinColumn(name = "modelo",nullable = false)
    private Modelo modelo;
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
    private Cor cor;
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
    private Tipo tipo;
}