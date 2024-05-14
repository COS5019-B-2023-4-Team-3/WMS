package org.Team3.Entities;
import javax.persistence.*;

/**
 * The OrderItem class represents an individual item within an order.
 *
 * An order item includes fields for its ID, quantity, cost, selling price, and profit.
 * It has many-to-one relationships with both Order and Product classes, meaning each order item belongs to one order and one product.
 */
@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "item_cost")
    private double cost;

    @Column(name = "item_selling_price")
    private double sellingPrice;

    @Column(name = "order_item_profit")
    private double profit;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    /**
     * Gets the ID of the order item.
     *
     * @return Long representing the ID of the order item.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the order item.
     *
     * @param id Long representing the ID of the order item.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the order to which the order item belongs.
     *
     * @return Order representing the order to which the order item belongs.
     */
    public Order getOrder() {
        return order;
    }

    /**
     * Sets the order to which the order item belongs.
     *
     * @param order Order representing the order to which the order item belongs.
     */
    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     * Gets the product associated with the order item.
     *
     * @return Product representing the product associated with the order item.
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Sets the product associated with the order item.
     *
     * @param product Product representing the product associated with the order item.
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Gets the quantity of the order item.
     *
     * @return int representing the quantity of the order item.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the order item.
     *
     * @param quantity int representing the quantity of the order item.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets the selling price of the order item.
     *
     * @return double representing the selling price of the order item.
     */
    public double getSellingPrice() {
        return sellingPrice;
    }

    /**
     * Sets the selling price of the order item.
     *
     * @param sellingPrice double representing the selling price of the order item.
     */
    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    /**
     * Gets the cost of the order item.
     *
     * @return double representing the cost of the order item.
     */
    public double getCost() {
        return cost;
    }

    /**
     * Sets the cost of the order item.
     *
     * @param cost double representing the cost of the order item.
     */
    public void setCost(double cost) {
        this.cost = cost;
    }

    /**
     * Gets the profit of the order item.
     *
     * @return double representing the profit of the order item.
     */
    public double getProfit() {
        return profit;
    }

    /**
     * Sets the profit of the order item.
     *
     * @param profit double representing the profit of the order item.
     */
    public void setProfit(double profit) {
        this.profit = profit;
    }
}