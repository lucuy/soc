package com.soc.service.events;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.soc.model.events.Events;
import com.soc.model.events.NotAnalyticEvents;
import com.util.page.Page;
import com.util.page.SearchResult;

public interface NotAnalyticEventsService
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
     * <查询未解析事件的数量>
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public int count(Map map);
    
    /**
     * <查询未解析事件分页>
     * <功能详细描述>
     * @param map
     * @param page
     * @return
     * @see [类、类#方法、类#成员]
     */
    public SearchResult query(Map map,Page page);
    
    /**
     * <查询近几天出现事件的资产ID>
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String queryListOfAssetID(int day);
    
    /**
     * <查询近几天的未采集信息的资产>
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public SearchResult queryAssetList(Map map,Page page);
    
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
    public SearchResult queryAllNotAnaly(Map map,Page page);
    /**
     * <高级查询所有的条数>
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public int queryAllNotAnalyCont(Map map);
}
