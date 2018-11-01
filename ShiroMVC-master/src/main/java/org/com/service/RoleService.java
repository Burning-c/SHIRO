package org.com.service;

import org.com.bean.Role;

import java.util.List;

/**
 * Created by Administrator on 2018/4/17.
 */
public interface RoleService {
    List<Role> findRoleByUserName(String userName);
    List<String> findRoleNameById(Integer id);
}
