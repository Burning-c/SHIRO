package com.springboot.shiro.service;

import com.springboot.shiro.common.Logs;
import com.springboot.shiro.common.ServiceException;
import com.springboot.shiro.mapper.UserMapper;
import com.springboot.shiro.model.User;
import com.springboot.shiro.model.UserExample;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: YafengLiang
 * @Description:
 * @Date: Created in 10:10 2018/6/6
 */
@Service
public class UserImpl implements UserService {

	@Resource
	private UserMapper userMapper;

	@Override
	public User findByUsername(String username) throws ServiceException {
		Logs.LOGGER.info("登录 ===》》 UserImpl.findByUsername()");
		UserExample example = new UserExample();
		UserExample.Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<User> users = userMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(users)) {
			return null;
		}
		User user = users.get(0);
		return user;
	}
}
