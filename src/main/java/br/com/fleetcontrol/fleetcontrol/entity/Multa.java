package br.com.fleetcontrol.fleetcontrol.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@NoArgsConstructor
@Entity
@Audited
@AuditTable(value = "tb_multa_audit",schema = "audit")
@Table(name = "tb_multa", schema = "public")
public class Multa extends AbstractEntity{
    @Getter
    @Setter
    @Column(name = "nome",nullable = false)
    private String nome;
    @Getter
    @Setter
    @Column(name = "valor",nullable = false)
    private Double valor;

    @Getter
    @Setter
    @Column(name = "tipo_multa",nullable = false)
    private String tipoMulta;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Usuario usuario;
}
