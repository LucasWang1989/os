-- OS channel order definition
create table os_channel_pay_order
(
    F_ID                        int(10) auto_increment comment 'ID' primary key,
    F_PAY_ORDER_NO              varchar(64)                  not null comment 'Inner payment order numbers',
    F_PAY_TYPE                  char(4)                      not null comment 'Payment type: 10-PayPal',
    F_CHANNEL_NO                varchar(20)                  null comment 'Channel numbers',
    F_CHANNEL_PAY_ORDER_NO      varchar(64)                  null comment 'Channel payment order number for payment',
    F_PAY_STATUS                char                         not null comment 'Payment status 0-No pay; 1-Paid; 2-Payment failed',
    F_PAY_AMOUNT                bigint                       not null comment 'Order amount',
    F_PAY_TIME                  varchar(20)                  not null comment 'Payment time',
    F_REMARK                    varchar(64)                  null comment 'Remark',
    F_UPDATE_TIME               varchar(32)                  not null comment 'Record updated time',
    F_CREATED_TIME              varchar(32)                  not null comment 'Record created time',
    F_CHANNEL_MERC_ID           varchar(128)                  null comment 'Channel merchant number',
    constraint uniq_f_id
        unique (F_ID)
)
    comment 'Channel Payment Order Table' charset = utf8;

create index idx_f_order_no
    on os_channel_pay_order (F_PAY_ORDER_NO);


-- Channel refund order table definition
create table os_channel_refund_order
(
    F_ID                        bigint auto_increment comment 'ID' primary key,
    F_ORI_PAY_ORDER_NO          varchar(64)                  null comment 'Inner original payment order numbers',
    F_REFUND_ORDER_NO           varchar(64)                  null comment 'Inner refund order numbers',
    F_CHANNEL_REFUND_ORDER_NO   varchar(64)                  null comment 'Channel refund order numbers',
    F_PAY_TYPE                  char(4)                      not null comment 'Payment type: 10-PayPal',
    F_CHANNEL_NO                varchar(20)                  null comment 'Channel number',
    F_REFUND_AMOUNT             bigint                       not null comment 'Refund amount',
    F_REFUND_TIME               varchar(20)                  not null comment 'Refund time',
    F_REMARK                    varchar(64)                  null comment 'Remark',
    F_REFUND_STATUS             char                         null comment 'Refund status: 0-no refunded;1-Refund success;2-Refund failure;3-Refunding',
    F_UPDATE_TIME               varchar(32)                  not null comment 'Record updated time',
    F_CREATED_TIME              varchar(32)                  not null comment 'Record created time',
    F_CHANNEL_MERC_ID           varchar(60)                  null comment 'Channel merchant ID',
    F_FAIL_REASONS              varchar(255)                 null comment 'Refund failure reasons',
    constraint uniq_fefund_order_no
        unique (F_REFUND_ORDER_NO)
)
    comment 'Channel Refund Order Table' charset = utf8;


create table os_channel_merc_info
(
    F_CHANNEL_NO        varchar(20)             not null comment 'Channel No',
    F_PAYMENT_TYPE      varchar(6)              not null comment 'payment type',
    F_CHANNEL_MERC_ID   varchar(60)             not null comment 'Merchant No from channel',
    F_SSD_KEY           varchar(3000)           null comment 'Key of sensitive data protection',
    F_MAC_KEY           varchar(3000)           null comment 'MacKey',
    F_CLEAR_TYPE        decimal(20, 5)          null comment 'Liquidation type 0-D0；1-T1',
    F_MERC_FEE          int(10)                 null comment 'Transaction fee',
    F_MERC_RATE         decimal(20, 5)          null comment 'Merchant rate',
    F_MERC_STATUS       char                    not null comment 'Merchant status 0-Inactive 1-Active',
    F_IMPORT_STATUS     char                    null comment 'Merchant import status 0-Not yet；1-Success',
    F_IMPORT_RESULT     varchar(100)            null comment 'Merchant import result',
    F_UPDATE_TIME       varchar(32)             not null comment 'Record updated time',
    F_CREATED_TIME      varchar(32)             not null comment 'Record created time',
    F_EXT_FLAG          char                    not null comment 'Acquire extension param flag：0-No 1-Yes',
    F_CHANNEL_ORDER_FIX varchar(30)             null comment 'Prefix of order No',
    primary key (F_CHANNEL_NO, F_PAYMENT_TYPE, F_CHANNEL_MERC_ID)
)
    comment 'Information of channel merchant' charset = utf8;

