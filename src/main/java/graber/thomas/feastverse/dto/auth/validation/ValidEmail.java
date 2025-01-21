package graber.thomas.feastverse.dto.auth.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for validating email addresses in fields or parameters.
 * <p>
 * This annotation applies validation rules to ensure that the annotated
 * String field or parameter contains a properly formatted email address.
 * <p>
 * The validation is performed using the {@link EmailValidator} class, which
 * checks the input against a regular expression pattern for valid email formats.
 * <p>
 * Default error message: "Invalid email address format."
 * <p>
 * Can be used on fields or method parameters and supports custom validation groups
 * and payload objects.
 */
@Constraint(validatedBy = EmailValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidEmail {
    String message() default "Invalid email address format.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
