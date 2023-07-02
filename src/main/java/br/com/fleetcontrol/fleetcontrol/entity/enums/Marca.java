package br.com.fleetcontrol.fleetcontrol.entity.enums;

public enum Marca {
    FIAT("FIAT"),
    CHEVROLET("CHEVROLET"),
    VOLKSWAGEN("VOLKSWAGEN"),
    TOYOTA("TOYOTA"),
    HYUNDAI("HYUNDAI"),
    RENAULT("RENAULT"),
    HONDA("HONDA"),
    VOLVO("VOLVO"),
    SCANIA("SCANIA"),
    MERCEDES("MERCEDES");

    private final String value;

    Marca(String value) {
        this.value = value;
    }
}
