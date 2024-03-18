package org.Team3.Entities;
import javax.persistence.*;

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

