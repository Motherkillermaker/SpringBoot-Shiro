package com.atguigu.service;

import com.atguigu.mapper.UserMapper;
import com.atguigu.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @title: UserService
 * @Author Tan
 * @Date: 2021/11/20 18:35
 * @Version 1.0
 */
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public User queryUserByName(String name) {
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper.eq("user_name",name);
        return userMapper.selectOne(wrapper);
    }
}

