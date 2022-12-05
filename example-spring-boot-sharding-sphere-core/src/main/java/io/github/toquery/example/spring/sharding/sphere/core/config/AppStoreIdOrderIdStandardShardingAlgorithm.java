package io.github.toquery.example.spring.sharding.sphere.core.config;

import com.google.common.collect.Range;
import org.apache.shardingsphere.sharding.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.sharding.api.sharding.complex.ComplexKeysShardingValue;

import java.util.Collection;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 *
 */
public class AppStoreIdOrderIdStandardShardingAlgorithm implements ComplexKeysShardingAlgorithm<Long> {

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
        return AppStoreIdOrderIdStandardShardingAlgorithm.class.getSimpleName();
    }

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, ComplexKeysShardingValue<Long> shardingValue) {
        String logicTableName = shardingValue.getLogicTableName();
        Map<String, Range<Long>> columnNameAndRangeValuesMap = shardingValue.getColumnNameAndRangeValuesMap();
        Map<String, Collection<Long>> columnNameAndShardingValuesMap = shardingValue.getColumnNameAndShardingValuesMap();
        LongSummaryStatistics sum = columnNameAndShardingValuesMap.values().stream().flatMap(item -> item.stream()).collect(Collectors.summarizingLong(item -> item));
        return List.of(logicTableName + "_" + sum.getSum() % 2);
    }


}
