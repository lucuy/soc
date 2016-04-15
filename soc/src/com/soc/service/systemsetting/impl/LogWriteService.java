package com.soc.service.systemsetting.impl;

import org.apache.log4j.Logger;

import com.soc.dao.systemsetting.SettingDao;
import com.soc.service.impl.BaseServiceImpl;
import com.util.StringUtil;

public class LogWriteService extends BaseServiceImpl {
	private static Logger logger;
	private SettingDao settingDao;
	static {
		logger = Logger.getLogger("SYSLOG");
	}

	public static Logger getLogger(String loggerString) {
		logger = Logger.getLogger(loggerString);
		return logger;
	}

	/*
	 * 打印行为审计日志
	 */
	public void writeConductAuditLog(String str) {
		String syslogUse = settingDao.queryByKey("syslogUse");
		String conductString = settingDao.queryByKey("conductAudit");
		if (StringUtil.isNotBlank(syslogUse)) {
			if (Integer.parseInt(syslogUse) == 1) {
				if (StringUtil.isNotBlank(conductString)) {
					if (Integer.parseInt(conductString) == 1) {
						logger.info(str);
					}
				}
			}
		}

	}

	/*
	 * 打印内部审计日志
	 */
	public void writeSystemAuditLog(String str) {
		String syslogUse = settingDao.queryByKey("syslogUse");
		String sysAudit = settingDao.queryByKey("sysAudit");
		if (StringUtil.isNotBlank(syslogUse)) {
			if (Integer.parseInt(syslogUse) == 1) {
				if (StringUtil.isNotBlank(sysAudit)) {
					if (Integer.parseInt(sysAudit) == 1) {
						logger.info(str);
					}
				}
			}
		}

	}

	/*
	 * 打印数据库审计日志
	 */
	public void writeDatabaseAuditLog(String str) {
		String syslogUse = settingDao.queryByKey("syslogUse");
		String databaseAudit = settingDao.queryByKey("databaseAudit");
		if (StringUtil.isNotBlank(syslogUse)) {
			if (Integer.parseInt(syslogUse) == 1) {
				if (StringUtil.isNotBlank(databaseAudit)) {
					if (Integer.parseInt(databaseAudit) == 1) {
						logger.info(str);
					}
				}
			}
		}

	}

	/*
	 * 打印警告日志
	 */
	public void writeWarninglog(String str) {
		String syslogUse = settingDao.queryByKey("syslogUse");
		if (StringUtil.isNotBlank(syslogUse)) {
			if (Integer.parseInt(syslogUse) == 1) {
				logger.info(str);
			}
		}
	}

	public static void info(String str) {
		logger.info(str);
	}

	public SettingDao getSettingDao() {
		return settingDao;
	}

	public void setSettingDao(SettingDao settingDao) {
		this.settingDao = settingDao;
	}

}
