package org.Team3.Entities;
import javax.persistence.*;

/**
 * The RawIngredient class represents a raw ingredient in the warehouse system.
 * It includes fields for the raw ingredient's ID, name, and quantity.
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}