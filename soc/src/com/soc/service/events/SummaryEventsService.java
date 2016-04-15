package com.soc.service.events;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.soc.model.events.Events;
import com.soc.model.events.SummaryEvents;

/**
 * 概要事件业务接口类
 * @author 王纯
 *
 */
public interface SummaryEventsService extends Serializable
{
    /**
     *插入概要事件 
     * @param sqlKey
     * @param summaryEvents
     * @return
     */
    //public long insertSummaryEvents(String sqlKey, ConcurrentLinkedQueue<SummaryEvents> temporarySummaryEventsQueue);
    public void insertSummaryEvents(String sqlKey, SummaryEvents summaryEvents);
    
    /**
     * 查询事件对象
     * @param eventsId
     * @return
     */
    //public Events queryEvents(long eventsId);
    /**
     * 查询所有事件名称
     */
    public List<Map<String, Object>> queryEventDefinition();
}