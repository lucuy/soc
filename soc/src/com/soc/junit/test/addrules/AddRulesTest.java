package com.soc.junit.test.addrules;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.soc.junit.test.base.BaseTest;
import com.soc.model.events.Events;
import com.soc.model.events.SummaryEvents;
import com.soc.model.systemsetting.rules.AnalysisRules;
import com.soc.model.systemsetting.rules.Device_category;
import com.soc.model.systemsetting.rules.QueryEvents_group;
import com.soc.service.addrules.AddRulesSerive;
import com.soc.service.events.QueryEventsService;
import com.util.page.Page;
import com.util.page.SearchResult;

public class AddRulesTest extends BaseTest {
	private AddRulesSerive addrulesserive;
	
	@Before
	public void init(){
		super.init();
		addrulesserive= (AddRulesSerive) super.getBean("addRulesManager");
		
	}
	
	@Ignore
	public void addAnalysisrules(){
		AnalysisRules analysisrules =new AnalysisRules();
		analysisrules.setAnalysisCondition("111");
		analysisrules.setAnalysisCreateTime(new Date());
		analysisrules.setAnalysisCreator("111");
		analysisrules.setAnalysisDescription("111");
		analysisrules.setAnalysisId("1111");
		analysisrules.setAnalysisName("111");
		analysisrules.setAnalysisType(111);
		analysisrules.setAnalysisUpdateTime(new Date());
		addrulesserive.insertAnalysisRules(analysisrules);
	}
	
	@Ignore
	public void addDevice_category(){
		Device_category device_category=new Device_category();
		device_category.setCode("111");
		device_category.setCustomd1(111);
		device_category.setDevname("111");
		device_category.setHigherUpsId(111);
		device_category.setName("111");
		device_category.setProgramtype("111");
		device_category.setRawid(111);
		addrulesserive.insertDevice_category(device_category);
	}
	
	@Ignore
	public void addQueryevents_group(){
		QueryEvents_group queryevents_group=new QueryEvents_group();
		queryevents_group.setConditions("111");
		queryevents_group.setName("111");
		queryevents_group.setParent_id(111);
		queryevents_group.setType(111);
		addrulesserive.insertQueryEvents_group(queryevents_group);
	}
	@Test
	public void queryAllName(){
		Map<String, String> map =new HashMap<String, String>();
		map.put("keyword", "111");
		List<String> list=addrulesserive.selectALLAnalysisName(map);
		for (String s : list) {
			System.out.println(s);
		}
	}
	
}
