-- auto-generated definition
create table os_order_product
(
    F_ID                    bigint auto_increment comment 'ID' primary key,
    F_ORDER_NO                  varchar(64)    not null comment 'Order number',
    F_PRODUCT_ID                 varchar(5)     not null comment 'Product ID',
    F_AMOUNT             varchar(128)    not null comment 'Product amount',
    F_UPDATE_DATE           varchar(8)    not null comment 'Record updated date',
    F_UPDATE_TIME           varchar(6)    not null comment 'Record updated time',
    F_CREATED_DATE          varchar(8)    not null comment 'Record created date',
    F_CREATED_TIME          varchar(6)    not null comment 'Record created time',
    constraint uniq_f_order_no
        unique (F_ORDER_NO, F_PRODUCT_ID)
)
    comment 'Product In Order Table' charset = utf8;