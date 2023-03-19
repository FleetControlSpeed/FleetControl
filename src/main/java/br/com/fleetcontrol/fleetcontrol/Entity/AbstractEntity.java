package br.com.fleetcontrol.fleetcontrol.Entity;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public abstract class AbstractEntity {

    @Id
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private LocalDateTime cadastro;
    @Getter
    @Setter
    private LocalDateTime edicao;
    @Getter
    @Setter
    private boolean ativo;

}
