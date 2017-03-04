package scau.zzf.base.shiro.filter;

import com.alibaba.fastjson.JSON;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Component;
import scau.zzf.dictionary.Code;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by zzf on 2017/2/27.
 */
@Component
public class MyRolesAuthorizationFilter extends RolesAuthorizationFilter {


    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {

        Subject subject = getSubject(request, response);
        // If the subject isn't identified, redirect to login URL
        if (subject.getPrincipal() == null) {
            try (BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());){
                Map map = new LinkedHashMap<>();
                map.put("statusCode", Code.UN_LOGIN.getStatusCode());
                map.put("statusMes", Code.UN_LOGIN.getStatusMsg());
                out.write(JSON.toJSONString(map).getBytes("utf-8"));
                out.flush();
            }
        } else {
            try (BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());){
                Map map = new LinkedHashMap<>();
                map.put("statusCode",Code.REJECT.getStatusCode());
                map.put("statusMes", Code.REJECT.getStatusMsg());
                out.write(JSON.toJSONString(map).getBytes("utf-8"));
                out.flush();
            }
        }
        return false;
    }
}
