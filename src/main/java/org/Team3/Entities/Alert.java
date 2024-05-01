package org.Team3.Entities;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Entity class for the Alert table in the database.
 */
@Entity
@Table(name = "alert_data")
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alert_id")
    private Long id;

    @Column(name = "alert_type")
    private String type;

    @Column(name = "alert_message")
    private String message;

    @Column(name = "alert_timestamp")
    private LocalDate timestamp;

    @Column(name = "product_id")
    private Long prod_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String name) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }

    public Long getProd_id() {
        return prod_id;
    }

    public void setProd_id(Long prod_id) {
        this.prod_id = prod_id;
    }
}
