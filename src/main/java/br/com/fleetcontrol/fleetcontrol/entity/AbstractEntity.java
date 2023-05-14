package br.com.fleetcontrol.fleetcontrol.entity;

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
    @NotNull(message = "Data de Cadastro não pode ser nula!")
    @NotEmpty
    private LocalDateTime cadastro;
    @Getter
    @Setter
    @Column(name = "edicao")
    private LocalDateTime edicao;
    @Getter
    @Setter
    @Column(name = "ativo",nullable = false)
    @NotNull(message = "Ativo tem que ser true or false!")
    @NotEmpty
    private boolean ativo;


    /**
     * Método automatico, executado no pré-cadastro dos dados
     */
    @PrePersist
    private void prePersist() {
        this.cadastro = LocalDateTime.now();
        this.ativo = true;
    }
    /**
     * Método automatico, executado no pré-edição dos dadosd
     */

    private void preUpdate() {
        this.edicao = LocalDateTime.now();
    }

}
