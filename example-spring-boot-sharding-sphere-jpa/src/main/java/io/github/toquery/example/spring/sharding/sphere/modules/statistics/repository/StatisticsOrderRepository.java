package io.github.toquery.example.spring.sharding.sphere.modules.statistics.repository;

import io.github.toquery.example.spring.sharding.sphere.modules.statistics.StatisticsOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 *
 */
public interface StatisticsOrderRepository extends JpaRepository<StatisticsOrder, Long> {
    List<StatisticsOrder> findByStoreId(Long storeId);

    List<StatisticsOrder> findByUserId(Long userId);
}
