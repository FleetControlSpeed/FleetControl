package br.com.fleetcontrol.fleetcontrol.repository;


import br.com.fleetcontrol.fleetcontrol.entity.Eventos;
import br.com.fleetcontrol.fleetcontrol.entity.Veiculo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo,Long> {

    @Query("SELECT veiculo FROM Veiculo veiculo WHERE veiculo.ativo = true")
    public List<Veiculo> buscarPorAtivo();

    @Transactional
    @Modifying
    @Query("UPDATE Veiculo veiculo SET veiculo.ativo = false WHERE veiculo.id = :id")
    public void desativar(@Param("id")Long id);

    @Transactional
    @Modifying
    @Query("UPDATE Veiculo veiculo SET veiculo.ativo = true WHERE veiculo.id = :id")
    public void ativar(@Param("id")Long id);

    @Query("SELECT eventos FROM Eventos eventos WHERE eventos.veiculo.id = :id")
    public List<Eventos> buscaVeiculoPorEvento(@Param("id") final Long id);
}
