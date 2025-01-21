package graber.thomas.feastverse.service;
import graber.thomas.feastverse.dto.user.UpdateDto;
import graber.thomas.feastverse.model.User;
import graber.thomas.feastverse.model.UserType;
import graber.thomas.feastverse.repository.user.UserRepository;
import graber.thomas.feastverse.repository.user.UserSpecifications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.util.*;

/**
 * Implementation of the UserService interface that provides application-level
 * operations for managing and retrieving User entities.
 *<p>
 * This service interacts with the UserRepository to perform core operations
 * like creating, updating, deleting, and fetching User data. It also supports
 * filtering and pagination for retrieving users.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final SecurityService securityService;

    public UserServiceImpl(UserRepository userRepository, SecurityService securityService) {
        this.userRepository = userRepository;
        this.securityService = securityService;
    }

    @Override
    public Optional<User> get(UUID id) {
        return this.userRepository.findById(id);
    }

    @Override
    public Page<User> getAllUsers(String role,
                                  String lastName,
                                  String firstName,
                                  String pseudo,
                                  String email,
                                  Pageable pageable
    ) throws AccessDeniedException, IllegalArgumentException {
        boolean hasFilter = hasFilter(role, lastName, firstName, pseudo, email);

        if (hasFilter && !securityService.hasRole("ROLE_ADMINISTRATOR")) {
            throw new AccessDeniedException("Only administrators can use filters on users.");
        }

        if (hasFilter) {
            return getAllFiltered(role, lastName, firstName, pseudo, email, pageable);
        } else {
            return userRepository.findAll(pageable);
        }
    }


    private Page<User> getAllFiltered(String role,
                                      String lastName,
                                      String firstName,
                                      String pseudo,
                                      String email,
                                      Pageable pageable
    ) {
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
        if (email != null) {
            spec = spec.and(UserSpecifications.hasEmail(email));
        }

        return userRepository.findAll(spec, pageable);
    }

    private boolean hasFilter(String role, String lastName, String firstName, String pseudo, String email) {
        return (role != null || lastName != null || firstName != null || pseudo != null || email != null);
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
    public Optional<User> patch(User user, UpdateDto updateDto) throws AccessDeniedException {
        final boolean isAdmin = securityService.hasRole("ROLE_ADMINISTRATOR");

        if(!isAdmin && updateDto.roles() != null){
            throw new AccessDeniedException("Only administrators can update roles.");
        }

        if(updateDto.firstName() != null){
            user.setFirstName(updateDto.firstName());
        }

        if(updateDto.lastName() != null){
            user.setLastName(updateDto.lastName());
        }

        if(updateDto.email() != null){
            user.setEmail(updateDto.email());
        }

        System.out.println(updateDto.roles());

        if(updateDto.roles() != null){
            user.setRoles(updateDto.roles());
        }

        if(updateDto.pseudo() != null){
            user.setPseudo(updateDto.pseudo());
        }

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
