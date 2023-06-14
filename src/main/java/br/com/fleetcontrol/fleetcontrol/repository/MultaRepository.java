package br.com.fleetcontrol.fleetcontrol.repository;

import br.com.fleetcontrol.fleetcontrol.entity.Multa;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MultaRepository extends JpaRepository<Multa,Long> {
    @Modifying
    @Query("UPDATE Empresas empresas SET empresas.ativo = false WHERE empresas.id = :id")
    public void desativar(@Param("id")Long id);

    @Modifying
    @Query("UPDATE Empresas empresas SET empresas.ativo = true WHERE empresas.id = :id")
    public void ativar(@Param("id")Long id);
}
