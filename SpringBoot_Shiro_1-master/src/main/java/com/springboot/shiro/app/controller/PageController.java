package com.springboot.shiro.app.controller;

import com.springboot.shiro.common.Logs;
import com.springboot.shiro.model.User;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author 作者 :HouZhiBo
 * @version 创建时间:2018年10月1日 下午3:59:14 类说明:page'
 */
@Controller
public class PageController {

	
	@RequestMapping(value = {"/loinErr" })
	public String loginError(String message) {
		return "error";
	}
	@RequestMapping(value = { "/", "/index" })
	public String index() {
		Subject subject = SecurityUtils.getSubject();
//		boolean hasRole = subject.hasRole("admin");
		User sessionUser = (User) subject.getPrincipal();
		return "index";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
//		org.apache.shiro.subject.Subject currentUser = SecurityUtils.getSubject();
		SecurityUtils.getSubject().logout();
		return "login";
	}

	@RequestMapping(value = "/login")
	public String login(HttpServletRequest request, Map<String, Object> map) {
		Logs.LOGGER.info("PageController.login()");
		// 登录失败从request中获取shiro处理的异常信息。
		// shiroLoginFailure:就是shiro异常类的全类名.
		String exception = (String) request.getAttribute("shiroLoginFailure");
		Logs.LOGGER.info("Exception::" + exception);
		String msg = "";
		if (exception!= null){
            if (UnknownAccountException.class.getName().equals(exception)) {
                System.out.println("UnknownAccountException -- > 账号不存在：");
                msg = "UnknownAccountException -- > 账号不存在：";
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                System.out.println("IncorrectCredentialsException -- > 密码不正确：");
                msg = "IncorrectCredentialsException -- > 密码不正确：";
            } else if ("kaptchaValidateFailed".equals(exception)) {
                System.out.println("kaptchaValidateFailed -- > 验证码错误");
                msg = "kaptchaValidateFailed -- > 验证码错误";
            } else if (LockedAccountException.class.getName().equals(exception)) {
				System.out.println("LockedAccountException -- > 账号已锁定：");
				msg = "LockedAccountException -- > 账号已锁定";
			} else {
                msg = "else >> "+exception;
                System.out.println("else -- >" + exception);
            }
        }
        map.put("msg",msg);
        // 此方法不处理登录成功,由shiro进行处理.
        return "/login";
	}
	
}
