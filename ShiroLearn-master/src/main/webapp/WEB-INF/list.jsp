<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--JS文件-->
<script src="static/js/jquery-1.11.2.min.js"></script>
<title>Insert title here</title>

<script type="text/javascript">

			/*  全选    */
		    function selectAll(){
		        if ($("#SelectAll").is(":checked")) {
		            $(":checkbox").prop("checked", true);//所有选择框都选中
		        } else {
		            $(":checkbox").prop("checked", false);
		        }
		    }
			
		    /*  注销   */
		    function exitUser(){
		        if ($("#SelectAll").is(":checked")) {
		            $(":checkbox").prop("checked", true);//所有选择框都选中
		        } else {
		            $(":checkbox").prop("checked", false);
		        }
		    }
			
			/* 删除   */
			function deleteUser(userId){
				if(confirm("真的要删除这本通讯录吗？")){
					$("#form1").attr("action","${pageContext.request.contextPath}/delete?userId="+userId);
					$("#form1").submit();	
				}
			}	
			/*   批量删除   */
       		function batchDelete(){
				if(confirm("真的要删除这本通讯录吗？")){
				$("#form1").attr("action","${pageContext.request.contextPath}/batchDelete");
				$("#form1").submit();	
				}
			}
		    var currentPage=${pages.currentPage};
		    var totalPages=${pages.totalPages};
		 
		    //上一页
			$("#upPage").click(function(){
				
				  var filedName="${filedName}";
				  var search="${search}";
				  
				  if(currentPage==1){
							
						}else{
							
							$("#upPage").attr("href","${pageContext.request.contextPath}/list?page="+(currentPage-1)+"&filedName="+filedName+"&search="+search);	
						}
						
					});
			    
				 //下一页
			$("#nextPage").click(function(){
				
				  var filedName="${filedName}";
				  var search="${search}";
				
					if(currentPage==totalPages){
						
					}else{
						
						$("#nextPage").attr("href","${pageContext.request.contextPath}/list?page="+(currentPage+1)+"&filedName="+filedName+"&search="+search);
						
					}
					
				});
			 //首页
			$("#head").click(function(){
				 var filedName="${filedName}";
				  var search="${search}";
					if(currentPage==1){
					}else{
						$("#head").attr("href","${pageContext.request.contextPath}/list?page=1"+"&filedName="+filedName+"&search="+search);
					}
				});
				 
			//末页
			$("#last").click(function(){
				
				 var filedName="${filedName}";
				  var search="${search}";
				  
				if(currentPage==totalPages){
					
				}else{
					
					$("#last").attr("href","${pageContext.request.contextPath}/list?page="+totalPages+"&filedName="+filedName+"&search="+search);
				}
					
				});
	
	</script>

</head>
<body>

	<div>
	权限添加：
	
		<shiro:hasPermission name="user:delete">
      		<form action="${pageContext.request.contextPath}/addRole" id="form"
			method="POST">
				<select name="userId">
					<c:forEach items="${user}" var="u">
						<option value="${u.id}" >${u.name}</option>
					</c:forEach>
				</select>
				
				 <select name="role">
				 	<c:forEach items="${role}" var="u">
						<option value="${u.id}">${u.name}</option>
					</c:forEach>
				</select>
				<button type="text" id="search">添加权限</button>
			</form>
   		</shiro:hasPermission>
	
	
		
	</div>
	
	
	<form id="form1" method="post">
		<table border="1px" cellspacing="0px" cellpadding="0">
			<tr>
				<th><input type="checkbox" id="SelectAll"
					onClick="selectAll();"></th>
				<th>编号</th>
				<th>名称</th>
				<th>密码</th>
				<th>年龄</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${user}" var="u">
				<tr>
					<td><input type="checkbox" id="subcheck" name="subcheck"
						value="${u.id }"></td>
					<td>${u.id}</td>
					<td>${u.name}</td>
					<td>${u.password}</td>
					<td>${u.age}</td>
					<td>
						<shiro:hasPermission name="user:delete">
      						 <a href="javascript:void(0)" onClick="deleteUser(${u.id})">删除</a>
   						</shiro:hasPermission>
   						
						<shiro:hasPermission name="user:update">
      						<a href="${pageContext.request.contextPath}/load?userId=${u.id}">修改</a>
   						</shiro:hasPermission>
						
						
					</td>
				</tr>
			</c:forEach>

		</table>
	</form>
	<!-- <a href="javascript:void(0)" onClick="batchDelete()">批量删除</a> -->s
	<div>
		<span class="fl pl10">共检测到${pages.count }条记录</span> <span class="pr10">
			<a id="head">首页</a> <a id="upPage">上一页</a> <c:forEach var="i"
				begin="1" end="${pages.totalPages }" step="1">
				<a href="${pageContext.request.contextPath}/list?page=${i}">${i}</a>
			</c:forEach> <a id="nextPage">下一页</a> <a id="last">尾页</a>
		</span>

	</div>
	<a href="${pageContext.request.contextPath}/add">添加用户</a>
	<a href="${pageContext.request.contextPath}/exitUser">注销账号</a>
</body>
</html>