package br.com.fleetcontrol.fleetcontrol.dto;

import br.com.fleetcontrol.fleetcontrol.entity.Empresas;
import br.com.fleetcontrol.fleetcontrol.entity.Usuario;
import br.com.fleetcontrol.fleetcontrol.entity.Veiculo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
public class EventoDTO {


    @Getter @Setter
    private Usuario usuario;
    @Getter @Setter
    private Veiculo veiculo;
    @Getter @Setter
    private LocalDateTime dataEvento;
    @Getter @Setter
    private Empresas localPartida;
    @Getter @Setter
    private Empresas localDestino;
    @Getter @Setter
    private String observacao;
    @Getter @Setter
    private LocalDateTime retorno;
}
