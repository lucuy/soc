package com.soc.dao.events;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.soc.model.asset.Asset;
import com.soc.model.events.Events;
import com.soc.model.events.NotAnalyticEvents;

public interface NotAnalyticEventsDao
{
    /**
     * 插入解析失败事件
     * @param notAnalyticEvents
     * @return
     */
    public void insertNotAnalyticEvents(NotAnalyticEvents notAnalyticEvents);
    
    /**
     * 查询解析失败事件
     * @return
     */
    public NotAnalyticEvents queryNotAnalyticEvents(Map<String, Object> map);
    
    /**
     * <判断表是否存在>
     * <功能详细描述>
     * @param tableName
     * @return
     * @see [类、类#方法、类#成员]
     */
    public int existsTable(String tableName);
    
    /**
     * <判断表是否存在>
     * <功能详细描述>
     * @param seqName String
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String existsSeq(String seqName);
    
    /**
     * <创建表序列>
     * <功能详细描述>
     * @param seqName
     * @see [类、类#方法、类#成员]
     */
    public void createNotAnalyticEventsSeq(String seqName);
    
    /**
     * <按日期建表>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public void createNotAnalyticEventsTable(Map<String, String> map);
    
    /**
     * <查询当天的未解析事件数量>
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public int count(Map map);
    
    /**
     * <查询当天的未解析日志>
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<NotAnalyticEvents> query(Map map,int startRow, int pageSize);
    
    /**
     * <查询近几天出现事件的资产ID>
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<Integer> queryListOfAssetID(Map map);
    
    /**
     * <查询近几天的未采集信息的资产list>
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<Asset> queryAssetList(Map map,int startRow, int pageSize);
    
    /**
     * <查询近几天的未采集信息的数目>
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public int countOfAsset(Map map);
    /**
     * <高级查询所有>
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<NotAnalyticEvents> queryAllNotAnaly(Map map,int startRow, int pageSize);
    /**
     * <高级查询所有的条数>
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public int queryAllNotAnalyCont(Map map);
    
    
    
}
