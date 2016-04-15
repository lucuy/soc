package com.soc.service.systemsetting;

import java.util.Map;

import com.soc.model.systemsetting.Setting;
import com.soc.service.BaseService;

public interface SystemSettingService extends BaseService {

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
