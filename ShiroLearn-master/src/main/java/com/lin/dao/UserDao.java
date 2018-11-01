package com.lin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lin.domain.Permission;
import com.lin.domain.Role;
import com.lin.domain.User;

public interface UserDao {

	List<User> selectAll();

	User selectPrimaryById(Integer userId);

	User findUserByLoginName(String username);

	// ��ȡ�û�������Ȩ��
	List<Permission> findUserPermissionByName(String username);

	int insert(User user);

	int delete(Integer userId);

	int update(User user);

	int selectCount();

	List<User> limit(@Param("startIndex") int startIndex, @Param("pageSize") int pageSize);

	// ���Ȩ��
	int insertRole(@Param("roleId") Integer roleId, @Param("userId") Integer userId);
	
	int updateRole(@Param("roleId") Integer roleId, @Param("userId") Integer userId);
	
	int countUserRoleById(Integer userId);
}