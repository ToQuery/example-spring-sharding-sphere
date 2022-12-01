package io.github.toquery.example.spring.sharding.sphere.core.config;

import com.google.common.collect.Range;
import org.apache.shardingsphere.infra.datanode.DataNodeInfo;
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;

import java.util.Collection;
import java.util.Properties;

/**
 *
 */
public class AppOrderIdStandardShardingAlgorithm implements StandardShardingAlgorithm<Long> {

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
        return "AppOrderIdStandardShardingAlgorithm";
    }


    /**
     *
     * @param collection 所有的物理表名称
     * @param preciseShardingValue
     */
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Long> preciseShardingValue) {
        Long value = preciseShardingValue.getValue();
        String columnName = preciseShardingValue.getColumnName();
        String logicTableName = preciseShardingValue.getLogicTableName();
        DataNodeInfo dataNodeInfo = preciseShardingValue.getDataNodeInfo();
        return dataNodeInfo.getPrefix() + (value % 2);
    }

    /**
     *
     * @param collection    所有的物理表名称
     * @param rangeShardingValue
     */
    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Long> rangeShardingValue) {

        String columnName = rangeShardingValue.getColumnName();
        Range<Long> rangeValue = rangeShardingValue.getValueRange();
        String logicTableName = rangeShardingValue.getLogicTableName();
        DataNodeInfo dataNodeInfo = rangeShardingValue.getDataNodeInfo();

        return null;
    }


}
