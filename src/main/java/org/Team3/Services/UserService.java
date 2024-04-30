package org.Team3.Services;

import org.Team3.Entities.User;
import org.Team3.Repositories.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * UserService class is responsible for encapsulating business logic related to the User entity.
 *
 * This service class coordinates interactions between controllers and repositories, providing methods
 * to perform CRUD (Create, Read, Update, Delete) operations on User entities. It also includes methods
 * for user authentication, registration, and checking if a user exists.
 *
 * Service methods interact with the UserRepository to perform database operations.
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //TODO: remove logger

    // Logger for logging user-related activities
    private static final Logger logger = LogManager.getLogger(UserService.class);

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Retrieves all users from the database.
     *
     * @return List of User objects representing all users in the database.
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Retrieves a user by their ID from the database.
     *
     * @param id Long representing the ID of the user to retrieve.
     * @return User object representing the user found, or null if not found.
     */
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    /**
     * Creates a new user in the database.
     *
     * @param user User object representing the user to create.
     * @return User object representing the newly created user.
     */
    public User createUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Deletes a user from the database.
     *
     * @param id Long representing the ID of the user to delete.
     * @return boolean value indicating whether the user was successfully deleted.
     *         Returns true if the user existed and was deleted, false otherwise.
     */
    public boolean deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            return false; // User with given id does not exist
        }
        userRepository.deleteById(id);
        return true;
    }

    /**
     * Authenticates a user based on the provided username and password.
     *
     * @param username String representing the username of the user.
     * @param password String representing the password of the user.
     * @return boolean value indicating whether the authentication was successful.
     *         Returns true if the provided password matches the stored password for the user, false otherwise.
     */
    public boolean authenticateUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            // Log the retrieved user and password
            logger.debug("Retrieved user: {}", user.getUsername());
            logger.debug("Retrieved password: {}", user.getPassword());

            // Check if the provided password matches the stored password
            boolean passwordMatches = passwordEncoder.matches(password, user.getPassword());
            if (passwordMatches) {
                logger.debug("Password matches");
            } else {
                logger.debug("Password does not match");
            }
            return passwordMatches;
        } else {
            // Log that no user was found with the given username
            logger.debug("No user found with username: {}", username);
            return false;
        }
    }

    /**
     * Checks if a user exists with the given username.
     *
     * @param username String representing the username to check.
     * @return boolean value indicating whether a user exists with the given username.
     */
    public boolean userExists(String username) {
        // Check if a user with the given username exists in the database
        return userRepository.existsByUsername(username);
    }

    /**
     * Registers a new user with the provided username and password.
     *
     * @param username String representing the username of the user to register.
     * @param password String representing the password of the user to register.
     * @return boolean value indicating whether the user was successfully registered.
     *         Returns true if the user was registered successfully, false if the user already exists.
     */
    public boolean registerUser(String username, String password) {
        if (userExists(username)) {
            logger.error("user: {} already exists", username);
            return false; // User already exists
        }

        // Encode the password before saving it
        String encodedPassword = passwordEncoder.encode(password);

        // Create a new User object with the provided username and encoded password
        User user = new User();
        user.setUsername(username);
        user.setPassword(encodedPassword);

        // Save the new user to the database
        userRepository.save(user);

        // Log the registration details
        logger.debug("user: {} saved! password: {}, encodedPassword: {}", username, password, encodedPassword);

        return true; // User registered successfully
    }
    public User updateUser (Long id, User user) {
        if(!userRepository.existsById(id)){
            return null;
        }
        return userRepository.save(user);
    }
}
