package graber.thomas.feastverse.dto.auth.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

/**
 * Validates email address strings based on a predefined regular expression pattern.
 * This validator is used in conjunction with the {@link ValidEmail} annotation
 * to enforce valid email address formats in fields or parameters.
 * <p>
 * The validation rules applied to ensure the following:
 * 1. The email contains an '@' symbol separating the local and domain parts.
 * 2. The domain includes a valid top-level domain (TLD) of 2 to 6 characters.
 * 3. The email only allows standard characters, numbers, dots, underscores,
 *    percentage signs, plus signs, and hyphens.
 * <p>
 * Implements the {@link ConstraintValidator} interface for validating objects
 * annotated with the {@link ValidEmail} annotation.
 * <p>
 * Returns {@code true} for valid email addresses and {@code false} for invalid ones
 * or when the input is {@code null} or an empty string.
 */
public class EmailValidator implements ConstraintValidator<ValidEmail, String> {
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$"
    );

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if(email == null) return true;
        if (email.isEmpty()) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches();
    }
}
