package com.compliance.dao.cpManage.gapReportDocument;

import java.util.List;


//合规管理
//差距分析报告
//导出差距分析报告
public interface GapReportDocumentDao {
	public List<Integer> getGaplineAndSortTwo(int pkca);
	public List<Integer> getGaplineAndSortThree(int pkca);
	public List<Integer> getGaplineAndSortFour(int pkca);
}
