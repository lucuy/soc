package com.compliance.dao.cpManage.assessResult;

import java.util.List;
import java.util.Map;

import com.compliance.model.cpManage.assessResult.AssessResult;
/**
 * 技术差距评估数据层接口类
 * Description： 
 * @author leiya
 *2013-5-18下午2:17:49
 */
public interface AssessResultDao {

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
	 * @return List<AssessResult>
	 */
	@SuppressWarnings("unchecked")
	public List<AssessResult>assessResults(Map<String , Object> map, int startRow, int pageSize);
	
	
	/**
	 * 模糊查询差距分布表
	 * @param Map
	 * @return List<AssessResult>
	 */
	@SuppressWarnings("unchecked")
	public List<AssessResult> queryAssessCountTable(Map map);
	
	/**
	 * 查询评估项存在数量
	 * @param Map
	 * @return List<AssessResult>
	 */
	@SuppressWarnings("unchecked")
	public List<AssessResult> queryAssessCount(Map map);
	
	/**
	 * 查询评估项已评估数量
	 * @param String
	 * @return int
	 */
	@SuppressWarnings("unchecked")
	public int queryAssessOverCount(String acId);
	
	/**
	 * 添加数据
	 * @param AssessResult
	 * @return void
	 */
	public void insert(AssessResult a);
	
	/**
	 * 修改数据
	 * @param AssessResult
	 * @return void
	 */
	public void update(AssessResult a);
	
	
	
}
