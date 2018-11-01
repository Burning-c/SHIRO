package org.com.filter;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
/**
 * Created by Administrator on 2018/4/18.
 */
public class UriFillter extends RolesAuthorizationFilter {

    @Override
    public void doFilterInternal(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
//        System.out.println("doFilterInternal。。。。。");
//        HttpServletRequest req = (HttpServletRequest)request;
//        String uriPath = req.getRequestURI();
//        String urlPath = req.getRequestURL().toString();
//        String contextPath = req.getContextPath();
//        String servletPath = req.getServletPath();
//        String queryString = req.getQueryString();
//        System.out.println("uriPath:"+uriPath+",urlPath:"+urlPath+",contextPath:"+contextPath+",servletPath:"+servletPath+",queryString:"+queryString);
        super.doFilterInternal(request, response, chain);
    }
}
