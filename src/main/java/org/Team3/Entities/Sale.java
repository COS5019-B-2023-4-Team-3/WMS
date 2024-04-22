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


}

