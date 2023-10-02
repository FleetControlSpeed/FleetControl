package br.com.fleetcontrol.fleetcontrol.entity;

import br.com.fleetcontrol.fleetcontrol.entity.enums.Marca;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "tb_Modelo", schema = "public")
@Audited
@AuditTable(value = "tb_modelo_audit",schema = "audit")
@NoArgsConstructor
public class Modelo extends AbstractEntity {
    @Getter @Setter
    @NotNull(message = "Nome é um campo obrigatorio!")
    @NotBlank(message = "Nome nulo ou invalido!")
    @Column(name = "nome",nullable = false)
    private String nome;

    @Getter @Setter
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Marca é um campo obrigatorio!")
    private Marca marca;

    public Modelo(String nome, Marca marca) {
        //Somente para os testes
    }

}