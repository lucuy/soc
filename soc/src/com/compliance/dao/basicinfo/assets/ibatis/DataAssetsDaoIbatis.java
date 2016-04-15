package com.compliance.dao.basicinfo.assets.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.compliance.dao.ibatis.BaseDaoiBatis;
import com.compliance.dao.basicinfo.assets.DataAssetsDao;
import com.compliance.model.basicinfo.assets.DataAssets;
import com.soc.model.conf.GlobalConfig;

public class DataAssetsDaoIbatis extends BaseDaoiBatis implements DataAssetsDao {
	// 模糊搜索数据行数
	public int dataAssetsCount(Map mapper) {
		// TODO Auto-generated method stub
		int dataAssetsNum = 0;
		Object dataAssetsObj = null;
		dataAssetsObj = this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"dataAssets.count", mapper);
		if (dataAssetsObj != null) {
			dataAssetsNum = ((Integer) dataAssetsObj).intValue();
		}

		return dataAssetsNum;

	}

	// 模糊搜索关键数据类别
	public List<DataAssets> query(Map map, int startRow, int pageSize) {
		// TODO Auto-generated method stub

		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"dataAssets.query", map, startRow, pageSize);

	}

	// 添加关键数据类别
	public void dataAssetsInsert(DataAssets dataAssets) {
		// TODO Auto-generated method stub

		int id = (Integer) this.getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"dataAssets.insert", dataAssets);
		//批量插入所属系统名称
		Map<String, Object> paraMap = new HashMap<String, Object>();
		for (String sysName : dataAssets.getRelsysName()) {
			paraMap.put("relsysName", sysName);
			paraMap.put("id", id);
			this.getSqlMapClientTemplate().insert(
					GlobalConfig.sqlId + "dataAssets.addSysName", paraMap);
			paraMap.clear();
		}
		//批量插入业务应用软件
		for (String sysName : dataAssets.getRelresName()) {
			paraMap.put("relresName", sysName);
			paraMap.put("id", id);
			this.getSqlMapClientTemplate().insert(
					GlobalConfig.sqlId + "dataAssets.addResName", paraMap);
			paraMap.clear();
		}
	}

	// 修改关键数据累呗
	public void dataAssetsUpdate(DataAssets dataAssets) {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().update(GlobalConfig.sqlId+"dataAssets.update", dataAssets);
		//删除原来的关联重新进行关联
		this.getSqlMapClientTemplate().update(GlobalConfig.sqlId+"dataAssets.delSysName", dataAssets.getId());
		//批量插入业务应用软件所属系统名称
		Map<String, Object> paraMap = new HashMap<String, Object>();
		for (String sysName : dataAssets.getRelsysName()) {
			paraMap.put("relsysName", sysName);
			paraMap.put("id", dataAssets.getId());
			this.getSqlMapClientTemplate().insert(
					GlobalConfig.sqlId + "dataAssets.addSysName", paraMap);
			paraMap.clear();
		}
		//批量插入业务应用软件
		for (String sysName : dataAssets.getRelresName()) {
			paraMap.put("relresName", sysName);
			paraMap.put("id", dataAssets.getId());
			this.getSqlMapClientTemplate().insert(
					GlobalConfig.sqlId + "dataAssets.addResName", paraMap);
			paraMap.clear();
		}
	}

	// 删除关键数据类别
	public void dataAssetsDelete(int id) {
		// TODO Auto-generated method stub
		// this.getSqlMapClientTemplate().delete("dataAssetsUse.delete", id);
		super.delete(GlobalConfig.sqlId+"dataAssets.delete", id);

	}

	// 根据id查询数据
	public DataAssets dataAssetsQueryById(int id) {
		return (DataAssets) this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"dataAssets.queryById", id);
		// TODO Auto-generated method stub

	}

	// 高级搜索数据条数
	public int dataAssetsPreciseCount(Map mapper) {
		// TODO Auto-generated method stub
		int dataAssetsNum = 0;
		Object dataAssetsObj = null;
		dataAssetsObj = this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"dataAssets.precisequerycount", mapper);
		if (dataAssetsObj != null) {
			dataAssetsNum = ((Integer) dataAssetsObj).intValue();
		}

		return dataAssetsNum;
	}

	// 高级搜索数据
	public List<DataAssets> queryPrecise(Map map, int startRow, int pageSize) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"dataAssets.precisequery", map, startRow, pageSize);
	}

	/**
	 * 评估导出报表。查询所有
	 * 
	 * @param
	 * @return List<DataAssets>
	 * @author dugaoyang createDate 2013-6-1
	 * 
	 */
	public List<DataAssets> queryAllDataAssets() {
		return (List<DataAssets>) this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"dataAssets.queryAll");
	}
}
