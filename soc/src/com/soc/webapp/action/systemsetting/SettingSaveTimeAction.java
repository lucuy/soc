package com.soc.webapp.action.systemsetting;

import java.util.ArrayList;
import java.util.List;

import com.soc.model.systemsetting.Setting;
import com.soc.model.user.User;
import com.soc.service.audit.AuditService;
import com.soc.service.systemsetting.SettingService;
import com.soc.webapp.action.BaseAction;
import com.util.DateUtil;

@SuppressWarnings("serial")
public class SettingSaveTimeAction extends BaseAction {
	private SettingService settingManager;
	private String saveTime;
	 // 审计业务管理类
    private AuditService auditManager;
	
	
	public String edit(){
		saveTime = settingManager.queryByKey("save_Time");
		return SUCCESS;
	}
	public String saveTime(){
		  List<String> fieldList = new ArrayList<String>();
	        fieldList.add(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
	                .getUserLoginName());
		Setting setting = new Setting();
		 setting.setKey("save_Time");
	        setting.setValue(saveTime);
		settingManager.updateByKey("save_Time", setting);
		
        // 审计入库
        auditManager.insertByUpdateOperator(((User) this.getSession()
                .getAttribute("SOC_LOGON_USER")).getUserId(), "保存时限", super
                .getRequest().getRemoteAddr(), fieldList);
        
        //syslog
       /* String logString = "";
        
        logString =
            "登录名:" + ((User)this.getSession().getAttribute("SOC_LOGON_USER")).getUserLoginName() + " 源IP:"
                + getRequest().getRemoteAddr() + " 操作时间:" + DateUtil.curDateTimeStr19() + " 操作类型 :保存时限";
        
        logManager.writeSystemAuditLog(logString);*/
        logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
				.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "修改数据保存时限");
        
        super.addActionMessage("配置保存成功！");
        
        return SUCCESS;
	}
	
	
	
	
	
	
	public SettingService getSettingManager() {
		return settingManager;
	}
	public void setSettingManager(SettingService settingManager) {
		this.settingManager = settingManager;
	}
	public String getSaveTime() {
		return saveTime;
	}
	public void setSaveTime(String saveTime) {
		this.saveTime = saveTime;
	}
	public AuditService getAuditManager() {
		return auditManager;
	}
	public void setAuditManager(AuditService auditManager) {
		this.auditManager = auditManager;
	}
	
	
}
