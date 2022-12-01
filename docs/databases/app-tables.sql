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

CREATE TABLE tb_order
(
    `id`           bigint NOT NULL,
    `user_id`      bigint NULL,
    `address_id`   bigint NULL,
    `order_status` varchar(255) NULL,
    `create_date_time` DATETIME NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE tb_order_item
(
    `id`                bigint NOT NULL,
    `user_id`           bigint NULL,
    `order_id`          bigint NULL,
    `order_item_status` varchar(255) NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE tb_statistics_order
(
    `id`                bigint NOT NULL,
    `store_id`          bigint NULL,
    `user_id`           bigint NULL,
    `order_id`          bigint NULL,
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


