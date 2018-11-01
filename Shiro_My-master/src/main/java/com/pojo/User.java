package com.pojo;

import java.io.Serializable;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@SuppressWarnings("serial")
@Component("user")
@Scope("prototype")
public class User implements Serializable{
	private Integer userid;

	private String username;

	private String password;

	private List<Mapping_UR> mapping_UR;

	public List<Mapping_UR> getMapping_UR() {
		return mapping_UR;
	}

	public void setMapping_UR(List<Mapping_UR> mapping_UR) {
		this.mapping_UR = mapping_UR;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	@Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username + ", password=" + password + ", mapping_UR="
				+ mapping_UR + "]";
	}
	
}