package io.github.toquery.example.spring.sharding.sphere.core.config;

import org.apache.shardingsphere.sharding.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.sharding.api.sharding.hint.HintShardingValue;

import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 *
 */
public class AppDatabaseTenantHintShardingAlgorithm implements HintShardingAlgorithm<Long> {

    private Properties properties;

    @Override
    public String getType() {
        return AppDatabaseTenantHintShardingAlgorithm.class.getSimpleName();
    }

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, HintShardingValue<Long> shardingValue) {
        Long value = shardingValue.getValues().stream().findFirst().get();
        // return availableTargetNames.stream().filter(availableTargetName -> availableTargetName.endsWith(value.toString())).collect(Collectors.toList());
        return List.of("ds-" + value.toString());
    }

    @Override
    public Properties getProps() {
        return properties;
    }

    @Override
    public void init(Properties props) {
        this.properties = props;
    }



}
