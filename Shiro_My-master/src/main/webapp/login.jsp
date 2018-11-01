<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>登入页</title>
<script type="text/javascript" src="js/jquery-3.1.1.js"></script>
<script type="text/javascript" src="js/login.js"></script>
<link rel="shortcut icon" href="icon/ali.gif" type="image/gif" />

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<form action = "login" method="post">
		<table align="center">
			<tr align="center">
				<td colspan="3"><h2>登入</h2></td>
				<td />
			</tr>
			<tr>
				<td>用户名：</td>
				<td colspan="2"><input id="username" type="text"
					name="username" /></td>
				<td width="120" id="result" />
			</tr>
			<tr>
				<td align="right">密码：</td>
				<td colspan="2"><input id="password" type="password"
					name="password" /></td>
				<td></td>
			</tr>
			<tr>
				<td />
				<td align="left"><input type="checkbox" name="RM">记住我</td>
				<td colspan="1" align="right"><input type="button"
					value="login" id="login" /></td>
				<td />
			</tr>
		</table>
	</form>
</body>
</html>
