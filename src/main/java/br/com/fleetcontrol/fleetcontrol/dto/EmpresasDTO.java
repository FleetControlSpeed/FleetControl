package br.com.fleetcontrol.fleetcontrol.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class EmpresasDTO {
    @Getter @Setter
    private String nome;

    @Getter @Setter
    private String CEP;

    @Getter @Setter
    private String endereco;

}
