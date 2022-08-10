package com.platform.service;

import com.platform.pojo.UserInfo;

import java.util.List;

public interface MyUserDetailsService{

    //根据userID查询用户信息
    UserInfo findByUserName(String username);

    //根据userID查询用户角色
    List<String> findRoleByUserName(String username);

    //根据用户角色查询用户权限
    List<String> findAuthorityByRoleCodes(List<String> roleCodes);
}
