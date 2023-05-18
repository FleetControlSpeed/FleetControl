package br.com.fleetcontrol.fleetcontrol.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "tb_veiculo", schema = "public")
public class Veiculo extends AbstractEntity{

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "modelo")
    private Modelo modelo;
    @Getter
    @Setter
    @NotNull(message = "Campo precisa ser preenchido")
    @Size(min=7, max=8, message = "Preencha o campo com no minimo 7 caracteres e maximo 8")
    @Column(name = "placa",nullable = false,unique = true)
    private String placa;
    @Getter
    @Setter
    @Column(name = "ano",nullable = false)
    private int ano;
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "cor", nullable = false)
    private Cor cor;
    @Getter
    @Setter
    @Column(name = "km",nullable = false)
    private Long km;
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private Tipo tipo;
}