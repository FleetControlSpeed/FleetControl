package br.com.fleetcontrol.fleetcontrol.entity;

import br.com.fleetcontrol.fleetcontrol.entity.enums.Cargo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.br.CPF;

/*
    @Author: Cristovão Martins
    Date: 06/05/2023
 */
@NoArgsConstructor
@Audited
@Entity
@AuditTable(value = "tb_usuario_audit",schema = "audit")
@Table(name = "tb_usuario", schema = "public")
public class Usuario extends AbstractEntity {
    @Getter
    @Setter
    @NotNull(message = "Email do Usuario não pode ser nulo!")
    @Column(name = "email",nullable = false,unique = true)
    private String email;
    @Getter
    @Setter
    @NotNull(message = "Usuario não pode ser nulo!")
    @NotBlank
    @Column(name = "usuario",nullable = false,unique = true)
    private String usuario;
    @Getter
    @Setter
    @NotNull(message = "Senha não pode ser nula!")
    @Column(name = "senha",nullable = false)
    private String senha;
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private Cargo cargo;
    @Getter
    @Setter
    @NotBlank
    @NotNull(message = "Primeiro nome não pode ser nulo nem em branco!")
    @Size(min = 5, max = 50)
    @Column(name = "primeiroNome",nullable = false,length = 50)
    private String primeiroNome;
    @Getter
    @Setter
    @NotNull(message = "Sobrenome não pode ser nulo nem em branco!")
    @NotBlank
    @Size(min = 2, max = 50)
    @Column(name = "Sobrenome",nullable = false,length = 50)
    private String sobrenome;
    @Getter
    @Setter
    @NotNull
    @Size(min = 5, max = 15)
    @CPF(message = "CPF invalido!")
    @Column(name = "cpf",nullable = false,unique = true,length = 15)
    private String cpf;

    @Getter
    @Setter
    @NotNull(message = "Telefone não pode ser nulo!")
    @Size(min = 5, max = 30)
    @Column(name = "telefone",nullable = false,length = 30)
    private String telefone;

    @Getter
    @Setter
    @NotNull(message = "Data de nascimento não pode ser nula nem em branco!")
    @Column(name = "DataNascimento",nullable = false)
    private String dataNascimento;

    @Getter
    @Setter
    @NotNull(message = "Endereco não pode ser nulo nem em branco!")
    @Column(name = "endereco",nullable = false)
    private String endereco;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "eventos_id")
    private Eventos eventos;

}
