package com.platform.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.platform.exception.CustomException;
import com.platform.pojo.UserGroup;
import com.platform.service.UserGroupService;
import com.platform.utils.AjaxResponse;
import com.platform.utils.CustomExceptionType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author lpl
 * @create 2022/8/5 9:55
 * @description TODO
 */
@RestController
@RequestMapping("/user_group")
public class UserGroupController {

    @Resource
    UserGroupService userGroupService;

    @PostMapping
    public AjaxResponse<?> createUserGroup(@RequestBody UserGroup userGroup){

        try {
            return AjaxResponse.success(userGroupService.save(userGroup));
        }//可能存在在数据库中不允许重复的字段重复的数据，导致产生ERR，因此使用Throwable
        catch(Throwable e){
            return AjaxResponse.error(CustomExceptionType.SYSTEM_ERROR,"数据插入失败,请检查是否存在重复数据");
        }
    }

    @GetMapping
    public AjaxResponse<?> findUserGroupPage(@RequestParam(defaultValue = "1") Integer pageNo,
                                        @RequestParam(defaultValue = "10") Integer pageSize,
                                        @RequestParam(defaultValue = "") String keyword){
        LambdaQueryWrapper<UserGroup> wrapper = Wrappers.lambdaQuery();
        if(StrUtil.isNotBlank(keyword)){
            wrapper.like(UserGroup::getDescription,keyword).or()
                    .like(UserGroup::getRemark,keyword).or()
                    .like(UserGroup::getGroupName,keyword);
        }
        Page<UserGroup> UserGroupPage = userGroupService.page(new Page<>(pageNo, pageSize), wrapper);
        return AjaxResponse.success(UserGroupPage);
    }

    @PutMapping
    public AjaxResponse<?> updateUserGroup(@RequestBody UserGroup UserGroup){
        try {
            return AjaxResponse.success(userGroupService.updateById(UserGroup));
        }//可能存在在数据库中不允许重复的字段重复的数据，导致产生ERR，因此使用Throwable
        catch(Throwable e){
            return AjaxResponse.error(CustomExceptionType.SYSTEM_ERROR,"数据更新失败,请检查是否存在重复数据");
        }
    }

    @DeleteMapping
    public AjaxResponse<?> deleteUserGroup(@RequestParam Long id){
        try {
            return AjaxResponse.success(userGroupService.removeById(id));
        }catch (CustomException e){
            return AjaxResponse.error(CustomExceptionType.SYSTEM_ERROR,"数据删除失败");
        }

    }
}
