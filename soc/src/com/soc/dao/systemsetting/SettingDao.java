package com.soc.dao.systemsetting;

import java.util.List;
import java.util.Map;

import com.soc.model.systemsetting.Setting;

/**
 * Description: 角色dao接口
 * 
 * @author 郭煜玺
 * @Version 1.0
 * @Created at 2010-12-31
 * @Modified by
 */
public interface SettingDao {

	/**
	 * 查询数据
	 * 
	 * @param String
	 * @return List<Setting>
	 */
	@SuppressWarnings("unchecked")
	public List<Setting> query(Map map);

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
	public int updateByKey(String key);

	/**
	 * 根据Key查询数据
	 * 
	 * @param String
	 * @return Setting
	 */
	public String queryByKey(String key);

	/**
	 * 新建数据并返回PK
	 * 
	 * @param Setting
	 * @return
	 */
	public int insert(Setting setting);

	/**
	 * 更新数据
	 * 
	 * @param Setting
	 * @return
	 */
	public void update(Setting setting);

	/**
	 * 更新全部数据
	 * 
	 * @param Setting
	 * @return
	 */
	public void updateById(Setting setting);

	/**
	 * 删除数据
	 * 
	 * @param String
	 */
	public void delete(String key);

	/**
	 * 根据ID删除数据
	 * 
	 * @param int
	 */
	public void delete(int id);
	
	/**
	 * 根据key查询setting对象
	 * @param key
	 * @return
	 */
	public Setting querySettingByKey(String key);

}
