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
import com.pojo.Mapping_RP;
import com.pojo.Role;
import com.service.IRoleService;

@Controller
public class RoleController {

	@Resource
	private IRoleService roleService;

	@RequiresRoles("admin")
	@RequestMapping(value = "/allRoles", method = RequestMethod.GET)
	@ResponseBody
	public List<Role> getallRoles() {
		List<Role> list = roleService.getroles();
		return list;
	}

	@RequiresRoles("admin")
	@RequestMapping(value = "/addRole", method = RequestMethod.POST)
	@ResponseBody
	public Role addRole(@RequestBody Role r) {
		Role role = roleService.createRole(r);
		int roleid = role.getRoleid();
		List<Mapping_RP> list = r.getMapping_RP();
		if (list != null && list.size() != 0) {
			for (Mapping_RP rp : list) {
				int pid = rp.getPermission().getPermissionid();
				roleService.correlationPermissions(roleid, pid);
			}
		}

		return role;
	}

	@RequiresRoles("admin")
	@RequestMapping(value = "/deleteRole/{roleid}", method = RequestMethod.DELETE)
	@ResponseBody
	public Message deleteRole(@PathVariable int roleid) {
		roleService.deleteroles(roleid);
		roleService.deleteRole(roleid);
		return new Message("success");
	}

	@RequestMapping(value = "/getRole", method = RequestMethod.GET)
	@ResponseBody
	public Role getRole(@RequestBody int roleid) {
		Role r = roleService.getrolebyid(roleid);
		return r;
	}

	@RequiresRoles("admin")
	@RequestMapping(value = "/updateRole/{roleid}", method = RequestMethod.PUT)
	@ResponseBody
	public Role updateRole(@PathVariable int roleid, @RequestBody Role r) {
		roleService.updateRole(r);
		if (r.getMapping_RP().size() != 0 && r.getMapping_RP() != null) {
			roleService.deleteroles(roleid);
			List<Mapping_RP> list = r.getMapping_RP();
			for (Mapping_RP rp : list) {
				int pid = rp.getPermission().getPermissionid();
				roleService.correlationPermissions(roleid, pid);
			}
		}

		return r;
	}

}
