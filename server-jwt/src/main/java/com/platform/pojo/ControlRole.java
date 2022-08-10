package com.platform.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author lpl
 * @create 2022/7/25 15:51
 * @description 角色实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ControlRole {
    @TableId(type = IdType.AUTO)
    private Long id;
    //角色中文名
    private String roleName;
    //英文代码，比如admin
    private String roleCode;
    private String description;
    private String remark;

}
