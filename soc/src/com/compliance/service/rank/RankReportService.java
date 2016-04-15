package com.compliance.service.rank;

import java.util.List;

import com.compliance.model.rank.RankReport;


public interface RankReportService {
		/**
		 * 查询所有数据
		 * 
		 * @param Map
		 * @return List<RankReport>
		 */
		@SuppressWarnings("unchecked")
		public List<RankReport> query();
		
	
}

