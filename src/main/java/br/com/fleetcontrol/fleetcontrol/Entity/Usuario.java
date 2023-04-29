package br.com.fleetcontrol.fleetcontrol.Entity;

import br.com.fleetcontrol.fleetcontrol.Entity.Enums.Cargo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "tb_usuario", schema = "public")
public class Usuario extends AbstractEntity{
    @Getter
    @Setter
    @Column(name = "email",nullable = false,unique = true)
    private String email;
    @Getter
    @Setter
    @Column(name = "usuario",nullable = false,unique = true)
    private String usuario;
    @Getter
    @Setter
    @Column(name = "senha",nullable = false)
    private String senha;
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private Cargo cargo;
    @Getter
    @Setter
    @Column(name = "primeiroNome",nullable = false,length = 50)
    private String primeiroNome;
    @Getter
    @Setter
    @Column(name = "Sobrenome",nullable = false,length = 50)
    private String sobrenome;
    @Getter
    @Setter
    @Column(name = "cpf",nullable = false,unique = true,length = 15)
    private String cpf;

    @Getter
    @Setter
    @Column(name = "telefone",nullable = false,length = 30)
    private String telefone;

    @Getter
    @Setter
    @Column(name = "DataNascimento",nullable = false)
    private String dataNascimento;

    @Getter
    @Setter
    @Column(name = "endereco",nullable = false)
    private String endereco;

    @Getter
    @Setter
    @ManyToOne()
    private Eventos eventos;

}
