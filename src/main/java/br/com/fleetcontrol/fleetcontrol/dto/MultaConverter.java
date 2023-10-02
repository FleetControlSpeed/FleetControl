package br.com.fleetcontrol.fleetcontrol.dto;

import br.com.fleetcontrol.fleetcontrol.entity.Multa;

import java.util.List;

public class MultaConverter {


    private MultaConverter() {
        // Este construtor está vazio; a classe não pode ser instanciada
    }
    public static MultaDTO toDTO(Multa multa) {
        MultaDTO multaDTO = new MultaDTO();
        multaDTO.setValor(multa.getValor());
        multaDTO.setTipoMulta(multa.getTipoMulta());
        multaDTO.setDataMulta(multa.getDataMulta());
        multaDTO.setUsuario(multa.getUsuario());
        return multaDTO;
    }

    public static Multa toEntity(MultaDTO multaDTO) {
        Multa multa = new Multa();
        multa.setValor(multaDTO.getValor());
        multa.setTipoMulta(multaDTO.getTipoMulta());
        multa.setDataMulta(multaDTO.getDataMulta());
        multa.setUsuario(multaDTO.getUsuario());
        return multa;
    }

    public static List<MultaDTO> toDTOList(List<Multa> multas) {
        return multas.stream()
                .map(MultaConverter::toDTO)
                .toList();
    }
}
