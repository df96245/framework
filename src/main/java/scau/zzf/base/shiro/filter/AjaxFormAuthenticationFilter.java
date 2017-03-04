package scau.zzf.base.shiro.filter;

import com.alibaba.fastjson.JSON;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import scau.zzf.dictionary.Code;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by zzf on 2017/2/22.
 */
@Component
public class AjaxFormAuthenticationFilter extends FormAuthenticationFilter {
    private static final Logger log = LoggerFactory
            .getLogger(AjaxFormAuthenticationFilter.class);

    /*
     *	主要是针对登入成功的处理方法。对于请求头是AJAX的之间返回JSON字符串。
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token,
                                     Subject subject, ServletRequest request, ServletResponse response)
            throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
//        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");

//        if (!"XMLHttpRequest".equalsIgnoreCase(httpServletRequest
//                .getHeader("X-Requested-With"))) {// 不是ajax请求
//            issueSuccessRedirect(request, response);
//        } else {
        httpServletResponse.setCharacterEncoding("UTF-8");
        PrintWriter out = httpServletResponse.getWriter();
        Map map = new HashMap();
        map.put(Code.OPERATION_SUCCESS.getStatusCode(), Code.OPERATION_SUCCESS.getStatusMsg());
        out.println(JSON.toJSONString(map));
        out.flush();
        out.close();
//        }
        return false;
    }

    /**
     * 主要是处理登入失败的方法
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token,
                                     AuthenticationException e, ServletRequest request,
                                     ServletResponse response) {
        if (!"XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request)
                .getHeader("X-Requested-With"))) {// 不是ajax请求
            setFailureAttribute(request, e);
            return true;
        }
        try (PrintWriter out = response.getWriter();) {
            response.setCharacterEncoding("UTF-8");
            String error = e.getClass().getSimpleName();
            if ("IncorrectCredentialsException".equals(error)) {
                out.println("{success:false,message:'密码错误'}");
            } else if ("UnknownAccountException".equals(error)) {
                out.println("{success:false,message:'账号不存在'}");
            } else if ("LockedAccountException".equals(error)) {
                out.println("{success:false,message:'账号被锁定'}");
            } else {
                out.println("{success:false,message:'未知错误'}");
            }
            out.flush();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return false;
    }

    /**
     * 所有请求都会经过的方法。
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (isLoginRequest(request, response)) {
            if (isLoginSubmission(request, response)) {
                if (log.isTraceEnabled()) {
                    log.trace("Login submission detected.  Attempting to execute login.");
                }
                return executeLogin(request, response);
            }
        else {
//            if (log.isTraceEnabled()) {
//                log.trace("Login page view.");
//            }
//            //allow them to see the login page ;)
//            return true;
                if (log.isTraceEnabled()) {
                log.trace("Get method is not allow.");
            }
                return false;
            }

        } else {
            if (log.isTraceEnabled()) {
                log.info("Attempting to access a path which requires authentication.return unlogin message");
            }
            try (PrintWriter out = response.getWriter();) {
                Map map = new LinkedHashMap<>();
                map.put("statusCode", Code.UN_LOGIN.getStatusCode());
                map.put("statusMsg", Code.UN_LOGIN.getStatusMsg());
                out.print(JSON.toJSONString(map));
            }

//        saveRequestAndRedirectToLogin(request, response);
            return false;
        }
    }

}
