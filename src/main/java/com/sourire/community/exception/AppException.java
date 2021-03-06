package com.sourire.community.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author sourire
 * @version 1.0
 * @date 2019/9/3 9:36
 */
@Getter
public class AppException extends RuntimeException {

    private Integer code;
    private String message;

    public AppException(AppExceptionCode appExceptionCode){
        this.code = appExceptionCode.getCode();
        this.message = appExceptionCode.getMessage();
    }
}
