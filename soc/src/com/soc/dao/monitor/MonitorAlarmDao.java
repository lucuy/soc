package com.soc.dao.monitor;

import java.util.List;
import java.util.Map;

import com.soc.model.monitor.MonitorAlarm;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  王亚男
 * @version  [版本号, 2012-11-12]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface MonitorAlarmDao
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
     * @param startRow int
     * @param pageSize int
     * @return 返回符合查询条件的告警结果列表List<MonitorAlarm>
     */
    public List<MonitorAlarm> query(Map map, int startRow, int pageSize);
    
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
