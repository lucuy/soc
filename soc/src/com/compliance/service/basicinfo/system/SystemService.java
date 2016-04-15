package com.compliance.service.basicinfo.system;

import java.util.List;
import java.util.Map;

import com.compliance.model.basicinfo.system.SystemManager;
import com.util.page.Page;
import com.util.page.SearchResult;

public interface SystemService {

	/**
	 * 计算有多少条记录
	 * 
	 * @param map
	 * @return int
	 */
	@SuppressWarnings("unchecked")
	public int count(Map map);

	/**
	 * 根据ID查询数据
	 * 
	 * @param int
	 * @return Admin
	 */
	@SuppressWarnings("unchecked")
	public SystemManager queryById(int id);

	/**
	 * 根据sysId查询数据
	 * 
	 * @param String
	 * @return SystemManager
	 */
	@SuppressWarnings("unchecked")
	public SystemManager queryBySysId(String sysId);

	/**
	 * 查询所有数据
	 * 
	 * @param Map
	 * @return List<Admin>
	 */
	@SuppressWarnings("unchecked")
	public List<SystemManager> query(Map map);

	public SearchResult query(Map map, Page page);

	/**
	 * 查询分页数据
	 * 
	 * @param Map
	 *            , int, int
	 * @return List<Admin>
	 */
	@SuppressWarnings("unchecked")
	public List<SystemManager> queryForPage(Map map, int startRow, int pageSize);

	// public List<Pro_Line> queryForPageScore(Map map, int startRow, int
	// pageSize);
	/**
	 * 新建数据并返回PK
	 * 
	 * @param SystemManager
	 * @return id值
	 */
	@SuppressWarnings("unchecked")
	public int insert(SystemManager system);

	public int insertProLine(SystemManager system);

	public void insertRecord(String sysId);

	/**
	 * 删除
	 * 
	 * @param id
	 */
	@SuppressWarnings("unchecked")
	public void delete(int id);

	public void deleteProLine(String id);

	public void deleteRecord(String id);

	/**
	 * 更新数据
	 * 
	 * @param SystemManager
	 */
	@SuppressWarnings("unchecked")
	public void update(SystemManager system);

	/**
	 * 高级搜索计算有多少条记录
	 * 
	 * @param map
	 * @return int
	 */
	@SuppressWarnings("unchecked")
	public int countPrecise(Map map);

	/**
	 * 高级搜索信息系统列表信息
	 * 
	 * @param map
	 * @return int
	 */
	public SearchResult queryPrecise(Map map, Page page);

}
