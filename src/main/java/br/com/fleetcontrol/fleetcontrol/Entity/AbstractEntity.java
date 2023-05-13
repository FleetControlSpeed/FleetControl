package br.com.fleetcontrol.fleetcontrol.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@NoArgsConstructor
@MappedSuperclass
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Column(name = "id",nullable = false,unique = true)
    private Long id;
    @Getter
    @Setter
    @Column(name = "cadastro",nullable = false)
    @NotNull
    @NotEmpty
    private LocalDateTime cadastro;
    @Getter
    @Setter
    @Column(name = "edicao")
    private LocalDateTime edicao;
    @Getter
    @Setter
    @Column(name = "ativo",nullable = false)
    @NotNull
    @NotEmpty
    private boolean ativo;

    /**
     * Método automatico, executado no pré-cadastro dos dados
     */
    @PrePersist
    public void prePersist() {
        this.cadastro = LocalDateTime.now();
        this.ativo = true;
    }

    /**
     * Método automatico, executado no pré-edição dos dadosd
     */
    @PreUpdate
    public void preUpdate() {
        this.edicao = LocalDateTime.now();
    }

}
