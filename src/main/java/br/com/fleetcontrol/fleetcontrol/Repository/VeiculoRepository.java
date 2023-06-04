package br.com.fleetcontrol.fleetcontrol.Repository;

import br.com.fleetcontrol.fleetcontrol.Entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo,Long> {
    @Modifying
    @Query("UPDATE Veiculo veiculo SET veiculo.ativo = false WHERE veiculo.id = :idVeiculo")
    public void desativar(@Param("idVeiculo") Long id);

    @Modifying
    @Query("UPDATE Veiculo veiculo SET veiculo.ativo = true WHERE veiculo.id = :idVeiculo")
    public void ativar(@Param("idVeiculo") Long id);

    @Query("SELECT veiculo FROM Veiculo veiculo WHERE veiculo.ativo = true")
    public List<Veiculo> VeiculosAtivos();
}
