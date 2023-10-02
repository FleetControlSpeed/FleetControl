package br.com.fleetcontrol.fleetcontrol.Enum;

import br.com.fleetcontrol.fleetcontrol.entity.enums.Marca;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MarcaTest {
    @Test
    void testFiat() {
        assertEquals("FIAT", Marca.FIAT.name());
    }
    @Test
    void testToStringFiat() {
        assertEquals("FIAT", Marca.FIAT.toString());
    }
    @Test
    void testChevrolet() {
        assertEquals("CHEVROLET", Marca.CHEVROLET.name());
    }

    @Test
    void testToStringChevrolet() {
        assertEquals("CHEVROLET", Marca.CHEVROLET.toString());
    }
    @Test
    void testVolkswagen() {
        assertEquals("VOLKSWAGEN", Marca.VOLKSWAGEN.name());
    }

    @Test
    void testToStringVolkswagen() {
        assertEquals("VOLKSWAGEN", Marca.VOLKSWAGEN.toString());
    }
    @Test
    void testToyota() {
        assertEquals("TOYOTA", Marca.TOYOTA.name());
    }

    @Test
    void testToStringToyota() {
        assertEquals("TOYOTA", Marca.TOYOTA.toString());
    }
    @Test
    void testHyundai() {
        assertEquals("HYUNDAI", Marca.HYUNDAI.name());
    }

    @Test
    void testToStringHyundai() {
        assertEquals("HYUNDAI", Marca.HYUNDAI.toString());
    }
    @Test
    void testRenaut() {
        assertEquals("RENAULT", Marca.RENAULT.name());
    }

    @Test
    void testToStringRenaut() {
        assertEquals("RENAULT", Marca.RENAULT.toString());
    }

    @Test
    void testToStringHonda() {
        assertEquals("HONDA", Marca.HONDA.toString());
    }
    @Test
    void testHonda() {
        assertEquals("HONDA", Marca.HONDA.name());
    }

    @Test
    void testToStringVolvo() {
        assertEquals("VOLVO", Marca.VOLVO.toString());
    }
    @Test
    void testVolvo() {
        assertEquals("VOLVO", Marca.VOLVO.name());
    }

    @Test
    void testToStringScania() {
        assertEquals("SCANIA", Marca.SCANIA.toString());
    }
    @Test
    void testScania() {
        assertEquals("SCANIA", Marca.SCANIA.name());
    }

    @Test
    void testToStringMercedes() {
        assertEquals("MERCEDES", Marca.MERCEDES.toString());
    }
    @Test
    void testMercedes() {
        assertEquals("MERCEDES", Marca.MERCEDES.name());
    }
}
