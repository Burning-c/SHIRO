
var list = [];
//由addUser()更新
var checkboxs = "";

$(document).ready(function() {
	$("#A").click(function() {
		if ($("#a").is(":hidden")) {
			$("#a").show();
		} else {
			$("#a").hide();
		}
	}),
	$("#B").click(function() {
		if ($("#b").is(":hidden")) {
			$("#b").show();
		} else {
			$("#b").hide();
		}
	}),
	$("#C").click(function() {
		if ($("#c").is(":hidden")) {
			$("#c").show();
		} else {
			$("#c").hide();
		}
	}),
	$("#t_add").click(function() {
		var json = {
			"username" : $("#username").val(),
			"password" : $("#password").val(),
			"mapping_UR" : []
		};
		$('input[name="roles"]:checked').each(function() {
			json.mapping_UR.push({
				'role' : {
					'roleid' : $(this).val()
				}
			});
		});
		$.ajax({
			url : 'addUser',
			type : "POST",
			data : JSON.stringify(json),
			dataType : 'json',
			contentType : 'application/json;charset=gbk',
			success : function(info) {
				if (info.message == null) {
					$("#username").val("");
					$("#password").val("");
					$("[name=roles]:checkbox").prop("checked", false);
					alert("add success!");
				} else if (info.message.indexOf("Unauthorized") != -1) {
					alert(info.message);
				}
			}
		});
	}),
	$("#role_add").click(function() {
		var json = {
			"name" : $("#rolename").val(),
			"mapping_RP" : []
		};
		$('input[name="permissions"]:checked').each(function() {
			json.mapping_RP.push({
				'permission' : {
					'permissionid' : $(this).val()
				}
			});
		});
		$.ajax({
			url : 'addRole',
			type : "POST",
			data : JSON.stringify(json),
			dataType : 'json',
			contentType : 'application/json;charset=gbk',
			success : function(info) {
				if (info.message == null) {
					$("#rolename").val("");
					$("[name=permissions]:checkbox").prop("checked", false);
					alert("add success!");
				} else if (info.message.indexOf("Unauthorized") != -1) {
					alert(info.message);
				}
			}
		});
	}),
	$("#permission_add").click(function() {
		var json = {
			"name" : $("#permissionname").val(),
		};
		$.ajax({
			url : 'addPermission',
			type : "POST",
			data : JSON.stringify(json),
			dataType : 'json',
			contentType : 'application/json;charset=gbk',
			success : function(info) {
				if (info.message == null) {
					$("#permissionname").val("");
					alert("add success!");
				} else if (info.message.indexOf("Unauthorized") != -1) {
					alert(info.message);
				}
			}
		});
	})
});

function getAll() {
	$.ajax({
		url : 'allUsers',
		type : "get",
		success : function(info) {
			if (info.message == null) {
				hid();
				$("#div_update").show();
				for (var index in list) {
					$("#" + list[index]).remove();
				}
				list = [];
				for (var i = 0; i < info.length; i++) {
					var roles = "[ ";
					for (var j = 0; j < info[i].mapping_UR.length; j++) {
						roles += info[i].mapping_UR[j].role.name;
						if (j != info[i].mapping_UR.length - 1) {
							roles += ",";
						}
					}
					roles += " ]";
					list.push(info[i].userid);
					$("#t_allUser").append("<tr id=" + info[i].userid + " align='center'><td>" + info[i].userid + "</td><td>" + info[i].username + "</td><td>" + roles + "</td><td><input type='button' value='udt' onclick='javascript:B_udt(" + info[i].userid + ")' />&nbsp;<input type='button' value='del' onclick='javascript:del(" + info[i].userid + ")' /></td></tr>");
				}
			} else {
				alert(info.message);
			}
		}
	});
}

function del(value) {
	$.ajax({
		url : 'deleteUser/' + value,
		type : "DELETE",
		success : function(info) {
			if (info.message.indexOf("delete success") != -1) {
				$("#" + value).remove();
			} else if (info.message.indexOf("delete failed") != -1) {
				alert("delete failed!");
			} else if (info.message.indexOf("Unauthorized") != -1) {
				alert("未授权!");
			}
		}
	});
}

