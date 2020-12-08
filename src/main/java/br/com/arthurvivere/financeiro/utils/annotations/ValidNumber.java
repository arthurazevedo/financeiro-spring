package br.com.arthurvivere.financeiro.utils.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidNumber implements ConstraintValidator<IsValidNumber, String> {
    private String[] allowedTypes;

    @Override
    public void initialize(IsValidNumber constraintAnnotation) {
        allowedTypes = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if(value == null) return true;

        try {
            String telefone = value.replace("(", "").replace(")", "")
                    .replace("-", "").replace(" ", "").replace(".", "")
                    .trim();
            Long number = Long.parseLong(telefone);

            return number != 0;
        } catch(Exception ex) {
            System.out.println("================================" + ex.getMessage());

            return false;
        }

    }
}
