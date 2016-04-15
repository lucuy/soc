package com.soc.dao.events;


import java.util.List;
import java.util.Map;

import com.soc.dao.BaseDao;
import com.soc.model.events.Events;
import com.soc.model.events.QueryEvents;
import com.soc.model.events.QueryEventsGroup;
import com.soc.model.events.SummaryEvents;

/**
 * 查询事件条件Dao类
 * @author 王纯
 *
 */
public interface QueryEventsDao extends BaseDao
{
    /**
     * 根据查询事件ID查询概要事件的查询条件字符串
     * @param queryEventsId
     * @return
     */
    public String querySummaryEventsConditions(long queryEventsGroupId);
    
    /**
     * 查询关联分析的查询条件字符串
     * @return
     */
    public List<QueryEventsGroup> queryRelevanceAnalysisConditions();
    
    /**
     * 查询认证事件条件
     * @return
     */
    public String queryAttestationEvents(long queryEventsId);
    
    /**
     * 查询配置变更事件条件
     * @return
     */
    public String queryConfigEvents(long queryEventsId);
    /**
     * 查询操作事件条件
     * @return
     */
    public String queryOperationEvents(long queryEventsId);
    
    /**
     * 查询网络异常
     * @param queryEventsId
     * @return
     */
    public String queryAnomalyEvents(long queryEventsId);
    /**
     * 查询系统资源事件条件
     * @return
     */
    public String queryResourcesEvents(long queryEventsId);
    /**
     * 查询服务器事件
     * @param queryEventsId
     * @return
     */
    public String queryServerEvents(long queryEventsId);
    
    /**
     * 查询一段时间内的事件 
     * @param timeValue
     * @return
     */
    public List<SummaryEvents> queryRecentEvents(Map<String,Long> timeValue,int startRow, int pageSize);
    
    /**
     * 查询自定义事件查询规则
     * @return
     */
    public String queryCustomEventsRules(long queryEventsGroupId);
    
    /**
     * 添加事件查询自定义规则
     * @param customQueryEvents
     * @return
     */
    public long insertQueryEventsRule(QueryEvents customQueryEvents);
    
    /**
     * 删除自定义查询条件
     * @param queryEventsId
     */
    public void delCustom(long queryEventsId);
    
    /**
     * 查询自定义查询条件
     * @param queryEventsId
     */
    public QueryEventsGroup queryCustomByID(long queryEventsId);

	public long updateinsertQueryEventsRule(QueryEvents customQueryEvents);
    
	/**
     * 查询导出的事件
     * @return 
     */
	public List<SummaryEvents> queryEventForExport(String sqlKey, Map<String, String> data);
	    
    
}
