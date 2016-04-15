package com.soc.service.monitor;

import java.util.List;
import java.util.Map;

import com.soc.model.monitor.MonitorAlarm;
import com.util.page.Page;
import com.util.page.SearchResult;

public interface MonitorAlarmService
{
    /**
     * 计算符合条件的告警记录数
     * 
     * @param Map
     * @return 返回符合查询条件的记录数
     */
    public int count(Map map);
    
    /**
     * 按条件分页查询告警
     * 
     * @param map Map
     * @param page Page
     * @return SearchResult
     */
    public SearchResult query(Map map, Page page);
    
    /**
     * 创建用户并返回告警ID
     * 
     * @param monitorAlarm MonitorAlarm
     * @return long
     */
    public long insert(MonitorAlarm monitorAlarm);
    
    /**
     * 最近24小时最新的10条阀值告警
     * 
     * @param Map map
     * @return List<MonitorAlarm>
     */
    public List<Map<String,Object>> queryTenAlarmMessage(Map map) ; 
}
