package com.soc.dao.events.ibatis;

import java.util.List;
import java.util.Map;

import com.soc.dao.events.QueryEventsGroupDao;
import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.events.QueryEvents;
import com.soc.model.events.QueryEventsGroup;

public class QueryEvevtsGroupDaoIbatis extends BaseDaoIbatis implements QueryEventsGroupDao
{

    @Override
    public List<QueryEventsGroup> queryByParentId(Map<String, Long> map)
    {
        return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"queryEvents.queryByParentId",map);
    }
    
    
    @Override
    public List<QueryEventsGroup> queryNodeByParentId(Map<String, Long> map)
    {
        return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"queryEvents.queryNodeByParentId",map);
    }
    

    @Override
    public List<QueryEvents> queryeEventsAttestationList(Map<String, Long> map)
    {
        return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"queryEventsatt.queryByGroupId",map);
    }

    @Override
    public List<QueryEvents> queryeEventsConfigList(Map<String, Long> map)
    {
        return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"queryEventscon.queryByGroupId",map);
        
    }

    @Override
    public List<QueryEvents> queryeEventsResourcesList(Map<String, Long> map)
    {
        return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"queryEventsre.queryByGroupId",map);
        
    }

    @Override
    public List<QueryEvents> queryeEventsOperationList(Map<String, Long> map)
    {
        return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"queryEvenop.queryByGroupId",map);
        
    }

    @Override
    public List<QueryEventsGroup> queryGroupNodeList(Map<String, Long> map)
    {
        return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"queryEventGroupNode.queryByGroupId",map);
        
    }

    @Override
    public List<QueryEvents> queryCustomEventsNodeList(Map<String, Long> map)
    {
        return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"queryCustomEventsNode.queryByGroupId",map);
    }

    @Override
    public List<QueryEvents> queryEventsServerList(Map<String, Long> map)
    {
        return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"queryServerEventsNode.queryByGroupId",map);
    }

}
