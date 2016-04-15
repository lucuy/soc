package com.compliance.service.cpManage.gapAnalysisSchedule.impl;

import java.util.ArrayList;
import java.util.List;

import com.compliance.dao.cpManage.gapAnalysisSchedule.GapScheduleDao;
import com.compliance.model.cpManage.summaryAnalysis.ProjectShowcase;
import com.compliance.service.cpManage.gapAnalysisSchedule.GapScheduleService;
//差距分析报告
//差距分析进度
public class GapScheduleServiceImpl implements GapScheduleService {
	private GapScheduleDao gapScheduleDao;

	public GapScheduleDao getGapScheduleDao() {
		return gapScheduleDao;
	}

	public void setGapScheduleDao(GapScheduleDao gapScheduleDao) {
		this.gapScheduleDao = gapScheduleDao;
	}

	public List<Integer> getGapScheduleTwoLevel(int pkca) {
		List<Integer> list = gapScheduleDao.getGapScheduleTwoLevel(pkca);
		return list;
	}

	public List<Integer> getGapScheduleThreeLevel(int pkca) {
		List<Integer> list = gapScheduleDao.getGapScheduleThreeLevel(pkca);
		return list;
	}

	public List<Integer> getGapScheduleFourLevel(int pkca) {
		List<Integer> list = gapScheduleDao.getGapScheduleFourLevel(pkca);
		return list;
	}
	
	
	public List<ProjectShowcase> getProjectShowcase() {

		List<ProjectShowcase> list = new ArrayList<ProjectShowcase>();
		list = gapScheduleDao.getProjectShowcase();
		return list;
	}
	
	
	
	public List<ProjectShowcase> getJinDuProjectShowcaseByName(int pkca) {

		List<ProjectShowcase> list = new ArrayList<ProjectShowcase>();
		list = gapScheduleDao.getJinDuProjectShowcaseByName(pkca);
		return list;
	}

}
