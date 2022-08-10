package com.platform.serverjwt;

import com.platform.controller.MenuController;
import com.platform.dao.*;
import com.platform.pojo.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import java.util.Date;

@SpringBootTest
class ServerJwtApplicationTests {

    @Resource
    PasswordEncoder passwordEncoder;

    @Resource
    UserInfoMapper userInfoMapper;

    @Resource
    UserGroupMapper userGroupMapper;

    @Resource
    GroupRoleMapper groupRoleMapper;

    @Resource
    ControlRoleMapper controlRoleMapper;

    @Resource
    MenuMapper menuMapper;


    //打印加密加盐后的密码
    @Test
    public void contextLoads() {
        controlRoleMapper.insert(new ControlRole(1L,"管理员","admin","相关权限全放行",null));
    }

    //初始化管理员账户与主要权限管理接口
    @Test
    public void init(){
        //创建管理员用户并关联用户组
        userInfoMapper.insert(new UserInfo(1L,"admin",
                passwordEncoder.encode("123456"),
                1L,new Date(),true,null,null));
        userGroupMapper.insert(new UserGroup(1L,"管理员","管理员用户组",null));
        //创建管理员角色并关联用户组
        controlRoleMapper.insert(new ControlRole(1L,"管理员","admin","相关权限全放行",null));
        groupRoleMapper.insert(new GroupRole(1L,1L,1L));

        //主要权限接口
        menuMapper.insert(new Menu(1L,"用户页面","/user","页面",null,null));
        menuMapper.insert(new Menu(2L,"用户组页面","/user_group","页面",null,null));
        menuMapper.insert(new Menu(3L,"角色页面","/role","页面",null,null));
        menuMapper.insert(new Menu(4L,"接口配置页面","/api","页面",null,null));
        menuMapper.insert(new Menu(5L,"关联数据添加权限","/association","接口",null,null));
    }


}
