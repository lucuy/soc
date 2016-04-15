package com.soc.webapp.action.systemsetting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.soc.model.conf.GlobalConfig;
import com.soc.model.systemsetting.Setting;
import com.soc.model.user.User;
import com.soc.service.audit.AuditService;
import com.soc.service.systemsetting.SettingService;
import com.soc.webapp.action.BaseAction;
import com.util.DateUtil;

/**
 * 
 * <一句话功能简述> <功能详细描述>
 * 
 * @author yinhaiping
 * @version [版本号, 2012-11-6]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class SettingAlertAction extends BaseAction {
    private static final long serialVersionUID = 1L;

    private SettingService settingManager;

    private String alertTerrace; // 启用平台提示告警

    private String alertSys; // 启用snmp trap\syslog配置

    // 审计业务管理类
    private AuditService auditManager;
    //邮箱的字符串
  	private String mails;
  	//百分比
  	private int present;
  	//放mail的lsit
  	private List<String> mailList;
    /**
     * 查看
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String queryAlert() {
        alertTerrace = settingManager.queryByKey("alertTerrace");
        alertSys = settingManager.queryByKey("alertSys");
        present = Integer.parseInt(settingManager.queryByKey("present"));
        mails = settingManager.queryByKey("mails");
        mailList = new ArrayList<String>();
		// 组建前台用来显示源地址的list
		if (!"".equals(mails)&&(mails!=null)) {
			String[] sourceAddr =mails.split(",");
			for (int i = 0; i < sourceAddr.length; i++) {
				this.mailList.add(sourceAddr[i]);
			}
		}
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
        setting.setKey("mails");
        if(mails==null){
        	mails="";
        }
        setting.setValue(mails);
        settingManager.updateByKey("mails", setting);
        setting.setKey("present");
        setting.setValue(present+"");
        settingManager.updateByKey("present", setting);

        // 审计入库
        auditManager.insertByUpdateOperator(((User) this.getSession()
                .getAttribute("SOC_LOGON_USER")).getUserId(), "告警配置", super
                .getRequest().getRemoteAddr(), fieldList);
        
        //syslog
       /* String logString = "";
        
        logString =
            "登录名:" + ((User)this.getSession().getAttribute("SOC_LOGON_USER")).getUserLoginName() + " 源IP:"
                + getRequest().getRemoteAddr() + " 操作时间:" + DateUtil.curDateTimeStr19() + " 操作类型 :告警配置";
        
        logManager.writeSystemAuditLog(logString);*/
        logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
				.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "修改告警配置");
        
        super.addActionMessage("配置保存成功！");
        
        return SUCCESS;
    }
    
    /**
     * <查询是否开启声光效果>
     * <功能详细描述>
     * @see [类、类#方法、类#成员]
     */
    public void queryAlertTrance() {
        
        log.info("[AlertSettingAction] Enter method queryAlertTrance.... ");
        
        alertTerrace = settingManager.queryByKey("alertTerrace");

        boolean falg = GlobalConfig.diskFalg;
		try {
			if (alertTerrace != null) {
				if (alertTerrace.equals("1")) {
					getResponse().getWriter().write("true,"+falg);

				} else {
					getResponse().getWriter().write("false,"+falg);
				}
			} else {
				getResponse().getWriter().write("false,"+falg);
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



	public String getMails() {
		return mails;
	}

	public void setMails(String mails) {
		this.mails = mails;
	}

	public void setPresent(int present) {
		this.present = present;
	}




	public int getPresent() {
		return present;
	}

	public List<String> getMailList() {
		return mailList;
	}

	public void setMailList(List<String> mailList) {
		this.mailList = mailList;
	}


}