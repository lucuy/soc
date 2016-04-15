package com.soc.dao.systemsetting.ibatis;

import java.util.List;
import java.util.Map;

import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.dao.systemsetting.SettingDao;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.systemsetting.BaseConf;
import com.soc.model.systemsetting.Setting;

/**
 * Description: 系统设置dao实现类
 * 
 * @author 郭煜玺
 * @Version 1.0
 * @Created at 2010-12-31
 * @Modified by
 * 
 */
public class SettingDaoIbatis extends BaseDaoIbatis implements SettingDao {

	/**
	 * 查询数据
	 * 
	 * @param Map
	 * @return List<Role>
	 */
	@SuppressWarnings("unchecked")
	public List<Setting> query(Map map) {
		return (List<Setting>) super.queryForList(GlobalConfig.sqlId+"setting.query", map);
	}

	/**
	 * 查询key相似的网卡设置
	 * 
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Setting> queryByConIP(Map map) {
		return (List<Setting>) super.queryForList(GlobalConfig.sqlId+"setting.queryByConIP", map);
	}

	/**
	 * 根据ID查询数据
	 * 
	 * @param int
	 * @return Setting
	 */
	public Setting queryById(int id) {
		return (Setting) super.queryForObject(GlobalConfig.sqlId+"setting.queryById", id);
	}

	/**
	 * 根据Key更新数据
	 * 
	 * @param String
	 * @return Setting
	 */
	public int updateByKey(String key) {
		// TODO Auto-generated method stub
		return (Integer) super.queryForObject(GlobalConfig.sqlId+"setting.updateByKey", key);
	}

	/**
	 * 根据Key查询
	 * 
	 * @param String
	 * @return Setting
	 */
	public String queryByKey(String key) {
		// TODO Auto-generated method stub
		return (String) super.queryForObject(GlobalConfig.sqlId+"setting.queryByKey", key);
	}

	/**
	 * 新建数据并返回PK
	 * 
	 * @param Setting
	 * @return
	 */
	public int insert(BaseConf setting) {
		Integer pk = 0;
		Object obj = super.create(GlobalConfig.sqlId+"setting.insert", setting);
		if (obj != null) {
			pk = Integer.parseInt(obj.toString());
		}
		return pk;
	}

	/**
	 * 更新数据
	 * 
	 * @param Setting
	 * @return
	 */
	public void update(Setting setting) {
		super.update(GlobalConfig.sqlId+"setting.update", setting);
	}

	/**
	 * 更新全部数据
	 * 
	 * @param Setting
	 * @return
	 */
	public void updateById(Setting setting) {
		super.update(GlobalConfig.sqlId+"setting.updateById", setting);
	}

	/**
	 * 删除数据
	 * 
	 * @param String
	 */
	public void delete(String key) {
		super.delete(GlobalConfig.sqlId+"setting.deleteById", key);
	}

	/**
	 * 根据ID删除数据
	 * 
	 * @param int
	 */
	public void delete(int id) {
		super.delete(GlobalConfig.sqlId+"setting.delete", id);
	}

	@Override
	public int insert(Setting setting) {
		return (Integer)super.create(GlobalConfig.sqlId+"setting.insert", setting);
	}

	@Override
	public Setting querySettingByKey(String key) {
		// TODO Auto-generated method stub
		return (Setting) super.queryForObject(GlobalConfig.sqlId+"setting.queryBykey", key);
	}

}
