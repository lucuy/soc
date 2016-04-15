package com.compliance.service.rank.impl;

import java.util.List;
import java.util.Map;

import com.compliance.dao.rank.RankReportDao;
import com.compliance.model.rank.RankReport;
import com.compliance.service.impl.BaseServiceImpl;
import com.compliance.service.rank.RankReportService;
 

public class RankReportServiceImpl extends BaseServiceImpl implements RankReportService {
	private RankReportDao rankReportDao;

	public List<RankReport> query() {
		return rankReportDao.query();
	}

	public RankReportDao getRankReportDao() {
		return rankReportDao;
	}

	public void setRankReportDao(RankReportDao rankReportDao) {
		this.rankReportDao = rankReportDao;
	}

	

}
