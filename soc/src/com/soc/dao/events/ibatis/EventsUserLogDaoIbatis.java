package com.soc.dao.events.ibatis;

import java.util.List;

import com.soc.dao.events.EventsUserLogDao;
import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.model.events.EventsUserLog;

public class EventsUserLogDaoIbatis extends BaseDaoIbatis implements
		EventsUserLogDao {

	@Override
	public List<EventsUserLog> queryAllUserLog() {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("queryEventsUserLog.queryAllLog");
	}

	@Override
	public List<EventsUserLog> queryAllUserLogByNumId(long numId) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("queryEventsUserLog.queryLogByNumID",numId);
	}

}
