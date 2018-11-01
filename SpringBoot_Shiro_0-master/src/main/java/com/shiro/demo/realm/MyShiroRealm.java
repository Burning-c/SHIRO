package com.shiro.demo.realm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.shiro.demo.domain.User;
import com.shiro.demo.utils.MyDES;

public class MyShiroRealm extends AuthorizingRealm {

	/**
	 * 授权用户权限
	 */
	@Override
	@RequiresRoles("admin")
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// 获取用户
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		// 获取用户角色
		Set<String> roleSet = new HashSet<String>();
		roleSet.add(user.getUsername());
		info.setRoles(roleSet);

		// 获取用户权限
		Set<String> permissionSet = new HashSet<String>();
		permissionSet.add("权限添加");
		permissionSet.add("权限删除");
		info.setStringPermissions(permissionSet);

		return info;
	}

	/**
	 * 验证用户身份
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {

		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;

		String username = token.getUsername();
//		String password = String.valueOf(token.getPassword());

//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("nickname", username);
//		// 密码进行加密处理 明文为 password+name
//		String paw = password + username;
//		String pawDES = MyDES.encryptBasedDes(paw);
//		map.put("pswd", pawDES);

		// 数据库用户
		User user = new User();
		user.setId("数据库id");
		user.setUsername(username);
		user.setPassword("数据库password");

		if (token.getUsername().equals("admin")) {
			return new SimpleAuthenticationInfo(user, "123", getName());
		} else {
			throw new UnknownAccountException();
		}

	}

}
