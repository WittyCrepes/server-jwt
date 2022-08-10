package com.platform.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.platform.exception.CustomException;
import com.platform.utils.AjaxResponse;
import com.platform.utils.CustomExceptionType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lpl
 * @create 2022/7/19 9:01
 * @description 自定义登陆失败的结果处理
 */
@Component
public class MyAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    //在application配置文件中配置登陆的类型是JSON数据响应还是做页面响应
    @Value("${spring.security.loginType}")
    private String loginType;

    private  static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception)
            throws IOException, ServletException {

        if (loginType.equalsIgnoreCase("JSON")) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(
                    objectMapper.writeValueAsString(
                            AjaxResponse.error(
                                    new CustomException(
                                            CustomExceptionType.USER_INPUT_ERROR,
                                            "用户名或密码存在错误，请检查后再次登录"))));
        } else {
            response.setContentType("text/html;charset=UTF-8");
            super.onAuthenticationFailure(request, response, exception);
        }

    }
}