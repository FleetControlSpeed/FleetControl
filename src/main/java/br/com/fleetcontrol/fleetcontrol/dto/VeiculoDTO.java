package br.com.fleetcontrol.fleetcontrol.dto;

import br.com.fleetcontrol.fleetcontrol.entity.Modelo;
import br.com.fleetcontrol.fleetcontrol.entity.enums.Cor;
import br.com.fleetcontrol.fleetcontrol.entity.enums.Tipo;
import lombok.Getter;
import lombok.Setter;

public class VeiculoDTO {
    @Getter @Setter
    private Modelo modelo;
    @Getter @Setter
    private String placa;
    @Getter @Setter
    private int ano;
    @Getter @Setter
    private Cor cor;
    @Getter @Setter
    private Long km;
    @Getter @Setter
    private Tipo tipo;
    public VeiculoDTO(Modelo modelo, String placa, int ano, Cor cor, Long km, Tipo tipo) {
        this.modelo = modelo;
        this.placa = placa;
        this.ano = ano;
        this.cor = cor;
        this.km = km;
        this.tipo = tipo;}
    public VeiculoDTO() {
    }
}
