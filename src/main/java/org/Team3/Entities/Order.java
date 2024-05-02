package org.Team3.Entities;

import javax.persistence.*;
import java.time.LocalDate;

/**
 The Order class represents an order and includes fields for id, orderItems, date, and status.
 It has a one-to-many relationship with OrderItem objects, meaning an order can have multiple order items.
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

}