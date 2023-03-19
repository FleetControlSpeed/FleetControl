package br.com.fleetcontrol.fleetcontrol.Entity;

import lombok.Getter;
import lombok.Setter;

public class Modelo extends AbstractEntity{
    @Getter
    @Setter
    private String nome;

    @Getter
    @Setter
    private Marca marca;
}
