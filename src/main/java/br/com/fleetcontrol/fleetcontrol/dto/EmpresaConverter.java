package br.com.fleetcontrol.fleetcontrol.dto;

import br.com.fleetcontrol.fleetcontrol.entity.Empresas;

import java.util.List;
import java.util.stream.Collectors;

public class EmpresaConverter {


    public static EmpresasDTO toDTO(Empresas empresa) {
        EmpresasDTO empresasDTO = new EmpresasDTO();
        empresasDTO.setNome(empresa.getNome());
        empresasDTO.setCEP(empresa.getCEP());
        empresasDTO.setEndereco(empresa.getEndereco());
        return empresasDTO;
    }

    public static Empresas toEntity(EmpresasDTO empresasDTO) {
        Empresas empresa = new Empresas();
        empresa.setNome(empresasDTO.getNome());
        empresa.setCEP(empresasDTO.getCEP());
        empresa.setEndereco(empresasDTO.getEndereco());
        return empresa;
    }

    public static List<EmpresasDTO> toDTOList(List<Empresas> empresas) {
        return empresas.stream()
                .map(EmpresaConverter::toDTO)
                .collect(Collectors.toList());
    }
}
