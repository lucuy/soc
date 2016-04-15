package com.soc.service.eventtypetag.impl;

import java.util.List;

import com.soc.dao.eventtypetag.ibatis.EventCategoryTagDaoIbatis;
import com.soc.model.eventtypetag.eventcategorytag;
import com.soc.service.eventtypetag.EventCategoryTagService;
import com.soc.service.impl.BaseServiceImpl;

/**
 * 
 * <事件分类对应关系的业务处理类>
 * <实现查询所有事件分类的关系>
 * 
 * @author  admin
 * @version  [版本号, 2013-6-26]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class EventCategoryTagServiceImpl extends BaseServiceImpl implements EventCategoryTagService {

    //事件分类的处理类
    private EventCategoryTagDaoIbatis eventCategoryTagDao;
	@Override
	public List<eventcategorytag> query() {
		// TODO Auto-generated method stub
		return eventCategoryTagDao.queryall();
	}
	
    public EventCategoryTagDaoIbatis getEventCategoryTagDao()
    {
        return eventCategoryTagDao;
    }
    public void setEventCategoryTagDao(EventCategoryTagDaoIbatis eventCategoryTagDao)
    {
        this.eventCategoryTagDao = eventCategoryTagDao;
    }

    @Override
    public List<eventcategorytag> queryByKeys(String keys)
    {
        // TODO Auto-generated method stub
        return eventCategoryTagDao.queryBykeys(keys);
    }

	@Override
	public String queryKeyBYCategoryName(String name) {
		// TODO Auto-generated method stub
		return eventCategoryTagDao.queryKeyBYCategoryName(name) ; 
	}

}
