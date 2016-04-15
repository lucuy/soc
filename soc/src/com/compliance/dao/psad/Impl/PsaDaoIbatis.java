package com.compliance.dao.psad.Impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

import com.compliance.dao.ibatis.BaseDaoiBatis;
import com.compliance.dao.psad.PsaDao;
import com.compliance.model.msad.MsaApp;
import com.compliance.model.psad.Psa;
import com.soc.model.conf.GlobalConfig;

public class PsaDaoIbatis extends BaseDaoiBatis implements PsaDao{

	public int insert(Psa p) {
		Integer ps = 0;
		Object obj = this.getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"psa.insert", p);
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
				GlobalConfig.sqlId+"psa.queryNum");
		if (compAssetsObj != null) {
			compAssetsNum = ((Integer) compAssetsObj).intValue();
		}
		return compAssetsNum;
	}
/**
 * 得到最新时间
 */
	public Date lastTime() {
		return   (Date) super.queryForObject(GlobalConfig.sqlId+"psa.queryLastTime");
	}
public void upData(Psa P) {
	 this.getSqlMapClientTemplate().update(GlobalConfig.sqlId+"psa.updata", P);
	
}
public Psa queryResultByTimeAndSort(Map<String, Object> map) {
	return  (Psa) this.queryForObject(GlobalConfig.sqlId+"psa.queryResultByTimeAndSort", map);
}

}
