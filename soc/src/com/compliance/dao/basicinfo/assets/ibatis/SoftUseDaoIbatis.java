package com.compliance.dao.basicinfo.assets.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.compliance.dao.basicinfo.assets.SoftUseDao;
import com.compliance.dao.ibatis.BaseDaoiBatis;
import com.compliance.model.basicinfo.assets.BusinessAssets;
import com.soc.model.conf.GlobalConfig;

;

public class SoftUseDaoIbatis extends BaseDaoiBatis implements SoftUseDao {

	public int softCount(Map mapper) {
		// TODO Auto-generated method stub
		int softNum = 0;
		Object softObj = null;
		softObj = this.getSqlMapClientTemplate().queryForObject(
				GlobalConfig.sqlId+"softUse.count", mapper);
		if (softObj != null) {
			softNum = ((Integer) softObj).intValue();
		}

		return softNum;
	}

	public List<BusinessAssets> query(Map map, int startRow, int pageSize) {
		// TODO Auto-generated method stub

		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"softUse.query",
				map, startRow, pageSize);

	}

	public void softInsert(BusinessAssets resType) {
		// TODO Auto-generated method stub

		int id = (Integer) this.getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"softUse.insert", resType);
		//批量插入业务应用软件所属系统名称
		Map<String, Object> paraMap = new HashMap<String, Object>();
		for (String sysName : resType.getRelsysName()) {
			paraMap.put("relsysName", sysName);
			paraMap.put("id", id);
			this.getSqlMapClientTemplate().insert(
					GlobalConfig.sqlId + "softUse.addSysName", paraMap);
			paraMap.clear();
		}

	}

	public void softUpdate(BusinessAssets resType) {
		this.getSqlMapClientTemplate().update(GlobalConfig.sqlId+"softUse.update", resType);
		//删除原来的关联重新进行关联
		this.getSqlMapClientTemplate().update(GlobalConfig.sqlId+"softUse.delSysName", resType.getId());
		//批量插入业务应用软件所属系统名称
		Map<String, Object> paraMap = new HashMap<String, Object>();
		for (String sysName : resType.getRelsysName()) {
			paraMap.put("relsysName", sysName);
			paraMap.put("id", resType.getId());
			this.getSqlMapClientTemplate().insert(
					GlobalConfig.sqlId + "softUse.addSysName", paraMap);
			paraMap.clear();
		}

	}

	public void softDelete(int id) {
		// TODO Auto-generated method stub
		// this.getSqlMapClientTemplate().delete("softUse.delete", id);
		super.delete(GlobalConfig.sqlId+"softUse.deleteref", id);
		super.delete(GlobalConfig.sqlId+"softUse.delete", id);

	}

	public BusinessAssets softQueryById(int id) {
		return (BusinessAssets) this.getSqlMapClientTemplate().queryForObject(
				GlobalConfig.sqlId+"softUse.queryById", id);
		// TODO Auto-generated method stub

	}

	// 精确查找
	public List<BusinessAssets> preciseQuerySoft(Map map, int startRow,
			int pageSize) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList(
				GlobalConfig.sqlId+"softUse.prisicequery", map, startRow, pageSize);
	}

	// 精确查找数据行数
	public int softPreciseCount(Map mapper) {
		// TODO Auto-generated method stub
		int softNum = 0;
		Object softObj = null;
		softObj = this.getSqlMapClientTemplate().queryForObject(
				GlobalConfig.sqlId+"softUse.prisicequerycount", mapper);
		if (softObj != null) {
			softNum = ((Integer) softObj).intValue();
		}

		return softNum;
	}
	
	/**
	 * 评估导出报表。查询所有
	 * 
	 * @param 
	 * @return  List<BusinessAssets>
	 * @author dugaoyang createDate 2013-6-1
	 * 
	 */
	public List<BusinessAssets>  queryAllBusinessAssets(){
		return (List<BusinessAssets>)this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"softUse.queryAll");
	}
	
}
