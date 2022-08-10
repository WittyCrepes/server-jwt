package com.platform.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.platform.exception.CustomException;
import com.platform.utils.AjaxResponse;
import com.platform.utils.CustomExceptionType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lpl
 * @create 2022/7/19 15:55
 * @description 未登录时返回系统资源处理
 */
@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private  static ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void commence(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().write(
                objectMapper.writeValueAsString(
                        AjaxResponse.error(
                                new CustomException(
                                        CustomExceptionType.USER_INPUT_ERROR,
                                        "禁止游客访问，请先登录！"))));
    }
}