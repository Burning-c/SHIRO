package org.com.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.com.bean.User;
import org.com.service.RoleService;
import org.com.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by Administrator on 2018/4/17.
 */
@Controller
public class LoginController {
    @Autowired
    private UserService userService;
//    @Autowired
//    private RoleService roleService;
    Logger logger = LoggerFactory.getLogger(LoginController.class);
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    
    public String login(@RequestParam(value="username") String userName,@RequestParam(value="password") String password){
        //用户登陆后对密码进行加密并与数据库密码核对校验   MD5+salt
        String salt = "admin";
        //加密   MD5+salt方式
        SimpleHash sh = new SimpleHash("MD5",password,salt,1024);
        System.out.println("加密之后的密码为:"+sh);
        UsernamePasswordToken token=new UsernamePasswordToken(userName,sh.toString());//获取用户账号及密码
        org.apache.shiro.subject.Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        Session session = currentUser.getSession();
        session.setAttribute("userName",userName);
        if(!currentUser.isAuthenticated()){//未经过认证则需要先认证
                try{
                    currentUser.login(token);
                    User user = (User) session.getAttribute("user");
                    System.out.println("开始设置角色。。。。。");
                    currentUser.hasRole("");
                    System.out.println("设置角色完毕。。。。。");
                }catch(AuthenticationException e){
                    //未通过认证返回错误页面或者登陆页面
                    logger.error("认证失败，请重新登陆。。。。。");
                    currentUser.logout();
                    return "error";
                }
        }
        return "success";
    }
    @RequestMapping("/findbyId")
    public String findbyId(){
        List<User> list = userService.findUserById(1);
        return "index";
    }
    @RequestMapping("/addUser")
    public String addUser(){
        return "index";
    }
    @RequestMapping("/updateUser")
    public String updateUser(){
        return "index";
    }
    @RequestMapping("/deleteUserById")
      public String deleteUserById(){
        return "index";
    }
    @RequestMapping("/findRoleById")
    public String findRoleById(){
        return "index";
    }
    @RequestMapping("/deleteRoleById")
    public String deleteRoleById(){
        return "index";
    }
    @RequestMapping("/updateRoleById")
    public String updateRoleById(){
        return "index";
    }
}
