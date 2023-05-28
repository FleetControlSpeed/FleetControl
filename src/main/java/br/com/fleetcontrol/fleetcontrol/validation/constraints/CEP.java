package br.com.fleetcontrol.fleetcontrol.validation.constraints;

import br.com.fleetcontrol.fleetcontrol.validation.CepValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CepValidation.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CEP {

    String message() default "CEP informado não é válido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
