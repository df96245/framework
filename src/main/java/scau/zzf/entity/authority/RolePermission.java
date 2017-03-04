package scau.zzf.entity.authority;

import scau.zzf.base.common.Unique;

/**
 * Created by zzf on 2017/2/26.
 */
public class RolePermission  extends Unique {
    private String roleId;
    private String permissionId;
    private String permName;

    public RolePermission() {

    }

    public RolePermission(String roleId, String permissionId, String permName) {
        this.roleId = roleId;
        this.permissionId = permissionId;
        this.permName = permName;
    }

    public String getPermName() {
        return permName;
    }

    public void setPermName(String permName) {
        this.permName = permName;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }
}
