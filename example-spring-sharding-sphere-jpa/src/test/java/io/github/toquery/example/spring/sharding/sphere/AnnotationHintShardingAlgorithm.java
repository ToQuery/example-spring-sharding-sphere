//package io.github.toquery.example.spring.sharding.sphere;
//
//import com.zaxxer.hikari.HikariDataSource;
//import org.apache.shardingsphere.infra.hint.HintManager;
//import org.apache.shardingsphere.sharding.api.config.ShardingRuleConfiguration;
//import org.apache.shardingsphere.sharding.api.sharding.hint.HintShardingAlgorithm;
//import org.apache.shardingsphere.sharding.api.sharding.hint.HintShardingValue;
//
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.Collection;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Properties;
//
///**
// *
// */
//class HintTest {
//
//    public static void main(String[] args) throws SQLException {
//        DataSource dataSource = new HintTest().getShardingDataSource();
//        Connection connection = dataSource.getConnection();
//        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM t_order");
//        HintManager.getInstance().setDatabaseShardingValue(1);
//        ResultSet resultSet = preparedStatement.executeQuery();
//        while (resultSet.next()) {
//            System.out.println(resultSet.getString("ORDER_ID") + " " + resultSet.getString("USER_ID"));
//        }
//        HintManager.getInstance().close();
//        resultSet.close();
//        preparedStatement.close();
//        connection.close();
//    }
//
//    private DataSource getShardingDataSource() throws SQLException {
//        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
//        shardingRuleConfig.getTableRuleConfigs().add(getOrderTableRuleConfiguration());
//        shardingRuleConfig.getTableRuleConfigs().add(getOrderItemTableRuleConfiguration());
//        shardingRuleConfig.getBindingTableGroups().add("t_order, t_order_item");
//        shardingRuleConfig.getBroadcastTables().add("t_config");
//        shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(new HintShardingStrategyConfiguration(new AnnotationHintShardingAlgorithm()));
//        shardingRuleConfig.setDefaultTableShardingStrategyConfig(new InlineShardingStrategyConfiguration("order_id", "ds_${order_id % 2}"));
//        Properties props = new Properties();
//        props.setProperty("sql.show", "true");
//        return ShardingDataSourceFactory.createDataSource(createDataSourceMap(), shardingRuleConfig, props);
//    }
//
//    private static KeyGeneratorConfiguration getKeyGeneratorConfiguration() {
//        KeyGeneratorConfiguration result = new KeyGeneratorConfiguration("SNOWFLAKE", "order_id");
//        return result;
//    }
//
//    TableRuleConfiguration getOrderTableRuleConfiguration() {
//        TableRuleConfiguration result = new TableRuleConfiguration("t_order", "ds_${0..1}.t_order_${0..1}");
//        result.setKeyGeneratorConfig(getKeyGeneratorConfiguration());
//        return result;
//    }
//
//    TableRuleConfiguration getOrderItemTableRuleConfiguration() {
//        TableRuleConfiguration result = new TableRuleConfiguration("t_order_item", "ds_${0..1}.t_order_item_${0..1}");
//        return result;
//    }
//
//    Map<String, DataSource> createDataSourceMap() {
//        Map<String, DataSource> result = new HashMap<>();
//        result.put("ds_0", DataSourceUtil.createDataSource("demo_ds_0"));
//        result.put("ds_1", DataSourceUtil.createDataSource("demo_ds_1"));
//        return result;
//    }
//}
//
//public final class DataSourceUtil {
//
//    private static final String HOST = "localhost";
//
//    private static final int PORT = 3306;
//
//    private static final String USER_NAME = "root";
//
//    private static final String PASSWORD = "123456";
//
//    public static DataSource createDataSource(final String dataSourceName) {
//        HikariDataSource result = new HikariDataSource();
//        result.setDriverClassName(com.mysql.jdbc.Driver.class.getName());
//        result.setJdbcUrl(String.format("jdbc:mysql://%s:%s/%s?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8", HOST, PORT, dataSourceName));
//        result.setUsername(USER_NAME);
//        result.setPassword(PASSWORD);
//        return result;
//    }
//}
//
//public class AnnotationHintShardingAlgorithm implements HintShardingAlgorithm<Integer> {
//
//    @Override
//    public Collection<String> doSharding(final Collection<String> availableTargetNames, final HintShardingValue<Integer> shardingValue) {
//        for (String each : availableTargetNames) {
//            if (each.endsWith(String.valueOf(shardingValue.getValues().iterator().next() % 2))) {
//                return Collections.singletonList(each);
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public Properties getProps() {
//        return null;
//    }
//
//    @Override
//    public void init(Properties props) {
//
//    }
//}
