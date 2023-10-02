package br.com.fleetcontrol.fleetcontrol.repository;

import br.com.fleetcontrol.fleetcontrol.entity.Eventos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventosRepository extends JpaRepository<Eventos,Long> {
    @Query("SELECT eventos FROM Eventos eventos WHERE eventos.ativo = true")
    public List<Eventos> buscarPorAtivo();
    @Modifying
    @Query("UPDATE Eventos eventos SET eventos.ativo = false WHERE eventos.id = :id")
    public void desativar(@Param("id")Long id);

    @Modifying
    @Query("UPDATE Eventos eventos SET eventos.ativo = true WHERE eventos.id = :id")
    public void ativar(@Param("id")Long id);
}
