package org.Team3.Entities;

import com.lowagie.text.pdf.PdfPCell;

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

    @Column(name = "order_volume")
    private Long volume;

    @Column(name = "order_id")
    private Long orderId;

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
    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", date=" + date +
                ", revenue=" + revenue +
                ", volume=" + volume +
                ", orderId=" + orderId +
                '}';
    }
}

