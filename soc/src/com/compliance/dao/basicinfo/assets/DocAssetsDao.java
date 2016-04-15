package com.compliance.dao.basicinfo.assets;

import java.util.List;
import java.util.Map;

import com.compliance.model.basicinfo.assets.DocAssets;

public interface DocAssetsDao {

	/**
	 * 模糊查询安全设备条数
	 * 
	 * @return int
	 * 
	 */
	public int docAssetsCount(Map mapper);

	/**
	 * 安全文档模糊查询
	 * 
	 * @return List
	 */
	public List<DocAssets> query(Map map, int startRow, int pageSize);

	/**
	 * 安全文档添加
	 * 
	 * @parma DocAssets
	 * 
	 */
	public void docAssetsInsert(DocAssets docAssets);

	/**
	 * 安全文档修改
	 * 
	 * @param DocAssets
	 * 
	 */
	public void docAssetsUpdate(DocAssets docAssets);

	/**
	 * 安全文档删除
	 * 
	 * @param int
	 * 
	 */
	public void docAssetsDelete(int id);

	/**
	 * 根据id查询安全文档
	 * 
	 * @param int
	 * 
	 */
	public DocAssets docAssetsQueryById(int id);

	/**
	 * 查询安全设备条数
	 * 
	 * @return int
	 * 
	 */
	public int docAssetsPreciseCount(Map mapper);

	/**
	 * 安全文档查询
	 * 
	 * @return List
	 */
	public List<DocAssets> queryPrecise(Map map, int startRow, int pageSize);

	/**
	 * 评估导出报表。查询所有
	 * 
	 * @param
	 * @return List<DocAssets>
	 * @author dugaoyang createDate 2013-6-1
	 * 
	 */
	public List<DocAssets> queryAllDocAssets();
}
