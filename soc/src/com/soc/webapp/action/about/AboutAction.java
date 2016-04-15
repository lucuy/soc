package com.soc.webapp.action.about;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.soc.model.serial.Serial;
import com.soc.service.asset.AssetService;
import com.soc.webapp.action.BaseAction;

public class AboutAction  extends BaseAction{
	private static final long serialVersionUID = 1L;
	
	private String verType;			// 0:试用版 | 1:正式版
	private String verTypeDesc;
	
	private String overDay;
	private String resourceNum;
	private String overResourceNum;
	
	//资产处理类
	private AssetService assetManager;
	
	/**
	 * 关于产品页面
	 * @return
	 */
	public String about() {
		
		// 判断软件版本
		if(Serial.SERIAL_AUTH_DAY == 10950) {
			verType = "1";
			verTypeDesc = "正式版";
		} else {
			verType = "0";
			verTypeDesc = "试用版";
		}
		System.out.println("gen time:" + Serial.SERIAL_GEN_TIME + "; auth day:"  + Serial.SERIAL_AUTH_DAY + "; now:" + new Date().getTime());
		// 剩余天数
		if(verType.equals("0")) {
			long lastDate = Serial.SERIAL_GEN_TIME + (86400 * Serial.SERIAL_AUTH_DAY);
			long overMillSec = lastDate - new Date().getTime() / 1000;
			overDay = String.valueOf(overMillSec / 60 / 60 / 24 + 1);
			System.out.println("lastDate:" + lastDate + "; overMillSec:"  + overMillSec);
		}
		
		// 资源点数
		resourceNum = String.valueOf(Serial.SERIAL_RESOURCE_NUM);
		//Resource resource = new Resource();
		//resource.setId(0);
		Map map =new HashMap();
		overResourceNum = String.valueOf(Serial.SERIAL_RESOURCE_NUM - assetManager.count(map));
		
		
		return SUCCESS;
	}

	public String getVerType() {
		return verType;
	}

	public void setVerType(String verType) {
		this.verType = verType;
	}

	public String getVerTypeDesc() {
		return verTypeDesc;
	}

	public void setVerTypeDesc(String verTypeDesc) {
		this.verTypeDesc = verTypeDesc;
	}

	public String getOverDay() {
		return overDay;
	}

	public void setOverDay(String overDay) {
		this.overDay = overDay;
	}

	public String getResourceNum() {
		return resourceNum;
	}

	public void setResourceNum(String resourceNum) {
		this.resourceNum = resourceNum;
	}

	public String getOverResourceNum() {
		return overResourceNum;
	}

	public void setOverResourceNum(String overResourceNum) {
		this.overResourceNum = overResourceNum;
	}

	public AssetService getAssetManager() {
		return assetManager;
	}

	public void setAssetManager(AssetService assetManager) {
		this.assetManager = assetManager;
	}
	
}
