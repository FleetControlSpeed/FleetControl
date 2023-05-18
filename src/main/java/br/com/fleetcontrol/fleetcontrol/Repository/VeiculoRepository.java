package br.com.fleetcontrol.fleetcontrol.Repository;

import br.com.fleetcontrol.fleetcontrol.Entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo,Long> {
}
