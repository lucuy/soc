package com.compliance.webapp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



public class AccessFilter implements Filter  {

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hrequest = (HttpServletRequest)request; 
		HttpServletResponse hresponse = (HttpServletResponse)response;

		String currentURL = hrequest.getRequestURI(); //取得根目录所对应的绝对路径:

		String targetURL = currentURL.substring(currentURL.indexOf("/", 1), 
		currentURL.length()); //截取到当前文件名用于比较
		HttpSession session = hrequest.getSession(false); 
		//判断当前页是否是重定向以后的登录页面页面，如果是就不做session的判断，防止出现死循环
	
			if(currentURL.contains("/login/check.action")||
			   currentURL.contains("/serial/initSerial.action")	||
			   currentURL.contains("/serial/initRegister.action")){
				chain.doFilter(hrequest, hresponse);
				return;
			}
			

			if(session==null){
				hresponse.sendRedirect("/compliance/pages/logout.jsp");
				return;
			}
	         
			if (session.getAttribute("SSI_LOGIN_USER")== null) {
				hresponse.sendRedirect("/compliance/pages/logout.jsp");
				return;
			}
				chain.doFilter(hrequest, hresponse);
		}
		

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	public AccessFilter() {
		////System.out.println("====AccessFilter构造函数被调用=======");
	}


}
