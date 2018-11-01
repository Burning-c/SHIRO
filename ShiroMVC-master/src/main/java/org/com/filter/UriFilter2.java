package org.com.filter;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.apache.shiro.web.filter.mgt.FilterChainResolver;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2018/4/18.
 */
public class UriFilter2 implements Filter {
    private String currentservletPath="/";
    private int count = 0;


    private List<String> currentUrls=new ArrayList<String>();

//    protected void doFilterInternal(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
//        System.out.println("doFilterInternal。。。。。");
//        HttpServletRequest req = (HttpServletRequest)servletRequest;
//        HttpServletResponse res = (HttpServletResponse)servletResponse;
//        String servletPath = req.getServletPath();
//        System.out.println("currentservletPath:"+currentservletPath+",servletPath:"+servletPath);
//        Session session = SecurityUtils.getSubject().getSession();
//        List<String> urls = (List<String>) session.getAttribute("currentUserUrls");
//        if(!compare(currentUrls,urls)){
//            currentUrls=urls;
//        }
//        System.out.println("currentUrls:"+currentservletPath+",urls:"+urls);
//        if(!currentservletPath.equals(servletPath)){
//            currentservletPath=servletPath;
//            if((currentUrls != null && currentUrls.contains(currentservletPath))){
//                filterChain.doFilter(req, res);
//            }else{
//                System.out.println("currentservletPath:"+currentservletPath+",servletPath:"+servletPath);
//                res.sendRedirect("/logon.jsp");
//                return;
//            }
//        }
//        filterChain.doFilter(req, res);
//    }
    public boolean compare(List<String> a, List<String> b) {
        if(a.size() != b.size())
            return false;
        for(int i=0;i<a.size();i++){
            if(!a.get(i).equals(b.get(i)))
                return false;
        }
        return true;
    }


    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @SuppressWarnings("unchecked")
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("doFilter。。。。。");
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse res = (HttpServletResponse)servletResponse;
        org.apache.shiro.subject.Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
		List<String> urls = (List<String>) session.getAttribute("currentUserUrls");
        String servletPath = req.getServletPath();
        System.out.println("urls为:"+urls+",servletpath为:"+servletPath);
        if((urls != null && urls.size()>=0 && !urls.contains(servletPath))){
            System.out.println("servletPath为:"+servletPath+"urls数据为:"+urls);
            res.sendRedirect(req.getContextPath() + "/logon.jsp");
            session.removeAttribute("currentUserUrls");
            return;
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    public void destroy() {

    }
}
