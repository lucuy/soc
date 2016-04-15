package com.util.reportForm.model;


import java.util.HashMap;
import java.util.Map;

public class ColConversion {

	String colName = new String();

	Map<String, String> map = new HashMap<String, String>();
	
	// 需包装后显示字段的ID值
	// 此处定制版与主线版不同。
	// 48是数据库中这个字段的ID 如果修改了数据库 同时还需修改这里。
	// 数据库中相关值是默认值，不建议修改
	public final static String eventId = "48"; 
	

	public ColConversion(String colName) {
		this.colName = colName;
		init();
	}

	public ColConversion() {
		init();
	}

	public void init() {
		
		map.clear();
		// serverlog eventid 字段初始化 汉字
		if (colName.equals(eventId)) {
			String[] values = { "登录", "登出", "新建分组", "修改分组", "删除分组", "新建用户",
					"修改用户", "删除用户", "新建角色", "修改角色", "删除角色", "新建策略", "修改策略",
					"分发策略", "删除策略", "移动客户端", "卸载客户端", "下载客户端", "生成客户端",
					"删除客户端", "服务器设置", "消息发送", "新建时间对象", "修改时间对象", "上传软件",
					"分发软件", "分发补丁", "新建补丁分组", "修改补丁分组", "删除消息", "删除系统日志",
					"删除报警日志", "删除网络行为", "删除程序行为", "删除文件访问", "删除服务器日志",
					"删除补丁分发日志", "删除操作系统日志" };
			for (Integer i = 1; i <= values.length; i++) {
				map.put(i.toString(), values[i-1]);
			}
		}
		
	}

	public String getColName() {
		return colName;
	}


	public void setColName(String colName) {
		this.colName = colName;
		init();
	}

	public Map<String, String> getMap() {
		return map;
	}

	public String getColValue(String value) {
		value = map.get(value);
		if (null == value || "".equals(value)) {
			value = "";
		}
		return value;
	}

}
