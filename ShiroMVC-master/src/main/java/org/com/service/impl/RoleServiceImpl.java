package org.com.service.impl;

import org.com.bean.Role;
import org.com.dao.RoleMapper;
import org.com.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator on 2018/4/17.
 */
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    public List<Role> findRoleByUserName(String userName) {
        return roleMapper.findRoleByUserName(userName);
    }

    public List<String> findRoleNameById(Integer id) {
        return roleMapper.findRoleNameById(id);
    }

}
