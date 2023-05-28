package br.com.fleetcontrol.fleetcontrol.validation;

import br.com.fleetcontrol.fleetcontrol.validation.constraints.Placa;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PlacaValidation implements ConstraintValidator<Placa,String> {
    @Override
    public void initialize(Placa constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        String placa  = value == null ? "" : value;
        return placa.matches("[a-zA-Z]{3}-[0-9][A-Za-z0-9][0-9]{2}");

    }
}