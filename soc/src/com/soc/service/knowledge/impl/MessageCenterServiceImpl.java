package com.soc.service.knowledge.impl;

import java.util.List;
import java.util.Map;

import com.soc.dao.knowledgemanger.MessageDao;
import com.soc.model.knowledge.MessageCenter;
import com.soc.model.user.User;
import com.soc.service.impl.BaseServiceImpl;
import com.soc.service.knowledge.MessageCenterService;
import com.util.page.Page;
import com.util.page.SearchResult;

public class MessageCenterServiceImpl extends BaseServiceImpl implements MessageCenterService {

	
	private MessageDao mDao;   //消息中心的dao类
	
	public MessageDao getmDao() {
		return mDao;
	}

	public void setmDao(MessageDao mDao) {
		this.mDao = mDao;
	}
	

	public SearchResult queryMessageCenters(Map map, Page page) {
		
		 // 按Map中存储的条件查找用户列表
        int rowsCount = mDao.queryMessageCenterCount(map);
        page.setTotalCount(rowsCount);
        List<MessageCenter> list = mDao.queryMessageCenters(map, page.getStartIndex(), page.getPageSize());
        
        // 对查找的用户列表做分页处理
        SearchResult sr = new SearchResult();
        sr.setList(list);
        sr.setPage(page);
        
		return sr;
	}

	public MessageCenter queryMessageCenterById(long id) {
		return mDao.queryMessageCenterById(id);
	}

	public int queryMessageCenterCount(Map map) {
		return mDao.queryMessageCenterCount(map);
	}

	public void updMessageStatus(Map map) {
		mDao.updMessageStatus(map);
	}

	public void delMessageById(long id) {
		mDao.delMessageById(id);
	}

	public long insertMessage(MessageCenter message) {
		return mDao.insertMessage(message);
	}

	public void updateMessage(Map map) {
		mDao.updateMessage(map);
	}

	public void updateMessageRead(Map map) {
		mDao.updateMessageRead(map);
	}

}
