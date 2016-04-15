package com.compliance.service.cpManage.technology.impl;

import java.util.List;
import java.util.Map;

import com.compliance.dao.cpManage.technology.TechnologyDao;
import com.compliance.model.cpManage.technology.Technology;
import com.compliance.service.cpManage.technology.TechnologyService;
import com.compliance.service.impl.BaseServiceImpl;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * Description:  技术差距分析 Service实现
 * 
 * @author 杜高杨
 * @Version 1.0
 * @Created at 2013-05-14
 * @Modified by
 */
public class TechnologyServiceImpl extends BaseServiceImpl implements TechnologyService {
	
	private TechnologyDao technologyDao;

	/**
	 * 查询数据
	 * 
	 * @param Map, Page
	 * @return SearchResult
	 */
	@SuppressWarnings("unchecked")
	public SearchResult query(Map<String, Object> map, Page page) {
		// 查询数据
		int rowCount = technologyDao.count(map);
		page.setTotalCount(rowCount);
		List<Technology> list = technologyDao.query(map, page.getStartIndex(), page.getPageSize());
		// 处理分页
		SearchResult sr = new SearchResult();
		sr.setList(list);
		sr.setPage(page);
		return sr;
	}
	
	/**
	 * 查询信息系统最新评估结果列表
	 * @param 
	 * @return List<Technology>
	 */
	@SuppressWarnings("unchecked")
	public List<Technology> queryMaxEndTime() {
		return technologyDao.queryMaxEndTime();
	}
	
	/**
	 * 根据系统id查询状态为评估结束的列表
	 * @param String
	 * @return List<Technology>
	 */
	@SuppressWarnings("unchecked")
	public List<Technology> queryByAcIdForCs2(String currentState){
		return technologyDao.queryByAcIdForCs2(currentState);
	}
	
	/**
	 * 根据id查询某条系统详细信息
	 * @param id
	 * @return Technology
	 */
	@SuppressWarnings("unchecked")
	public Technology queryById(int id) {
		return technologyDao.queryById(id);
	}
	
	
	/**
	 * 删除数据
	 * 
	 * @param id
	 * @return void
	 */
	
	public void delete(int id) {
		technologyDao.delete(id);
	}

	/**
	 * 插入数据
	 * @param Technology
	 * @return void
	 */
	public int insert(Technology t) {
		return technologyDao.insert(t);
	}
	
	/**
	 * 修改数据
	 * @param Technology
	 * @return void
	 */
	public void update(Technology t) {
		technologyDao.update(t);
	}
	
	/**
	 * 修改状态
	 * @param Map
	 * @return void
	 */
	public void updateCurrentState(Map map){
		technologyDao.updateCurrentState(map);
	}
	
	/**
	 * 修改结束时间
	 * @param Map 
	 * @return void
	 */
	public void updateEndTime(Map map){
		technologyDao.updateEndTime(map);
	}
	
	
	public TechnologyDao getTechnologyDao() {
		return technologyDao;
	}

	public void setTechnologyDao(TechnologyDao technologyDao) {
		this.technologyDao = technologyDao;
	}

}
