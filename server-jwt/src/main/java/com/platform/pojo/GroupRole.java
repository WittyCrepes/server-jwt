package com.platform.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lpl
 * @create 2022/7/25 15:49
 * @description TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupRole {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long groupId;
    private Long roleId;
}
