package com.soc.dao.asset.ibatis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.soc.dao.asset.AssetProbeTaskDao;
import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.model.asset.AssetProbeTask;
import com.soc.model.conf.GlobalConfig;

public class AssetProbeTaskDaoIbatis extends BaseDaoIbatis implements
		AssetProbeTaskDao {

	@Override
	public long insertTask(AssetProbeTask task) {
		long assetProbeTaskId = 0;
		Object assetObject = this.getSqlMapClientTemplate().insert(GlobalConfig.sqlId+
				"assetProbeTask.insert", task);
		if (assetObject != null) {
			assetProbeTaskId = Long.parseLong(assetObject.toString());
		}
		return assetProbeTaskId;

	}

	@Override
	public List<AssetProbeTask> queryProbe(int startRow,int pageSize) {

		return this.getSqlMapClientTemplate().queryForList(
				GlobalConfig.sqlId+"assetProbeTask.query",startRow,pageSize);
		
	}
	@Override
	public List<Map> queryName(int startRow,int pageSize , String keyword){
		
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"assetProbeTask.queryName",keyword,startRow,pageSize);
	}
	@Override
	public void updateTask(AssetProbeTask task) {
		this.getSqlMapClientTemplate().update(GlobalConfig.sqlId+"assetProbeTask.update", task);

	}

	@Override
	public void deleteTask(int taskId) {
		this.getSqlMapClientTemplate().delete(GlobalConfig.sqlId+"assetProbeTask.delete", taskId);

	}

	@Override
	public AssetProbeTask queryByIdTask(int taskId) {
		return (AssetProbeTask) this.getSqlMapClientTemplate().queryForObject(
				GlobalConfig.sqlId+"assetProbeTask.queryById", taskId);
	}

	@Override
	public int count(Map map) {
		// TODO Auto-generated method stub
		int count = 0;
		count = (Integer)this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"assetProbeTask.count");
	    return count;
	}

	@Override
	public List<Map> queryAllTask(int startRow,int pageSize) {
		// TODO Auto-generated method stub
		//List<Map> list = new ArrayList<Map>() ; 
		return  this.getSqlMapClientTemplate().queryForList(
				GlobalConfig.sqlId+	"assetProbeTask.queryTaskLeftJoin",startRow,pageSize);

	}

	@Override
	public int queryNumLeftJoin(String keyword) {
		// TODO Auto-generated method stub
		return (Integer)this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"assetProbeTask.queryNameOfCount",keyword);
	}

	@Override
	public int getProCount(Map map) {
		// TODO Auto-generated method stub
		return (Integer)this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"assetProbeTask.queryCountPro",map);
	}

	@Override
	public List<Map> queryPro(Map map ,int startRow, int pageSize) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"assetProbeTask.queryPro",map,startRow,pageSize);
	}
}
