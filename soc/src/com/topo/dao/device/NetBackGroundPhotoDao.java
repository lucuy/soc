package com.topo.dao.device;

import java.util.List;
import java.util.Map;

import com.topo.model.device.NetBackGroundPhoto;

public interface NetBackGroundPhotoDao {
	
	/**
	 * 插入
	 */
	public void insert(NetBackGroundPhoto netBackGroundPhoto);

	/**
	 * 更新
	 */
	public  void update(NetBackGroundPhoto netBackGroundPhoto);
	
	/**
	 * 查询
	 */
	public String queryCurrentPhotoName(int status);
	
	/**
	 * 查询所有的图片
	 */
	public List<NetBackGroundPhoto> queryAllNBGP();
	
	/**
	 * 查询正在使用的
	 */
	public NetBackGroundPhoto queryCurrentNBGP(Map map);
	
	/**
	 * 删除
	 */
	public void deleteNBGP(String photoName);
}
