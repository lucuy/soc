package com.soc.webapp.action.systemsetting;


import java.util.ArrayList;
import java.util.List;

import com.soc.model.systemsetting.Setting;
import com.soc.model.user.User;
import com.soc.service.audit.AuditService;
import com.soc.service.systemsetting.SettingService;
import com.soc.webapp.action.BaseAction;
import com.util.DateUtil;

public class SettingThresholdAction extends BaseAction
{
    private static final long serialVersionUID = 1L;
    
    //阀值服务管理类
    private SettingService settingManager;
    
    //CPU阀值
    private String cpuThreshold;
    
    //CPU告警间隔时间
    private String cpuTime;
    
    //CPU告警等级
    private String cpuRank;
    
    //内存阀值
    private String ramThreshold;
    
    //内存告警间隔时间
    private String ramTime;
    
    //内存设置等级
    private String ramRank;
    
    //硬盘阀值
    private String hddThreshold;
    
    //硬盘告警间隔时间
    private String hddTime;
    
    //硬盘设置等级
    private String hddRank;
    
    //流量阀值
    private String flawThresholdUp;
    
    //流量阀值
    private String flawThresholdDown;
    
    //流量设置等级
    private String flowRankUp;
    
    private String flowRankDown;
    
    //内部审计
    private AuditService auditManager;
    /**
     * 编辑阀值浏览
     * @return String
     * @see [类、类#方法、类#成员]
     */
    public String editThreshold()
    {
        LOG.info("[editThreshold] enter method edit() ...");
        
        // 接收查询条件，并存储到map中
            cpuThreshold = settingManager.queryByKey("cpu_threshold");
            cpuTime = settingManager.queryByKey("cpu_time");
            cpuRank= settingManager.queryByKey("cpu_rank");
            hddThreshold = settingManager.queryByKey("hdd_threshold");
            hddTime = settingManager.queryByKey("hdd_time");
            hddRank = settingManager.queryByKey("hdd_rank");
            ramThreshold = settingManager.queryByKey("ram_threshold");
            ramTime = settingManager.queryByKey("ram_time");
            ramRank = settingManager.queryByKey("ram_rank");
            flawThresholdUp = settingManager.queryByKey("flow_up");
            flawThresholdDown = settingManager.queryByKey("flow_down");
            flowRankUp = settingManager.queryByKey("flow_up_rank");
            flowRankDown=settingManager.queryByKey("flow_down_rank");
            
        return SUCCESS;
    }
    
