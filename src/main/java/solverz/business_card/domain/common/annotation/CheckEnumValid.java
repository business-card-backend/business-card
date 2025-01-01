package solverz.business_card.domain.common.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import solverz.business_card.domain.common.validator.EnumValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EnumValidator.class)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckEnumValid {
    String message() default "Invalid Enum value.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    Class<? extends Enum> enumClass();
}
