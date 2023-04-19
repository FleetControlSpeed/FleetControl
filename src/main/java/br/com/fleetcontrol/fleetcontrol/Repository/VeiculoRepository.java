package br.com.fleetcontrol.fleetcontrol.Repository;

import br.com.fleetcontrol.fleetcontrol.Entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo,Long> {
}
