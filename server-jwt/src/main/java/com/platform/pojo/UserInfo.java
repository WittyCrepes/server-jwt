package com.platform.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

/**
 * @author lpl
 * @create 2022/7/25 15:35
 * @description TODO
 */
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo implements UserDetails {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String username;
    private String password;
    private Long groupId;
    @TableField(fill = FieldFill.INSERT)
    private Date createDate;
    private Boolean enabled;
    private String remark;
    @TableField(exist = false)
    Collection<? extends GrantedAuthority> authorities;  //用户的权限集合

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public Long getGroupId() {
        return groupId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
