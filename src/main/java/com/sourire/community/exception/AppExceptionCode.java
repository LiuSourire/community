package com.sourire.community.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author sourire
 * @version 1.0
 * @date 2019/9/3 10:02
 */
@AllArgsConstructor
@Getter
public enum AppExceptionCode implements IAppExceptionCode{
    /**
     * 问题找不到异常信息
     */
    QUESTION_NOT_FOUND(1001,"你找的问题不存在，换个问题试试吧~~~"),
    /**
     * 页面找不到异常信息
     */
    PAGE_NOT_FOUND(1002,"你访问页面不见了，换个页面看看吧~~~"),
    /**
     * 未登录
     */
    NO_LOGIN(1003,"当前操作需要登录，请登录后重试！"),
    /**
     * 目标参数未找到
     */
    TARGET_PARAM_NOT_FOUND(1004,"未选中任何问题或评论进行回复！"),
    /**
     * 系统错误
     */
    SYS_WRONG(1005,"服务器冒烟了，晚点再试试吧~~~"),
    /**
     * 评论不存在
     */
    COMMENT_NOT_FOUND(1006,"你回复的评论不存在，换个试试吧~~~"),
    /**
     * ;
     * 回复的内容为空
     */
    CONTENT_NOT_EMPTY(1007, "回复的内容不能为空");

    private Integer code;
    private String message;

    @Override
    public String message() {
        return message;
    }

    @Override
    public Integer code() {
        return code;
    }
}
