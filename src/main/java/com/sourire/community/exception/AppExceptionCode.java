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
public enum AppExceptionCode {
    /**
     * 问题找不到异常信息
     */
    QUESTION_NOT_FOUND("你找的问题不存在，换个问题试试吧~~~"),
    /**
     * 页面找不到异常信息
     */
    PAGE_NOT_FOUND("你访问页面不见了，换个页面看看吧~~~");

    private String message;
}
