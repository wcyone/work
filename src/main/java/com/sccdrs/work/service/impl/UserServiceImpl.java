package com.sccdrs.work.service.impl;

import com.sccdrs.work.entity.User;
import com.sccdrs.work.mapper.UserMapper;
import com.sccdrs.work.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wcy
 * @date 2019/7/29 15:01
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User Sel(int id) {
        return userMapper.Sel(id);
    }
}
