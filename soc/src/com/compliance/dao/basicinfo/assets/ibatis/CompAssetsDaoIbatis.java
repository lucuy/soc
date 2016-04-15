package com.compliance.dao.basicinfo.assets.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.compliance.dao.basicinfo.assets.CompAssetsDao;
import com.compliance.dao.ibatis.BaseDaoiBatis;

import com.compliance.model.basicinfo.assets.CompAssets;
import com.soc.model.conf.GlobalConfig;

public class CompAssetsDaoIbatis extends BaseDaoiBatis implements CompAssetsDao {
	// 模糊搜索数据行数
	public int compAssetsCount(Map mapper) {
		// TODO Auto-generated method stub
		int compAssetsNum = 0;
		Object compAssetsObj = null;
		compAssetsObj = this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"compAssets.count", mapper);
		if (compAssetsObj != null) {
			compAssetsNum = ((Integer) compAssetsObj).intValue();
		}
		return compAssetsNum;
	}

	// 模糊搜索主机存储信息
	public List<CompAssets> query(Map map, int startRow, int pageSize) {
		// TODO Auto-generated method stub

		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"compAssets.query", map, startRow, pageSize);

	}

	// 添加主机存储设备
	public void compAssetsInsert(CompAssets compAssets) {
		// TODO Auto-generated method stub

		int id = (Integer) this.getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"compAssets.insert", compAssets);
		//批量插入所属系统名称
		Map<String, Object> paraMap = new HashMap<String, Object>();
		for (String sysName : compAssets.getRelsysName()) {
			paraMap.put("relsysName", sysName);
			paraMap.put("id", id);
			this.getSqlMapClientTemplate().insert(
					GlobalConfig.sqlId + "compAssets.addSysName", paraMap);
			paraMap.clear();
		}
		//批量插入业务应用软件
		for (String sysName : compAssets.getRelresName()) {
			paraMap.put("relresName", sysName);
			paraMap.put("id", id);
			this.getSqlMapClientTemplate().insert(
					GlobalConfig.sqlId + "compAssets.addResName", paraMap);
			paraMap.clear();
		}
	}

	// 修改主机存储设备信息
	public void compAssetsUpdate(CompAssets compAssets) {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().update(GlobalConfig.sqlId+"compAssets.update", compAssets);
		//删除原来的关联重新进行关联
		this.getSqlMapClientTemplate().update(GlobalConfig.sqlId+"compAssets.delSysName", compAssets.getId());
		//批量插入业务应用软件所属系统名称
		Map<String, Object> paraMap = new HashMap<String, Object>();
		for (String sysName : compAssets.getRelsysName()) {
			paraMap.put("relsysName", sysName);
			paraMap.put("id", compAssets.getId());
			this.getSqlMapClientTemplate().insert(
					GlobalConfig.sqlId + "compAssets.addSysName", paraMap);
			paraMap.clear();
		}
		//批量插入业务应用软件
		for (String sysName : compAssets.getRelresName()) {
			paraMap.put("relresName", sysName);
			paraMap.put("id", compAssets.getId());
			this.getSqlMapClientTemplate().insert(
					GlobalConfig.sqlId + "compAssets.addResName", paraMap);
			paraMap.clear();
		}
	}

	// 删除主机存储设备信息
	public void compAssetsDelete(int id) {
		// TODO Auto-generated method stub
		// this.getSqlMapClientTemplate().delete("compAssetsUse.delete", id);
		super.delete(GlobalConfig.sqlId+"compAssets.delete", id);

	}

	// 根据id查找主机存储设备信息
	public CompAssets compAssetsQueryById(int id) {
		return (CompAssets) this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"compAssets.queryById", id);
		// TODO Auto-generated method stub

	}

	// 高级搜索返回数据行数
	public int compAssetsPreciseCount(Map mapper) {
		// TODO Auto-generated method stub
		int compAssetsNum = 0;
		Object compAssetsObj = null;
		compAssetsObj = this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"compAssets.countprecise", mapper);
		if (compAssetsObj != null) {
			compAssetsNum = ((Integer) compAssetsObj).intValue();
		}

		return compAssetsNum;
	}

	// 高级搜索返回主机存储设备信息列表
	public List<CompAssets> queryPrecise(Map map, int startRow, int pageSize) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"compAssets.queryprecise", map, startRow, pageSize);
	}

	/**
	 * 评估导出报表。查询所有
	 * 
	 * @param
	 * @return List<CompAssets>
	 * @author dugaoyang createDate 2013-6-1
	 * 
	 */
	public List<CompAssets> queryAllCompAssets() {
		return (List<CompAssets>) this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"compAssets.queryAll");
	}

}
