package com.soc.service.events.impl;

import java.util.List;

import com.soc.dao.events.EventTreeDao;
import com.soc.model.events.EventTree;
import com.soc.service.events.EventTreeService;
/**
 * 业务层实现类
 * @author 何贝贝
 *
 */
public class EventTreeServiceImpl implements EventTreeService {

	private static final long serialVersionUID = 1L;
	private EventTreeDao eventTreeDao;
	
	public EventTreeDao getEventTreeDao() {
		return eventTreeDao;
	}

	public void setEventTreeDao(EventTreeDao eventTreeDao) {
		this.eventTreeDao = eventTreeDao;
	}

	@Override
	public int addEventTree(List<EventTree> eventTree) {
		return eventTreeDao.insertEventTree(eventTree);
	}

	@Override
	public List<EventTree> queryEventTree() {
		return eventTreeDao.selectEventTree();
	}
	
	@Override
	public int modifyEventTree(List<EventTree> eventTree) {
		return eventTreeDao.updateEventTree(eventTree);
	}

	@Override
	public int deleteEventTreeById(int... ids) {
		return eventTreeDao.deleteEventTreeById(ids);
	}

	public EventTree queryEventTreeById(int id){
		return eventTreeDao.selectEventTreeById(id);
	}
}
