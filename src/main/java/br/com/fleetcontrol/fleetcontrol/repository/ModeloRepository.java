package br.com.fleetcontrol.fleetcontrol.repository;

import br.com.fleetcontrol.fleetcontrol.entity.Modelo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ModeloRepository extends JpaRepository<Modelo,Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Modelo modelo SET modelo.ativo = false WHERE modelo.id = :id")
    public void desativar(@Param("id")Long id);

    @Transactional
    @Modifying
    @Query("UPDATE Modelo modelo SET modelo.ativo = true WHERE modelo.id = :id")
    public void ativar(@Param("id")Long id);

}
