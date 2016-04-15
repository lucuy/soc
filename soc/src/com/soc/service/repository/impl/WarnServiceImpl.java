package com.soc.service.repository.impl;

import global.GlobalThreadPool;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.soc.dao.asset.AssetDao;
import com.soc.dao.repository.WarnDao;
import com.soc.dao.systemsetting.SettingDao;
import com.soc.dao.user.UserDao;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.user.User;
import com.soc.model.warn.Warn;
import com.soc.service.impl.BaseServiceImpl;
import com.soc.service.repository.WarnService;
import com.soc.webapp.listener.CommunicationContextListener;
import com.util.Base64;
import com.util.DES;
import com.util.SendEmail;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * 
 * <预警发布服务实现类> <功能详细描述>
 * 
 * @author liruifeng
 * @version [版本号, 2013-1-27]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class WarnServiceImpl extends BaseServiceImpl implements WarnService {
	private WarnDao warnDao;

	private SettingDao settingDao;
	
	private UserDao userDao ; 

	public WarnDao getWarnDao() {
		return warnDao;
	}

	public void setWarnDao(WarnDao warnDao) {
		this.warnDao = warnDao;
	}

	public SettingDao getSettingDao() {
		return settingDao;
	}

	public void setSettingDao(SettingDao settingDao) {
		this.settingDao = settingDao;
	}

	/**
	 * {@inheritDoc}查询预警
	 */
	@Override
	public List<Warn> findAllWarn() {
		// TODO Auto-generated method stub
		List<Warn> list = warnDao.findAllWarn();
		return list;
	}

	/**
	 * {@inheritDoc}新增预警
	 */
	/*
	 * @Override public int insertWarn(Warn warn) { // TODO Auto-generated
	 * method stub int i = warnDao.insertWarn(warn); return i; }
	 */
	public int insertWarn(Warn warn) {
		// TODO Auto-generated method stub
		int i = warnDao.insertWarn(warn);
		
		String InfluenceSysTem =  "('"+warn.getInfluenceSys().replaceAll(" ", "','")+"')";
        Map map = new HashMap() ; 
        map.put("assetSupportDevice", InfluenceSysTem);
		List<User> userList = userDao.queryAssetResponsibilityUserByPrincipalSys(map) ; 
		
		//发送邮件给设备的管理者
		if(!userList.isEmpty()){
        emailHandling(userList, warn);
		}
		
		return i;
	}

	/**
	 * {@inheritDoc}详细查询
	 */
	@Override
	public Warn queryById(Warn warn) {
		// TODO Auto-generated method stub

		return warnDao.queryById(warn);
	}

	/**
	 * {@inheritDoc}删除预警
	 */
	@Override
	public int deleteWarn(String result) {
		// TODO Auto-generated method stub
		int i = warnDao.deleteWarn(result);
		return i;
	}

	/**
	 * {@inheritDoc}更新预警
	 */
	@Override
	public void updateWarn(Warn warn) {
		// TODO Auto-generated method stub
		warnDao.updateWarn(warn);
	}

	/**
	 * {@inheritDoc}根据条件查询条数
	 */
	@Override
	public int count(Map map) {
		// TODO Auto-generated method stub
		return warnDao.count(map);
	}

	/**
	 * {@inheritDoc}根据条件查询信息
	 */
	@Override
	public SearchResult<Warn> query(Map map, Page page) {
		// 按Map中存储的条件查找用户列表
		int rowsCount = warnDao.count(map);

		page.setTotalCount(rowsCount);

		
		
		List<Warn> list = warnDao.query(map, page.getStartIndex(),
				page.getPageSize());

		// 对查找的预警列表做分页处理
		SearchResult<Warn> sr = new SearchResult<Warn>();
		sr.setList(list);

		sr.setPage(page);

		return sr;
	}

	@Override
	public SearchResult sort(Map map, Page page) {
		int rowsCount = warnDao.count(map);
		page.setTotalCount(rowsCount);
		String field = (String) map.get("field");
		String sortType = (String) map.get("sortType");
		String str = " \"" + field + "\"" + " " + sortType;
		List<Warn> list = warnDao.sort(str, page.getStartIndex(),
				page.getPageSize());
		SearchResult sr = new SearchResult();
		sr.setList(list);
		sr.setPage(page);
		return sr;
	}

	/**
	 * 处理发送邮件的内容信息
	 * 
	 */
	@Override
	public void emailHandling(List<User> userList, Warn warn) {
		StringBuffer userEmails = new StringBuffer(); // 用户Email地址
		StringBuffer message = new StringBuffer(); // 邮件内容
		StringBuffer userRealNames = new StringBuffer(); // 邮件接受人的真实姓名

		if ((!userList.isEmpty()) && warn != null) {
			for (User user : userList) {
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
		
			if (userEmails.length() > 0) {
				message.append("管理员您好！<br/>");
				message.append("您的资产："+"\"资产名\"有风险，具体内容如下：");
				message.append("<b style=\"margin-left:20px;\">SOC系统预警信息：</b>");
				message.append("<table style=\"margin-left:20px;\" width=\"100%\"  cellspacing=\"0\" cellpadding=\"0\" ><tr><td width=\"20%\" align=\"center\" style=\"border-top:1px solid;border-left:1px solid; background:#CCCCCC ;\">预警危害</td><td width=\"30%\" align=\"center\" style=\"border-top:1px solid;border-left:1px solid;border-right:1px solid;background:#CCCCCC;\">预警名称</td></tr>"+
						"<td align=\"center\" style=\"border-top:1px solid;border-left:1px solid;border-right:1px solid;background:#CCCCCC;\">预警描述</td>");
				message.append("<tr><td  align=\"center\" style=\"border-top:1px solid;border-left:1px solid;border-bottom:1px solid;\">"
						+ warn.getWarnHarm()
						+ "</td><td align=\"center\" style=\"border-top:1px solid;border-left:1px solid;border-bottom:1px solid;border-right:1px solid;\">"
						+ warn.getWarnName() + "</td></tr>"
						+"</td><td align=\"center\" style=\"border-top:1px solid;border-left:1px solid;border-bottom:1px solid;border-right:1px solid;\">"
						+ warn.getWarnDescription() + "</td></tr>");
				message.append("</table>");
				if (CommunicationContextListener.threadPoolId <= GlobalConfig.MAX_THREADPOOL_NUMBER) {
					CommunicationContextListener.threadPoolId++;
					GlobalThreadPool.pool.execute(creatThread(
							message.toString(), userEmails.toString()));
					CommunicationContextListener.threadPoolId--;
				}
			}
			message = new StringBuffer();
			userEmails = new StringBuffer();
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
	public Runnable creatThread(final String message, final String toEmailAdd) {
		return new Runnable() {
			public void run() {
				sendEmail(message, toEmailAdd);
			}
		};
	}

	/**
	 * {@inheritDoc}发送邮件
	 */
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
				sendEmail.setSubject("预警信息");
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

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	
	
	

}
