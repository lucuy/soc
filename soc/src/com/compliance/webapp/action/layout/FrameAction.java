package com.compliance.webapp.action.layout;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.compliance.webapp.action.BaseAction;
import com.util.DateUtil;


public class FrameAction extends BaseAction {
	
	private transient Log log = LogFactory.getLog(getClass());
	
	private static final long serialVersionUID = 1L;

	private String todayDate;
	private String todayWeekday;
	
	private String message;


	/**
	 * 显示系统头部
	 * @return
	 */
	public String frameHeader(){
		
		// 初始化日期时间
		todayDate = DateUtil.curDateStr();
		todayWeekday = DateUtil.curDateWeekday();
		
		//初始化通知公告
		//在此实现.............................
		//System.out.println("server ip addr: " + super.getRequest().getRemoteAddr());
		//System.out.println("local ip addr: " + super.getRequest().getLocalAddr());
		
		return SUCCESS;
	}
	
	/**
	 * 显示系统框架
	 * @return
	 */
	public String frame(){
		return SUCCESS;
	}

	public String getTodayDate() {
		return todayDate;
	}

	public void setTodayDate(String todayDate) {
		this.todayDate = todayDate;
	}

	public String getTodayWeekday() {
		return todayWeekday;
	}

	public void setTodayWeekday(String todayWeekday) {
		this.todayWeekday = todayWeekday;
	}
	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
