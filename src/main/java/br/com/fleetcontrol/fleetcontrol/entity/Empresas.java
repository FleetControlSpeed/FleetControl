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
@Audited
@Table(name = "empresas", schema = "public")
@AuditTable(value = "empresas_audit", schema = "audit")
public class Empresas extends abstractEntity {

    @Getter @Setter
    @NotNull
    @Size(min = 5, max = 100)
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Getter @Setter
    @NotNull
    @Size(min = 5, max = 15)
    @Column(name = "CEP",nullable = false,unique = true,length = 10)
    private String CEP;

    @Getter @Setter
    @NotNull
    @Size(min = 5, max = 100)
    @Column(name = "endereco",nullable = false,unique = true,length = 100)
    private String endereco;


}
