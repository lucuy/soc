package com.topo.dao.device.ibatis;

import java.util.List;
import java.util.Map;

import com.topo.dao.device.NetBackGroundPhotoDao;
import com.topo.dao.ibatis.BaseDaoiBatis;
import com.topo.model.device.NetBackGroundPhoto;

public class NetBackGroundPhotoIbatis extends BaseDaoiBatis implements
		NetBackGroundPhotoDao {

	@Override
	public void update(NetBackGroundPhoto netBackGroundPhoto) {
		this.getSqlMapClientTemplate().update("updateNetBackGroundPhotoName",netBackGroundPhoto);
	}

	@Override
	public String queryCurrentPhotoName(int status) {
		return (String)this.getSqlMapClientTemplate().queryForObject("queryCurrentPhoto", status);
	}

	@Override
	public List<NetBackGroundPhoto> queryAllNBGP() {
		return this.getSqlMapClientTemplate().queryForList("queryAllNBGP");
	}

	@Override
	public NetBackGroundPhoto queryCurrentNBGP(Map map) {
		// TODO Auto-generated method stub
		return (NetBackGroundPhoto)this.getSqlMapClientTemplate().queryForObject("queryCurrentNBGP",map);
	}

	@Override
	public void insert(NetBackGroundPhoto netBackGroundPhoto) {
		this.getSqlMapClientTemplate().update("insertNBGP",netBackGroundPhoto);
	}

	@Override
	public void deleteNBGP(String photoName) {
		this.getSqlMapClientTemplate().delete("deleteNBGP",photoName);
		
	}

}
