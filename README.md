## 第六周作业
6.（必做）基于电商交易场景（用户、商品、订单），设计一套简单的表结构，提交 DDL 的 SQL 文件到 Github（后面 2 周的作业依然要是用到这个表结构）。
```sql
create table user
(
    id          int primary key auto_increment comment '主键',
    name        varchar(20) not null                             default '' comment '名字',
    user_name   varchar(20) not null                             default '' comment '用户名',
    phone       varchar(11) not null                             default '' comment '手机号',
    pwd         varchar(50) not null                             default '' comment '密码',
    deleted     int         not null                             default 0 comment '删除标记',
    insert_time datetime    not null                             default current_timestamp comment '新增时间',
    update_time datetime    not null on update current_timestamp default current_timestamp comment '修改时间'
) COMMENT ='用户表'
    ENGINE = InnoDB;
create table address
(
    id          int primary key auto_increment comment '主键',
    user_id     int         not null                             default 0 comment '用户id',
    region_code varchar(20) not null                             default '' comment '地区编码',
    address     varchar(50) not null                             default '' comment '详细地址',
    phone       varchar(20) not null                             default '' comment '收货人手机号',
    deleted     int         not null                             default 0 comment '删除标记',
    insert_time datetime    not null                             default current_timestamp comment '新增时间',
    update_time datetime    not null on update current_timestamp default current_timestamp comment '修改时间'
) COMMENT ='地址表'
    ENGINE = InnoDB;

create table spu
(
    id          int primary key auto_increment comment '主键',
    code        varchar(20)  not null                             default '' comment 'spu_code',
    name        varchar(50)  not null                             default '' comment '名字',
    type        int          not null                             default 0 comment '类型',
    intro_info  varchar(200) not null                             default '' comment '介绍信息',
    logo        varchar(128) not null not null                    default '' comment 'logo',
    deleted     int          not null                             default 0 comment '删除标记',
    insert_time datetime     not null                             default current_timestamp comment '新增时间',
    update_time datetime     not null on update current_timestamp default current_timestamp comment '修改时间'
) COMMENT ='spu'
    ENGINE = InnoDB;

create table sku
(
    id          int primary key auto_increment comment '主键',
    code        varchar(20)  not null                             default '' comment 'sku_code',
    name        varchar(50)  not null                             default '' comment '名字',
    spu_type    int          not null                             default 0 comment 'spu_type',
    type        int          not null                             default '' comment '类型',
    stock_count int          not null                             default 0 comment '库存',
    intro_info  varchar(200) not null                             default '' comment '介绍信息',
    logo        varchar(128) not null                             default '' comment 'logo',
    status      int          not null                             default 0 comment '状态',
    deleted     int          not null                             default 0 comment '删除标记',
    insert_time datetime     not null                             default current_timestamp comment '新增时间',
    update_time datetime     not null on update current_timestamp default current_timestamp comment '修改时间'
) COMMENT ='sku'
    ENGINE = InnoDB;
create table `order`
(
    id          int primary key auto_increment comment '主键',
    code        varchar(20) not null                             default '' comment 'code',
    status      int         not null                             default 0 comment '状态',
    user_id     int         not null                             default 0 comment '用户id',
    pay_time    datetime    null comment '支付时间',
    region_code varchar(20) not null                             default '' comment '地区编码',
    address     varchar(50) not null                             default '' comment '邮寄地址',
    deleted     int         not null                             default 0 comment '删除标记',
    insert_time datetime    not null                             default current_timestamp comment '新增时间',
    update_time datetime    not null on update current_timestamp default current_timestamp comment '修改时间'
) comment 'order' engine = InnoDB;

create table order_item
(
    id          int primary key auto_increment comment '主键',
    order_code  varchar(20) not null                             default '' comment '订单编号',
    spu_code    varchar(20) not null                             default '' comment 'spu_code',
    sku_code    varchar(20) not null                             default '' comment 'sku_code',
    sku_name    varchar(50) not null                             default '' comment 'sku_name',
    deleted     int         not null                             default 0 comment '删除标记',
    insert_time datetime    not null                             default current_timestamp comment '新增时间',
    update_time datetime    not null on update current_timestamp default current_timestamp comment '修改时间'
) comment 'order_item' engine InnoDB;

```
