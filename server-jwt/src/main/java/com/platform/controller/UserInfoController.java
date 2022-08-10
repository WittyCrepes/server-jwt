package com.platform.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.platform.exception.CustomException;
import com.platform.pojo.UserInfo;
import com.platform.service.UserInfoService;
import com.platform.utils.AjaxResponse;
import com.platform.utils.CustomExceptionType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author lpl
 * @create 2022/8/5 9:55
 * @description TODO
 */
@RestController
@RequestMapping("/user")
public class UserInfoController {

    @Resource
    UserInfoService userInfoService;
    @Resource
    PasswordEncoder passwordEncoder;

    @PostMapping
    public AjaxResponse<?> createUserInfo(@RequestBody UserInfo user){

        try {
            //加密
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return AjaxResponse.success(userInfoService.save(user));
        }//可能存在在数据库中不允许重复的字段重复的数据，导致产生ERR，因此使用Throwable
        catch(CustomException e){
            return AjaxResponse.error(CustomExceptionType.SYSTEM_ERROR,"数据插入失败,请检查是否存在重复数据");
        }
    }

    @GetMapping
    public AjaxResponse<?> findUserInfoPage(@RequestParam(defaultValue = "1") Integer pageNo,
                                        @RequestParam(defaultValue = "10") Integer pageSize,
                                        @RequestParam(defaultValue = "") String keyword){
        LambdaQueryWrapper<UserInfo> wrapper = Wrappers.lambdaQuery();
        if(StrUtil.isNotBlank(keyword)){
            wrapper.like(UserInfo::getUsername,keyword).or()
                    .like(UserInfo::getRemark,keyword).or()
                    .like(UserInfo::getGroupId,keyword);
        }
        Page<UserInfo> UserInfoPage = userInfoService.page(new Page<>(pageNo, pageSize), wrapper);
        return AjaxResponse.success(UserInfoPage);
    }

    @PutMapping
    public AjaxResponse<?> updateUserInfo(@RequestBody UserInfo user){
        try {
            return AjaxResponse.success(userInfoService.updateById(user));
        }//可能存在在数据库中不允许重复的字段重复的数据，导致产生ERR，因此使用Throwable
        catch(Throwable e){
            return AjaxResponse.error(CustomExceptionType.SYSTEM_ERROR,"数据更新失败,请检查是否存在重复数据");
        }
    }

    @DeleteMapping
    public AjaxResponse<?> deleteUserInfo(@RequestParam Long id){
        try {
            return AjaxResponse.success(userInfoService.removeById(id));
        }catch (CustomException e){
            return AjaxResponse.error(CustomExceptionType.SYSTEM_ERROR,"数据删除失败");
        }

    }
}
