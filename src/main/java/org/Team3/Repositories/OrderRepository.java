package org.Team3.Repositories;

import org.Team3.Entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA provides built-in methods for common CRUD operations
 * like saving, updating, deleting, and querying entities.
 * You can also define custom query methods in the repository interfaces as needed.
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
}
