package com.compliance.dao.rank;

import java.util.List;
import java.util.Map;

import com.compliance.model.rank.RankReport;

public interface RankReportDao {
	
	/**
	 * 查询所有数据
	 * 
	 * @param Map
	 * @return List<RankReport>
	 */
	@SuppressWarnings("unchecked")
	public List<RankReport> query();
	
	
}
