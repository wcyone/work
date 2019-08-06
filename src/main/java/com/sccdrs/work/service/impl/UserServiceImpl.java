package com.sccdrs.work.service.impl;

import com.sccdrs.work.entity.User;
import com.sccdrs.work.mapper.UserMapper;
import com.sccdrs.work.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author wcy
 * @date 2019/7/29 15:01
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 获取用户策略：先从缓存中获取用户，没有则取数据表中 数据，再将数据写入缓存
     */
    @Override
    public User getUserById(int id) {
        String key = "user_" + id; //定义user在redis中的key
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            User user = operations.get(key);
            System.out.println("==========从缓存中获得数据=========");
            return user;
        } else {
            User user = userMapper.findUserById(id);
            System.out.println("==========从数据表中获得数据=========");
            // 写入缓存 设置缓存的有效时间
            operations.set(key, user, 5, TimeUnit.HOURS);
            return user;
        }
    }

    @Override
    public int updateUser(int id,int status){
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        //修改
        int num = userMapper.updateUser(id,status);
        if (num != 0) {
            String key = "user_" + id;
            boolean haskey = redisTemplate.hasKey(key);
            if (haskey) {
                redisTemplate.delete(key);
                System.out.println("删除缓存中的key=========>" + key);
            }
            // 再将更新后的数据加入缓存
            User userNew = userMapper.findUserById(id);
            if (userNew != null) {
                operations.set(key, userNew, 5, TimeUnit.HOURS);
            }
        }
        return num;

    }

    /**
     * 实际业务为软删除  --暂时不写代码
     * @param id
     * @return
     */
    @Override
    public int deleteUserById(int id) {
        int result = userMapper.deleteUserById(id);
        String key = "user_" + id;
        if (result != 0) {
            boolean hasKey = redisTemplate.hasKey(key);
            if (hasKey) {
                redisTemplate.delete(key);
                System.out.println("删除了缓存中的key:" + key);
            }
        }
        return result;
    }
}
