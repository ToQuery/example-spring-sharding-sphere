package io.github.toquery.example.spring.sharding.sphere.modules.statistics.service;

import io.github.toquery.example.spring.sharding.sphere.modules.statistics.StatisticsOrder;
import io.github.toquery.example.spring.sharding.sphere.modules.statistics.repository.StatisticsOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
