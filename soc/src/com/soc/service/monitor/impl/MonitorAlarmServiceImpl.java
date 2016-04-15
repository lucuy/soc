package com.soc.service.monitor.impl;

import global.GlobalThreadPool;

import java.util.List;
import java.util.Map;

import com.soc.dao.monitor.MonitorAlarmDao;
import com.soc.dao.systemsetting.SettingDao;
import com.soc.dao.user.UserDao;
import com.soc.model.asset.Asset;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.monitor.MonitorAlarm;
import com.soc.model.user.User;
import com.soc.service.monitor.MonitorAlarmService;
import com.soc.webapp.listener.CommunicationContextListener;
import com.util.Base64;
import com.util.DES;
import com.util.DateUtil;
import com.util.SendEmail;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;

public class MonitorAlarmServiceImpl implements MonitorAlarmService
{
    
    private MonitorAlarmDao monitorAlarmDao;
    private SettingDao settingDao ; 
    private UserDao userDao ;
    
    /**
     * {@inheritDoc}
     */
    public int count(Map map)
    {
        return monitorAlarmDao.count(map);
    }
    
    /**
     * {@inheritDoc}
     */
    public SearchResult query(Map map, Page page)
    {
        // 按Map中存储的条件查找告警列表
        int rowsCount = monitorAlarmDao.count(map);
        page.setTotalCount(rowsCount);
        List<MonitorAlarm> list = monitorAlarmDao.query(map, page.getStartIndex(), page.getPageSize());
        
        // 对查找的告警列表做分页处理
        SearchResult sr = new SearchResult();
        sr.setList(list);
        sr.setPage(page);
        
        return sr;
    }
    
    /**
     * {@inheritDoc}
     */
    public long insert(MonitorAlarm monitorAlarm)
    {
    	//添加阀值告警入库功能
    	long l = monitorAlarmDao.insert(monitorAlarm);
    	Asset asset = GlobalConfig.assetGlobleMap.get(monitorAlarm.getMonitorAlarmAssetId()) ; 
    	//判断资产是否存在
    	if(asset != null){
    	
        	//邮件内容
        	StringBuffer message = new StringBuffer();
        	//用户email地址
        	StringBuffer userEmails = new StringBuffer() ; 

        	if(asset.getAsset_answer_user_id() != 0 && asset.getAsset_pessponsibility_user_id() != 0){
        		//查询资产相应人、责任人
        		List<User> userList = userDao.queryByIds(asset.getAsset_answer_user_id()+","+asset.getAsset_pessponsibility_user_id());
        		//拼接邮件内容
        		if(!userList.isEmpty()){
        			
        			message.append("管理员,您好：<br/>");
					message.append("<b style=\"margin-left:20px;\">SOC系统阀值告警信息：</b>");
					message.append("<table style=\"margin-left:20px;\" width=\"100%\"  cellspacing=\"0\" cellpadding=\"0\" ><tr>" +
							"<td width=\"20%\" align=\"center\" style=\"border-top:1px solid;border-left:1px solid; background:#CCCCCC ;\">告警名称</td>" +
							"<td align=\"center\" style=\"border-top:1px solid;border-left:1px solid;border-right:1px solid;background:#CCCCCC;\">资产名称</td>" +
							"<td align=\"center\" style=\"border-top:1px solid;border-left:1px solid;border-right:1px solid;background:#CCCCCC;\">资产IP</td>" +
							"<td align=\"center\" style=\"border-top:1px solid;border-left:1px solid;border-right:1px solid;background:#CCCCCC;\">设定阀值</td>" +
							"<td align=\"center\" style=\"border-top:1px solid;border-left:1px solid;border-right:1px solid;background:#CCCCCC;\">当前值</td>"
					       +  "<td align=\"center\" style=\"border-top:1px solid;border-left:1px solid;border-right:1px solid;background:#CCCCCC;\">告警时间</td>"                                               
					       + "</tr>"
							      );
					message.append("<tr><td  align=\"center\" style=\"border-top:1px solid;border-left:1px solid;border-bottom:1px solid;\">"
							+ monitorAlarm.getMonitorAlarmIndex()
							+ "</td>" 
							+"<td align=\"center\" style=\"border-top:1px solid;border-left:1px solid;border-bottom:1px solid;border-right:1px solid;\">"
							+ asset.getAssetName() 
							+ "</td>" 
							+"<td align=\"center\" style=\"border-top:1px solid;border-left:1px solid;border-bottom:1px solid;border-right:1px solid;\">"
							+ asset.getAssetMac() 
							+ "</td>" 
							+"<td align=\"center\" style=\"border-top:1px solid;border-left:1px solid;border-bottom:1px solid;border-right:1px solid;\">"
							+ monitorAlarm.getMonitorAlarmThreshold()
							+ "</td>" 
							+ "<td align=\"center\" style=\"border-top:1px solid;border-left:1px solid;border-bottom:1px solid;border-right:1px solid;\">"
							+ monitorAlarm.getMonitorAlarmCurrentValue()
							+ "</td>" 
							+ "<td align=\"center\" style=\"border-top:1px solid;border-left:1px solid;border-bottom:1px solid;border-right:1px solid;\">"
							+ DateUtil.putDateToTimeStr19(monitorAlarm.getMonitorAlarmTime())
							+ "</td>"
						    + "</tr>");
					message.append("</table>");

        	        //遍历userList  --  拼接用户地址
    	            for(User user : userList){
    	            	userEmails.append(user.getUserEmail()+",");
    	            }
	    	
		    	    //使用线程池发送邮件
			    	if (CommunicationContextListener.threadPoolId <= GlobalConfig.MAX_THREADPOOL_NUMBER) {
						CommunicationContextListener.threadPoolId++;
						GlobalThreadPool.pool.execute(createThread(
								message.toString(), userEmails.toString()));
						CommunicationContextListener.threadPoolId--;
					}
        		}
        	}
        }
    	
        return l ;
    }
    
    
    
