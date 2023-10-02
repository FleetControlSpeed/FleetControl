package br.com.fleetcontrol.fleetcontrol.Enum;

import br.com.fleetcontrol.fleetcontrol.entity.enums.Cor;
import br.com.fleetcontrol.fleetcontrol.entity.enums.Tipo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TipoTest {

    @Test
    void testCarro() {
        assertEquals("CARRO", Tipo.CARRO.name());
    }

    @Test
    void testToStringCarro() {
        assertEquals("CARRO", Tipo.CARRO.toString());
    }

    @Test
    void testVan() {
        assertEquals("VAN", Tipo.VAN.name());
    }

    @Test
    void testToStringVan() {
        assertEquals("VAN", Tipo.VAN.toString());
    }

    @Test
    void testCaminhao() {
        assertEquals("CAMINHÃO", Tipo.CAMINHÃO.name());
    }

    @Test
    void testToStringCaminhao() {
        assertEquals("CAMINHÃO", Tipo.CAMINHÃO.toString());
    }

}
