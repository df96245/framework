package scau.zzf.base.common;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.http.auth.AuthenticationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import scau.zzf.dictionary.Code;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Validator;
import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by zzf on 2016/11/26.
 */
@CrossOrigin(origins = {"http://localhost:3000","http://apizza.cc"}, allowCredentials = "true")
public abstract class BaseController {
    protected Logger logger=LogManager.getLogger(getClass());


    /**
     * 验证Bean实例对象
     */
    @Autowired
    protected Validator validator;


    /**
     * 添加Model消息
     * @param messages
     */
    protected void addMessage(Model model, String... messages) {
        StringBuilder sb = new StringBuilder();
        for (String message : messages){
            sb.append(message).append(messages.length>1?"<br/>":"");
        }
        model.addAttribute("message", sb.toString());


    }

    protected void sendCode(Model model,Code code){
        model.addAttribute("statusCode",code.getStatusCode());
        model.addAttribute("statusMsg",code.getStatusMsg());
    }
    protected void sendCode(Map map, Code code){
        map.put("statusCode",code.getStatusCode());
        map.put("statusMsg",code.getStatusMsg());
    }

    /**重定向转发消息
     * 添加Flash消息
     * return "redirect:/.."
     * @param messages
     */
    protected void addMessage(RedirectAttributes redirectAttributes, String... messages) {
        StringBuilder sb = new StringBuilder();
        for (String message : messages){
            sb.append(message).append(messages.length>1?"<br/>":"");
        }
        redirectAttributes.addFlashAttribute("message", sb.toString());
    }

    /**
     * 客户端返回字符串
     * @param response
     * @param string
     * @return
     */
    protected String renderString(HttpServletResponse response, String string, String type) {
        try {
            response.reset();
            response.setContentType(type);
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
            return null;
        } catch (IOException e) {
            return null;
        }
    }
    @ExceptionHandler(value = {UnauthorizedException.class})
    @ResponseBody
    public Map catchUnauthorizedException(UnauthorizedException ex) {
        Map map = new LinkedHashMap<>();
        map.put("statusCode",Code.REJECT.getStatusCode());
        map.put("statusMsg",Code.REJECT.getStatusMsg());
        logger.catching(ex);
        return map;
    }
    @ExceptionHandler(value = {RuntimeException.class})
    @ResponseBody
    public Map catchRuntimeException(RuntimeException ex) {
        Map map = new LinkedHashMap<>();
        map.put("statusCode",Code.REJECT.getStatusCode());
        map.put("statusMsg","系统异常");
        logger.catching(ex);
        return map;
    }

    /**
     * 授权登录异常
      */
//    @ExceptionHandler({AuthenticationException.class})
//    @ResponseBody
//    public String authenticationException() {
//        return "error/403";
//    }
//    @ExceptionHandler({UnauthenticatedException.class})
//    @ResponseBody
//    public Map authenticationException() {
//        Map map = new LinkedHashMap<>();
//        map.put("statusCode", Code.REJECT.getStatusCode());
//        map.put("statusMsg", Code.REJECT.getStatusMsg());
//        return map;
//    }

    /**
     * 初始化数据绑定
     * 1. 将所有传递进来的String进行HTML编码，防止XSS攻击
     * 2. 将字段中Date类型转换为String类型
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        // String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
        binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(text == null ? null : StringEscapeUtils.escapeHtml4(text.trim()));
            }
            @Override
            public String getAsText() {
                Object value = getValue();
                return value != null ? value.toString() : "";
            }
        });
        // Date 类型转换
//        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
//            @Override
//            public void setAsText(String text) {
//                setValue(DateUtils.parseDate(text));
//            }
//			@Override
//			public String getAsText() {
//				Object value = getValue();
//				return value != null ? DateUtils.formatDateTime((Date)value) : "";
//			}
//        });
    }
}
