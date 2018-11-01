package org.com.service;

import org.com.bean.User;

import java.util.List;

/**
 * Created by Administrator on 2018/4/17.
 */
public interface UserService {
    List<User> findUserById(Integer id);
    void deleteUserById(Integer id);
    void updateUserById(Integer id);
    User findUserByUserName(String userName);
}
