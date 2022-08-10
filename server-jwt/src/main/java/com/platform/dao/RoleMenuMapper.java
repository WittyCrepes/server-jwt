package com.platform.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.platform.pojo.RoleMenu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {
    @Select("SELECT menu_id FROM role_menu WHERE role_id = #{roleId}")
    List<Long> selectMenuIdByRoleId(@Param("roleId") Long roleId);

    @Select("SELECT menu_name \n" +
            "FROM menu \n" +
            "left join\n" +
            "role_menu rm \n" +
            "on menu.id = rm.menu_id\n" +
            "WHERE rm.role_id = #{roleId}")
    List<String> selectAllMenuNameByRoleId(@Param("roleId") Long roleId);

    @Delete("DELETE \n" +
            "FROM role_menu \n" +
            "WHERE menu_id = #{menuId}\n" +
            "AND role_id = #{roleId}")
    int deleteRoleMenu(@Param("roleId") Long roleId,@Param("menuId") Long menuId);

    @Select("SELECT menu_name\n" +
            "FROM menu\n" +
            "WHERE menu.id NOT IN\n" +
            "(SELECT menu_id FROM role_menu WHERE role_id = #{roleId})")
    List<String> selectAvailableMenu(@Param("roleId") Long roleId);
}
