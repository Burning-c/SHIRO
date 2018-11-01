package com.lin.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lin.domain.Permission;
import com.lin.domain.Role;
import com.lin.domain.User;

public interface UserService {
	public User getUserById(int id);

	public User findUserByLoginName(String username);
	
	public List<Permission> findUserPermissionByName(String username);
	
	List<User> selectAll();

	User selectPrimaryById(Integer userId);

	int insert(User user);

	int delete(Integer userId);

	int update(User user);

	int selectCount();

	List<User> limit(@Param("startIndex") int startIndex, @Param("pageSize") int pageSize);

	
	// ÃÌº”»®œﬁ
	int insertRole(Integer roleId, Integer userId);
	
	int countUserRoleById(Integer userId);
}
