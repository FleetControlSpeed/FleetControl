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
public class Modelo extends abstractEntity {
    @Getter
    @Setter
    @NotNull(message = "insira um nome valido")
    @Column(name = "nome",nullable = false)
    private String nome;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private Marca marca;


}
