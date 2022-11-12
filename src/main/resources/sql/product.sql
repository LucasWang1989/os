-- auto-generated definition
create table os_product_def
(
    F_ID                    bigint auto_increment   comment 'ID' primary key,
    F_NAME                  varchar(64)    not null comment 'Product name',
    F_PRICE                 bigint         not null comment 'Product price',
    F_DESC                  text           not null comment 'Product description',
    F_IMG_PATH              varchar(128)   null     comment 'Product image path',
    F_UPDATE_DATE           varchar(8)     not null comment 'Record updated date',
    F_UPDATE_TIME           varchar(6)     not null comment 'Record updated time',
    F_CREATED_DATE          varchar(8)     not null comment 'Record created date',
    F_CREATED_TIME          varchar(6)     not null comment 'Record created time',
    constraint uniq_product_def
        unique (F_NAME)
)
    comment 'Product Definition Table' charset = utf8;



insert into os_product_def(F_NAME,F_PRICE,F_DESC,F_IMG_PATH,F_UPDATE_DATE,F_UPDATE_TIME,F_CREATED_DATE,F_CREATED_TIME) values ('Pizza',1600,'100g each strong white and strong wholewheat flour. 1 tsp or 7g sachet easy-blend dried yeast, 125ml warm water. 200g chopped tomato. Juice drained handful cherry tomatoes , halved 1 large courgette , thinly sliced using a peeler 25g mozzarella , torn into pieces. 1 tsp capers in brine, drained 8 green olives , roughly chopped 1 garlic clove , finely chopped 1 tbsp olive oil 2 tbsp chopped parsley.','img/tof/up1.jpg','20221024','132900','20221024','132900');
insert into os_product_def(F_NAME,F_PRICE,F_DESC,F_IMG_PATH,F_UPDATE_DATE,F_UPDATE_TIME,F_CREATED_DATE,F_CREATED_TIME) values ('Hamburger',1200,'500g lean top-quality minced beef , freshly minced 1 small onion , grated 2 tsp Worcestershire sauce 4 large basil leaves 50g brie , 4 sundried tomatoes in oil, halved salad leaves, cornichons and sliced tomatoes.','img/tof/up2.jpg','20221024','132900','20221024','132900');
insert into os_product_def(F_NAME,F_PRICE,F_DESC,F_IMG_PATH,F_UPDATE_DATE,F_UPDATE_TIME,F_CREATED_DATE,F_CREATED_TIME) values ('Coffee',1500,'18g ground espresso , or 1 espresso pod 100ml milk.','img/tof/up3.jpg','20221024','132900','20221024','132900');
insert into os_product_def(F_NAME,F_PRICE,F_DESC,F_IMG_PATH,F_UPDATE_DATE,F_UPDATE_TIME,F_CREATED_DATE,F_CREATED_TIME) values ('Bread',2000,'250g plain white flour; 250g plain wholemeal flour; 100g porridge oats; 1 tsp bicarbonate of soda; 1 tsp salt; 25g butter pieces; 500ml buttermilk.','img/tof/up4.jpg','20221024','132900','20221024','132900');

