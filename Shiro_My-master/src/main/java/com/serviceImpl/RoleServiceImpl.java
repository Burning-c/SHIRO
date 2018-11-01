package com.serviceImpl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.dao.RoleMapper;
import com.pojo.Role;
import com.service.IRoleService;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 5)
public class RoleServiceImpl implements IRoleService {

	@Resource
	private RoleMapper rolemapper;

	public Role createRole(Role role) {
		rolemapper.addRole(role);
		return role;
	}

	public void deleteRole(int roleId) {
		rolemapper.deleterole(roleId);
	}

	public void correlationPermissions(int roleId, int... permissionIds) {
		for (int pid : permissionIds) {
			rolemapper.addRolePermission(roleId, pid);
		}

	}

	public void uncorrelationPermissions(int roleId, int... permissionIds) {
		for (int pid : permissionIds) {
			rolemapper.deleterole_permission(roleId, pid);
		}
	}

	public List<Role> getroles() {
		return rolemapper.getRoles();
	}

	public void deleteroles(int roleid) {
		rolemapper.deleteroles(roleid);
	}

	public Role getrolebyid(int roleid) {
		return rolemapper.getRolebyid(roleid);
	}

	public Role updateRole(Role r) {
		rolemapper.updateRole(r);
		return r;
	}

}
