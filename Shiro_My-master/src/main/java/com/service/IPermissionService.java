package com.service;

import java.util.List;

import com.pojo.Permission;

public interface IPermissionService {
	public Permission createPermission(Permission permission);

	public void deletePermission(int permissionId);

	public void deletePermissions(int permissionId);

	public List<Permission> getPermissions();

	public Permission getPermissionByid(int permissionid);

	public Permission updatePermission(Permission permission);

}
