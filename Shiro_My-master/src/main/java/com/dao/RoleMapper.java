package com.dao;

import java.util.List;

import com.pojo.Role;

public interface RoleMapper {
	List<Role> getRoles();

	void addRole(Role role);

	void deleterole(int roleid);// 删除一个角色

	void addRolePermission(int roleid, int permissionid);

	void deleterole_permission(int roleid, int permissionid);// 删除一个角色和一个权限的关联

	void deleteroles(int roleid);// 删除一个角色的所有权限关联

	Role getRolebyid(int roleid);

	void updateRole(Role role);
}