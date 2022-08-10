package com.platform.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.platform.pojo.GroupRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GroupRoleMapper extends BaseMapper<GroupRole> {

    @Select("SELECT role_id\n" +
            "FROM group_role\n" +
            "WHERE group_id = #{groupId}")
    List<Long> selectAllRoleIdByGroupId(@Param("groupId") Long groupId);

    @Select("SELECT role_name \n" +
            "FROM control_role cr \n" +
            "LEFT JOIN \n" +
            "group_role gr\n" +
            "ON gr.role_id = cr.id\n" +
            "WHERE gr.group_id = #{groupId}")
    List<String> selectAllRoleNameByGroupId(@Param("groupId") Long groupId);

    @Delete("DELETE \n" +
            "FROM group_role \n" +
            "WHERE group_id = #{groupId}\n" +
            "AND role_id = #{roleId}")
    int deleteGroupRole(@Param("groupId") Long groupId,@Param("roleId") Long roleId);

    @Select("SELECT role_name\n" +
            "FROM control_role cr\n" +
            "WHERE cr.id NOT IN\n" +
            "(SELECT role_id FROM group_role WHERE group_id = #{groupId})")
    List<String> selectAvailableRole(@Param("groupId") Long groupId);
}
