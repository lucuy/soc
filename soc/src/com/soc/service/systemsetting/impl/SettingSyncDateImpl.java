package com.soc.service.systemsetting.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.soc.dao.systemsetting.SettingDao;
import com.soc.model.systemsetting.Setting;
import com.soc.service.systemsetting.SettingSyncDate;
import com.util.StringUtil;

public class SettingSyncDateImpl implements SettingSyncDate {

	
	private SettingDao settingDao  ; 
	
	@Override
	public void settingNtpAddress(String str) {
		// TODO Auto-generated method stub

		String order[] = new String[]{"ntpdate",str} ; 
		
        try {
			Runtime.getRuntime().exec(order) ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	@Override
	public Date getSystemDate() {
		// TODO Auto-generated method stub
		InputStreamReader inputStream = null;
		BufferedReader br = null;
		Date date = null;
		try {
			// String order = "date -d today +\"%Y-%m-%d %T\"" ;
			String order[] = new String[] {"date", "-d", "today",
					"+\"%Y-%m-%d %T\"" };
			Process process = Runtime.getRuntime().exec(order);
			inputStream = new InputStreamReader(process.getInputStream());
			br = new BufferedReader(inputStream);

			String line = "";

			line = br.readLine();
			line = line.replaceAll("\"", "");

			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			try {
				date = format.parse(line);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				br.close();
				inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return date;
	}

	@Override
	public void setTime(String installTime) {
		// TODO Auto-generated method stub
		
		String order[] = new String[]{"date","-s",installTime} ;
		try {
			
			Process process = Runtime.getRuntime().exec(order) ;
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

	@Override
	public String getSyncIp() {

        //query exist ntpIP
		
		Map map = new HashMap() ; 
		map.put("key", "ntpIP") ; 
		List list = settingDao.query(map) ; 
		
		String ntpIP =  settingDao.queryByKey("ntpIP") ; 
		if(ntpIP == null && list.isEmpty()){
			Setting set = new Setting() ; 
			set.setKey("ntpIP") ; 
			set.setMemo("ntp服务器地址") ; 
			settingDao.insert(set) ; 
		}
		
		return ntpIP;
	}

	
	@Override
	public void updateNtpIP(String ntpStr) {
		// TODO Auto-generated method stub
		
		Setting set = new Setting();
		set.setKey("ntpIP") ; 
		set.setValue(ntpStr) ; 
		settingDao.update(set) ; 
		
	}
	

	public SettingDao getSettingDao() {
		return settingDao;
	}

	public void setSettingDao(SettingDao settingDao) {
		this.settingDao = settingDao;
	}

	
	

}
