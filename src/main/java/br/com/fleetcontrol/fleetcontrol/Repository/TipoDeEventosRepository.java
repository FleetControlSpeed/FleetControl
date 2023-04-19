package br.com.fleetcontrol.fleetcontrol.Repository;

import br.com.fleetcontrol.fleetcontrol.Entity.TipoDeEventos;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface TipoDeEventosRepository extends JpaRepository<TipoDeEventos,Long> {
}
