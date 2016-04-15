package com.compliance.dao.cpManage.technology;

import java.util.List;
import java.util.Map;

import com.compliance.model.cpManage.technology.Technology;


/**
 * Description: 技术差距分析 Dao
 * @author 杜高杨
 * @Version 1.0
 * @Created at 2013-05-14
 * @Modified by
 * 
 */
public interface TechnologyDao {
	
	
	/**
	 * 计算有多少条记录
	 * @param map
	 * @return int
	 */
	@SuppressWarnings("unchecked")
	public int count(Map map);
	
	/**
	 * 查询数据
	 * @param Map, int, int
	 * @return List<Technology>
	 */
	@SuppressWarnings("unchecked")
	public List<Technology> query(Map<String , Object> map, int startRow, int pageSize);
	
	/**
	 * 查询信息系统最新评估结果列表
	 * @param 
	 * @return List<Technology>
	 */
	@SuppressWarnings("unchecked")
	public List<Technology> queryMaxEndTime();
	
	/**
	 * 根据系统id查询状态为评估结束的列表
	 * @param String
	 * @return List<Technology>
	 */
	@SuppressWarnings("unchecked")
	public List<Technology> queryByAcIdForCs2(String currentState);
	
	/**
	 * 根据id查询某条系统详细信息
	 * @param id
	 * @return Technology
	 */
	@SuppressWarnings("unchecked")
	public Technology queryById(int id);
	
	/**
	 * 删除数据
	 * @param id
	 * @return void
	 */
	public void delete(int id);
	
	/**
	 * 添加数据
	 * @param Technology
	 * @return void
	 */
	public int insert(Technology t);
	
	/**
	 * 修改数据
	 * @param Technology
	 * @return void
	 */
	public void update(Technology t);
	
	/**
	 * 修改状态
	 * @param Map 
	 * @return void
	 */
	public void updateCurrentState(Map map);
	
	/**
	 * 修改结束时间
	 * @param Map 
	 * @return void
	 */
	public void updateEndTime(Map map);
	
}
