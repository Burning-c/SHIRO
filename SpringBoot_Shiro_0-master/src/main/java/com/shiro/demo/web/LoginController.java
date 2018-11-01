package com.shiro.demo.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shiro.demo.domain.User;

@RestController
public class LoginController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		Subject subject = SecurityUtils.getSubject();
		User sessionUser = (User) subject.getPrincipal();
		if(sessionUser==null) {
			return "未登录，请先登录";
		}else {
			return "路径不对";
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(String username, String password, String vcode, Boolean rememberMe) {
		// 获取当前subject
//		Subject currentUser = SecurityUtils.getSubject();
//		User sessionUser = (User) currentUser.getPrincipal();
		// 测试当前的用户是否已经被认证，即是否已经登陆
//		if (sessionUser == null// 未登录
//				|| !currentUser.isAuthenticated()// 未认证
//				|| !sessionUser.getUsername().equals(username)// 账号改变
//				|| !sessionUser.getPassword().equals(password)
//		) {
		// 把用户名和密码封装为UsernamePasswordToken 对象
		try {
			UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
			// 执行登陆
			SecurityUtils.getSubject().login(token);
			// 将当前用户信息放入session
//			SecurityUtils.getSubject().getSession().setAttribute("user", SecurityUtils.getSubject());

		} catch (UnknownAccountException e) {
			System.out.println("用户未注册!");
			return "用户未注册!";
		} catch (IncorrectCredentialsException e) {
			System.out.println("密码错误!!");
			return "密码错误!!";
		} catch (LockedAccountException e) {
			System.out.println("该账户不可用~");
			return "该账户不可用~";
		} catch (ExcessiveAttemptsException e) {
			System.out.println("尝试次数超限!!");
			return "尝试次数超限!!";
		}
//		}
		return "登录成功";

	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public User home() {
		Subject subject = SecurityUtils.getSubject();
		boolean hasRole = subject.hasRole("admin");
		User sessionUser = (User) subject.getPrincipal();
		return sessionUser;
	}

	/**
	 * 登录方法
	 * 
	 * @param userInfo
	 * @return
	 */
	@RequestMapping(value = "/ajaxLogin", method = RequestMethod.POST)
	@ResponseBody
	public String ajaxLogin(String username, String password, String vcode, Boolean rememberMe) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
		try {
			subject.login(token);
		} catch (IncorrectCredentialsException e) {
			System.out.println("密码错误");
		} catch (LockedAccountException e) {
			System.out.println("冻结");
		} catch (AuthenticationException e) {
			System.out.println("用户不存在");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "loginSuccess";
	}

}
