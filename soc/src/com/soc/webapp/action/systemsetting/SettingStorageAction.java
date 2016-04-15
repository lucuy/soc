package com.soc.webapp.action.systemsetting;

import java.util.ArrayList;
import java.util.List;

import com.soc.model.systemsetting.Setting;
import com.soc.model.user.User;
import com.soc.service.audit.AuditService;
import com.soc.service.systemsetting.SettingService;
import com.soc.webapp.action.BaseAction;
import com.util.DateUtil;
import com.util.StringUtil;

/**
 * <数据源配置>
 * <功能详细描述>
 * 
 * @author  yinhaiping
 * @version  [版本号, 2012-11-5]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SettingStorageAction extends BaseAction
{
    private static final long serialVersionUID = 1L;
    private SettingService settingManager;
    private String jdbcUrl;                 
    private String jdbcName;                //数据库用户名
    private String jdbcPw;                  //数据库密码
    private String jdbcTime;                //超时重连
    private String jdbcNo;                  //线程数
    private String jdbcBatchNo;             //批量数
    private String jdbcBatch;               //是否开启批量
    private String jdbcBatchNo2;            //批量数
    private String jdbcCache;               //是否开启缓存
    private String jdbcType="PostgreSQL";   //数据库类型
    
    //审计
    private AuditService auditManager;
    
    
    
    /** <查询数据源配置>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String storage(){
         jdbcUrl=settingManager.queryByKey("jdbcUrl");
         jdbcName=settingManager.queryByKey("jdbcName");
         jdbcTime=settingManager.queryByKey("jdbcTime");
         jdbcNo=settingManager.queryByKey("jdbcNo");
         jdbcBatchNo=settingManager.queryByKey("jdbcBatchNo");
         jdbcBatch=settingManager.queryByKey("jdbcBatch");
         jdbcBatchNo2=settingManager.queryByKey("jdbcBatchNo2");
         jdbcCache=settingManager.queryByKey("jdbcCache");
         jdbcType=settingManager.queryByKey("jdbcType");
        return SUCCESS;
    }
    
    /** <查询数据源配置>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String saveStorage(){
        Setting setting = new Setting();
        setting.setKey("jdbcUrl");
        setting.setValue(jdbcUrl);
        settingManager.updateByKey("jdbcUrl", setting);
        setting.setKey("jdbcName");
        setting.setValue(jdbcName);
        settingManager.updateByKey("jdbcName", setting);
        if(StringUtil.isNotBlank(jdbcPw)){
            setting.setKey("jdbcPw");
            setting.setValue(jdbcPw);
            settingManager.updateByKey("jdbcPw", setting);
        }
        setting.setKey("jdbcTime");
        setting.setValue(jdbcTime);
        settingManager.updateByKey("jdbcTime", setting);
        setting.setKey("jdbcNo");
        setting.setValue(jdbcNo);
        settingManager.updateByKey("jdbcNo", setting);
        setting.setKey("jdbcBatchNo");
        setting.setValue(jdbcBatchNo);
        settingManager.updateByKey("jdbcBatchNo", setting);
        setting.setKey("jdbcBatch");
        setting.setValue(jdbcBatch);
        settingManager.updateByKey("jdbcBatch", setting);
        setting.setKey("jdbcBatchNo2");
        setting.setValue(jdbcBatchNo2);
        settingManager.updateByKey("jdbcBatchNo2", setting);
        setting.setKey("jdbcCache");
        setting.setValue(jdbcCache);
        settingManager.updateByKey("jdbcCache", setting);
        
        List<String> fieldList = new ArrayList<String>();
        fieldList.add(((User) this.getSession()
            .getAttribute("SOC_LOGON_USER")).getUserLoginName());
       
        auditManager.insertBySystemOperator(((User) this.getSession()
            .getAttribute("SOC_LOGON_USER")).getUserId(), "数据源配置", super
            .getRequest().getRemoteAddr(), fieldList);
        //syslog
       /* String logString = "";
       
        logString =
           "登录名:" + ((User)this.getSession().getAttribute("SOC_LOGON_USER")).getUserLoginName() + " 源IP:"
               + getRequest().getRemoteAddr() + " 操作时间:" + DateUtil.curDateTimeStr19() + " 操作类型 :数据源配置";
       
       logManager.writeSystemAuditLog(logString);*/
        logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
				.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "修改数据源配置");
        
       return SUCCESS;
   }

    public String getJdbcUrl()
    {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl)
    {
        this.jdbcUrl = jdbcUrl;
    }

    public String getJdbcName()
    {
        return jdbcName;
    }

    public void setJdbcName(String jdbcName)
    {
        this.jdbcName = jdbcName;
    }

    public String getJdbcPw()
    {
        return jdbcPw;
    }

    public void setJdbcPw(String jdbcPw)
    {
        this.jdbcPw = jdbcPw;
    }

    public String getJdbcTime()
    {
        return jdbcTime;
    }

    public void setJdbcTime(String jdbcTime)
    {
        this.jdbcTime = jdbcTime;
    }

    public String getJdbcNo()
    {
        return jdbcNo;
    }

    public void setJdbcNo(String jdbcNo)
    {
        this.jdbcNo = jdbcNo;
    }

    public String getJdbcBatchNo()
    {
        return jdbcBatchNo;
    }

    public void setJdbcBatchNo(String jdbcBatchNo)
    {
        this.jdbcBatchNo = jdbcBatchNo;
    }

    public String getJdbcBatch()
    {
        return jdbcBatch;
    }

    public void setJdbcBatch(String jdbcBatch)
    {
        this.jdbcBatch = jdbcBatch;
    }

    public String getJdbcBatchNo2()
    {
        return jdbcBatchNo2;
    }

    public void setJdbcBatchNo2(String jdbcBatchNo2)
    {
        this.jdbcBatchNo2 = jdbcBatchNo2;
    }

    public String getJdbcCache()
    {
        return jdbcCache;
    }

    public void setJdbcCache(String jdbcCache)
    {
        this.jdbcCache = jdbcCache;
    }

    public SettingService getSettingManager()
    {
        return settingManager;
    }

    public void setSettingManager(SettingService settingManager)
    {
        this.settingManager = settingManager;
    }

    public String getJdbcType()
    {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType)
    {
        this.jdbcType = jdbcType;
    }

	public AuditService getAuditManager() {
		return auditManager;
	}

	public void setAuditManager(AuditService auditManager) {
		this.auditManager = auditManager;
	}



}