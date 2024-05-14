package org.Team3.Repositories;

import org.Team3.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

/**
 * The ProductRepository interface extends JpaRepository to provide built-in methods for CRUD operations on Product entities.
 * Additionally, custom query methods are defined for retrieving expired products and products with low stock levels.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Retrieves a list of products with expiry dates earlier than or equal to the specified current date.
     * @param currentDate The current date used to filter expired products
     * @return List of expired products
     */
    @Query(value = "SELECT * FROM products p WHERE p.expiry_date <= :currentDate", nativeQuery = true)
    List<Product> findExpiredProducts(LocalDate currentDate);

    /**
     * Retrieves a list of products with current stock levels lower than their minimum stock levels.
     * @return List of products with low stock levels
     */
    @Query("SELECT p FROM Product p WHERE p.currentStockLevel <= p.minStockLevel + 5")
    List<Product> findLowStockProducts();
}
