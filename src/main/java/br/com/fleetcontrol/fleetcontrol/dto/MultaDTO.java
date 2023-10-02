package br.com.fleetcontrol.fleetcontrol.dto;

import br.com.fleetcontrol.fleetcontrol.entity.Usuario;
import br.com.fleetcontrol.fleetcontrol.entity.enums.Marca;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public class MultaDTO {
    @Getter @Setter
    private Double valor;
    @Getter @Setter
    private String tipoMulta;

    @Getter @Setter
    private LocalDate dataMulta;

    @Getter @Setter
    private Usuario usuario;


    public MultaDTO(Double valor, String tipoMulta, LocalDate dataMulta, Usuario usuario) {
        this.valor = valor;
        this.tipoMulta = tipoMulta;
        this.dataMulta = dataMulta;
        this.usuario = usuario;
    }

    public MultaDTO() {
    }
}
