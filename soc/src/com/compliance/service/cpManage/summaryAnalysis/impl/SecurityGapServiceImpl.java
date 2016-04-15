package com.compliance.service.cpManage.summaryAnalysis.impl;

import java.util.ArrayList;
import java.util.List;

import com.compliance.dao.cpManage.summaryAnalysis.SecurityGapDao;
import com.compliance.model.cpManage.summaryAnalysis.ProjectShowcase;
import com.compliance.service.cpManage.summaryAnalysis.SecurityGapService;

public class SecurityGapServiceImpl implements SecurityGapService {

	public SecurityGapDao securityGapDao;

	public SecurityGapDao getSecurityGapDao() {
		return securityGapDao;
	}

	public void setSecurityGapDao(SecurityGapDao securityGapDao) {
		this.securityGapDao = securityGapDao;
	}

	public List<Integer> getJiZhuWangLou3(int pkca) {

		List<Integer> list = securityGapDao.getJiZhuWangLou3(pkca);

		return list;

	}
	
	
	public List<Integer> getJiZhuWangLou4(int pkca) {

		List<Integer> list = securityGapDao.getJiZhuWangLou4(pkca);

		return list;

	}
	
	public List<Integer> getJiZhuWangLou2(int pkca) {

		List<Integer> list = securityGapDao.getJiZhuWangLou2(pkca);

		return list;

	}

	public List<ProjectShowcase> getProjectShowcase() {

		List<ProjectShowcase> list = new ArrayList<ProjectShowcase>();
		list = securityGapDao.getProjectShowcase();
		return list;
	}
	
	
	public List<ProjectShowcase> getProjectShowcaseByName(int pkca) {

		List<ProjectShowcase> list = new ArrayList<ProjectShowcase>();
		list = securityGapDao.getProjectShowcaseByName(pkca);
		return list;
	}

}
