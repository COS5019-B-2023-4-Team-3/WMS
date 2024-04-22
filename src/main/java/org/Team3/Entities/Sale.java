package org.Team3.Entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "sales_data")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sales_id")
    private Long id;

    @Column(name = "transaction_date")
    private LocalDate date;

    @Column(name = "revenue")
    private Long income;

    @Column(name = "order_volume")
    private Long volume;

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
        return income;
    }

    public void setIncome(Long income) {
        this.income = income;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

}

