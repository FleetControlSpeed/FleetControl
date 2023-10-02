package br.com.fleetcontrol.fleetcontrol.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import br.com.fleetcontrol.fleetcontrol.entity.enums.Marca;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class ModeloDTO {
    @Getter @Setter
    private String nome;
    @Getter @Setter
    private Marca marca;
}
