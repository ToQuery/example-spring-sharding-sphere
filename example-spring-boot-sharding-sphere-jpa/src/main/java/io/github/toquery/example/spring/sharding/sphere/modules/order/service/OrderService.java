package io.github.toquery.example.spring.sharding.sphere.modules.order.service;

import io.github.toquery.example.spring.sharding.sphere.bff.open.order.model.response.OrderUserResponse;
import io.github.toquery.example.spring.sharding.sphere.modules.order.Order;
import io.github.toquery.example.spring.sharding.sphere.modules.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        return orderRepository.save(order);
    }

    public List<Order> save(Long userId, Long addressId) {
        Order order1 = new Order();
        order1.setUserId(userId);
        order1.setAddressId(addressId);
        order1.setStatus("PAY");
        Order order2 = new Order();
        order2.setUserId(userId);
        order2.setAddressId(addressId);
        order2.setStatus("PAY");
        return orderRepository.saveAll(List.of(order1, order2));
    }

    public Order update(Order order) {
        return orderRepository.save(order);
    }

    public List<Order> list() {
        return orderRepository.findAll();
    }


    public List<OrderUserResponse> listWithUser() {
        return orderRepository.listWithUser();
    }
    public Page<Order> page(Integer page, Integer size) {
        return orderRepository.findAll(PageRequest.of(page,size, Sort.by("createDateTime").ascending()));
    }

    public List<Order> findByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    public Order findById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        orderRepository.deleteById(id);
    }


}