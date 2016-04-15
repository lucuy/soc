package com.soc.webapp.action.systemsetting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.soc.model.conf.MailserversInstance;
import com.soc.model.systemsetting.Setting;
import com.soc.model.user.User;
import com.soc.service.audit.AuditService;
import com.soc.service.systemsetting.MailServersInstanceService;
import com.soc.service.systemsetting.SettingService;
import com.soc.webapp.action.BaseAction;
import com.util.Base64;
import com.util.DES;
import com.util.DateUtil;
import com.util.StringUtil;

public class SettingMailAction extends BaseAction {
	private static final long serialVersionUID = -7603311996839283437L;

	private SettingService settingManager;
	private MailServersInstanceService mailManager;

	private MailserversInstance mailser;
	
	// 昵称
	private String nicky;
	
	// 邮件地址
	private String mailAddr;
	
	//邮件签名
	private String mailSign;
	
	// SMTP服务器
	private String smtpServer;
	
	//
	private int isNeedAuth;
	
	// 用户名
	private String mailUserName;
	
	//密码
	private String mailUserPwd;
	
	//邮箱端口号
	private String mailSmtpPort;
	
	//是否需要ssl
	private int isNeedSsl;
	
	private AuditService auditManager;

	/**
	 * 查询邮箱配置显示
	 * 
	 * @return
	 */
	public String show() {
        
	    //获得用户的昵称	   
		nicky = settingManager.queryByKey("email_nicky");
		
		mailAddr = settingManager.queryByKey("email_address");
		
		mailSign = settingManager.queryByKey("email_sign");
		
		smtpServer = settingManager.queryByKey("smtp_server");
		
		String isneed = settingManager.queryByKey("smtp_isneedauth");
		
		if (StringUtil.isBlank(isneed)) {
			isNeedAuth = 1;
		} else {
			isNeedAuth = Integer.parseInt(isneed);
		}
		mailUserName = settingManager.queryByKey("email_username");
		
		mailSmtpPort = settingManager.queryByKey("smtp_port");
		
		String isneed1 = settingManager.queryByKey("smtp_isneedssl");
		
		if (StringUtil.isBlank(isneed1)) {
			isNeedSsl = 1;
		} else {
			isNeedSsl = Integer.parseInt(isneed1);
		}

		return SUCCESS;
	}

	/**
	 * 保存邮件配置
	 * 
	 * @return
	 */
	public String update() {

	    List<String> fieldList = new ArrayList<String>();
		Setting setting = new Setting();

		// 昵称
		setting.setKey("email_nicky");
		
		setting.setValue(String.valueOf(nicky));
		
		settingManager.updateByKey("email_nicky", setting);

		// 邮件地址
		setting.setKey("email_address");
		setting.setValue(String.valueOf(mailAddr));
		settingManager.updateByKey("email_address", setting);

		// 签名
		setting.setKey("email_sign");
		setting.setValue(String.valueOf(mailSign));
		settingManager.updateByKey("email_sign", setting);

		// SMTP服务器
		setting.setKey("smtp_server");
		setting.setValue(String.valueOf(smtpServer));
		settingManager.updateByKey("smtp_server", setting);

		// 是否需要认证
		setting.setKey("smtp_isneedauth");
		setting.setValue(String.valueOf(isNeedAuth));
		settingManager.updateByKey("smtp_isneedauth", setting);

		// 用户名
		setting.setKey("email_username");
		setting.setValue(String.valueOf(mailUserName));
		settingManager.updateByKey("email_username", setting);

		// 密码
		if (!mailUserPwd.equals("")) {
			setting.setKey("email_password");
			// 邮箱密码加密
			setting.setValue(Base64.encodeString(DES.setEncrypt(mailUserPwd)));
			settingManager.updateByKey("email_password", setting);
		}

		// SMTP端口号
		setting.setKey("smtp_port");
		setting.setValue(String.valueOf(mailSmtpPort));
		settingManager.updateByKey("smtp_port", setting);

		// 是否需要ssl认证
		setting.setKey("smtp_isneedssl");
		setting.setValue(String.valueOf(isNeedSsl));
		settingManager.updateByKey("smtp_isneedssl", setting);

		super.addActionMessage("配置保存成功！");

		fieldList.add(((User) this.getSession()
            .getAttribute("SOC_LOGON_USER")).getUserLoginName());
        auditManager.insertByUpdateOperator(((User) this.getSession()
            .getAttribute("SOC_LOGON_USER")).getUserId(), "邮件配置", super
            .getRequest().getRemoteAddr(), fieldList);
        
      /*  //syslog
        String logString = "";
        
        logString =
            "登录名:" + ((User)this.getSession().getAttribute("SOC_LOGON_USER")).getUserLoginName() + " 源IP:"
                + getRequest().getRemoteAddr() + " 操作时间:" + DateUtil.curDateTimeStr19() + " 操作类型 :邮箱设置";
        
        logManager.writeSystemAuditLog(logString);*/
        logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
				.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "修改邮箱设置");
        
