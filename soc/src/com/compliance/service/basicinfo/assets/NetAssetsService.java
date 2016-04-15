package com.compliance.service.basicinfo.assets;

import java.util.List;
import java.util.Map;

import com.compliance.model.basicinfo.assets.DevAssets;
import com.compliance.model.basicinfo.assets.NetAssets;
import com.compliance.model.basicinfo.assets.BusinessAssets;
import com.util.page.Page;
import com.util.page.SearchResult;

public interface NetAssetsService {

	/**
	 * 模糊查询安全设备条数
	 * 
	 * @return int
	 * @author lidayong createDate 2013-2-22
	 * 
	 */
	public int netAssetsCount(Map mapper);

	/**
	 * 查询
	 * 
	 * @return List
	 * @author lidayong createDate 2013-2-22
	 */
	public List<NetAssets> query(Map map, int startRow, int pageSize);

	/**
	 * 查询网络互连设备信息列表
	 * 
	 * @param map
	 * @param page
	 * @return
	 */
	public SearchResult query(Map map, Page page);

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
	 * 根据id查询网络互连设备
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
	 * 高级搜索网络互连设备信息列表
	 * 
	 * @param map
	 * @param page
	 * @return
	 */
	public SearchResult queryPrecise(Map map, Page page);

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
