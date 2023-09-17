package br.com.fleetcontrol.fleetcontrol.dto;

import br.com.fleetcontrol.fleetcontrol.entity.enums.Marca;
import lombok.Getter;
import lombok.Setter;

public class ModeloDTO {
    @Getter @Setter
    private String nome;
    @Getter @Setter
    private Marca marca;

    public ModeloDTO(String nome, Marca marca) {
        this.nome = nome;
        this.marca = marca;
    }
    public ModeloDTO() {
    }
}
