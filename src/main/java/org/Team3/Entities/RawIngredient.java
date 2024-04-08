package org.Team3.Entities;
import javax.persistence.*;

/**
 * The RawIngredient class represents a raw ingredient in the warehouse system.
 *
 * It includes fields for the raw ingredient's ID, name, and quantity.
 *
 * The class is annotated with JPA annotations to define its mapping to the database table named "raw_ingredients".
 */
@Entity
@Table(name = "raw_ingredients")
public class RawIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "raw_ingredient_id")
    private Long id;

    @Column(name = "raw_ingredient_name")
    private String name;

    @Column(name = "raw_ingredient_quantity")
    private int quantity;

    /**
     * Default constructor for RawIngredient class.
     */
    public RawIngredient(){}

    /**
     * Constructor for RawIngredient class with parameters.
     *
     * @param name     The name of the raw ingredient.
     * @param quantity The quantity of the raw ingredient.
     */
    public RawIngredient(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    /**
     * Gets the ID of the raw ingredient.
     *
     * @return Long representing the ID of the raw ingredient.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the raw ingredient.
     *
     * @param id Long representing the ID of the raw ingredient.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the raw ingredient.
     *
     * @return String representing the name of the raw ingredient.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the raw ingredient.
     *
     * @param name String representing the name of the raw ingredient.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the quantity of the raw ingredient.
     *
     * @return int representing the quantity of the raw ingredient.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the raw ingredient.
     *
     * @param quantity int representing the quantity of the raw ingredient.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}