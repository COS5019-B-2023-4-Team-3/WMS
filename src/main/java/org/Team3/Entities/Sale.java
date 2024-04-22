package org.Team3.Entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "sales_data")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_id")
    private Long id;

    @Column(name = "transaction_date")
    private LocalDate date;

    @Column(name = "revenue")
    private Long revenue;

    @Column(name = "order_id")
    private Long oid;

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

    public Long getIncome() {
        return revenue;
    }

    public void setIncome(Long income) {
        this.revenue = income;
    }

    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

}

