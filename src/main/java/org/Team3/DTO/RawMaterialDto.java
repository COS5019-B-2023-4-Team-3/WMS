package org.Team3.DTO;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RawMaterialDto {

    @NotBlank
    private String name;
    @NotNull
    private int quantity;

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
