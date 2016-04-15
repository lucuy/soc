package com.compliance.dao.basicinfo.assets;

import java.util.List;
import java.util.Map;

import com.compliance.model.basicinfo.assets.NetAssets;

public interface NetAssetsDao {

	/**
	 * 模糊查询网络互连设备条数
	 * 
	 * @return int
	 * @author lidayong createDate 2013-2-22
	 * 
	 */
	public int netAssetsCount(Map mapper);

	/**
	 * 网络互连设备模糊查询
	 * 
	 * @return List
	 * @author lidayong createDate 2013-2-22
	 */
	public List<NetAssets> query(Map map, int startRow, int pageSize);

	/**
	 * 网络互连设备添加
	 * 
	 * @parma NetAssets
	 * @author lidayong createDate 2013-2-22
	 * 
	 */
	public void netAssetsInsert(NetAssets netAssets);

	/**
	 * 修改网络互连设备
	 * 
	 * @param NetAssets
	 * @author lidayong createDate 2013-2-22
	 * 
	 */
	public void netAssetsUpdate(NetAssets netAssets);

	/**
	 * 删除网络互连设备
	 * 
	 * @param int
	 * @author lidayong createDate 2013-2-22
	 * 
	 */
	public void netAssetsDelete(int id);

	/**
	 * 根据id查询 网络互连设备
	 * 
	 * @param int
	 * @author lidayong createDate 2013-2-22
	 * 
	 */
	public NetAssets netAssetsQueryById(int id);

	/**
	 * 高级搜索网络互连设备条数
	 * 
	 * @return int
	 * @author lidayong createDate 2013-2-22
	 * 
	 */
	public int netAssetsPreciseCount(Map mapper);

	/**
	 * 网络互连设备高级搜索
	 * 
	 * @return List
	 * @author lidayong createDate 2013-2-22
	 */
	public List<NetAssets> queryPrecise(Map map, int startRow, int pageSize);

	/**
	 * 评估导出报表。查询所有
	 * 
	 * @param
	 * @return List<NetAssets>
	 * @author dugaoyang createDate 2013-6-1
	 * 
	 */
	public List<NetAssets> queryAllNetAssets();
}
