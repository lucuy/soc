package com.compliance.dao.cpManage.summaryAnalysis;

import java.util.List;

import com.compliance.model.cpManage.summaryAnalysis.ProjectShowcase;

public interface SecurityGapDao {
	public List<Integer> getJiZhuWangLou2(int pkca);
	public List<Integer> getJiZhuWangLou3(int pkca);
	public List<Integer> getJiZhuWangLou4(int pkca);
	
	public List<ProjectShowcase> getProjectShowcaseByName(int pkca);
	
	
	public List<ProjectShowcase> getProjectShowcase();
}
