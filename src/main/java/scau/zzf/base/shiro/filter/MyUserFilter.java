package scau.zzf.base.shiro.filter;

import com.alibaba.fastjson.JSON;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.stereotype.Component;
import scau.zzf.dictionary.Code;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by zzf on 2017/2/22.
 */
@Component
public class MyUserFilter extends AccessControlFilter {
    /**
     * Returns <code>true</code> if the request is a
     * {@link #isLoginRequest(ServletRequest, ServletResponse) loginRequest} or
     * if the current {@link #getSubject(ServletRequest, ServletResponse) subject}
     * is not <code>null</code>, <code>false</code> otherwise.
     *
     * @return <code>true</code> if the request is a
     * {@link #isLoginRequest(ServletRequest, ServletResponse) loginRequest} or
     * if the current {@link #getSubject(ServletRequest, ServletResponse) subject}
     * is not <code>null</code>, <code>false</code> otherwise.
     */
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (isLoginRequest(request, response)) {
            return true;
        } else {
            Subject subject = getSubject(request, response);
            // If principal is not null, then the user is known and should be allowed access.
            return subject.getPrincipal() != null;
        }
    }

    /**
     * This default implementation simply calls
     * {@link #saveRequestAndRedirectToLogin(ServletRequest, ServletResponse) saveRequestAndRedirectToLogin}
     * and then immediately returns <code>false</code>, thereby preventing the chain from continuing so the redirect may
     * execute.
     */
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        try (PrintWriter out =response.getWriter();){
            Map map = new LinkedHashMap<>();
            map.put("statusCode", Code.UN_LOGIN.getStatusCode());
            map.put("statusMsg", Code.UN_LOGIN.getStatusMsg());
            out.print(JSON.toJSONString(map));
        }
        saveRequest(request);
        //不进行重定向
//        saveRequestAndRedirectToLogin(request, response);
        return false;
    }
}
