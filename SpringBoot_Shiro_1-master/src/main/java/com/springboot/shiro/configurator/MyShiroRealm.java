package com.springboot.shiro.configurator;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.springboot.shiro.common.Logs;
import com.springboot.shiro.model.Permission;
import com.springboot.shiro.model.Role;
import com.springboot.shiro.model.User;
import com.springboot.shiro.service.UserService;

/**
 * @author 作者 :HouZhiBo
 * @version 创建时间:2018年10月1日 下午3:59:14 类说明:page'
 */
public class MyShiroRealm extends AuthorizingRealm {

	@Resource
	private UserService userService;

	@Override
//	@RequiresRoles("admin")
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Logs.LOGGER.info("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		User user = (User) principals.getPrimaryPrincipal();
		for (Role role : user.getRoleList()) {
			authorizationInfo.addRole(role.getRole());
			for (Permission permission : role.getPermissions()) {
				authorizationInfo.addStringPermission(permission.getPermission());
			}
		}
		return authorizationInfo;
	}

//	@Override
//	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//		// 1.把AuthenticationToken转换为UsernamePasswordToken
//		UsernamePasswordToken userToken = (UsernamePasswordToken) token;
//
//		// 2.从UsernamePasswordToken中获取username
//		String username = userToken.getUsername();
//
//		// 3.调用数据库的方法，从数据库中查询Username对应的用户记录
//		System.out.println("从数据看中获取UserName为" + username + "所对应的信息。");
//		// Map模拟数据库取数据
//		User u = userMap.get(username);
//
//		// 4.若用户不行存在，可以抛出UnknownAccountException
//		if (u == null) {
//			throw new UnknownAccountException("用户不存在");
//		}
//
//		// 5.若用户被锁定，可以抛出LockedAccountException
//		if (u.isLocked()) {
//			throw new LockedAccountException("用户被锁定");
//		}
//
//		// 7.根据用户的情况，来构建AuthenticationInfo对象,通常使用的实现类为SimpleAuthenticationInfo
//		// 以下信息是从数据库中获取的
//		// 1)principal：认证的实体信息，可以是username，也可以是数据库表对应的用户的实体对象
//		Object principal = u.getUsername();
//		// 2)credentials：密码
//		Object credentials = u.getPassword();
//		// 3)realmName：当前realm对象的name，调用父类的getName()方法即可
//		String realmName = getName();
//		// 4)credentialsSalt盐值
//		ByteSource credentialsSalt = ByteSource.Util.bytes(principal + "" + principal);// 使用账号作为盐值
//
//		SimpleAuthenticationInfo info = null; // new SimpleAuthenticationInfo(principal,credentials,realmName);
//		info = new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);
//		return info;
//	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

		Logs.LOGGER.info("开始身份认证：===》  MyShiroRealm.doGetAuthenticationInfo()");
		// 获取用户的输入的账号.
		UsernamePasswordToken userToken = (UsernamePasswordToken) token;
		String username = userToken.getUsername();
		String password = String.valueOf(userToken.getPassword());
		Logs.LOGGER.info("用户信息" + token.getCredentials());
		// 通过username从数据库中查找 User对象，如果找到，没找到.
		// 实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
		User user = userService.findByUsername(username);

//		User user = userMap.get(username);
		Logs.LOGGER.info("----->>user=：：" + user);
		if (user == null) {
			throw new UnknownAccountException("用户不存在");
		}
		if (user.getState() == 1) { // 账户冻结
			throw new LockedAccountException();
		}
		List<Role> roleList= new ArrayList<>();
		Role role=new Role();
		role.setRole(user.getName());
		List<Permission> permissions=new ArrayList();
//		role.setPermissions("");
		roleList.add(role);
		user.setRoleList(roleList);
		ByteSource credentialsSalt = ByteSource.Util.bytes(username + "" + password);// 使用账号作为盐值
		return new SimpleAuthenticationInfo(user, user.getPassword(), credentialsSalt, getName());
	}
	
}
