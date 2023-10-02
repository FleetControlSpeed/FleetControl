package br.com.fleetcontrol.fleetcontrol.Enum;

import br.com.fleetcontrol.fleetcontrol.entity.enums.Cargo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CargoTest {

    @Test
    void testCargo() {
        assertEquals("ADMINISTRADOR", Cargo.ADMINISTRADOR.name());
    }

    @Test
    void testToStringCargo() {assertEquals("ADMINISTRADOR", Cargo.ADMINISTRADOR.toString());}

    @Test
    void testMotorista() {
        assertEquals("MOTORISTA", Cargo.MOTORISTA.name());
    }

    @Test
    void testToStringMotorista() {assertEquals("MOTORISTA", Cargo.MOTORISTA.toString());}

}
