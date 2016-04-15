package com.topo.service.device.impl;

import java.util.List;
import java.util.Map;

import com.topo.dao.device.NetBackGroundPhotoDao;
import com.topo.model.device.NetBackGroundPhoto;
import com.topo.service.device.NetBackGroundPhotoService;
import com.topo.service.impl.BaseServiceImpl;

public class NetBackGroundPhotoServiceImpl extends BaseServiceImpl implements
		NetBackGroundPhotoService {

	private NetBackGroundPhotoDao netBackGroundPhotoDao;
	@Override
	public void update(NetBackGroundPhoto netBackGroundPhoto) {
		netBackGroundPhotoDao.update(netBackGroundPhoto);
	}

	@Override
	public String queryCurrentPhotoName(int status) {
		return netBackGroundPhotoDao.queryCurrentPhotoName(status);
	}

	@Override
	public List<NetBackGroundPhoto> queryAllNBGP() {
		return netBackGroundPhotoDao.queryAllNBGP();
	}
	
	public NetBackGroundPhotoDao getNetBackGroundPhotoDao() {
		return netBackGroundPhotoDao;
	}

	public void setNetBackGroundPhotoDao(NetBackGroundPhotoDao netBackGroundPhotoDao) {
		this.netBackGroundPhotoDao = netBackGroundPhotoDao;
	}

	@Override
	public NetBackGroundPhoto queryCurrentNBGP(Map map) {
		// TODO Auto-generated method stub
		return netBackGroundPhotoDao.queryCurrentNBGP(map);
	}

	@Override
	public void insert(NetBackGroundPhoto netBackGroundPhoto) {
		this.netBackGroundPhotoDao.insert(netBackGroundPhoto);
		
	}

	@Override
	public void deleteNBGP(String photoName) {
		this.netBackGroundPhotoDao.deleteNBGP(photoName);
		
	}

	

	
}
