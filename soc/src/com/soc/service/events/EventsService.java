package com.soc.service.events;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.soc.model.events.Events;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * 事件业务接口类
 * @author 王纯
 *
 */
public interface EventsService  extends Serializable
{
    /**
     * 插入事件
     * @param eventsList List<Events>
     * @return
     */
    public long insertEvents(ConcurrentLinkedQueue<Events> eventsQueue);
    
    /**
     * 插入源始日志 
     * @param originLogData
     * @return
     */
    public void insertOriginLog(ConcurrentLinkedQueue<Map<String, Object>> rawLogQueue);
    
    /**
     * 查询事件对象
     * @param eventsId
     * @return
     */
    public Events queryEvents(Map<String,Object> map);
    
    /**
     * <判断表是否存在>
     * <功能详细描述>
     * @param tableName
     * @return
     * @see [类、类#方法、类#成员]
     */
    public int existsTable(String tableName);
    
    /**
     * <按照时间ID来查询事件>
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     
    public Events quertEventsByLog_ID(Map map);*/
    
    /**
     * <判断表是否存在>
     * <功能详细描述>
     * @param seqName String
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String existsSeq(String seqName);
    
    /**
     * <创建日志表序列>
     * <功能详细描述>
     * @param seqName
     * @see [类、类#方法、类#成员]
     */
    public void createLogSeq(String seqName);
    
    /**
     * <按日期建表>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public void createLogTable(Map<String, String> map);
    
    /**
     * <按日期建表>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public void createNotAnalyticEventsTable(Map<String, String> map);
    
    /**
     * 查询协议信息
     * @return
     */
    public List<Map<String, Object>> querProtocol();
    
    
    /**
     * <大屏展示事件的最新>
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public int countRecentScreenEvents(Map map);
    
    
    /**
     * <大屏展示近期事件>
     * <功能详细描述>
     * @param map
     * @param page
     * @return
     * @see [类、类#方法、类#成员]
     */
    public SearchResult queryRecentScreenEvents(Map map, Page page);
    
    /**
     * <当天事件按照级别统计>
     * <功能详细描述>
     * @param map
     * @param page
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Object getEventsByToday(Map map);

	public void insertEventsDrools(Events eventTemp);
	
	/**
	 * 判断sqlServer数据库下的是否存在表的函数
	 * @param tableName
	 * @return
	 */
	public int exsitsqlServerTable(String tableName);
	
	/**
     * <根据传入的时间，查询范围内的事件趋势>
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String queryEventsTrendAnalysis(Map map);
    
    /**
     * <查询当天的事件，并按大类分类>
     * <功能详细描述>
     * @param 
     * @return String
     * @see [类、类#方法、类#成员]
     */
    public String queryEventByCategory(Map map);
    
    /**
     * <查询当天的事件，并按IP分类>
     * <功能详细描述>
     * @param map
     * @return List<Map<String, Object>>
     * @see [类、类#方法、类#成员]
     */
    public String queryEventByIP(Map map);
    
    /**
     * <根据日期删除表>
     * <功能详细描述>
     * @param String
     * @return 
     * @see [类、类#方法、类#成员]
     */
    public void deleteTable(String tableName);
    /**
     * <根据日期删除序列>
     * <功能详细描述>
     * @param String
     * @return 
     * @see [类、类#方法、类#成员]
     */
    public void deleteSequence(String sequence);
}
