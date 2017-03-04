import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import scau.zzf.base.common.IBaseService;

import scau.zzf.base.util.PasswordHelper;
import scau.zzf.entity.Message;
import scau.zzf.entity.User;
import scau.zzf.entity.authority.MessageRole;
import scau.zzf.entity.authority.RolePermission;
import scau.zzf.service.IUserService;
import scau.zzf.service.authority.IRolePermissionService;

import java.util.List;


/**
 * Created by Administrator on 2016/9/6.
 */

public class UserServiceTest extends BaseTest {
    private static Logger logger = LogManager.getLogger(UserServiceTest.class);


    @Autowired
    private IBaseService<User> userService;

    @Test
    public void add() {
        User user = new User("superadmin", "superadmin");
        PasswordHelper.encryptPassword(user);
        userService.add(user);
    }

    @Autowired
    private IBaseService<MessageRole> messageRoleIBaseService;
    @Test
    public void findRoleWithUser() {
        List<MessageRole> messageRoles = messageRoleIBaseService.findSelectProperty("username", "superadmin", "roleName", "roleId");
    }

    @Autowired
    private IUserService iUserService;

    @Test
    public void addUser() {
        User user = new User("zzf", "zzf");
        PasswordHelper.encryptPassword(user);
        Message message = new Message();
        message.setUsername("zzf");
        message.setEmail("zzf@163.com");
        iUserService.add(user,message);
    }

    @Test
    public void testLogin() {
        List<User> userList = userService.findSelectProperty("username", "superadmin", "username", "password", "salt");
    }

    @Autowired
    private IRolePermissionService iRolePermissionService;
    @Test
    public void addRolePermission() {
        RolePermission rolePermission = new RolePermission("6623c146ff0811e68a5380fa5b1432fc", "6e23c7acff4d11e68a5380fa5b1432fc", "user:admin:*");
        iRolePermissionService.add(rolePermission);
    }
}
