package io.github.toquery.example.spring.sharding.sphere.modules.statistics.service;

import io.github.toquery.example.spring.sharding.sphere.modules.statistics.StatisticsOrder;
import io.github.toquery.example.spring.sharding.sphere.modules.statistics.repository.StatisticsOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

        return statisticsOrderRepository.findAll(Example.of(statisticsOrderQuery));
    }
}
