package org.Team3.Entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * User class represents a user entity in the application's warehouse system.
 *
 * This entity is annotated with JPA annotations to define its mapping to the "users" table in the database.
 * It implements the UserDetails interface provided by Spring Security for user authentication and authorization.
 */
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

    /**
     * Default constructor for User class.
     */
    public User() {
    }

    /**
     * Retrieves the ID of the user.
     *
     * @return Long representing the ID of the user.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the user.
     *
     * @param id Long representing the ID of the user.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retrieves the username of the user.
     *
     * @return String representing the username of the user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Retrieves the password of the user.
     *
     * @return String representing the password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password String representing the password of the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets the username of the user.
     *
     * @param username String representing the username of the user.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Determines if the user's account is non-expired.
     *
     * @return boolean value indicating if the user's account is non-expired.
     */
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    /**
     * Determines if the user's account is non-locked.
     *
     * @return boolean value indicating if the user's account is non-locked.
     */
    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    /**
     * Determines if the user's credentials are non-expired.
     *
     * @return boolean value indicating if the user's credentials are non-expired.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    /**
     * Determines if the user's account is enabled.
     *
     * @return boolean value indicating if the user's account is enabled.
     */
    @Override
    public boolean isEnabled() {
        return false;
    }

    /**
     * Retrieves the authorities granted to the user.
     *
     * @return Collection of GrantedAuthority representing the authorities granted to the user.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        return authorities;
    }

    /**
     * Retrieves the role of the user.
     *
     * @return Role object representing the role of the user.
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets the role of the user.
     *
     * @param role Role object representing the role of the user.
     */
    public void setRole(Role role) {
        this.role = role;
    }
}