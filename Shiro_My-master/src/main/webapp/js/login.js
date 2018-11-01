$(document).ready(function() {
	$("#login").click(function() {
		var json = {
			"username" : $("#username").val(),
			"password" : $("#password").val(),
			"rememberme" : "false",
		};
		$('input[name="RM"]:checked').each(function() {
			json.rememberme = "true";
		});
		$.ajax({
			url : 'login',
			type : "POST",
			data : JSON.stringify(json), //转JSON字符串  
			dataType : 'json',
			contentType : 'application/json;charset=gbk',
			success : function(info) {
				$("#result").html(info.message);
				if (info.message.indexOf("login success") != -1) {
					window.location.href = "index.jsp";
				} else if (info.message.indexOf("Unauthorized") != -1) {
					alert("未授权!");
				}
			},
			error : function(info) {
				alert(JSON.stringify(info))
			}
		});
	});
});