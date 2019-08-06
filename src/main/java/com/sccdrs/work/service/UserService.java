package com.sccdrs.work.service;

import com.sccdrs.work.entity.User;

/**
 * @author wcy
 * @date 2019/7/29 15:00
 * @Description:
 */
public interface UserService {
    User getUserById(int id);

    int updateUser(int id,int status);

    int deleteUserById(int id);

}
