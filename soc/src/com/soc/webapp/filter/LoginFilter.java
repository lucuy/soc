package com.soc.webapp.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.soc.model.user.OnlineUser;

/**
 * 
 * 登录过滤器
 * 登录过程时候的各种验证
 * 
 * @author  liuyanxu
 * @version  [V100R001C001, 2012-8-19]
 * @see  
 * @since 
 */

public class LoginFilter implements Filter
{
    
    private final String LOGIN_PAGE;
    
    private final String LOGOUT_PAGE;
    
    protected FilterConfig filterConfig;
    
    public LoginFilter()
    {
        LOGIN_PAGE = "/soc/pages/login.jsp";
        LOGOUT_PAGE = "/soc/login/logout.action";
    }
    
    public void setFilterConfig(FilterConfig filterConfig)
    {
        this.filterConfig = filterConfig;
    }
    
    public void init(FilterConfig config)
        throws ServletException
    {
        filterConfig = config;
    }
    
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException
    {
        HttpServletRequest hRequest = (HttpServletRequest)request;
        HttpServletResponse hResponse = (HttpServletResponse)response;
        
        String url = hRequest.getRequestURI();
        
        // 如果是登录请求则直接通过
        if (url.contains("/login/check.action") || url.contains("/login/checkSingle.action")
            || url.contains("/login/logout.action")||url.contains("/serial")
            )
        {
            chain.doFilter(request, response);
            return;
        }
        
        // 此外的请求均判断是否登录
        Object isLog = hRequest.getSession().getAttribute("SOC_LOGON_USER");
        
        if (isLog != null)
        {
            // 判断是否需要被单用户在线机制强制退出
            List<OnlineUser> userList =
                (List<OnlineUser>)hRequest.getSession().getServletContext().getAttribute("SOC_ONLINE_USERLIST");
            for (OnlineUser onlineUser : userList)
            {
                if (hRequest.getSession().getId().equals(onlineUser.getSessionId()) && onlineUser.getFlag() != 1)
                {
                    hResponse.sendRedirect(LOGOUT_PAGE);
                    return;
                }
            }
            chain.doFilter(request, response);
            return;
        }
        else
        {
         // 刷新父页面
            try
            {
                String script = "<script language=javascript>top.location.href='"+LOGIN_PAGE+"' </script>";
                response.getWriter().print(script);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            //hResponse.sendRedirect(LOGIN_PAGE);
            return;
            
        }
    }
    
    public void destroy()
    {
        filterConfig = null;
    }
    
}
