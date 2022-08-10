package com.platform.controller;

import com.platform.dao.ControlRoleMapper;
import com.platform.dao.GroupRoleMapper;
import com.platform.dao.MenuMapper;
import com.platform.dao.RoleMenuMapper;
import com.platform.exception.CustomException;
import com.platform.pojo.GroupRole;
import com.platform.pojo.RoleMenu;
import com.platform.utils.AjaxResponse;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author  lpl
 * @create 2022/8/8 18:12
 * @description TODO
 */
@RestController
@RequestMapping("/association")
public class AssociationController {
    @Resource
    GroupRoleMapper groupRoleMapper;

    @Resource
    RoleMenuMapper roleMenuMapper;

    @Resource
    ControlRoleMapper controlRoleMapper;

    @Resource
    MenuMapper menuMapper;

    @PostMapping("/group_role")
    public AjaxResponse<?> saveGroupRole(@RequestParam Long id,@RequestParam String name){
        try{
            Long rId = controlRoleMapper.selectRoleIdByRoleName(name);
            groupRoleMapper.insert(new GroupRole(null,id,rId));
            return AjaxResponse.success();
        }catch (CustomException e){
            return AjaxResponse.error(e);
        }
    }
    @GetMapping("/group_role")
    public AjaxResponse<?> getRoleName(@RequestParam Long id){
        try{
            List<String> names = groupRoleMapper.selectAllRoleNameByGroupId(id);
            return AjaxResponse.success(names);
        }catch (CustomException e){
            return AjaxResponse.error(e);
        }
    }
    @GetMapping("/group_role_available")
    public AjaxResponse<?> getAvailableRole(Long id){
        try{
            List<String> menu = groupRoleMapper.selectAvailableRole(id);
            return AjaxResponse.success(menu);
        }catch (CustomException e){
            return AjaxResponse.error(e);
        }
    }

    @DeleteMapping("/group_role")
    public AjaxResponse<?> deleteGroupRole(@RequestParam Long id
            ,@RequestParam String name){
        try {
            Long roleId = controlRoleMapper.selectRoleIdByRoleName(name);
            groupRoleMapper.deleteGroupRole(id,roleId);
            return AjaxResponse.success();
        }catch (CustomException e){
            return AjaxResponse.error(e);
        }
    }

    @PostMapping("/role_menu")
    public AjaxResponse<?> saveRoleMenu(@RequestParam Long id,
                                        @RequestParam String name){
        try{
            Long menuId = menuMapper.selectIdByMenuName(name);
            roleMenuMapper.insert(new RoleMenu(null,id,menuId));
            return AjaxResponse.success();
        }catch (CustomException e){
            return AjaxResponse.error(e);
        }
    }

    @GetMapping("/role_menu")
    public AjaxResponse<?> getMenu(Long id){
        try{
            List<String> menu = roleMenuMapper.selectAllMenuNameByRoleId(id);
            return AjaxResponse.success(menu);
        }catch (CustomException e){
            return AjaxResponse.error(e);
        }
    }


    @DeleteMapping("/role_menu")
    public AjaxResponse<?> deleteRoleMenu(@RequestParam Long id
            ,@RequestParam String name){
        try {
            Long menuId = menuMapper.selectIdByMenuName(name);
            roleMenuMapper.deleteRoleMenu(id,menuId);
            return AjaxResponse.success();
        }catch (CustomException e){
            return AjaxResponse.error(e);
        }
    }

    @GetMapping("/role_menu_available")
    public AjaxResponse<?> getAvailableMenu(Long id){
        try{
            List<String> menu = roleMenuMapper.selectAvailableMenu(id);
            return AjaxResponse.success(menu);
        }catch (CustomException e){
            return AjaxResponse.error(e);
        }
    }


}
