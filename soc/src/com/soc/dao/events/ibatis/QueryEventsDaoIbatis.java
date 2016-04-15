package com.soc.dao.events.ibatis;

import java.util.List;
import java.util.Map;

import com.soc.dao.events.QueryEventsDao;
import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.events.Events;
import com.soc.model.events.QueryEvents;
import com.soc.model.events.QueryEventsGroup;
import com.soc.model.events.SummaryEvents;

public class QueryEventsDaoIbatis extends BaseDaoIbatis implements QueryEventsDao
{

    @Override
    public String querySummaryEventsConditions(long queryEventsGroupId)
    {
        QueryEventsGroup queryEventsGroup = (QueryEventsGroup)super.queryForObject(GlobalConfig.sqlId+"query.summaryEventsConditions", queryEventsGroupId); 
        String eventsConditionsStr = "";
        if(queryEventsGroup != null)
        {
            eventsConditionsStr = queryEventsGroup.getQueryEventsConditions();
        }
        return eventsConditionsStr;
    }
    
    @Override
    public String queryAttestationEvents(long queryEventsId)
    {
        
        QueryEvents queryEvents = (QueryEvents)super.queryForObject(GlobalConfig.sqlId+"query.attestation", queryEventsId); 
        String eventsStr = "";
        if(queryEvents != null)
        {
            eventsStr = queryEvents.getQueryEventsConditions();
        }
        return eventsStr;
    }

    @Override
    public String queryConfigEvents(long queryEventsId)
    {
        QueryEvents queryEvents = (QueryEvents)super.queryForObject(GlobalConfig.sqlId+"query.config", queryEventsId); 
        String eventsStr = "";
        if(queryEvents != null)
        {
            eventsStr = queryEvents.getQueryEventsConditions();
        }
        return eventsStr;
    }

    @Override
    public String queryOperationEvents(long queryEventsId)
    {
        QueryEvents queryEvents = (QueryEvents)super.queryForObject(GlobalConfig.sqlId+"query.operatioin", queryEventsId); 
        String eventsStr = "";
        if(queryEvents != null)
        {
            eventsStr = queryEvents.getQueryEventsConditions();
        }
        return eventsStr;
    }

    @Override
    public String queryResourcesEvents(long queryEventsId)
    {
        QueryEvents queryEvents = (QueryEvents)super.queryForObject(GlobalConfig.sqlId+"query.resources", queryEventsId); 
        String eventsStr = "";
        if(queryEvents != null)
        {
            eventsStr = queryEvents.getQueryEventsConditions();
        }
        return eventsStr;
    }

    @Override
    public List<SummaryEvents> queryRecentEvents(Map<String,Long> timeValue,int startRow, int pageSize)
    {
        return super.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"query.recent", timeValue,startRow,pageSize);
    }

    @Override
    public String queryCustomEventsRules(long queryEventsGroupId)
    {
        QueryEventsGroup queryEventsGroup = (QueryEventsGroup)super.queryForObject(GlobalConfig.sqlId+"query.summaryEventsConditions", queryEventsGroupId); 
        String eventsConditionsStr = "";
        if(queryEventsGroup != null)
        {
            eventsConditionsStr = queryEventsGroup.getQueryEventsConditions();
        }
        return eventsConditionsStr;
    }

    @Override
    public long insertQueryEventsRule(QueryEvents customQueryEvents)
    {
        return (Long)this.getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"insert.queryEventsRule", customQueryEvents);
    }

    @Override
	public long updateinsertQueryEventsRule(QueryEvents customQueryEvents) {
    	getSqlMapClientTemplate().update(GlobalConfig.sqlId+"update.queryEventsRule", customQueryEvents);
		return customQueryEvents.getQueryEventsId();
	}

	@Override
    public void delCustom(long queryEventsId)
    {
        super.getSqlMapClientTemplate().delete(GlobalConfig.sqlId+"del.custom", queryEventsId);
    }

    @Override
    public String queryAnomalyEvents(long queryEventsId)
    {
        QueryEvents queryEvents = (QueryEvents)this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"query.anomaly", queryEventsId); 
        String eventsStr = "";
        if(queryEvents != null)
        {
            eventsStr = queryEvents.getQueryEventsConditions();
        }
        return eventsStr;
    }

    @Override
    public String queryServerEvents(long queryEventsId)
    {
        QueryEvents queryEvents = (QueryEvents)this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"query.server", queryEventsId); 
        String eventsStr = "";
        if(queryEvents != null)
        {
            eventsStr = queryEvents.getQueryEventsConditions();
        }
        return eventsStr;
    }

    @Override
    public List<QueryEventsGroup> queryRelevanceAnalysisConditions()
    {
        return  this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"query.RelevanceAnalysis");
    }

	@Override
	public List<SummaryEvents> queryEventForExport(String sqlKey, Map<String, String> data)
	    {
	        return super.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+sqlKey, data);
	    }

	@Override
	public QueryEventsGroup queryCustomByID(long queryEventsId) {
		return (QueryEventsGroup) this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"quer.customByID", queryEventsId);
	}


}
