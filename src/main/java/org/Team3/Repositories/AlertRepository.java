package org.Team3.Repositories;

import org.Team3.Entities.Alert;
import org.Team3.Entities.Order;
import org.Team3.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface AlertRepository extends JpaRepository<Alert, Long>{
    @Query(value = "SELECT * FROM alert_data", nativeQuery = true)
    List<Alert> getAllAlerts();

}
