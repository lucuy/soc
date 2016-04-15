package com.soc.webapp.action.systemsetting;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import com.soc.model.systemsetting.MemoryData;
import com.soc.model.systemsetting.SysInfo;
import com.soc.service.systemsetting.SystemMemoryService;
import com.soc.service.systemsetting.impl.SystemInfoService;
import com.util.OSUtil;

public class SettingSetMemoryAction
{
    private MemoryData memoryData;
    private List<MemoryData> cpuList;
    private SystemMemoryService settingMemoryManager;
    
    public void memoryAction() throws IOException, InterruptedException{
        
        if (OSUtil.getOSName().toLowerCase().contains("linux"))
        {
            SysInfo sysInfo = SystemInfoService.getSysInfo();
            MemoryData memoryData = new MemoryData();
            memoryData.setSystemMemoryEmploy(sysInfo.getUsedMemory().replaceAll("M", ""));
            memoryData.setSystemMemoryTotal(sysInfo.getTotalMemorySize());
            memoryData.setSystemMemoryPercent(sysInfo.getMemoryRatio());
            memoryData.setSystemMemoryRemain(sysInfo.getUnuseMemory());
            memoryData.setSystemMemoryDate(new Date());
            settingMemoryManager.insert(memoryData);
        }
        else
        {
            //System.out.println("请部署到Linux服务器上");
        }
    }
    
    public MemoryData getMemoryData()
    {
        return memoryData;
    }

    public void setMemoryData(MemoryData memoryData)
    {
        this.memoryData = memoryData;
    }

    public List<MemoryData> getCpuList()
    {
        return cpuList;
    }

    public void setCpuList(List<MemoryData> cpuList)
    {
        this.cpuList = cpuList;
    }

    public SystemMemoryService getSettingMemoryManager()
    {
        return settingMemoryManager;
    }

    public void setSettingMemoryManager(SystemMemoryService settingMemoryManager)
    {
        this.settingMemoryManager = settingMemoryManager;
    }
    
}