package com.lin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lin.domain.Role;
import com.lin.domain.User;
import com.lin.realm.ShiroDbRealm;
import com.lin.service.RoleService;
import com.lin.service.UserService;
import com.lin.utils.CipherUtil;
import com.lin.utils.Page;

@Controller
public class UserControler {
	private static Logger logger = LoggerFactory.getLogger(ShiroDbRealm.class);
	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	/**
	 * ��֤springmvc��batis���ӳɹ�
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/{id}/showUser")
	public String showUser(@PathVariable int id, HttpServletRequest request) {
		User user = userService.getUserById(id);
		System.out.println(user.getName());
		request.setAttribute("user", user);
		return "showUser";
	}

	/**
	 * ��ʼ��½����
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/login")
	public ModelAndView tologin(HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.debug("����IP[" + request.getRemoteHost() + "]�ķ���");
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("login");
		return modelView;
	}

	/**
	 * ��֤�û���������
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/checkLogin", method = RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request, Model model) {
		ModelAndView modelView = new ModelAndView();

		String result = "login";
		// ȡ���û���
		String username = request.getParameter("username");
		// ȡ�� ���룬����MD5����
//		String password = CipherUtil.generatePassword(request.getParameter("password"));
		String password = request.getParameter("password");
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		Subject currentUser = SecurityUtils.getSubject();
		try {
			currentUser.login(token);// ��֤��ɫ��Ȩ��
		} catch (AuthenticationException e) {
			e.printStackTrace();
			String msg = "��¼�������. Password for account " + token.getPrincipal() + " was incorrect.";
			model.addAttribute("message", msg);
			System.out.println(msg);
			modelView.setViewName("login");
		}
		System.out.println("----------------------------");
		if (currentUser.isAuthenticated()) {// ʹ��shiro����֤
			token.setRememberMe(true);
			modelView.setViewName("redirect:/list");
		} else {
			modelView.setViewName("login");

		}
		return modelView;
	}

	/**
	 * �˳�
	 * 
	 * @return
	 */
	@RequestMapping(value = "/exitUser")
	@ResponseBody
	public ModelAndView logout() {
		ModelAndView modelView = new ModelAndView();
		Subject currentUser = SecurityUtils.getSubject();
		String result = "logout";
		currentUser.logout();
		modelView.setViewName("redirect:login");
		return modelView;
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/chklogin", method = RequestMethod.POST)
	@ResponseBody
	public String chkLogin() {
		Subject currentUser = SecurityUtils.getSubject();
		if (!currentUser.isAuthenticated()) {
			return "false";
		}
		return "true";
	}

	// �û���ɾ�Ĳ�ģ��
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add() {
		System.out.println("======================");
		return "add";
	}

	// �û���ɾ�Ĳ�ģ��
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(User user) {
		userService.insert(user);
		return "redirect:list";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, HttpServletRequest request) {

		/**
		 * ��ȡҳ�����
		 */
		String page = request.getParameter("page");
		String filedName = request.getParameter("filedName");
		String search = request.getParameter("search");
		String selectRole = request.getParameter("selectRole");
		/**
		 * �ܼ�¼��
		 */
		int count = 0;
		int currentPage = 0;
		Page pages = null;
		List<User> user = null;

		/**
		 * �жϵ�ǰҳ
		 */
		if (page == null || page.equals("")) {
			currentPage = 1;
		} else {
			currentPage = Integer.parseInt(page);
		}

		/**
		 * �ж�ģ����ѯ����
		 */
		if (filedName != null && filedName != "" && (search == null || search == "")) {
			/* ��ȡ��ѯ�ܼ�¼�� */
			count = userService.selectCount();

			/* ͨ��Page�������Ի�ȡ��ҳ����ʼ�±������ */
			pages = new Page(count, currentPage);

			/* ƴ�ӷ�ҳ��� */
			user = userService.limit(pages.getStartIndex(), pages.getPagesize());
		} else {

			/* ��ȡ��ѯ�ܼ�¼�� */
			count = userService.selectCount();

			/* ͨ��Page�������Ի�ȡ��ҳ����ʼ�±������ */
			pages = new Page(count, currentPage);

			/* ƴ�ӷ�ҳ��� */
			user = userService.limit(pages.getStartIndex(), pages.getPagesize());
		}

		model.addAttribute("pages", pages);
		model.addAttribute("filedName", filedName);
		model.addAttribute("user", user);
		model.addAttribute("search", search);
		model.addAttribute("role", roleService.selectAll());
		return "list";
	}

	@RequestMapping("/delete")
	@RequiresPermissions({ "user:delete" })
	public String delete(Integer userId) {
		userService.delete(userId);
		return "redirect:list";
	}

	/**
	 * ����ɾ���û�
	 */
	@RequestMapping(value = "/batchDelete")
	public String batchDelete(HttpServletRequest request) {

		String[] ids = request.getParameterValues("subcheck");
		if (ids == null) {
			return "redirect:list";
		} else {
			for (int i = 0; i < ids.length; i++) {
				Integer id = Integer.parseInt(ids[i]);
				userService.delete(id);
				;
			}
			return "redirect:list";
		}
	}

	@RequestMapping("/load")
	@RequiresPermissions({ "user:update" })
	public String load(Model model, Integer userId) {
		User user = userService.selectPrimaryById(userId);
		model.addAttribute("user", user);
		return "update";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(User user) {
		userService.update(user);
		return "redirect:list";
	}

	@RequestMapping(value = "/addRole", method = RequestMethod.POST)
	public String update(HttpServletRequest request, Model model) {
		String userId = request.getParameter("userId");
		// ȡ�� ���룬����MD5����
		String roleId = (request.getParameter("role"));
		userService.insertRole(Integer.valueOf(roleId), Integer.valueOf(userId));
		return "redirect:list";
	}

}
