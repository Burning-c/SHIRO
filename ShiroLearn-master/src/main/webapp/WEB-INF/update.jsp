<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="${pageContext.request.contextPath}/update" method="post">
		
		<table border="1px" cellspacing="0px" cellpadding="0" align="center">
		<tr>
			<th>编号</th>
			<th>名称</th>
			<th>密码</th>
			<th>年龄</th>
		</tr>
			<tr>
		     	<td><input type="text" name="userId" value="${user.id}" /></td>
		     	<td><input type="text" name="userName" value="${user.name}" /></td>
		     	<td><input type="text" name="password" value="${user.password}" /></td>
		     	<td><input type="text" name="age" value="${user.age}" /></td>
			</tr>
		</table>
		<input type="submit" value="修改" />
	</form>

</body>
</html>