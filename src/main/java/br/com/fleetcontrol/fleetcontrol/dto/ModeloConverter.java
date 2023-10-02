package br.com.fleetcontrol.fleetcontrol.dto;

import br.com.fleetcontrol.fleetcontrol.entity.Modelo;

import java.util.List;

public class ModeloConverter {


    private ModeloConverter() {
        // Este construtor está vazio; a classe não pode ser instanciada
    }
    public static ModeloDTO toDTO(Modelo modelo) {
        ModeloDTO modeloDTO = new ModeloDTO();
        modeloDTO.setNome(modelo.getNome());
        modeloDTO.setMarca(modelo.getMarca());
        return modeloDTO;
    }

    public static Modelo toEntity(ModeloDTO modeloDTO) {
        Modelo modelo = new Modelo();
        modelo.setNome(modeloDTO.getNome());
        modelo.setMarca(modeloDTO.getMarca());
        return modelo;
    }

    public static List<ModeloDTO> toDTOList(List<Modelo> modelos) {
        return modelos.stream()
                .map(ModeloConverter::toDTO)
                .toList();
    }
}
