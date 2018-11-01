package com.serviceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.dao.RoleMapper;
import com.dao.UserMapper;
import com.pojo.Mapping_RP;
import com.pojo.Mapping_UR;
import com.pojo.Role;
import com.pojo.User;
import com.service.*;

@Service("userService")
public class UserServiceImpl implements IUserService {

	@Resource
	private UserMapper usermapper;
	@Resource
	private RoleMapper rolemapper;

	public User createUser(User user) {
		usermapper.adduser(user);
		return user;
	}

	public void changePassword(int userId, String newPassword) {
		User u = usermapper.getUserByid(userId);
		u.setPassword(newPassword);
		usermapper.updateuser(u);
	}

	public void correlationRoles(int userId, int... roleIds) {
		for (int roleid : roleIds) {
			usermapper.adduserrole(userId, roleid);
		}
	}

	public void uncorrelationRoles(int userId, int... roleIds) {
		for (int roleid : roleIds) {
			usermapper.deleteuserrole(userId, roleid);
		}
	}

	public User findByUsername(String username) {
		User u = usermapper.getUserByusername(username);
		return u;
	}

	public Set<String> findRoles(String username) {
		Set<String> roles = new HashSet<String>();
		User u = usermapper.getUserByusername(username);
		List<Mapping_UR> user_roles = u.getMapping_UR();
		for (Mapping_UR ur : user_roles) {
			Role r = ur.getRole();
			roles.add(r.getName());
		}
		return roles;
	}

	public Set<String> findPermissions(String username) {
		Set<String> permissions = new HashSet<String>();
		User u = usermapper.getUserByusername(username);
		if (u != null) {
			List<Mapping_UR> user_roles = u.getMapping_UR();
			if (user_roles.size() != 0)
				for (Mapping_UR ur : user_roles) {
					Role role = rolemapper.getRolebyid((ur.getRole().getRoleid()));
					if (role != null) {
						List<Mapping_RP> rps = role.getMapping_RP();
						if (rps.size() != 0)
							for (Mapping_RP rp : rps) {
								String permission = rp.getPermission().getName();
								permissions.add(permission);
							}
					}
				}
		}
		return permissions;
	}

	public List<User> getallusers() {
		return usermapper.getusers();
	}

	public User updateuser(User u) {
		usermapper.updateuser(u);
		return u;
	}

	public void deleteuser(int userid) {
		usermapper.deleteuser(userid);
	}

	public User getUser(int uid) {
		User u = usermapper.getUserByid(uid);
		return u;
	}

	public void deleteuserroles(int uid) {
		usermapper.deleteuseroles(uid);
	}

}
