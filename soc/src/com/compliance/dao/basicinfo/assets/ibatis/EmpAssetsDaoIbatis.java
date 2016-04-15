package com.compliance.dao.basicinfo.assets.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.compliance.dao.basicinfo.assets.EmpAssetsDao;
import com.compliance.dao.ibatis.BaseDaoiBatis;
import com.compliance.model.basicinfo.assets.EmpAssets;
import com.soc.model.conf.GlobalConfig;

public class EmpAssetsDaoIbatis extends BaseDaoiBatis implements EmpAssetsDao {

	public int empAssetsCount(Map mapper) {
		int empAssetsNum = 0;
		Object empAssetsObj = null;
		empAssetsObj = this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"empAssets.count", mapper);
		if (empAssetsObj != null) {
			empAssetsNum = ((Integer) empAssetsObj).intValue();
		}

		return empAssetsNum;
	}

	public List<EmpAssets> query(Map map, int startRow, int pageSize) {
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"empAssets.query", map, startRow, pageSize);

	}

	public void empAssetsInsert(EmpAssets empAssets) {
		int id = (Integer)this.getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"empAssets.insert", empAssets);
		//批量插入业务应用软件所属系统名称
		Map<String, Object> paraMap = new HashMap<String, Object>();
		for (String sysName : empAssets.getRelsysName()) {
			paraMap.put("relsysName", sysName);
			paraMap.put("id", id);
			this.getSqlMapClientTemplate().insert(
					GlobalConfig.sqlId + "empAssets.addSysName", paraMap);
			paraMap.clear();
		}
	}

	public void empAssetsUpdate(EmpAssets empAssets) {
		this.getSqlMapClientTemplate().update(GlobalConfig.sqlId+"empAssets.update", empAssets);
		//删除原来的关联重新进行关联
		this.getSqlMapClientTemplate().update(GlobalConfig.sqlId+"empAssets.delSysName", empAssets.getId());
		//批量插入业务应用软件所属系统名称
		Map<String, Object> paraMap = new HashMap<String, Object>();
		for (String sysName : empAssets.getRelsysName()) {
			paraMap.put("relsysName", sysName);
			paraMap.put("id", empAssets.getId());
			this.getSqlMapClientTemplate().insert(
					GlobalConfig.sqlId + "empAssets.addSysName", paraMap);
			paraMap.clear();
		}
	}

	public void empAssetsDelete(int id) {
		super.delete(GlobalConfig.sqlId+"empAssets.delete", id);

	}

	public EmpAssets empAssetsQueryById(int id) {
		return (EmpAssets) this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"empAssets.queryById", id);
	}

	public int empAssetsPreciseCount(Map mapper) {
		int empAssetsNum = 0;
		Object empAssetsObj = null;
		empAssetsObj = this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"empAssets.countprecise", mapper);
		if (empAssetsObj != null) {
			empAssetsNum = ((Integer) empAssetsObj).intValue();
		}

		return empAssetsNum;
	}

	public List<EmpAssets> queryPrecise(Map map, int startRow, int pageSize) {
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"empAssets.queryprecise", map, startRow, pageSize);
	}

	/**
	 * 评估导出报表。查询所有
	 * 
	 * @param
	 * @return List<EmpAssets>
	 * @author dugaoyang createDate 2013-6-1
	 * 
	 */
	public List<EmpAssets> queryAllEmpAssets() {
		return (List<EmpAssets>) this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"empAssets.queryAll");
	}

}
