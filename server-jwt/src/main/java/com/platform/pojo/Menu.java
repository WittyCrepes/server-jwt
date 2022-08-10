package com.platform.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lpl
 * @create 2022/7/25 15:58
 * @description TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String menuName;
    private String url;
    private String menuType;
    private String description;
    private String remark;
}
