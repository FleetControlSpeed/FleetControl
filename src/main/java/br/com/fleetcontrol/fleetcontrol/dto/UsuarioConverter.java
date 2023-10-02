package br.com.fleetcontrol.fleetcontrol.dto;

import br.com.fleetcontrol.fleetcontrol.entity.Usuario;

import java.util.List;

public class UsuarioConverter {
    private UsuarioConverter() {
        // Este construtor está vazio; a classe não pode ser instanciada
    }
    public static UsuarioDTO toDTO(Usuario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setUsuario(usuario.getUsuario());
        usuarioDTO.setSenha(usuario.getSenha());
        usuarioDTO.setCargo(usuario.getCargo());
        usuarioDTO.setPrimeiroNome(usuario.getPrimeiroNome());
        usuarioDTO.setSobrenome(usuario.getSobrenome());
        usuarioDTO.setCpf(usuario.getCpf());
        usuarioDTO.setTelefone(usuario.getTelefone());
        usuarioDTO.setDataNascimento(usuario.getDataNascimento());
        usuarioDTO.setEndereco(usuario.getEndereco());
        return usuarioDTO;
    }

    public static Usuario toEntity(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setUsuario(usuarioDTO.getUsuario());
        usuario.setSenha(usuarioDTO.getSenha());
        usuario.setCargo(usuarioDTO.getCargo());
        usuario.setPrimeiroNome(usuarioDTO.getPrimeiroNome());
        usuario.setSobrenome(usuarioDTO.getSobrenome());
        usuario.setCpf(usuarioDTO.getCpf());
        usuario.setTelefone(usuarioDTO.getTelefone());
        usuario.setDataNascimento(usuarioDTO.getDataNascimento());
        usuario.setEndereco(usuarioDTO.getEndereco());

        return usuario;
    }

    public static List<UsuarioDTO> toDTOList(List<Usuario> usuarios) {
        return usuarios.stream()
                .map(UsuarioConverter::toDTO)
                .toList();
    }
}
