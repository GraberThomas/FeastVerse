package graber.thomas.feastverse.service;
import graber.thomas.feastverse.model.User;
import graber.thomas.feastverse.model.UserType;
import graber.thomas.feastverse.repository.user.UserRepository;
import graber.thomas.feastverse.repository.user.UserSpecifications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> get(UUID id) {
        return this.userRepository.findById(id);
    }

    @Override
    public Page<User> getAll(Pageable pageable) {
        List<String> allowedSortFields = List.of("firstName", "lastName", "email");
        return userRepository.findAll(pageable);
    }

    @Override
    public Page<User> getAllFiltered(String role, String lastName, String firstName, String pseudo, String email, Pageable pageable) {
        Specification<User> spec = Specification.where(null);

        if (role != null) {
            UserType userType = convertRoleToUserType(role);
            Set<UserType> roles = Collections.singleton(userType);
            spec = spec.and(UserSpecifications.hasRole(roles));
        }
        if (lastName != null) {
            spec = spec.and(UserSpecifications.hasLastName(lastName));
        }
        if (firstName != null) {
            spec = spec.and(UserSpecifications.hasFirstName(firstName));
        }
        if (pseudo != null) {
            spec = spec.and(UserSpecifications.hasPseudo(pseudo));
        }
        if(email != null) {
            spec = spec.and(UserSpecifications.hasEmail(email));
        }

        // Appeler le repository avec la spécification
        return userRepository.findAll(spec, pageable);
    }




    @Override
    public Optional<User> getByUsername(String username) {
        // -- In our context, the username represent username
        // -- But in spring security username can be username, id, pseudo, phone number and so on...
        if(username.contains("@")) {
            return Optional.ofNullable(this.userRepository.findUserByEmail(username));
        }else{
            return Optional.ofNullable(this.userRepository.findUserByPseudo(username));
        }
    }

    @Override
    public Optional<User> create(User user) {
        // -- Set create and update date to now
        user.setCreatedDate(LocalDate.now());
        user.setUpdatedDate(LocalDate.now());
        return Optional.of(this.userRepository.save(user));
    }

    @Override
    public Optional<User> update(User user) {
        // -- Set user update date to now
        user.setUpdatedDate(LocalDate.now());
        return Optional.of(this.userRepository.save(user));
    }

    @Override
    public void delete(User user) {
        this.userRepository.delete(user);
    }

    private UserType convertRoleToUserType(String role) throws IllegalArgumentException {
        try {
            return UserType.valueOf(role.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Rôle invalide : " + role);
        }
    }
}
