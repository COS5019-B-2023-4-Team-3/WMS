package org.Team3.Entities;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * represent the data model of the warehouse system
 * annotated with JPA annotations to define their mapping to database tables
 */
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "order_date")
    private LocalDate date;

    @Column(name = "order_status")
    private String status;

    public Order() {}

    // Other properties like customer, etc. can be added as needed



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
