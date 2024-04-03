package org.Team3.Entities;
import javax.persistence.*;

/**
 * Role class represents a role entity in the application.
 *
 * This entity is mapped to the "roles" table in the database.
 * It includes fields for the role ID and role name.
 */
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="role_id")
    private Long id;

    @Column(name = "role_name")
    private String name;

    /**
     * Default constructor for Role class.
     */
    public Role() {
    }

    /**
     * Parameterized constructor for Role class.
     *
     * @param roleName String representing the name of the role.
     */
    public Role(String roleName) {
        this.name = roleName;
    }

    /**
     * Retrieves the ID of the role.
     *
     * @return Long representing the ID of the role.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the role.
     *
     * @param id Long representing the ID of the role.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the role.
     *
     * @return String representing the name of the role.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the role.
     *
     * @param name String representing the name of the role.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns a string representation of the Role object.
     *
     * @return String representing the Role object.
     */
    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
