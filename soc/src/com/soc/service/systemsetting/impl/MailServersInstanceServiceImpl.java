package com.soc.service.systemsetting.impl;

import java.util.HashMap;
import java.util.Map;

import com.soc.dao.systemsetting.MailServersInstanceDao;
import com.soc.model.conf.MailserversInstance;
import com.soc.service.impl.BaseServiceImpl;
import com.soc.service.systemsetting.MailServersInstanceService;

/**
 * 邮件服务请求Service层实现类
 * 
 * @author zsa
 * 
 */
public class MailServersInstanceServiceImpl extends BaseServiceImpl implements
		MailServersInstanceService {

	private MailServersInstanceDao mailserversinstancedao;

	public MailServersInstanceDao getMailserversinstancedao() {
		return mailserversinstancedao;
	}

	public void setMailserversinstancedao(
			MailServersInstanceDao mailserversinstancedao) {
		this.mailserversinstancedao = mailserversinstancedao;
	}

	/**
	 * 根据邮件地址查询配置
	 * 
	 * @param msiServername
	 * @return
	 */
	public MailserversInstance queryByName(String msiServername) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("msiServername", msiServername);
		return mailserversinstancedao.queryByName(map);
	}

}
