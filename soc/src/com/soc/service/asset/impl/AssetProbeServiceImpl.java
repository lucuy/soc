package com.soc.service.asset.impl;

import java.util.List;

import com.soc.dao.asset.AssetProbeDao;
import com.soc.model.asset.AssetProbe;
import com.soc.service.asset.AssetProbeService;
import com.soc.service.impl.BaseServiceImpl;

public class AssetProbeServiceImpl extends BaseServiceImpl implements
		AssetProbeService {
	// 资产信息管理类
	private AssetProbeDao assetProbeDao;

	public AssetProbeDao getAssetProbeDao() {
		return assetProbeDao;
	}

	public void setAssetProbeDao(AssetProbeDao assetProbeDao) {
		this.assetProbeDao = assetProbeDao;
	}

	@Override
	public List<AssetProbe> queryByMac(String Mac) {
		// TODO Auto-generated method stub
		return assetProbeDao.queryByMac(Mac);
	}

	@Override
	public List<AssetProbe> queryByTaskId(int id) {
		// TODO Auto-generated method stub
		return assetProbeDao.queryByTaskId(id);
	}

	@Override
	public void deleteByTaskId(int taskId) {
		// TODO Auto-generated method stub
		assetProbeDao.deleteAssetProbe(taskId);
	}

}
