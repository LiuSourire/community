package com.sourire.community.enums;

import lombok.Data;
import lombok.Getter;

/**
 * @author sourire
 * @version 1.0
 * @date 2019/9/10 9:05
 */

public enum CommentTypeEnum {
    /**
     * 回复问题
     */
    QUESTION (1),
    /**
     * 回复评论
     */
    COMMENT_FIRST(2),
    /**
     * 回复评论二级
     */
    COMMENT_SECOND(3)
    ;

    @Getter
    private Integer type;

    CommentTypeEnum(Integer type) {
        this.type = type;
    }

    public static boolean isExist(Integer type) {
        for (CommentTypeEnum typeEnum : CommentTypeEnum.values()) {
            if (typeEnum.getType().equals(type)){
                return true;
            }
        }
        return false;
    }

    public Integer getCommentType(CommentTypeEnum commentTypeEnum){
        return commentTypeEnum.getType();
    }
}
