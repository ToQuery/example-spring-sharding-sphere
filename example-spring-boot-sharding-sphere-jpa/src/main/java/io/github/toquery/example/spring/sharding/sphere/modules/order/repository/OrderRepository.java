package io.github.toquery.example.spring.sharding.sphere.modules.order.repository;

import io.github.toquery.example.spring.sharding.sphere.core.modules.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 *
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);
}
