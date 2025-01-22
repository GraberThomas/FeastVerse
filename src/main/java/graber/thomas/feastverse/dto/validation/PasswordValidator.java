package graber.thomas.feastverse.dto.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Validates password strings based on a predefined regular expression pattern.
 * This validator is used in conjunction with the {@link ValidPassword} annotation
 * to enforce password strength requirements in fields or parameters.
 * <p>
 * The validation rules applied to ensure the following:
 * - The password must be at least 8 characters long.
 * - The password must include at least one uppercase letter.
 * - The password must include at least one lowercase letter.
 * - The password must include at least one digit.
 * - The password must include at least one special character from the set: #?!@$%^&*-.
 * <p>
 * Implements the {@link ConstraintValidator} interface for validating objects
 * annotated with the {@link ValidPassword} annotation.
 * <p>
 * Returns {@code true} for passwords that meet these requirements and {@code false} for
 * those that do not.
 */
public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        String regex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";
        return password.matches(regex);
    }
}
