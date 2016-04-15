package com.soc.junit.test.user;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.soc.junit.test.base.BaseTest;
import com.soc.model.asset.AutoToPo;
import com.soc.model.user.User;
import com.soc.service.asset.AssetService;
import com.soc.service.user.UserService;
import com.topo.service.device.DeviceManageService;
import com.util.AutoToPoUtils;

public class UserTest extends BaseTest{

	private UserService us;
	private AssetService assetManager;
	private DeviceManageService   deviceManageService;
	
	@Before
	public void init(){
		super.init();
		us=(UserService) super.getBean("userManager");
		assetManager=(AssetService) super.getBean("assetManager");
		deviceManageService=(DeviceManageService) super.getBean("deviceService");
	}
	
	@Ignore
	public void queryAll(){
		List<User> list=us.queryAll();
		for (User user : list) {
			//System.out.println(user.getUserLoginName());
		}
	}
	@Test
	public void ceshi(){
		AutoToPo a = new AutoToPo();
		a.setIp("170.168.139.2");
		a.setType(1);
		AutoToPo a1 = new AutoToPo();
		a1.setIp("170.168.139.3");
		a1.setSuperIp("170.168.139.2");
		a1.setType(2);
		AutoToPo a2 = new AutoToPo();
		a2.setIp("170.168.139.4");
		a2.setSuperIp("170.168.139.2");
		a2.setType(2);
		AutoToPo a3 = new AutoToPo();
		a3.setIp("170.168.139.5");
		a3.setSuperIp("170.168.139.2");
		a3.setType(2);
		List<AutoToPo> list  = new ArrayList<AutoToPo>();
		list.add(a);
		list.add(a1);
		list.add(a2);
		list.add(a3);
		AutoToPoUtils at = new AutoToPoUtils(assetManager, deviceManageService);
		at.autoInsert(list);
	}
	
}
