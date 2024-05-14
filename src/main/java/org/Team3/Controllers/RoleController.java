package org.Team3.Controllers;

import org.Team3.Entities.Role;
import org.Team3.Services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * RoleController class defines endpoints to handle CRUD (Create, Read, Update, Delete) operations related to user roles.
 *
 * This controller is responsible for processing HTTP requests and returning appropriate responses for operations
 * on user roles such as retrieving all roles, getting a role by ID, creating a new role, updating an existing role, and deleting a role.
 */
@Controller
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * Retrieves all roles from the database.
     *
     * @return ResponseEntity containing a List of Role objects representing all roles in the system.
     *         Returns HTTP status code OK (200) on success.
     */
    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.getAllRoles();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    /**
     * Retrieves a role by its ID from the database.
     *
     * @param id Long representing the ID of the role to retrieve.
     * @return ResponseEntity containing a Role object representing the retrieved role.
     *         Returns HTTP status code OK (200) if the role is found.
     *         Returns HTTP status code NOT_FOUND (404) if the role with the given ID is not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Long id) {
        Role role = roleService.getRoleById(id);
        if (role == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    /**
     * Creates a new role in the database.
     *
     * @param role Role object representing the role to create.
     * @return ResponseEntity containing a Role object representing the newly created role.
     *         Returns HTTP status code CREATED (201) on success.
     */
    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        Role createdRole = roleService.createRole(role);
        return new ResponseEntity<>(createdRole, HttpStatus.CREATED);
    }

    /**
     * Updates an existing role in the database.
     *
     * @param id   Long representing the ID of the role to update.
     * @param role Role object representing the updated role data.
     * @return ResponseEntity containing a Role object representing the updated role.
     *         Returns HTTP status code OK (200) if the role is updated successfully.
     *         Returns HTTP status code NOT_FOUND (404) if the role with the given ID is not found.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable Long id, @RequestBody Role role) {
        Role updatedRole = roleService.updateRole(id, role);
        if (updatedRole == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedRole, HttpStatus.OK);
    }

    /**
     * Deletes a role from the database.
     *
     * @param id Long representing the ID of the role to delete.
     * @return ResponseEntity with no content.
     *         Returns HTTP status code NO_CONTENT (204) if the role is deleted successfully.
     *         Returns HTTP status code NOT_FOUND (404) if the role with the given ID is not found.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        boolean deleted = roleService.deleteRole(id);
        if (!deleted) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}