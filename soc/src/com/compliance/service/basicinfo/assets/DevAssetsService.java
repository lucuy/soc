package com.compliance.service.basicinfo.assets;

import java.util.List;
import java.util.Map;

import com.compliance.model.basicinfo.assets.DevAssets;
import com.compliance.model.basicinfo.assets.BusinessAssets;
import com.util.page.Page;
import com.util.page.SearchResult;

public interface DevAssetsService {

	/**
	 * 查询安全设备条数
	 * 
	 * @return int
	 * @author lidayong createDate 2013-2-21
	 * 
	 */
	public int devAssetsCount(Map mapper);

	/**
	 * 安全设备查询
	 * 
	 * @return List
	 * @author lidayong createDate 2013-2-21
	 */
	public List<DevAssets> query(Map map, int startRow, int pageSize);

	/**
	 * 查询安全设备信息列表
	 * 
	 * @param map
	 * @param page
	 * @return
	 */
	public SearchResult query(Map map, Page page);

	/**
	 * 安全设备添加
	 * 
	 * @parma DevAssets
	 * @author lidayong createDate 2013-2-21
	 * 
	 */
	public void devAssetsInsert(DevAssets devAssets);

	/**
	 * 安全设备修改
	 * 
	 * @param DevAssets
	 * @author lidayong createDate 2013-2-21
	 * 
	 */
	public void devAssetsUpdate(DevAssets devAssets);

	/**
	 * 安全设备删除
	 * 
	 * @param int
	 * @author lidayong createDate 2013-2-21
	 * 
	 */
	public void devAssetsDelete(int id);

	/**
	 * 根据id查询 安全设备
	 * 
	 * @param int
	 * @author lidayong createDate 2013-2-21
	 * 
	 */
	public DevAssets devAssetsQueryById(int id);

	/**
	 * 高级搜索安全设备条数
	 * 
	 * @return int
	 * @author lidayong createDate 2013-2-21
	 * 
	 */
	public int devAssetsPreciseCount(Map mapper);

	/**
	 * 高级搜索安全设备信息列表
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
	 * @return List<DevAssets>
	 * @author dugaoyang createDate 2013-6-1
	 * 
	 */
	public List<DevAssets> queryAllDevAssets();
}
