package com.soc.dao.events;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.soc.dao.BaseDao;
import com.soc.model.events.Events;

/**
 * 事件dao接口类
 * @author 王纯
 *
 */
public interface EventsDao extends BaseDao
{
    /**
     * 插入事件
     * @param events
     * @return
     */
    public long insertEvents(ConcurrentLinkedQueue<Events> eventsQueue,String userid);
    
    /**
     * 插入源始日志信息
     * @param identification
     * @param msg
     * @return long
     */
    public void insertOriginLog(ConcurrentLinkedQueue<Map<String, Object>> rawLogQueue);
    
    /**
     * 查询事件对象
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
     * <根据监控条件查询大表>
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<Map> queryMointorEvents(Map map);
    
    
    
    /**
     * <插入ftp事件小表>
     * <功能详细描述>
     * @param list
     * @see [类、类#方法、类#成员]
     */
    public void insertMonitorFtpEvents(List<Map> list);
    
    
    /**
     * <插入telnet事件小表 >
     * <功能详细描述>
     * @param list
     * @see [类、类#方法、类#成员]
     */
    public void insertMonitorTelnetEvents(List<Map> list);
    
    
    /**
     * <插入连接被阻事件>
     * <功能详细描述>
     * @param list
     * @see [类、类#方法、类#成员]
     */
    public void insertMonitorConnectRefuse(List<Map> list);
   
     /**
      * <插入账户锁定事件>
      * <功能详细描述>
      * @param list
      * @see [类、类#方法、类#成员]
      */
    public void insertMonitorAccountLocked(List<Map> list);
    
    
    
    /**
     * <插入账户登录事件>
     * <功能详细描述>
     * @param list
     * @see [类、类#方法、类#成员]
     */
    public void insertMonitorAccountLogin(List<Map> list);
    
    /**
     * <插入账户的管理事件>
     * <功能详细描述>
     * @param list
     * @see [类、类#方法、类#成员]
     */
    public void insertMonitorAccountManage(List<Map> list);
    
    /**
     * <插入特权命令小表>
     * <功能详细描述>
     * @param list
     * @see [类、类#方法、类#成员]
     */
    public void insertMonitorPrivilge(List<Map> list);
    

    /**
     * 查询协议信息
     * @return
     */
    public List<Map<String, Object>> querProtocol();
    
    
    /**
     * <首页大屏展示统计最近事件的数量>
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public int countRecentScreenEvents(Map map);
    
    /**
     * <查看最近大屏展示的事件>
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<Events> queryRecentScreenEvents(Map map,int startRow, int pageSize);
    
    /**
     * <按照当天的事件级别统计>
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<Map> queryTodayEventsByPriority(Map map);

	public List<Map> getEventsStatistics(Map map);

	public void insertEventsDrools(Events e);
	
	/**
	 * 判断SqlServer数据库下面是否存在表
	 * @param tableName
	 * @return
	 */
	public int exsitsqlServerTable(String tableName);
	
	/**
     * <根据传入的时间，查询范围内的事件趋势>
     * <功能详细描述>
     * @param map
     * @return int
     * @see [类、类#方法、类#成员]
     */
    public int queryEventsTrendAnalysis(Map map);
    
    /**
     * <查询当天的事件，并按大类分类>
     * <功能详细描述>
     * @param map
     * @return List<Map<String, Object>>
     * @see [类、类#方法、类#成员]
     */
    public List<Map<String, Object>> queryEventByCategory(Map map);
    
    /**
     * <查询当天的事件，并按IP分类>
     * <功能详细描述>
     * @param map
     * @return List<Map<String, Object>>
     * @see [类、类#方法、类#成员]
     */
    public List<Map<String, Object>> queryEventByIP(Map map);
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
