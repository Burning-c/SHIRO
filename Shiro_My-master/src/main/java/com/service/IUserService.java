package com.service;

import java.util.List;
import java.util.Set;

import com.pojo.User;

public interface IUserService {

	/**
	 * 创建用户
	 * 
	 * @param user
	 */
	public User createUser(User user);

	/**
	 * 修改密码
	 * 
	 * @param userId
	 * @param newPassword
	 */
	public void changePassword(int userId, String newPassword);

	/**
	 * 添加用户-角色关系
	 * 
	 * @param userId
	 * @param roleIds
	 */
	public void correlationRoles(int userId, int... roleIds);

	/**
	 * 移除用户-角色关系
	 * 
	 * @param userId
	 * @param roleIds
	 */
	public void uncorrelationRoles(int userId, int... roleIds);

	/**
	 * 根据用户名查找用户
	 * 
	 * @param username
	 * @return
	 */
	public User findByUsername(String username);

	public Set<String> findRoles(String username);

	/**
	 * 根据用户名查找其权限
	 * 
	 * @param username
	 * @return
	 */
	public Set<String> findPermissions(String username);

	public List<User> getallusers();

	public User updateuser(User u);

	public void deleteuser(int userid);

	public User getUser(int userid);

	public void deleteuserroles(int userid);

}
