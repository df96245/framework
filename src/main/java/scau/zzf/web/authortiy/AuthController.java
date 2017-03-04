package scau.zzf.web.authortiy;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import scau.zzf.base.common.BaseController;
import scau.zzf.dictionary.Code;
import scau.zzf.entity.Message;
import scau.zzf.entity.authority.MessageRole;
import scau.zzf.entity.authority.Permission;
import scau.zzf.entity.authority.Role;
import scau.zzf.service.authority.IMessageRoleService;
import scau.zzf.service.authority.IPermissionService;
import scau.zzf.service.authority.IRoleService;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by zzf on 2017/2/27.
 */
@RestController
@RequestMapping(value = "/auth",produces = "application/json")
public class AuthController extends BaseController {

    @Autowired
    private IRoleService iRoleService;
    @Autowired
    private IMessageRoleService iMessageRoleService;
    @Autowired
    private IPermissionService iPermissionService;

    //SUPER_ADMIN角色才能够访问该路径
//    @RequiresRoles("SUPER_ADMIN")
    @PostMapping("/add_role")
    public Map addRole(Role role) {
        Map map = new LinkedHashMap<>();
        iRoleService.add(role);
        sendCode(map, Code.OPERATION_SUCCESS);
        return map;
    }



    @PostMapping("/add_perm")
    @RequiresPermissions("system:*")
    public Map addPerm(Permission permission) {
        Map map = new LinkedHashMap<>();
        iPermissionService.add(permission);
        sendCode(map, Code.OPERATION_SUCCESS);
        return map;
    }

    /**
     * 授予某个角色给指定用户
     * @param messageRole username ，roleName, roleId
     * @return
     */
    @PostMapping("/give_role")
    @RequiresPermissions("system:*")
    public Map givRole(MessageRole messageRole) {
        Map map = new LinkedHashMap<>();
        iMessageRoleService.add(messageRole);
        sendCode(map, Code.OPERATION_SUCCESS);
        return map;
    }
}
