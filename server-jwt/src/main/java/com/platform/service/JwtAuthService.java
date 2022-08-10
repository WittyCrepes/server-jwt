package com.platform.service;

import com.platform.exception.CustomException;
import com.platform.utils.CustomExceptionType;
import com.platform.utils.JwtTokenUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author lpl
 * @create 2022/7/20 16:07
 * @description TODO
 */
@Service
public class JwtAuthService {

    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private UserDetailsService userDetailsService;
    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Resource
    PasswordEncoder passwordEncoder;

    public String login(String username, String password) throws CustomException {

        try{
            //使用用户名密码进行登录验证
            UsernamePasswordAuthenticationToken upToken =
                    new UsernamePasswordAuthenticationToken( username, password);
            Authentication authentication = authenticationManager.authenticate(upToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }catch(AuthenticationException e){
            throw new CustomException(CustomExceptionType.USER_INPUT_ERROR,
                    "用户名或密码不正确");
        }

        //生成JWT
        UserDetails userDetails = userDetailsService.loadUserByUsername( username );
        return jwtTokenUtil.generateToken(userDetails);
    }

    public String refreshToken(String oldToken) {
        if (!jwtTokenUtil.isTokenExpired(oldToken)) {
            return jwtTokenUtil.refreshToken(oldToken);
        }
        return null;
    }
}
