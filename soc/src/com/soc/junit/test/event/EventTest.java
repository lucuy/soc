package com.soc.junit.test.event;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.soc.junit.test.base.BaseTest;
import com.soc.model.events.Events;
import com.soc.model.events.SummaryEvents;
import com.soc.service.events.QueryEventsService;
import com.util.page.Page;
import com.util.page.SearchResult;

public class EventTest extends BaseTest {
	private QueryEventsService event;
	
	@Before
	public void init(){
		super.init();
		event=(QueryEventsService) super.getBean("queryEeventsManager");
		
	}
	
	@Ignore
	public void query(){
		//List<SummaryEvents> list = event.queryEventForExport(56, "1,2,3,4");
	/*	for (SummaryEvents events : list) {
			//System.out.println(events.getEventsId());
		}*/
	}
	
	
	
}
