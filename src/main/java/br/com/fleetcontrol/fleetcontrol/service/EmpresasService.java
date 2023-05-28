package br.com.fleetcontrol.fleetcontrol.service;

import br.com.fleetcontrol.fleetcontrol.entity.Empresas;
import br.com.fleetcontrol.fleetcontrol.entity.Eventos;
import br.com.fleetcontrol.fleetcontrol.repository.EmpresasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

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

    @Transactional(rollbackFor = Exception.class)
    public void cadastrar(final Empresas empresas){
        Assert.isTrue(empresas.getNome() == null,"Error campo nome vazio");
        Assert.isTrue(empresas.getCEP() == null, "Error  campo CEP vazio");
        Assert.isTrue(empresas.getEndereco().isBlank(), "Campo ENDERECO vazio");
        this.empresasRepository.save(empresas);
    }


    @Transactional(rollbackFor = Exception.class)
    public void editar(final Long id, final Empresas empresas){
        final Empresas empresasBanco = this.empresasRepository.findById(id).orElse(null);

        Assert.isTrue(empresasBanco != null || empresasBanco.getId().equals(empresas.getId()), "Error registro nao encontrado");
        Assert.isTrue(empresas.getId().equals(id), "id da URL e diferente do body");
        Assert.isTrue(empresas.getNome() == null,"Error campo nome vazio");
        Assert.isTrue(empresas.getCEP() == null, "Error  campo CEP vazio");
        Assert.isTrue(empresas.getEndereco().isBlank(), "Campo endereço vazio");


        this.empresasRepository.save(empresas);

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
            this.empresasRepository.deleteById(id);

        } else {
            empresasRepository.desativar(empresa.getId());
            throw new RuntimeException(", empresa possui eventos cadastrados ativos, empresa desativada!");
        }
    }
}