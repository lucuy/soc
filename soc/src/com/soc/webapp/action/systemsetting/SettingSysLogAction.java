package com.soc.webapp.action.systemsetting;

import java.util.ArrayList;
import java.util.List;

import com.soc.model.systemsetting.Setting;
import com.soc.model.user.User;
import com.soc.service.audit.AuditService;
import com.soc.service.systemsetting.SettingService;
import com.soc.webapp.action.BaseAction;
import com.util.DateUtil;

public class SettingSysLogAction extends BaseAction {
	private static final long serialVersionUID = 8460887795031184907L;

	public String getSysLogJson() {
		return sysLogJson;
	}

	public void setSysLogJson(String sysLogJson) {
		this.sysLogJson = sysLogJson;
	}

	public String getSysLogJsonId() {
		return sysLogJsonId;
	}

	public void setSysLogJsonId(String sysLogJsonId) {
		this.sysLogJsonId = sysLogJsonId;
	}

	private String sysLogJson;
	private String sysLogJsonId;
	private SettingService settingManager;
	private AuditService auditManager;

	public SettingService getSettingManager() {
		return settingManager;
	}

	public void setSettingManager(SettingService settingManager) {
		this.settingManager = settingManager;
	}
	/**
	 * 显示设置
	 * 
	 * @return
	 */
	public String setSyslog() {
	    
		/*String isUse = settingManager.queryByKey("syslogUse");
		if (StringUtil.isNotBlank(isUse)) {
			syslogUse = Integer.parseInt(isUse);
		}
		equServerIp = settingManager.queryByKey("equServerIp");
		equServerPort = settingManager.queryByKey("equServerPort");
		if (StringUtil.isNotBlank(settingManager.queryByKey("conductAudit"))) {
			conductAudit = Integer.parseInt(settingManager
					.queryByKey("conductAudit"));
		}
		if (StringUtil.isNotBlank(settingManager.queryByKey("databaseAudit"))) {
			databaseAudit = Integer.parseInt(settingManager
					.queryByKey("databaseAudit"));
		}
		if (StringUtil.isNotBlank(settingManager.queryByKey("sysAudit"))) {
			sysAudit = Integer.parseInt(settingManager.queryByKey("sysAudit"));
		}
		return SUCCESS;*/
		sysLogJson = settingManager.queryByKey("SETTING_SYSLOG_JSON");
		
		return SUCCESS;
	}

