package com.platform.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lpl
 * @create 2022/7/25 15:47
 * @description TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserGroup {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String groupName;
    private String description;
    private String remark;
}
