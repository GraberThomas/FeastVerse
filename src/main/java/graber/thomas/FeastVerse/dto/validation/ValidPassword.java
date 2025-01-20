package graber.thomas.FeastVerse.dto.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PasswordValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {
    String message() default "The password is not strong enough. It must be at least 8 characters long, at least one lowercase character, at least one uppercase character, at least one number, and at least one special character.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
