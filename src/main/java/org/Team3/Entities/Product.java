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

    @Column(name = "image_url")
    private String imageURL;

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

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(double unitCost) {
        this.unitCost = unitCost;
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

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

}