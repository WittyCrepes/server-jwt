package com.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.platform.dao.UserInfoMapper;
import com.platform.pojo.UserInfo;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService extends ServiceImpl<UserInfoMapper,UserInfo> {
}
