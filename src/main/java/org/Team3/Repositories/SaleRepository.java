package org.Team3.Repositories;

import org.Team3.Entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Query(value = "SELECT * FROM sales_data s WHERE s.transaction_date BETWEEN :startDate AND :endDate", nativeQuery = true)
    List<Sale> getSalesInRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

}

