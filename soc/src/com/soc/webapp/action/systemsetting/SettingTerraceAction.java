package com.soc.webapp.action.systemsetting;

import java.util.ArrayList;
import java.util.List;

import com.soc.model.conf.GlobalConfig;
import com.soc.model.systemsetting.Setting;
import com.soc.model.user.User;
import com.soc.service.audit.AuditService;
import com.soc.service.systemsetting.SettingService;
import com.soc.webapp.action.BaseAction;
import com.util.DateUtil;

public class SettingTerraceAction extends BaseAction
{
    private String terraceServerPort;
    
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;
    private SettingService settingManager;
    
    //内部审计
    private AuditService auditManager;
    
    public String display()
    {
        terraceServerPort = settingManager.queryByKey("terrace_port");
        return SUCCESS;
    }
    
    public String update()
    {
        Setting setting = new Setting();
        setting.setKey("terrace_port");
        setting.setValue(terraceServerPort);
        settingManager.updateByKey("terrace_port", setting);
        GlobalConfig.terracePort = terraceServerPort;
        super.addActionMessage("配置保存成功");
        
        
        List<String> fieldList = new ArrayList<String>();
        
        fieldList.add("平台配置(平台配置)");
        //审计入库
        auditManager.insertByUpdateOperator(((User)this.getSession().getAttribute("SOC_LOGON_USER")).getUserId(),
            "平台配置",
            super.getRequest().getRemoteAddr(),
            fieldList);
        //syslog
       /* String logString = "";
        
        logString =
            "登录名:" + ((User)this.getSession().getAttribute("SOC_LOGON_USER")).getUserLoginName() + " 源IP:"
                + getRequest().getRemoteAddr() + " 操作时间:" + DateUtil.curDateTimeStr19() + " 操作类型 :平台配置";
        
        logManager.writeSystemAuditLog(logString);*/
        logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
				.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "修改平台配置");
        
        return SUCCESS;
    }

    public SettingService getSettingManager()
    {
        return settingManager;
    }

    public void setSettingManager(SettingService settingManager)
    {
        this.settingManager = settingManager;
    }

    public String getTerraceServerPort()
    {
        return terraceServerPort;
    }

    public void setTerraceServerPort(String terraceServerPort)
    {
        this.terraceServerPort = terraceServerPort;
    }

	public AuditService getAuditManager() {
		return auditManager;
	}

	public void setAuditManager(AuditService auditManager) {
		this.auditManager = auditManager;
	}
    
}
