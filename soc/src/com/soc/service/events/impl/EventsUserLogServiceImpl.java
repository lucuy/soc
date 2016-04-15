package com.soc.service.events.impl;

import java.util.List;

import com.soc.dao.events.EventsUserLogDao;
import com.soc.model.events.EventsUserLog;
import com.soc.service.events.EventsUserLogService;

public class EventsUserLogServiceImpl implements EventsUserLogService {
	
	
	private EventsUserLogDao eventsUserlogDao ;

	
	@Override
	public List<EventsUserLog> queryAllUserLogByNumId(long numId) {
		// TODO Auto-generated method stub
		return eventsUserlogDao.queryAllUserLogByNumId(numId);
	}
	
	public EventsUserLogDao getEventsUserlogDao() {
		return eventsUserlogDao;
	}


	public void setEventsUserlogDao(EventsUserLogDao eventsUserlogDao) {
		this.eventsUserlogDao = eventsUserlogDao;
	}
	
	
	
	


	
	
	
	
	
	
	
	
	
}
