package com.compliance.dao.basicinfo.assets.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.compliance.dao.ibatis.BaseDaoiBatis;
import com.compliance.dao.basicinfo.assets.DocAssetsDao;
import com.compliance.model.basicinfo.assets.DocAssets;
import com.soc.model.conf.GlobalConfig;

public class DocAssetsDaoIbatis extends BaseDaoiBatis implements DocAssetsDao {

	public int docAssetsCount(Map mapper) {
		// TODO Auto-generated method stub
		int docAssetsNum = 0;
		Object docAssetsObj = null;
		docAssetsObj = this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"docAssets.count", mapper);
		if (docAssetsObj != null) {
			docAssetsNum = ((Integer) docAssetsObj).intValue();
		}

		return docAssetsNum;

	}

	public List<DocAssets> query(Map map, int startRow, int pageSize) {
		// TODO Auto-generated method stub

		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"docAssets.query", map, startRow, pageSize);

	}

	public void docAssetsInsert(DocAssets docAssets) {
		// TODO Auto-generated method stub

		int id = (Integer) this.getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"docAssets.insert", docAssets);
		//批量插入业务应用软件所属系统名称
		Map<String, Object> paraMap = new HashMap<String, Object>();
		for (String sysName : docAssets.getRelsysName()) {
			paraMap.put("relsysName", sysName);
			paraMap.put("id", id);
			this.getSqlMapClientTemplate().insert(
					GlobalConfig.sqlId + "docAssets.addSysName", paraMap);
			paraMap.clear();
		}
	}

	public void docAssetsUpdate(DocAssets docAssets) {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().update(GlobalConfig.sqlId+"docAssets.update", docAssets);
		//删除原来的关联重新进行关联
		this.getSqlMapClientTemplate().update(GlobalConfig.sqlId+"docAssets.delSysName", docAssets.getId());
		//批量插入业务应用软件所属系统名称
		Map<String, Object> paraMap = new HashMap<String, Object>();
		for (String sysName : docAssets.getRelsysName()) {
			paraMap.put("relsysName", sysName);
			paraMap.put("id", docAssets.getId());
			this.getSqlMapClientTemplate().insert(
					GlobalConfig.sqlId + "docAssets.addSysName", paraMap);
			paraMap.clear();
		}
	}

	public void docAssetsDelete(int id) {
		// TODO Auto-generated method stub
		// this.getSqlMapClientTemplate().delete("docAssetsUse.delete", id);
		super.delete(GlobalConfig.sqlId+"docAssets.delete", id);

	}

	public DocAssets docAssetsQueryById(int id) {
		return (DocAssets) this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"docAssets.queryById", id);
		// TODO Auto-generated method stub

	}

	public int docAssetsPreciseCount(Map mapper) {
		// TODO Auto-generated method stub
		int docAssetsNum = 0;
		Object docAssetsObj = null;
		docAssetsObj = this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"docAssets.countpresice", mapper);
		if (docAssetsObj != null) {
			docAssetsNum = ((Integer) docAssetsObj).intValue();
		}

		return docAssetsNum;
	}

	public List<DocAssets> queryPrecise(Map map, int startRow, int pageSize) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"docAssets.pricisequery", map, startRow, pageSize);
	}

	/**
	 * 评估导出报表。查询所有
	 * 
	 * @param
	 * @return List<DocAssets>
	 * @author dugaoyang createDate 2013-6-1
	 * 
	 */
	public List<DocAssets> queryAllDocAssets() {
		return (List<DocAssets>) this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"docAssets.queryAll");
	}

}
