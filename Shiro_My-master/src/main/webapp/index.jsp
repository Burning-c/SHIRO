<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>管理系统</title>
<script type="text/javascript" src="js/jquery-3.1.1.js"></script>
<script type="text/javascript" src="js/index.js"></script>
<link rel="shortcut icon" href="icon/ali.gif" type="image/gif" />

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<style type="text/css">
a {
	outline-style: none;
	color: #535353;
	text-decoration: none
}
</style>
</head>

<body onload="addUser()">
	<div>
		<table>
			<tr>
				<td align="right"><h3>当前用户:</h3></td>
				<td><h3 id="user">
						<shiro:principal />
					</h3></td>
			</tr>
			<tr>
				<td colspan="2" width="150">
					<ul>
						<li><a href="javascript:;" id="A">用户管理</a></li>
						<ul id="a" style="display:none">
							<li><a href="javascript:addUser()">添加用户</a></li>
							<li><a href="javascript:getAll()">修改用户</a></li>
						</ul>
						<li><a href="javascript:;" id="B">角色管理</a></li>
						<ul id="b" style="display:none">
							<li><a href="javascript:addRole()">添加角色</a></li>
							<li><a href="javascript:getAllRoles()">修改角色</a></li>
						</ul>
						<li><a href="javascript:;" id="C">权限管理</a></li>
						<ul id="c" style="display:none">
							<li><a href="javascript:addPermission()">添加权限</a></li>
							<li><a href="javascript:getAllPermissions()">修改角色</a></li>
						</ul>
						<li><a href="logout">Exit</a></li>
					</ul>
				</td>
			</tr>
		</table>
	</div>
	<div id="div_add">
		<form style="position: absolute;top: 100;left: 500">
			<table align="center" width="300">
				<tr>
					<td colspan="2" align="center"><h1>添加</h1>
						<hr></td>
				</tr>
				<tr>
					<td align="right">姓名:</td>
					<td align="center"><input type="text" name="username"
						id="username" /></td>
				</tr>
				<tr>
					<td align="right">密码:</td>
					<td align="center"><input type="password" name="password"
						id="password" /></td>
				</tr>
				<shiro:hasRole name="admin">
					<tr>
						<td align="right">角色:</td>
						<td id="roles" align="center"></td>
					</tr>
				</shiro:hasRole>
				<tr>
					<td></td>
					<td align="right"><input type="button" value="提 交" id="t_add" /></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="div_update" style=display:none>
		<form action="addUser" method="post">
			<table align="center" style="position: absolute;top: 100;left: 500"
				border="1" bordercolor="black" cellspacing="0" id="t_allUser">
				<tr>
					<td colspan="4" align="center"><h1>查询</h1></td>
				</tr>
				<tr align="center" id="last">
					<td width="40">ID</td>
					<td width="60">姓名</td>
					<td width="120">权限</td>
					<td width="90">操作</td>
				</tr>
			</table>
		</form>
	</div>
	<div id="div_role" style=display:none>
		<form style="position: absolute;top: 100;left: 500">
			<table align="center" width="300">
				<tr>
					<td colspan="2" align="center"><h1>添加</h1>
						<hr></td>
				</tr>
				<tr>
					<td align="right">角色:</td>
					<td align="center"><input type="text" name="rolename"
						id="rolename" /></td>
				</tr>
				<shiro:hasRole name="admin">
					<tr>
						<td align="right">权限:</td>
						<td id="permission" align="center"></td>
					</tr>
				</shiro:hasRole>
				<tr>
					<td></td>
					<td align="right"><input type="button" value="提 交"
						id="role_add" /></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="div_updaterole" style=display:none>
		<form action="addRole" method="post">
			<table align="center" style="position: absolute;top: 100;left: 500"
				border="1" bordercolor="black" cellspacing="0" id="t_allRole">
				<tr>
					<td colspan="4" align="center"><h1>查询</h1></td>
				</tr>
				<tr align="center" id="last">
					<td width="40">ID</td>
					<td width="60">角色</td>
					<td width="120">权限</td>
					<td width="90">操作</td>
				</tr>
			</table>
		</form>
	</div>
	<div id="div_permission" style=display:none>
		<form style="position: absolute;top: 100;left: 500">
			<table align="center" width="300">
				<tr>
					<td colspan="2" align="center"><h1>添加</h1>
						<hr></td>
				</tr>
				<tr>
					<td align="right">权限:</td>
					<td align="center"><input type="text" name="permissionname"
						id="permissionname" /></td>
				</tr>
				<tr>
					<td></td>
					<td align="right"><input type="button" value="提 交"
						id="permission_add" /></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="div_updatepermission" style=display:none>
		<form action="addPermission" method="post">
			<table align="center" style="position: absolute;top: 100;left: 500"
				border="1" bordercolor="black" cellspacing="0" id="t_allPermission">
				<tr>
					<td colspan="3" align="center"><h1>查询</h1></td>
				</tr>
				<tr align="center" id="last">
					<td width="40">ID</td>
					<td width="120">权限</td>
					<td width="90">操作</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
