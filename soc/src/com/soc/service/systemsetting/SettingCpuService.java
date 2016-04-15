package com.soc.service.systemsetting;

import java.util.List;
import java.util.Map;

import com.soc.model.systemsetting.CpuData;
import com.soc.model.systemsetting.MemoryData;
import com.util.page.Page;
import com.util.page.SearchResult;

public interface SettingCpuService
{
    public void setCpuData(CpuData cpuData);
    public SearchResult query(Map map, Page page);
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<CpuData> cpuQuery();
    
    public List<CpuData> cpuQueryOne();
}