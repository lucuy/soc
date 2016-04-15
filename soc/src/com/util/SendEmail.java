package com.util;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.soc.dao.systemsetting.SettingDao;
import com.util.Base64;
import com.util.DES;
import com.util.StringUtil;

/**
 * 邮件发送应用类
 * 
 */
public class SendEmail
{
	private MimeMessage mimeMsg; 

	private Session session; 

	private Properties props; 

	private boolean needAuth; 

	private String username = ""; 

	private String password = "";

	private Multipart mp; 
	
	private boolean isHavaCoptyto=false;
	
	private boolean isHavaBcc=false;

	public SendEmail(String smtpHost) {
		setSmtpHost(smtpHost);
		createMimeMessage();
	}
	
	public SendEmail(EmailInfo email) 
	{
		this.setSmtpHost(email.getMailHost());
		this.setSmtpPort(email.getMailPort());
		this.setNamePass(email.getUserName(), email.getUserPassword());
		createMimeMessage();
		this.setFrom(email.getMailFrom());
		this.setTo(email.getMailTo());
		if(email.getMailCopyTo()!=null&&email.getMailCopyTo()!="")
		{
			isHavaCoptyto=true;
			this.setCopyTo(email.getMailCopyTo());
		}
		if(email.getMailBcc()!=null&&email.getMailBcc()!="")
		{
			isHavaBcc=true;
			this.setBcc(email.getMailBcc());
		}
		this.setSubject(email.getMailSubject());
		//this.setBody(email.getMailContent());
		this.setNeedAuth(true);
		if(email.getMailAffixDir()!=null&&email.getMailAffixDir()!="")
		{
			this.addFileAffix(email.getMailAffixDir());
		}
			
		
	}

	/**
	 * @param hostName String
	 */
	public void setSmtpHost(String hostName) 
	{
		////System.out.println("设置系统属性：mail.smtp.host = " + hostName);
		if (props == null)
			props = System.getProperties(); 

		props.put("mail.smtp.host", hostName);
	}
	
	
	public void setSmtpPort(String port) 
	{
		////System.out.println("设置系统属性：mail.smtp.prot = " +prot );
		if (props == null)
			props = System.getProperties();
		
		if(port!=null && port!="")
		{
			props.put("mail.smtp.port", port); 
		}
		else
		{
			props.put("mail.smtp.port", "25"); 
		}
	}
	

	/**
	 * @return boolean
	 */
	public boolean createMimeMessage() {
		try {
			////System.out.println("准备获取邮件会话对象！");
			session = Session.getDefaultInstance(props, null); 
		} catch (Exception e) {
			System.err.println("获取邮件会话对象时发生错误！" + e);
			return false;
		}

		////System.out.println("准备创建MIME邮件对象！");
		try {
			mimeMsg = new MimeMessage(session); 
			mimeMsg.setHeader("Content-Type","text/html;charset=utf-8");
			mp = new MimeMultipart();

			return true;
		} catch (Exception e) {
			//System.err.println("创建MIME邮件对象失败！" + e);
			return false;
		}
	}

	/**
	 * @param need
	 *            boolean
	 */
	public void setNeedAuth(boolean need) 
	{
		////System.out.println("设置smtp身份认证：mail.smtp.auth = " + need);
		if (props == null)
			props = System.getProperties();

		if (need) {
			props.put("mail.smtp.auth", "true");
		} else {
			props.put("mail.smtp.auth", "false");
		}
	}

	/**
	 * @param name
	 *            String
	 * @param pass
	 *            String
	 */
	public void setNamePass(String name, String pass) 
	{
		username = name;
		password = pass;
	}

	/**
	 * @param mailSubject
	 *            String
	 * @return boolean
	 */
	public boolean setSubject(String mailSubject)
	{
		////System.out.println("设置邮件主题！");
		try {
			mimeMsg.setSubject(MimeUtility.encodeText(mailSubject, "GB2312", "B"));
			return true;
		} catch (Exception e) {
			//System.err.println("设置邮件主题发生错误！");
			return false;
		}
	}

	/**
	 * @param mailBody
	 *            String
	 */
	public boolean setBody(String mailBody)
	{
		try {
			BodyPart bp = new MimeBodyPart();
			bp.setContent(mailBody,"text/html;charset=utf-8");
			mp.addBodyPart(bp);

			return true;
		} catch (Exception e) {
			//System.err.println("设置邮件正文时发生错误！" + e);
			return false;
		}
	}

