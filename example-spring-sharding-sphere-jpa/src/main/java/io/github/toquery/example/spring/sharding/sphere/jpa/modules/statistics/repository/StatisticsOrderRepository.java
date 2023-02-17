package io.github.toquery.example.spring.sharding.sphere.jpa.modules.statistics.repository;

import io.github.toquery.example.spring.sharding.sphere.modules.statistics.StatisticsOrder;
import org.springframework.data.domain.Range;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 */
public interface StatisticsOrderRepository extends JpaRepository<StatisticsOrder, Long> {
    List<StatisticsOrder> findByStoreId(Long storeId);

    List<StatisticsOrder> findByUserId(Long userId);

    List<StatisticsOrder> findByStoreIdAndUserIdAndOrderId(Long storeId, Long userId, Long orderId);

    List<StatisticsOrder> findByUserIdAndOrderIdAndPayDateTimeAfterAndPayDateTimeBefore(Long userId, Long orderId, LocalDateTime startDate, LocalDateTime endDate);
}
