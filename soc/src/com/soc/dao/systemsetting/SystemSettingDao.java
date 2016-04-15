package com.soc.dao.systemsetting;

import java.util.Map;

import com.soc.dao.BaseDao;
import com.soc.model.systemsetting.Setting;

public interface SystemSettingDao extends BaseDao
{
    
	/**
	 * 修改同步用户服务器ip
	 * @param map
	 * @return
	 */
	public long insert4AIp(String str);
	/**
	 * 修改同步用户服务器ip
	 * @param map
	 * @return
	 */
	public void update4AIp(String str);
	
	/**
	 * 查询同步服务器Ip
	 * @return
	 */
	public String query4AIpByKey();
	
}
