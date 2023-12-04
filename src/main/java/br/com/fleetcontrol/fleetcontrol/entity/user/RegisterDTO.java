package br.com.fleetcontrol.fleetcontrol.entity.user;

public record RegisterDTO(String username, String password, UserRole role) {
}
