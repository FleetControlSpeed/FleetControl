package br.com.fleetcontrol.fleetcontrol.dto;
import br.com.fleetcontrol.fleetcontrol.entity.Veiculo;

import java.util.List;
import java.util.stream.Collectors;

public class VeiculoConverter {


    public static VeiculoDTO toDTO(Veiculo veiculo) {
        VeiculoDTO veiculoDTO = new VeiculoDTO();
        veiculoDTO.setModelo(veiculo.getModelo());
        veiculoDTO.setPlaca(veiculo.getPlaca());
        veiculoDTO.setAno(veiculo.getAno());
        veiculoDTO.setCor(veiculo.getCor());
        veiculoDTO.setKm(veiculo.getKm());
        veiculoDTO.setTipo(veiculo.getTipo());
        return veiculoDTO;
    }

    public static Veiculo toEntity(VeiculoDTO veiculoDTO) {
        Veiculo veiculo = new Veiculo();
        veiculo.setModelo(veiculoDTO.getModelo());
        veiculo.setPlaca(veiculoDTO.getPlaca());
        veiculo.setAno(veiculoDTO.getAno());
        veiculo.setCor(veiculoDTO.getCor());
        veiculo.setKm(veiculoDTO.getKm());
        veiculo.setTipo(veiculoDTO.getTipo());
        return veiculo;
    }
    public static List<VeiculoDTO> toDTOList(List<Veiculo> veiculos) {
        return veiculos.stream()
                .map(VeiculoConverter::toDTO)
                .collect(Collectors.toList());
    }
}
