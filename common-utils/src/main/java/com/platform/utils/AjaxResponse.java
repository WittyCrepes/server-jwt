package com.platform.utils;

import com.platform.exception.CustomException;
import lombok.Data;

/**
 * @author  lpl
 * @create 2022/7/15 9:41
 * @description TODO
 */
@Data
public class AjaxResponse<T> {

    private  boolean success;
    private  int code;
    private  String message;
    private  T data;

    private AjaxResponse() {

    }

    //请求出现异常时的响应数据封装
    public static AjaxResponse<?> error(CustomException e) {
        AjaxResponse<Object> resultBean = new AjaxResponse<>();
        resultBean.setSuccess(false);
        resultBean.setCode(e.getCode());
        resultBean.setData(null);
        if(e.getCode() == CustomExceptionType.USER_INPUT_ERROR.getCode()){
            resultBean.setMessage(e.getMessage());
        }else if(e.getCode() == CustomExceptionType.SYSTEM_ERROR.getCode()){
            resultBean.setMessage(e.getMessage() + ",请将该异常信息发送给管理员!");
        }else{
            resultBean.setMessage("系统出现未知异常，请联系管理员!");
        }
        //TODO 这里最好将异常信息持久化
        return resultBean;
    }

    //请求出现异常时的响应数据封装
    public static AjaxResponse<?> error(CustomExceptionType customExceptionType,
                                         String errorMessage) {
        AjaxResponse<Object> resultBean = new AjaxResponse<>();
        resultBean.setSuccess(false);
        resultBean.setCode(customExceptionType.getCode());
        resultBean.setMessage(errorMessage);
        resultBean.setData(null);
        return resultBean;
    }

    //请求处理成功时的数据响应
    public static AjaxResponse<?> success() {
        AjaxResponse<Object> resultBean = new AjaxResponse<>();
        resultBean.setSuccess(true);
        resultBean.setCode(200);
        resultBean.setMessage("success");
        resultBean.setData(null);
        return resultBean;
    }

    //请求处理成功，并响应结果数据
    public  static AjaxResponse<?> success(Object data) {
        AjaxResponse resultBean = AjaxResponse.success();
        resultBean.setData(data);
        return resultBean;
    }

}