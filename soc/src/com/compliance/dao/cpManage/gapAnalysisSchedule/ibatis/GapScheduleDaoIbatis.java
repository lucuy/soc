package com.compliance.dao.cpManage.gapAnalysisSchedule.ibatis;

import java.util.ArrayList;
import java.util.List;

import com.compliance.dao.cpManage.gapAnalysisSchedule.GapScheduleDao;
import com.compliance.dao.ibatis.BaseDaoiBatis;
import com.compliance.model.cpManage.gapAnalysisSchedule.GapSchedule;
import com.compliance.model.cpManage.summaryAnalysis.ProjectShowcase;
import com.soc.model.conf.GlobalConfig;

//差距分析报告
//差距分析进度
public class GapScheduleDaoIbatis extends BaseDaoiBatis implements
		GapScheduleDao {

	public List<Integer> getGapScheduleTwoLevel(int pkca) {
		List<Integer> list = new ArrayList<Integer>();
		GapSchedule gapSchedule = new GapSchedule();
		String[] strs = { "6.1%", "6.2%", "6.3%", "6.4%", "6.5%", "6.6%",
				"6.7%" };
		for (int j = 0; j < 7; j++) {
			gapSchedule.setFkca(pkca);
			gapSchedule.setCiaSort(strs[j]);
			Integer count = (Integer) this
					.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getGapSchedule.count", gapSchedule);
			list.add(count);
		}
		return list;

	}

	public List<Integer> getGapScheduleThreeLevel(int pkca) {
		List<Integer> list = new ArrayList<Integer>();
		GapSchedule gapSchedule = new GapSchedule();
		String[] strs = { "7.1%", "7.2%", "7.3%", "7.4%", "7.5%", "7.6%",
				"7.7%" };
		for (int j = 0; j < 7; j++) {
			gapSchedule.setFkca(pkca);
			gapSchedule.setCiaSort(strs[j]);
			Integer count = (Integer) this
					.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getGapSchedule.count", gapSchedule);
			list.add(count);
		}
		return list;

	}

	public List<Integer> getGapScheduleFourLevel(int pkca) {
		List<Integer> list = new ArrayList<Integer>();
		GapSchedule gapSchedule = new GapSchedule();
		String[] strs = { "8.1%", "8.2%", "8.3%", "8.4%", "8.5%", "8.6%",
				"8.7%" };
		for (int j = 0; j < 7; j++) {
			gapSchedule.setFkca(pkca);
			gapSchedule.setCiaSort(strs[j]);
			Integer count = (Integer) this
					.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getGapSchedule.count", gapSchedule);
			list.add(count);
		}
		return list;

	}
	
	
	public List<ProjectShowcase> getProjectShowcase() {
		ProjectShowcase projectShowcase = new ProjectShowcase();
		List<ProjectShowcase> list = new ArrayList<ProjectShowcase>();
		list = this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"getGapSchedule.show");
		
		return list;
	}
	
	
	public List<ProjectShowcase> getJinDuProjectShowcaseByName(int pkca) {
		ProjectShowcase projectShowcase = new ProjectShowcase();
		List<ProjectShowcase> list = new ArrayList<ProjectShowcase>();
		list = this.getSqlMapClientTemplate().queryForList( GlobalConfig.sqlId+"getJinDuProjectShowcase.showByName",pkca);
		
		return list;
	}

}
