package org.Team3.Entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.sql.*;

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

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", date=" + date +
                ", revenue=" + revenue +
                '}';
    }
}
