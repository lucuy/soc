package com.compliance.service.cpManage.gapReportDocument.impl;

import java.util.List;

import com.compliance.dao.cpManage.gapReportDocument.GapReportDocumentDao;
import com.compliance.service.cpManage.gapReportDocument.GapReportDocumentService;

//合规管理
//差距分析报告
//导出差距分析报告
public class GapReportDocumentServiceImpl implements GapReportDocumentService {
	GapReportDocumentDao gapReportDocumentDao;

	public List<Integer> getGaplineAndSortTwo(int pkca) {
		List<Integer> list = gapReportDocumentDao.getGaplineAndSortTwo(pkca);
		return list;
	}

	public List<Integer> getGaplineAndSortThree(int pkca) {
		List<Integer> list = gapReportDocumentDao.getGaplineAndSortThree(pkca);
		return list;
	}

	public List<Integer> getGaplineAndSortFour(int pkca) {
		List<Integer> list = gapReportDocumentDao.getGaplineAndSortFour(pkca);
		return list;
	}

	public GapReportDocumentDao getGapReportDocumentDao() {
		return gapReportDocumentDao;
	}

	public void setGapReportDocumentDao(
			GapReportDocumentDao gapReportDocumentDao) {
		this.gapReportDocumentDao = gapReportDocumentDao;
	}

}
