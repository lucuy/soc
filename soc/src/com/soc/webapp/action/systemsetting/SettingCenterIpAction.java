package com.soc.webapp.action.systemsetting;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.soc.model.conf.GlobalConfig;
import com.soc.model.systemsetting.Setting;
import com.soc.model.user.User;
import com.soc.service.audit.AuditService;
import com.soc.service.systemsetting.SettingService;
import com.soc.service.systemsetting.SystemCenterIpService;
import com.soc.webapp.action.BaseAction;
import com.util.DateUtil;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  yinhaiping
 * @version  [版本号, 2012-12-21]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SettingCenterIpAction extends BaseAction
{
    private static final long serialVersionUID = 1L;
    
    private SystemCenterIpService systemCenterIpManager;
    
    private SettingService settingManager;
    
    private String centerPort;
    
    private String centerIp;
    
    private String centerWwwUpIp;
    
    private String centerWwwUpPort;
    
    private String centerNatIp;
    
    private String centerNatPort;
    
    private String centerNatUpIp;
    
    private String centerNatUpPort;
    
    // 审计业务管理类
    private AuditService auditManager;
    
    public String centerIp(){
        centerIp = settingManager.queryByKey("centerIp");
        centerPort = settingManager.queryByKey("centerPort");
        centerWwwUpIp = settingManager.queryByKey("centerWwwUpIp");
        centerWwwUpPort = settingManager.queryByKey("centerWwwUpPort");
        centerNatIp = settingManager.queryByKey("centerNatIp");
        centerNatPort = settingManager.queryByKey("centerNatPort");
        centerNatUpIp = settingManager.queryByKey("centerNatUpIp");
        centerNatUpPort = settingManager.queryByKey("centerNatUpPort");
        return SUCCESS;
    }
    
    public String centerIpUpdate(){
    	List<String> fieldList = new ArrayList<String>();
        fieldList.add(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
                .getUserLoginName());
    	
        
        Setting setting = new Setting();
        setting.setKey("centerIp");
        setting.setValue(centerIp);
        settingManager.updateByKey("centerIp", setting);
        setting.setKey("centerPort");
        setting.setValue(centerPort);
        settingManager.updateByKey("centerPort", setting);
        setting.setKey("centerWwwUpIp");
        setting.setValue(centerWwwUpIp);
        settingManager.updateByKey("centerWwwUpIp", setting);
        setting.setKey("centerWwwUpPort");
        setting.setValue(centerWwwUpPort);
        settingManager.updateByKey("centerWwwUpPort",setting );
        setting.setKey("centerNatIp");
        setting.setValue(centerNatIp);
        settingManager.updateByKey("centerNatIp", setting);
        setting.setKey("centerNatPort");
        setting.setValue(centerNatPort);
        settingManager.updateByKey("centerNatPort", setting);
        GlobalConfig.terracePort = centerNatPort;
        setting.setKey("centerNatUpIp");
        setting.setValue(centerNatUpIp);
        settingManager.updateByKey("centerNatUpIp", setting);
        setting.setKey("centerNatUpPort");
        setting.setValue(centerNatUpPort);
        settingManager.updateByKey("centerNatUpPort", setting);
        
        //更新配置文件 
        systemCenterIpManager.updateConf();
        HttpServletRequest request = getRequest();
        request.setAttribute("setSuccess", "设置成功");
        
        // 审计入库
        auditManager.insertByUpdateOperator(((User) this.getSession()
                .getAttribute("SOC_LOGON_USER")).getUserId(), "网络IP", super
                .getRequest().getRemoteAddr(), fieldList);
        
        //syslog
      /*  String logString = "";
        
        logString =
            "登录名:" + ((User)this.getSession().getAttribute("SOC_LOGON_USER")).getUserLoginName() + " 源IP:"
                + getRequest().getRemoteAddr() + " 操作时间:" + DateUtil.curDateTimeStr19() + " 操作类型 :网络IP";
        
        logManager.writeSystemAuditLog(logString);*/
        logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
				.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "修改网络IP");
        
        return SUCCESS;
    }

    public String getCenterPort()
    {
        return centerPort;
    }

    public void setCenterPort(String centerPort)
    {
        this.centerPort = centerPort;
    }

    public String getCenterIp()
    {
        return centerIp;
    }

    public void setCenterIp(String centerIp)
    {
        this.centerIp = centerIp;
    }

    public SettingService getSettingManager()
    {
        return settingManager;
    }

    public void setSettingManager(SettingService settingManager)
    {
        this.settingManager = settingManager;
    }

    public String getCenterWwwUpIp()
    {
        return centerWwwUpIp;
    }

    public void setCenterWwwUpIp(String centerWwwUpIp)
    {
        this.centerWwwUpIp = centerWwwUpIp;
    }

    public String getCenterWwwUpPort()
    {
        return centerWwwUpPort;
    }

    public void setCenterWwwUpPort(String centerWwwUpPort)
    {
        this.centerWwwUpPort = centerWwwUpPort;
    }

    public String getCenterNatIp()
    {
        return centerNatIp;
    }

    public void setCenterNatIp(String centerNatIp)
    {
        this.centerNatIp = centerNatIp;
    }

    public String getCenterNatPort()
    {
        return centerNatPort;
    }

    public void setCenterNatPort(String centerNatPort)
    {
        this.centerNatPort = centerNatPort;
    }

    public String getCenterNatUpIp()
    {
        return centerNatUpIp;
    }

    public void setCenterNatUpIp(String centerNatUpIp)
    {
        this.centerNatUpIp = centerNatUpIp;
    }

    public String getCenterNatUpPort()
    {
        return centerNatUpPort;
    }

    public void setCenterNatUpPort(String centerNatUpPort)
    {
        this.centerNatUpPort = centerNatUpPort;
    }

    public SystemCenterIpService getSystemCenterIpManager()
    {
        return systemCenterIpManager;
    }

    public void setSystemCenterIpManager(SystemCenterIpService systemCenterIpManager)
    {
        this.systemCenterIpManager = systemCenterIpManager;
    }

	public AuditService getAuditManager() {
		return auditManager;
	}

	public void setAuditManager(AuditService auditManager) {
		this.auditManager = auditManager;
	}
    
}