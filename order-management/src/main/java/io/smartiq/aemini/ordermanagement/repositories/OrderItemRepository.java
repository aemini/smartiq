package io.smartiq.aemini.ordermanagement.repositories;

import io.smartiq.aemini.ordermanagement.entities.Order;
import io.smartiq.aemini.ordermanagement.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

	List<OrderItem> findAllByOrder(Order order);

}
