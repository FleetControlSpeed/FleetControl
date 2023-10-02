package br.com.fleetcontrol.fleetcontrol.dto;

import br.com.fleetcontrol.fleetcontrol.entity.enums.Cargo;
import lombok.Getter;
import lombok.Setter;

public class UsuarioDTO {
    @Getter @Setter
    private String email;
    @Getter @Setter
    private String usuario;
    @Getter @Setter
    private String senha;
    @Getter @Setter
    private Cargo cargo;
    @Getter @Setter
    private String primeiroNome;
    @Getter @Setter
    private String sobrenome;
    @Getter @Setter
    private String cpf;
    @Getter @Setter
    private String telefone;
    @Getter @Setter
    private String dataNascimento;
    @Getter @Setter
    private String endereco;

    public UsuarioDTO(String email, String usuario, String senha, Cargo cargo, String primeiroNome, String sobrenome, String cpf, String telefone, String dataNascimento, String endereco) {
        this.email = email;
        this.usuario = usuario;
        this.senha = senha;
        this.cargo = cargo;
        this.primeiroNome = primeiroNome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
    }
    public UsuarioDTO() {
    }
}
