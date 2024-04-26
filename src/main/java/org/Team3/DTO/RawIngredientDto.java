package org.Team3.DTO;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * The RawIngredientDto class represents a data transfer object (DTO) for transferring raw ingredient-related information between layers of the application.
 * It includes fields for the name and quantity of a raw ingredient.
 */
public class RawIngredientDto {

    @NotBlank
    private String name;
    @NotNull
    private int quantity;
    private String description;

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

    public String getDescription() { this.description = description;
        return description;
    }

    public void setDescription(String description) { this.description = description;}
}
