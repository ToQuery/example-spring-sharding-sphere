# example-spring-sharding-sphere

[TOC]

## 业务场景准备：

共有两个数据库（es3_write_ds_0、es3_write_ds_1），并且数据库都实现一个读库，总共存在4个数据库。存在6张逻辑表（如下）：

- tb_account 账号表
- tb_address 地址表
- tb_order 主订单表
- tb_order_item 子订单表
- tb_user 用户表
- tb_statistics_order统计订单表

tb_user 为广播表，所有库表都一致，tb_account、tb_address 为分库不分表，tb_order、tb_order_item为分库分表，tb_statistics_order多字段分库分表。

### 逻辑数据

- tb_user

| id   | username | pwd    |
| ---- | -------- | ------ |
| 1    | user_1   | user_1 |
| 2    | user_2   | user_2 |
| 3    | user_3   | user_3 |
| 4    | user_4   | user_4 |
| 5    | user_5   | user_5 |
| 6    | user_6   | user_6 |

------

- tb_account

| 数据库（物理） | id   | user_id | account_status |
| -------------- | ---- | ------- | -------------- |
| es3_write_ds_0 | 2    | 2       | enable         |
|                | 4    | 4       | enable         |
|                | 6    | 6       | enable         |
| es3_write_ds_1 | 1    | 1       | enable         |
|                | 3    | 3       | enable         |
|                | 5    | 5       | enable         |

------

- tb_address

| 数据库（物理） | id   | user_id | address_name    |
| -------------- | ---- | ------- | --------------- |
| es3_write_ds_0 | 1    | 2       | user_2_address_ |
|                | 2    | 4       | user_4_address_ |
|                | 3    | 6       | user_6_address_ |
| es3_write_ds_1 | 4    | 1       | user_1_address_ |
|                | 5    | 3       | user_3_address_ |
|                | 6    | 5       | user_5_address_ |

------

- tb_order

| 数据库（物理） | 数据表（物理） | id   | user_id | address_id | order_status | create_date_time    |
| -------------- | -------------- | ---- | ------- | ---------- | ------------ | ------------------- |
| es3_write_ds_0 | tb_order_0     | 2    | 2       | 2          | 5            | 2022-11-02 11:11:11 |
|                |                | 4    | 4       | 4          | 5            | 2022-11-04 11:11:11 |
|                |                | 6    | 6       | 6          | 1            | 2022-11-06 11:11:11 |
|                | tb_order_1     | 1    | 2       | 2          | 4            | 2022-11-01 11:11:11 |
|                |                | 3    | 4       | 4          | 1            | 2022-11-03 11:11:11 |
|                |                | 5    | 6       | 6          | 1            | 2022-11-05 11:11:11 |
| es3_write_ds_1 | tb_order_0     | 8    | 1       | 1          | 3            | 2022-11-02 11:11:11 |
|                |                | 10   | 3       | 3          | 1            | 2022-11-04 11:11:11 |
|                |                | 12   | 5       | 5          | 1            | 2022-11-06 11:11:11 |
|                | tb_order_1     | 7    | 1       | 1          | 2            | 2022-11-01 11:11:11 |
|                |                | 9    | 3       | 3          | 1            | 2022-11-03 11:11:11 |
|                |                | 11   | 5       | 5          | 1            | 2022-11-05 11:11:11 |

------

- tb_order_item

| 数据库（物理） | 数据表（物理）  | id   | user_id | order_id | order_item_status |
| -------------- | --------------- | ---- | ------- | -------- | ----------------- |
| es3_write_ds_0 | tb_order_item_0 | 1   | 2          | 2       | PAY          |
| | | 2 | 2 | 2 | PAY |
|                |                | 3    | 4          | 4       | PAY          |
| | | 4 | 4 | 4 | PAY |
|                |                | 5    | 6          | 6       | PAY          |
| | | 6 | 6 | 6 | PAY |
|                | tb_order_item_1 | 7    | 2          | 1       | PAY          |
| | | 8 | 2 | 1 | PAY |
|                |                | 9    | 4          | 3       | PAY          |
| | | 10 | 4 | 3 | PAY |
|                |                | 11   | 6          | 5       | PAY          |
| | | 12 | 6 | 5 | PAY |
| es3_write_ds_1 | tb_order_item_0 | 13   | 1          | 8       | PAY          |
| | | 14 | 1 | 8 | PAY |
|                |                | 15  | 3          | 10      | PAY          |
| | | 16 | 3 | 10 | PAY |
|                |                | 17   | 5          | 12      | PAY          |
| | | 18 | 5 | 12 | PAY |
|                | tb_order_item_1 | 19   | 1          | 7       | PAY          |
| | | 20 | 1 | 7 | PAY |
|                |                | 21   | 3          | 9       | PAY          |
| | | 22 | 3 | 9 | PAY |
|                |                | 23  | 5          | 11     | PAY          |
| | | 24 | 5 | 11 | PAY |

