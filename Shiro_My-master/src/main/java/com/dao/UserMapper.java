package com.dao;

import java.util.List;

import com.pojo.User;

public interface UserMapper {
	List<User> getusers();

	User getUserByid(int userid);

	User getUserByusername(String username);

	void deleteuser(int userid);

	void deleteuserrole(int userid, int roleid);

	void adduserrole(int userid, int roleid);

	void adduser(User user);

	void updateuser(User user);

	void deleteuseroles(int userid);

}