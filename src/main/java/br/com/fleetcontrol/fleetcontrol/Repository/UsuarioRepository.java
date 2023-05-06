package br.com.fleetcontrol.fleetcontrol.Repository;

import br.com.fleetcontrol.fleetcontrol.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/*
    @Author: Cristovão Martins
    Date: 06/05/2023

 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
}
