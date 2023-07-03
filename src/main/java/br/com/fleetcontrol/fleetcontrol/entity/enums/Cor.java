package br.com.fleetcontrol.fleetcontrol.entity.enums;

public enum Cor {
    AMARELO("AMARELO"),
    AZUL("AZUL"),
    BEGE("BEGE"),
    BRANCO("BRANCO"),
    CINZA("CINZA"),
    DOURADA("DOURADA"),
    GRENÁ("GRENÁ"),
    LARANJA("LARANJA"),
    MARROM("MARROM"),
    PRATA("PRATA"),
    PRETO("PRETO"),
    ROSA("ROSA"),
    ROXO("ROXO"),
    VERDE("VERDE"),
    VERMELHO("VERMELHO"),
    FANTASIA("FANTASIA");

    private final String value;

    private Cor(String value){
        this.value=value;
    }
}
