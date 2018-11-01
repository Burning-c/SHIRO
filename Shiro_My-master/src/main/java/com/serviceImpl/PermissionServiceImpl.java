package com.serviceImpl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.dao.PermissionMapper;
import com.pojo.Permission;
import com.service.IPermissionService;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,timeout=5)
@Service
public class PermissionServiceImpl implements IPermissionService {

	@Resource
	private PermissionMapper permissionmapper;
	
	public Permission createPermission(Permission permission) {
		permissionmapper.addpermission(permission);
		return permission;
	}

	public void deletePermission(int permissionId) {
		permissionmapper.deletepermission(permissionId);
	}

	public List<Permission> getPermissions() {
		return permissionmapper.getPermissions();
	}

	public Permission getPermissionByid(int permissionid) {
		return permissionmapper.getPermissionByid(permissionid);
	}

	public Permission updatePermission(Permission permission) {
		permissionmapper.updatePermission(permission);
		return permission;
	}

	public void deletePermissions(int permissionId) {
		permissionmapper.deletePermissionsByid(permissionId);
	}

}
