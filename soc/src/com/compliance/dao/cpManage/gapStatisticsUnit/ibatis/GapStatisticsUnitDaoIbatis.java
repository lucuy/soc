package com.compliance.dao.cpManage.gapStatisticsUnit.ibatis;

import java.util.ArrayList;
import java.util.List;

import com.compliance.dao.cpManage.gapStatisticsUnit.GapStatisticsUnitDao;
import com.compliance.dao.ibatis.BaseDaoiBatis;
import com.compliance.model.cpManage.summaryAnalysis.SecurityGap;
import com.soc.model.conf.GlobalConfig;

public class GapStatisticsUnitDaoIbatis extends BaseDaoiBatis  implements GapStatisticsUnitDao {
	
	
	///  6.1%的不同符合情况
	public List<Integer> getGapStatisticsUnitTwo0(int fkca) {
		String[] strs = { "6.1%", "6.2%", "6.3%", "6.4%", "6.5%", "6.6%",
				"6.7%" };
		List<Integer> list = new ArrayList<Integer>();
		SecurityGap securityGap = new SecurityGap();
		for (int i = 0; i < 7; i++) {
			int num = 0;
			securityGap.setPkca(fkca);
			securityGap.setStr(strs[i]);
			num = (Integer) this.getSqlMapClientTemplate().queryForObject(
					GlobalConfig.sqlId+"getGapStatisticsUnitConform.count", securityGap);
			list.add(num);
		}
		return list;
	}
	
	public List<Integer> getGapStatisticsUnitTwo1(int fkca) {
		String[] strs = { "6.1%", "6.2%", "6.3%", "6.4%", "6.5%", "6.6%",
				"6.7%" };
		List<Integer> list = new ArrayList<Integer>();
		SecurityGap securityGap = new SecurityGap();
		for (int i = 0; i < 7; i++) {
			int num = 0;
			securityGap.setPkca(fkca);
			securityGap.setStr(strs[i]);
			num = (Integer) this.getSqlMapClientTemplate().queryForObject(
					GlobalConfig.sqlId+"getGapStatisticsUnitPartConform.count", securityGap);
			list.add(num);
		}
		return list;
	}
	
	public List<Integer> getGapStatisticsUnitTwo2(int fkca) {
		String[] strs = { "6.1%", "6.2%", "6.3%", "6.4%", "6.5%", "6.6%",
				"6.7%" };
		List<Integer> list = new ArrayList<Integer>();
		SecurityGap securityGap = new SecurityGap();
		for (int i = 0; i < 7; i++) {
			int num = 0;
			securityGap.setPkca(fkca);
			securityGap.setStr(strs[i]);
			num = (Integer) this.getSqlMapClientTemplate().queryForObject(
					GlobalConfig.sqlId+"getGapStatisticsUnitNotConform.count", securityGap);
			list.add(num);
		}
		return list;
	}
	
	public List<Integer> getGapStatisticsUnitTwo1And2(int fkca) {
		String[] strs = { "6.1%", "6.2%", "6.3%", "6.4%", "6.5%", "6.6%",
				"6.7%" };
		List<Integer> list = new ArrayList<Integer>();
		SecurityGap securityGap = new SecurityGap();
		for (int i = 0; i < 7; i++) {
			int num = 0;
			securityGap.setPkca(fkca);
			securityGap.setStr(strs[i]);
			num = (Integer) this.getSqlMapClientTemplate().queryForObject(
					GlobalConfig.sqlId+"getGapStatisticsUnitPartAndNotConform.count", securityGap);
			list.add(num);
		}
		return list;
	}
	
	////////
	
	///  7.1%的不同符合情况
		public List<Integer> getGapStatisticsUnitThree0(int fkca) {
			String[] strs = { "7.1%", "7.2%", "7.3%", "7.4%", "7.5%", "7.6%",
					"7.7%" };
			List<Integer> list = new ArrayList<Integer>();
			SecurityGap securityGap = new SecurityGap();
			for (int i = 0; i < 7; i++) {
				int num = 0;
				securityGap.setPkca(fkca);
				securityGap.setStr(strs[i]);
				num = (Integer) this.getSqlMapClientTemplate().queryForObject(
						GlobalConfig.sqlId+"getGapStatisticsUnitConform.count", securityGap);
				list.add(num);
			}
			return list;
		}
		
