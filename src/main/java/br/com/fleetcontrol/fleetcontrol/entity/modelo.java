package br.com.fleetcontrol.fleetcontrol.entity;

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
public class modelo extends abstractEntity {
    @Getter
    @Setter
    @NotNull(message = "insira um nome valido")
    @Column(name = "nome",nullable = false)
    private String nome;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private br.com.fleetcontrol.fleetcontrol.entity.enums.marca marca;


}
