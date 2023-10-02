package br.com.fleetcontrol.fleetcontrol.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import jakarta.persistence.*;
import java.time.LocalDate;
@NoArgsConstructor
@Entity
@Audited
@AuditTable(value = "tb_multa_audit",schema = "audit")
@Table(name = "tb_multa", schema = "public")
public class Multa extends AbstractEntity{

    @Getter
    @Setter
    @Column(name = "valor",nullable = false)
    private Double valor;

    @Getter
    @Setter
    @Column(name = "tipo_multa",nullable = false)
    private String tipoMulta;

    @Getter
    @Setter
    @Column(name = "dataMulta",nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")

    private LocalDate dataMulta;


    @ManyToOne
    @Getter
    @Setter
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
