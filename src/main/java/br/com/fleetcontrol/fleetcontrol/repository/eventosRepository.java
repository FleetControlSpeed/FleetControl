package br.com.fleetcontrol.fleetcontrol.repository;

import br.com.fleetcontrol.fleetcontrol.entity.eventos;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface eventosRepository extends JpaRepository<eventos,Long> {

    @Query("SELECT eventos FROM Eventos eventos WHERE eventos.ativo = true")
    public List<eventos> buscarPorAtivo();

    @Transactional
    @Modifying
    @Query("UPDATE Eventos eventos SET eventos.ativo = false WHERE eventos.id = :id")
    public void desativar(@Param("id")Long id);

    @Transactional
    @Modifying
    @Query("UPDATE Eventos eventos SET eventos.ativo = true WHERE eventos.id = : id")
    public void ativar(@Param("id")Long id);
}
