package com.lin.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lin.dao.UserDao;
import com.lin.domain.Permission;
import com.lin.domain.Role;
import com.lin.domain.User;
import com.lin.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	public User getUserById(int id) {
		return userDao.selectPrimaryById(id);
	}

	public User findUserByLoginName(String username) {
		System.out.println("findUserByLoginName call!");
		return userDao.findUserByLoginName(username);
	}

	@Override
	public List<User> selectAll() {
		return userDao.selectAll();
	}

	@Override
	public User selectPrimaryById(Integer userId) {
		return userDao.selectPrimaryById(userId);
	}

	@Override
	public int insert(User user) {
		userDao.insert(user);
		return 0;
	}

	@Override
	public int delete(Integer id) {
		userDao.delete(id);
		return 0;
	}

	@Override
	public int update(User user) {
		userDao.update(user);
		return 0;
	}

	@Override
	public int selectCount() {
		return userDao.selectCount();
	}

	@Override
	public List<User> limit(int startIndex, int pageSize) {
		return userDao.limit(startIndex, pageSize);
	}

	@Override
	public List<Permission> findUserPermissionByName(String username) {
		System.out.println("findUserByLoginName call!");
		return userDao.findUserPermissionByName(username);
	}

	@Override
	public int insertRole(Integer roleId, Integer userId) {
		if (countUserRoleById(userId) == 0) {
			return userDao.insertRole(roleId, userId);
		} else {

			return userDao.updateRole(roleId, userId);
		}
	}

	@Override
	public int countUserRoleById(Integer userId) {
		// TODO Auto-generated method stub
		return userDao.countUserRoleById(userId);
	}

}
