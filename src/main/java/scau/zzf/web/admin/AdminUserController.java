package scau.zzf.web.admin;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import scau.zzf.base.common.BaseController;
import scau.zzf.dictionary.Code;
import scau.zzf.entity.User;
import scau.zzf.service.IUserService;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zzf on 2017/3/2.
 */
@RestController
@RequestMapping(value = "/admin/user",produces = "application/json")
public class AdminUserController extends BaseController {

    @Autowired
    private IUserService iUserService;

    @GetMapping("/show_all")
    @RequiresPermissions(value = {"system:*","user:admin:*"},logical = Logical.OR)
    public Map showAll() {
        Map map = new LinkedHashMap<>();
        List<User> userList = iUserService.findAll();
        map.put("userList", userList);
        sendCode(map, Code.OPERATION_SUCCESS);
        return map;
    }
}
