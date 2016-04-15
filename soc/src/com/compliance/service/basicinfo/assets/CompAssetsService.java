package com.compliance.service.basicinfo.assets;

import java.util.List;
import java.util.Map;

import com.compliance.model.basicinfo.assets.CompAssets;
import com.compliance.model.basicinfo.assets.CompAssets;
import com.compliance.model.basicinfo.assets.DevAssets;
import com.compliance.model.basicinfo.assets.BusinessAssets;
import com.util.page.Page;
import com.util.page.SearchResult;

public interface CompAssetsService {

	/**
	 * 模糊查询主机存储设备条数
	 * 
	 * @return int
	 * @author lidayong createDate 2013-2-21
	 * 
	 */
	public int compAssetsCount(Map mapper);

	/**
	 * 主机存储设备查询
	 * 
	 * @return List
	 * @author lidayong createDate 2013-2-21
	 */
	public List<CompAssets> query(Map map, int startRow, int pageSize);

	/**
	 * 模糊查询主机存储设备信息列表
	 * 
	 * @param map
	 * @param page
	 * @return
	 */
	public SearchResult query(Map map, Page page);

	/**
	 * 主机存储设备添加
	 * 
	 * @parma compAssets
	 * @author lidayong createDate 2013-2-21
	 * 
	 */
	public void compAssetsInsert(CompAssets compAssets);

	/**
	 * 主机存储设备修改
	 * 
	 * @param compAssets
	 * @author lidayong createDate 2013-2-21
	 * 
	 */
	public void compAssetsUpdate(CompAssets compAssets);

	/**
	 * 主机存储设备删除
	 * 
	 * @param int
	 * @author lidayong createDate 2013-2-21
	 * 
	 */
	public void compAssetsDelete(int id);

	/**
	 * 根据id查询 主机存储设备
	 * 
	 * @param int
	 * @author lidayong createDate 2013-2-21
	 * 
	 */
	public CompAssets compAssetsQueryById(int id);

	/**
	 * 模糊查询主机存储设备条数
	 * 
	 * @return int
	 * @author lidayong createDate 2013-2-21
	 * 
	 */
	public int compAssetsPreciseCount(Map mapper);

	/**
	 * 高级搜索主机存储设备信息列表
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
	 * @return List<CompAssets>
	 * @author dugaoyang createDate 2013-6-1
	 * 
	 */
	public List<CompAssets> queryAllCompAssets();
}