	/**
	 * 添加syslog设置
	 * <功能详细描述>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	/*public String insert() {
	    List<String> fieldList = new ArrayList<String>();
		Setting setSysLog = new Setting();
		setSysLog.setKey("syslogUse");
		setSysLog.setValue(String.valueOf(syslogUse));
		settingManager.updateByKey("syslogUse", setSysLog);

		setSysLog.setKey("conductAudit");
		setSysLog.setValue(String.valueOf(1));
		settingManager.updateByKey("conductAudit", setSysLog);

		setSysLog.setKey("databaseAudit");
		setSysLog.setValue(String.valueOf(1));
		settingManager.updateByKey("databaseAudit", setSysLog);

		setSysLog.setKey("sysAudit");
		setSysLog.setValue(String.valueOf(1));
		settingManager.updateByKey("sysAudit", setSysLog);

		Setting setSysLog2 = new Setting();
		setSysLog2.setKey("equServerIp");
		setSysLog2.setValue(equServerIp);
		settingManager.updateByKey("equServerIp", setSysLog2);

		Setting setSysLog3 = new Setting();
		setSysLog3.setKey("equServerPort");
		setSysLog3.setValue(equServerPort);
		settingManager.updateByKey("equServerPort", setSysLog3);
		//System.out.println(getRealPath());
		Properties properties = new Properties();
		try {
			File file = new File(getRealPath()
					+ "WEB-INF/classes/log4j.properties");
			properties.load(new FileInputStream(file));
			//System.out.println(properties.getProperty("log4j.appender.A2.syslogHost"));
			if (StringUtil.isNotBlank(equServerIp)) {
				properties.setProperty("log4j.appender.A2.syslogHost",
						equServerIp);
			}
			
			if (StringUtil.isNotBlank(equServerPort)) {
				properties.setProperty("log4j.appender.A2.Port", equServerPort);
			}
			properties.store(new FileWriter(file), "修改文件里syslog信息");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		super.addActionMessage("配置保存成功！");

		
		fieldList.add(((User) this.getSession()
            .getAttribute("SOC_LOGON_USER")).getUserLoginName());
        auditManager.insertByUpdateOperator(((User) this.getSession()
            .getAttribute("SOC_LOGON_USER")).getUserId(), "Syslog配置", super
            .getRequest().getRemoteAddr(), fieldList);
        auditManager.insertByUpdateOperator(((User) this.getSession()
            .getAttribute("SOC_LOGON_USER")).getUserId(), "更改规则状态", super
            .getRequest().getRemoteAddr(), fieldList);
        
        //syslog
        String logString = "";
        
        logString =
            "登录名:" + ((User)this.getSession().getAttribute("SOC_LOGON_USER")).getUserLoginName() + " 源IP:"
                + getRequest().getRemoteAddr() + " 操作时间:" + DateUtil.curDateTimeStr19() + " 操作类型 :更改规则状态";
        
        logManager.writeSystemAuditLog(logString);
		return "success";
	}*/
	
	
    /**
     * 更新syslog配置
     * @return
     */
	public String update() {
		Setting setting = new Setting();
		setting.setKey("SETTING_SYSLOG_JSON");
		setting.setValue(sysLogJson);
		settingManager.updateSysLog(setting);
		
		List<String> fieldList = new ArrayList<String>();
		fieldList.add(((User) this.getSession()
	            .getAttribute("SOC_LOGON_USER")).getUserLoginName());
        auditManager.insertByUpdateOperator(((User) this.getSession()
                .getAttribute("SOC_LOGON_USER")).getUserId(), "syslog", super
                .getRequest().getRemoteAddr(), fieldList);
            
            //syslog
           /* String logString = "";
            
            logString =
                "登录名:" + ((User)this.getSession().getAttribute("SOC_LOGON_USER")).getUserLoginName() + " 源IP:"
                    + getRequest().getRemoteAddr() + " 操作时间:" + DateUtil.curDateTimeStr19() + " 操作类型 :更新syslog";
            
            logManager.writeSystemAuditLog(logString);*/
        logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
				.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "修改syslog");
		
		return SUCCESS;
	}
    
	/**
	 * 根据ID删除syslog配置
	 * @return
	 */
	public String delete() {
		if (null != sysLogJsonId && !"".equals(sysLogJsonId))
			settingManager.deleteSysLog(sysLogJsonId);
		
		List<String> fieldList = new ArrayList<String>();
		fieldList.add(((User) this.getSession()
	            .getAttribute("SOC_LOGON_USER")).getUserLoginName());
        auditManager.insertByDeleteOperator(((User) this.getSession()
                .getAttribute("SOC_LOGON_USER")).getUserId(), "syslog", super
                .getRequest().getRemoteAddr(), fieldList);
            
            //syslog
           /* String logString = "";
            
            logString =
                "登录名:" + ((User)this.getSession().getAttribute("SOC_LOGON_USER")).getUserLoginName() + " 源IP:"
                    + getRequest().getRemoteAddr() + " 操作时间:" + DateUtil.curDateTimeStr19() + " 操作类型 :删除syslog";
            
            logManager.writeSystemAuditLog(logString);*/
        logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
				.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "删除syslog");
		return SUCCESS;
	}


	public AuditService getAuditManager() {
		return auditManager;
	}

	public void setAuditManager(AuditService auditManager) {
		this.auditManager = auditManager;
	}



}