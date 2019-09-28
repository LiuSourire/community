create table comment
(
    id          int auto_increment comment '评论id'
        primary key,
    parentId    int           not null comment '父一级id',
    type        int           not null comment '评论类型',
    commentator int           not null comment '评论人id',
    gmt_create  datetime      null comment '评论时间',
    gmt_modify  datetime      null comment '修改评论时间',
    like_count  int default 0 null comment '点赞数',
    content     varchar(1024) not null comment '评论内容'
)
    comment '评论表';

