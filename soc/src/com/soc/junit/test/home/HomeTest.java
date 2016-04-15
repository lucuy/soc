package com.soc.junit.test.home;

import net.sf.json.JSONArray;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.soc.dao.events.EventsDao;
import com.soc.junit.test.base.BaseTest;
import com.soc.service.events.EventsService;

public class HomeTest extends BaseTest{
	
	private EventsService EventManager;
	
	@Before
	public void init(){
		super.init();
		EventManager=(EventsService) super.getBean("eventsManager");
	}
	
	@Ignore
	public void queryEventsTrendAnalysis(){
//		//String highcharts=EventManager.queryEventsTrendAnalysis();
//		System.out.println("======================");
//		System.out.println(highcharts);
	}
	
	@Ignore
	public void queryEventsByCategory(){
//		String date=EventManager.queryEventByCategory();
//		System.out.println(date);
	}
	
	@Test
	public void queryEventByIP(){
//		String date=EventManager.queryEventByIP();
//		System.out.println(date);
	}
}
