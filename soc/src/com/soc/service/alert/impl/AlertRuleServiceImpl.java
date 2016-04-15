package com.soc.service.alert.impl;

import global.GlobalThreadPool;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.smslib.GatewayException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.util.SendMessage;
import com.lowagie.text.pdf.PRAcroForm;
import com.soc.dao.alert.AlertMessageDao;
import com.soc.dao.alert.AlertRuleDao;
import com.soc.dao.systemsetting.SettingDao;
import com.soc.dao.user.UserDao;
import com.soc.model.alert.AlertInteractionTable;
import com.soc.model.alert.AlertRule;
import com.soc.model.alert.alertMessage.AlertMessage;
import com.soc.model.asset.Asset;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.user.User;
import com.soc.service.alert.AlertRuleService;
import com.soc.service.asset.AssetService;
import com.soc.service.impl.BaseServiceImpl;
import com.soc.service.securityPolicy.SecurityPolicyService;
import com.soc.webapp.listener.CommunicationContextListener;
import com.util.Base64;
import com.util.DES;
import com.util.DateUtil;
import com.util.IpConvert;
import com.util.SendEmail;
import com.util.StringUtil;
import com.util.SysLogSender;
import com.util.ThreadPool;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * 告警规则Service <对规则的相关操作：查询,添加，删除>
 * 
 * @author jiadongxu
 * @version V100R001C001
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class AlertRuleServiceImpl extends BaseServiceImpl implements
		AlertRuleService {

	private AlertRuleDao alertRuleDao;

	private AlertMessageDao alertMessgeDao; // 告警事件

	private UserDao userDao;

	private SettingDao settingDao;
	// 资产服务管理类
	private AssetService assetManager;
	//安全策略管理业务处理类
	private SecurityPolicyService securityPolicyManager;
	// 线程池
	public static ThreadPool threadPool = null;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int count(Map map) {
		// TODO Auto-generated method stub
		return alertRuleDao.count(map);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SearchResult ruleQuery(Map map, Page page) {
		// TODO Auto-generated method stub
		int rowsCount = alertRuleDao.count(map);
		page.setTotalCount(rowsCount);
		List<AlertRule> list = alertRuleDao.queryRule(map,
				page.getStartIndex(), page.getPageSize());
		// 对查找的审计列表做分页处理
		SearchResult sr = new SearchResult();
		sr.setList(list);
		sr.setPage(page);

		return sr;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AlertRule queryByRuleId(long ruleId) {
		// TODO Auto-generated method stub
		return alertRuleDao.queryByRuleId(ruleId);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<User> queryUserByRuleId(long ruleId) {
		// TODO Auto-generated method stub
		return alertRuleDao.queryUserByRuleId(ruleId);
	}

	/**
	 * {@inheritDoc}
	 */
	public long updateRule(AlertRule rule) {
		long ruleId;

		// 更新规则
		if (rule.getRuleId() != 0) {
			rule.setRuleUpdateTime(new Date());
			alertRuleDao.updateRule(rule);
			ruleId = rule.getRuleId();
		}

		// 添加规则
		else {
			rule.setRuleCreateTime(new Date());
			rule.setRuleUpdateTime(new Date());
			ruleId = alertRuleDao.insertRule(rule);
		}
		return ruleId;
	}

	/**
	 * {@inheritDoc}
	 */
	public void deleteRule(long ruleId) {
		alertRuleDao.deleteRule(ruleId);
	}

	/**
	 * {@inheritDoc}
	 */
	public void deleteRelUser(long ruleId) {
		alertRuleDao.deleteRelUser(ruleId);
	}

	/**
	 * {@inheritDoc}
	 */
	/*
	 * public void deleteRelAsset(long ruleId) {
	 * alertRuleDao.deleteRelAsset(ruleId); }
	 */

	/**
	 * {@inheritDoc}
	 */
	public void deleteRank(long ruleId) {
		alertRuleDao.deleteRank(ruleId);
	}

	/**
	 * {@inheritDoc}
	 */
	public void insertRelUser(Map map) {
		alertRuleDao.insertRelUser(map);
	}

	/**
	 * {@inheritDoc}
	 */
	/*
	 * public void insertRelAsset(Map map) { alertRuleDao.insertRelAsset(map); }
	 */

	/**
	 * {@inheritDoc}
	 */
	/*
	 * public List<Map> queryRelAsset(long ruleId) { List<Map> list;
	 * 
	 * // 取得相关资产 list = alertRuleDao.queryRelAsset(ruleId);
	 * 
	 * return list; }
	 */

	/**
	 * {@inheritDoc}
	 */
	public List<Map> queryRelUser(long ruleId) {
		List<Map> list;

		// 取得相关人员
		list = alertRuleDao.queryRelUser(ruleId);
		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Map> queryRelUserByRuleId(long ruleId) {
		return alertRuleDao.queryRelUserByRuleId(ruleId);
	}

	/**
	 * {@inheritDoc}
	 */
	/*
	 * public List<Map> queryRelAssetByRuleId(long ruleId) { return
	 * alertRuleDao.queryRelAssetByRuleId(ruleId); }
	 */

	/**
	 * 拼接等级操作
	 * 
	 * @param rank
	 *            String
	 * @return String
	 */
	public String setRank(String rank) {
		String iRank = "0,0,0,0,0";
		String[] rankMails = iRank.split(",");
		String[] newRans = rank.split(",");
		Integer r = 0;
		for (int j = 0; j < newRans.length; j++) {
			r = Integer.parseInt(newRans[j].trim());
			rankMails[r] = "1";
		}

		StringBuffer newRan = new StringBuffer();
		for (String s1 : rankMails) {
			newRan = newRan.append(s1 + ",");
		}
		String s = newRan.toString().substring(0, newRan.length()-1);
		return s.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	public void updateRank(Map map) {
		alertRuleDao.updateRank(map);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Map> queryRank(Map map) {
		return alertRuleDao.queryRank(map);
	}

	public AlertRuleDao getAlertRuleDao() {
		return alertRuleDao;
	}

	public void setAlertRuleDao(AlertRuleDao alertRuleDao) {
		this.alertRuleDao = alertRuleDao;
	}

	public List<AlertRule> queryAlertRule(long assetId, int oriPriority) {
		// TODO Auto-generated method stub
		List<AlertRule> alertRuleList = null;
		Map<String, Object> map = new HashMap<String, Object>();

		if (assetId != 0 && oriPriority != 0) {
			String levelStr = "";
			switch (oriPriority) {
			case 1:
				levelStr = "1,_,_,_,_";
				break;
			case 2:
				levelStr = "_,1,_,_,_";
				break;
			case 3:
				levelStr = "_,_,1,_,_";
				break;
			case 4:
				levelStr = "_,_,_,1,_";
				break;
			case 5:
				levelStr = "_,_,_,_,1";
				break;
			}
			map.put("levelStr", levelStr);
			map.put("assetId", assetId);
			alertRuleList = alertRuleDao.queryAlertRuleByAssetIdAndLevel(map);
		}

		return alertRuleList;
	}

	// 发送syslog信息
	private void sendSyslogMethod(String str) {
		SysLogSender.sender(str);
	}
	
	@Override
	public void logAlarmHandling(Map<String, Object> dataMap,
			List<AlertRule> alertRuleList) {
		StringBuffer userEmails = new StringBuffer(); // 用户Email地址
		StringBuffer message = new StringBuffer(); // 邮件内容
		String levelStr = "";
		int levelL = 0;
		for (AlertRule alertRule : alertRuleList) {
			if (getAlarmFalg(alertRule, dataMap) == 1) {
				// 告警级别标识
				/*
				 * Map<String, String> levelFalg = analyticLevel(
				 * alertRule.getRuleLevelValue(), levelL);
				 */
				try {
					levelL = Integer.parseInt(dataMap.get("oriPriority")
							.toString());
				} catch (NumberFormatException n) {
					n.printStackTrace();
				}

				// 查询告警规则对应的用户
				String userIds = alertRule.getUserId();

				List<User> userList = null;

				if (userIds.length() > 0) {
					userIds = userIds.substring(0, userIds.lastIndexOf(","));

					userList = userDao.queryByIds(userIds.toString());
				}
				StringBuffer userRealNames = new StringBuffer();
				List<String> phoneList = new ArrayList<String>();

				// 查询告警对应的user集合
				if (userList != null) {
					for (User user : userList) {
						if (user.getUserMobile().length() > 0) {
							phoneList.add(user.getUserMobile());
						}
						if (userRealNames.length() > 0) {
							userRealNames.append("," + user.getUserRealName());
						} else {
							userRealNames.append(user.getUserRealName());
						}

						if (userEmails.length() > 0) {
							userEmails.append("," + user.getUserEmail());
						} else {
							userEmails.append(user.getUserEmail());
						}

					}
				}

				// 构造告警对象
				AlertMessage alertMessage = new AlertMessage();
				// alertMessage.setAlertNumber(Integer.parseInt((String)dataMap.get("num")));
				alertMessage.setAlertRank(levelL);
				// alertMessage.setSendMode(levelFalg.get("levelName"));
				alertMessage.setAlertState(0);
				alertMessage.setAlertEventName((String) dataMap
						.get("eventsName"));
				alertMessage.setAlertEventType((String) dataMap
						.get("eventsType"));
				alertMessage.setAlertDeviceIp((String) dataMap.get("deviceIp"));
				alertMessage.setAlertDeviceType((String) dataMap
						.get("deviceType"));
				alertMessage.setUserRealName(userRealNames.toString());
				alertMessage.setRelEventsIdentification(Long
						.valueOf((String) dataMap.get("identification")));
				alertMessage
						.setAlertCreateDatetime((Long) new Date().getTime());
				alertMessage.setAlertAssetId((Long) dataMap.get("assetId"));
				alertMessage.setAlertAssetName((String) dataMap
						.get("assetName"));
				alertMessage.setAlertLogTableName((String) dataMap
						.get("logTableName"));
				alertMessage.setAlarmRuleId(alertRule.getRuleId());
				alertMessage.setEventsSourceAdd((Long) dataMap.get("sAddr"));
				alertMessage.setEventsSourcePort((Long) dataMap.get("sPort"));
				alertMessage.setAlertDate(new Date());
				alertMessage.setAlertDeviceName(dataMap.get("DevName")
						.toString());
				alertMessage.setEventsTargetAdd((Long) dataMap.get("dAddr"));
				alertMessage.setMessageOperateDate(new Date());
				alertMessage.setEventsTargetPort((Long) dataMap.get("dPort"));
				alertMessage.setEventSendTime((Long) dataMap.get("sendTime"));
				// 保存告警
				alertMessgeDao.insertAlertMessage(alertMessage);
				//执行脚本
				if(alertRule.getChangeScript()==1){
					Asset asset = assetManager.queryAssetByIp(alertMessage.getEventsTargetAdd());
					if(asset!=null){
						securityPolicyManager.actionPolicy(asset, alertRule.getScriptFile(),  GlobalConfig.securityPolicyFilePath);
					}
				}
				// 告警名称
				String eventsName = (String) (dataMap.get("eventsType") != null ? dataMap
						.get("eventsType") : "");

				/** 判断是否发送syslog */
				if (alertRule.getSendSyslog() == 0) {

					// 告警字符串
					StringBuffer msgsbf = new StringBuffer();
					try {
						eventsName = GlobalConfig.eventTypeTag.get(Long
								.parseLong(eventsName));
					} catch (Exception e) {
						eventsName = eventsName;
						log.info("转化错误!");
					}
					/*msgsbf.append("管理员您好！有新的告警产生。告警等级:" + levelL + ",告警名称:"
							+ eventsName);*/
					//告警级别
					msgsbf.append("告警级别："+ levelL +"\t");
					//事件名称
					String RelEventsName = alertMessage.getAlertEventName() ; 
					if(StringUtil.isNotEmpty(RelEventsName)){
						msgsbf.append("事件名称：" + RelEventsName +"\t");
					}
					//告警类型
					msgsbf.append("告警类型：" + eventsName+"\t") ;
					//设备名称
					String alertdriverName = alertMessage.getAlertDeviceName() ;
					if(StringUtil.isNotEmpty(alertdriverName)){
						msgsbf.append("设备名称："+ alertdriverName + "\t");
					}
					//源IP
					String soureIP = alertMessage.getEventsSourceAddT();
					if(StringUtil.isNotEmpty(soureIP)){
						msgsbf.append("源IP：" + soureIP + "\t");
					}
					//目标IP
					String targetIP = alertMessage.getEventsTargetAddT() ; 
					if(StringUtil.isNotEmpty(targetIP)){
						msgsbf.append("目标IP：" + targetIP + "\t");
					}
					//操作时间
					String date = alertMessage.getDate() ; 
					if(StringUtil.isNotEmpty(date)){
						msgsbf.append("产生时间：" + date + "\t");
					}
					//源端口
					long sPort = alertMessage.getEventsSourcePort();
					msgsbf.append("源端口：" + sPort + "\t") ; 
					
					//目标端口
					long tPort = alertMessage.getEventsTargetPort() ; 
					msgsbf.append("目标端口：" + tPort + "\t") ; 

					// 发送syslog信息
					sendSyslogMethod(msgsbf.toString());
				}

				/** 判断是否发送邮件 */
				if (alertRule.getSendMail() == 0) {
					if (userEmails.length() > 0) {

						message.append("管理员,您好：<br/>");
						message.append("<b style=\"margin-left:20px;\">SOC系统告警信息：</b>");
						message.append("<table style=\"margin-left:20px;\" width=\"100%\"  cellspacing=\"0\" cellpadding=\"0\" ><tr><td width=\"20%\" align=\"center\" style=\"border-top:1px solid;border-left:1px solid; background:#CCCCCC ;\">告警等级</td><td align=\"center\" style=\"border-top:1px solid;border-left:1px solid;border-right:1px solid;background:#CCCCCC;\">告警名称</td>" +
										"<td align=\"center\" style=\"border-top:1px solid;border-left:1px solid;border-right:1px solid;background:#CCCCCC;\">事件名称</td>"
								       +  "<td align=\"center\" style=\"border-top:1px solid;border-left:1px solid;border-right:1px solid;background:#CCCCCC;\">告警时间</td>"                                               
								       + "</tr>"
								      );
						message.append("<tr><td  align=\"center\" style=\"border-top:1px solid;border-left:1px solid;border-bottom:1px solid;\">"
								+ levelL
								+ "</td>" 
								+"<td align=\"center\" style=\"border-top:1px solid;border-left:1px solid;border-bottom:1px solid;border-right:1px solid;\">"
								+ eventsName 
								+ "</td>" 
								+ "<td align=\"center\" style=\"border-top:1px solid;border-left:1px solid;border-bottom:1px solid;border-right:1px solid;\">"
								+ alertMessage.getAlertEventName() 
								+ "</td>" 
								+ "<td align=\"center\" style=\"border-top:1px solid;border-left:1px solid;border-bottom:1px solid;border-right:1px solid;\">"
								+ alertMessage.getDate()
								+ "</td>"
							    + "</tr>");
						message.append("</table>");
						// sendEmail(message.toString(), userEmails.toString());
						/*
						 * threadPool = new com.util.ThreadPool(1); try {
						 * Thread.sleep(1000); } catch (InterruptedException e)
						 * { e.printStackTrace(); }
						 */
						if (CommunicationContextListener.threadPoolId <= GlobalConfig.MAX_THREADPOOL_NUMBER) {
							CommunicationContextListener.threadPoolId++;
							GlobalThreadPool.pool.execute(createThread(
									message.toString(), userEmails.toString()));
							CommunicationContextListener.threadPoolId--;
						}
					}
					message = new StringBuffer();
					userEmails = new StringBuffer();
					phoneList.clear();
				}
			}
		}

	}

	
	/**
	 * 将告警标识转换为汉字显示与发送标识
	 * 
	 * @param levelStr
	 * @param levle
	 * @return Map<String, String>
	 */
	/**
	public Map<String, String> analyticLevel(String levelStr, int levle) {
		Map<String, String> levelFalg = new HashMap<String, String>();
		String levelName = null;
		if (StringUtil.isNotEmpty(levelStr)) {

			// 用豆号截取字符串 ,每一段代表一种发送类型
			String[] levels = levelStr.split(",");
			if (levels.length > 0) {
				StringBuffer levelNameBuf = new StringBuffer();
				for (int i = 0; i < 4; i++) {

					
					   //取每一段判断当前级别位是否为字符1.如果是记录发送标记 0：邮件提示 1：平台提示 2：短信提示
					   // 3：snmp,trap/syslog提示
					
					if (StringUtil.equals("1",
							levels[i].substring(levle, levle + 1))) {
						if (levelNameBuf.length() > 0) {
							levelNameBuf.append("," + i);
						} else {
							levelNameBuf.append(i);
						}
					}
				}

				// 将标记转换为汉字
				levelName = levelNameBuf.toString();
				levelName = levelName.replaceAll("0", "邮件提示");
				levelName = levelName.replaceAll("1", "平台提示");
				levelName = levelName.replaceAll("2", "短信提示");
				levelName = levelName.replaceAll("3", "snmp,trap/syslog提示");
				if (StringUtil.isNotEmpty(levelNameBuf.toString())
						&& StringUtil.isNotEmpty(levelName)) {
					levelFalg.put("levelName", levelName);
					levelFalg.put("levelFalg", levelNameBuf.toString());
				}
			}
		}

		return levelFalg;
	}
*/
	/**
	 * <判断级别符合的条件是否别的条件合适> <功能详细描述>
	 * 
	 * @param alarmRuleRankList
	 * @param dataMap
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public int getAlarmFalg(AlertRule alarmRule, Map<String, Object> dataMap) {
		// 条件是否合适的标示;
		int flag = 0;

		try {
			// 获取告警规则关联的事件类型。
			String eventType = alarmRule.getAssetId();

			// 获取告警规则关联的事件类别
			String eventCategory = alarmRule.getDefinitionId();

			// 获取告警规则关联的设备名称
			String deviceName = alarmRule.getDeviceCategoryId();

			// 告警规则的源地址
			String SAddr = alarmRule.getSourceAddress();

			// 告警规则的目的地址
			String DAddr = alarmRule.getTargetAddress();

			int SAddrFlag = 0;

			// 判断源地址。
			if (SAddr != null && StringUtil.isNotEmpty(SAddr)) {
				long SAddrtemp = IpConvert.iPTransition(SAddr);

				if (Long.valueOf(String.valueOf(dataMap.get("SAddr"))) == SAddrtemp) {

					SAddrFlag = 1;
				} else {
					SAddrFlag = 0;
				}
			} else {
				SAddrFlag = 1;
			}

			// 目的地址标志位
			int DAddrFlag = 0;

			if (DAddr != null && StringUtil.isNotEmpty(DAddr)) {
				long DAddrtemp = IpConvert.iPTransition(DAddr);

				if (Long.valueOf(String.valueOf(dataMap.get("DAddr"))) == DAddrtemp) {

					DAddrFlag = 1;
				} else {
					DAddrFlag = 0;
				}
			} else {
				DAddrFlag = 1;
			}

			// 判断告警规则关联的事件类型内是否有事件的类型。
			if (eventType.contains((dataMap.get("eventsType") + ","))) {
				// 判断告警规则关联的事件级别捏是否有事件级别
				if (eventCategory.contains((dataMap.get("category") + ","))) {
					// 判断告警规则关联的设备名称是否包含事件的资产名称
					if (deviceName.contains((dataMap.get("assetId") + ","))) {
						if (DAddrFlag == 1 && SAddrFlag == 1) {
							flag = 1;
						} else {
							flag = 0;
						}
					}
				}
			}
		} catch (Exception e) {
			log.warn("处理告警时候产生错误!");
		}
		return flag;
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
				sendEmail.setSubject("事件告警");
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
	public AlertInteractionTable queryDeviceTypeByIdAll(long ruleId) {

		return alertRuleDao.queryDeviceTypeByIdAll(ruleId);
	}

	@Override
	public List<Map<String, Object>> queryDeviceType() {
		return alertRuleDao.queryDeviceType();
	}

	@Override
	public List<Map<String, Object>> queryAssetType() {
		return alertRuleDao.queryAssetType();
	}

	@Override
	public List<Map<String, Object>> queryDeviceByName() {
		return alertRuleDao.queryDeviceByName();
	}

	@Override
	public List<Map<String, Object>> queryAssetTypeById(String deviceCategoryId) {
		return alertRuleDao.queryAssetTypeById(deviceCategoryId);
	}

	@Override
	public List<Map<String, Object>> queryDeviceByNameById(String assetId) {
		return alertRuleDao.queryDeviceByNameById(assetId);
	}

	@Override
	public List<Map<String, Object>> queryDeviceTypeById(String definitionId) {
		return alertRuleDao.queryDeviceTypeById(definitionId);
	}

	@Override
	public List<Map<String, Object>> queryUserById(String string) {

		return alertRuleDao.queryUserById(string);
	}

	public AlertMessageDao getAlertMessgeDao() {
		return alertMessgeDao;
	}

	public void setAlertMessgeDao(AlertMessageDao alertMessgeDao) {
		this.alertMessgeDao = alertMessgeDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public SettingDao getSettingDao() {
		return settingDao;
	}

	public void setSettingDao(SettingDao settingDao) {
		this.settingDao = settingDao;
	}

	@Override
	public List<Map<String, Object>> queryEventType(String string) {
		// TODO Auto-generated method stub
		return alertRuleDao.queryEventType(string);
	}

	@Override
	public List<Map<String, Object>> queryAllEventType() {
		// TODO Auto-generated method stub
		return alertRuleDao.queryAllEventType();
	}

	public AssetService getAssetManager() {
		return assetManager;
	}

	public void setAssetManager(AssetService assetManager) {
		this.assetManager = assetManager;
	}

	public SecurityPolicyService getSecurityPolicyManager() {
		return securityPolicyManager;
	}

	public void setSecurityPolicyManager(SecurityPolicyService securityPolicyManager) {
		this.securityPolicyManager = securityPolicyManager;
	}
	
}
