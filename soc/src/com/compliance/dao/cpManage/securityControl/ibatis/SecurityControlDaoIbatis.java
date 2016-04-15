package com.compliance.dao.cpManage.securityControl.ibatis;

import java.util.ArrayList;
import java.util.List;

import com.compliance.dao.cpManage.securityControl.SecurityControlDao;
import com.compliance.dao.ibatis.BaseDaoiBatis;
import com.compliance.model.cpManage.securityControl.ControlTask;
import com.compliance.model.cpManage.securityControl.LineAndSort;
import com.soc.model.conf.GlobalConfig;

public class SecurityControlDaoIbatis extends BaseDaoiBatis implements
		SecurityControlDao {
	public List<Integer> getSecurityControl2(int pkca) {
		String a[][] = new String[10][7];

		List<Integer> list = new ArrayList<Integer>();
		LineAndSort lineAndSort = new LineAndSort();
		String[] strs = { "6.1%", "6.2%", "6.3%", "6.4%", "6.5%", "6.6%",
				"6.7%" };
		String[] line = { "CIA_A", "CIA_B", "CIA_C", "CIA_D", "CIA_E", "CIA_F",
				"CIA_G", "CIA_H", "CIA_I", "CIA_J" };
		for (int j = 0; j < 7; j++) {
			lineAndSort.setSort(strs[j]);

			lineAndSort.setPkca(pkca);
			Integer A = (Integer) this
					.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getSecurityControl.showCIA_A", lineAndSort);

			Integer B = (Integer) this
					.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getSecurityControl.showCIA_B", lineAndSort);

			Integer C = (Integer) this
					.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getSecurityControl.showCIA_C", lineAndSort);
			Integer D = (Integer) this
					.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getSecurityControl.showCIA_D", lineAndSort);
			Integer E = (Integer) this
					.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getSecurityControl.showCIA_E", lineAndSort);
			Integer F = (Integer) this
					.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getSecurityControl.showCIA_F", lineAndSort);
			Integer G = (Integer) this
					.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getSecurityControl.showCIA_G", lineAndSort);
			Integer H = (Integer) this
					.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getSecurityControl.showCIA_H", lineAndSort);
			Integer I = (Integer) this
					.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getSecurityControl.showCIA_I", lineAndSort);
			Integer J = (Integer) this
					.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getSecurityControl.showCIA_J", lineAndSort);

			int sum = A + B + C + D + E + F + G + H + I + J;

			list.add(sum);
		}
		return list;

	}

	public List<Integer> getSecurityControl3(int pkca) {
		String a[][] = new String[10][7];

		List<Integer> list = new ArrayList<Integer>();
		LineAndSort lineAndSort = new LineAndSort();
		String[] strs = { "7.1%", "7.2%", "7.3%", "7.4%", "7.5%", "7.6%",
				"7.7%" };
		String[] line = { "CIA_A", "CIA_B", "CIA_C", "CIA_D", "CIA_E", "CIA_F",
				"CIA_G", "CIA_H", "CIA_I", "CIA_J" };
		for (int j = 0; j < 7; j++) {
			lineAndSort.setSort(strs[j]);

			lineAndSort.setPkca(pkca);
			Integer A = (Integer) this
					.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getSecurityControl.showCIA_A", lineAndSort);

			Integer B = (Integer) this
					.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getSecurityControl.showCIA_B", lineAndSort);

			Integer C = (Integer) this
					.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getSecurityControl.showCIA_C", lineAndSort);
			Integer D = (Integer) this
					.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getSecurityControl.showCIA_D", lineAndSort);
			Integer E = (Integer) this
					.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getSecurityControl.showCIA_E", lineAndSort);
			Integer F = (Integer) this
					.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getSecurityControl.showCIA_F", lineAndSort);
			Integer G = (Integer) this
					.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getSecurityControl.showCIA_G", lineAndSort);
			Integer H = (Integer) this
					.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getSecurityControl.showCIA_H", lineAndSort);
			Integer I = (Integer) this
					.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getSecurityControl.showCIA_I", lineAndSort);
			Integer J = (Integer) this
					.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getSecurityControl.showCIA_J", lineAndSort);

			int sum = A + B + C + D + E + F + G + H + I + J;

			list.add(sum);
		}
		return list;

	}

	public List<Integer> getSecurityControl4(int pkca) {
		String a[][] = new String[10][7];

		List<Integer> list = new ArrayList<Integer>();
		LineAndSort lineAndSort = new LineAndSort();
		String[] strs = { "8.1%", "8.2%", "8.3%", "8.4%", "8.5%", "8.6%",
				"8.7%" };
		String[] line = { "CIA_A", "CIA_B", "CIA_C", "CIA_D", "CIA_E", "CIA_F",
				"CIA_G", "CIA_H", "CIA_I", "CIA_J" };
		for (int j = 0; j < 7; j++) {
			lineAndSort.setSort(strs[j]);

			lineAndSort.setPkca(pkca);
			Integer A = (Integer) this
					.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getSecurityControl.showCIA_A", lineAndSort);

			Integer B = (Integer) this
					.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getSecurityControl.showCIA_B", lineAndSort);

			Integer C = (Integer) this
					.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getSecurityControl.showCIA_C", lineAndSort);
			Integer D = (Integer) this
					.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getSecurityControl.showCIA_D", lineAndSort);
			Integer E = (Integer) this
					.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getSecurityControl.showCIA_E", lineAndSort);
			Integer F = (Integer) this
					.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getSecurityControl.showCIA_F", lineAndSort);
			Integer G = (Integer) this
					.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getSecurityControl.showCIA_G", lineAndSort);
			Integer H = (Integer) this
					.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getSecurityControl.showCIA_H", lineAndSort);
			Integer I = (Integer) this
					.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getSecurityControl.showCIA_I", lineAndSort);
			Integer J = (Integer) this
					.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getSecurityControl.showCIA_J", lineAndSort);

			int sum = A + B + C + D + E + F + G + H + I + J;

			list.add(sum);
		}
		return list;

	}

	public List<Integer> getControlTask4(String sysId) {

		List<Integer> list = new ArrayList<Integer>();
		ControlTask controlTask = new ControlTask();
		String[] strs = { "8.1%", "8.2%", "8.3%", "8.4%", "8.5%", "8.6%",
				"8.7%" };
		for (int j = 0; j < 7; j++) {
			controlTask.setSysId(sysId);
			controlTask.setControlDomainName(strs[j]);
			Integer count = (Integer) this.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getControlTask.count", controlTask);
			list.add(count);
		}
		return list;

	}
	
	public List<Integer> getControlTask3(String sysId) {

		List<Integer> list = new ArrayList<Integer>();
		ControlTask controlTask = new ControlTask();
		String[] strs = { "7.1%", "7.2%", "7.3%", "7.4%", "7.5%", "7.6%",
				"7.7%" };
		for (int j = 0; j < 7; j++) {
			controlTask.setSysId(sysId);
			controlTask.setControlDomainName(strs[j]);
			Integer count = (Integer) this.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getControlTask.count", controlTask);
			list.add(count);
		}
		return list;

	}
	public List<Integer> getControlTask2(String sysId) {

		List<Integer> list = new ArrayList<Integer>();
		ControlTask controlTask = new ControlTask();
		String[] strs = { "6.1%", "6.2%", "6.3%", "6.4%", "6.5%", "6.6%",
				"6.7%" };
		for (int j = 0; j < 7; j++) {
			controlTask.setSysId(sysId);
			controlTask.setControlDomainName(strs[j]);
			Integer count = (Integer) this.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getControlTask.count", controlTask);
			list.add(count);
		}
		return list;

	}

}
