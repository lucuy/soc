package com.compliance.dao.cpManage.summaryAnalysis.ibatis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.compliance.dao.cpManage.summaryAnalysis.SecurityGapDao;
import com.compliance.dao.ibatis.BaseDaoiBatis;
import com.compliance.model.cpManage.summaryAnalysis.ProjectShowcase;
import com.compliance.model.cpManage.summaryAnalysis.SecurityGap;
import com.soc.model.conf.GlobalConfig;

import java.util.List;

public class SecurityGapDaoIbatis extends BaseDaoiBatis implements
		SecurityGapDao {

	public List<Integer> getJiZhuWangLou2(int pkca) {
		int num;
		int a[][] = new int[4][7];
		List<Integer> list = new ArrayList<Integer>();
		SecurityGap securityGap = new SecurityGap();
		String[] strs = { "6.1%", "6.2%", "6.3%", "6.4%", "6.5%", "6.6%", "6.7%"};
		for (int j = 0; j < 7; j++) {

			securityGap.setStr(strs[j]);

			for (int i = 0; i < 4; i++) {
				securityGap.setNum(i+"");
				securityGap.setPkca(pkca);
				a[i][j] = (Integer) this.getSqlMapClientTemplate()
						.queryForObject(GlobalConfig.sqlId+"getSecurityGapList.count",
								securityGap);
				list.add(a[i][j]);
			}
		}
		return list;

	}
	
	
	public List<Integer> getJiZhuWangLou3(int pkca) {
		int num;
		int a[][] = new int[4][20];
		List<Integer> list = new ArrayList<Integer>();
		SecurityGap securityGap = new SecurityGap();
		String[] strs = {"7.1%","7.2%","7.3%","7.4%","7.5%","7.6%","7.7%"};

		for (int j = 0; j < 7; j++) {

			securityGap.setStr(strs[j]);

			for (int i = 0; i < 4; i++) {
				securityGap.setNum(i+"");
				securityGap.setPkca(pkca);
				a[i][j] = (Integer) this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"getSecurityGapList.count",securityGap);
				list.add(a[i][j]);
			}
		}
		return list;

	}
	
	
	public List<Integer> getJiZhuWangLou4(int pkca) {
		int num;
		int a[][] = new int[4][20];
		List<Integer> list = new ArrayList<Integer>();
		SecurityGap securityGap = new SecurityGap();
		String[] strs = {"8.1%","8.2%","8.3%","8.4%","8.5%","8.6%","8.7%"};

		for (int j = 0; j < 7; j++) {

			securityGap.setStr(strs[j]);

			for (int i = 0; i < 4; i++) {
				securityGap.setNum(i+"");
				securityGap.setPkca(pkca);
				a[i][j] = (Integer) this.getSqlMapClientTemplate()
						.queryForObject(GlobalConfig.sqlId+"getSecurityGapList.count",
								securityGap);
				list.add(a[i][j]);
			}
		}
		return list;

	}

	public List<ProjectShowcase> getProjectShowcase() {
		ProjectShowcase projectShowcase = new ProjectShowcase();
		List<ProjectShowcase> list = new ArrayList<ProjectShowcase>();
		list = this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"getProjectShowcase.show");
		
		return list;
	}
	
	public List<ProjectShowcase> getProjectShowcaseByName(int pkca) {
		ProjectShowcase projectShowcase = new ProjectShowcase();
		List<ProjectShowcase> list = new ArrayList<ProjectShowcase>();
		list = this.getSqlMapClientTemplate().queryForList(
				GlobalConfig.sqlId+"getProjectShowcase.showByName22",pkca);
		
		return list;
	}
}
