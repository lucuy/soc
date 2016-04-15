package com.topo.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.compliance.service.BaseService;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport {
	protected transient Log log = LogFactory.getLog(getClass());
	private static final long serialVersionUID = 1L;
	private BaseService baseService1;
	

	public BaseService getBaseService1() {
		return baseService1;
	}

	public void setBaseService1(BaseService baseService1) {
		this.baseService1 = baseService1;
	}

	/**
	 * @author 
	 * @return
	 */
	public String getRealPath() {
		return this.getRequest().getSession().getServletContext().getRealPath("/");
	}
	
	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	public HttpSession getSession() {
		return getRequest().getSession();
	}

	public ServletContext getServletContext() {
		return ServletActionContext.getServletContext();
	}

	public String getRealyPath(String path) {
		return getServletContext().getRealPath(path);
	}
	
 
	
}
