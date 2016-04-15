package com.util.email;

import global.GlobalThreadPool;

import java.text.SimpleDateFormat;

import com.soc.model.conf.GlobalConfig;
import com.soc.model.user.User;
import com.soc.model.workorder.WorkOrder;
import com.soc.service.systemsetting.SettingService;
import com.soc.webapp.listener.CommunicationContextListener;
import com.util.Base64;
import com.util.DES;
import com.util.SendEmail;
import com.util.StringUtil;

public class WorkOrderSendMail {
	private User user;
	private WorkOrder workOrder;
	private SettingService settingManager;
	private WorkOrderSendMail(){
		
	}
	
	private  WorkOrderSendMail(User user, WorkOrder workOrder,
			SettingService settingManager) {
		this.user = user;
		this.workOrder = workOrder;
		this.settingManager = settingManager;
	}

	public static WorkOrderSendMail initialization (User user, WorkOrder workOrder,
			SettingService settingManager){
		
		return new WorkOrderSendMail(user, workOrder, settingManager);
	}
	/**
	 * 处理发送邮件的内容信息
	 * 
	 */
	public void emailHandling() {
		
		StringBuffer message = new StringBuffer(); // 邮件内容
		

		if (user!=null && workOrder != null) {
				if (StringUtil.isNotEmpty(user.getUserEmail())) {
					message.append("管理员您好！<br/>");
					message.append("您有新的未处理工单，具体信息如下：");
					message.append("<b style=\"margin-left:20px;\">SOC系统工单信息：</b>");
					message.append("<table style=\"margin-left:20px;\" width=\"100%\"  cellspacing=\"0\" cellpadding=\"0\" >"
							+ "<tr>"
							+ "<td width=\"10%\" align=\"center\" style=\"border-top:1px solid;border-left:1px solid; background:#CCCCCC ;\">工单编号</td>"
							+ "<td width=\"10%\" align=\"center\" style=\"border-top:1px solid;border-left:1px solid; background:#CCCCCC ;\">工单名称</td>"
							+ "<td width=\"10%\" align=\"center\" style=\"border-top:1px solid;border-left:1px solid; background:#CCCCCC ;\">紧急程度</td>"
							+ "<td width=\"10%\" align=\"center\" style=\"border-top:1px solid;border-left:1px solid; background:#CCCCCC ;\">发布时间</td>"
							+ "<td width=\"15%\" align=\"center\" style=\"border-top:1px solid;border-left:1px solid; background:#CCCCCC ;\">派发人</td>"
							+ "<td width=\"30%\" align=\"center\" style=\"border-top:1px solid;border-left:1px solid; background:#CCCCCC ;\">描述</td>"
							+ "</tr>");
					String level ="";
					switch (workOrder.getExigencyLevel()) {
					case 0:
						level="紧急";
						break;
					case 1:
						level="重要";
						
						break;
					case 2:
						level="次要";
						
						break;
					case 3:
						level="一般";
						
						break;

					
					}
					SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					message.append("<tr>"
							+ "<td  align=\"center\" style=\"border-top:1px solid;border-left:1px solid;border-bottom:1px solid;\">"
							+ workOrder.getWorkOrderId()
							+ "</td><td align=\"center\" style=\"border-top:1px solid;border-left:1px solid;border-bottom:1px solid;border-right:1px solid;\">"
							+ workOrder.getWorkOrderName()
							+ "</td><td align=\"center\" style=\"border-top:1px solid;border-left:1px solid;border-bottom:1px solid;border-right:1px solid;\">"
							+ level
							+ "</td><td align=\"center\" style=\"border-top:1px solid;border-left:1px solid;border-bottom:1px solid;border-right:1px solid;\">"
							+ sf.format(workOrder.getProduceDate())
							+ "</td><td align=\"center\" style=\"border-top:1px solid;border-left:1px solid;border-bottom:1px solid;border-right:1px solid;\">"
							+ workOrder.getWorkOrderStart()
							+ "</td><td align=\"center\" style=\"border-top:1px solid;border-left:1px solid;border-bottom:1px solid;border-right:1px solid;\">"
							+ workOrder.getWorkOrderDescribe()
							 +"</td></tr>");
					message.append("</table>");
					if (CommunicationContextListener.threadPoolId <= GlobalConfig.MAX_THREADPOOL_NUMBER) {
						CommunicationContextListener.threadPoolId++;
						GlobalThreadPool.pool.execute(creatThread(
								message.toString(), user.getUserEmail()));
						CommunicationContextListener.threadPoolId--;
					}
				}
			
		}
	}

	/**
	 * 发送邮件线程
	 * 
	 * @param message
	 * @param toEmailAdd
	 * @return Runable
	 */
	private Runnable creatThread(final String message, final String toEmailAdd) {
		return new Runnable() {
			public void run() {
				sendEmail(message, toEmailAdd);
			}
		};
	}

	/**
	 * {@inheritDoc}发送邮件
	 */
	private void sendEmail(String message, String emailAdd) {
		// 获得邮箱参数
		String smtpServer = settingManager.queryByKey("smtp_server");
		String emailNicky = settingManager.queryByKey("email_nicky"); // 发件人昵称
		String smtpPort = settingManager.queryByKey("smtp_port");
		String smtpIsneedauth = settingManager.queryByKey("smtp_isneedauth");
		String smtpIsneedssl = settingManager.queryByKey("smtp_isneedssl");
		String emailUsername = settingManager.queryByKey("email_username");
		String emailPass = settingManager.queryByKey("email_password");
		String emailAddress = settingManager.queryByKey("email_address");
		if (StringUtil.isNotBlank(smtpServer)
				&& StringUtil.isNotBlank(emailNicky)
				&& StringUtil.isNotBlank(smtpPort)
				&& StringUtil.isNotBlank(smtpIsneedauth)
				&& StringUtil.isNotBlank(emailUsername)
				&& StringUtil.isNotBlank(emailPass)
				&& StringUtil.isNotBlank(emailAddress)) {
			String emailPassword = DES.getEncrypt(Base64
					.decodeString(emailPass));
			
			
				SendEmail sendEmail = new SendEmail(smtpServer);
				sendEmail.setBody(message);
				sendEmail.setSubject("工单信息");
				sendEmail.setTo(emailAdd);
				if (StringUtil.isNotBlank(smtpIsneedssl)) {
					// 设置发送邮件服务器是否需要安全连接(SSL)
					int isssl = Integer.parseInt(smtpIsneedssl);
					if (isssl == 1) {
						sendEmail.setNeedSsl(true);
						sendEmail.setSSLSecurity(smtpPort);
					} else {
						// sendEmail.setNeedSsl(false);
						sendEmail.setSmtpPort(smtpPort);
					}
				} else {
					sendEmail.setNeedSsl(false);
					sendEmail.setSmtpPort(smtpPort);
				}
				// 发送邮件服务器是否需要身份验证
				if (Integer.parseInt(smtpIsneedauth) == 1) {
					sendEmail.setNeedAuth(true);
				} else {
					sendEmail.setNeedAuth(false);
				}
				sendEmail.setNamePass(emailUsername, emailPassword);
				sendEmail.setFrom(emailAddress);
				sendEmail.sendout();
			
		}
	}
}
