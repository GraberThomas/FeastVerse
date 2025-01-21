package graber.thomas.feastverse.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

/**
 * The User class represents an entity for user information in the application.
 * It is mapped to the "users" table in the database.
 * <p>
 * This class includes attributes such as unique identifiers, personal details,
 * login credentials, and metadata like creation and updated timestamps.
 * The passwords are excluded from API responses for security reasons.
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    @ColumnDefault(value = "gen_random_uuid()")
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;

    @Column(unique = true, nullable = false)
    private String pseudo;


    @JsonIgnore // -- To ignore password in api response
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<UserType> roles;

    private LocalDate createdDate;
    private LocalDate updatedDate;

    public User() {
    }

    public User(UUID id, String firstName, String lastName, String email, String password, Set<UserType> roles, LocalDate createdDate, LocalDate updatedDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<UserType> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserType> roles) {
        this.roles = roles;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
}