	/**
	 * @param name
	 *            String
	 * @param pass
	 *            String
	 */
	public boolean addFileAffix(String filename) 
	{

		////System.out.println("增加邮件附件：" + filename);
		try {
			BodyPart bp = new MimeBodyPart();
			FileDataSource fileds = new FileDataSource(filename);
			bp.setDataHandler(new DataHandler(fileds));
			bp.setFileName(new String(fileds.getName().getBytes(),"ISO8859-1"));

			mp.addBodyPart(bp);

			return true;
		} catch (Exception e) {
			//System.err.println("增加邮件附件：" + filename + "发生错误！" + e);
			return false;
		}
	}

	/**
	 * @param name
	 *            String
	 * @param pass
	 *            String
	 */
	public boolean setFrom(String from) 
	{
		////System.out.println("设置发信人！");
		try {
			mimeMsg.setFrom(new InternetAddress(from)); 
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * @param name
	 *            String
	 * @param pass
	 *            String
	 */
	public boolean setTo(String to) 
	{
		if (to == null)
			return false;
		try {
			mimeMsg.setRecipients(Message.RecipientType.TO, InternetAddress
					.parse(to));
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * @param name
	 *            String
	 * @param pass
	 *            String
	 */
	public boolean setCopyTo(String copyto) 
	{
		if (copyto == null)
			return false;
		try {
			mimeMsg.setRecipients(Message.RecipientType.CC,
					(Address[]) InternetAddress.parse(copyto));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * @param name
	 *            String
	 * @param pass
	 *            String
	 */
	public boolean setBcc(String bcc) 
	{
		if (bcc == null)
			return false;
		try {
			mimeMsg.setRecipients(Message.RecipientType.BCC,
					(Address[]) InternetAddress.parse(bcc));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**     
	*   @param   need   是否要求认证     
	*/     
	public   void   setNeedSsl(boolean   need) {     
		if(props   ==   null)     
			props   =   System.getProperties();   
		if(need){     
			props.put("mail.smtp.starttls.enable","true");     
		}else{     
			props.put("mail.smtp.starttls.enable","false");     
		}     
	}  
	
	public void setSSLSecurity(String port) {
		props.put("mail.smtp.socketFactory.port", port);
		props.put("mail.smtp.port", port); 
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
	}
	
	/**
	 * 邮件发送
	 * @return
	 */
	public boolean sendout()
	{
		try {
			mimeMsg.setContent(mp);
			mimeMsg.saveChanges();
			////System.out.println("正在发送邮件....");
			
			Session mailSession;
			
			EmailAuthenticator   myauth=   new EmailAuthenticator(this.username, this.password);   
			mailSession   =   Session.getInstance(props,myauth); 
			Transport transport = mailSession.getTransport("smtp");
			transport.connect((String) props.get("mail.smtp.host"), username,
					password);
			transport.sendMessage(mimeMsg, mimeMsg
					.getRecipients(Message.RecipientType.TO));
			
			if(isHavaCoptyto)
			{
				transport.sendMessage(mimeMsg, mimeMsg
						.getRecipients(Message.RecipientType.CC));
			}
			
			if(isHavaBcc)
			{
				transport.sendMessage(mimeMsg, mimeMsg
						.getRecipients(Message.RecipientType.BCC));
			}
			
			//System.out.println("发送邮件成功！");
			transport.close();

			return true;
		} catch (Exception e) {
			System.err.println("邮件发送失败！" + e);
			e.printStackTrace();
			return false;
			
		}
	}

	/**
	 * sso受到策略限制-发送邮件
	 * @param session
	 */
	public static void sendEmail(HttpSession session,String message){
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(session.getServletContext());
		SettingDao settingdao=(SettingDao)wac.getBean("settingDao");
		String mailAlarm=settingdao.queryByKey("mailAlarm");
		if(StringUtil.isNotBlank(mailAlarm)){
			int mailAlarmInt=Integer.parseInt(mailAlarm);
			if(mailAlarmInt==1){
				//获得邮箱参数
				String smtpServer=settingdao.queryByKey("smtp_server");
				String emailNicky=settingdao.queryByKey("email_nicky");
				String smtpPort=settingdao.queryByKey("smtp_port");
				String emailStr=settingdao.queryByKey("email");
				String smtpIsneedauth=settingdao.queryByKey("smtp_isneedauth");
				String smtpIsneedssl=settingdao.queryByKey("smtp_isneedssl");
				String emailUsername=settingdao.queryByKey("email_username");
				String emailPass=settingdao.queryByKey("email_password");
				String emailAddress=settingdao.queryByKey("email_address");
				//
				if(StringUtil.isNotBlank(smtpServer)&&StringUtil.isNotBlank(emailNicky)&&StringUtil.isNotBlank(smtpPort)
						&&StringUtil.isNotBlank(smtpIsneedauth)&&StringUtil.isNotBlank(emailUsername)&&StringUtil.isNotBlank(emailPass)
						&&StringUtil.isNotBlank(emailAddress)){
					//邮箱密码解密
					String emailPassword=DES.getEncrypt(Base64.decodeString(emailPass));
					if(emailStr.indexOf(",")==-1){
						SendEmail sendEmail=new SendEmail(smtpServer);
						sendEmail.setBody(emailNicky+"您好：<br/>"+message);
						sendEmail.setSubject("异常报告");
						sendEmail.setTo(emailStr);
						if(StringUtil.isNotBlank(smtpIsneedssl)){
							int isssl=Integer.parseInt(smtpIsneedssl);
							if(isssl==1){
								sendEmail.setNeedSsl(true);
								sendEmail.setSSLSecurity(smtpPort);
							}else{
								//sendEmail.setNeedSsl(false);
								sendEmail.setSmtpPort(smtpPort);
							}
						}else{
							sendEmail.setNeedSsl(false);
							sendEmail.setSmtpPort(smtpPort);
						}
						if(Integer.parseInt(smtpIsneedauth)==1){
							sendEmail.setNeedAuth(true);
						}
						else{
							sendEmail.setNeedAuth(false);
						}
						sendEmail.setNamePass(emailUsername,emailPassword);
						sendEmail.setFrom(emailAddress);
						sendEmail.sendout();
					}else{
						String []str=emailStr.split(",");
						for(String email:str){
							SendEmail sendEmail=new SendEmail(smtpServer);
							sendEmail.setBody(emailNicky+"您好：<br/>"+message);
							sendEmail.setSubject("异常报告");
							sendEmail.setTo(email);
							if(StringUtil.isNotBlank(smtpIsneedssl)){
								int isssl=Integer.parseInt(smtpIsneedssl);
								if(isssl==1){
									sendEmail.setNeedSsl(true);
									sendEmail.setSSLSecurity(smtpPort);
								}else{
									sendEmail.setNeedSsl(false);
									sendEmail.setSmtpPort(smtpPort);
								}
							}else{
								sendEmail.setNeedSsl(false);
								sendEmail.setSmtpPort(smtpPort);
							}
							if(Integer.parseInt(smtpIsneedauth)==1){
								sendEmail.setNeedAuth(true);
							}
							else{
								sendEmail.setNeedAuth(false);
							}
							sendEmail.setNamePass(emailUsername,emailPassword);
							sendEmail.setFrom(emailAddress);
							sendEmail.sendout();
							try {
								Thread.sleep(5000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
	}
	
	public static void main(String[] argv) {
	
		/*SendEmail inst = new SendEmail("smtp.gmail.com");
		inst.setBody("尊敬的郭煜玺<br /><br />asdf");
		inst.setSubject("111");
		inst.setTo("yuxiguo@gmail.com");
		inst.setNeedSsl(true);
		inst.setSSLSecurity("465");
		inst.setNeedAuth(true);
		inst.setNamePass("wwoyaotest", "woyaotest123");
		inst.setFrom("wwoyaotest@gmail.com");
		inst.sendout();*/
		
		SendEmail inst_jidi=new SendEmail("smtp.gmail.com");
		inst_jidi.setBody("五一放假通知、、、ok!");
		inst_jidi.setSubject("通知");
		inst_jidi.setTo("dreams_hope@126.com");
		inst_jidi.setNeedSsl(true);
		//inst_jidi.setSmtpPort("465");
		inst_jidi.setSSLSecurity("25");
		inst_jidi.setNeedAuth(true);
		//System.out.println("dddddddddd");
		inst_jidi.setNamePass("haohaode110","haohaode_153544");
		inst_jidi.setFrom("haohaode110@gmail.com");
		inst_jidi.sendout();
	}
}
