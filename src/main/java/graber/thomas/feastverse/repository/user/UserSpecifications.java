package graber.thomas.feastverse.repository.user;

import graber.thomas.feastverse.model.User;
import graber.thomas.feastverse.model.UserType;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

import java.util.Set;

/**
 * UserSpecifications is a utility class that provides a set of JPA Specifications for querying
 * the User entity. It includes methods for filtering users based on various attributes such
 * as role, first name, last name, pseudo, and email.
 * <p>
 * These specifications are designed to be used in conjunction with JPA's Criteria API,
 * enabling dynamic and type-safe query generation.
 * <p>
 * Methods:
 * - hasRole(Set<UserType> roles): Filters users based on their associated roles.
 * - hasLastName(String lastName): Filters users whose last name matches the specified string, case-insensitively.
 * - hasFirstName(String firstName): Filters users whose first name matches the specified string, case-insensitively.
 * - hasPseudo(String pseudo): Filters users whose pseudo matches the specified string, case-insensitively.
 * - hasEmail(String email): Filters users whose email matches the specified string, case-insensitively.
 */
public class UserSpecifications {

    public static Specification<User> hasRole(Set<UserType> roles) {
        return (root, query, criteriaBuilder) -> {
            if (roles == null || roles.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            Join<User, UserType> roleJoin = root.join("roles");
            return roleJoin.in(roles);
        };
    }

    public static Specification<User> hasLastName(String lastName) {
        return (root, query, criteriaBuilder) -> {
            if (lastName == null || lastName.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")), "%" + lastName.toLowerCase() + "%");
        };
    }

    public static Specification<User> hasFirstName(String firstName) {
        return (root, query, criteriaBuilder) -> {
            if (firstName == null || firstName.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")), "%" + firstName.toLowerCase() + "%");
        };
    }

    public static Specification<User> hasPseudo(String pseudo) {
        return (root, query, criteriaBuilder) -> {
            if (pseudo == null || pseudo.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("pseudo")), "%" + pseudo.toLowerCase() + "%");
        };
    }

    public static Specification<User> hasEmail(String email) {
        return (root, query, criteriaBuilder) -> {
            if (email == null || email.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("email")), "%" + email.toLowerCase() + "%");
        };
    }
}
