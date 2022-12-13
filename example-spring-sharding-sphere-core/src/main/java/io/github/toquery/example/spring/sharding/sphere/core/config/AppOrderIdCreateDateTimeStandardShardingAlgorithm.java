package io.github.toquery.example.spring.sharding.sphere.core.config;

import com.google.common.collect.Range;
import org.apache.shardingsphere.sharding.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.sharding.api.sharding.complex.ComplexKeysShardingValue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 *
 */
public class AppOrderIdCreateDateTimeStandardShardingAlgorithm implements ComplexKeysShardingAlgorithm<Comparable<?>> {

    private Properties properties;

    @Override
    public Properties getProps() {
        return properties;
    }

    @Override
    public void init(Properties props) {
        this.properties = props;
    }

    @Override
    public String getType() {
        return AppOrderIdCreateDateTimeStandardShardingAlgorithm.class.getSimpleName();
    }

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, ComplexKeysShardingValue<Comparable<?>> shardingValue) {
        String logicTableName = shardingValue.getLogicTableName();
        Map<String, Range<Comparable<?>>> columnNameAndRangeValuesMap = shardingValue.getColumnNameAndRangeValuesMap();

        Map<String, Collection<Comparable<?>>> columnNameAndShardingValuesMap = shardingValue.getColumnNameAndShardingValuesMap();

        List<Long> orderIds = columnNameAndShardingValuesMap.get("order_id").stream().map(item -> (Long) item).toList();
        List<LocalDateTime> createDateTimes = columnNameAndShardingValuesMap.get("create_date_time").stream().map(item -> (LocalDateTime) item).toList();

        Collection<String> list = new ArrayList<>();
        for (int i = 0; i < orderIds.size(); i++) {
            LocalDateTime localDateTime = createDateTimes.get(i);
            list.add(logicTableName + "_" + orderIds.get(i) % 3 + "_" + localDateTime.getYear() + "_" + localDateTime.getMonth() + "_" + localDateTime.getDayOfMonth());
        }

        return list;
    }


}
