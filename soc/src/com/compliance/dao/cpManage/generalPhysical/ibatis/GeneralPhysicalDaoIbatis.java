package com.compliance.dao.cpManage.generalPhysical.ibatis;

import java.util.ArrayList;
import java.util.List;

import com.compliance.dao.cpManage.generalPhysical.GeneralPhysicalDao;
import com.compliance.dao.ibatis.BaseDaoiBatis;
//差距分析报告导出word文档
//表：信息系统总体符合度
import com.soc.model.conf.GlobalConfig;

public class GeneralPhysicalDaoIbatis extends BaseDaoiBatis implements
		GeneralPhysicalDao {

	public int getGeneralPhysicalCount() {
		int sum = 0;
		String[] strs = { "10.1%", "10.2%", "10.3%", "10.4%", "10.5%", "10.6%" };
		for (int j = 0; j < 6; j++) {
			String sort = strs[j];
			String num = (String) this.getSqlMapClientTemplate()
					.queryForObject(GlobalConfig.sqlId+"getGeneralPhysical.Count", sort);
			if (num != null) {
				sum += 1;
			}
		}
		return sum;
	}
//	public int getGeneralPhysicalCount() {
//		int sum = 0;
//		String[] strs = { "10.1%", "10.2%", "10.3%", "10.4%", "10.5%", "10.6%" };
//		for (int j = 0; j < 6; j++) {
//			String sort = strs[j];
//			int num = (Integer) this.getSqlMapClientTemplate().queryForObject("getGeneralPhysical.Count", sort);
//				sum += num;
//		}
//		return sum;
//	}
	public int getManagementCount() {
		int sum = 0;
		String[] strs = { "11.1%", "11.2%", "11.3%", "11.4%", "11.5%" };
		for (int j = 0; j < 5; j++) {
			String sort2 = strs[j];
			int num = (Integer) this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"getManagementCount.Count", sort2);
				sum += num;
		}
		return sum;
	}

	public int getTechnologyCount(int pca) {
		int num = (Integer) this.getSqlMapClientTemplate().queryForObject(
				GlobalConfig.sqlId+"getTechnologyCount.Count", pca);

		return num;
	}

	public int getTongYongWuLi() {
		int sum = 0;
		String[] strs = { "1", "0" };
		for (int m = 0; m < 2; m++) {
			String sort = strs[m];
			int a = (Integer) this.getSqlMapClientTemplate().queryForObject(
					GlobalConfig.sqlId+"getTongYongWuLi.CountA", sort);

			int b = (Integer) this.getSqlMapClientTemplate().queryForObject(
					GlobalConfig.sqlId+"getTongYongWuLi.CountB", sort);

			int c = (Integer) this.getSqlMapClientTemplate().queryForObject(
					GlobalConfig.sqlId+"getTongYongWuLi.CountC", sort);

			int d = (Integer) this.getSqlMapClientTemplate().queryForObject(
					GlobalConfig.sqlId+"getTongYongWuLi.CountD", sort);

			int e = (Integer) this.getSqlMapClientTemplate().queryForObject(
					GlobalConfig.sqlId+"getTongYongWuLi.CountE", sort);

			int f = (Integer) this.getSqlMapClientTemplate().queryForObject(
					GlobalConfig.sqlId+"getTongYongWuLi.CountF", sort);

			int g = (Integer) this.getSqlMapClientTemplate().queryForObject(
					GlobalConfig.sqlId+"getTongYongWuLi.CountG", sort);
			int h = (Integer) this.getSqlMapClientTemplate().queryForObject(
					GlobalConfig.sqlId+"getTongYongWuLi.CountH", sort);
			int i = (Integer) this.getSqlMapClientTemplate().queryForObject(
					GlobalConfig.sqlId+"getTongYongWuLi.CountI", sort);

			int j = (Integer) this.getSqlMapClientTemplate().queryForObject(
					GlobalConfig.sqlId+"getTongYongWuLi.CountJ", sort);

			sum = a + b + c + d + e + f + g + h + i + j + sum;
		}
		return sum;
	}

	public int getTongYongGuanLi() {
		int sum = 0;
		String[] strs = { "1", "0" };
		for (int m = 0; m < 2; m++) {
			String sort = strs[m];
			int a = (Integer) this.getSqlMapClientTemplate().queryForObject(
					GlobalConfig.sqlId+"getTongYongGuanLi.CountA", sort);

			int b = (Integer) this.getSqlMapClientTemplate().queryForObject(
					GlobalConfig.sqlId+"getTongYongGuanLi.CountB", sort);

			int c = (Integer) this.getSqlMapClientTemplate().queryForObject(
					GlobalConfig.sqlId+"getTongYongGuanLi.CountC", sort);

			int d = (Integer) this.getSqlMapClientTemplate().queryForObject(
					GlobalConfig.sqlId+"getTongYongGuanLi.CountD", sort);

			int e = (Integer) this.getSqlMapClientTemplate().queryForObject(
					GlobalConfig.sqlId+"getTongYongGuanLi.CountE", sort);

			int f = (Integer) this.getSqlMapClientTemplate().queryForObject(
					GlobalConfig.sqlId+"getTongYongGuanLi.CountF", sort);

			int g = (Integer) this.getSqlMapClientTemplate().queryForObject(
					GlobalConfig.sqlId+"getTongYongGuanLi.CountG", sort);
			int h = (Integer) this.getSqlMapClientTemplate().queryForObject(
					GlobalConfig.sqlId+"getTongYongGuanLi.CountH", sort);
			int i = (Integer) this.getSqlMapClientTemplate().queryForObject(
					GlobalConfig.sqlId+"getTongYongGuanLi.CountI", sort);

			int j = (Integer) this.getSqlMapClientTemplate().queryForObject(
					GlobalConfig.sqlId+"getTongYongGuanLi.CountJ", sort);

			sum = a + b + c + d + e + f + g + h + i + j + sum;
		}
		return sum;
	}
	
	
	
	//3.1 通用物理 差距项统计表

	public int getTyWuliFuHeNum() {
		int i = (Integer) this.getSqlMapClientTemplate().queryForObject(
				GlobalConfig.sqlId+"getGeneralPhysical.Count0");
		return i;
	}
	public int getTyWuliBufenFuHeNum() {
		int i = (Integer) this.getSqlMapClientTemplate().queryForObject(
				GlobalConfig.sqlId+"getGeneralPhysical.Count1");
		return i;
	}
	public int getTyWuliBuFuHeNum() {
		int i = (Integer) this.getSqlMapClientTemplate().queryForObject(
				GlobalConfig.sqlId+"getGeneralPhysical.Count2");
		return i;
	}
	
	public int getTyWuliChaJuNum() {
		int i = (Integer) this.getSqlMapClientTemplate().queryForObject(
				GlobalConfig.sqlId+"getGeneralPhysical.Count1and0");
		return i;
	}
	
	///3.1 通用管理 差距项统计表 
	public int getTyGuanLiFuHeNum(){
		int i = (Integer) this.getSqlMapClientTemplate().queryForObject(
				GlobalConfig.sqlId+"getManagementCount.Count0");
		return i;
		
	}
	
	public int getTyGuanLiFuFenHeNum(){
		int i = (Integer) this.getSqlMapClientTemplate().queryForObject(
				GlobalConfig.sqlId+"getManagementCount.Count1");
		return i;
		
	}
	
	public int getTyGuanLiBuFuHeNum(){
		int i = (Integer) this.getSqlMapClientTemplate().queryForObject(
				GlobalConfig.sqlId+"getManagementCount.Count2");
		return i;
		
	}
	
	public int getTyGuanLiChaJuNum(){
		int i = (Integer) this.getSqlMapClientTemplate().queryForObject(
				GlobalConfig.sqlId+"getManagementCount.Count0and1");
		return i;
		
	}
	

}
