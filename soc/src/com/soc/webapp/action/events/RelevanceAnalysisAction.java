package com.soc.webapp.action.events;

import com.soc.service.events.QueryEventsService;
import com.soc.webapp.action.BaseAction;

/**
 * 
 * <操作临时表>
 * <功能详细描述>
 * 
 * @author  liuyanxu
 * @version  [版本号, 2013-3-2]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class RelevanceAnalysisAction extends BaseAction
{
    private QueryEventsService queryEeventsManager; //事件管理业务对象
    
    
    public void relevanceAnalysis()
    {
        queryEeventsManager.insertRelevanceAnalysis();
    }
    
    public QueryEventsService getQueryEeventsManager()
    {
        return queryEeventsManager;
    }
    
    public void setQueryEeventsManager(QueryEventsService queryEeventsManager)
    {
        this.queryEeventsManager = queryEeventsManager;
    }
    
}
