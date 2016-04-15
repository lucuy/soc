package com.soc.dao.systemsetting;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.soc.dao.BaseDao;
import com.soc.model.events.QueryEvents;
import com.soc.model.events.QueryEventsGroup;
import com.soc.model.policy.AddressPolicy;
import com.soc.model.systemsetting.Collector;
import com.soc.model.systemsetting.CpuData;

/**
 * <采集器配置>
 * <功能详细描述>
 * 
 * @author  yinhaiping
 * @version  [版本号, 2012-11-5]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface SettingCollectorDao extends BaseDao
{
    /**
     * <保存注册采集器>
     * <功能详细描述>
     * @param collectorData
     * @see [类、类#方法、类#成员]
     */
    public void setCollector(Collector collectorData);
    /**
     * <查询所有>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<Collector> queryCollector();
    /**
     * <查询树>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<Collector>queryCollectorTree();
    /**
     * <查询树id>
     * <功能详细描述>
     * @param treeDataId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<Collector> queryCollectorTree(Map<String, Integer> treeDataId);
    /**
     * <根据id更新>
     * <功能详细描述>
     * @param updateData
     * @see [类、类#方法、类#成员]
     */
    public void updateId(Collector updateData);
    /**
     * <根据id更新>
     * <功能详细描述>
     * @param updateData
     * @see [类、类#方法、类#成员]
     */
    public void updateById(ConcurrentLinkedQueue<Collector> updateData);
    /**
     * <根据id删除>
     * <功能详细描述>
     * @param collectorId
     * @see [类、类#方法、类#成员]
     */
    public void delId(int collectorId);
    /**
     * <分页>
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public int countPage(Map map);
    /**
     * <分页>
     * <功能详细描述>
     * @param map
     * @param startRow
     * @param pageSize
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<Collector> queryPage(Map map, int startRow, int pageSize);
    
    /**
     * <根据ID查询采集器>
     * <功能详细描述>
     * @param collectorId long
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Collector queryById(long collectorId);
    
    public List<Collector> selectMac(String addressName);
    
    /**
     * 更新采集器count
     * @param map
     */
    public void updateCollectorCount(String sql);
    
    public void updateLoggerStatus(Collector collectorList);
    
    /**
     * <更新采集器是否在线的状态>
     * <功能详细描述>
     * @param sql
     * @see [类、类#方法、类#成员]
     */
    public void updateCollectorIsOnLine(String sql);
    
    /**
     * <根据用户关联的资产组查询采集器>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<Collector> queryCollectors(Map map);
}