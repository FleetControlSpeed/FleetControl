package br.com.fleetcontrol.fleetcontrol.validation.constraints;

import br.com.fleetcontrol.fleetcontrol.validation.PlacaValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PlacaValidation.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Placa {

    String message() default "Placa do carro inválida!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
