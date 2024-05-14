package org.Team3.Services;

import org.Team3.Entities.Role;
import org.Team3.Repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * RoleService class is responsible for encapsulating business logic related to the Role entity.
 *
 * This service class coordinates interactions between controllers and repositories, providing methods
 * to perform CRUD (Create, Read, Update, Delete) operations on Role entities.
 *
 * Service methods interact with the RoleRepository to perform database operations.
 */
@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * Retrieves all roles from the database.
     *
     * @return List of Role objects representing all roles in the database.
     */
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    /**
     * Retrieves a role by its ID from the database.
     *
     * @param id Long representing the ID of the role to retrieve.
     * @return Role object representing the role found, or null if not found.
     */
    public Role getRoleById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    /**
     * Creates a new role in the database.
     *
     * @param role Role object representing the role to create.
     * @return Role object representing the newly created role.
     */
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    /**
     * Updates an existing role in the database.
     *
     * @param id Long representing the ID of the role to update.
     * @param role Role object representing the updated role data.
     * @return Role object representing the updated role, or null if the role with the given ID does not exist.
     */
    public Role updateRole(Long id, Role role) {
        if (!roleRepository.existsById(id)) {
            return null; // Role with given id does not exist
        }
        role.setId(id);
        return roleRepository.save(role);
    }

    /**
     * Deletes a role from the database.
     *
     * @param id Long representing the ID of the role to delete.
     * @return boolean value indicating whether the role was successfully deleted.
     *         Returns true if the role existed and was deleted, false otherwise.
     */
    public boolean deleteRole(Long id) {
        if (!roleRepository.existsById(id)) {
            return false; // Role with given id does not exist
        }
        roleRepository.deleteById(id);
        return true;
    }
}