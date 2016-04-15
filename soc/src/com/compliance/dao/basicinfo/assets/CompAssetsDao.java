package com.compliance.dao.basicinfo.assets;

import java.util.List;
import java.util.Map;

import com.compliance.model.basicinfo.assets.CompAssets;

public interface CompAssetsDao {

	/**
	 * 模糊查询主机存储设备条数
	 * 
	 * @return int
	 * @author liujingjing
	 * 
	 */
	public int compAssetsCount(Map mapper);

	/**
	 * 主机存储设备模糊查询信息列表
	 * 
	 * @return List
	 * @author liujingjing
	 */
	public List<CompAssets> query(Map map, int startRow, int pageSize);

	/**
	 * 主机存储设备添加
	 * 
	 * @parma compAssets
	 * @author liujingjing
	 * 
	 */
	/**
	 * @param compAssets
	 */
	public void compAssetsInsert(CompAssets compAssets);

	/**
	 * 主机存储设备修改
	 * 
	 * @param compAssets
	 * @author liujingjing
	 * 
	 */
	public void compAssetsUpdate(CompAssets compAssets);

	/**
	 * 主机存储设备删除
	 * 
	 * @param int
	 * @author liujingjing
	 * 
	 */
	public void compAssetsDelete(int id);

	/**
	 * 根据id查询 主机存储设备
	 * 
	 * @param int
	 * @author liujingjing
	 * 
	 */
	public CompAssets compAssetsQueryById(int id);

	/**
	 * 高级搜索主机存储设备条数
	 * 
	 * @return int
	 * @author liujingjing
	 * 
	 */
	public int compAssetsPreciseCount(Map mapper);

	/**
	 * 主机存储设备高级搜索信息列表
	 * 
	 * @return List
	 * @author liujingjing
	 */
	public List<CompAssets> queryPrecise(Map map, int startRow, int pageSize);

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
