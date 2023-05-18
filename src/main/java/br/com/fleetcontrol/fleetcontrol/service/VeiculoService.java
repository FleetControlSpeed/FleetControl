package br.com.fleetcontrol.fleetcontrol.service;

import br.com.fleetcontrol.fleetcontrol.Entity.Veiculo;
import br.com.fleetcontrol.fleetcontrol.Repository.VeiculoRepository;
import jakarta.validation.constraints.Null;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Service
public class VeiculoService {
    @Autowired
    private VeiculoRepository veiculoRepository;
    public Veiculo findById(Long id) {

        Optional<Veiculo> placa = this.veiculoRepository.findById(id);
        return placa.orElseThrow(() -> new RuntimeException(
                        "Placa nao encontrada! Id:" + id + "tipo" + Veiculo.class.getName()
                )


        );

    }

    @Transactional
    public Veiculo create(Veiculo placa) {
        try {
            placa = this.veiculoRepository.save(placa);
            return placa;
        }
        catch (Exception e){
            System.out.println("Placa não econtrada");
            throw new RuntimeException(e);

        }

    }
    @Transactional
    public Veiculo update(Veiculo veiculo) {
        Veiculo newVeiculo = findById(veiculo.getId());
        return this.veiculoRepository.save(veiculo);
    }
}

