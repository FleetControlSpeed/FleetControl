package br.com.fleetcontrol.fleetcontrol.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jdk.jfr.Event;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tb_tipos_de_eventos")

public class TipoDeEventos extends AbstractEntity{
    @Getter
    @Setter
    private String nome;

    @Getter
    @OneToMany(mappedBy = "tipoDeEventos")
    private List<Eventos> eventos;
}
