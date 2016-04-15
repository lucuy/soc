package com.compliance.dao.basicinfo.assets.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.compliance.dao.basicinfo.assets.NetAssetsDao;
import com.compliance.dao.ibatis.BaseDaoiBatis;
import com.compliance.model.basicinfo.assets.NetAssets;
import com.soc.model.conf.GlobalConfig;

public class NetAssetsDaoIbatis extends BaseDaoiBatis implements NetAssetsDao {

	public int netAssetsCount(Map mapper) {
		// TODO Auto-generated method stub
		int netAssetsNum = 0;
		Object netAssetsObj = null;
		netAssetsObj = this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"netAssets.count", mapper);
		if (netAssetsObj != null) {
			netAssetsNum = ((Integer) netAssetsObj).intValue();
		}

		return netAssetsNum;
	}

	public List<NetAssets> query(Map map, int startRow, int pageSize) {
		// TODO Auto-generated method stub

		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"netAssets.query", map, startRow, pageSize);

	}

	public void netAssetsInsert(NetAssets netAssets) {
		// TODO Auto-generated method stub

		int id = (Integer) this.getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"netAssets.insert", netAssets);
		//批量插入业务应用软件所属系统名称
		Map<String, Object> paraMap = new HashMap<String, Object>();
		for (String sysName : netAssets.getRelsysName()) {
			paraMap.put("relsysName", sysName);
			paraMap.put("id", id);
			this.getSqlMapClientTemplate().insert(
					GlobalConfig.sqlId + "netAssets.addSysName", paraMap);
			paraMap.clear();
		}
	}

	public void netAssetsUpdate(NetAssets netAssets) {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().update(GlobalConfig.sqlId+"netAssets.update", netAssets);
		//删除原来的关联重新进行关联
		this.getSqlMapClientTemplate().update(GlobalConfig.sqlId+"netAssets.delSysName", netAssets.getId());
		//批量插入业务应用软件所属系统名称
		Map<String, Object> paraMap = new HashMap<String, Object>();
		for (String sysName : netAssets.getRelsysName()) {
			paraMap.put("relsysName", sysName);
			paraMap.put("id", netAssets.getId());
			this.getSqlMapClientTemplate().insert(
					GlobalConfig.sqlId + "netAssets.addSysName", paraMap);
			paraMap.clear();
		}
	}

	public void netAssetsDelete(int id) {
		// TODO Auto-generated method stub
		// this.getSqlMapClientTemplate().delete("netAssetsUse.delete", id);
		super.delete(GlobalConfig.sqlId+"netAssets.delete", id);

	}

	public NetAssets netAssetsQueryById(int id) {
		return (NetAssets) this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"netAssets.queryById", id);
		// TODO Auto-generated method stub

	}

	public int netAssetsPreciseCount(Map mapper) {
		// TODO Auto-generated method stub
		int netAssetsNum = 0;
		Object netAssetsObj = null;
		netAssetsObj = this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"netAssets.countprecise", mapper);
		if (netAssetsObj != null) {
			netAssetsNum = ((Integer) netAssetsObj).intValue();
		}

		return netAssetsNum;
	}

	public List<NetAssets> queryPrecise(Map map, int startRow, int pageSize) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"netAssets.queryprecise", map, startRow, pageSize);
	}
	
	/**
	 * 评估导出报表。查询所有
	 * 
	 * @param
	 * @return List<NetAssets>
	 * @author dugaoyang createDate 2013-6-1
	 * 
	 */
	public List<NetAssets> queryAllNetAssets(){
		return (List<NetAssets>)this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"netAssets.queryAll");
	}

}
