CREATE TABLE tb_account
(
    `id`             bigint NOT NULL,
    `user_id`        bigint NULL,
    `account_status` varchar(255) NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE tb_address
(
    `id`           bigint NOT NULL,
    `user_id`      bigint NULL,
    `address_name` varchar(255) NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE tb_order_0
(
    `id`           bigint NOT NULL,
    `user_id`      bigint NULL,
    `address_id`   bigint NULL,
    `order_status` varchar(255) NULL,
    `create_date_time` DATETIME NULL,
    PRIMARY KEY (`id`)
);


CREATE TABLE tb_order_1
(
    `id`           bigint NOT NULL,
    `user_id`      bigint NULL,
    `address_id`   bigint NULL,
    `order_status` varchar(255) NULL,
    `create_date_time` DATETIME NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE tb_order_item_0
(
    `id`                bigint NOT NULL,
    `user_id`           bigint NULL,
    `order_id`          bigint NULL,
    `order_item_status` varchar(255) NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE tb_order_item_1
(
    `id`                bigint NOT NULL,
    `user_id`           bigint NULL,
    `order_id`          bigint NULL,
    `order_item_status` varchar(255) NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE tb_statistics_order_0
(
    `id`            bigint NOT NULL,
    `store_id`      bigint NULL,
    `user_id`       bigint NULL,
    `order_id`      bigint NULL,
    `pay_date_time` DATETIME NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE tb_statistics_order_1
(
    `id`            bigint NOT NULL,
    `store_id`      bigint NULL,
    `user_id`       bigint NULL,
    `order_id`      bigint NULL,
    `pay_date_time` DATETIME NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE tb_user
(
    `id`       bigint NOT NULL,
    `username` varchar(255) NULL,
    `pwd`      varchar(255) NULL,
    PRIMARY KEY (`id`)
);

insert into tb_account (`id`, `user_id`, `account_status`)
values (4, 1, 'enable'),
       (5, 3, 'enable'),
       (6, 5, 'enable');

insert into tb_address (`id`, `user_id`, `address_name`)
values (4, 1, 'user_1_address_'),
       (5, 3, 'user_3_address_'),
       (6, 5, 'user_5_address_');

insert into tb_order_0 (`id`, `user_id`, `address_id`, `order_status`, `create_date_time`)
values (8, 1, 1, 'PAY', '2022-11-02 11:11:11'),
       (10, 3, 3, 'PAY', '2022-11-04 11:11:11'),
       (12, 5, 5, 'PAY', '2022-11-06 11:11:11');

insert into tb_order_1 (`id`, `user_id`, `address_id`, `order_status`, `create_date_time`)
values (7, 1, 1, 'PAY', '2022-11-01 11:11:11'),
       (9, 3, 3, 'PAY', '2022-11-03 11:11:11'),
       (11, 5, 5, 'PAY', '2022-11-05 11:11:11');

insert into tb_order_item_0 (`id`, `user_id`, `order_id`, `order_item_status`)
values (13, 1, 8, 'PAY'),
       (14, 1, 8, 'PAY'),
       (15, 3, 10, 'PAY'),
       (16, 3, 10, 'PAY'),
       (17, 5, 12, 'PAY'),
       (18, 5, 12, 'PAY');

insert into tb_order_item_1 (`id`, `user_id`, `order_id`, `order_item_status`)
values (19, 1, 7, 'PAY'),
       (20, 1, 7, 'PAY'),
       (21, 3, 9, 'PAY'),
       (22, 3, 9, 'PAY'),
       (23, 5, 11, 'PAY'),
       (24, 5, 11, 'PAY');

insert into tb_user (`id`, `username`, `pwd`)
values (1, 'user_1', 'user_1'),
       (2, 'user_2', 'user_2'),
       (3, 'user_3', 'user_3'),
       (4, 'user_4', 'user_4'),
       (5, 'user_5', 'user_5'),
       (6, 'user_6', 'user_6');

insert into tb_statistics_order_0 (`id`, `store_id`, `user_id`, `order_id`, `pay_date_time`)
values (7, 1, 1, 7, '2022-11-07 22:22:22'),
       (8, 2, 3, 9, '2022-11-09 22:22:22'),
       (9, 3, 5, 11, '2022-11-11 22:22:22');

insert into tb_statistics_order_1 (`id`, `store_id`, `user_id`, `order_id`, `pay_date_time`)
values (10, 1, 1, 9, '2022-11-09 22:22:22'),
       (11, 2, 3, 10, '2022-11-10 22:22:22'),
       (12, 3, 5, 12, '2022-11-12 22:22:22');


