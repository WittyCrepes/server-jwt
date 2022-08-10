package com.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.platform.dao.MenuMapper;
import com.platform.pojo.Menu;
import org.springframework.stereotype.Service;

@Service
public class MenuService extends ServiceImpl<MenuMapper,Menu> {

}
