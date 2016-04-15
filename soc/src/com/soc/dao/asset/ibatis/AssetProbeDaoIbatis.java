package com.soc.dao.asset.ibatis;

import java.util.List;

import com.soc.dao.asset.AssetProbeDao;
import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.model.asset.AssetProbe;
import com.soc.model.conf.GlobalConfig;

public class AssetProbeDaoIbatis extends BaseDaoIbatis implements AssetProbeDao {

	@Override
	public List<AssetProbe> queryByMac(String Mac) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"assetProbe.query",Mac);
	}

	@Override
	public List<AssetProbe> queryByTaskId(int id) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"assetProbe.queryByTaskId" , id);
	}

	@Override
	public void deleteAssetProbe(int id) {
		// TODO Auto-generated method stub
		 this.getSqlMapClientTemplate().delete(GlobalConfig.sqlId+"assetProbe.deleteAssetProbe",id);
	}

}