- tb_statistics_order 统计订单表

| 数据库（物理） | 数据表（物理）                | id   | store_id | user_id | order_id | pay_date_time       |
| -------------- | ----------------------------- | ---- | -------- | ------- | -------- | ------------------- |
| es3_write_ds_0 | tb_statistics_order_0_2022_01 | 1    | 1        | 4       | 3        | 2022-01-03 22:22:22 |
|                |                               | 2    | 2        | 6       | 6        | 2022-01-06 22:22:22 |
|                | tb_statistics_order_0_2022_02 |      |          |         |          |                     |
|                | tb_statistics_order_0_2022_03 |      |          |         |          |                     |
|                |                               |      |          |         |          |                     |
|                | tb_statistics_order_1_2022_01 |      |          |         |          |                     |
|                | tb_statistics_order_1_2022_02 | 3    | 3        | 2       | 1        | 2022-02-01 22:22:22 |
|                |                               | 4    | 1        | 4       | 4        | 2022-02-04 22:22:22 |
|                | tb_statistics_order_1_2022_03 |      |          |         |          |                     |
|                |                               |      |          |         |          |                     |
|                | tb_statistics_order_2_2022_01 |      |          |         |          |                     |
|                | tb_statistics_order_2_2022_02 |      |          |         |          |                     |
|                | tb_statistics_order_2_2022_03 | 5    | 2        | 2       | 2        | 2022-03-02 22:22:22 |
|                |                               | 6    | 3        | 6       | 5        | 2022-03-05 22:22:22 |
|                |                               |      |          |         |          |                     |
| es3_write_ds_1 | tb_statistics_order_0_2022_01 | 7    | 1        | 3       | 9        | 2022-01-09 22:22:22 |
|                |                               | 8    | 2        | 5       | 12       | 2022-01-12 22:22:22 |
|                | tb_statistics_order_0_2022_02 |      |          |         |          |                     |
|                | tb_statistics_order_0_2022_03 |      |          |         |          |                     |
|                |                               |      |          |         |          |                     |
|                | tb_statistics_order_0_2022_01 |      |          |         |          |                     |
|                | tb_statistics_order_1_2022_02 | 9    | 3        | 1       | 7        | 2022-02-07 22:22:22 |
|                |                               | 10   | 1        | 3       | 10       | 2022-02-10 22:22:22 |
|                | tb_statistics_order_0_2022_03 |      |          |         |          |                     |
|                |                               |      |          |         |          |                     |
|                | tb_statistics_order_2_2022_01 |      |          |         |          |                     |
|                | tb_statistics_order_2_2022_02 |      |          |         |          |                     |
|                | tb_statistics_order_2_2022_03 | 11   | 2        | 1       | 8        | 2022-03-08 22:22:22 |
|                |                               | 12   | 3        | 5       | 11       | 2022-03-11 22:22:22 |




------

### 物理结构

数据库物理结构如下，不体现两个读库（与主库一致）

