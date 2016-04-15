package com.soc.service.systemsetting.impl;

import org.apache.log4j.Logger;

import com.soc.dao.systemsetting.SettingDao;
import com.soc.service.impl.BaseServiceImpl;
import com.util.SysLogSender;

public class LogWriteServiceImpl extends BaseServiceImpl {
    
	private static Logger logger;
	
	//private SystemSettingDao systemSettingDao;
	private SettingDao settingDao;
	
	static{
		logger=Logger.getLogger("SYSLOG");
	}
	public static Logger getLogger(String loggerString){
		logger = Logger.getLogger(loggerString);
		return logger;
	}
	/*
	 * 打印行为审计日志
	 */
	public  void writeConductAuditLog(String str){
		/*String syslogUse = settingDao.queryByKey("syslogUse");
		String conductString = settingDao.queryByKey("conductAudit");
		if(StringUtil.isNotBlank(syslogUse)){
			if(Integer.parseInt(syslogUse)==1){
				if(StringUtil.isNotBlank(conductString)){
					if(Integer.parseInt(conductString)==1){
						logger.info(str);
					}
				}
			}
		}*/
		SysLogSender.sender(str);
		
	}
	
	
    public SettingDao getSettingDao()
    {
        return settingDao;
    }
    public void setSettingDao(SettingDao settingDao)
    {
        this.settingDao = settingDao;
    }
    /*
	 * 打印内部审计日志
	 */
	public  void writeSystemAuditLog(String str){
		/*String syslogUse = settingDao.queryByKey("syslogUse");
		String sysAudit = settingDao.queryByKey("sysAudit");
		if(StringUtil.isNotBlank(syslogUse)){
			if(Integer.parseInt(syslogUse)==1){
				if(StringUtil.isNotBlank(sysAudit)){
					if(Integer.parseInt(sysAudit)==1){
						logger.info(str);
					}
				}
			}
		}*/
		SysLogSender.sender(str);
		
	}
	
	/*
	 * 打印数据库审计日志
	 */
	public  void writeDatabaseAuditLog(String str){
	/*	String syslogUse = settingDao.queryByKey("syslogUse");
		String databaseAudit = settingDao.queryByKey("databaseAudit");
		if(StringUtil.isNotBlank(syslogUse)){
			if(Integer.parseInt(syslogUse)==1){
				if(StringUtil.isNotBlank(databaseAudit)){
					if(Integer.parseInt(databaseAudit)==1){
						logger.info(str);
					}
				}
			}
		}*/
		SysLogSender.sender(str);
		
	}
	/*
	 * 打印警告日志
	 */
	public  void writeWarninglog(String str){
		/*String syslogUse = settingDao.queryByKey("syslogUse");
		if(StringUtil.isNotBlank(syslogUse)){
			if(Integer.parseInt(syslogUse)==1){
				logger.info(str);
			}
		}*/
		SysLogSender.sender(str);
	}
	public static void info(String str){
		logger.info(str);
	}
	/**
	 * <Syslog转发系统内部审计日志>
     * <功能详细描述>
	 * @param userName 操作用户
	 * @param address 用户ip地址
	 * @param operationTime 操作时间
	 * @param operationType 操作类型
	 * @return null
	 * @see [类、类#方法、类#成员]
	 */
	public void writeSystemAuditLog(String userName,String address,String operationTime,String operationType){
		String logString = "登录名:["+userName+"] 源IP:["+address+ "] 操作时间:["
				+operationTime+ "] 操作类型:["+operationType+"]";
		SysLogSender.sender(logString);
	}
     
}
