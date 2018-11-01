package com.lin.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class User {
	private Integer id;
	private String name;
	private Integer age;
	private Date birthday;
	private String password;
	private Set<Role> roleSet = new HashSet<Role>();

	public Set<Role> getRoleSet() {
		return roleSet;
	}

	public void setRoleSet(Set<Role> roleSet) {
		this.roleSet = roleSet;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public String toString() {
		return this.name + ", " + this.age + ", " + this.birthday + ", " + this.password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}