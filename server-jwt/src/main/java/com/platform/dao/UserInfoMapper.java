package com.platform.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.platform.pojo.ControlRole;
import com.platform.pojo.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author lpl
 * @create 2022/7/25 16:04
 * @description TODO
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {
    @Select("SELECT *\n" +
            "FROM user_info u\n" +
            "WHERE username = #{username}")
    UserInfo selectUserByUsername(@Param("username") String username);

    @Select("SELECT group_id FROM user_info WHERE username = #{username}")
    Long selectGroupIdByUsername(@Param("username") String username);


    @Select("SELECT role_code\n" +
            "FROM control_role cr\n" +
            "LEFT JOIN group_role gr\n" +
            "ON gr.role_id = cr.id\n" +
            "LEFT JOIN  user_group ug\n" +
            "ON ug.id = gr.group_id\n" +
            "WHERE ug.id = #{groupId}")
    List<String> selectRolesByUserId(@Param("groupId") Long groupId);
}
