# example-spring-sharding-sphere

[TOC]

## 业务场景模拟：

共有两个数据库（es3_write_ds_0、es3_write_ds_1），并且数据库都实现两个读库，总共存在6个数据库。存在5张逻辑表（如下）：

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
| -------------- | -------------- | ---- | ------- | ---------- | ------------ |---------------------|
| es3_write_ds_0 | tb_order_0     | 2    | 2       | 2          | PAY          | 2022-11-02 11:11:11 |
|                |                | 4    | 4       | 4          | PAY          | 2022-11-04 11:11:11 |
|                |                | 6    | 6       | 6          | PAY          | 2022-11-06 11:11:11 |
|                | tb_order_1     | 1    | 2       | 2          | PAY          | 2022-11-01 11:11:11 |
|                |                | 3    | 4       | 4          | PAY          | 2022-11-03 11:11:11 |
|                |                | 5    | 6       | 6          | PAY          | 2022-11-05 11:11:11 |
| es3_write_ds_1 | tb_order_0     | 8    | 1       | 1          | PAY          | 2022-11-02 11:11:11 |
|                |                | 10   | 3       | 3          | PAY          | 2022-11-04 11:11:11 |
|                |                | 12   | 5       | 5          | PAY          | 2022-11-06 11:11:11 |
|                | tb_order_1     | 7    | 1       | 1          | PAY          | 2022-11-01 11:11:11 |
|                |                | 9    | 3       | 3          | PAY          | 2022-11-03 11:11:11 |
|                |                | 11   | 5       | 5          | PAY          | 2022-11-05 11:11:11 |

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

| 数据库（物理） | 数据表（物理）        | id   | store_id | user_id | order_id | pay_date_time       |
| -------------- | --------------------- | ---- | -------- | ------- | -------- | ------------------- |
| es3_write_ds_0 | tb_statistics_order_0 | 1    | 1        | 2       | 1        | 2022-11-01 22:22:22 |
|                |                       | 2    | 2        | 4       | 3        | 2022-11-03 22:22:22 |
|                |                       | 3    | 3        | 6       | 5        | 2022-11-05 22:22:22 |
|                | tb_statistics_order_1 | 4    | 1        | 2       | 2        | 2022-11-02 22:22:22 |
|                |                       | 5    | 2        | 4       | 4        | 2022-11-04 22:22:22 |
|                |                       | 6    | 3        | 6       | 6        | 2022-11-06 22:22:22 |
| es3_write_ds_1 | tb_statistics_order_0 | 7    | 1        | 1       | 7        | 2022-11-07 22:22:22 |
|                |                       | 8    | 2        | 3       | 9        | 2022-11-09 22:22:22 |
|                |                       | 9    | 3        | 5       | 11       | 2022-11-11 22:22:22 |
|                | tb_statistics_order_1 | 10   | 1        | 1       | 8        | 2022-11-08 22:22:22 |
|                |                       | 11   | 2        | 3       | 10       | 2022-11-10 22:22:22 |
|                |                       | 12   | 3        | 5       | 12       | 2022-11-12 22:22:22 |
|                |                       |      |          |         |          |                     |
|                |                       |      |          |         |          |                     |






------

### 物理结构

数据库物理结构如下，不体现两个读库（与主库一致）

| 数据库         | 数据表          | 分库 | 分表 |
| -------------- | --------------- | ---- | ---- |
| es3_write_ds_0 | tb_user         | -    | -    |
|                | tb_account      | 是   | -    |
|                | tb_address      | 是   | -    |
|                | tb_order_0      | 是   | 是   |
|                | tb_order_item_0 | 是   | 是   |
| es3_write_ds_1 | tb_user         | -    | -    |
|                | tb_account      | 是   | -    |
|                | tb_address      | 是   | -    |
|                | tb_order_1      | 是   | 是   |
|                | tb_order_item_1 | 是   | 是   |

### 分库、分表规则

1. 优先跟进 userid 取余分配数据库，
2. tb_order、tb_order_item 分库后，跟进 orderid 分表

