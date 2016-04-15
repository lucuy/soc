package com.soc.webapp.action.systemsetting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.soc.model.systemsetting.Setting;
import com.soc.model.user.User;
import com.soc.service.audit.AuditService;
import com.soc.service.systemsetting.SettingService;
import com.soc.webapp.action.BaseAction;
import com.util.DateUtil;
import com.util.StringUtil;

public class SettingSNMPAction extends BaseAction
{
    private static final long serialVersionUID = 1L;
    
    private SettingService settingManager;
    
    private String checkingUse;
    
   private AuditService auditManager;
    
    public String sign()
    {
        List<String> fieldList = new ArrayList<String>();
        String s = getRequest().getParameter("checkingUse");
        
        if (StringUtil.isBlank(s))
        {
            checkingUse = "0";
        }
     
        if("0".equals(checkingUse)){
        	try {
        		 Runtime.getRuntime().exec("/etc/init.d/snmpd stop");
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        if("1".equals(checkingUse)){
        	try {
        		 Runtime.getRuntime().exec("/etc/init.d/snmpd start");
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        Setting setting = new Setting();
        setting.setKey("snmp");
        setting.setValue(checkingUse);
        settingManager.updateByKey("snmp", setting);
        
        fieldList.add(((User) this.getSession()
            .getAttribute("SOC_LOGON_USER")).getUserLoginName());
       auditManager.insertByUpdateOperator(((User) this.getSession()
            .getAttribute("SOC_LOGON_USER")).getUserId(), "SNMP配置", super
            .getRequest().getRemoteAddr(), fieldList);
        
      //syslog
        /*String logString = "";
       
        logString =
           "登录名:" + ((User)this.getSession().getAttribute("SOC_LOGON_USER")).getUserLoginName() + " 源IP:"
               + getRequest().getRemoteAddr() + " 操作时间:" + DateUtil.curDateTimeStr19() + " 操作类型 :SNMP配置";
       
       logManager.writeSystemAuditLog(logString);*/
       logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
				.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "修改SNMP配置");
        return SUCCESS;
        
    }
    
    public String settingSNMP()
    {
        checkingUse = settingManager.queryByKey("snmp");
        if("0".equals(checkingUse)){
        	try {
        		System.out.println("开始执行stop命令...");
        		 Runtime.getRuntime().exec("/etc/init.d/snmpd stop");
        		 System.out.println("执行stop命令结束...");
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        if("1".equals(checkingUse)){
        	try {
        		System.out.println("开始执行start命令...");
        		 Runtime.getRuntime().exec("/etc/init.d/snmpd start");
        		 System.out.println("执行start命令结束...");
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        return SUCCESS;
    }
    
    public String getCheckingUse()
    {
        return checkingUse;
    }
    
    public void setCheckingUse(String checkingUse)
    {
        this.checkingUse = checkingUse;
    }
    
    public SettingService getSettingManager()
    {
        return settingManager;
    }
    
    public void setSettingManager(SettingService settingManager)
    {
        this.settingManager = settingManager;
    }

	public AuditService getAuditManager() {
		return auditManager;
	}

	public void setAuditManager(AuditService auditManager) {
		this.auditManager = auditManager;
	}

    
    
}