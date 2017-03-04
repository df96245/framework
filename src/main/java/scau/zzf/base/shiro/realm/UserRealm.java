package scau.zzf.base.shiro.realm;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import scau.zzf.base.common.IBaseService;
import scau.zzf.entity.Message;
import scau.zzf.entity.User;
import scau.zzf.entity.authority.MessageRole;
import scau.zzf.entity.authority.RolePermission;
import scau.zzf.service.IMessageService;
import scau.zzf.service.IUserService;
import scau.zzf.service.authority.IMessageRoleService;
import scau.zzf.service.authority.IRolePermissionService;

import java.util.*;

/**
 * Created by Administrator on 2016/9/7.
 * Realm：域，Shiro从从Realm获取安全数据（如用户、角色、权限），就是说SecurityManager要验证用户身份，
 * 那么它需要从Realm获取相应的用户进行比较以确定用户身份是否合法；
 * 也需要从Realm得到用户相应的角色/权限进行验证用户是否能进行操作；
 * 可以把Realm看成DataSource，即安全数据源。如我们之前的ini配置方式将使用org.apache.shiro.realm.text.IniRealm。
 */

public class UserRealm extends AuthorizingRealm {
    private static Logger logger = LogManager.getLogger(UserRealm.class);

    @Autowired
    private IUserService userService;
    @Autowired
    private IMessageService iMessageService;
    @Autowired
    private IRolePermissionService iRolePermissionService;
    @Autowired
    private IMessageRoleService iMessageRoleService;
    /**
     * 授权
     **/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录时输入的用户名
        String loginName = (String) principalCollection.getPrimaryPrincipal();
        logger.info("==================开始授权 ==================");

//            List<Message> messageList = iMessageService.findSelectProperty("username",loginName,"id");

            Set<String> roleSet = new HashSet<>();
            //权限信息对象info，用于存放查出的用户的所有的角色（role）和权限（authority）
            SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

            //找到用户对应的角色
            List<MessageRole> messageRoles = iMessageRoleService.findSelectProperty("username", loginName, "roleName", "roleId");
            List<String> roleIdList = new ArrayList<>();
            for (MessageRole mr :
                    messageRoles) {
                roleSet.add(mr.getRoleName());
                roleIdList.add(mr.getRoleId());
            }
            if (!roleIdList.isEmpty()) {
                Set rolePermSet = new HashSet<>();
                //找到用户对应角色的权限
                List<RolePermission> rolePermissions=iRolePermissionService.findByProperties(roleIdList, "roleId");
                for (RolePermission rp :
                        rolePermissions) {
                    rolePermSet.add(rp.getPermName());
                }
                authorizationInfo.addStringPermissions(rolePermSet);
            }

            authorizationInfo.setRoles(roleSet);

            return authorizationInfo;

    }

    /**
     * 认证
     **/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        //查出是否有此用户
        List<User> userList = userService.findSelectProperty("username", username, "username", "password", "salt");

        if (userList.isEmpty()) {
                throw new UnknownAccountException();//没找到帐号
            }
            logger.info("==================开始认证==================");

        User user = userList.get(0);
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以在此判断或自定义实现
        return new SimpleAuthenticationInfo(
                user.getUsername(), //用户名
                user.getPassword(), //密码
                ByteSource.Util.bytes(user.getCredentialsSalt()),//salt=username+salt
                getName()  //realm name
        );
    }

    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }

}
