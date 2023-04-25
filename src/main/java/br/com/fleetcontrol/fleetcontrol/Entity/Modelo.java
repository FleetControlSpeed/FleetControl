package br.com.fleetcontrol.fleetcontrol.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Entity
@Table(name = "tb_Modelo", schema = "public")
public class Modelo extends AbstractEntity{
    @Getter
    @Setter
    @NotNull
    @NotEmpty
    @Size(min = 3, max = 20)
    @Column(name = "nome",nullable = false,length = 20)
    private String nome;

    @Getter
    @Setter
    @NotNull
    @NotEmpty
    @Enumerated(EnumType.STRING)
    @Column(name = "marca",nullable = false)
    private Marca marca;

}
