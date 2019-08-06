package com.sccdrs.work.mapper;

import com.sccdrs.work.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author wcy
 * @date 2019/7/29 15:03
 * @Description:
 */

@Repository
public interface UserMapper {
    User findUserById(int id);

    int updateUser(int id,int status);

    int deleteUserById(int id);


}
