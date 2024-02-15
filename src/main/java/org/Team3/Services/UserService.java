package org.Team3.Services;
import org.Team3.Entities.User;
import org.Team3.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 *  Responsible for encapsulating business logic related to its corresponding entity and coordinating interactions between controllers and repositories.
 *  Service methods interact with repository classes to perform database operations.
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Logger logger = LogManager.getLogger(UserService.class);

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, User user) {
        if (!userRepository.existsById(id)) {
            return null;
        }
        user.setId(id);
        return userRepository.save(user);
    }

    public boolean deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            return false;
        }
        userRepository.deleteById(id);
        return true;
    }

    // Method to authenticate user
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

    public boolean userExists(String username) {
        // Check if a user with the given email exists in the database
        return userRepository.existsByUsername(username);
    }

    public boolean registerUser(String username, String password) {
        if (userExists(username)) {
            logger.error("user: {} already exists", username);
            return false; // User already exists
        }

        String encodedPassword = passwordEncoder.encode(password);
        User user = new User();
        user.setUsername(username);
        user.setPassword(encodedPassword);
        userRepository.save(user);
        logger.debug("user: {} saved! password: {}, encodedPassword: {}", username, password, encodedPassword);
        return true; // User registered successfully
    }

}
