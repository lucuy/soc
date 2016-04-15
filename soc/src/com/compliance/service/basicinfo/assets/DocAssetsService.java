package com.compliance.service.basicinfo.assets;

import java.util.List;
import java.util.Map;

import com.compliance.model.basicinfo.assets.DevAssets;
import com.compliance.model.basicinfo.assets.DocAssets;
import com.compliance.model.basicinfo.assets.BusinessAssets;
import com.util.page.Page;
import com.util.page.SearchResult;

public interface DocAssetsService {

	/**
	 * 查询安全设备条数
	 * 
	 * @return int
	 * @author lidayong createDate 2013-2-22
	 * 
	 */
	public int docAssetsCount(Map mapper);

	/**
	 * 安全文档查询
	 * 
	 * @return List
	 * @author lidayong createDate 2013-2-22
	 */
	public List<DocAssets> query(Map map, int startRow, int pageSize);

	/**
	 * 查询安全管理文档信息列表
	 * 
	 * @param map
	 * @param page
	 * @return
	 */
	public SearchResult query(Map map, Page page);

	/**
	 * 安全文档添加
	 * 
	 * @parma DocAssets
	 * @author lidayong createDate 2013-2-22
	 * 
	 */
	public void docAssetsInsert(DocAssets docAssets);

	/**
	 * 安全文档修改
	 * 
	 * @param DocAssets
	 * @author lidayong createDate 2013-2-22
	 * 
	 */
	public void docAssetsUpdate(DocAssets docAssets);

	/**
	 * 安全文档删除
	 * 
	 * @param int
	 * @author lidayong createDate 2013-2-22
	 * 
	 */
	public void docAssetsDelete(int id);

	/**
	 * 根据id查询安全文档
	 * 
	 * @param int
	 * @author lidayong createDate 2013-2-22
	 * 
	 */
	public DocAssets docAssetsQueryById(int id);

	/**
	 * 高级搜索安全设备条数
	 * 
	 * @return int
	 * @author lidayong createDate 2013-2-22
	 * 
	 */
	public int docAssetsPreciseCount(Map mapper);

	/**
	 * 高级搜索安全管理文档信息列表
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
	 * @return List<DocAssets>
	 * @author dugaoyang createDate 2013-6-1
	 * 
	 */
	public List<DocAssets> queryAllDocAssets();
}
