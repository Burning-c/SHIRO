package com.lin.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lin.dao.RoleDao;
import com.lin.domain.Role;
import com.lin.service.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao userDao;

	@Override
	public List<Role> selectAll() {

		return userDao.selectAll();
	}

}
