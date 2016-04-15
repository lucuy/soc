package com.soc.service.eventtypetag.impl;

import java.util.List;

import com.soc.dao.eventtypetag.EventTypeTagDao;
import com.soc.model.eventtypetag.EventTypeTag;
import com.soc.service.eventtypetag.EventTypeTagService;
import com.soc.service.impl.BaseServiceImpl;

public class EventTypeTagServiceImpl  extends BaseServiceImpl implements EventTypeTagService{
	
	private EventTypeTagDao eventTypeTagDao;
	@Override
	public List<EventTypeTag> query() {
		return eventTypeTagDao.query();
	}
	
	public EventTypeTagDao getEventTypeTagDao() {
		return eventTypeTagDao;
	}
	public void setEventTypeTagDao(EventTypeTagDao eventTypeTagDao) {
		this.eventTypeTagDao = eventTypeTagDao;
	}

    @Override
    public List<EventTypeTag> queryByKeys(String keys)
    {
        // TODO Auto-generated method stub
        return eventTypeTagDao.queryByKeys(keys);
    }

	@Override
	public int eventTypeIdByName(String name) {
		// TODO Auto-generated method stub
		return eventTypeTagDao.eventTypeIdByName(name);
	}
}