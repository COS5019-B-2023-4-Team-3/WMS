package org.Team3.Entities;
import javax.persistence.*;

/**
 * The Role class represents a role in the system, such as "ADMIN", "EMPLOYEE", etc.
 *
 * It includes fields for the role's ID and name.
 *
 * The class is annotated with JPA annotations to define its mapping to the database table named "roles".
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
    public Role() {}

    /**
     * Constructor for Role class with parameter.
     *
     * @param roleName The name of the role.
     */
    public Role(String roleName) {
        this.name = roleName;
    }

    /**
     * Gets the ID of the role.
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
     * Gets the name of the role.
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
     * @return String containing the ID and name of the role.
     */
    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}