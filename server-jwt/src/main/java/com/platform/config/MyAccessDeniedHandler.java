package com.platform.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.platform.exception.CustomException;
import com.platform.utils.AjaxResponse;
import com.platform.utils.CustomExceptionType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lpl
 * @create 2022/7/19 16:00
 * @description 已登录，但是无权访问资源
 */
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {

    private  static ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void handle(HttpServletRequest httpServletRequest,
                       HttpServletResponse httpServletResponse,
                       AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().write(
                objectMapper.writeValueAsString(
                        AjaxResponse.error(
                                new CustomException(
                                        CustomExceptionType.USER_INPUT_ERROR,
                                        "没有访问权限！"))));
    }
}
