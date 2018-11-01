package com.Controllers;

import java.util.List;
import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.page.Message;
import com.pojo.Permission;
import com.service.IPermissionService;

@Controller
public class PromissionController {
	
	@Resource
	private IPermissionService permissionService;
	
	@RequiresRoles("admin")
	@RequestMapping(value = "/allPermissions", method = RequestMethod.GET)
	@ResponseBody
	public List<Permission> getallpermissions() {
		List<Permission> list = permissionService.getPermissions();
		return list;
	}

	@RequestMapping(value = "/deletePermission/{pid}", method = RequestMethod.DELETE)
	@ResponseBody
	public Message deletepermission(@PathVariable int pid) {
		permissionService.deletePermissions(pid);
		permissionService.deletePermission(pid);
		return new Message("success");
	}

	@RequiresRoles("admin")
	@RequestMapping(value = "/addPermission", method = RequestMethod.POST)
	@ResponseBody
	public Permission addpermission(@RequestBody Permission p) {
		Permission per = permissionService.createPermission(p);
		return per;
	}

	@RequestMapping(value = "/updatePermission", method = RequestMethod.PUT)
	@ResponseBody
	public Permission updatepermission(@RequestBody Permission p) {
		Permission per = permissionService.updatePermission(p);
		return per;
	}
}