function B_udt(value) {
	for (var index in list) {
		if (value != list[index])
			$("#" + list[index]).remove();
	}
	list.push(value + "1");
	list.push(value + "2");
	list.push(value + "3");
	$("#t_allUser").append("<tr id=" + value + "1" + "><td align='center' colspan='2'>" +
		"Name:</td><td colspan='2' align='left'><input type='text' name='username' id='Reusername' /></td></tr><tr id=" + value + "2" + "><td colspan='2' align='center'>" +
		"Roles:</td><td align='left' colspan='2'>" + checkboxs + "</td></tr>" +
		"<tr align='right' id=" + value + "3" + "><td colspan='4'>" +
		"<input type='button' value='back' onclick='getAll()'/>&nbsp;<input type='button' value='yes' onclick='udt(" + value + ")'/>&nbsp;<td></tr>"
	);

}

function udt(value) {
	var userName = "";
	if ($("#Reusername").val() != "") {
		userName = $("#Reusername").val();
	} else {
		userName = $("#" + value).children('td').eq(1).html();
	}
	var json = {
		"userid" : parseInt(value),
		"username" : userName,
		"mapping_UR" : []
	};
	$('input[name="roles"]:checked').each(function() {
		json.mapping_UR.push({
			'role' : {
				'roleid' : $(this).val()
			}
		});
	});
	$.ajax({
		url : 'updateUser/' + value,
		type : "PUT",
		data : JSON.stringify(json),
		dataType : 'json',
		contentType : 'application/json;charset=gbk',
		success : function(info) {
			if (info.message == null) {
				$("#Reusername").val("");
				$("[name=roles]:checkbox").prop("checked", false);
				getAll();
			} else if (info.message.indexOf("Unauthorized") != -1) {
				alert("未授权!");
			}
		}
	});

}

//获取roles
function addUser() {
	checkboxs = "";
	$.ajax({
		url : 'allRoles',
		type : "GET",
		success : function(info) {
			if (info != null) {
				for (var i in info) {
					checkboxs += "<input type='checkbox' name='roles' value=" + info[i].roleid + " /> " + info[i].name;
				}
				$("#roles").html(checkboxs);
			}
		}
	});
	hid();
	$("#div_add").show();
}

function getAllRoles() {
	$.ajax({
		url : 'allRoles',
		type : "get",
		success : function(info) {
			if (info.message == null) {
				hid();
				$("#div_updaterole").show();
				for (var index in list) {
					$("#" + list[index]).remove();
				}
				list = [];
				for (var i = 0; i < info.length; i++) {
					var permissions = "[ ";
					for (var j = 0; j < info[i].mapping_RP.length; j++) {
						permissions += info[i].mapping_RP[j].permission.name;
						if (j != info[i].mapping_RP.length - 1) {
							permissions += ",";
						}
					}
					permissions += " ]";
					list.push(info[i].roleid);
					$("#t_allRole").append("<tr id=" + info[i].roleid + " align='center'><td>" + info[i].roleid + "</td><td>" + info[i].name + "</td><td>" + permissions + "</td><td><input type='button' value='udt' onclick='javascript:B_udtRole(" + info[i].roleid + ")' />&nbsp;<input type='button' value='del' onclick='javascript:delRole(" + info[i].roleid + ")' /></td></tr>");
				}
			} else {
				alert(info.message);
			}
		}
	});
}

function delRole(value) {
	$.ajax({
		url : 'deleteRole/' + value,
		type : "DELETE",
		success : function(info) {
			if (info.message.indexOf("success") != -1) {
				$("#" + value).remove();
			} else if (info.message.indexOf("Unauthorized") != -1) {
				alert("未授权!");
			}
		}
	});
}

function B_udtRole(value) {
	for (var index in list) {
		if (value != list[index])
			$("#" + list[index]).remove();
	}
	list.push(value + "1");
	list.push(value + "2");
	list.push(value + "3");
	$("#t_allRole").append("<tr id=" + value + "1" + "><td align='center' colspan='2'>" +
		"Name:</td><td colspan='2' align='left'><input type='text' name='rolename' id='Rerolename' /></td></tr><tr id=" + value + "2" + "><td colspan='2' align='center'>" +
		"Permissions:</td><td align='left' colspan='2'>" + checkboxs + "</td></tr>" +
		"<tr align='right' id=" + value + "3" + "><td colspan='4'>" +
		"<input type='button' value='back' onclick='getAllRoles()'/>&nbsp;<input type='button' value='yes' onclick='udtRole(" + value + ")'/>&nbsp;<td></tr>"
	);

}