    /**
	 * 创建线程并启动
	 * 
	 * @return
	 */
	public Runnable createThread(final String message, final String toEmialAdds) {
		return new Runnable() {
			public void run() {
				sendEmail(message, toEmialAdds);
			}
		};
	}
	
    public void sendEmail(String message, String toEmialAdds) {
		// 获得邮箱参数
		String smtpServer = settingDao.queryByKey("smtp_server");
		String emailNicky = settingDao.queryByKey("email_nicky"); // 发件人昵称
		String smtpPort = settingDao.queryByKey("smtp_port");
		String smtpIsneedauth = settingDao.queryByKey("smtp_isneedauth");
		String smtpIsneedssl = settingDao.queryByKey("smtp_isneedssl");
		String emailUsername = settingDao.queryByKey("email_username");
		String emailPass = settingDao.queryByKey("email_password");
		String emailAddress = settingDao.queryByKey("email_address");
		if (StringUtil.isNotBlank(smtpServer)
				&& StringUtil.isNotBlank(emailNicky)
				&& StringUtil.isNotBlank(smtpPort)
				&& StringUtil.isNotBlank(smtpIsneedauth)
				&& StringUtil.isNotBlank(emailUsername)
				&& StringUtil.isNotBlank(emailPass)
				&& StringUtil.isNotBlank(emailAddress)) {
			String emailPassword = DES.getEncrypt(Base64
					.decodeString(emailPass));
			String[] emailAdds;
			if (toEmialAdds.indexOf(",") != -1) {
				emailAdds = toEmialAdds.toString().split(",");
			} else {
				emailAdds = new String[1];
				emailAdds[0] = toEmialAdds.toString();
			}
			for (String emailAdd : emailAdds) {
				SendEmail sendEmail = new SendEmail(smtpServer);
				sendEmail.setBody(message);
				sendEmail.setSubject("阀值告警");
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
    
    @Override
	public List<Map<String,Object>> queryTenAlarmMessage(Map map) {
		// TODO Auto-generated method stub
		return monitorAlarmDao.queryTenAlarmMessage(map);
	}

    public MonitorAlarmDao getMonitorAlarmDao()
    {
        return monitorAlarmDao;
    }

    public void setMonitorAlarmDao(MonitorAlarmDao monitorAlarmDao)
    {
        this.monitorAlarmDao = monitorAlarmDao;
    }

	public SettingDao getSettingDao() {
		return settingDao;
	}

	public void setSettingDao(SettingDao settingDao) {
		this.settingDao = settingDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

    
    
	
    
}
