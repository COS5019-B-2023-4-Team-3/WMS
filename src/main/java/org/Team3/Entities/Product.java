package org.Team3.Entities;
import javax.persistence.*;
import java.time.LocalDate;


/**
 * The Product class represents a product in the warehouse system.
 *
 * It includes fields for the product's ID, name, SKU code, description, shelf life, expiry date,
 * selling price, unit cost, current stock level, and minimum acceptable stock level.
 *
 * The class is annotated with JPA annotations to define its mapping to the database table named "products".
 */
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private Long id;

    @Column(name = "product_name")
    private String name;

    @Column(name = "sku_code", unique = true)
    private String skuCode;

    @Column(name="product_description")
    private String description;

    @Column(name = "shelf_life_in_days")
    private int shelfLife;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    @Column(name = "selling_price")
    private double sellingPrice;

    @Column(name = "unit_cost")
    private double unitCost;

    @Column(name = "current_stock_level")
    private int currentStockLevel;

    @Column(name = "min_acceptable_stock_level")
    private int minStockLevel;

    /**
     * Gets the ID of the product.
     *
     * @return Long representing the ID of the product.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the product.
     *
     * @param id Long representing the ID of the product.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the product.
     *
     * @return String representing the name of the product.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the product.
     *
     * @param name String representing the name of the product.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the SKU code of the product.
     *
     * @return String representing the SKU code of the product.
     */
    public String getSkuCode() {
        return skuCode;
    }

    /**
     * Sets the SKU code of the product.
     *
     * @param skuCode String representing the SKU code of the product.
     */
    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    /**
     * Gets the description of the product.
     *
     * @return String representing the description of the product.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the product.
     *
     * @param description String representing the description of the product.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the shelf life of the product in days.
     *
     * @return int representing the shelf life of the product in days.
     */
    public int getShelfLife() {
        return shelfLife;
    }

    /**
     * Sets the shelf life of the product in days.
     *
     * @param shelfLife int representing the shelf life of the product in days.
     */
    public void setShelfLife(int shelfLife) {
        this.shelfLife = shelfLife;
    }

    /**
     * Gets the expiry date of the product.
     *
     * @return LocalDate representing the expiry date of the product.
     */
    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    /**
     * Sets the expiry date of the product.
     *
     * @param expiryDate LocalDate representing the expiry date of the product.
     */
    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    /**
     * Gets the selling price of the product.
     *
     * @return double representing the selling price of the product.
     */
    public double getSellingPrice() {
        return sellingPrice;
    }

    /**
     * Sets the selling price of the product.
     *
     * @param sellingPrice double representing the selling price of the product.
     */
    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    /**
     * Gets the unit cost of the product.
     *
     * @return double representing the unit cost of the product.
     */
    public double getUnitCost() {
        return unitCost;
    }

    /**
     * Sets the unit cost of the product.
     *
     * @param unitCost double representing the unit cost of the product.
     */
    public void setUnitCost(double unitCost) {
        this.unitCost = unitCost;
    }

    /**
     * Gets the current stock level of the product.
     *
     * @return int representing the current stock level of the product.
     */
    public int getCurrentStockLevel() {
        return currentStockLevel;
    }

    /**
     * Sets the current stock level of the product.
     *
     * @param currentStockLevel int representing the current stock level of the product.
     */
    public void setCurrentStockLevel(int currentStockLevel) {
        this.currentStockLevel = currentStockLevel;
    }

    /**
     * Gets the minimum acceptable stock level of the product.
     *
     * @return int representing the minimum acceptable stock level of the product.
     */
    public int getMinStockLevel() {
        return minStockLevel;
    }

    /**
     * Sets the minimum acceptable stock level of the product.
     *
     * @param minStockLevel int representing the minimum acceptable stock level of the product.
     */
    public void setMinStockLevel(int minStockLevel) {
        this.minStockLevel = minStockLevel;
    }
}