package br.com.fleetcontrol.fleetcontrol.Entity;

import br.com.fleetcontrol.fleetcontrol.Entity.Enums.Cargo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

/*

    @Author: Cristovão Martins
    Date: 06/05/2023

 */
@NoArgsConstructor
@Audited
@Entity
@AuditTable(value = "marcas_audit",schema = "audit")
@Table(name = "tb_usuario", schema = "public")
public class Usuario extends AbstractEntity{
    @Getter
    @Setter
    @NotNull
    @Column(name = "email",nullable = false,unique = true)
    private String email;
    @Getter
    @Setter
    @NotNull
    @Column(name = "usuario",nullable = false,unique = true)
    private String usuario;
    @Getter
    @Setter
    @NotNull
    @Column(name = "senha",nullable = false)
    private String senha;
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private Cargo cargo;
    @Getter
    @Setter
    @NotNull
    @Size(min = 5, max = 50)
    @Column(name = "primeiroNome",nullable = false,length = 50)
    private String primeiroNome;
    @Getter
    @Setter
    @NotNull
    @Size(min = 2, max = 50)
    @Column(name = "Sobrenome",nullable = false,length = 50)
    private String sobrenome;
    @Getter
    @Setter
    @NotNull
    @Size(min = 5, max = 15)
    @Column(name = "cpf",nullable = false,unique = true,length = 15)
    private String cpf;

    @Getter
    @Setter
    @NotNull
    @Size(min = 5, max = 30)
    @Column(name = "telefone",nullable = false,length = 30)
    private String telefone;

    @Getter
    @Setter
    @NotNull
    @Column(name = "DataNascimento",nullable = false)
    private String dataNascimento;

    @Getter
    @Setter
    @NotNull
    @Column(name = "endereco",nullable = false)
    private String endereco;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "eventos_id")
    private Eventos eventos;

}
