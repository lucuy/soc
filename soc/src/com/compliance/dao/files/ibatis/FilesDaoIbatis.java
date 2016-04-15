package com.compliance.dao.files.ibatis;

import java.util.List;
import java.util.Map;

import com.compliance.dao.files.FilesDao;
import com.compliance.dao.ibatis.BaseDaoiBatis;
import com.compliance.model.basicinfo.assets.DocAssets;
import com.compliance.model.rank.Rank;
import com.compliance.model.rank.SysAbolish;
import com.soc.model.conf.GlobalConfig;

public class FilesDaoIbatis extends BaseDaoiBatis implements FilesDao {

	public int count(Map map,String flag) {
		// TODO Auto-generated method stub
		Object ob = null;
		try {
			if(flag.equals("safety")){
				ob = super.queryForObject(GlobalConfig.sqlId+"safety.count", map);
			}else if(flag.equals("rank")){
				ob = super.queryForObject(GlobalConfig.sqlId+"rank.count", map);
			}else if(flag.equals("abolish")){
				ob = super.queryForObject(GlobalConfig.sqlId+"sysabolish.count", map);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		int totalRows = 0;
		if (ob != null) {
			totalRows = ((Integer) ob).intValue();
		}

		return totalRows;
	}
	public List<Rank> queryRank(Map map, int startRow, int pageSize) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"rankfiles.query",map,startRow,pageSize);
	}

	public List<SysAbolish> queryabolish(Map map, int startRow, int pageSize) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"abolishfiles.query",map,startRow,pageSize);
	}
	

}
