package com.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.productivity.java.syslog4j.Syslog;
import org.productivity.java.syslog4j.SyslogConfigIF;
import org.productivity.java.syslog4j.SyslogIF;
import org.productivity.java.syslog4j.impl.net.tcp.TCPNetSyslogConfig;
import org.productivity.java.syslog4j.impl.net.udp.UDPNetSyslogConfig;
import org.productivity.java.syslog4j.util.SyslogUtility;

import com.soc.model.conf.GlobalConfig;

public class SysLogSender {
	public static SyslogIF createSyslogIf(String name, String protocol,
			String host, String port, String charset, String facility) {
		SyslogConfigIF syslogConfig = null;
		if ("UDP".equals(protocol))
		{
			syslogConfig = new UDPNetSyslogConfig(facility);
		}
		if ("TCP".equals(protocol))
		{
			syslogConfig = new TCPNetSyslogConfig(facility);
		}
		syslogConfig.setCharSet(charset);
		syslogConfig.setHost(host);
		syslogConfig.setPort(Integer.parseInt(port));
		return Syslog.createInstance(name, syslogConfig);
	}

	public static void sender(SyslogIF syslogIF, String message) {
		if (null != syslogIF) {
			int level = SyslogUtility.getLevel("INFO");
			syslogIF.log(level, message);
		}
	}

	public static List<Map<String, SyslogIF>> getSysLogs(JSONArray syslogConfig) {
		if (null == syslogConfig || syslogConfig.isEmpty()) {
			return null;
		}
		List<Map<String, SyslogIF>> sysLogIFList = new ArrayList<Map<String, SyslogIF>>();
		for (int i = 0; i < syslogConfig.size(); i++) {
			JSONObject obj = syslogConfig.getJSONObject(i);
			SyslogIF syslogIF = SysLogSender.createSyslogIf(
					obj.getString("syslog_id"), obj.getString("protocol"),
					obj.getString("host_ip"), obj.getString("port"),
					obj.getString("code"), "USER");
			Map<String, SyslogIF> syslogIfMap = new HashMap<String, SyslogIF>();
			if (null != syslogIF) {
				syslogIfMap.put(obj.getString("syslog_id"), syslogIF);
			}
			if (null != syslogIfMap && !syslogIfMap.isEmpty()) {
				sysLogIFList.add(syslogIfMap);
			}
		}
		return sysLogIFList;
	}

	public static void initSysLog(String syslogConfig) {
		JSONArray sysLogConfig = JSONArray.fromObject(syslogConfig);
		List<Map<String, SyslogIF>> sysLogObjectList = SysLogSender
				.getSysLogs(sysLogConfig);
		GlobalConfig.SYSLOG_OBJECT = sysLogObjectList;
	}

	public static void sender(String message) {
		List<Map<String, SyslogIF>> sysLogObjectList = GlobalConfig.SYSLOG_OBJECT;
		for (Map<String, SyslogIF> sysLogMap : sysLogObjectList) {
			for (String key : sysLogMap.keySet()) {
				SyslogIF syslogIF = sysLogMap.get(key);
				SysLogSender.sender(syslogIF, message);
			}
		}
	}

	public static void updateSysLogConfig(JSONObject sysLogConfig) {
		List<Map<String, SyslogIF>> sysLogObjectList = GlobalConfig.SYSLOG_OBJECT;
		List<Map<String, SyslogIF>> deleList = new ArrayList<Map<String, SyslogIF>>();
		List<Map<String, SyslogIF>> addList = new ArrayList<Map<String, SyslogIF>>();
		String newKey = sysLogConfig.getString("syslog_id");
		boolean falg = true;
		for (Map<String, SyslogIF> sysLogMap : sysLogObjectList) {
			for (String key : sysLogMap.keySet()) {
				if (key.equals(newKey)) {
					SyslogIF syslogIF = sysLogMap.get(newKey);
					Syslog.destroyInstance(syslogIF);
					// syslogIF.shutdown();
					deleList.add(sysLogMap);
					SyslogIF newSyslogIF = SysLogSender.createSyslogIf(
							sysLogConfig.getString("syslog_id"),
							sysLogConfig.getString("protocol"),
							sysLogConfig.getString("host_ip"),
							sysLogConfig.getString("port"),
							sysLogConfig.getString("code"), "USER");

					if (null != syslogIF) {
						Map<String, SyslogIF> syslogIfMap = new HashMap<String, SyslogIF>();
						syslogIfMap.put(sysLogConfig.getString("syslog_id"),
								newSyslogIF);
						addList.add(syslogIfMap);
					}
					falg = false;
					break;
				}
			}
		}
		sysLogObjectList.removeAll(deleList);
		sysLogObjectList.addAll(addList);
		if (falg) {
			SyslogIF newSyslogIF = SysLogSender.createSyslogIf(
					sysLogConfig.getString("syslog_id"),
					sysLogConfig.getString("protocol"),
					sysLogConfig.getString("host_ip"),
					sysLogConfig.getString("port"),
					sysLogConfig.getString("code"), "USER");
			if (null != newSyslogIF) {
				Map<String, SyslogIF> syslogIfMap = new HashMap<String, SyslogIF>();
				syslogIfMap.put(sysLogConfig.getString("syslog_id"),
						newSyslogIF);
				sysLogObjectList.add(syslogIfMap);
			}
		}
		GlobalConfig.SYSLOG_OBJECT = sysLogObjectList;
	}

	public static void deleteSysLogConfig(String oldKey) {
		List<Map<String, SyslogIF>> sysLogObjectList = GlobalConfig.SYSLOG_OBJECT;
		List<Map<String, SyslogIF>> deleList = new ArrayList<Map<String, SyslogIF>>();
		for (Map<String, SyslogIF> sysLogMap : sysLogObjectList) {
			for (String key : sysLogMap.keySet()) {
				if (key.equals(oldKey)) {
					SyslogIF syslogIF = sysLogMap.get(oldKey);
					syslogIF.shutdown();
					deleList.add(sysLogMap);
				}
			}
		}
		sysLogObjectList.removeAll(deleList);
		GlobalConfig.SYSLOG_OBJECT = sysLogObjectList;
	}
	
	public static void main(String[] args) {

		JSONArray array = JSONArray
				.fromObject("[{\"syslog_id\":\"120012064090\",\"host_ip\":\"127.0.0.1\",\"port\":\"514\",\"protocol\":\"udp\",\"code\":\"UTF-8\"}]");
		List<Map<String, SyslogIF>> list = SysLogSender.getSysLogs(array);
		for (Map<String, SyslogIF> map : list) {
			for (String key : map.keySet()) {
				SyslogIF syslogIF = map.get(key);
				SysLogSender.sender(syslogIF, "fdsafdsa你好！");
			}
		}
	}

}
