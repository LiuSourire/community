create table question
(
    id            int(10) auto_increment
        primary key,
    title         varchar(200)          null,
    description   text                  null,
    gmt_create    datetime              null,
    gmt_modify    datetime              null,
    creator       bigint(10)            null,
    view_count    bigint(255) default 0 null,
    comment_count bigint(255) default 0 null,
    like_count    bigint(255) default 0 null,
    tag           varchar(255)          null
);

