package com.lin.domain;

import java.util.HashSet;
import java.util.Set;

public class Role {
	private Integer id;

	private String code;

	private String name;

	private Set<Permission> permissionSet = new HashSet<Permission>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code == null ? null : code.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public Set<Permission> getPermissionSet() {
		return permissionSet;
	}

	public void setPermissionSet(Set<Permission> permissionSet) {
		this.permissionSet = permissionSet;
	}

}