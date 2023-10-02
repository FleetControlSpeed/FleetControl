package br.com.fleetcontrol.fleetcontrol.repository;

import br.com.fleetcontrol.fleetcontrol.entity.Empresas;
import br.com.fleetcontrol.fleetcontrol.entity.Eventos;
import br.com.fleetcontrol.fleetcontrol.entity.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpresasRepository extends JpaRepository <Empresas, Long> {
    List<Empresas> findByAtivo(boolean ativo);

    @Query("SELECT eventos FROM Eventos eventos WHERE eventos.localDestino.id = :id")
    public List<Eventos> buscaEmpresaPorEvento (@Param("id") final Long id);

    @Query("SELECT empresas FROM Empresas empresas WHERE empresas.ativo = true")
    public List<Empresas> empresasAtivas();

    @Modifying
    @Query("UPDATE Empresas empresas SET empresas.ativo = false WHERE empresas.id = :id")
    public void desativar(@Param("id")Long id);

    @Modifying
    @Query("UPDATE Empresas empresas SET empresas.ativo = true WHERE empresas.id = :id")
    public void ativar(@Param("id")Long id);

}