function udtRole(value) {
	var roleName = "";
	if ($("#Rerolename").val() != "") {
		roleName = $("#Rerolename").val();
	} else {
		roleName = $("#" + value).children('td').eq(1).html();
	}
	var json = {
		"roleid" : parseInt(value),
		"name" : roleName,
		"mapping_RP" : []
	};
	$('input[name="permissions"]:checked').each(function() {
		json.mapping_RP.push({
			'permission' : {
				'permissionid' : $(this).val()
			}
		});
	});
	$.ajax({
		url : 'updateRole/' + value,
		type : "PUT",
		data : JSON.stringify(json),
		dataType : 'json',
		contentType : 'application/json;charset=gbk',
		success : function(info) {
			if (info.message == null) {
				$("#Rerolename").val("");
				$("[name=permissions]:checkbox").prop("checked", false);
				getAllRoles();
			} else if (info.message.indexOf("Unauthorized") != -1) {
				alert("未授权!");
			}
		}
	});

}

//获取roles
function addRole() {
	checkboxs = "";
	$.ajax({
		url : 'allPermissions',
		type : "GET",
		success : function(info) {
			if (info != null) {
				for (var i in info) {
					checkboxs += "<input type='checkbox' name='permissions' value=" + info[i].permissionid + " /> " + info[i].name;
				}
				$("#permission").html(checkboxs);
			}
		}
	});
	hid();
	$("#div_role").show();
}

function delPermission(value) {
	$.ajax({
		url : 'deletePermission/' + value,
		type : "DELETE",
		success : function(info) {
			if (info.message.indexOf("success") != -1) {
				$("#" + value).remove();
			} else if (info.message.indexOf("Unauthorized") != -1) {
				alert("未授权!");
			}
		}
	});
}

function B_udtPermission(value) {
	for (var index in list) {
		if (value != list[index])
			$("#" + list[index]).remove();
	}
	list.push(value + "1");
	list.push(value + "2");
	$("#t_allPermission").append("<tr id=" + value + "1" + "><td align='center' colspan='1'>" +
		"Name:</td><td colspan='2' align='left'><input type='text' name='permissionname' id='Repermissionname' /></td></tr>" +
		"<tr align='right' id=" + value + "2" + "><td colspan='3'>" +
		"<input type='button' value='back' onclick='getAllPermissions()'/>&nbsp;<input type='button' value='yes' onclick='udtPermission(" + value + ")'/>&nbsp;<td></tr>"
	);

}

function udtPermission(value) {
	var permissionName = "";
	if ($("#Repermissionname").val() != "") {
		permissionName = $("#Repermissionname").val();
	} else {
		permissionName = $("#" + value).children('td').eq(1).html();
	}
	var json = {
		"permissionid" : parseInt(value),
		"name" : permissionName,
	};
	$.ajax({
		url : 'updatePermission',
		type : "PUT",
		data : JSON.stringify(json),
		dataType : 'json',
		contentType : 'application/json;charset=gbk',
		success : function(info) {
			if (info.message == null) {
				$("#Repermissionname").val("");
				getAllPermissions();
			} else if (info.message.indexOf("Unauthorized") != -1) {
				alert("未授权!");
			}
		}
	});

}

function getAllPermissions() {
	$.ajax({
		url : 'allPermissions',
		type : "get",
		success : function(info) {
			if (info.message == null) {
				hid();
				$("#div_updatepermission").show();
				for (var index in list) {
					$("#" + list[index]).remove();
				}
				list = [];
				for (var i = 0; i < info.length; i++) {
					list.push(info[i].permissionid);
					$("#t_allPermission").append("<tr id=" + info[i].permissionid + " align='center'><td>" + info[i].permissionid + "</td><td>" + info[i].name + "</td><td><input type='button' value='udt' onclick='javascript:B_udtPermission(" + info[i].permissionid + ")' />&nbsp;<input type='button' value='del' onclick='javascript:delPermission(" + info[i].permissionid + ")' /></td></tr>");
				}
			} else {
				alert(info.message);
			}
		}
	});
}

function addPermission() {
	hid();
	$("#div_permission").show();
}

function hid() {
	$("#div_add").hide();
	$("#div_update").hide();
	$("#div_role").hide();
	$("#div_updaterole").hide();
	$("#div_permission").hide();
	$("#div_updatepermission").hide();
}