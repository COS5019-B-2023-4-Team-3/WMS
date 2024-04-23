package org.Team3.Entities;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * The Order class represents an order in the system.
 *
 * An order includes fields for its ID, order date, status, and vendor ID.
 * It has a one-to-many relationship with OrderItem objects, meaning an order can have multiple order items.
 */
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_id")
    private Long id;

    @Column(name = "order_date")
    private LocalDate date;

    @Column(name = "order_status")
    private String status;

    @Column(name = "vendor_id")
    private Long vendorId;

    // Other properties can be added as needed

    /**
     * Gets the ID of the order.
     *
     * @return Long representing the ID of the order.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the order.
     *
     * @param id Long representing the ID of the order.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the date of the order.
     *
     * @return LocalDate representing the date of the order.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets the date of the order.
     *
     * @param date LocalDate representing the date of the order.
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Gets the status of the order.
     *
     * @return String representing the status of the order.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of the order.
     *
     * @param status String representing the status of the order.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets the ID of the vendor associated with the order.
     *
     * @return Long representing the ID of the vendor associated with the order.
     */
    public Long getVendorId() {
        return vendorId;
    }

    /**
     * Sets the ID of the vendor associated with the order.
     *
     * @param vendorId Long representing the ID of the vendor associated with the order.
     */
    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }
}