package com.pojo;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("role")
@Scope("prototype")
public class Role {
	private Integer roleid;

	private String name;

	private List<Mapping_UR> mapping_UR;

	private List<Mapping_RP> mapping_RP;

	public List<Mapping_RP> getMapping_RP() {
		return mapping_RP;
	}

	public void setMapping_RP(List<Mapping_RP> mapping_RP) {
		this.mapping_RP = mapping_RP;
	}

	public List<Mapping_UR> getMapping_UR() {
		return mapping_UR;
	}

	public void setMapping_UR(List<Mapping_UR> mapping_UR) {
		this.mapping_UR = mapping_UR;
	}

	public Integer getRoleid() {
		return roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	@Override
	public String toString() {
		return "Role [roleid=" + roleid + ", name=" + name + ", mapping_UR=" + mapping_UR + ", mapping_RP=" + mapping_RP
				+ "]";
	}
	
}