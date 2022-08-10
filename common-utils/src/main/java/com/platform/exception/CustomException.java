package com.platform.exception;

import com.platform.utils.CustomExceptionType;

/**
 * @author lpl
 * @create 2022/7/15 9:39
 * @description TODO
 */
public class CustomException extends RuntimeException{
        //异常错误编码
        private int code ;
        //异常信息
        private String message;

        private CustomException(){}

        public CustomException(CustomExceptionType exceptionTypeEnum,
                               String message) {
            this.code = exceptionTypeEnum.getCode();
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        @Override
        public String getMessage() {
            return message;
        }

}
