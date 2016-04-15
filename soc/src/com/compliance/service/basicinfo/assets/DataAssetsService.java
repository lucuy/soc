package com.compliance.service.basicinfo.assets;

import java.util.List;
import java.util.Map;

import com.compliance.model.basicinfo.assets.DataAssets;
import com.compliance.model.basicinfo.assets.DevAssets;
import com.compliance.model.basicinfo.assets.BusinessAssets;
import com.util.page.Page;
import com.util.page.SearchResult;

public interface DataAssetsService {

	/**
	 * 模糊查询关键数据条数
	 * 
	 * @return int
	 * @author lidayong createDate 2013-2-22
	 * 
	 */
	public int dataAssetsCount(Map mapper);

	/**
	 * 关键数据模糊查询
	 * 
	 * @return List
	 * @author lidayong createDate 2013-2-22
	 */
	public List<DataAssets> query(Map map, int startRow, int pageSize);

	/**
	 * 查询关键数据类别列表
	 * 
	 * @param map
	 * @param page
	 * @return
	 */
	public SearchResult query(Map map, Page page);

	/**
	 * 关键数据添加
	 * 
	 * @parma dataAssets
	 * @author lidayong createDate 2013-2-22
	 * 
	 */
	public void dataAssetsInsert(DataAssets dataAssets);

	/**
	 * 关键数据修改
	 * 
	 * @param dataAssets
	 * @author lidayong createDate 2013-2-22
	 * 
	 */
	public void dataAssetsUpdate(DataAssets dataAssets);

	/**
	 * 关键数据删除
	 * 
	 * @param int
	 * @author lidayong createDate 2013-2-22
	 * 
	 */
	public void dataAssetsDelete(int id);

	/**
	 * 根据id查询 关键数据
	 * 
	 * @param int
	 * @author lidayong createDate 2013-2-22
	 * 
	 */
	public DataAssets dataAssetsQueryById(int id);

	/**
	 * 高级搜索关键数据条数
	 * 
	 * @return int
	 * @author lidayong createDate 2013-2-22
	 * 
	 */
	public int dataAssetsPreciseCount(Map mapper);

	/**
	 * 关键数据高级搜索
	 * 
	 * @return List
	 * @author lidayong createDate 2013-2-22
	 */
	public SearchResult queryPrecise(Map map, Page page);
	
	/**
	 * 评估导出报表。查询所有
	 * 
	 * @param
	 * @return List<DataAssets>
	 * @author dugaoyang createDate 2013-6-1
	 * 
	 */
	public List<DataAssets> queryAllDataAssets();
}
