package org.com.service;

import java.util.List;

/**
 * Created by Administrator on 2018/4/18.
 */
public interface PermissionService {
    List<String> findUrlByRoleName(String roleName);
}
