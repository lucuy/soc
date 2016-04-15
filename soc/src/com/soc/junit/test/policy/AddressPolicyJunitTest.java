package com.soc.junit.test.policy;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.soc.junit.test.base.BaseTest;
import com.soc.model.policy.AddressPolicy;
import com.soc.service.policy.AddressPolicyService;
import com.util.page.Page;
import com.util.page.SearchResult;

public class AddressPolicyJunitTest extends BaseTest{

	public AddressPolicyJunitTest(){}
	
	private AddressPolicyService apService;
	private SearchResult sr = null;
	private Map<String, Object> map= null;
	private Page page =null;
	
	@Before
	public void init(){
		super.init();
		apService = (AddressPolicyService) super.getBean("addressPolicyManager");
	}
	
	@Test
	public void getTimePolicyCountTest(){
		map = new HashMap<String, Object>();
		int count = apService.count(map);
		//System.out.println(count);
	}

	@Ignore
	public void getTimePolicyPageTest(){
		map = new HashMap<String, Object>();
		page = new Page(5, 0);
		//需要测试的条件
		
		
		sr = apService.queryAddressPolicy(map, page);
		List<AddressPolicy> list = sr.getList();
		page = sr.getPage();
		for (AddressPolicy ap : list) {
			//System.out.println(ap.getAddressPolicyId()+"\t\t"+ap.getAddressPolicyName()+"\t\t"+ap.getAddressPolicyStatus());
		}
		//System.out.println(page.getStartIndex());
	}

	@Ignore
	public void getTimePolicyTest(){
		map = new HashMap<String, Object>();
		//需要测试的条件
		map.put("addressPolicyName", "tes");
		map.put("addressPolicyMemo", "这是");
		map.put("addressPolicyStatus", 0);
		map.put("addressPolicyEffectWay", 0);
		
		List<AddressPolicy> list = apService.queryAddressPolicy(map);
		for (AddressPolicy ap : list) {
			//System.out.println(ap.getAddressPolicyId()+"\t\t"+ap.getAddressPolicyName()+"\t\t"+ap.getAddressPolicyStatus());
		}
	}
	
	//删除地址策略
	@Ignore
	public void deleteAddressPolicyByIdTest(){
		
		apService.deleteAddressPolicy(9);
		
	}
	
	
	//修改地址策略的状态
	@Ignore
	public void updateAddressPolicyStatus(){
		apService.updateAddressPolicyStatus(8, 0);
	}
	
	
	//修改地址策略
	@Ignore
	public void updateAddressPolicy(){
		AddressPolicy apolicy = new AddressPolicy();
		//String ips = "100.100.100.100;111.111.111.111";
		//String ips = "192.168.1.110-192.168.1.130"; 
		String ips = "192.168.1.110-192.168.1.130;192.168.1.140-192.168.1.150";
		apolicy.setAddressPolicyId(6);
		apolicy.setAddressPolicyName("test");
		apolicy.setAddressPolicyCreateTime(new Date());
		apolicy.setAddressPolicyStatus(0);
		apolicy.setAddressPolicyIsDelete(0);
		apolicy.setAddressPolicyMemo("这是一个修改测试");
		apService.updateAddressPolicy(apolicy, ips);
	}
	
	@Ignore
	public void saveAddressPolicy(){
		AddressPolicy apolicy = new AddressPolicy();
		//String ips = "100.100.100.100;111.111.111.111";
		//String ips = "192.168.1.110-192.168.1.130"; 
		String ips = "192.168.1.110-192.168.1.130;192.168.1.140-192.168.1.150";
		apolicy.setAddressPolicyCreateTime(new Date());
		apolicy.setAddressPolicyStatus(0);
		apolicy.setAddressPolicyIsDelete(0);
		apolicy.setAddressPolicyName("testsave");
		apolicy.setAddressPolicyMemo("这是一个添加测试");
		apService.updateAddressPolicy(apolicy, ips);
	}
	
	
}
