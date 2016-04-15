package com.compliance.dao.basicinfo.system;

import java.util.List;
import java.util.Map;

import com.compliance.model.basicinfo.system.SystemManager;

public interface SystemDao {

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
	 * 更新数据
	 * 
	 * @param SystemManager
	 */
	@SuppressWarnings("unchecked")
	public void updateOther(SystemManager system);

	/**
	 * 查询信息系统条数
	 * 
	 * @retrun int
	 * @author lidayong
	 * @createDate 2013-2-19
	 * 
	 * 
	 * 
	 *             public int sysCount();
	 */
	/**
	 * 查询信息列表
	 * 
	 * @param map
	 * @param startRow
	 * @param pagesize
	 * @return
	 */
	public List<SystemManager> query(Map map, int startRow, int pagesize);

	/**
	 * 高级搜索计算有多少条记录
	 * 
	 * @param map
	 * @return int
	 */
	@SuppressWarnings("unchecked")
	public int countPrecise(Map map);

	/**
	 * 高级搜索信息系统列表
	 * 
	 * @param map
	 * @param startRow
	 * @param pagesize
	 * @return
	 */
	public List<SystemManager> queryPrecise(Map map, int startRow, int pagesize);

}
