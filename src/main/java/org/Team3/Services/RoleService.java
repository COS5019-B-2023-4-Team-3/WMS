package org.Team3.Services;
import org.Team3.Entities.Role;
import org.Team3.Repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 *  Responsible for encapsulating business logic related to its corresponding entity and coordinating interactions between controllers and repositories.
 *  Service methods interact with repository classes to perform database operations.
 */
@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role getRoleById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    public Role updateRole(Long id, Role role) {
        if (!roleRepository.existsById(id)) {
            return null; // Role with given id does not exist
        }
        role.setId(id);
        return roleRepository.save(role);
    }

    public boolean deleteRole(Long id) {
        if (!roleRepository.existsById(id)) {
            return false; // Role with given id does not exist
        }
        roleRepository.deleteById(id);
        return true;
    }
}
