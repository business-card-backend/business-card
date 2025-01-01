package solverz.business_card.domain.common.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import solverz.business_card.domain.common.annotation.CheckEnumValid;

import java.util.Arrays;

public class EnumValidator implements ConstraintValidator<CheckEnumValid, Enum> {

    private CheckEnumValid checkEnumValid;

    @Override
    public void initialize(CheckEnumValid constraintAnnotation) {
        this.checkEnumValid = constraintAnnotation;
    }

    @Override
    public boolean isValid(Enum value, ConstraintValidatorContext context) {
        Object[] enumValues = this.checkEnumValid.enumClass().getEnumConstants();
        if (enumValues == null) {
            return false;
        }
        return Arrays.stream(enumValues)
                .map(Object::toString)
                .anyMatch(enumName -> enumName.equalsIgnoreCase(value.toString()));
    }
}
