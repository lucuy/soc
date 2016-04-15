package com.soc.webapp.action.alert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.soc.model.systemsetting.Setting;
import com.soc.model.user.User;
import com.soc.service.audit.AuditService;
import com.soc.service.systemsetting.SettingService;
import com.soc.webapp.action.BaseAction;

/**
 * 
 * <一句话功能简述> <功能详细描述>
 * 
 * @author yinhaiping
 * @version [版本号, 2012-11-6]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class AlertSettingAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private SettingService settingManager;

	private String alertTerrace; // 启用平台提示告警

	private String alertSys; // 启用snmp trap\syslog配置

	// 审计业务管理类
	private AuditService auditManager;

	

	/**
	 * 查看
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String queryAlert() {
		alertTerrace = settingManager.queryByKey("alertTerrace");
		alertSys = settingManager.queryByKey("alertSys");
		return SUCCESS;
	}

	/**
	 * 保存
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String saveAlert() {

		List<String> fieldList = new ArrayList<String>();
		fieldList.add(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
				.getUserLoginName());

		Setting setting = new Setting();
		setting.setKey("alertTerrace");
		setting.setValue(alertTerrace);
		settingManager.updateByKey("alertTerrace", setting);
		setting.setKey("alertSys");
		setting.setValue(alertSys);
		settingManager.updateByKey("alertSys", setting);

		// 审计入库
		auditManager.insertByUpdateOperator(((User) this.getSession()
				.getAttribute("SOC_LOGON_USER")).getUserId(), "告警配置", super
				.getRequest().getRemoteAddr(), fieldList);

		return SUCCESS;
	}
    
	/**
	 * <查询是否开启声光效果>
	 * <功能详细描述>
	 * @see [类、类#方法、类#成员]
	 */
	public void queryAlertTrance() {
		
		//log.info("[AlertSettingAction] Enter method queryAlertTrance.... ");
		
		alertTerrace = settingManager.queryByKey("alertTerrace");

		try {
			if (alertTerrace != null) {
				if (alertTerrace.equals("1")) {
					getResponse().getWriter().write("true");

				} else {
					getResponse().getWriter().write("false");
				}
			} else {
				getResponse().getWriter().write("false");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getAlertTerrace() {
		return alertTerrace;
	}

	public void setAlertTerrace(String alertTerrace) {
		this.alertTerrace = alertTerrace;
	}

	public String getAlertSys() {
		return alertSys;
	}

	public void setAlertSys(String alertSys) {
		this.alertSys = alertSys;
	}

	public SettingService getSettingManager() {
		return settingManager;
	}

	public void setSettingManager(SettingService settingManager) {
		this.settingManager = settingManager;
	}

	public AuditService getAuditManager() {
		return auditManager;
	}

	public void setAuditManager(AuditService auditManager) {
		this.auditManager = auditManager;
	}
}