| 数据库         | 数据表                | 分库 | 分表 | 分表规则                      |
| -------------- | --------------------- | ---- | ---- | ----------------------------- |
| es3_write_ds_0 | tb_user               | -    | -    |                               |
|                | tb_account            | 是   | -    |                               |
|                | tb_address            | 是   | -    |                               |
|                | tb_order_0            | 是   | 是   | id%2                          |
|                | tb_order_1            | 是   | 是   | id%2                          |
|                | tb_order_item_0       | 是   | 是   | 自定义：order_id%2            |
|                | tb_order_item_1       | 是   | 是   | 自定义：order_id%2            |
|                | tb_statistics_order_0 | 是   | 是   | 自定义：order_id%2_yyyy_MM_dd |
|                | tb_statistics_order_1 | 是   | 是   | 自定义：order_id%2_yyyy_MM_dd |
| es3_write_ds_1 | tb_user               | -    | -    |                               |
|                | tb_account            | 是   | -    |                               |
|                | tb_address            | 是   | -    |                               |
|                | tb_order_0            | 是   | 是   | id%2                          |
|                | tb_order_1            | 是   | 是   | id%2                          |
|                | tb_order_item_0       | 是   | 是   | 自定义：order_id%2            |
|                | tb_order_item_1       | 是   | 是   | 自定义：order_id%2            |
|                | tb_statistics_order_0 | 是   | 是   | 自定义：order_id%2_yyyy_MM_dd |
|                | tb_statistics_order_1 | 是   | 是   | 自定义：order_id%2_yyyy_MM_dd |

### 分库、分表规则

1. 首先根据 user_id 除2取余分配数据库
2. tb_order、tb_order_item 分库后，根据 order_id 除2取余分表，tb_order_item为自定义分表策略
3. tb_statistics_order 多字段分表，根据 order_id 除2取余分表_yyyy_MM_dd



## 场景模拟调用

- 跨库、跨表查询用户和订单信息 /open/order/user/1
- 保存（批量）用户订单等信息 /open/order/save
- 跨库、跨表查询订单列表 /open/order/list
- 跨表查询订单列表 /open/order/list?userId=1
- 查询订单列表 /open/order/list?userId=1&orderId=1
- 分片表tb_order为主表，inner join关联查询用户表  /open/order/list-user?userId=1&orderId=1
- 跨库、跨表分页查询订单数据 /open/order/page
- 跨库、跨表统计订单数据 /open/order/statistics
- 跨库统计订单数据 /open/order/statistics?storeId=1&orderId=2
- 跨表统计订单数据 /open/order/statistics?userId=2
- 单分片表查询 /open/order/statistics?storeId=1&orderId=2&userId=1
- 跨库、跨表订单统计 /open/order/statistics
- 



## Q&A



Q：分库、分表场景中事务回滚问题

A：

------

Q：在where条件中没有分表语句时，ShardingSphere是如何做的

A：首先在分库场景下，每个库都会执行相同操作也就会汇总到最终的分表场景；分表时会 UNION ALL **所有** 分片表，也就是有多个分片表就需要查询多少个，因此也杜绝这种查询业务！！！

------

Q：如上面tb_order数据，进行分页 size=2 并排序 order by order_status,create_date_time desc  查询，是否会丢失ID为1、7、8的数据？

A: 分析日志为四个物理表进行了分页 limit 0,2 查询，第一页查询排序后拿到所有信息正常分页获取，第二页查询时分页为 0,4查询，获取到之前所有分页的数据，程序进行拼装分页，所以不会丢失查询第一页未使用的数据场景。但会引入一个问题，越往后分页在程序里拼接的数据会越来越多？这个场景其实并不正确，因为没有分表条件无法定位到具体表信息，从而无法进行单表的分页操作。

------

Q：大量数据场景中，（如上一个问题）页码越往后分页在程序里拼接的数据会越来越多？

A：如上，这个场景其实并不正确，因为没有分表条件无法定位到具体表信息，从而无法进行单表的分页操作。

------

Q：分表条件下 where in 查询分库字段是否会分表规则

A：会查询到具体表

------

Q：能否更新分表规则字段？数据是否会自动迁移

A：不能更新分表字段，提示 Can not update sharding value for table 错误。解决方式如下，方法一： update 语句 where 条件增加字段值并且和原值一致，方法二：update 语句删除修改分表字段。JPA通过修改注解属性`@Column(name = "xxx", updatable = false)`解决。

------

Q：

A：



## TODO

- [x] 分页查询时，order by 非分表条件、实际会查询所有分表的前（page*size）行记录，但越往后数据越多？？？
- [x] 更换查询维度，原表先删除，新表再新增？
- [x] 跨表wherein是否能直接到表
- [ ] 多字段跨表，where in 和时间范围综合影响
