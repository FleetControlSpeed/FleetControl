package br.com.fleetcontrol.fleetcontrol.dto;
import lombok.Getter;
import lombok.Setter;

public class EmpresasDTO {
    @Getter @Setter
    private String nome;

    @Getter @Setter
    private String CEP;

    @Getter @Setter
    private String endereco;

    public EmpresasDTO(String nome, String CEP, String endereco) {
        this.nome = nome;
        this.CEP = CEP;
        this.endereco = endereco;
    }

    public EmpresasDTO() {

    }
}
