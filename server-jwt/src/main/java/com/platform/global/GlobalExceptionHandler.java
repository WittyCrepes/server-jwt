package com.platform.global;

import com.platform.exception.CustomException;
import com.platform.utils.AjaxResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lpl
 * @create 2022/7/21 17:03
 * @description 统一异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public AjaxResponse customerException(CustomException e) {
        return AjaxResponse.error(e);
    }
}