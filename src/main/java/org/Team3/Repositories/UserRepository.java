package org.Team3.Repositories;

import org.Team3.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UserRepository interface extends JpaRepository interface, providing access to Spring Data JPA's built-in
 * methods for common CRUD (Create, Read, Update, Delete) operations on the User entity.
 *
 * Spring Data JPA automatically generates implementations for these methods at runtime based on their
 * method signatures, eliminating the need for manual implementation.
 *
 * Additionally, custom query methods can be defined in the repository interface as needed, allowing
 * developers to write specific queries tailored to their application requirements.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Checks if a user exists with the given username.
     *
     * @param username String representing the username to check.
     * @return boolean value indicating whether a user exists with the given username.
     */
    boolean existsByUsername(String username);

    /**
     * Finds a user by their username.
     *
     * @param username String representing the username to search for.
     * @return User object representing the user found, or null if not found.
     */
    User findByUsername(String username);
}