    /**
     * 更新阀值配置
     * @return String
     * @see [类、类#方法、类#成员]
     */
    public String updateThreshold()
    {
        LOG.info("[updateThreshold] enter method update() ...");
        
        List<String> fieldList = new ArrayList<String>();
        
        fieldList.add("阀值设置(阀值更改)");
        
        Setting setting = new Setting();
        setting.setKey("cpu_threshold");
        setting.setValue(cpuThreshold);
        settingManager.updateByKey("cpu_threshold", setting);
        
        setting.setKey("cpu_time");
        setting.setValue(cpuTime);
        settingManager.updateByKey("cpu_time", setting);
        
        setting.setKey("cpu_rank");
        setting.setValue(cpuRank);
        settingManager.updateByKey("cpu_rank", setting);
        
        setting.setKey("hdd_threshold");
        setting.setValue(hddThreshold);
        settingManager.updateByKey("hdd_threshold", setting);
        
        setting.setKey("hdd_time");
        setting.setValue(hddTime);
        settingManager.updateByKey("hdd_time", setting);
        
        setting.setKey("hdd_rank");
        setting.setValue(hddRank);
        settingManager.updateByKey("hdd_rank", setting);
        
        setting.setKey("ram_threshold");
        setting.setValue(ramThreshold);
        settingManager.updateByKey("ram_threshold", setting);
        
        setting.setKey("ram_time");
        setting.setValue(ramTime);
        settingManager.updateByKey("ram_time", setting);
        
        setting.setKey("ram_rank");
        setting.setValue(ramRank);
        settingManager.updateByKey("ram_rank", setting);
        
        setting.setKey("flow_up");
        setting.setValue(flawThresholdUp);
        settingManager.updateByKey("flow_up", setting);
        
        setting.setKey("flow_up_rank");
        setting.setValue(flowRankUp);
        settingManager.updateByKey("flow_up_rank", setting);
        
        setting.setKey("flow_down");
        setting.setValue(flawThresholdDown);
        settingManager.updateByKey("flow_down", setting);
        
        setting.setKey("flow_down_rank");
        setting.setValue(flowRankDown);
        settingManager.updateByKey("flow_down_rank", setting);
        
        super.addActionMessage("配置保存成功！");
        
        //审计入库
        auditManager.insertByUpdateOperator(((User)this.getSession().getAttribute("SOC_LOGON_USER")).getUserId(),
            "阀值配置",
            super.getRequest().getRemoteAddr(),
            fieldList);
        //syslog
       /* String logString = "";
        
        logString =
            "登录名:" + ((User)this.getSession().getAttribute("SOC_LOGON_USER")).getUserLoginName() + " 源IP:"
                + getRequest().getRemoteAddr() + " 操作时间:" + DateUtil.curDateTimeStr19() + " 操作类型 :配置阀值";
        
        logManager.writeSystemAuditLog(logString);*/
        logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
				.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "修改阀值配置");
        return SUCCESS;
    }
    public String getCpuThreshold()
    {
        return cpuThreshold;
    }

    public void setCpuThreshold(String cpuThreshold)
    {
        this.cpuThreshold = cpuThreshold;
    }

    public String getCpuTime()
    {
        return cpuTime;
    }

    public void setCpuTime(String cpuTime)
    {
        this.cpuTime = cpuTime;
    }

    public String getCpuRank()
    {
        return cpuRank;
    }

    public void setCpuRank(String cpuRank)
    {
        this.cpuRank = cpuRank;
    }

    public String getRamThreshold()
    {
        return ramThreshold;
    }

    public void setRamThreshold(String ramThreshold)
    {
        this.ramThreshold = ramThreshold;
    }

    public String getRamTime()
    {
        return ramTime;
    }

    public void setRamTime(String ramTime)
    {
        this.ramTime = ramTime;
    }

    public String getRamRank()
    {
        return ramRank;
    }

    public void setRamRank(String ramRank)
    {
        this.ramRank = ramRank;
    }

    public String getHddThreshold()
    {
        return hddThreshold;
    }

    public void setHddThreshold(String hddThreshold)
    {
        this.hddThreshold = hddThreshold;
    }

    public String getHddTime()
    {
        return hddTime;
    }

    public void setHddTime(String hddTime)
    {
        this.hddTime = hddTime;
    }

    public String getHddRank()
    {
        return hddRank;
    }

    public void setHddRank(String hddRank)
    {
        this.hddRank = hddRank;
    }

    public String getFlawThresholdUp()
    {
        return flawThresholdUp;
    }

    public void setFlawThresholdUp(String flawThresholdUp)
    {
        this.flawThresholdUp = flawThresholdUp;
    }

    public String getFlawThresholdDown()
    {
        return flawThresholdDown;
    }

    public void setFlawThresholdDown(String flawThresholdDown)
    {
        this.flawThresholdDown = flawThresholdDown;
    }

    public String getFlowRankUp()
    {
        return flowRankUp;
    }

    public void setFlowRankUp(String flowRankUp)
    {
        this.flowRankUp = flowRankUp;
    }

    public String getFlowRankDown()
    {
        return flowRankDown;
    }

    public void setFlowRankDown(String flowRankDown)
    {
        this.flowRankDown = flowRankDown;
    }

    public SettingService getSettingManager()
    {
        return settingManager;
    }

    public void setSettingManager(SettingService settingManager)
    {
        this.settingManager = settingManager;
    }

	public AuditService getAuditManager() {
		return auditManager;
	}

	public void setAuditManager(AuditService auditManager) {
		this.auditManager = auditManager;
	}



}
