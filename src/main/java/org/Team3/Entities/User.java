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

    @Column(name = "vendor_id")
    private Long vendorID;

    public User() {
    }

    // Other properties like full name, etc. can be added as needed

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Long getVendorID() {
        return vendorID;
    }

    public void setVendorID(Long vendorID) {
        this.vendorID = vendorID;
    }

    /**
     * Determines if the user's account is non-expired.
     *
     * @return boolean value indicating if the user's account is non-expired.
     */
    @Override
    public boolean isAccountNonExpired() {
        return false; // Implementation based on application logic
    }

    /**
     * Determines if the user's account is non-locked.
     *
     * @return boolean value indicating if the user's account is non-locked.
     */
    @Override
    public boolean isAccountNonLocked() {
        return false; // Implementation based on application logic
    }

    /**
     * Determines if the user's credentials are non-expired.
     *
     * @return boolean value indicating if the user's credentials are non-expired.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return false; // Implementation based on application logic
    }

    /**
     * Determines if the user's account is enabled.
     *
     * @return boolean value indicating if the user's account is enabled.
     */
    @Override
    public boolean isEnabled() {
        return false; // Implementation based on application logic
    }

    /**
     * Retrieves the authorities granted to the user.
     *
     * @return Collection of GrantedAuthority representing the authorities granted to the user.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName())); // Assigning role-based authorities
        return authorities;
    }
}