package br.com.fleetcontrol.fleetcontrol.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tb_usuario")
public class Usuario extends AbstractEntity{
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String usuario;
    @Getter
    @Setter
    private String senha;

    @Getter
    @Setter
    private Cargo cargo;

    @Getter
    @Setter
    private String primeiroNome;
    @Getter
    @Setter
    private String sobrenome;
    @Getter
    @Setter
    private String cpf;
    @Getter
    @Setter
    private String telefone;
    @Getter
    @Setter
    private String dataNascimento;
    @Getter
    @Setter
    private String endereco;

    @Getter
    @OneToMany(mappedBy = "usuario")
    private List<Eventos> eventos;
}
