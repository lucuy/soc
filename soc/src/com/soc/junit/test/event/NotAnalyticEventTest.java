package com.soc.junit.test.event;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.soc.junit.test.base.BaseTest;
import com.soc.model.asset.Asset;
import com.soc.model.events.NotAnalyticEvents;
import com.soc.service.events.NotAnalyticEventsService;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.util.GetDateUtil;
import com.util.page.Page;
import com.util.page.SearchResult;

public class NotAnalyticEventTest extends BaseTest{
	private NotAnalyticEventsService naes;
	private SearchResult sr = null;
	
	@Before
	public void init(){
		super.init();
		naes = (NotAnalyticEventsService) super.getBean("notAnalyticEventsManager");
		SearchResult sr = new SearchResult();
	}
	
	@Ignore
	public void queryCount(){
		Map map=new HashMap();
		map.put("day", 7);
		map.put("keyword", "北京");
		int num=naes.countOfAsset(map);
		//System.out.println(num);
	}
	@Ignore
	public void queryListOfAssetID(){
		//System.out.println(naes.queryListOfAssetID(1));
	}
	
	@Ignore
	public void querylist(){
		Map map=new HashMap();
		map.put("day", 7);
		sr =naes.queryAssetList(map, new Page(15, 0));
		List<Asset> list=sr.getList();
		for (Asset asset : list) {
			//System.out.println(asset.getAssetName());
		}
	}
	
}
