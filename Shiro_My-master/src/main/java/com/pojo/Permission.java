package com.pojo;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("permission")
@Scope("prototype")
public class Permission {
	private Integer permissionid;

	private String name;

	private List<Mapping_RP> mapping_RP;

	public List<Mapping_RP> getMapping_RP() {
		return mapping_RP;
	}

	public void setMapping_RP(List<Mapping_RP> mapping_RP) {
		this.mapping_RP = mapping_RP;
	}

	public Integer getPermissionid() {
		return permissionid;
	}

	public void setPermissionid(Integer permissionid) {
		this.permissionid = permissionid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	@Override
	public String toString() {
		return "Permission [permissionid=" + permissionid + ", name=" + name + ", mapping_RP=" + mapping_RP + "]";
	}
	
}