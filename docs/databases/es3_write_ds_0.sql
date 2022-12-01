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

CREATE TABLE tb_user
(
    `id`       bigint NOT NULL,
    `username` varchar(255) NULL,
    `pwd`      varchar(255) NULL,
    PRIMARY KEY (`id`)
);

insert into tb_account (`id`, `user_id`, `account_status`)
values (1, 2, 'enable'),
       (2, 4, 'enable'),
       (3, 6, 'enable');

insert into tb_address (`id`, `user_id`, `address_name`)
values (1, 2, 'user_2_address_'),
       (2, 4, 'user_4_address_'),
       (3, 6, 'user_6_address_');

insert into tb_order_0 (`id`, `user_id`, `address_id`, `order_status`, `create_date_time`)
values (2, 2, 2, 'PAY', '2022-11-02 11:11:11'),
       (4, 4, 4, 'PAY', '2022-11-04 11:11:11'),
       (6, 6, 6, 'PAY', '2022-11-06 11:11:11');

insert into tb_order_1 (`id`, `user_id`, `address_id`, `order_status`, `create_date_time`)
values (1, 2, 2, 'PAY', '2022-11-01 11:11:11'),
       (3, 4, 4, 'PAY', '2022-11-03 11:11:11'),
       (5, 6, 6, 'PAY', '2022-11-05 11:11:11');

insert into tb_order_item_0 (`id`, `user_id`, `order_id`, `order_item_status`)
values (1, 2, 2, 'PAY'),
       (2, 2, 2, 'PAY'),
       (3, 4, 4, 'PAY'),
       (4, 4, 4, 'PAY'),
       (5, 6, 6, 'PAY'),
       (6, 6, 6, 'PAY');

insert into tb_order_item_1 (`id`, `user_id`, `order_id`, `order_item_status`)
values (7, 2, 1, 'PAY'),
       (8, 2, 1, 'PAY'),
       (9, 4, 3, 'PAY'),
       (10, 4, 3, 'PAY'),
       (11, 6, 5, 'PAY'),
       (12, 6, 5, 'PAY');

insert into tb_user (`id`, `username`, `pwd`)
values (1, 'user_1', 'user_1'),
       (2, 'user_2', 'user_2'),
       (3, 'user_3', 'user_3'),
       (4, 'user_4', 'user_4'),
       (5, 'user_5', 'user_5'),
       (6, 'user_6', 'user_6');

