package org.com.service.impl;

import org.com.dao.PermissionMapper;
import org.com.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator on 2018/4/18.
 */
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;
    public List<String> findUrlByRoleName(String roleName) {
        return permissionMapper.findUrlByRoleName(roleName);
    }
}
