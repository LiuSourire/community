package com.sourire.community.advice;

import com.sourire.community.exception.AppException;
import com.sourire.community.exception.AppExceptionCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * @author sourire
 * @version 1.0
 * @date 2019/9/3 9:35
 */
@ControllerAdvice
public class ApplicationControllerAdvice extends ResponseEntityExceptionHandler {


    @ExceptionHandler(Exception.class)
    ModelAndView handleControllerException(Throwable e, Model model) {
        String message;
        if(e instanceof AppException){
            message=e.getMessage();
        }else if (e instanceof NoHandlerFoundException){
            message=AppExceptionCode.PAGE_NOT_FOUND.getMessage();
        }else{
            message="服务太热啦，要不然稍等下再来试试~";
        }
        model.addAttribute("message",message);
        return new ModelAndView("error");
    }
}