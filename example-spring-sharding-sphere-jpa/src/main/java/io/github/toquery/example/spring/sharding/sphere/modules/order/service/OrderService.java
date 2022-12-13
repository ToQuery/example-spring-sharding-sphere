package io.github.toquery.example.spring.sharding.sphere.modules.order.service;

import cn.hutool.core.util.RandomUtil;
import io.github.toquery.example.spring.sharding.sphere.bff.open.order.model.response.OrderUserResponse;
import io.github.toquery.example.spring.sharding.sphere.modules.order.Order;
import io.github.toquery.example.spring.sharding.sphere.modules.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;


    public Order save(Order order) {
        return orderRepository.saveAndFlush(order);
    }

    public List<Order> save(Long userId, Long addressId) {
        Order order1 = new Order();
        order1.setUserId(userId);
        order1.setAddressId(addressId);
        order1.setOrderStatus(RandomUtil.randomInt(1, 5));
        Order order2 = new Order();
        order2.setUserId(userId);
        order2.setAddressId(addressId);
        order2.setOrderStatus(RandomUtil.randomInt(1, 5));
        return orderRepository.saveAll(List.of(order1, order2));
    }

    public Order update(Order order) {
        return orderRepository.saveAndFlush(order);
    }

    public List<Order> list() {
        return orderRepository.findAll();
    }


    public List<Order> list(Long userId, Long orderId, List<Long> orderIds) {
        if (orderIds == null || orderIds.isEmpty()) {
            Order orderQuery = new Order();

            if (userId != null) {
                orderQuery.setUserId(userId);
            }
            if (orderId != null) {
                orderQuery.setId(orderId);
            }
            return orderRepository.findAll(Example.of(orderQuery));
        } else {
            return orderRepository.findByUserIdAndIdIn(userId, orderIds);
        }

    }

    public List<OrderUserResponse> listWithUser() {
        return orderRepository.listWithUser();
    }


    public List<OrderUserResponse> listWithUser(Long userId, Long orderId) {
        return orderRepository.listWithUser(userId, orderId);
    }

    public Page<Order> page(Integer page, Integer size) {
        return orderRepository.findAll(PageRequest.of(page, size, Sort.by("orderStatus", "createDateTime").descending()));
    }

    public List<Order> findByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    public Order findById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public List<OrderUserResponse> findOrderUserResponseByUserId(Long userId) {
        return orderRepository.findOrderUserResponseByUserId(userId);
    }

    public OrderUserResponse findOrderUserResponseById(Long orderId) {
        return orderRepository.findOrderUserResponseById(orderId);
    }

    public void delete(Long id) {
        orderRepository.deleteById(id);
    }


    public List<Order> saveAll(List<Order> orders) {
        return orderRepository.saveAllAndFlush(orders);
    }

    public List<Order> listByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }
}
