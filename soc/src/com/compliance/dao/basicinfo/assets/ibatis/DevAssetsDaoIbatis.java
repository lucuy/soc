package com.compliance.dao.basicinfo.assets.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.compliance.dao.basicinfo.assets.DevAssetsDao;
import com.compliance.dao.ibatis.BaseDaoiBatis;
import com.compliance.model.basicinfo.assets.DevAssets;
import com.soc.model.conf.GlobalConfig;

public class DevAssetsDaoIbatis extends BaseDaoiBatis implements DevAssetsDao {

	public int devAssetsPreciseCount(Map mapper) {
		// TODO Auto-generated method stub
		int devAssetsNum = 0;
		Object devAssetsObj = null;
		devAssetsObj = this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"devAssets.countprecise", mapper);
		if (devAssetsObj != null) {
			devAssetsNum = ((Integer) devAssetsObj).intValue();
		}

		return devAssetsNum;
	}

	public List<DevAssets> queryPrecise(Map map, int startRow, int pageSize) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"devAssets.pricisequery", map, startRow, pageSize);
	}

	public int devAssetsCount(Map mapper) {
		// TODO Auto-generated method stub
		int devAssetsNum = 0;
		Object devAssetsObj = null;
		devAssetsObj = this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"devAssets.count", mapper);
		if (devAssetsObj != null) {
			devAssetsNum = ((Integer) devAssetsObj).intValue();
		}

		return devAssetsNum;
	}

	public List<DevAssets> query(Map map, int startRow, int pageSize) {
		// TODO Auto-generated method stub

		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"devAssets.query", map, startRow, pageSize);

	}

	public void devAssetsInsert(DevAssets devAssets) {
		// TODO Auto-generated method stub

		int id = (Integer) this.getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"devAssets.insert", devAssets);
		//批量插入业务应用软件所属系统名称
		Map<String, Object> paraMap = new HashMap<String, Object>();
		for (String sysName : devAssets.getRelsysName()) {
			paraMap.put("relsysName", sysName);
			paraMap.put("id", id);
			this.getSqlMapClientTemplate().insert(
					GlobalConfig.sqlId + "devAssets.addSysName", paraMap);
			paraMap.clear();
		}

	}

	public void devAssetsUpdate(DevAssets devAssets) {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().update(GlobalConfig.sqlId+"devAssets.update", devAssets);
		//删除原来的关联重新进行关联
		this.getSqlMapClientTemplate().update(GlobalConfig.sqlId+"devAssets.delSysName", devAssets.getId());
		//批量插入业务应用软件所属系统名称
		Map<String, Object> paraMap = new HashMap<String, Object>();
		for (String sysName : devAssets.getRelsysName()) {
			paraMap.put("relsysName", sysName);
			paraMap.put("id", devAssets.getId());
			this.getSqlMapClientTemplate().insert(
					GlobalConfig.sqlId + "devAssets.addSysName", paraMap);
			paraMap.clear();
		}

	}

	public void devAssetsDelete(int id) {
		// TODO Auto-generated method stub
		// this.getSqlMapClientTemplate().delete("devAssetsUse.delete", id);
		super.delete(GlobalConfig.sqlId+"devAssets.delete", id);

	}

	public DevAssets devAssetsQueryById(int id) {
		return (DevAssets) this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"devAssets.queryById", id);
		// TODO Auto-generated method stub

	}

	/**
	 * 评估导出报表。查询所有
	 * 
	 * @param
	 * @return List<DevAssets>
	 * @author dugaoyang createDate 2013-6-1
	 * 
	 */
	public List<DevAssets> queryAllDevAssets(){
		return (List<DevAssets>)this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"devAssets.queryAll");
	}
}
