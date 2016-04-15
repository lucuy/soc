package com.compliance.service.cpManage.assessResult;

import java.util.List;
import java.util.Map;

import jsx3.chart.LineChart;

import org.jfree.chart.JFreeChart;

import com.compliance.model.cpManage.assessResult.AssessResult;
import com.compliance.model.cpManage.demand.DemandCollet;
import com.compliance.model.cpManage.technology.Technology;

/**
 * 技术差距评估service层接口 Description：
 * 
 * @author leiya 2013-5-18下午2:09:49
 */
public interface AssessResultService {

	/**
	 * 查询评估项存在数量
	 * 
	 * @param Map
	 * @return List<AssessResult>
	 */
	@SuppressWarnings("unchecked")
	public List<AssessResult> queryAssessCount(Map map);

	/**
	 * 查询评估项已评估数量
	 * 
	 * @param String
	 * @return int
	 */
	@SuppressWarnings("unchecked")
	public int queryAssessOverCount(String acId);

	/**
	 * 模糊查询差距分布表
	 * 
	 * @param Map
	 * @return List<AssessResult>
	 */
	@SuppressWarnings("unchecked")
	public List<AssessResult> queryAssessCountTable(Map map);

	/**
	 * 添加数据
	 * 
	 * @param AssessResult
	 * @return void
	 */
	public void insert(AssessResult a);

	/**
	 * 修改数据
	 * 
	 * @param AssessResult
	 * @return void
	 */
	public void update(AssessResult a);

	/**
	 * 生成符合度百分比饼图
	 * 
	 * @param acId
	 * @return JFreeChart
	 * 
	 * */
	public JFreeChart getAssessResultTotalPieJFreeChart(int acId);

	/**
	 * 生成符合度雷达图
	 * 
	 * @param List
	 *            <DemandCollet>,int
	 * @return JFreeChart
	 * 
	 * */
	public JFreeChart getAssessResultRadarJFreeChart(List<DemandCollet> demandColletList, int acId);

	/**
	 * 差距评估符合度饼图
	 * 
	 * @param int , int ,int
	 * @return JFreeChart
	 * 
	 * */
	public JFreeChart getPointJFreeChart(int assessResult0, int assessResult1, int assessResult2);

	/**
	 * 差距评估符合度雷达图
	 * 
	 * @param int , int ,int
	 * @return JFreeChart
	 * 
	 * */
	public JFreeChart getPointRadarJFreeChart(int assessResult0, int assessResult1, int assessResult2);

	/**
	 * 差距评估符合度柱状图
	 * 
	 * @param int , int ,int
	 * @return JFreeChart
	 * 
	 * */
	public JFreeChart getPointFitBarChart(int assessResult0, int assessResult1, int assessResult2);
	
	/**
	 * 历次评估对比柱状图
	 * 
	 * @param List List
	 * @return JFreeChart
	 * 
	 * */
	public JFreeChart getEveryTimePointBarChart(List<DemandCollet> demandColletList , List<Double> douList, String technologyName);

	/**
	 * 历次评估对比折线图
	 * 
	 * @param List
	 * @return JFreeChart
	 * 
	 * */
	public JFreeChart getEveryTimeLineBarChart(List<Technology> technologyList);
}
