package com.compliance.service.basicinfo.assets;

import java.util.List;
import java.util.Map;

import com.compliance.model.basicinfo.assets.BusinessAssets;
import com.util.page.Page;
import com.util.page.SearchResult;

public interface SoftUseService {

	/**
	 * 模糊查询业务应用软件条数
	 * 
	 * @return int
	 * @author lidayong createDate 2013-2-19
	 * 
	 */
	public int softCount(Map mapper);

	/**
	 * 业务应用软件查询
	 * 
	 * @return List
	 * @author lidayong createDate 2013-2-19
	 * */ 
	  public List<BusinessAssets> query(Map map, int startRow, int
	         pageSize);
	

	/**
	 * 模糊查询业务应用软件信息列表
	 * 
	 * @param map
	 * @param page
	 * @return
	 */
	public SearchResult query(Map map, Page page);

	/**
	 * 业务应用软件添加
	 * 
	 * @parma ResType
	 * @author lidayong createDate 2013-2-19
	 * 
	 */
	public void softInsert(BusinessAssets resType);

	/**
	 * 业务应用软件修改
	 * 
	 * @param ResType
	 * @author lidayong createDate 2013-2-19
	 * 
	 */
	public void softUpdate(BusinessAssets resType);

	/**
	 * 业务应用软件删除
	 * 
	 * @param int
	 * @author lidayong createDate 2013-2-19
	 * 
	 */
	public void softDelete(int id);

	/**
	 * 根据id查询 业务应用软件
	 * 
	 * @param int
	 * @author lidayong createDate 2013-2-19
	 * 
	 */
	public BusinessAssets softQueryById(int id);

	/**
	 * 高级搜索
	 * 
	 * @return List
	 * @author lidayong createDate 2013-2-19
	 */
	public SearchResult queryPreciseSoft(Map map, Page page);

	/**
	 * 高级搜索查询业务应用软件条数
	 * 
	 * @return int
	 * @author lidayong createDate 2013-2-19
	 * 
	 */
	public int softPreciseCount(Map mapper);
	
	/**
	 * 评估导出报表。查询所有
	 * 
	 * @param 
	 * @return  List<BusinessAssets>
	 * @author dugaoyang createDate 2013-6-1
	 * 
	 */
	public List<BusinessAssets>  queryAllBusinessAssets();

}
