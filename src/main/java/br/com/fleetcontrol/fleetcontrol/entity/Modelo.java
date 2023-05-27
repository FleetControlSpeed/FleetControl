package br.com.fleetcontrol.fleetcontrol.entity;

import br.com.fleetcontrol.fleetcontrol.entity.enums.Marca;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "tb_Modelo", schema = "public")
@Audited
@AuditTable(value = "tb_modelo_audit",schema = "audit")
public class Modelo extends AbstractEntity {
    @Getter
    @Setter
    @NotNull(message = "Nome do modelo não pode ser nulo!")
    @Column(name = "nome",nullable = false)
    private String nome;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private Marca marca;


}