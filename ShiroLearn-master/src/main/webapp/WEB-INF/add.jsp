<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 5.01 Transitional//EN" "http://www.w3.org/TR/html5/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="${pageContext.request.contextPath }/add" method="post">
		
		<table border="1px" cellspacing="0px" cellpadding="0" align="center">
		<tr>
			<th>名称</th>
			<th>密码</th>
			<th>年龄</th>
			<th>生日</th>
		</tr>
			<tr>
		     	<td><input type="text" name="name" /></td>
		     	<td><input type="text" name="password" /></td>
		     	<td><input type="text" name="age" /></td>
		     	<!-- <input type="date" name="birthday"/></td> --><td>
			</tr>
		</table>
		<input type="submit" value="添加" />
	</form>

</body>
</html>