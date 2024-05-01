package org.Team3.Repositories;

import org.Team3.Entities.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for the Alert entity.
 * This interface extends JpaRepository and provides methods to interact with the alert_data table in the database.
 */
public interface AlertRepository extends JpaRepository<Alert, Long>{

}
