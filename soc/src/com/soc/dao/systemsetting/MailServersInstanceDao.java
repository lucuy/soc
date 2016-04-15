package com.soc.dao.systemsetting;

import java.util.Map;

import com.soc.model.conf.MailserversInstance;

/**
 * 邮件服务请求Dao层接口
 * 
 * @author zsa
 * 
 */
public interface MailServersInstanceDao {

	/**
	 * 根据邮件地址查询配置
	 * 
	 * @param msiServername
	 * @return
	 */
	public MailserversInstance queryByName(Map map);

}
