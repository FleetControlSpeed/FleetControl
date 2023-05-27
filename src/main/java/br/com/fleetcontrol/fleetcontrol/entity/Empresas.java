package br.com.fleetcontrol.fleetcontrol.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "tb_empresas", schema = "public")
@Audited
@AuditTable(value = "tb_empresas_audit", schema = "audit")
public class Empresas extends AbstractEntity {

    @Getter @Setter
    @NotNull(message = "Nome não pode ser nulo!")
    @Size(min = 5, max = 100)
    @Column(name = "nome")
    private String nome;

    @Getter @Setter
    @NotNull(message = "CEP não pode ser nulo!")
    @Size(min = 5, max = 15)
    @Column(name = "CEP", unique = true)
    private String CEP;

    @Getter @Setter
    @NotNull(message = "Endereco não pode ser nulo!")
    @Size(min = 5, max = 100)
    @Column(name = "endereco",unique = true)
    private String endereco;


}
