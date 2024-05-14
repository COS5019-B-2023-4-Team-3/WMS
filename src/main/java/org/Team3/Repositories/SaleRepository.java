package org.Team3.Repositories;

import org.Team3.Entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * Repository interface for the Sale entity.
 * This interface extends JpaRepository and provides methods to interact with the sales_data table in the database.
 */
public interface SaleRepository extends JpaRepository<Sale, Long> {

    /**
     * Custom query to fetch all sales from the sales_data table that occurred within a specified date range.
     *
     * @param startDate The start date of the range.
     * @param endDate The end date of the range.
     * @return A list of Sale objects that occurred within the specified date range.
     */
    @Query(value = "SELECT * FROM sales_data s WHERE s.transaction_date BETWEEN :startDate AND :endDate", nativeQuery = true)
    List<Sale> getSalesInRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

}

