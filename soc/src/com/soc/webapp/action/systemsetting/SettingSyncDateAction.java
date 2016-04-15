package com.soc.webapp.action.systemsetting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.soc.model.asset.Asset;
import com.soc.model.user.User;
import com.soc.service.asset.AssetService;
import com.soc.service.audit.AuditService;
import com.soc.service.systemsetting.SettingSyncDate;
import com.soc.webapp.action.BaseAction;
import com.util.DateUtil;
import com.util.StringUtil;
import com.util.linkMethod.SSHMode;
import com.util.linkMethod.TelnetMode;

/**
 * 
 * 通过ntp同步系统时间
 * 
 * @author HR
 * 
 * 
 */

public class SettingSyncDateAction extends BaseAction {

	private SettingSyncDate syncDateManager;

	private String msg;

	// 当前系统时间

	private Date currenDate;

	// 传递过来的ntp地址

	private String syncDate;

	// 传递的要变更的时间
	private String installTime;

	// queryString
	private String synceDateQuery;

	// 审计业务处理类
	private AuditService auditManager;

	private AssetService assetManager;

	// 获取当前系统时间

	public String ntpDate() {

		try {

			synceDateQuery = syncDateManager.getSyncIp();

			msg = "请输入正确的NTP服务器地址。例如：202.120.2.101";

			currenDate = syncDateManager.getSystemDate();

		} catch (Exception e) {

			// TODO: handle exception

			// System.out.println(e);

		}

		return SUCCESS;

	}

	// 设置ntp服务器

	public String settingSyncDate() {

		// System.out.println("******************syncDate************" +
		// "   "syncDate);

		if (StringUtil.isNotEmpty(syncDate)) {

			syncDateManager.updateNtpIP(syncDate);

			syncDateManager.settingNtpAddress(syncDate);
			// 写入审计日志
			List<String> fieldList = new ArrayList<String>();
			fieldList.add("时间同步" + "(" + "时间同步" + ")");
			auditManager.insertByUpdateOperator(((User) this.getSession()
					.getAttribute("SOC_LOGON_USER")).getUserId(), "时间同步", super
					.getRequest().getRemoteAddr(), fieldList);

			// syslog
			/*
			 * String logString = "";
			 * 
			 * logString = "登录名:" +
			 * ((User)this.getSession().getAttribute("SOC_LOGON_USER"
			 * )).getUserLoginName() + " 源IP:" + getRequest().getRemoteAddr() +
			 * " 操作时间:" + DateUtil.curDateTimeStr19() + " 操作类型 :时间同步";
			 * 
			 * logManager.writeSystemAuditLog(logString);
			 */
			logManager.writeSystemAuditLog(((User) this.getSession()
					.getAttribute("SOC_LOGON_USER")).getUserLoginName(),
					getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(),
					"修改系统时间");

			ntpDate();
			// 同步完成后，执行重启的命令
			try {

				Process process = Runtime.getRuntime().exec("reboot");

			} catch (IOException e) {
				e.printStackTrace();
			}
			msg = "同步成功!";

		}

		return SUCCESS;

	}

	// set system date
	public String installTime() {

		if (installTime != null && StringUtil.isNotEmpty(installTime)) {

			syncDateManager.setTime(installTime);
			// 写入审计日志
			List<String> fieldList = new ArrayList<String>();
			fieldList.add("时间同步" + "(" + "时间同步" + ")");
			auditManager.insertByUpdateOperator(((User) this.getSession()
					.getAttribute("SOC_LOGON_USER")).getUserId(), "时间同步", super
					.getRequest().getRemoteAddr(), fieldList);

			// syslog
			String logString = "";

			logString = "登录名:"
					+ ((User) this.getSession().getAttribute("SOC_LOGON_USER"))
							.getUserLoginName() + " 源IP:"
					+ getRequest().getRemoteAddr() + " 操作时间:"
					+ DateUtil.curDateTimeStr19() + " 操作类型 :时间同步";

			logManager.writeSystemAuditLog(logString);

			try {
				Process process = Runtime.getRuntime().exec("reboot");

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		return SUCCESS;
	}

	/**
	 * 同步其他审计设备事件
	 * 
	 * @return
	 */
	public String syncServerDate() {

		String date = DateUtil.curDateStr10();
		String time = DateUtil.curTimeStr10();

		Map map = new HashMap();
		map.put("assetGroupId", 1);
		List<Asset> assetList = assetManager.query(map);
		System.out.println(assetList);
		SSHMode s = new SSHMode();
		TelnetMode t = new TelnetMode();
		for (Asset asset : assetList) {
			if ("SSH".equals(asset.getSecurityLinkeThod())) {
				s.link(asset.getSecurityUserName(), asset.getSecurityPWD(),
						asset.getAssetMac(), asset.getSecurityPort(),
						"date -s \"" + date + " " + time + "\"");
			} else {
				t.link(asset.getSecurityUserName(), asset.getSecurityPWD(),
						asset.getAssetMac(), asset.getSecurityPort(), 
						"date "+date+"&& time "+time);
			}
		}
		msg = "1";
		return SUCCESS;
	}

	public SettingSyncDate getSyncDateManager() {

		return syncDateManager;

	}

	public void setSyncDateManager(SettingSyncDate syncDateManager) {

		this.syncDateManager = syncDateManager;

	}

	public String getMsg() {

		return msg;

	}

	public void setMsg(String msg) {

		this.msg = msg;

	}

	public Date getCurrenDate() {

		return currenDate;

	}

	public void setCurrenDate(Date currenDate) {

		this.currenDate = currenDate;

	}

	public String getSyncDate() {

		return syncDate;

	}

	public void setSyncDate(String syncDate) {

		this.syncDate = syncDate;

	}

	public String getInstallTime() {
		return installTime;
	}

	public void setInstallTime(String installTime) {
		this.installTime = installTime;
	}

	public String getSynceDateQuery() {
		return synceDateQuery;
	}

	public void setSynceDateQuery(String synceDateQuery) {
		this.synceDateQuery = synceDateQuery;
	}

	public AuditService getAuditManager() {
		return auditManager;
	}

	public void setAuditManager(AuditService auditManager) {
		this.auditManager = auditManager;
	}

	public AssetService getAssetManager() {
		return assetManager;
	}

	public void setAssetManager(AssetService assetManager) {
		this.assetManager = assetManager;
	}

}
