package com.compliance.dao.basicinfo.assets;

import java.util.List;
import java.util.Map;

import com.compliance.model.basicinfo.assets.DataAssets;

;

public interface DataAssetsDao {

	/**
	 * 模糊查询关键数据条数
	 * 
	 * @return int
	 * 
	 */
	public int dataAssetsCount(Map mapper);

	/**
	 * 关键数据模糊查询
	 * 
	 * @return List
	 */
	public List<DataAssets> query(Map map, int startRow, int pageSize);

	/**
	 * 关键数据添加
	 * 
	 * @parma dataAssets
	 * 
	 */
	public void dataAssetsInsert(DataAssets dataAssets);

	/**
	 * 关键数据修改
	 * 
	 * @param dataAssets
	 * 
	 */
	public void dataAssetsUpdate(DataAssets dataAssets);

	/**
	 * 关键数据删除
	 * 
	 * @param int
	 * 
	 */
	public void dataAssetsDelete(int id);

	/**
	 * 根据id查询 关键数据
	 * 
	 * @param int
	 * 
	 */
	public DataAssets dataAssetsQueryById(int id);

	/**
	 * 高级搜索关键数据条数
	 * 
	 * @return int
	 * 
	 */
	public int dataAssetsPreciseCount(Map mapper);

	/**
	 * 关键数据高级搜索
	 * 
	 * @return List
	 */
	public List<DataAssets> queryPrecise(Map map, int startRow, int pageSize);

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