		public List<Integer> getGapStatisticsUnitThree1(int fkca) {
			String[] strs = { "7.1%", "7.2%", "7.3%", "7.4%", "7.5%", "7.6%",
					"7.7%" };
			List<Integer> list = new ArrayList<Integer>();
			SecurityGap securityGap = new SecurityGap();
			for (int i = 0; i < 7; i++) {
				int num = 0;
				securityGap.setPkca(fkca);
				securityGap.setStr(strs[i]);
				num = (Integer) this.getSqlMapClientTemplate().queryForObject(
						GlobalConfig.sqlId+"getGapStatisticsUnitPartConform.count", securityGap);
				list.add(num);
			}
			return list;
		}
		
		public List<Integer> getGapStatisticsUnitThree2(int fkca) {
			String[] strs = { "7.1%", "7.2%", "7.3%", "7.4%", "7.5%", "7.6%",
					"7.7%" };
			List<Integer> list = new ArrayList<Integer>();
			SecurityGap securityGap = new SecurityGap();
			for (int i = 0; i < 7; i++) {
				int num = 0;
				securityGap.setPkca(fkca);
				securityGap.setStr(strs[i]);
				num = (Integer) this.getSqlMapClientTemplate().queryForObject(
						GlobalConfig.sqlId+"getGapStatisticsUnitNotConform.count", securityGap);
				list.add(num);
			}
			return list;
		}
		
		public List<Integer> getGapStatisticsUnitThree1And2(int fkca) {
			String[] strs = { "7.1%", "7.2%", "7.3%", "7.4%", "7.5%", "7.6%",
					"7.7%" };
			List<Integer> list = new ArrayList<Integer>();
			SecurityGap securityGap = new SecurityGap();
			for (int i = 0; i < 7; i++) {
				int num = 0;
				securityGap.setPkca(fkca);
				securityGap.setStr(strs[i]);
				num = (Integer) this.getSqlMapClientTemplate().queryForObject(
						GlobalConfig.sqlId+"getGapStatisticsUnitPartAndNotConform.count", securityGap);
				list.add(num);
			}
			return list;
		}
		
		////////
	
		///  8.1%的不同符合情况
				public List<Integer> getGapStatisticsUnitFour0(int fkca) {
					String[] strs = { "8.1%", "8.2%", "8.3%", "8.4%", "8.5%", "8.6%",
							"8.7%" };
					List<Integer> list = new ArrayList<Integer>();
					SecurityGap securityGap = new SecurityGap();
					for (int i = 0; i < 7; i++) {
						int num = 0;
						securityGap.setPkca(fkca);
						securityGap.setStr(strs[i]);
						num = (Integer) this.getSqlMapClientTemplate().queryForObject(
								GlobalConfig.sqlId+"getGapStatisticsUnitConform.count", securityGap);
						list.add(num);
					}
					return list;
				}
				
				public List<Integer> getGapStatisticsUnitFour1(int fkca) {
					String[] strs = { "8.1%", "8.2%", "8.3%", "8.4%", "8.5%", "8.6%",
							"8.7%" };
					List<Integer> list = new ArrayList<Integer>();
					SecurityGap securityGap = new SecurityGap();
					for (int i = 0; i < 7; i++) {
						int num = 0;
						securityGap.setPkca(fkca);
						securityGap.setStr(strs[i]);
						num = (Integer) this.getSqlMapClientTemplate().queryForObject(
								GlobalConfig.sqlId+"getGapStatisticsUnitPartConform.count", securityGap);
						list.add(num);
					}
					return list;
				}
				
				public List<Integer> getGapStatisticsUnitFour2(int fkca) {
					String[] strs = { "8.1%", "8.2%", "8.3%", "8.4%", "8.5%", "8.6%",
							"8.7%" };
					List<Integer> list = new ArrayList<Integer>();
					SecurityGap securityGap = new SecurityGap();
					for (int i = 0; i < 7; i++) {
						int num = 0;
						securityGap.setPkca(fkca);
						securityGap.setStr(strs[i]);
						num = (Integer) this.getSqlMapClientTemplate().queryForObject(
								GlobalConfig.sqlId+"getGapStatisticsUnitNotConform.count", securityGap);
						list.add(num);
					}
					return list;
				}
				
				public List<Integer> getGapStatisticsUnitFour1And2(int fkca) {
					String[] strs = { "8.1%", "8.2%", "8.3%", "8.4%", "8.5%", "8.6%",
							"8.7%" };
					List<Integer> list = new ArrayList<Integer>();
					SecurityGap securityGap = new SecurityGap();
					for (int i = 0; i < 7; i++) {
						int num = 0;
						securityGap.setPkca(fkca);
						securityGap.setStr(strs[i]);
						num = (Integer) this.getSqlMapClientTemplate().queryForObject(
								GlobalConfig.sqlId+"getGapStatisticsUnitPartAndNotConform.count", securityGap);
						list.add(num);
					}
					return list;
				}
				
				////////
	
	
	

}
