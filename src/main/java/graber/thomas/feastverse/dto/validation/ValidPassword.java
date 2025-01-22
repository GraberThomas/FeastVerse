package graber.thomas.feastverse.dto.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for validating the strength of a password in fields or parameters.
 * <p>
 * This annotation applies validation rules to ensure that the annotated String
 * field or parameter meets predefined password strength requirements.
 * <p>
 * The validation is performed using the {@link PasswordValidator} class, which
 * enforces the following rules:
 * - The password must be at least 8 characters long.
 * - The password must include at least one uppercase letter.
 * - The password must include at least one lowercase letter.
 * - The password must include at least one digit.
 * - The password must include at least one special character from the set: #?!@$%^&*-.
 * <p>
 * Default error message: "The password is not strong enough. It must be at least 8 characters long,
 * at least one lowercase character, at least one uppercase character, at least one number,
 * and at least one special character."
 * <p>
 * Can be used on fields or method parameters and supports custom validation groups
 * and payload objects.
 */
@Constraint(validatedBy = PasswordValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {
    String message() default "The password is not strong enough. It must be at least 8 characters long, at least one lowercase character, at least one uppercase character, at least one number, and at least one special character.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
