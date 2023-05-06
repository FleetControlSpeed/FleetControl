package br.com.fleetcontrol.fleetcontrol.Entity;

import br.com.fleetcontrol.fleetcontrol.Entity.Enums.Marca;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "tb_Modelo", schema = "public")
@Audited
public class Modelo extends AbstractEntity{
    @Getter
    @Setter
    @Column(name = "nome",nullable = false)
    private String nome;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private Marca marca;


}
