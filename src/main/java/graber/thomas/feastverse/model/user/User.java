package graber.thomas.feastverse.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import graber.thomas.feastverse.model.like.RecipeLike;
import graber.thomas.feastverse.model.report.Reportable;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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
@DiscriminatorValue("USER")
public class User extends Reportable {
    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String pseudo;


    @Column(nullable = false)
    @JsonIgnore // -- To ignore password in api response
    private String password;

    @Column(nullable = false)
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<UserType> roles;

    @Column(nullable = false)
    private LocalDate createdDate;

    private LocalDate updatedDate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RecipeLike> recipeLikes = new HashSet<>();

    public User() {
    }

    public User(String firstName, String lastName, String email, String password, Set<UserType> roles, LocalDate createdDate, LocalDate updatedDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
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

    public Set<RecipeLike> getRecipeLikesLikes() {
        return recipeLikes;
    }

    public void setRecipeLikesLikes(Set<RecipeLike> recipeLikes) {
        this.recipeLikes = recipeLikes;
    }
}
