package com.service;

import java.util.List;

import com.pojo.Role;

public interface IRoleService {

	public Role createRole(Role role);

	public void deleteRole(int roleId);

	/**
	 * 添加角色-权限之间关系
	 * 
	 * @param roleId
	 * @param permissionIds
	 */
	public void correlationPermissions(int roleId, int... permissionIds);

	/**
	 * 移除角色-权限之间关系
	 * 
	 * @param roleId
	 * @param permissionIds
	 */
	public void uncorrelationPermissions(int roleId, int... permissionIds);

	public List<Role> getroles();

	public void deleteroles(int roleid);// 删除该角色的所有权限关联

	public Role getrolebyid(int roleid);

	public Role updateRole(Role r);

}
