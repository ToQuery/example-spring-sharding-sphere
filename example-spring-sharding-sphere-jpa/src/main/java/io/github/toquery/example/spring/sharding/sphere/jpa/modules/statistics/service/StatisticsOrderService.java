package io.github.toquery.example.spring.sharding.sphere.jpa.modules.statistics.service;

import cn.hutool.core.util.RandomUtil;
import io.github.toquery.example.spring.sharding.sphere.modules.order.Order;
import io.github.toquery.example.spring.sharding.sphere.modules.statistics.StatisticsOrder;
import io.github.toquery.example.spring.sharding.sphere.jpa.modules.statistics.repository.StatisticsOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 *
 */
@RequiredArgsConstructor
@Service
public class StatisticsOrderService {

    private final StatisticsOrderRepository statisticsOrderRepository;


    public List<StatisticsOrder> findByStoreId(Long storeId) {
        return statisticsOrderRepository.findByStoreId(storeId);
    }

    public List<StatisticsOrder> findByUserId(Long userId) {
        return statisticsOrderRepository.findByUserId(userId);
    }

    public List<StatisticsOrder> findByStoreIdAndUserIdAndOrderId(Long storeId, Long userId, Long orderId) {

        StatisticsOrder statisticsOrderQuery = new StatisticsOrder();
        if (storeId != null){
            statisticsOrderQuery.setStoreId(storeId);
        }
        if (userId!= null) {
            statisticsOrderQuery.setUserId(userId);
        }
        if (orderId!= null) {
            statisticsOrderQuery.setOrderId(orderId);
        }
        // Range.closed();
        return statisticsOrderRepository.findAll(Example.of(statisticsOrderQuery));
    }

    public List<StatisticsOrder> findByStoreIdAndUserIdAndOrderId(Long storeId, Long userId, Long orderId, LocalDate startDate, LocalDate endDate) {
        return statisticsOrderRepository.findByUserIdAndOrderIdAndPayDateTimeAfterAndPayDateTimeBefore(userId, orderId, LocalDateTime.of(startDate, LocalTime.MIN), LocalDateTime.of(endDate, LocalTime.MAX));
    }


    public List<StatisticsOrder> save(Long userId, List<Order> orders) {
        return statisticsOrderRepository.saveAll(orders.stream().map(order -> {
            StatisticsOrder statisticsOrder = new StatisticsOrder();
            statisticsOrder.setUserId(userId);
            statisticsOrder.setOrderId(order.getId());
            statisticsOrder.setStoreId((long)RandomUtil.randomInt(1, 4));
            statisticsOrder.setPayDateTime(LocalDateTime.now());
            return statisticsOrder;
        }).toList());
    }
}
