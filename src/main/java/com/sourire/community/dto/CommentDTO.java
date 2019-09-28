package com.sourire.community.dto;

import com.sourire.community.entity.User;
import lombok.Data;

import java.util.Date;

/**
 * @author sourire
 * @version 1.0
 * @date 2019/9/10 9:03
 */
@Data
public class CommentDTO {
    private Integer id;

    /**
     * 父一级id
     */
    private Integer parentId;

    /**
     * 评论类型
     */
    private Integer type;

    /**
     * 评论人id
     */
    private Integer commentator;

    /**
     * 评论时间
     */
    private Date gmtCreate;

    /**
     * 修改评论时间
     */
    private Date gmtModify;

    /**
     * 点赞数
     */
    private Integer likeCount;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 点赞数
     */
    private Long commentCount;

    /**
     * 发表评论用户对象
     */
    private User user;
}
