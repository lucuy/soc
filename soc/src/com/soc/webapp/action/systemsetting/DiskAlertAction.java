package com.soc.webapp.action.systemsetting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.soc.model.systemsetting.Setting;
import com.soc.model.user.User;
import com.soc.service.audit.AuditService;
import com.soc.service.systemsetting.SettingService;
import com.soc.webapp.action.BaseAction;
import com.soc.webapp.quartz.DiskAlertTrigger;
import com.util.DateUtil;
import com.util.StringUtil;

public class DiskAlertAction extends BaseAction {

	/**
	 * Description: 系统设置-磁盘空间报警设置Action
	 * 
	 * @author 王亚男
	 * @Version 1.0
	 * @Created at 2011-10-28
	 * @Modified by
	 */

	private static final long serialVersionUID = 1L;

	private SettingService settingManager;

	private int diskAlertUse;
	private String diskAlterCriticalPoint;
	private String emailDisk;
	private AuditService auditManager;

	/**
	 * 显示信息
	 * 
	 * @return
	 */
	public void display() {
	    Map<String, Object> map = new HashMap<String, Object>();
	    //磁盘告警时候发送到的邮件地址
	    String email = settingManager.queryByKey("email_disk");
	    //磁盘使用告警是否使用
		String isUse = settingManager.queryByKey("diskalert_use");
		//磁盘告警使用临界点
		String criticalPoint = settingManager
				.queryByKey("diskalert_criticalPoint");
		if (StringUtil.isNotBlank(email)) {
		    map.put("emailDisk", email);
        }

		if (StringUtil.isNotBlank(isUse)) {
			map.put("diskAlertUse", isUse);
		}

		if (StringUtil.isNotBlank(criticalPoint)) {
			map.put("diskAlterCriticalPoint", criticalPoint);
		}
		
		// 转换为JSON数据结构
        JSONArray jsonArray = JSONArray.fromObject(map);
        
        // Ajax返回
        try {
            getResponse().getWriter().write(jsonArray.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	/**
	 * 更新磁盘告警设置
	 * <功能详细描述>
	 * @see [类、类#方法、类#成员]
	 */
	public void update() {
	    List<String> fieldList = new ArrayList<String>();
		Setting setting = new Setting();

		setting.setKey("email_disk");
        setting.setValue(emailDisk);
        settingManager.updateByKey("email_disk", setting);
        
		setting.setKey("diskalert_use");
		setting.setValue(String.valueOf(diskAlertUse));
		settingManager.updateByKey("diskalert_use", setting);

		setting.setKey("diskalert_criticalPoint");
		setting.setValue(diskAlterCriticalPoint);
		settingManager.updateByKey("diskalert_criticalPoint", setting);
		
		//将邮箱发送标志设置为未发邮件状态
		DiskAlertTrigger.flagEmail=false;
		//super.addActionMessage("配置保存成功");
		
		fieldList.add(((User) this.getSession()
            .getAttribute("SOC_LOGON_USER")).getUserLoginName());
        auditManager.insertByUpdateOperator(((User) this.getSession()
            .getAttribute("SOC_LOGON_USER")).getUserId(), "磁盘告警", super
            .getRequest().getRemoteAddr(), fieldList);
        
        //syslog
        /*String logString = "";
        
        logString =
            "登录名:" + ((User)this.getSession().getAttribute("SOC_LOGON_USER")).getUserLoginName() + " 源IP:"
                + getRequest().getRemoteAddr() + " 操作时间:" + DateUtil.curDateTimeStr19() + " 操作类型 :磁盘告警";
        
        logManager.writeSystemAuditLog(logString);*/
        logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
				.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "修改磁盘告警设置");

	}

	public SettingService getSettingManager() {
		return settingManager;
	}

	public void setSettingManager(SettingService settingManager) {
		this.settingManager = settingManager;
	}

	public int getDiskAlertUse() {
		return diskAlertUse;
	}

	public void setDiskAlertUse(int diskAlertUse) {
		this.diskAlertUse = diskAlertUse;
	}

	public String getDiskAlterCriticalPoint() {
		return diskAlterCriticalPoint;
	}

	public void setDiskAlterCriticalPoint(String diskAlterCriticalPoint) {
		this.diskAlterCriticalPoint = diskAlterCriticalPoint;
	}

    public String getEmailDisk()
    {
        return emailDisk;
    }

    public void setEmailDisk(String emailDisk)
    {
        this.emailDisk = emailDisk;
    }

	public AuditService getAuditManager() {
		return auditManager;
	}

	public void setAuditManager(AuditService auditManager) {
		this.auditManager = auditManager;
	}


}
