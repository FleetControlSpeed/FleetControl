package br.com.fleetcontrol.fleetcontrol.Entity;

import br.com.fleetcontrol.fleetcontrol.Entity.Enums.Cor;
import br.com.fleetcontrol.fleetcontrol.Entity.Enums.Tipo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "tb_veiculo", schema = "public")
@Audited
public class Veiculo extends AbstractEntity{
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "modelo")
    private Modelo modelo;
    @Getter
    @Setter
    @Column(name = "placa",nullable = false,unique = true)
    private String placa;
    @Getter
    @Setter
    @Column(name = "ano",nullable = false)
    private int ano;
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private Cor cor;
    @Getter
    @Setter
    @Column(name = "km",nullable = false)
    private Long km;
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private Tipo tipo;
}