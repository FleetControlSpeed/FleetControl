package br.com.fleetcontrol.fleetcontrol.repository;

import br.com.fleetcontrol.fleetcontrol.entity.Eventos;
import br.com.fleetcontrol.fleetcontrol.entity.Multa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MultaRepository extends JpaRepository<Multa,Long> {
    List<Multa> findByAtivo(boolean ativo);
    @Modifying
    @Query("UPDATE Multa multa SET multa.ativo = false WHERE multa.id = :id")
    public void desativar(@Param("id")Long id);

    @Modifying
    @Query("UPDATE Multa multa SET multa.ativo = true WHERE multa.id = :id")
    public void ativar(@Param("id")Long id);
}
