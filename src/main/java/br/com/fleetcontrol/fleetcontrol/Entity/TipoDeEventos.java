package br.com.fleetcontrol.fleetcontrol.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;

@Entity
@Table(name = "tb_tipo_eventos", schema = "public")
public class TipoDeEventos extends AbstractEntity{
    @Getter
    @Setter
    @Column(name = "nome",nullable = false)
    private String nome;
}
