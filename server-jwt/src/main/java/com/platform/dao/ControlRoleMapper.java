package com.platform.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.platform.pojo.ControlRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ControlRoleMapper extends BaseMapper<ControlRole>{
    @Select("select id from control_role where role_code = #{roleCode}")
    Long selectRoleIdByRoleCode(@Param("roleCode") String roleCode);

    @Select("select id from control_role where role_name = #{roleName}")
    Long selectRoleIdByRoleName(@Param("roleName") String roleName);

    @Select("select role_code from control_role where id = #{roleId}")
    String selectRoleCodeByRoleId(@Param("roleCode") Long roleId);

}
