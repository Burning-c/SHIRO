package org.com.service.impl;

import org.com.bean.User;
import org.com.dao.UserMapper;
import org.com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/4/17.
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;
    public List<User> findUserById(Integer id) {
        return userMapper.findUserById(id);
    }

    public void deleteUserById(Integer id) {
        userMapper.deleteUserById(id);
    }

    public void updateUserById(Integer id) {
        userMapper.updateUserById(id);
    }

    public User findUserByUserName(String userName) {
        return userMapper.findUserByUserName(userName);
    }
}
