package br.com.fleetcontrol.fleetcontrol.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@MappedSuperclass
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Column(name = "id",nullable = false,unique = true)
    private Long id;
    @Getter
    @Setter
    @Column(name = "cadastro")
    private LocalDateTime cadastro;
    @Getter
    @Setter
    @Column(name = "edicao")
    private LocalDateTime edicao;
    @Getter
    @Setter
    @Column(name = "ativo")
    private boolean ativo;

}
