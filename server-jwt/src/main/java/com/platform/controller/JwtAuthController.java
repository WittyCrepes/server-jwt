package com.platform.controller;

import com.platform.exception.CustomException;
import com.platform.service.JwtAuthService;
import com.platform.utils.AjaxResponse;
import com.platform.utils.CustomExceptionType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author lpl
 * @create 2022/7/20 16:05
 * @description TODO
 */
@RestController
public class JwtAuthController {

    @Resource
    private JwtAuthService jwtAuthService;

    @PostMapping(value = "/authentication")
    public AjaxResponse login(@RequestBody Map<String, String> map) {
        String username = map.get("username");
        String password = map.get("password");
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return AjaxResponse.error(
                    new CustomException(
                            CustomExceptionType.USER_INPUT_ERROR,"用户名密码不能为空"));
        }
        try{
            return AjaxResponse.success(jwtAuthService.login(username, password));
        }catch(CustomException e){
            return AjaxResponse.error(e);
        }
    }

    @GetMapping(value = "/refreshtoken")
    public AjaxResponse refresh(@RequestHeader("${jwt.header}") String token) {
        return AjaxResponse.success(jwtAuthService.refreshToken(token));
    }

}