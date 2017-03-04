package scau.zzf.web.login;

import com.alibaba.fastjson.JSON;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import scau.zzf.base.common.BaseController;
import scau.zzf.dictionary.Code;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by zzf on 2017/2/26.
 */
@Controller
public class LoginController extends BaseController {
    @GetMapping("login")
    public String showLogin() {
        return "test/login";
    }
    @PostMapping(value = "/login",produces = "application/json")
    @ResponseBody
    public Map checkLogin(String username, String password)  {
        Map model = new LinkedHashMap<>();
        logger.entry(username,password);
        logger.info("======进入登陆======");
        SecurityUtils.getSecurityManager().logout(SecurityUtils.getSubject());
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        org.apache.shiro.subject.Subject subject = SecurityUtils.getSubject();
        token.setRememberMe(true);
        try {
            subject.login(token);
        } catch (UnknownAccountException ex) {
            logger.catching(ex);
            sendCode(model, Code.USER_NO_EXIST);
            return model;
        } catch (IncorrectCredentialsException ex) {
            logger.catching(ex);
            sendCode(model, Code.INCORRECT);
            return model;
//        } catch (Exception e) {
//            logger.error("未知异常");
//            return JSON.toJSONString("未知异常");
        }
        sendCode(model,Code.OPERATION_SUCCESS);
        return model;
    }

    @PostMapping(value = "/logout",produces = "application/json")
    @ResponseBody
    public Map logout() {
        Map model = new LinkedHashMap<>();
        Subject subject=SecurityUtils.getSubject();
        subject.logout();
        sendCode(model, Code.OPERATION_SUCCESS);
        return model;
    }
}
