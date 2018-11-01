package org.com.dao;

import java.util.List;

/**
 * Created by Administrator on 2018/4/18.
 */
public interface PermissionMapper {
    List<String> findUrlByRoleName(String roleName);
}
