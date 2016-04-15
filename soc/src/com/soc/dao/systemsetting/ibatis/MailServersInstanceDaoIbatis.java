package com.soc.dao.systemsetting.ibatis;

import java.util.Map;

import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.dao.systemsetting.MailServersInstanceDao;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.conf.MailserversInstance;

/**
 * 邮件服务请求Dao层实现类
 * 
 * @author zsa
 * 
 */
public class MailServersInstanceDaoIbatis extends BaseDaoIbatis implements
		MailServersInstanceDao {

	/**
	 * 根据邮件地址查询配置
	 * 
	 * @param msiServername
	 * @return
	 */
	public MailserversInstance queryByName(Map map) {
		return (MailserversInstance) super.queryForObject(
				GlobalConfig.sqlId+"mailservers_instance.queryByName", map);
	}

}
