package com.springboot.shiro.service;

import com.springboot.shiro.common.ServiceException;
import com.springboot.shiro.model.User;

/**
 * @Author: YafengLiang
 * @Description:
 * @Date: Created in  10:10 2018/6/6
 */
public interface UserService {
    User findByUsername(String username) throws ServiceException;
}
