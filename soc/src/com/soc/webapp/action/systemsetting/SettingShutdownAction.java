package com.soc.webapp.action.systemsetting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.soc.model.user.User;
import com.soc.service.audit.AuditService;
import com.soc.webapp.action.BaseAction;
import com.util.DateUtil;

public class SettingShutdownAction extends BaseAction {
	private static final long serialVersionUID = -2650710539643687706L;
	private AuditService auditManager;
	
	/**
	 * 重启服务器
	 * @return
	 */
	public void rebootServer() {
	    List<String> fieldList = new ArrayList<String>();
	    fieldList.add(((User) this.getSession()
            .getAttribute("SOC_LOGON_USER")).getUserLoginName());
        auditManager.insertBySystemOperator(((User) this.getSession()
            .getAttribute("SOC_LOGON_USER")).getUserId(), "重启服务器", super
            .getRequest().getRemoteAddr(), fieldList);
        //syslog
       /* String logString = "";
       
        logString =
           "登录名:" + ((User)this.getSession().getAttribute("SOC_LOGON_USER")).getUserLoginName() + " 源IP:"
               + getRequest().getRemoteAddr() + " 操作时间:" + DateUtil.curDateTimeStr19() + " 操作类型 :重启服务器";
       
       logManager.writeSystemAuditLog(logString);*/
        logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
				.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "重启服务器");
		try {
			Process process = Runtime.getRuntime().exec("reboot");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 关闭服务器
	 * @return
	 */
	public void shutdownServer() {
	    List<String> fieldList = new ArrayList<String>();
        fieldList.add(((User) this.getSession()
            .getAttribute("SOC_LOGON_USER")).getUserLoginName());
        auditManager.insertBySystemOperator(((User) this.getSession()
            .getAttribute("SOC_LOGON_USER")).getUserId(), "关闭服务器", super
            .getRequest().getRemoteAddr(), fieldList);
        //syslog
        /* String logString = "";
        
         logString =
            "登录名:" + ((User)this.getSession().getAttribute("SOC_LOGON_USER")).getUserLoginName() + " 源IP:"
                + getRequest().getRemoteAddr() + " 操作时间:" + DateUtil.curDateTimeStr19() + " 操作类型 :关闭服务器";
        
        logManager.writeSystemAuditLog(logString);*/
        logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
				.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "关闭服务器");
		try {
			Process process = Runtime.getRuntime().exec("poweroff");
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public AuditService getAuditManager() {
		return auditManager;
	}

	public void setAuditManager(AuditService auditManager) {
		this.auditManager = auditManager;
	}


}
