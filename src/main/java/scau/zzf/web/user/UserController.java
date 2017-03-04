package scau.zzf.web.user;

import com.alibaba.fastjson.JSON;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import scau.zzf.base.IdWorker;
import scau.zzf.base.common.BaseController;
import scau.zzf.base.common.IBaseService;
import scau.zzf.base.util.PasswordHelper;
import scau.zzf.dictionary.Code;
import scau.zzf.entity.Message;
import scau.zzf.entity.User;
import scau.zzf.service.IUserService;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 用户登录 注册 等验证
 * Created by Sunlight on 2016/12/7.
 */


@RestController
@RequestMapping(value = "/user",produces = "application/json")
public class UserController extends BaseController {


    @Autowired
    private IBaseService<User> userIBaseService;
    @Autowired
    private IUserService iUserService;
    @PostMapping("/add")
    public Map addUser(User user, Message message) {
        Map map = new LinkedHashMap<>();
        PasswordHelper.encryptPassword(user);
        iUserService.add(user, message);
        sendCode(map, Code.OPERATION_SUCCESS);
        return map;
    }


}
