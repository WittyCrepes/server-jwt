package com.platform.service.impl;

import com.platform.dao.*;
import com.platform.pojo.ControlRole;
import com.platform.pojo.Menu;
import com.platform.pojo.UserInfo;
import com.platform.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author lpl
 * @create 2022/7/25 17:04
 * @description Spring security调用方法验证账户权限
 */
@Service
public class MyUserServiceImpl implements MyUserDetailsService {

    private UserInfoMapper userInfoMapper;

    private MenuMapper menuMapper;

    private ControlRoleMapper controlRoleMapper;

    private GroupRoleMapper groupRoleMapper;

    private RoleMenuMapper roleMenuMapper;

    @Autowired
    public void setUserInfoMapper(UserInfoMapper userInfoMapper){
        this.userInfoMapper = userInfoMapper;
    }
    @Autowired
    public void setMenuMapper(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }
    @Autowired
    public void setRoleMapper(ControlRoleMapper controlRoleMapper){
        this.controlRoleMapper = controlRoleMapper;
    }
    @Autowired
    public void setGroupRoleMapper(GroupRoleMapper groupRoleMapper){
        this.groupRoleMapper = groupRoleMapper;
    }
    @Autowired
    public void setRoleMenuMapper(RoleMenuMapper roleMenuMapper){
        this.roleMenuMapper = roleMenuMapper;
    }

    //根据userID查询用户信息
    @Override
    public UserInfo findByUserName(String username) {
        UserInfo userInfo = userInfoMapper.selectUserByUsername(username);
        return userInfo;
    }

    //根据userID查询用户角色
    @Override
    public List<String> findRoleByUserName(String username) {
        Set<String> set =  new HashSet<>();
        /*UserInfo userInfo = userInfoMapper.selectUserByUsername(username);
        Long groupId = userInfo.getGroupId();*/
        Long groupId = userInfoMapper.selectGroupIdByUsername(username);
        List<Long> roleIds = groupRoleMapper.selectAllRoleIdByGroupId(groupId);
        for (Long roleId : roleIds) {
            ControlRole controlRole = controlRoleMapper.selectById(roleId);
            set.add(controlRole.getRoleCode());
        }
        return new ArrayList<>(set);
    }

    //根据用户角色查询用户权限
    @Override
    public List<String> findAuthorityByRoleCodes(List<String> roleCodes) {
        Set<String> set = new HashSet<>();
        for (String roleCode : roleCodes) {
            Long roleId = controlRoleMapper.selectRoleIdByRoleCode(roleCode);
            List<Long> menuIds = roleMenuMapper.selectMenuIdByRoleId(roleId);
            for (Long menuId : menuIds) {
                Menu menu = menuMapper.selectById(menuId);
                if(menu!=null){
                    set.add(menu.getUrl());
                }

            }
        }
        return new ArrayList<>(set);
    }

}
