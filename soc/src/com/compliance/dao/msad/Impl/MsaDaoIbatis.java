package com.compliance.dao.msad.Impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

import com.compliance.dao.ibatis.BaseDaoiBatis;
import com.compliance.dao.msad.MsaDao;
import com.compliance.model.msad.MsaApp;
import com.compliance.model.msad.Msad;
import com.soc.model.conf.GlobalConfig;

public class MsaDaoIbatis extends BaseDaoiBatis implements MsaDao{

	public int insert(MsaApp m) {
		Integer ps = 0;
		Object obj = this.getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"msa.insert", m);
		
		if (obj != null) {
			ps = Integer.parseInt(obj.toString());
		}
		return ps;
	}
	
	/**
	 * 得到最新的数据条数
	 * @throws SQLException 
	 */
		public int lastNum()  {
			// TODO Auto-generated method stub
			int compAssetsNum = 0;
			Object compAssetsObj = null;
			compAssetsObj = this.getSqlMapClientTemplate().queryForObject(
					GlobalConfig.sqlId+"msa.queryNum");
			if (compAssetsObj != null) {
				compAssetsNum = ((Integer) compAssetsObj).intValue();
			}
			return compAssetsNum;
		}
	/**
	 * 得到最新时间
	 */
		public Date lastTime() {
			return   (Date) super.queryForObject(GlobalConfig.sqlId+"msa.queryLastTime");
		}

	public MsaApp queryResultByTimeAndSort(Map<String, String> map) {
		 
		return  (MsaApp) this.queryForObject(GlobalConfig.sqlId+"msa.queryResultByTimeAndSort", map);
	}

	public void upData(MsaApp m) {
		 this.getSqlMapClientTemplate().update(GlobalConfig.sqlId+"msa.updata", m);
	}
}
