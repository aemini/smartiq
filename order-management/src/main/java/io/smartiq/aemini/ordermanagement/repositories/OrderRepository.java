package io.smartiq.aemini.ordermanagement.repositories;

import io.smartiq.aemini.ordermanagement.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
