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

    @Query(value = """
            select
            new io.github.toquery.example.spring.sharding.sphere.bff.open.order.model.response.OrderUserResponse(o.id, o.userId, u.username, u.pwd, o.addressId, o.orderStatus, o.createDateTime)
            from Order o 
            inner join User u on u.id = o.userId
            """)
    List<OrderUserResponse> listWithUser();

    @Query(value = """
            select
            new io.github.toquery.example.spring.sharding.sphere.bff.open.order.model.response.OrderUserResponse(o.id, o.userId, u.username, u.pwd, o.addressId, o.orderStatus, o.createDateTime)
            from Order o inner join User u on u.id = o.userId
            where o.userId = :userId
            and o.id = :orderId
            """)
    List<OrderUserResponse> listWithUser(Long userId, Long orderId);

    List<Order> findByUserIdAndId(Long userId, Long orderId);

    @Query(value = """
            select
            new io.github.toquery.example.spring.sharding.sphere.bff.open.order.model.response.OrderUserResponse(o.id, o.userId, u.username, u.pwd, o.addressId, o.orderStatus, o.createDateTime)
            from Order o
            inner join User u on u.id = o.userId
            where o.userId = :userId
            """)
    List<OrderUserResponse> findOrderUserResponseByUserId(Long userId);

    @Query(value = """
            select
            new io.github.toquery.example.spring.sharding.sphere.bff.open.order.model.response.OrderUserResponse(o.id, o.userId, u.username, u.pwd, o.addressId, o.orderStatus, o.createDateTime)
            from Order o
            inner join User u on u.id = o.userId
            and o.id = :orderId
            """)
    OrderUserResponse findOrderUserResponseById(Long orderId);

    List<Order> findByUserIdAndIdIn(Long userId, List<Long> orderIds);
}
