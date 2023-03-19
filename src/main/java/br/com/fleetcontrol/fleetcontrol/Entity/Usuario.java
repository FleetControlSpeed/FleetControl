package br.com.fleetcontrol.fleetcontrol.Entity;

import lombok.Getter;
import lombok.Setter;

public class Usuario extends AbstractEntity{
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String usuario;
    @Getter
    @Setter
    private String senha;

    @Getter
    @Setter
    private Cargo cargo;

    @Getter
    @Setter
    private String primeiroNome;
    @Getter
    @Setter
    private String sobrenome;
    @Getter
    @Setter
    private String cpf;
    @Getter
    @Setter
    private String telefone;
    @Getter
    @Setter
    private String dataNascimento;
    @Getter
    @Setter
    private String endereco;
    @Getter
    @Setter
    private Eventos eventos;
}
