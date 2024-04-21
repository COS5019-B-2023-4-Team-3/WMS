package org.Team3.DTO;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * The ProductDto class represents a data transfer object (DTO) for transferring product-related information between layers of the application.
 * It includes fields for various attributes of a product.
 */
public class ProductDto {

    @NotBlank
    private String name;

    @NotBlank
    private String skuCode;

    @NotBlank
    private String description;

    @NotNull
    private int shelfLife;

    @NotNull
    private LocalDate expiryDate;

    @NotNull
    private int currentStockLevel;

    @NotNull
    private int minStockLevel;

    @NotNull
    private Double unitCost;

    @NotNull
    private Double unitSellPrice;

    private String imageURL;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(int shelfLife) {
        this.shelfLife = shelfLife;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getCurrentStockLevel() {
        return currentStockLevel;
    }

    public void setCurrentStockLevel(int currentStockLevel) {
        this.currentStockLevel = currentStockLevel;
    }

    public int getMinStockLevel() {
        return minStockLevel;
    }

    public void setMinStockLevel(int minStockLevel) {
        this.minStockLevel = minStockLevel;
    }

    public Double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(Double unitCost) {
        this.unitCost = unitCost;
    }

    public Double getUnitSellPrice() {
        return unitSellPrice;
    }

    public void setUnitSellPrice(Double unitSellPrice) {
        this.unitSellPrice = unitSellPrice;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}