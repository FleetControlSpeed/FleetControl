package br.com.fleetcontrol.fleetcontrol.repository;

import br.com.fleetcontrol.fleetcontrol.entity.Eventos;
import br.com.fleetcontrol.fleetcontrol.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

/*
    @Author: Cristovão Martins
    Date: 06/05/2023
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    List<Usuario> findByAtivo(boolean ativo);

    @Modifying
    @Query("UPDATE Usuario usuario SET usuario.ativo = false WHERE usuario.id = :id")
    public void desativar(@Param("id") Long id);

    @Modifying
    @Query("UPDATE Usuario usuario SET usuario.ativo = true WHERE usuario.id = :id")
    public void ativar(@Param("id") Long id);

    @Query("SELECT usuario FROM Usuario usuario WHERE usuario.ativo = true")
    public List<Usuario> usuariosAtivos();

    @Query("SELECT eventos FROM Eventos eventos WHERE eventos.usuario.id = :id")
    public List<Eventos> buscaUsuarioPorEvento (@Param("id") final Long id);
}
