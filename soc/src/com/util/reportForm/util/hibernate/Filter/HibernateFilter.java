package com.util.reportForm.util.hibernate.Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.util.reportForm.util.hibernate.hibernateUtil.HibernateUtil;



public class HibernateFilter implements Filter {

	// Log
	private static Log log = LogFactory.getLog(HibernateFilter.class);

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		try {
			// 开始一个新的事务
			HibernateUtil.beginTransaction();
			// 进行业务处理
			chain.doFilter(request, response);
			// 提交事务
			HibernateUtil.commitTransaction();
		} catch (ServletException ex) {
			log.debug("Rolling back the database transaction.");
			// 回滚事务，并关闭Session
			HibernateUtil.rollbackTransaction();
			throw ex;
		} catch (IOException ex) {
			log.debug("Rolling back the database transaction.");
			// 回滚事物，并关闭Session

		}

	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

	//
}
