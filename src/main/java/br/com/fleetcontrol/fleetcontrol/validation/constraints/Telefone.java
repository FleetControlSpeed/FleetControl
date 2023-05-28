package br.com.fleetcontrol.fleetcontrol.validation.constraints;

import br.com.fleetcontrol.fleetcontrol.validation.TelefoneValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = TelefoneValidation.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Telefone {

    String message() default "Telefone informado não é válido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
