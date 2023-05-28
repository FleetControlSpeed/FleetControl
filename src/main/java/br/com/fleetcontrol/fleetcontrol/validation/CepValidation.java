package br.com.fleetcontrol.fleetcontrol.validation;

import br.com.fleetcontrol.fleetcontrol.validation.constraints.CEP;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CepValidation implements ConstraintValidator<CEP,String> {
    @Override
    public void initialize(CEP constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String cep, ConstraintValidatorContext constraintValidatorContext) {
        String cepRegex = "^\\d{5}-\\d{3}$";
        Pattern pattern = Pattern.compile(cepRegex);
        Matcher matcher = pattern.matcher(cep);
        return matcher.matches();
    }
}