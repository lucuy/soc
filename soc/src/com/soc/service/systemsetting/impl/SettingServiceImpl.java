package com.soc.service.systemsetting.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.ha.client.Client;

import com.soc.dao.systemsetting.SettingDao;
import com.soc.model.systemsetting.Setting;
import com.soc.service.impl.BaseServiceImpl;
import com.soc.service.systemsetting.SettingService;
import com.util.SysLogSender;

/**
 * Description: 系统设置service实现类
 * 
 * @author 郭煜玺
 * @Version 1.0
 * @Created at 2010-12-13
 * @Modified by
 * 
 */

public class SettingServiceImpl extends BaseServiceImpl implements
		SettingService {

	private SettingDao settingDao;

	/**
	 * 查询设置项
	 * 
	 * @param Setting
	 * @return List<Setting>
	 */
	public List<Setting> query(Setting setting) {
		// 处理查询条件
		Map<String, Object> map = new HashMap<String, Object>();
		if (setting != null) {
			map.put("key", setting.getKey());
			map.put("value", setting.getValue());
			map.put("type", setting.getType());
		}

		return settingDao.query(map);
	}

	/**
	 * 查询key相似的网卡设置
	 * 
	 * @param key
	 * @return
	 */
	public List<Setting> queryByConIP(Map map) {
		return settingDao.queryByConIP(map);
	}

	/**
	 * 根据ID查询
	 * 
	 * @param int
	 * @return Setting
	 */
	public Setting queryById(int id) {
		return settingDao.queryById(id);
	}

	/**
	 * 根据Key更新
	 * 
	 * @param String
	 * @return Setting
	 */
	public void updateByKey(String key, Setting setting) {
		int count = settingDao.updateByKey(key);
		if (count < 1) {
			settingDao.insert(setting);
		} else {
			settingDao.update(setting);
		}

	}

	/**
	 * 根据Key查询
	 * 
	 * @param String
	 * @return Setting
	 */
	public String queryByKey(String key) {
		return settingDao.queryByKey(key);
	}

	/**
	 * 新增数据
	 * 
	 * @param Setting
	 * @return Integer
	 */
	public Integer update(Setting setting) {
		Integer id;
		if (setting.getId() != null) {
			settingDao.update(setting);
			id = setting.getId();
		} else {
			id = settingDao.insert(setting);
		}
		return id;
	}

	/**
	 * 删除数据
	 * 
	 * @param String
	 * @return void
	 */
	public void delete(String key) {
		settingDao.delete(key);
	}

	/**
	 * 根据ID删除数据
	 * 
	 * @param int
	 * @return void
	 */
	public void delete(int id) {
		settingDao.delete(id);
	}
	
	
	public JSONObject getHaMessage(String data) {
		if (data.isEmpty()) {
			System.err.println("Data is null");
			return null;
		}
		JSONObject linkJsonObject = null;
		JSONArray jsonArray = JSONArray.fromObject("[" + data + "]");
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject object = jsonArray.getJSONObject(i);
			if (object.getBoolean("mainLink")) {
				linkJsonObject = object;
				break;
			}
		}
		return linkJsonObject;
	}
	
	@Override
	public List<String> getLDAPHaIp(String data) {
		if (data.isEmpty()) {
			System.err.println("Data is null");
			return null;
		}
		List<String> haIP = new ArrayList<String>();
		JSONArray jsonArray = JSONArray.fromObject("[" + data + "]");
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject object = jsonArray.getJSONObject(i);
			haIP.add(object.getString("mainHaIp"));
		}
		return haIP;
	}
	
	@Override
	public String getHAStart(int type, List<String> ips){
		StringBuilder messBuilder = new StringBuilder();
		if(null == ips || ips.isEmpty()){
			System.out.println("IP address in null");
			return null;
		}
		for(String ip : ips){
			Client client = new Client(ip);
			Socket so = client.connect();
			if(null != so){

				
				try {
					BufferedReader bReader = new BufferedReader(
							new InputStreamReader(so.getInputStream()));
					PrintWriter out = new PrintWriter(so.getOutputStream(),
							true);
					String tempStr = bReader.readLine();
					if ("OK".equals(tempStr.trim())) {
						out.println(type);
						out.println("");
						out.println("");
						out.println("END");
						out.flush();
						
						while ((tempStr = bReader.readLine()) != null) {
							if(messBuilder.length() > 0)
								messBuilder.append("\n"+tempStr.trim());
							else
								messBuilder.append(tempStr.trim());
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			}
		}
		
		return messBuilder.toString();
	}
	
	

	public SettingDao getSettingDao() {
		return settingDao;
	}

	public void setSettingDao(SettingDao settingDao) {
		this.settingDao = settingDao;
	}

	@Override
	public void updateSysLog(Setting setting) {
		// TODO Auto-generated method stub
		Setting syslog = settingDao.querySettingByKey(setting.getKey());
		if (null != syslog) {
			String tempVal = syslog.getValue();
			if (null != tempVal && !"".equals(tempVal)) {
				JSONArray array = JSONArray.fromObject(tempVal);
				JSONObject newObj = JSONObject.fromObject(setting.getValue());
				for (int i = 0; i < array.size(); i++) {
					JSONObject obj = array.getJSONObject(i);
					if (obj.get("syslog_id").equals(
							newObj.getString("syslog_id"))) {
						array.remove(obj);
						array.add(newObj);
						SysLogSender.updateSysLogConfig(newObj);
						setting = null;
					}
				}
				if (null != setting) {
					array.add(JSONObject.fromObject(setting.getValue()));
					SysLogSender.updateSysLogConfig(JSONObject.fromObject(setting.getValue()));
				}
				syslog.setValue(array.toString());
			} else {
				syslog.setValue(setting.getValue());
			}
			settingDao.update(syslog);
		} else {
			String temp = setting.getValue();
			setting.setValue("[" + temp + "]");
			settingDao.insert(setting);
			SysLogSender.updateSysLogConfig(JSONObject.fromObject(temp));
		}
		
	}

	@Override
	public void deleteSysLog(String syslogJsonId) {
		// TODO Auto-generated method stub
		Setting syslog = settingDao.querySettingByKey("SETTING_SYSLOG_JSON");
		if (null != syslog) {
			String temp = syslog.getValue();
			if (null != temp && !"".equals(temp)) {
				JSONArray array = JSONArray.fromObject(temp);
				for (int i = 0; i < array.size(); i++) {
					JSONObject obj = array.getJSONObject(i);
					if (syslogJsonId.equals(obj.getString("syslog_id"))) {
						array.remove(obj);
						SysLogSender.deleteSysLogConfig(obj.getString("syslog_id"));
					}
				}
				syslog.setValue(array.toString());
			}
			settingDao.update(syslog);
		}
	}

}
