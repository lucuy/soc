package com.compliance.service.cpManage.securityTable.impl;

import java.util.List;

import com.compliance.dao.cpManage.securityTable.SecurityTableDao;
import com.compliance.service.cpManage.securityTable.SecurityTableService;
//合规管理
////差距分析
//差距分析报告
//表：安全领域符合度

public class SecurityTableServiceImpl implements SecurityTableService {
	private SecurityTableDao securityTableDao;

	public SecurityTableDao getSecurityTableDao() {
		return securityTableDao;
	}

	public void setSecurityTableDao(SecurityTableDao securityTableDao) {
		this.securityTableDao = securityTableDao;
	}

	public List<Integer> getSecurityTableServiceTwo(int fkca) {
		List<Integer> sum = securityTableDao.getSecurityTableTwo(fkca);
		return sum;
	}

	public List<Integer> getSecurityTableServiceThree(int fkca) {
		List<Integer> sum = securityTableDao.getSecurityTableThree(fkca);
		return sum;
	}

	public List<Integer> getSecurityTableServiceFour(int fkca) {
		List<Integer> sum = securityTableDao.getSecurityTableFour(fkca);
		return sum;
	}
}
