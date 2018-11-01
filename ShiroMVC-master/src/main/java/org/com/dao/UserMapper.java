package org.com.dao;

import org.com.bean.User;

import java.util.List;

/**
 * Created by Administrator on 2018/4/17.
 */
public interface UserMapper {
    List<User>  findUserById(Integer id);
    void deleteUserById(Integer id);
    void updateUserById(Integer id);
    User findUserByUserName(String userName);
}
