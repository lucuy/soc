package com.soc.dao.events;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.soc.dao.BaseDao;
import com.soc.model.events.Events;
import com.soc.model.events.SummaryEvents;

/**
 * 概要事件Dao
 * @author 王纯
 *
 */
public interface SummaryEventsDao extends BaseDao
{
    /**
     * 查询总条数
     * @param map
     * @return
     */
    public int count(String sqlKey,Map<String,String> map);
    
    /**
     * 查询总条数
     * @param sqlKey
     * @param sql
     * @return
     */
    public int count(String sqlKey, String sql);
    
    /**
     * 查询近期事件总条数
     * @return
     */
    public int count(Map<String,Long> map);
    
    /**
     * 分页查询
     * @param map
     * @param startRow
     * @param pageSize
     * @return
     */
    public List<SummaryEvents> query(String sqlKey,Map<String,String> map, int startRow, int pageSize);
    
    
    /**
     * 概要事件类型查询概要事件
     * @return
     */
    public List<SummaryEvents> querySummaryEventsByEventsType(String sqlKey,Map<String,String> data, int startRow, int pageSize);
    
    /**
     * 自定义查询
     * @param sqkKey
     * @param map
     * @param startRow
     * @param pageSize
     * @return
     */
    public List<Events> queryCustomEventsRules(String sqkKey,String sql, int startRow, int pageSize);
    
    /**
     * 插入概要事件
     * @param summaryEvents
     * @return
     */
    //public long insertSummaryEvents(String sqlKey, ConcurrentLinkedQueue<SummaryEvents> temporarySummaryEventsQueue);
    public void insertSummaryEvents(String sqlKey, SummaryEvents summaryEvents);
    
    
  /*  *//**
     * <监控图表>
     * <用于统计成功失败的认证事件>
     * @param sqlKey
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     *//*
    public int monitorCount(String sqlKey,Map map);*/
    
    /**
     * <监控图表>
     * <用于统计不同类型的成功失败的认证事件>
     * @param sqlKey
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<Map> monitorSummary(String sqlKey,Map map);
    
    
    
    /**
     * 关联分析
     * @return
     */
    public List<Events> queryRelevanceAnalysis(String sqlKey,Map<String,String> data, int startRow, int pageSize);
    
    /**
     * 关联分析
     * @return
     */
    public List<Events> queryRelevanceAnalysis(String sqlKey,Map<String,String> data);
    
    /**
     * 插入关联分析cache表
     */
    public void insertRelevanceAnalysis(Map<String, Object> map);

    /**
     * 查询关联分析
     * @param queryEventsId
     * @param startRow
     * @param pageSize
     * @return
     */
    public List<Events> queryRelevanceAnalysis(long relId,int startRow, int pageSize);
    
    /**
     * 清空关联分析cache表
     */
    public void emptyTable();
    /**
     * 查询近期事件的方法
     * @param queryEventsId
     * @param startRow
     * @param pageSize
     * @return
     */
	public List<Events> queryRecentEvents(Map<String, Object> map,
			int startIndex, int pageSize);
	/**
     * 查询近期事件条数
	 * @param map 
     * @param queryEventsId
     * @param startRow
     * @param pageSize
     * @return
     */
	public int getRecentEventsCount(Map<String, Object> map);

	public int existsTable(String tableName);
	
	/**
	 * 查看当天的未定义事件
	 */
	public int getUndefinedEventsCount(Map<String,Object> map);
	
	/**
	 * 查看当天的未定义事件列表
	 * @param map
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public List<Events> queryUndfinedEvents(Map<String,Object> map,int startIndex,int pageSize);
	
    
    /**
     * 查询事件名称
     * @return 
     */
    public List<Map<String, Object>> queryEventDefinition();
    
    /**
     * <根据资产ID查询总条数>
     * <功能详细描述>
     * @param sqlKey
     * @param sql
     * @return
     * @see [类、类#方法、类#成员]
     */
    public int countByassetId(String sqlKey, String sql);
    
    /**
     * <根据资产ID查询对应的事件列表>
     * <功能详细描述>
     * @param sqkKey
     * @param sql
     * @param startRow
     * @param pageSize
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<Events> queryEventsByassetIds(String sqkKey,String sql, int startRow, int pageSize);
    /**
     * <查询自定义监控图表数据>
     * <功能详细描述>
     * @param sqkKey
     * @param sql
     * @return
     * @see [类、类#方法、类#成员]
     */
	public List<Map> getMonitorCustomCount(Map map);
	
	 public List<Events> queryCustomEventsRules(String sqkKey, String sql);
	 

	    /**
	     * <根据资产ID查询对应的事件列表>
	     * <功能详细描述>
	     * @param sqkKey
	     * @param sql
	     * @param startRow
	     * @param pageSize
	     * @return
	     * @see [类、类#方法、类#成员]
	     */
	    public List<Events> queryExportEventsByassetIds(String sqkKey,String sql);
	    
	    public List<List<Events>> queryCustomEventsRules111(String sqlKsy,List<String> list);
    
}