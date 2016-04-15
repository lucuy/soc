package com.soc.service.systemsetting;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.soc.model.policy.AddressPolicy;
import com.soc.model.systemsetting.Collector;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * <采集器的配置>
 * <功能详细描述>
 * 
 * @author  yinhaiping
 * @version  [版本号, 2012-11-5]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface SettingCollectorService
{
    /**
     * <保存采集器的数据>
     * <功能详细描述>
     * @param collectorData
     * @see [类、类#方法、类#成员]
     */
    public void setCollector(Collector collectorData);
    /**
     * <查询采集器的配置>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<Collector> queryCollector();
    /**
     * <查询树>
     * <功能详细描述>
     * @param objectBath
     * @return
     * @see [类、类#方法、类#成员]
     */
    /*public String queryTree(String objectBath);*/
    /**
     * <查询树的id>
     * <功能详细描述>
     * @param treeDataId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<Collector> queryCollectorTree(Map<String, Integer> treeDataId);
    /**
     * <更新采集器>
     * <功能详细描述>
     * @param updateData
     * @see [类、类#方法、类#成员]
     */
    public void updateId(Collector updateData);
    /**
     * <更新采集器>
     * <功能详细描述>
     * @param updateData
     * @see [类、类#方法、类#成员]
     */
    public void updateById(ConcurrentLinkedQueue<Collector> updateData);
    /**
     * <删除数据>
     * <功能详细描述>
     * @param collectorId
     * @return 
     * @see [类、类#方法、类#成员]
     */
    public void delId(int collectorId);
    public int selectAsset(int collectorId);
    /**
     * <分页>
     * <功能详细描述>
     * @param map
     * @param page
     * @return
     * @see [类、类#方法、类#成员]
     */
    public SearchResult queryPage(Map map, Page page);
    
    public String queryCollectorTree(String objectBath);
    
    public Collector queryById(long collectorId);
    
    public List<Collector> selectMac(String addressName);
    
    /**
     * 更新采集器count
     * @param map
     */
    public void updateColloectorCount(Map<String,Long> map);
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @see [类、类#方法、类#成员]
     */
    public void updateLoggerStatus(Collector collectorList);
    
    /**
     * <更新采集器在线或者健康状态>
     * <功能详细描述>
     * @param map
     * @see [类、类#方法、类#成员]
     */
    public void updateCollectorIsOnline(Map<String,Integer> map);
    
    /**
     * <根据用户关联的资产组查询采集器>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<Collector> queryCollectors(Map map);
    
}