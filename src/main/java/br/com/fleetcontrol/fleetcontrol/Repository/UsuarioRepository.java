package br.com.fleetcontrol.fleetcontrol.Repository;

import br.com.fleetcontrol.fleetcontrol.Entity.Usuario;
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

    @Modifying
    @Query("UPDATE Usuario usuario SET usuario.ativo = false WHERE usuario.id = :idUsuario")
    public void desativar(@Param("idUsuario") Long id);

    @Query("SELECT usuario FROM Usuario usuario WHERE usuario.ativo = true")
    public List<Usuario> UsuariosAtivos();
}
