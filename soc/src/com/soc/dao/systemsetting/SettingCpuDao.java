package com.soc.dao.systemsetting;

import java.util.List;
import java.util.Map;

import com.soc.model.systemsetting.CpuData;

public interface SettingCpuDao
{
    public void setCpuData(CpuData cpuData);
    public int count(Map map);
    public List<CpuData> query(Map map, int startRow, int pageSize);
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<CpuData> cpuQuery();
    
    public List<CpuData> cpuQueryOne();
}