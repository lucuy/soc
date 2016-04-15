package com.soc.service.events.impl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.soc.dao.events.EventsDao;
import com.soc.dao.events.SummaryEventsDao;
import com.soc.model.events.Events;
import com.soc.model.events.SummaryEvents;
import com.soc.service.events.SummaryEventsService;
import com.soc.service.impl.BaseServiceImpl;

/**
 * 概要事件业务实现类
 * @author 王纯
 *
 */
public class SummaryEventsServiceImpl extends BaseServiceImpl implements SummaryEventsService
{
    private SummaryEventsDao summaryEventsDao; //概要事件
    
    private EventsDao eventsDao; //事件对象
    
    @Override
    //public long insertSummaryEvents(String sqlKey, ConcurrentLinkedQueue<SummaryEvents> temporarySummaryEventsQueue)
    public void insertSummaryEvents(String sqlKey, SummaryEvents summaryEvents)
    {
        //long summaryEventsId = summaryEventsDao.insertSummaryEvents(sqlKey, temporarySummaryEventsQueue);
        summaryEventsDao.insertSummaryEvents(sqlKey, summaryEvents);
    }
    
    /*public Events queryEvents(long eventsId)
    {
        return eventsDao.queryEvents(eventsId);
    }*/
    
    public EventsDao getEventsDao()
    {
        return eventsDao;
    }
    
    public void setEventsDao(EventsDao eventsDao)
    {
        this.eventsDao = eventsDao;
    }
    
    public SummaryEventsDao getSummaryEventsDao()
    {
        return summaryEventsDao;
    }
    
    public void setSummaryEventsDao(SummaryEventsDao summaryEventsDao)
    {
        this.summaryEventsDao = summaryEventsDao;
    }

	@Override
	public List<Map<String, Object>> queryEventDefinition() {
		return summaryEventsDao.queryEventDefinition();
	}
    
}