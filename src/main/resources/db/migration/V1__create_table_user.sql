create table user
(
    id         bigint auto_increment
        primary key,
    account_id varchar(100) null,
    name       varchar(50)  null,
    token      varchar(36)  null,
    gmt_create date         null,
    gmt_modify date         null,
    avatar_url varchar(100) null
);

