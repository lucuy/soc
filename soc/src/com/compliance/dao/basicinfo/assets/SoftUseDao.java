package com.compliance.dao.basicinfo.assets;

import java.util.List;
import java.util.Map;

import com.compliance.model.basicinfo.assets.BusinessAssets;

public interface SoftUseDao {

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
	 */
	public List<BusinessAssets> query(Map map, int startRow, int pageSize);

	/**
	 * 业务软件添加
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
	 * 根据软件名称、所属系统名称、软件功能描述、重要程度进行精确查找
	 * 
	 * @param int
	 * @author lidayong createDate 2013-2-19
	 * 
	 */
	public List<BusinessAssets> preciseQuerySoft(Map map, int startRow,
			int pageSize);

	/**
	 * 根据软件名称、所属系统名称、软件功能描述、重要程度进行精确查找，查找数据行数
	 * 
	 * @param int
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
