package br.com.fleetcontrol.fleetcontrol.Repository;


import br.com.fleetcontrol.fleetcontrol.Entity.Veiculo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VeiculoRepository_New extends JpaRepository<Veiculo,Long> {

    @Query("SELECT eventos FROM Eventos eventos WHERE eventos.ativo = true")
    public List<Veiculo> buscarPorAtivo();

    @Transactional
    @Modifying
    @Query("UPDATE Eventos eventos SET eventos.ativo = false WHERE eventos.id = :id")
    public void desativar(@Param("id")Long id);

    @Transactional
    @Modifying
    @Query("UPDATE Eventos eventos SET eventos.ativo = true WHERE eventos.id = : id")
    public void ativar(@Param("id")Long id);
}
