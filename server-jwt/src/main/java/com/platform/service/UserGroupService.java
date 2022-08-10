package com.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.platform.dao.UserGroupMapper;
import com.platform.pojo.UserGroup;
import org.springframework.stereotype.Service;

@Service
public class UserGroupService extends ServiceImpl<UserGroupMapper,UserGroup> {
}
