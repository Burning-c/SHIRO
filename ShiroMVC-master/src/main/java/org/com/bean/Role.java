package org.com.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/17.
 */
public class Role {
    private Integer id;
    private Integer uid;
    private String description;
    private String role;
    private List<Permission> permissionss;

    public List<Permission> getPermissionss() {
        return permissionss;
    }

    public void setPermissionss(List<Permission> permissionss) {
        this.permissionss = permissionss;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
