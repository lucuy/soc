package com.compliance.service.cpManage.technology;

import java.util.List;
import java.util.Map;

import com.compliance.model.cpManage.technology.Technology;
import com.util.page.Page;
import com.util.page.SearchResult;


/**
 * Description: 技术差距分析 Service
 * @author 杜高杨
 * @Version 1.0
 * @Created at 2013-05-14
 * @Modified by
 * 
 */
public interface TechnologyService {
	
	
	/**
	 * 查询数据
	 * @param Map, Page
	 * @return SearchResult
	 */
	@SuppressWarnings("unchecked")
	public SearchResult query(Map<String , Object> map, Page page);
	
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
