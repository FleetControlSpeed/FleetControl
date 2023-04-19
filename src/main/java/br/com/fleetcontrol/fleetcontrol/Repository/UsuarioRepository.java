package br.com.fleetcontrol.fleetcontrol.Repository;

import br.com.fleetcontrol.fleetcontrol.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
}
