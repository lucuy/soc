package com.compliance.service.cpManage.securityControl.impl;

import java.util.List;

import com.compliance.dao.cpManage.securityControl.SecurityControlDao;
import com.compliance.service.cpManage.securityControl.SecurityControlService;

public class SecurityControlServiceImpl implements SecurityControlService {
	public SecurityControlDao securityControlDao;


	public SecurityControlDao getSecurityControlDao() {
		return securityControlDao;
	}


	public void setSecurityControlDao(SecurityControlDao securityControlDao) {
		this.securityControlDao = securityControlDao;
	}


	public List<Integer> getSecurityControl2(int pkca) {
		List<Integer> list = securityControlDao.getSecurityControl2(pkca);

		return list;
	}
	
	public List<Integer> getSecurityControl3(int pkca) {
		List<Integer> list = securityControlDao.getSecurityControl3(pkca);

		return list;
	}
	
	public List<Integer> getSecurityControl4(int pkca) {
		List<Integer> list = securityControlDao.getSecurityControl4(pkca);

		return list;
	}

	
	public List<Integer> getControlTask4(String sysId) {
		List<Integer> list = securityControlDao.getControlTask4(sysId);

		return list;
	}
	public List<Integer> getControlTask3(String sysId) {
		List<Integer> list = securityControlDao.getControlTask3(sysId);

		return list;
	}
	public List<Integer> getControlTask2(String sysId) {
		List<Integer> list = securityControlDao.getControlTask2(sysId);

		return list;
	}
	
}
