package com.soc.model.user;

import java.util.Date;

/**
 * Description: 人员实体类
 * @author 郭煜玺
 * @Version 1.0
 * @Created at 2011-05-15
 * @Modified by 
 */
public class OnlineUser {
	private long userId;
	private String userLoginName;
	private Date loginTime;
	private String userIp;
	private String sessionId;
	private int flag;			// 1：当前在线  | 0：在线待退出(尚未提醒) | -1：在线待退出(已经提醒) ；设置两个状态值为了避免重复下线提醒
	

	public String getUserIp() {
		return userIp;
	}
	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUserLoginName() {
		return userLoginName;
	}
	public void setUserLoginName(String userLoginName) {
		this.userLoginName = userLoginName;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
}
