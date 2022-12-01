package io.github.toquery.example.spring.sharding.sphere.core.config;

import com.google.common.collect.Range;
import org.apache.shardingsphere.sharding.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.sharding.api.sharding.complex.ComplexKeysShardingValue;

import java.util.Collection;
import java.util.Map;
import java.util.Properties;

/**
 *
 */
public class AppStoreIdUserIdStandardShardingAlgorithm implements ComplexKeysShardingAlgorithm<Long> {

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
        return "AppStoreIdUserIdStandardShardingAlgorithm";
    }

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, ComplexKeysShardingValue<Long> shardingValue) {
        String logicTableName = shardingValue.getLogicTableName();
        Map<String, Range<Long>> columnNameAndRangeValuesMap =  shardingValue.getColumnNameAndRangeValuesMap();
        Map<String, Collection<Long>> columnNameAndShardingValuesMap = shardingValue.getColumnNameAndShardingValuesMap();
        return null;
    }


}
