package com.soc.webapp.action.systemsetting;

import java.util.ArrayList;
import java.util.List;

import com.soc.model.user.User;
import com.soc.service.audit.AuditService;
import com.soc.service.systemsetting.SystemSettingService;
import com.soc.webapp.action.BaseAction;
import com.util.DateUtil;

public class SettingSync4AIpAction extends BaseAction {
	
	private SystemSettingService ssManager;
	private String syncIp;
	private String msg;  //传给前台的成功提示
	
	//审计业务处理类
	private AuditService auditManager;
	
	
	public String querySyncIp(){
		syncIp = ssManager.query4AIpByKey();
		msg="请输入正确的服务器IP或者域名，否则将导致无法同步服务器用户！";
		return SUCCESS;
	}
	public String updateSyncIp(){
		ssManager.update4AIp(syncIp);
		msg="保存成功！";
		
		//写入审计日志
		List<String> fieldList = new ArrayList<String>();
		fieldList.add("账号同步"+"("+"账号同步"+")");
		auditManager.insertByDeleteOperator(((User) this.getSession()
            .getAttribute("SOC_LOGON_USER")).getUserId(), "账号同步", super
            .getRequest().getRemoteAddr(), fieldList);
        
        //syslog
       /* String logString = "";
        
        logString =
            "登录名:" + ((User)this.getSession().getAttribute("SOC_LOGON_USER")).getUserLoginName() + " 源IP:"
                + getRequest().getRemoteAddr() + " 操作时间:" + DateUtil.curDateTimeStr19() + " 操作类型 :账号同步";
        
        logManager.writeSystemAuditLog(logString);*/
		logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
				.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "修改账号同步");
		
		return SUCCESS;
	}

	public SystemSettingService getSsManager() {
		return ssManager;
	}

	public void setSsManager(SystemSettingService ssManager) {
		this.ssManager = ssManager;
	}

	public String getSyncIp() {
		return syncIp;
	}

	public void setSyncIp(String syncIp) {
		this.syncIp = syncIp;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public AuditService getAuditManager() {
		return auditManager;
	}
	public void setAuditManager(AuditService auditManager) {
		this.auditManager = auditManager;
	}
	

}
