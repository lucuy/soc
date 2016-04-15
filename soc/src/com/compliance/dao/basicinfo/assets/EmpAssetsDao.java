package com.compliance.dao.basicinfo.assets;

import java.util.List;
import java.util.Map;

import com.compliance.model.basicinfo.assets.EmpAssets;

public interface EmpAssetsDao {

	/**
	 * 查询安全人员条数
	 * 
	 * @return int
	 * @author lidayong createDate 2013-2-21
	 * 
	 */
	public int empAssetsCount(Map mapper);

	/**
	 * 安全人员查询
	 * 
	 * @return List
	 * @author lidayong createDate 2013-2-21
	 */
	public List<EmpAssets> query(Map map, int startRow, int pageSize);

	/**
	 * 安全人员添加
	 * 
	 * @parma EmpAssets
	 * @author lidayong createDate 2013-2-21
	 * 
	 */
	public void empAssetsInsert(EmpAssets empAssets);

	/**
	 * 安全人员修改
	 * 
	 * @param EmpAssets
	 * @author lidayong createDate 2013-2-21
	 * 
	 */
	public void empAssetsUpdate(EmpAssets empAssets);

	/**
	 * 安全人员删除
	 * 
	 * @param int
	 * @author lidayong createDate 2013-2-21
	 * 
	 */
	public void empAssetsDelete(int id);

	/**
	 * 根据id查询 安全人员
	 * 
	 * @param int
	 * @author lidayong createDate 2013-2-21
	 * 
	 */
	public EmpAssets empAssetsQueryById(int id);

	/**
	 * 高级搜索安全人员条数
	 * 
	 * @return int
	 * @author lidayong createDate 2013-2-21
	 * 
	 */
	public int empAssetsPreciseCount(Map mapper);

	/**
	 * 安全人员查询
	 * 
	 * @return List
	 * @author lidayong createDate 2013-2-21
	 */
	public List<EmpAssets> queryPrecise(Map map, int startRow, int pageSize);

	/**
	 * 评估导出报表。查询所有
	 * 
	 * @param
	 * @return List<EmpAssets>
	 * @author dugaoyang createDate 2013-6-1
	 * 
	 */
	public List<EmpAssets> queryAllEmpAssets();
}
