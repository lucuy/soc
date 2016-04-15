package com.compliance.dao.basicinfo.assets;

import java.util.List;
import java.util.Map;

import com.compliance.model.basicinfo.assets.DevAssets;

public interface DevAssetsDao {

	/**
	 * 模糊查询安全设备条数
	 * 
	 * @return int
	 * 
	 */
	public int devAssetsCount(Map mapper);

	/**
	 * 安全设备查询
	 * 
	 * @return List
	 */
	public List<DevAssets> query(Map map, int startRow, int pageSize);

	/**
	 * 安全设备添加
	 * 
	 * @parma DevAssets
	 * 
	 */
	public void devAssetsInsert(DevAssets devAssets);

	/**
	 * 安全设备修改
	 * 
	 * @param DevAssets
	 * 
	 */
	public void devAssetsUpdate(DevAssets devAssets);

	/**
	 * 安全设备删除
	 * 
	 * @param int
	 * 
	 */
	public void devAssetsDelete(int id);

	/**
	 * 根据id查询 安全设备
	 * 
	 * @param int
	 * 
	 */
	public DevAssets devAssetsQueryById(int id);

	/**
	 * 高级搜索安全设备条数
	 * 
	 * @return int
	 * 
	 */
	public int devAssetsPreciseCount(Map mapper);

	/**
	 * 安全设备高级搜索
	 * 
	 * @return List
	 */
	public List<DevAssets> queryPrecise(Map map, int startRow, int pageSize);

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
