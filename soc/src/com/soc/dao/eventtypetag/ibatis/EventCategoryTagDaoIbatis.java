package com.soc.dao.eventtypetag.ibatis;

import java.util.List;

import com.soc.dao.eventtypetag.EventCategoryTagDao;
import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.eventtypetag.eventcategorytag;

public class EventCategoryTagDaoIbatis extends BaseDaoIbatis implements EventCategoryTagDao 
{

    @Override
    public List<eventcategorytag> queryall()
    {
        // TODO Auto-generated method stub
        return super.queryForList(GlobalConfig.sqlId+"query.eventcategorytag");
    }

    @Override
    public List<eventcategorytag> queryBykeys(String keys)
    {
        // TODO Auto-generated method stub
        return super.queryForList(GlobalConfig.sqlId+"query.eventTagByKeys",keys);
    }

	@Override
	public String queryKeyBYCategoryName(String name) {
		// TODO Auto-generated method stub
		return  super.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"query.eventKeyByName",name).toString();
	}
    
}
