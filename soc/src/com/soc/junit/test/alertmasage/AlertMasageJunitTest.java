package com.soc.junit.test.alertmasage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.soc.junit.test.base.BaseTest;
import com.soc.model.alert.alertMessage.AlertMessage;
import com.soc.service.alert.AlertMessageSerive;

public class AlertMasageJunitTest extends BaseTest{
	private AlertMessageSerive alertmassage;
	
	@Before
	public void init(){
		super.init();
		alertmassage=(AlertMessageSerive) super.getBean("alertMessageManager");
	}
	@Ignore
	public void exportExcel(){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("ids", "1,2,3,4,5");
		List<Map> list=alertmassage.exportExcel(map);
		
		for (Map map2 : list) {
			//System.out.println(map2.get("ALARM_ID"));
		}
	}
	@Ignore
	public void update(){
		Map map=new HashMap();
		map.put("alertID", 2);
		alertmassage.updateAlertMassage(map);
	}
	@Ignore
	public void querybyid(){
		Map map=new HashMap();
		map.put("id", 29459);
		AlertMessage a = alertmassage.queryById(map);
		//System.out.println(a.getAlertId());
	}
}
