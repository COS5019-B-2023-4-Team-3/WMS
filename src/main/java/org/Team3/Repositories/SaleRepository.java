package org.Team3.Repositories;

import org.Team3.Entities.Product;
import org.Team3.Entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.sql.*;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Query(value = "SELECT * FROM sales_data s WHERE s.transaction_date BETWEEN '2023-04-23' AND '2023-04-29'", nativeQuery = true)
    List<Sale> findSalesAmount(LocalDate currentDate);

}

