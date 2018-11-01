<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<h3>success page</h3><br/>
    <!--对资源进行角色配置，也可以根据权限来配置-->
    <div>
        <h3>用户操作模块</h3>
        <shiro:hasRole name="admin"><a href="logout">退出当前账号</a></shiro:hasRole><br/>
        <shiro:hasRole name="admin"><a href="findbyId">查询权限</a></shiro:hasRole><br/>
        <shiro:hasRole name="admin"><a href="addUser">新增权限</a></shiro:hasRole><br/>
        <shiro:hasRole name="test"><a href="updateUser">修改权限</a></shiro:hasRole><br/>
        <shiro:hasRole name="vip"><a href="deleteUserById">删除账号权限</a></shiro:hasRole><br/>
    </div>
    <div>
        <h3>角色操作模块</h3>
        <shiro:hasRole name="admin"><a href="logout">退出当前账号</a></shiro:hasRole><br/>
        <a href="findRoleById">查询权限</a><br/>
        <a href="updateRoleById">修改权限</a><br/>
        <a href="deleteRoleById">删除账号权限</a><br/>
        <a href="other">其他权限</a><br/>
    </div>
</body>
</html>