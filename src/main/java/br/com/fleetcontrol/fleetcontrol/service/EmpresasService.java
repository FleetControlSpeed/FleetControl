package br.com.fleetcontrol.fleetcontrol.service;

import br.com.fleetcontrol.fleetcontrol.entity.Empresas;
import br.com.fleetcontrol.fleetcontrol.repository.EmpresasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class EmpresasService {

    @Autowired
    private EmpresasRepository empresasRepository;


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


    @Transactional(rollbackFor = Exception.class)
    public void deletar(final Long id){
        final Empresas empresasBanco = this.empresasRepository.findById(id).orElse(null);

        Assert.isTrue(empresasBanco != null, "Error registro nao encontrado");

        this.empresasRepository.delete(empresasBanco);
    }




}