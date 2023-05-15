package br.com.fleetcontrol.fleetcontrol.entity;

import br.com.fleetcontrol.fleetcontrol.entity.enums.Cor;
import br.com.fleetcontrol.fleetcontrol.entity.enums.Tipo;
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
public class Veiculo extends AbstractEntity {
    @Getter
    @Setter
    @NotNull(message = "Modelo não pode ser nulo nem em branco!")
    @NotEmpty
    @ManyToOne
    @JoinColumn(name = "modelo",nullable = false)
    private Modelo modelo;
    @Getter
    @Setter
    @NotNull(message = "Placa não pode ser nula nem em branco!")
    @NotEmpty
    @Column(name = "placa",nullable = false,unique = true)
    private String placa;
    @Getter
    @Setter
    @NotNull
    @NotEmpty(message = "Ano nao pode ser nulo nem em branco!")
    @Column(name = "ano",nullable = false)
    private int ano;
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private Cor cor;
    @Getter
    @Setter
    @NotNull(message = "Km não pode ser nula nem em branca!")
    @NotEmpty
    @Column(name = "km",nullable = false)
    private Long km;
    @Getter
    @Setter
    @NotNull(message = "Tipo não pode ser nula nem em branca!")
    @NotEmpty
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo",nullable = false)
    private Tipo tipo;
}