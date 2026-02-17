-- os_merc_order definition
create table os_merc_order
(
    F_ID                    bigint auto_increment comment 'ID' primary key,
    F_ORDER_NO              varchar(64)    not null comment 'Order numbers',
    F_TABLE_NO              varchar(3)     not null comment 'Table numbers',
    F_MERC_ID               varchar(20)    not null comment 'Merchant ID',
    F_SHOP_ID               varchar(20)    null comment 'Shop numbers',
    F_PAY_TYPE              char(4)        not null comment 'Payment type: 10-PayPal online',
    F_ORDER_STATUS          char           not null comment 'Order status: 0-Pre created; 1-Created; 2-Finished; 3-Cancelled',
    F_REFUND_STATUS         char           not null comment 'Refund status: 0-No refund; 1-Partial refund; 2-Full refund',
    F_PAY_STATUS            char           not null comment 'Payment status: 0-No pay; 1-Paid; 2-Payment failed',
    F_COOKING_STATUS        char           not null comment 'Cooking status: 0-Pending; 1-Delivered; 2-Canceled',
    F_ORDER_AMOUNT          bigint         not null comment 'Order amount',
    F_RESIDUE_REFUND_AMOUNT bigint         null comment 'Refundable amount',
    F_ALREADY_REFUND_AMOUNT bigint         null comment 'Amount refunded',
    F_PRODUCT_DESC          varchar(128)   null comment 'Product details',
    F_UPDATE_DATE           varchar(8)    not null comment 'Record updated date',
    F_UPDATE_TIME           varchar(6)    not null comment 'Record updated time',
    F_CREATED_DATE          varchar(8)    not null comment 'Record created date',
    F_CREATED_TIME          varchar(6)    not null comment 'Record created time',
    F_ORDER_TYPE            char           null comment 'Order type: 1-payment order 2-refund order',
    F_EXT_PARA              text           null comment 'Extended field',
    F_RESERVED              varchar(30)    null comment 'Reserved field',
    F_RESERVED_1            varchar(30)    null comment 'Reserved field 2',
    F_RESERVED_2            varchar(30)     null comment 'Reserved field 3',
    constraint uniq_f_order_no
        unique (F_ORDER_NO)
)
    comment 'Merchant Order Table' charset = utf8;

-- create index idx_STATUS_TYPE_OUTNO
--     on os_merc_order (F_PAY_STATUS, F_OUTER_ORDER_NO, F_ORDER_TYPE);
--
-- create index idx_f_create_time
--     on os_merc_order (F_CREATED_TIME);
--
-- create index idx_f_merc_id_f_outer_order_no
--     on os_merc_order (F_MERC_ID, F_OUTER_ORDER_NO);
--
-- create index idx_f_outer_order_no
--     on os_merc_order (F_OUTER_ORDER_NO);

