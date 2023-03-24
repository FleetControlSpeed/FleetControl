package br.com.fleetcontrol.fleetcontrol.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "tb_Modelo", schema = "public")
public class Modelo extends AbstractEntity{
    @Getter
    @Setter
    @Column(name = "nome",nullable = false)
    private String nome;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private Marca marca;


}
