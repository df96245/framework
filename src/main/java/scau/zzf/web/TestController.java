package scau.zzf.web;

import com.alibaba.fastjson.JSON;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import scau.zzf.base.common.BaseController;
import scau.zzf.base.common.IBaseService;
import scau.zzf.dictionary.Code;
import scau.zzf.entity.Message;
import scau.zzf.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;

/**
 * Created by zzf on 2016/12/4.
 */
//@RestController
@RestController
@RequestMapping(value = "test", produces = "application/json")
public class TestController extends BaseController {

    @GetMapping("/1")
    public String testAuthc() {
        return "test";
    }

    @GetMapping("get_username")
    public String getUsername() {
      return (String) SecurityUtils.getSubject().getPrincipal();
    }
}

