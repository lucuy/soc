package com.soc.junit.test.knowledgemanger;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.soc.junit.test.base.BaseTest;
import com.soc.model.knowledge.Security;
import com.soc.service.knowledge.SecurityService;
import com.util.page.Page;
import com.util.page.SearchResult;

public class SecurityBulletinJunitTest extends BaseTest{

	private SecurityService sService;
	private Map<String, Object> map;
	private Page page;
	private SearchResult sr;
	
	@Before
	public void init(){
		super.init();
		sService = (SecurityService) super.getBean("securityManager");
		sr = new SearchResult();
	}
	
	@Ignore
	public void getSecurityTest(){
		map = new HashMap<String, Object>();
		map.put("keyword", "f");
		page = new Page(5, 0);
		sr = sService.querySecurity(map, page);
		List<Security> list = sr.getList();
		for (Security security : list) {
			//System.out.println(security.getSecurityId()+"\t\t"+security.getSecurityTitle());
		} 
	}
	
	@Ignore
	public void deleteSecurityByIds(){
		String ids="0,2";
//		String ids="";
//		String ids="1,3,4";
		sService.deleteSecurity(ids);
	}
	
	@Ignore
	public void saveSecurityTest(){
		Security security = new Security();
		security.setPublisher("张三");
		security.setSecurityDate(new Date());
		security.setSecurityDetails("这是个junit测试");
		security.setSecurityTitle("测试");
		security.setSource("未知");
		sService.insertSecuity(security);
		
	}
	
	@Ignore
	public void updateSecurityTest(){
		Security security = new Security();
		security.setSecurityId(5);
		security.setPublisher("张三5");
		security.setSecurityDate(new Date());
		security.setSecurityDetails("这是个junit测试5");
		security.setSecurityTitle("测试5");
		security.setSource("未知5");
		sService.updateSecurity(security);
	}
	
	@Ignore
	public void queryAllSecurity(){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("keyword", "f");
		List<Security> list=sService.queryAllSecurity(map);
		for (Security security : list) {
			//System.out.println(security.getSecurityTitle());
		}
	}
	
	
	@Test
	public void queryForExport(){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("ids", "7,8,9");
		map.put("keyword", "1");
		List<Security> list=sService.securityForExport(map);
		for (Security security : list) {
			//System.out.println(security.getSecurityTitle());
		}
	}
}
