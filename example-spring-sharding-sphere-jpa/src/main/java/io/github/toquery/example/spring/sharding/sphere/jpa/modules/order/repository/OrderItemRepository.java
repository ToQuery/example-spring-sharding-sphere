package io.github.toquery.example.spring.sharding.sphere.jpa.modules.order.repository;

import io.github.toquery.example.spring.sharding.sphere.modules.order.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 *
 */
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByUserId(Long userId);
}
