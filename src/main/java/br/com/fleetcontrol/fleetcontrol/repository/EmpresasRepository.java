package br.com.fleetcontrol.fleetcontrol.repository;

import br.com.fleetcontrol.fleetcontrol.entity.Empresas;
import br.com.fleetcontrol.fleetcontrol.entity.Eventos;
import br.com.fleetcontrol.fleetcontrol.entity.Veiculo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmpresasRepository extends JpaRepository <Empresas, Long> {

    @Query("SELECT evento FROM Evento evento WHERE evento.empresa.id = :id")
    public List<Eventos> buscaEmpresaPorEvento (@Param("id") final Long id);

    @Transactional
    @Modifying
    @Query("UPDATE Empresa empresa SET empresa.ativo = false WHERE empresa.id = :id")
    public void desativar(@Param("id")Long id);

    @Transactional
    @Modifying
    @Query("UPDATE Empresa empresa SET empresa.ativo = true WHERE empresa.id = :id")
    public void ativar(@Param("id")Long id);

}
