package com.platform.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.platform.dao.MenuMapper;
import com.platform.exception.CustomException;
import com.platform.pojo.Menu;
import com.platform.service.MenuService;
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
@RequestMapping("/api")
public class MenuController {

    @Resource
    MenuService menuService;

    @PostMapping
    public AjaxResponse<?> createMenu(@RequestBody Menu menu){

        try {
            return AjaxResponse.success(menuService.save(menu));
        }//可能存在在数据库中不允许重复的字段重复的数据，导致产生ERR，因此使用Throwable
        catch(Throwable e){
            return AjaxResponse.error(CustomExceptionType.SYSTEM_ERROR,"数据插入失败,请检查是否存在重复数据");
        }
    }

    @GetMapping
    public AjaxResponse<?> findMenuPage(@RequestParam(defaultValue = "1") Integer pageNo,
                                        @RequestParam(defaultValue = "10") Integer pageSize,
                                        @RequestParam(defaultValue = "") String keyword){
        LambdaQueryWrapper<Menu> wrapper = Wrappers.lambdaQuery();
        if(StrUtil.isNotBlank(keyword)){
            wrapper.like(Menu::getDescription,keyword).or()
                    .like(Menu::getRemark,keyword).or()
                    .like(Menu::getMenuName,keyword);
        }
        Page<Menu> menuPage = menuService.page(new Page<>(pageNo, pageSize), wrapper);
        return AjaxResponse.success(menuPage);
    }

    @PutMapping
    public AjaxResponse<?> updateMenu(@RequestBody Menu menu){
        try {
            return AjaxResponse.success(menuService.updateById(menu));
        }//可能存在在数据库中不允许重复的字段重复的数据，导致产生ERR，因此使用Throwable
        catch(Throwable e){
            return AjaxResponse.error(CustomExceptionType.SYSTEM_ERROR,"数据更新失败,请检查是否存在重复数据");
        }
    }

    @DeleteMapping
    public AjaxResponse<?> deleteMenu(@RequestParam Long id){
        try {
            return AjaxResponse.success(menuService.removeById(id));
        }catch (CustomException e){
            return AjaxResponse.error(CustomExceptionType.SYSTEM_ERROR,"数据删除失败");
        }

    }
}
