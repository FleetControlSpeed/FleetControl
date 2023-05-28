package br.com.fleetcontrol.fleetcontrol.entity;


import br.com.fleetcontrol.fleetcontrol.validation.constraints.CEP;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
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
    @NotNull(message = "Nome é um campo obrigatorio!")
    @NotBlank(message = "Nome nulo ou invalido!")
    @Size(min = 5, max = 100, message = "Nome deve conter de 5 a 100 caracteres!")
    @Column(name = "nome")
    private String nome;

    @Getter @Setter
    @NotNull(message = "CEP é um campo obrigatorio!")
    @CEP(message = "CEP nulo ou invalido!d")
    @Size(min = 5, max = 15)
    @Column(name = "CEP", unique = true)
    private String CEP;

    @Getter @Setter
    @NotNull(message = "Endereço é um campo obrigatorio!")
    @NotBlank(message = "Endereço nulo ou invalido!")
    @Size(min = 5, max = 100)
    @Column(name = "endereco",unique = true)
    private String endereco;


}
