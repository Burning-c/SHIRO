package org.com.dao;

import org.com.bean.Role;

import java.util.List;

/**
 * Created by Administrator on 2018/4/17.
 */
public interface RoleMapper {
    List<Role> findRoleByUserName(String userName);
    List<String> findRoleNameById(Integer id);
}
