package org.com.controller;

import org.apache.shiro.SecurityUtils;
import org.com.bean.User;

public abstract class BaseController {

	public User getShiroUser() {
		return (User) SecurityUtils.getSubject().getPrincipal();
	}
}
