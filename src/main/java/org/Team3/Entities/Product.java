package org.Team3.Entities;
import javax.persistence.*;
import java.time.LocalDate;


/**
 * represent the data model of the warehouse system
 * annotated with JPA annotations to define their mapping to database tables
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

    @Column(name = "product_price")
    private double price;
    @Column(name = "current_stock_level")
    private int currentStockLevel;

    @Column(name = "min_acceptable_stock_level")
    private int minStockLevel;

    public Product(){}

    public Product(String name, String skuCode, double price, int shelfLife, int currentStockLevel, int minStockLevel) {
        this.name = name;
        this.price = price;
        this.skuCode = skuCode;
        this.shelfLife = shelfLife;
        this.currentStockLevel = currentStockLevel;
        this.minStockLevel = minStockLevel;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

}
