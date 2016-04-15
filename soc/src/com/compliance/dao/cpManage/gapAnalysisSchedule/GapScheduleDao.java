package com.compliance.dao.cpManage.gapAnalysisSchedule;

import java.util.List;

import com.compliance.model.cpManage.summaryAnalysis.ProjectShowcase;

//差距分析报告
//差距分析进度
public interface GapScheduleDao {
	public List<Integer> getGapScheduleTwoLevel(int pkca);

	public List<Integer> getGapScheduleThreeLevel(int pkca);

	public List<Integer> getGapScheduleFourLevel(int pkca);
	public List<ProjectShowcase> getProjectShowcase();
	public List<ProjectShowcase> getJinDuProjectShowcaseByName(int pkca);
}
