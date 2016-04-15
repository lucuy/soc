package com.compliance.dao.cpManage.securityTable.ibatis;

import java.util.ArrayList;
import java.util.List;

import com.compliance.dao.cpManage.securityTable.SecurityTableDao;
import com.compliance.dao.ibatis.BaseDaoiBatis;
import com.compliance.model.cpManage.summaryAnalysis.SecurityGap;
//合规管理
////差距分析
//差距分析报告
//表：安全领域符合度
import com.soc.model.conf.GlobalConfig;

public class SecurityTableDaoIbatis extends BaseDaoiBatis implements
		SecurityTableDao {
	public List<Integer> getSecurityTableTwo(int fkca) {
		String[] strs = { "6.1%", "6.2%", "6.3%", "6.4%", "6.5%", "6.6%",
				"6.7%" };

		List<Integer> list = new ArrayList<Integer>();
		SecurityGap securityGap = new SecurityGap();
		for (int i = 0; i < 7; i++) {
			int num = 0;
			securityGap.setPkca(fkca);
			securityGap.setStr(strs[i]);
			num = (Integer) this.getSqlMapClientTemplate().queryForObject(
					GlobalConfig.sqlId+"getSecurityTable.count", securityGap);
			list.add(num);
		}
		return list;
	}

	public List<Integer> getSecurityTableThree(int fkca) {
		String[] strs = { "7.1%", "7.2%", "7.3%", "7.4%", "7.5%", "7.6%",
				"7.7%" };

		List<Integer> list = new ArrayList<Integer>();
		SecurityGap securityGap = new SecurityGap();
		for (int i = 0; i < 7; i++) {
			int num = 0;
			securityGap.setPkca(fkca);
			securityGap.setStr(strs[i]);
			num = (Integer) this.getSqlMapClientTemplate().queryForObject(
					GlobalConfig.sqlId+"getSecurityTable.count", securityGap);
			list.add(num);
		}
		return list;
	}

	public List<Integer> getSecurityTableFour(int fkca) {
		String[] strs = { "8.1%", "8.2%", "8.3%", "8.4%", "8.5%", "8.6%",
				"8.7%" };

		List<Integer> list = new ArrayList<Integer>();
		SecurityGap securityGap = new SecurityGap();
		for (int i = 0; i < 7; i++) {
			int num = 0;
			securityGap.setPkca(fkca);
			securityGap.setStr(strs[i]);
			num = (Integer) this.getSqlMapClientTemplate().queryForObject(
					GlobalConfig.sqlId+"getSecurityTable.count", securityGap);
			list.add(num);
		}
		return list;
	}
}
