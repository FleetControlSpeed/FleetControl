package br.com.fleetcontrol.fleetcontrol.service;

import br.com.fleetcontrol.fleetcontrol.entity.Empresas;
import br.com.fleetcontrol.fleetcontrol.entity.Eventos;
import br.com.fleetcontrol.fleetcontrol.repository.EmpresasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmpresasService {

    @Autowired
    private EmpresasRepository empresasRepository;


    public Empresas buscarPorId(Long id) {
        if (id == 0) {
            throw new RuntimeException(", por favor, informe um valor valido!");

        } else if (empresasRepository.findById(id).isEmpty()) {
            throw new RuntimeException(", não foi possivel localizar a empresa informada!");

        } else {
            return empresasRepository.findById(id).orElse(null);
        }
    }

    public List<Empresas> listar(){
        if(empresasRepository.findAll().isEmpty()){
            throw new RuntimeException(", não foi possivel localizar nenhuma empresa cadastrada!");

        } else {
            return empresasRepository.findAll();
        }
    }

    public List<Empresas> listarPorAtivo(){
        if(empresasRepository.empresasAtivas().isEmpty()){
            throw new RuntimeException(", não foi possivel localizar nenhuma empresa ativa cadastrada!");

        } else {
            return empresasRepository.empresasAtivas();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void cadastrar(final Empresas empresas){
        empresasRepository.save(empresas);
    }


    @Transactional(rollbackFor = Exception.class)
    public void editar(Long id, Empresas empresasNovo){
        final Empresas empresasBanco = buscarPorId(id);

        if(empresasBanco == null || !empresasBanco.getId().equals(empresasNovo.getId())){
            throw new RuntimeException(", não foi possivel localizar a empresa informada!");

        } else {
            cadastrar(empresasNovo);
        }
    }

    @Transactional
    public void desativar(Long id) {
        Empresas empresa = buscarPorId(id);

        if (!empresa.isAtivo()) {
            throw new RuntimeException(", empresa informada já esta desativada!");

        } else {
            empresasRepository.desativar(id);
        }
    }

    @Transactional
    public void ativar(Long id) {
        Empresas empresa = buscarPorId(id);

        if (empresa.isAtivo()) {
            throw new RuntimeException(", empresa informada já esta ativada!");

        } else {
            empresasRepository.ativar(id);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void deletar(Long id){
        Empresas empresa = buscarPorId(id);

        List<Eventos> empresas = empresasRepository.buscaEmpresaPorEvento(id);

        if(empresas.isEmpty()){
            empresasRepository.deleteById(id);

        } else {
            empresasRepository.desativar(empresa.getId());
            throw new RuntimeException(", empresa possui eventos cadastrados ativos, empresa desativada!");
        }
    }
}