package org.Team3.Repositories;

import org.Team3.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

/**
 * Spring Data JPA provides built-in methods for common CRUD operations
 * like saving, updating, deleting, and querying entities.
 * You can also define custom query methods in the repository interfaces as needed.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT * FROM products p WHERE p.expiry_date <= :currentDate", nativeQuery = true)
    List<Product> findExpiredProducts(LocalDate currentDate);

    @Query("SELECT p FROM Product p WHERE p.currentStockLevel < p.minStockLevel")
    List<Product> findLowStockProducts();
}
