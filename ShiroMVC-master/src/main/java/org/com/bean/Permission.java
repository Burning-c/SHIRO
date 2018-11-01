package org.com.bean;

/**
 * Created by Administrator on 2018/4/18.
 */
public class Permission {
    private Integer id;
    private String roleName;
    private String model;
    private String permission;
    private String resources_type;
    private String url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getResources_type() {
        return resources_type;
    }

    public void setResources_type(String resources_type) {
        this.resources_type = resources_type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
