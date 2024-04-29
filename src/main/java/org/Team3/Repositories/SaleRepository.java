package org.Team3.Repositories;

import org.Team3.Entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Query(value = "SELECT * FROM sales_data s WHERE s.transaction_date BETWEEN '2024-04-22' AND '2024-04-15' GROUP BY s.transaction_date", nativeQuery = true)
    List<Sale> findSalesAmount(LocalDate currentDate);

}

