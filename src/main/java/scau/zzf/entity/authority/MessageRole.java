package scau.zzf.entity.authority;

import scau.zzf.base.common.Unique;

/**
 * Created by zzf on 2017/2/26.
 */
public class MessageRole extends Unique {
    private String username;
    private String roleId;
    private String roleName;

    public MessageRole() {

    }
    public MessageRole(String username, String roleId, String roleName) {
        this.username = username;
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
