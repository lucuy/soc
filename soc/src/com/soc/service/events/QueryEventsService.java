package com.soc.service.events;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.soc.model.events.Events;
import com.soc.model.events.OriginalEvents;
import com.soc.model.events.QueryEvents;
import com.soc.model.events.QueryEventsGroup;
import com.soc.model.events.SummaryEvents;
import com.util.page.Page;
import com.util.page.SearchResult;


/**
 * 事件管理业务接口类
 * @author admin
 *
 */

public interface QueryEventsService extends Serializable
{
    /**
     * 根据组ID查询概要事件信息
     * @param queryEventsId
     * @param page
     * @return
     */
    public SearchResult<SummaryEvents> querySummaryEventsById(long queryEventsGroupId,Page page,String ids,String assetId,Map maps);
    
    
    /**
     * 查询认证事件
     * @return
     */
    public SearchResult<SummaryEvents> queryAttestationEventsById(long queryEventsId,Page page);
    
    /**
     * 查询配置变更事件
     * @return
     */
    public SearchResult<SummaryEvents> queryConfigEvents(long queryEventsId,Page page);
    
    /**
     * 查询操作事件
     * @return
     */
    public SearchResult<SummaryEvents> queryOperationEvents(long queryEventsId,Page page);
    
    /**
     * 查询系统资源事件
     * @param queryEventsId
     * @param page
     * @return
     */
    public SearchResult<SummaryEvents> queryResourcesEvents(long queryEventsId,Page page);
    
    /**
     * 查询网络异常事件
     * @param queryEventsId
     * @param page
     * @return
     */
    public SearchResult<SummaryEvents> queryAnomalyEvents(long queryEventsId, Page page);
    
    /**
     * 查询近期事件
     * @return
     */
    public SearchResult<SummaryEvents> queryRecentEvents(Page page);
    
    
    /**
     * 查询自定义事件查询规则
     * @return
     */
    public SearchResult<Events> queryCustomEventsRules(long queryEventsId,Page page,String id);
    
    /**
     * 查询自定义事件查询规则
     * @return
     */
    public SearchResult<Events> queryCustomEventsRules(String str,Page page,String id);
    
    /**
     * 查询服务器事件
     * @param queryEventsId
     * @param page
     * @return
     */
    public SearchResult<SummaryEvents> queryServerEvents(long queryEventsId,Page page);
    
    /**
     * 添加事件查询自定义规则
     * @param customQueryEvents
     * @param data
     * @return
     */
    public long insertQueryEventsRule( QueryEvents customQueryEvents);
 
    
    /**
     * 删除自定义查询条件
     * @param queryEventsId
     */
    public void delCustom(long queryEventsId);
    
     
    /**
     * <认证类的服务>
     * <功能详细描述>
     * @param sqlKey
     * @param count
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Object getMonitorCount(String sqlKey,Map map);
    
    /**
     * <服务器类的图表数据>
     * <功能详细描述>
     * @param sqlKey
     * @param count
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Object getMonitorServerCount(String sqlKey,Map map);
    
    /**
     * 查询关联分析
     * @param queryEventsId
     * @param page
     * @return
     */
    public SearchResult<Events> queryRelevanceAnalysis(long relId, Page page);
   
    /**
     * 插入关联分析cache表
     * @param map
     */
    public void insertRelevanceAnalysis();
    
    /**
     * <插入监控频道的ftp事件>
     * <功能详细描述>
     * @see [类、类#方法、类#成员]
     */
    public void insertMonitorFtpEvents();
    
    /**
     * <插入监控用的telnet事件 >
     * <功能详细描述>
     * @see [类、类#方法、类#成员]
     */
    public void insertMonitorTelnetEvents();
    
    /**
     * <插入监控用的抗阻击事件>
     * <功能详细描述>
     * @see [类、类#方法、类#成员]
     */
    public void insertMonitorconnectRefuseEvents();
    
    
    /**
     * <插入监控用的账户锁定事件>
     * <功能详细描述>
     * @see [类、类#方法、类#成员]
     */
    public void insertMonitorAccountLockedEvents();
    
    
    /**
     * <插入账户事件...登录事件>
     * <功能详细描述>
     * @see [类、类#方法、类#成员]
     */
    public void insertMonitorAccountLoginEvents();
    
    /**
     * <插入账户管理事件>
     * <功能详细描述>
     * @see [类、类#方法、类#成员]
     */
    public void insertMonitorAccountManageEvents();
    /**
     * <插入特权命令事件>
     * <功能详细描述>
     * @see [类、类#方法、类#成员]
     */
    public void insertMonitorPrivligeEvents();

	/**
	 * <是否存在表> <功能详细描述>
	 * 
	 * @param tableName
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public int existsTable(String tableName);
	
	public SearchResult queryUndefinedEvents(Map<String,Object> map , Page page);
    /**
     * <用来查询近期事件>
     * <功能详细描述>
     * @param sr 
     * @see [类、类#方法、类#成员]
     */
	public SearchResult queryRecentEvents(Map<String, Object> map, Page page);
	 /**
     * <查询自定义查询条件>
     * @param queryEventsGroupId 
     * @see [类、类#方法、类#成员]
     */
public String queryEventsConditionsStr(long queryEventsGroupId);
/**
 * <更新自定义查询条件>
 * @param queryEventsGroupId 
 * @see [类、类#方法、类#成员]
 */

	public long updateQueryEventsRule(
			com.soc.model.events.QueryEvents customQueryEvents);
	
	/**
     * 查询导出的事件
     * @return 
     */
    public List<SummaryEvents> queryEventForExport(long queryEventsGroupId,Map map);
    
    /**
     * <根据条件查询事件列表>
     * <功能详细描述>
     * @param condition
     * @param page
     * @return
     * @see [类、类#方法、类#成员]
     */
    public SearchResult<Events> queryRelAssetEvents(String condition, Page page);

    /**
     * <自定义监控图表>
     * <功能详细描述>
     * @param condition
     * @param page
     * @return
     * @see [类、类#方法、类#成员]
     */
	public Object getMonitorCustomCount(Map map);
	
	/**
     * 查询自定义查询条件
     * @param queryEventsId
     */
    public QueryEventsGroup queryCustomByID(long queryEventsId);
    
    public List<Events> queryExportEventsRules(long queryEventsGroupId,String id,String ids);
    
    public List<Events> queryExportEventsByRules(String conditions,String id,String ids);
    

    /**
     * <根据条件查询事件列表>
     * <功能详细描述>
     * @param condition
     * @param page
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<Events> queryExportRelAssetEvents(String condition,String ids);
    
    /**
     * 查询自定义事件查询规则
     * @return
     */
    public int queryCountCustomEventsRules(String str,String id,String ids);
    
    /**
     * 查询自定义事件查询规则
     * @return
     */
    public List<List<Events>> queryCustomEventsRules1(String str,String id,String ids);
    

    /**
     * 查询自定义事件查询规则
     * @return
     */
    public List<List<Events>> excelEventsByAsset(String str,String ids);
    /**
     * <导出原始事件>
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<OriginalEvents> exportOriginalEvents(Map map);
}
