package br.com.fleetcontrol.fleetcontrol.entity.enums;


public enum Cargo {
    ADMINISTRADOR("ADMINISTRADOR"),
    MOTORISTA ("MOTORISTA");

    private final String value;

    private Cargo(String value){
        this.value=value;
    }
}
