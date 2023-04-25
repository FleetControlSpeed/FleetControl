package br.com.fleetcontrol.fleetcontrol.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Table(name = "tb_Usuario", schema = "public")
public class Usuario extends AbstractEntity{
    @Getter
    @Setter
    @NotNull
    @NotEmpty
    @Column(name = "email",nullable = false,unique = true)
    private String email;
    @Getter
    @Setter
    @NotNull
    @NotEmpty
    @Column(name = "usuario",nullable = false,unique = true)
    private String usuario;
    @Getter
    @Setter
    @NotNull
    @NotEmpty
    @Column(name = "senha",nullable = false)
    private String senha;

    @Getter
    @Setter
    @NotNull
    @NotEmpty
    @Enumerated(EnumType.STRING)
    @Column(name = "cargo",nullable = false)
    private Cargo cargo;

    @Getter
    @Setter
    @NotNull
    @NotEmpty
    @Size(min = 3,max = 50)
    @Column(name = "primeiro_Nome",nullable = false,length = 50)
    private String primeiroNome;
    @Getter
    @Setter
    @NotNull
    @NotEmpty
    @Size(min = 3,max = 50)
    @Column(name = "Sobrenome",nullable = false,length = 50)
    private String sobrenome;
    @Getter
    @Setter
    @NotNull
    @NotEmpty
    @Size(min = 15,max = 15)
    @Column(name = "cpf",nullable = false,unique = true,length = 15)
    private String cpf;
    @Getter
    @Setter
    @NotNull
    @NotEmpty
    @Size(min = 9,max = 30)
    @Column(name = "telefone",nullable = false,length = 30)
    private String telefone;
    @Getter
    @Setter
    @NotNull
    @NotEmpty
    @Column(name = "data_Nascimento",nullable = false)
    private String dataNascimento;
    @Getter
    @Setter
    @NotNull
    @NotEmpty
    @Column(name = "endereco",nullable = false)
    private String endereco;
    @Getter
    @Setter
    @NotNull
    @NotEmpty
    @ManyToOne
    @JoinColumn(name = "eventos_id",nullable = false)
    private Eventos eventos;
}
