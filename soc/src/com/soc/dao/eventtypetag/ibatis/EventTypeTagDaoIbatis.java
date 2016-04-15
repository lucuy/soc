package com.soc.dao.eventtypetag.ibatis;

import java.util.List;

import com.soc.dao.eventtypetag.EventTypeTagDao;
import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.eventtypetag.EventTypeTag;

public class EventTypeTagDaoIbatis extends BaseDaoIbatis implements EventTypeTagDao{

	@Override
	public List<EventTypeTag> query() {
		
		return super.queryForList(GlobalConfig.sqlId+"query.eventtypetag");
	}

    @Override
    public List<EventTypeTag> queryByKeys(String keys)
    {
        // TODO Auto-generated method stub
        return super.queryForList(GlobalConfig.sqlId+"query.eventTypeByKeys",keys);
    }

	@Override
	public int eventTypeIdByName(String name) {
		// TODO Auto-generated method stub
		return (Integer)super.queryForObject(GlobalConfig.sqlId+"query.eventTypeIdByName",name);
	}

}