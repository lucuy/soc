package com.soc.service.systemsetting;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.soc.model.systemsetting.Setting;

/**
 * Description: 系统设置service接口
 * 
 * @author 郭煜玺
 * @Version 1.0
 * @Created at 2010-12-31
 * @Modified by
 * 
 */
public interface SettingService {

	/**
	 * 查询数据
	 * 
	 * @param Setting
	 * @return List<Setting>
	 */
	public List<Setting> query(Setting setting);

	/**
	 * 查询key相似的网卡设置
	 * 
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Setting> queryByConIP(Map map);

	/**
	 * 根据ID查询数据
	 * 
	 * @param int
	 * @return Setting
	 */
	public Setting queryById(int id);

	/**
	 * 根据Key更新数据
	 * 
	 * @param String
	 * @return Setting
	 */
	public void updateByKey(String key, Setting setting);

	/**
	 * 根据Key查询数据
	 * 
	 * @param String
	 * @return Setting
	 */
	public String queryByKey(String key);

	/**
	 * 新增或更新数据
	 * 
	 * @param Setting
	 * @return Integer
	 */
	public Integer update(Setting setting);

	/**
	 * 删除数据
	 * 
	 * @param String
	 * @return void
	 */
	public void delete(String key);

	/**
	 * 根据ID删除数据
	 * 
	 * @param int
	 * @return void
	 */
	public void delete(int id);

	
	public JSONObject getHaMessage(String data) ;
	/**
	 * 获取LDAP服务器浮动IP地址集合
	 * @param data
	 * @return
	 */
	public List<String> getLDAPHaIp(String data);
	
	/**
	 * 获取双机状态
	 * @param type
	 * @return
	 */
	public String getHAStart(int type,List<String> list); 
	
	
	/**
	 * 更改syslog的设置
	 * @param setting
	 */
	public void updateSysLog(Setting setting);
	
	/**
	 * 根据ID删除syslog的设置
	 * @param syslogJsonId
	 */
	public void deleteSysLog(String syslogJsonId);

}
