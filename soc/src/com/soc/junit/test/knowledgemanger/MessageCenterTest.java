package com.soc.junit.test.knowledgemanger;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.soc.junit.test.base.BaseTest;
import com.soc.model.knowledge.MessageCenter;
import com.soc.service.knowledge.MessageCenterService;
import com.util.page.Page;
import com.util.page.SearchResult;

public class MessageCenterTest extends BaseTest {

	private MessageCenterService mcManager;
	private Map<String, Object> map;
	private Page page;
	private SearchResult sr;
	
	@Before
	public void init(){
		super.init();
		mcManager = (MessageCenterService) super.getBean("messageManager");
		sr = new SearchResult();
	}
	
	@Ignore
	public void queryMessageTest(){
		map = new HashMap<String, Object>();
		map.put("dualstatus", 0);
		page = new Page(15, 0);
		sr = mcManager.queryMessageCenters(map, page);
		List<MessageCenter> list = sr.getList();
		for (MessageCenter msg : list) {
			//System.out.println(msg.getMessageId()+"\t\t"+msg.getMessageTitle());
		} 
	}
	
	@Ignore
	public void queryMessageCenterByIdTest(){
		MessageCenter msg = mcManager.queryMessageCenterById(3);
		//System.out.println(msg.getMessageTitle());
		
	}
	
	@Ignore
	public void updMessageStatusTest(){
		map = new HashMap<String, Object>();
		map.put("messageId", 12);
		map.put("operateDate", new Date());
		mcManager.updMessageStatus(map);
	}
	
	@Ignore
	public void delMessageByIdTest(){
		mcManager.delMessageById(12);
	}
	
	@Ignore
	public void updateMessageTest(){
		map = new HashMap<String, Object>();
		map.put("messageDeadIdea", "没有意见");
		map.put("messageId", 12);
		mcManager.updateMessage(map);
	}
	
	@Ignore
	public void updateMessageReadTest(){
		map = new HashMap<String, Object>();
		map.put("operateDate", new Date());
		map.put("messageId",12);
		mcManager.updateMessageRead(map);
	}

	
}
