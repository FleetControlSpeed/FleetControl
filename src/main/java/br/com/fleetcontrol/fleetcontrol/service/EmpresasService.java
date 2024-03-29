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
            throw new RuntimeException("Por favor, informe um valor válido!");
        }
        Empresas empresa = empresasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Não foi possível localizar a empresa informada!"));
        return empresa;
    }

    public List<Empresas> listar() {
        if (empresasRepository.findAll().isEmpty()) {
            throw new RuntimeException("Não foi possível localizar nenhuma empresa cadastrada!");
        } else {
            return empresasRepository.findAll();
        }
    }

    public List<Empresas> listarPorAtivo() {
        if (empresasRepository.empresasAtivas().isEmpty()) {
            throw new RuntimeException("Não foi possível localizar nenhuma empresa ativa cadastrada!");
        } else {
            return empresasRepository.empresasAtivas();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public Empresas cadastrar(Empresas cadastrar) {
        return this.empresasRepository.save(cadastrar);
    }

    public Empresas atualizar(Long id, Empresas empresaAtualizada) {
        Empresas empresaExistente = empresasRepository.findById(id).orElse(null);
        if (empresaExistente == null) {
            return null;
        } else {
            empresaExistente.setEndereco(empresaAtualizada.getEndereco());
            empresaExistente.setCep(empresaAtualizada.getCep());
            empresaExistente.setNome(empresaAtualizada.getNome());
            return empresasRepository.save(empresaExistente);
        }
    }

    @Transactional
    public void desativar(Long id) {
        Empresas empresa = buscarPorId(id);

        if (!empresa.isAtivo()) {
            throw new RuntimeException("Empresa informada já está desativada!");
        } else {
            empresa.setAtivo(false);
            empresasRepository.save(empresa);
        }
    }

    @Transactional
    public void ativar(Long id) {
        Empresas empresa = buscarPorId(id);

        if (empresa.isAtivo()) {
            throw new RuntimeException("Empresa informada já está ativada!");
        } else {
            empresa.setAtivo(true);
            empresasRepository.save(empresa);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void deletar(Long id) {
        Empresas empresa = buscarPorId(id);

        List<Eventos> eventos = empresasRepository.buscaEmpresaPorEvento(id);

        if (eventos.isEmpty()) {
            empresasRepository.deleteById(id);
        } else {
            empresa.setAtivo(false);
            empresasRepository.save(empresa);
            throw new RuntimeException("Empresa possui eventos cadastrados ativos, empresa desativada!");
        }
    }
}