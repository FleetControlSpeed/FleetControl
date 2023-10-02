package br.com.fleetcontrol.fleetcontrol.Enum;

import br.com.fleetcontrol.fleetcontrol.entity.enums.Cargo;
import br.com.fleetcontrol.fleetcontrol.entity.enums.Cor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CorTest {


    @Test
    void testAmarelo() {
        assertEquals("AMARELO", Cor.AMARELO.name());
    }

    @Test
    void testToStringAmarelo() {
        assertEquals("AMARELO", Cor.AMARELO.toString());
    }

    @Test
    void testBege() {
        assertEquals("BEGE", Cor.BEGE.name());
    }

    @Test
    void testToStringBege() {
        assertEquals("BEGE", Cor.BEGE.toString());
    }

    @Test
    void testBranco() {
        assertEquals("BRANCA", Cor.BRANCA.name());
    }

    @Test
    void testToStringBranco() {
        assertEquals("BRANCA", Cor.BRANCA.toString());
    }

    @Test
    void testCinza() {
        assertEquals("CINZA", Cor.CINZA.name());
    }

    @Test
    void testToStringCinza() {
        assertEquals("CINZA", Cor.CINZA.toString());
    }

    @Test
    void testMarrom() {
        assertEquals("MARROM", Cor.MARROM.name());
    }

    @Test
    void testToStringMarrom() {
        assertEquals("MARROM", Cor.MARROM.toString());
    }

    @Test
    void testAzul() {
        assertEquals("AZUL", Cor.AZUL.name());
    }

    @Test
    void testToStringAzul() {
        assertEquals("AZUL", Cor.AZUL.toString());
    }

    @Test
    void testPrata() {
        assertEquals("PRATA", Cor.PRATA.name());
    }

    @Test
    void testToStringPrata() {
        assertEquals("PRATA", Cor.PRATA.toString());
    }

    @Test
    void testPreta() {
        assertEquals("PRETA", Cor.PRETA.name());
    }

    @Test
    void testToStringPreta() {
        assertEquals("PRETA", Cor.PRETA.toString());
    }

    @Test
    void testVerde() {
        assertEquals("VERDE", Cor.VERDE.name());
    }

    @Test
    void testToStringVerde() {
        assertEquals("VERDE", Cor.VERDE.toString());
    }

    @Test
    void testVermelho() {
        assertEquals("VERMELHO", Cor.VERMELHO.name());
    }

    @Test
    void testToStringtestVermelho() {
        assertEquals("VERMELHO", Cor.VERMELHO.toString());
    }

}
