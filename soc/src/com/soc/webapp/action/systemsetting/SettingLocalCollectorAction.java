package com.soc.webapp.action.systemsetting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.soc.model.systemsetting.Setting;
import com.soc.model.user.User;
import com.soc.service.audit.AuditService;
import com.soc.service.systemsetting.SettingService;
import com.soc.webapp.action.BaseAction;
import com.util.DateUtil;
import com.util.StringUtil;

/**
 * <本机采集器配置>
 * <配置本机采集器>
 * 
 * @author  yinhaiping
 * @version  [V100R001C001, 2012-11-5]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SettingLocalCollectorAction extends BaseAction
{
    private static final long serialVersionUID = 1L;
    //
    private SettingService settingManager;
    
    private String collectorIp;             //ip地址
    
    private String collectorWalkPort;       //walk端口
    
    private String collectorGroup;          //团体名
    
    private String collectorTime;           //轮循间隔
    
    private String collectorEnter;          //snmp信息库导入
    
    private String collectorTrip;           //Trip端口
    
    private String collectorLog;            //sys端口
    
    private String collectorAgentPort;      //Agent端口
    
    private String collectorUpTime;         //上报间隔：
    
    private String collectorIntervalTime;   //轮循间隔：
    
    private String collectorPattern;        //轮循间隔：
    
    private List<Map> ipList;
    
    private List<Map> groupList;
    
 // 审计业务管理类
    private AuditService auditManager;
    
    /** <查询本机采集器的配置>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String localCollector()
    {
        
        String ip = settingManager.queryByKey("collectorIp");
        String group = settingManager.queryByKey("collectorGroup");
        if (StringUtil.isNotBlank(ip) && StringUtil.isNotBlank(group))
        {
            ipList = new ArrayList<Map>();
            String[] ips = StringUtil.split(ip, ",");

            groupList = new ArrayList<Map>();
            String[] groups = StringUtil.split(group, ",");
            
            for (int p = 0; p < ips.length; p++)
            {
                String[] ipf = StringUtil.split(ips[p], ",");
                String[] groupf = StringUtil.split(groups[p], ",");
                Map<String, String> ipm = new HashMap<String, String>();
                for (int k = 0; k < ipf.length; k++)
                {
                    ipm.put("ip" + k, ipf[k]);
                    ipm.put("group" +k , groupf[k]);
                    
                }
                ipList.add(p, ipm);

            }
            
        }
        
        collectorWalkPort = settingManager.queryByKey("collectorWalkPort");
        collectorTime = settingManager.queryByKey("collectorTime");
        collectorEnter = settingManager.queryByKey("collectorEnter");
        collectorTrip = settingManager.queryByKey("collectorTrip");
        collectorLog = settingManager.queryByKey("collectorLog");
        collectorAgentPort = settingManager.queryByKey("collectorAgentPort");
        collectorUpTime = settingManager.queryByKey("collectorUpTime");
        collectorIntervalTime = settingManager.queryByKey("collectorIntervalTime");
        collectorPattern = settingManager.queryByKey("collectorPattern");
        return SUCCESS;
    }
    
    /** <保存本机采集器的配置>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String savelocalCollector()
    {
        Setting setting = new Setting();
        setting.setKey("collectorIp");
        setting.setValue(collectorIp);
        settingManager.updateByKey("collectorIp", setting);
        setting.setKey("collectorGroup");
        setting.setValue(collectorGroup);
        settingManager.updateByKey("collectorGroup", setting);
        
        setting.setKey("collectorWalkPort");
        setting.setValue(collectorWalkPort);
        settingManager.updateByKey("collectorWalkPort", setting);
        setting.setKey("collectorTime");
        setting.setValue(collectorTime);
        settingManager.updateByKey("collectorTime", setting);
        setting.setKey("collectorEnter");
        setting.setValue(collectorEnter);
        settingManager.updateByKey("collectorEnter", setting);
        setting.setKey("collectorTrip");
        setting.setValue(collectorTrip);
        settingManager.updateByKey("collectorTrip", setting);
        setting.setKey("collectorLog");
        setting.setValue(collectorLog);
        settingManager.updateByKey("collectorLog", setting);
        setting.setKey("collectorAgentPort");
        setting.setValue(collectorAgentPort);
        settingManager.updateByKey("collectorAgentPort", setting);
        setting.setKey("collectorUpTime");
        setting.setValue(collectorUpTime);
        settingManager.updateByKey("collectorUpTime", setting);
        setting.setKey("collectorIntervalTime");
        setting.setValue(collectorIntervalTime);
        settingManager.updateByKey("collectorIntervalTime", setting);
        setting.setKey("collectorPattern");
        setting.setValue(collectorPattern);
        settingManager.updateByKey("collectorPattern", setting);
        
        List<String> fieldList = new ArrayList<String>();
        fieldList.add(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
            .getUserLoginName());
        
        // 审计入库
        auditManager.insertByUpdateOperator(((User) this.getSession()
                .getAttribute("SOC_LOGON_USER")).getUserId(), "本机采集器", super
                .getRequest().getRemoteAddr(), fieldList);
        
        //syslog
        /*String logString = "";
        
        logString =
            "登录名:" + ((User)this.getSession().getAttribute("SOC_LOGON_USER")).getUserLoginName() + " 源IP:"
                + getRequest().getRemoteAddr() + " 操作时间:" + DateUtil.curDateTimeStr19() + " 操作类型 :本机采集器配置";
        
        logManager.writeSystemAuditLog(logString);*/
        logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
				.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "修改本机采集器配置");

        super.addActionMessage("配置保存成功！");
        return SUCCESS;
    }
    
    public SettingService getSettingManager()
    {
        return settingManager;
    }
    
    public void setSettingManager(SettingService settingManager)
    {
        this.settingManager = settingManager;
    }
    
    public String getCollectorIp()
    {
        return collectorIp;
    }
    
    public void setCollectorIp(String collectorIp)
    {
        this.collectorIp = collectorIp;
    }
    
    public String getCollectorWalkPort()
    {
        return collectorWalkPort;
    }
    
    public void setCollectorWalkPort(String collectorWalkPort)
    {
        this.collectorWalkPort = collectorWalkPort;
    }
    
    public String getCollectorGroup()
    {
        return collectorGroup;
    }
    
    public void setCollectorGroup(String collectorGroup)
    {
        this.collectorGroup = collectorGroup;
    }
    
    public String getCollectorTime()
    {
        return collectorTime;
    }
    
    public void setCollectorTime(String collectorTime)
    {
        this.collectorTime = collectorTime;
    }
    
    public String getCollectorEnter()
    {
        return collectorEnter;
    }
    
    public void setCollectorEnter(String collectorEnter)
    {
        this.collectorEnter = collectorEnter;
    }
    
    public String getCollectorTrip()
    {
        return collectorTrip;
    }
    
    public void setCollectorTrip(String collectorTrip)
    {
        this.collectorTrip = collectorTrip;
    }
    
    public String getCollectorLog()
    {
        return collectorLog;
    }
    
    public void setCollectorLog(String collectorLog)
    {
        this.collectorLog = collectorLog;
    }
    
    public String getCollectorAgentPort()
    {
        return collectorAgentPort;
    }
    
    public void setCollectorAgentPort(String collectorAgentPort)
    {
        this.collectorAgentPort = collectorAgentPort;
    }
    
    public String getCollectorUpTime()
    {
        return collectorUpTime;
    }
    
    public void setCollectorUpTime(String collectorUpTime)
    {
        this.collectorUpTime = collectorUpTime;
    }
    
    public String getCollectorIntervalTime()
    {
        return collectorIntervalTime;
    }
    
    public void setCollectorIntervalTime(String collectorIntervalTime)
    {
        this.collectorIntervalTime = collectorIntervalTime;
    }
    
    public String getCollectorPattern()
    {
        return collectorPattern;
    }
    
    public void setCollectorPattern(String collectorPattern)
    {
        this.collectorPattern = collectorPattern;
    }
    
    public List<Map> getIpList()
    {
        return ipList;
    }
    
    public void setIpList(List<Map> ipList)
    {
        this.ipList = ipList;
    }
    
    public List<Map> getGroupList()
    {
        return groupList;
    }
    
    public void setGroupList(List<Map> groupList)
    {
        this.groupList = groupList;
    }

    public AuditService getAuditManager()
    {
        return auditManager;
    }

    public void setAuditManager(AuditService auditManager)
    {
        this.auditManager = auditManager;
    }
    
}