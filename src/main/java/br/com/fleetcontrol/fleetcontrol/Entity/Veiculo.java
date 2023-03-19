package br.com.fleetcontrol.fleetcontrol.Entity;

import lombok.Getter;
import lombok.Setter;

public class Veiculo extends AbstractEntity{
    @Getter
    @Setter
    private Modelo modelo;
    @Getter
    @Setter
    private String placa;
    @Getter
    @Setter
    private int ano;
    @Getter
    @Setter
    private Cor cor;
    @Getter
    @Setter
    private Long km;
    @Getter
    @Setter
    private Tipo tipo;
}
