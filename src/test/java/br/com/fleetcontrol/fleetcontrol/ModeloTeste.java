package br.com.fleetcontrol.fleetcontrol;
import br.com.fleetcontrol.fleetcontrol.entity.Modelo;
import br.com.fleetcontrol.fleetcontrol.entity.enums.Marca;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ModeloTeste {

    private Validator validador;
    @BeforeEach
    public void configurar() {
        ValidatorFactory fabrica = Validation.buildDefaultValidatorFactory();
        validador = fabrica.getValidator();
    }
    @Test
    public void testNomeEhObrigatorio() {
        Modelo modelo = new Modelo();
        modelo.setMarca(Marca.TOYOTA);

        var violacoes = validador.validate(modelo);

        Assertions.assertFalse(violacoes.isEmpty());
        Assertions.assertTrue(
                violacoes.stream()
                        .anyMatch(violacao -> "nome".equals(violacao.getPropertyPath().toString()))
        );
    }
    @Test
    public void testMarcaEhObrigatoria() {
        Modelo modelo = new Modelo();
        modelo.setNome("MAZDA MIATA");
        var violacoes = validador.validate(modelo);

        Assertions.assertFalse(violacoes.isEmpty());
        Assertions.assertTrue(
                violacoes.stream()
                        .anyMatch(violacao -> "marca".equals(violacao.getPropertyPath().toString()))
        );
    }

    @Test
    public void testModeloValido() {
        Modelo modelo = new Modelo();
        modelo.setNome("Corolla");
        modelo.setMarca(Marca.TOYOTA);

        var violacoes = validador.validate(modelo);

        Assertions.assertTrue(violacoes.isEmpty());
    }
    ////////
}
