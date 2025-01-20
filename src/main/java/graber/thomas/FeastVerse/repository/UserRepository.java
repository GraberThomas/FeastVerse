package graber.thomas.FeastVerse.repository;

import graber.thomas.FeastVerse.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findUserByEmail(String email);
    User findUserByFirstName(String firstName);
    User findUserByPseudo(String pseudo);

    @Query("SELECT u FROM User u JOIN u.roles r WHERE r IN :roles")
    Page<User> findUsersByRoles(@Param("roles") Set<Integer> roles, Pageable pageable);

}
