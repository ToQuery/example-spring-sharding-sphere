package io.github.toquery.example.spring.sharding.sphere.modules.order.repository;

import io.github.toquery.example.spring.sharding.sphere.bff.open.order.model.response.OrderUserResponse;
import io.github.toquery.example.spring.sharding.sphere.modules.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 *
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);

    @Query(value = "select new io.github.toquery.example.spring.sharding.sphere.bff.open.order.model.response.OrderUserResponse(o.id) as orderId from Order o left join User u on u.id = o.userId")
    List<OrderUserResponse> listWithUser();
}
