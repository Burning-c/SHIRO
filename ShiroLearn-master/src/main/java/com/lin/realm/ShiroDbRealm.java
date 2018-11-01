package com.lin.realm;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.lin.domain.Permission;
import com.lin.domain.Role;
import com.lin.domain.User;
import com.lin.service.UserService;
import com.lin.utils.CipherUtil;

public class ShiroDbRealm extends AuthorizingRealm {
	private static Logger logger = LoggerFactory.getLogger(ShiroDbRealm.class);
	private static final String ALGORITHM = "MD5";

	@Autowired
	private UserService userService;

	public ShiroDbRealm() {
		super();
	}

	/**
	 * 验证登陆
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		System.out.println(token.getUsername());
		User user = userService.findUserByLoginName(token.getUsername());
		System.out.println(user);
		CipherUtil cipher = new CipherUtil();// MD5加密
		if (user != null) {
			return new SimpleAuthenticationInfo(user.getName(), user.getPassword(), getName());
		} else {
			throw new AuthenticationException();
		}
	}

	/**
	 * 登陆成功之后，进行角色和权限验证
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

		String username = (String) getAvailablePrincipal(principals);
		// String username = (String) principals.getPrimaryPrincipal();

		// 列举此用户所有的权限
		List<Permission> permissions = userService.findUserPermissionByName(username);

		Set<String> strs=new HashSet<String>();
		Iterator<Permission> it = permissions.iterator();
		while (it.hasNext()) {
			Permission per=it.next();
			strs.add(per.getPermission());
		}

		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.addStringPermissions(strs);

		return authorizationInfo;
	}

	/**
	 * 清除所有用户授权信息缓存.
	 */
	public void clearCachedAuthorizationInfo(String principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
		clearCachedAuthorizationInfo(principals);
	}

	/**
	 * 清除所有用户授权信息缓存.
	 */
	public void clearAllCachedAuthorizationInfo() {
		Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
		if (cache != null) {
			for (Object key : cache.keys()) {
				cache.remove(key);
			}
		}
	}

	// @PostConstruct
	// public void initCredentialsMatcher() {//MD5鍔犲瘑
	// HashedCredentialsMatcher matcher = new
	// HashedCredentialsMatcher(ALGORITHM);
	// setCredentialsMatcher(matcher);
	// }
}
