package com.soc.service.systemsetting;

import com.soc.model.conf.MailserversInstance;

/**
 * 邮件服务请求Service层接口
 * 
 * @author zsa
 * 
 */
public interface MailServersInstanceService {
	/**
	 * 根据邮件地址查询配置
	 * 
	 * @param msiServername
	 * @return
	 */
	public MailserversInstance queryByName(String msiServername);

}