		return SUCCESS;
	}

	/**
	 * 获取邮箱设置信息
	 * 
	 * @return
	 */
	public void mailInstance() {
	    
		HttpServletRequest request = super.getRequest();
		HttpServletResponse response = super.getResponse();
		
		//获得传递过来的邮箱地址
		String mail_whole = request.getParameter("mail");
		
		String mailStr = mail_whole.substring(mail_whole.indexOf("@") + 1);
		
		//查询库内对应的对象
		MailserversInstance mail = mailManager.queryByName(mailStr);
		
		String okStr = "";
		try {
			if (mail != null) {

				okStr = mail.getMsiSmtpname();
				
				if (mail.getMsiPop3ssl() != null && mail.getMsiPop3ssl() == 1) {
					okStr = okStr + "|" + mail_whole;
				} else {
					okStr = okStr + "|"
							+ mail_whole.substring(0, mail_whole.indexOf("@"));
				}
				if (mail.getMsiSmtpssl() != null && mail.getMsiSmtpssl() == 1) {
					okStr = okStr + "|" + mail.getMsiSmtpport() + "|" + 1;
				} else {
					okStr = okStr + "|" + "25" + "|" + 0;//改动过
				}
				response.getWriter().write(okStr);
			} else {
				okStr = "smtp."
						+ mail_whole.substring(mail_whole.indexOf("@") + 1);
				okStr = okStr + "|"
						+ mail_whole.substring(0, mail_whole.indexOf("@"));
				okStr = okStr + "|25|" + 1;
				response.getWriter().write(okStr);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 查询需要ssl连接的端口号
	 * <功能详细描述>
	 * @see [类、类#方法、类#成员]
	 */
    public void mailSelectPort()
    {
        HttpServletRequest request = super.getRequest();
        HttpServletResponse response = super.getResponse();
        
        String smtpServer= request.getParameter("smtpServer");
        try { 
           String mailStr = smtpServer.substring(smtpServer.indexOf("@") + 1);
           //查询库内对应的对象
           MailserversInstance mail = mailManager.queryByName(mailStr);
        
           String port=mail.getMsiSmtpport();
           if(port==null||port.equals(""))
           {
               port="25";
           }
           //System.out.println(port);
           response.getWriter().write(port);
        }catch (IOException e) {
            e.printStackTrace();
        }
        
    }
	public SettingService getSettingManager() {
		return settingManager;
	}

	public void setSettingManager(SettingService settingManager) {
		this.settingManager = settingManager;
	}

	public MailServersInstanceService getMailManager() {
		return mailManager;
	}

	public void setMailManager(MailServersInstanceService mailManager) {
		this.mailManager = mailManager;
	}

	public MailserversInstance getMailser() {
		return mailser;
	}

	public void setMailser(MailserversInstance mailser) {
		this.mailser = mailser;
	}

	public String getNicky() {
		return nicky;
	}

	public void setNicky(String nicky) {
		this.nicky = nicky;
	}

	public String getMailAddr() {
		return mailAddr;
	}

	public void setMailAddr(String mailAddr) {
		this.mailAddr = mailAddr;
	}

	public String getMailSign() {
		return mailSign;
	}

	public void setMailSign(String mailSign) {
		this.mailSign = mailSign;
	}

	public String getSmtpServer() {
		return smtpServer;
	}

	public void setSmtpServer(String smtpServer) {
		this.smtpServer = smtpServer;
	}

	public int getIsNeedAuth() {
		return isNeedAuth;
	}

	public void setIsNeedAuth(int isNeedAuth) {
		this.isNeedAuth = isNeedAuth;
	}

	public int getIsNeedSsl() {
		return isNeedSsl;
	}

	public void setIsNeedSsl(int isNeedSsl) {
		this.isNeedSsl = isNeedSsl;
	}

	public String getMailUserName() {
		return mailUserName;
	}

	public void setMailUserName(String mailUserName) {
		this.mailUserName = mailUserName;
	}

	public String getMailUserPwd() {
		return mailUserPwd;
	}

	public void setMailUserPwd(String mailUserPwd) {
		this.mailUserPwd = mailUserPwd;
	}

	public String getMailSmtpPort() {
		return mailSmtpPort;
	}

	public void setMailSmtpPort(String mailSmtpPort) {
		this.mailSmtpPort = mailSmtpPort;
	}

	public AuditService getAuditManager() {
		return auditManager;
	}

	public void setAuditManager(AuditService auditManager) {
		this.auditManager = auditManager;
	}


	// 测试使用
//	public String settingMail() {
//		return SUCCESS;
//	}
}
