package com.sourire.community.dto;

import com.sourire.community.exception.AppException;
import com.sourire.community.exception.AppExceptionCode;
import lombok.Data;

/**
 * @author sourire
 * @version 1.0
 * @date 2019/9/10 22:02
 */
@Data
public class ResultDTO {

    private Integer code;
    private String message;

    public static ResultDTO errorOf(Integer code,String message){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return resultDTO;
    }

    public static ResultDTO errorOf(AppExceptionCode appExceptionCode){
        return errorOf(appExceptionCode.getCode(),appExceptionCode.getMessage());
    }

    public static ResultDTO errorOf(AppException appException){
        return errorOf(appException.getCode(), appException.getMessage());
    }

    public static ResultDTO okOf(){
        return errorOf(200,"请求成功");
    }

}
