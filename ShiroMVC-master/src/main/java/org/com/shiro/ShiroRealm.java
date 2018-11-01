package org.com.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.com.bean.Role;
import org.com.bean.User;
import org.com.dao.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;
import java.util.*;

public class ShiroRealm extends AuthorizingRealm{
    @Autowired
    private UserMapper userMapper;
    Logger logger = LoggerFactory.getLogger(ShiroRealm.class);
    private String name="ShiroRealm";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
/**
     * 进行授权，认证通过后判断该用户拥有什么角色从而判断该用户可以访问哪些资源或者可以进行哪些操作
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        String userName = (String)principalCollection.getPrimaryPrincipal();
        System.out.println("username="+userName);
        org.apache.shiro.subject.Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        session.removeAttribute("currentUserRoles");
        session.removeAttribute("currentUserUrls");
        //如果权限配置数据库可通过查询数据库获取否则可以通过配置文件形式来进行定义、这里只通过roles来控制
        Set<String> roles = new HashSet<String>();
        List<String> urls = new ArrayList<String>();
        //数据库连接 一个用户可以拥有多个角色
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shiro_mvc?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT", "root", "123");
            String sql="select r.role,p.url from sys_role r left join sys_permission p on r.role=p.rolename \n" +
                    "where r.uid = (select uid from user_info where username = ?);";
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setObject(1, userName);
            ResultSet res = psmt.executeQuery();
            while(res.next()){
                roles.add(res.getString("role"));
                urls.add(res.getString("url"));
            }
            session.setAttribute("currentUserRoles",roles);//将当前用户角色放入session回话中
            session.setAttribute("currentUserUrls",urls);//将当前用户角色放入session回话中
        }catch (SQLException e) {
            logger.error("数据库连接失败。。。。");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            logger.error("加载数据驱动失败。。。。");
            e.printStackTrace();
        }
        simpleAuthorizationInfo.addRoles(roles);
        System.out.println("该用户的角色为:"+roles.toString()+",权限url为:"+urls);
        return simpleAuthorizationInfo;
    }

    /**
     * 进行认证   用户登陆验证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        SimpleAuthenticationInfo simpleAuthenticationInfo = null;
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;//获取用户输入的token信息
        String userName = token.getUsername();
        org.apache.shiro.subject.Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        //数据库连接
            try {
               User user = userMapper.findUserByUserName(userName);
               String dateBasePassword = user.getPassword();
               System.out.println("数据库的密码为:"+dateBasePassword);
                session.setAttribute("user",user);
               simpleAuthenticationInfo=new SimpleAuthenticationInfo(userName,dateBasePassword,this.getName());
            }catch (Exception e) {
                logger.error("查询User表失败。。。。");
                e.printStackTrace();
            }
        return simpleAuthenticationInfo;
    }
}

