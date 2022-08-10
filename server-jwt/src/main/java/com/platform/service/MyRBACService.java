package com.platform.service;

import com.platform.dao.UserInfoMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author lpl
 * @create 2022/7/20 15:47
 * @description 动态鉴权规则
 */
@Component("rbacService")
public class MyRBACService {

    @Resource
    UserInfoMapper userInfoMapper;
    /**
     * 判断某用户是否具有该request资源的访问权限
     */
    public boolean hasPermission(HttpServletRequest request, Authentication authentication){

       Object principal = authentication.getPrincipal();

        if(principal instanceof UserDetails){
            //获取当前登录用户的UserDetails
            UserDetails userDetails = ((UserDetails)principal);
            List<GrantedAuthority> authorityList = AuthorityUtils.commaSeparatedStringToAuthorityList(request.getRequestURI());

            //判断用户已授权访问的资源中，是否包含“本次请求的资源”
            //将当前请求的访问资源路径，只查询一级路径。如/association/group_role只取/association
            String[] split = request.getRequestURI().split("/");
            String url = split[1]==null?request.getRequestURI():"/"+split[1];
            SimpleGrantedAuthority simpleGrantedAuthority
                    = new SimpleGrantedAuthority(url);
            //判断用户已授权访问的资源中，是否包含“本次请求的资源”
            Long groupId = userInfoMapper.selectGroupIdByUsername(userDetails.getUsername());
            //管理员角色放行
            List<String> roles = userInfoMapper.selectRolesByUserId(groupId);
            for (String role : roles) {
                if(role.equalsIgnoreCase("admin")){
                    return true;
                }
            }
            return userDetails.getAuthorities().contains(simpleGrantedAuthority);

        }
        return false;
    }
}
