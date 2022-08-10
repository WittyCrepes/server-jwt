package com.platform.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.platform.exception.CustomException;
import com.platform.pojo.ControlRole;
import com.platform.service.ControlRoleService;
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
@RequestMapping("/role")
public class RoleController {

    @Resource
    ControlRoleService roleService;

    @PostMapping
    public AjaxResponse<?> createControlRole(@RequestBody ControlRole role){

        try {
            return AjaxResponse.success(roleService.save(role));
        }//可能存在在数据库中不允许重复的字段重复的数据，导致产生ERR，因此使用Throwable
        catch(Throwable e){
            return AjaxResponse.error(CustomExceptionType.SYSTEM_ERROR,"数据插入失败,请检查是否存在重复数据");
        }
    }

    @GetMapping
    public AjaxResponse<?> findControlRolePage(@RequestParam(defaultValue = "1") Integer pageNo,
                                        @RequestParam(defaultValue = "10") Integer pageSize,
                                        @RequestParam(defaultValue = "") String keyword){
        LambdaQueryWrapper<ControlRole> wrapper = Wrappers.lambdaQuery();
        if(StrUtil.isNotBlank(keyword)){
            wrapper.like(ControlRole::getDescription,keyword).or()
                    .like(ControlRole::getRemark,keyword).or()
                    .like(ControlRole::getRoleCode,keyword).or()
                    .like(ControlRole::getRoleName,keyword);
        }
        Page<ControlRole> ControlRolePage = roleService.page(new Page<>(pageNo, pageSize), wrapper);
        return AjaxResponse.success(ControlRolePage);
    }

    @PutMapping
    public AjaxResponse<?> updateControlRole(@RequestBody ControlRole ControlRole){
        try {
            return AjaxResponse.success(roleService.updateById(ControlRole));
        }//可能存在在数据库中不允许重复的字段重复的数据，导致产生ERR，因此使用Throwable
        catch(Throwable e){
            return AjaxResponse.error(CustomExceptionType.SYSTEM_ERROR,"数据更新失败,请检查是否存在重复数据");
        }
    }

    @DeleteMapping
    public AjaxResponse<?> deleteControlRole(@RequestParam Long id){
        try {
            return AjaxResponse.success(roleService.removeById(id));
        }catch (CustomException e){
            return AjaxResponse.error(CustomExceptionType.SYSTEM_ERROR,"数据删除失败");
        }

    }
}
