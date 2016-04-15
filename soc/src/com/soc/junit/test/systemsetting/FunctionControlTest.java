package com.soc.junit.test.systemsetting;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.soc.junit.test.base.BaseTest;
import com.soc.service.systemsetting.SettingFunctionControlSerive;

public class FunctionControlTest extends BaseTest{
	
	private SettingFunctionControlSerive settingFunctionControlManager;
	
	@Before
	public void init(){
		super.init();
		settingFunctionControlManager=(SettingFunctionControlSerive) super.getBean("settingFunctionControlManager");
	}
	
	
	
	/*@Ignore
	public void functionColtrolAction(){
		settingFunctionControlManager.SettingFunctionControl("JSOC-EM");
	}*/
	/*@Test
	public void functionColtrolAction(){
		settingFunctionControlManager.SettingFunctionControl("JSOC-CM");
	}*/
	/*@Test
	public void functionColtrolAction(){
		settingFunctionControlManager.SettingFunctionControl("JSOC-NM");
	}*/
	@Test
	public void functionColtrolAction(){
		settingFunctionControlManager.SettingFunctionControl("JSOC-RM");
	}
}
