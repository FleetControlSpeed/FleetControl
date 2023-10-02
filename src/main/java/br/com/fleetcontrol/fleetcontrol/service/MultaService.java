package br.com.fleetcontrol.fleetcontrol.service;
import br.com.fleetcontrol.fleetcontrol.entity.Empresas;
import br.com.fleetcontrol.fleetcontrol.entity.Multa;
import br.com.fleetcontrol.fleetcontrol.repository.MultaRepository;
import br.com.fleetcontrol.fleetcontrol.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MultaService {

    @Autowired
    private MultaRepository multaRepository;

    @Transactional(readOnly = true)
    public Multa buscarPorId(Long id) {
        Multa multa = multaRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Multa não encontrada!"));
        return multa;
    }

    public List<Multa> listar() {
        if (multaRepository.findAll().isEmpty()) {
            throw new RuntimeException("Não foi possível localizar nenhuma empresa cadastrada!");
        } else {
            return multaRepository.findAll();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public Multa cadastrar(Multa cadastrar) {
        return this.multaRepository.save(cadastrar);
    }

    @Transactional
    public void atualizar(Long id, Multa multaNovo) {
        Multa multaBanco = buscarPorId(id);

        if(multaNovo == null || !multaNovo.getId().equals(multaBanco.getId())){
            throw new RuntimeException(", não foi possivel identificar a multa informada!");

        } else {
            cadastrar(multaNovo);
        }
    }

    @Transactional
    public void desativar(Long id){
        Multa multaBanco = buscarPorId(id);

        if(!multaBanco.isAtivo()){
            throw new RuntimeException(", Multa informada já esta desativada!");

        } else {
            multaRepository.desativar(id);
        }
    }

    @Transactional
    public void ativar(Long id){
        Multa multaBanco = buscarPorId(id);

        if(multaBanco.isAtivo()){
            throw new RuntimeException(", Multa informada já esta ativado!");

        } else {
            multaRepository.ativar(id);
        }
    }
}
