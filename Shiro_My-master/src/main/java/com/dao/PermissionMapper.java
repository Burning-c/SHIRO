package com.dao;

import java.util.List;

import com.pojo.Permission;

public interface PermissionMapper {
	List<Permission> getPermissions();

	void addpermission(Permission permission);

	void deletepermission(int pid);

	Permission getPermissionByid(int pid);

	void updatePermission(Permission permission);

	void deletePermissionsByid(int permissionid);// 删除所有角色中的permission
}