package br.com.fleetcontrol.fleetcontrol.entity;

import br.com.fleetcontrol.fleetcontrol.entity.enums.Cargo;
import br.com.fleetcontrol.fleetcontrol.validation.constraints.CEP;
import br.com.fleetcontrol.fleetcontrol.validation.constraints.CPF;
import br.com.fleetcontrol.fleetcontrol.validation.constraints.Telefone;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import java.util.ArrayList;
import java.util.List;
/*
    @Author: Cristovão Martins
    Date: 06/05/2023
 */
@NoArgsConstructor
@Entity
@Audited
@AuditTable(value = "tb_usuario_audit",schema = "audit")
@Table(name = "tb_usuario", schema = "public")
public class Usuario extends AbstractEntity {
    @Getter @Setter
    @NotBlank(message = "Email é um campo obrigatorio!")
    @Email(message = "Email nulo ou invalido!")
    @Column(name = "email",nullable = false,unique = true)
    private String email;

    @Getter @Setter
    @NotEmpty(message = "Usuario não pode ter campos em brancos ou espaços")
    @Pattern(regexp = "^[a-zA-Z0-9-/-]{2,50}$",message = "Usuario não pode caracteres especiais! alem de / e ou -")
    @NotNull(message = "Usuario é um campo obrigatorio!")
    @Column(name = "usuario",nullable = false,unique = true)
    private String usuario;

    @Getter @Setter
    @NotNull(message = "Senha é um campo obrigatorio!")
    @NotBlank(message = "Senha nula ou invalida!")
    @Column(name = "senha",nullable = false)
    private String senha;

    @Getter @Setter
    @NotNull(message = "Cargo é um campo obrigatorio!")
    @Enumerated(EnumType.STRING)
    private Cargo cargo;

    @Getter @Setter
    @NotBlank(message = "Primeiro nome é um campo obrigatorio!")
    @Size(min = 2, max = 50, message = "Primeiro nome deve conter de 2 a 50 caracteres!")
    @Column(name = "primeiroNome",nullable = false,length = 50)
    private String primeiroNome;

    @Getter @Setter
    @NotBlank(message = "Sobrenome é um campo obrigatorio!")
    @Size(min = 2, max = 50, message = "Sobrenome deve conter de 2 a 50 caracteres!")
    @Column(name = "Sobrenome",nullable = false,length = 50)
    private String sobrenome;

    @Getter @Setter
    @NotBlank(message = "CPF é um campo obrigatorio!")
    @CPF(message = "CPF nulo ou invalido!")
    @Column(name = "cpf",nullable = false,unique = true,length = 15)
    private String cpf;

    @Getter @Setter
    @NotBlank(message = "Telefone não pode ser nulo!")
    @Telefone(message = "Telefone informado nulo ou invalido!")
    @Size(min = 5, max = 12, message = "Telefone deve possuir de 5 a 12 caracteres!")
    @Column(name = "telefone",nullable = false,length = 30)
    private String telefone;

    @Getter @Setter
    @NotNull(message = "Data de nascimento é um campo obrigatorio!")
    @NotBlank(message = "Data de nascimento nula ou invalida!")
    @Column(name = "DataNascimento",nullable = false)
    private String dataNascimento;

    @Getter @Setter
    @NotNull(message = "Endereço é um campo obrigatorio!")
    @NotBlank(message = "Endereco nulo ou invalido!")
    @Column(name = "endereco",nullable = false)
    private String endereco;

    @OneToMany(mappedBy = "usuario")
    private List<Multa> listaMultas = new ArrayList<>();

}