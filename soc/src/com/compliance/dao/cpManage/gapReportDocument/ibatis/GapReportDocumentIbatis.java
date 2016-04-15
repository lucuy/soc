package com.compliance.dao.cpManage.gapReportDocument.ibatis;

import java.util.ArrayList;
import java.util.List;
import com.compliance.dao.cpManage.gapReportDocument.GapReportDocumentDao;
import com.compliance.dao.ibatis.BaseDaoiBatis;
import com.compliance.model.cpManage.gapReportDocument.ReportDocument;
import com.compliance.model.cpManage.securityControl.LineAndSort;
import com.soc.model.conf.GlobalConfig;

//合规管理
//差距分析报告
//导出差距分析报告

public class GapReportDocumentIbatis extends BaseDaoiBatis implements
		GapReportDocumentDao {
	public List<Integer> getGaplineAndSortTwo(int pkca) {
		String a[][] = new String[10][7];
		List<Integer> list = new ArrayList<Integer>();
		LineAndSort lineAndSort = new LineAndSort();
		ReportDocument reportDocument = new ReportDocument();
		String[] strs = { "6.1%", "6.2%", "6.3%", "6.4%", "6.5%", "6.6%",
				"6.7%" };
		String[] line = { "CIA_A", "CIA_B", "CIA_C", "CIA_D", "CIA_E", "CIA_F",
				"CIA_G", "CIA_H", "CIA_I", "CIA_J" };
		for (int j = 0; j < 7; j++) {
			lineAndSort.setSort(strs[j]);

			lineAndSort.setPkca(pkca);
			Integer A = (Integer) this.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getSecurityControl.showCIA_AInOneAndZore",
							lineAndSort);

			Integer B = (Integer) this.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getSecurityControl.showCIA_BInOneAndZore",
							lineAndSort);

			Integer C = (Integer) this.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getSecurityControl.showCIA_CInOneAndZore",
							lineAndSort);
			Integer D = (Integer) this.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getSecurityControl.showCIA_DInOneAndZore",
							lineAndSort);
			Integer E = (Integer) this.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getSecurityControl.showCIA_EInOneAndZore",
							lineAndSort);
			Integer F = (Integer) this.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getSecurityControl.showCIA_FInOneAndZore",
							lineAndSort);
			Integer G = (Integer) this.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getSecurityControl.showCIA_GInOneAndZore",
							lineAndSort);
			Integer H = (Integer) this.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getSecurityControl.showCIA_HInOneAndZore",
							lineAndSort);
			Integer I = (Integer) this.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getSecurityControl.showCIA_IInOneAndZore",
							lineAndSort);
			Integer J = (Integer) this.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getSecurityControl.showCIA_JInOneAndZore",
							lineAndSort);
			int sum = A + B + C + D + E + F + G + H + I + J;

			list.add(sum);
		}
		return list;

	}

	public List<Integer> getGaplineAndSortThree(int pkca) {
		String a[][] = new String[10][7];
		LineAndSort lineAndSort = new LineAndSort();
		List<Integer> list = new ArrayList<Integer>();
		ReportDocument reportDocument = new ReportDocument();
		String[] strs = { "7.1%", "7.2%", "7.3%", "7.4%", "7.5%", "7.6%",
				"7.7%" };
		String[] line = { "CIA_A", "CIA_B", "CIA_C", "CIA_D", "CIA_E", "CIA_F",
				"CIA_G", "CIA_H", "CIA_I", "CIA_J" };
		for (int j = 0; j < 7; j++) {
			lineAndSort.setSort(strs[j]);

			lineAndSort.setPkca(pkca);
			Integer A = (Integer) this.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getSecurityControl.showCIA_AInOneAndZore",
							lineAndSort);

			Integer B = (Integer) this.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getSecurityControl.showCIA_BInOneAndZore",
							lineAndSort);

			Integer C = (Integer) this.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getSecurityControl.showCIA_CInOneAndZore",
							lineAndSort);
			Integer D = (Integer) this.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getSecurityControl.showCIA_DInOneAndZore",
							lineAndSort);
			Integer E = (Integer) this.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getSecurityControl.showCIA_EInOneAndZore",
							lineAndSort);
			Integer F = (Integer) this.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getSecurityControl.showCIA_FInOneAndZore",
							lineAndSort);
			Integer G = (Integer) this.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getSecurityControl.showCIA_GInOneAndZore",
							lineAndSort);
			Integer H = (Integer) this.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getSecurityControl.showCIA_HInOneAndZore",
							lineAndSort);
			Integer I = (Integer) this.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getSecurityControl.showCIA_IInOneAndZore",
							lineAndSort);
			Integer J = (Integer) this.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getSecurityControl.showCIA_JInOneAndZore",
							lineAndSort);

			int sum = A + B + C + D + E + F + G + H + I + J;

			list.add(sum);
		}
		return list;

	}

	public List<Integer> getGaplineAndSortFour(int pkca) {
		String a[][] = new String[10][7];
		LineAndSort lineAndSort = new LineAndSort();
		List<Integer> list = new ArrayList<Integer>();
		ReportDocument reportDocument = new ReportDocument();
		String[] strs = { "8.1%", "8.2%", "8.3%", "8.4%", "8.5%", "8.6%",
				"8.7%" };
		String[] line = { "CIA_A", "CIA_B", "CIA_C", "CIA_D", "CIA_E", "CIA_F",
				"CIA_G", "CIA_H", "CIA_I", "CIA_J" };
		for (int j = 0; j < 7; j++) {
			lineAndSort.setSort(strs[j]);

			lineAndSort.setPkca(pkca);
			Integer A = (Integer) this.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getSecurityControl.showCIA_AInOneAndZore",
							lineAndSort);

			Integer B = (Integer) this.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getSecurityControl.showCIA_BInOneAndZore",
							lineAndSort);

			Integer C = (Integer) this.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getSecurityControl.showCIA_CInOneAndZore",
							lineAndSort);
			Integer D = (Integer) this.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getSecurityControl.showCIA_DInOneAndZore",
							lineAndSort);
			Integer E = (Integer) this.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getSecurityControl.showCIA_EInOneAndZore",
							lineAndSort);
			Integer F = (Integer) this.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getSecurityControl.showCIA_FInOneAndZore",
							lineAndSort);
			Integer G = (Integer) this.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getSecurityControl.showCIA_GInOneAndZore",
							lineAndSort);
			Integer H = (Integer) this.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getSecurityControl.showCIA_HInOneAndZore",
							lineAndSort);
			Integer I = (Integer) this.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getSecurityControl.showCIA_IInOneAndZore",
							lineAndSort);
			Integer J = (Integer) this.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getSecurityControl.showCIA_JInOneAndZore",
							lineAndSort);

			int sum = A + B + C + D + E + F + G + H + I + J;

			list.add(sum);
		}
		return list;

	}

}
