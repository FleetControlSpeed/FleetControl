package br.com.fleetcontrol.fleetcontrol.dto;
import lombok.Getter;
import lombok.Setter;

public class EmpresasDTO {
    @Getter @Setter
    private String nome;

    @Getter @Setter
    private String cep;

    @Getter @Setter
    private String endereco;

    public EmpresasDTO(String nome, String cep, String endereco) {
        this.nome = nome;
        this.cep = cep;
        this.endereco = endereco;
    }

    public EmpresasDTO() {

    }
}
