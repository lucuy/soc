package com.compliance.webapp.action.rank;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import com.compliance.model.rank.RankReport;
import com.compliance.service.rank.RankReportService;
import com.compliance.webapp.action.BaseAction;

public class RankReportAction extends BaseAction {

	private RankReportService rankReportService;
	private List<RankReport> rankReportList;
	
	
	//查询统计报表 
	public String query(){
		HttpServletRequest request = super.getRequest();
		HttpServletResponse response = super.getResponse();
		response.setCharacterEncoding("UTF-8");
		
		rankReportList = rankReportService.query();
	
		return SUCCESS;
	}
	
	
	
	//查询统计报表 饼状图
	public void queryAjax(){
		HttpServletRequest request = super.getRequest();
		HttpServletResponse response = super.getResponse();
		
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Pragma","No-cache"); 
		response.setHeader("Cache-Control","no-cache"); 
		response.setDateHeader("Expires", 0);
		
		try {
			String json = "{chart: {renderTo: 'container', plotBackgroundColor: null, plotBorderWidth: null, plotShadow: false},title: {text: '信息系统安全等级分布饼图'}, tooltip: { pointFormat: '{series.name}: <b>{point.percentage}%</b>',percentageDecimals: 1},plotOptions: {pie: {allowPointSelect: true,cursor: 'pointer',dataLabels: {enabled: true,color: '#000000',connectorColor: '#000000',formatter: function() {return '<b>'+ this.point.name +'</b>('+this.y+'): '+ this.percentage.toFixed(1) +' %';}},showInLegend: true}},series: [{type: 'pie',name: 'share',data: [";//['未定级',100.0]]}]}";	 
			rankReportList = rankReportService.query();
			int oneGrade = 0;
			int twoGrade = 0;
			int threeGrade = 0;
			int fourGrade = 0;
			int fiveGrade = 0;
			int other = 0;
			if(rankReportList.size() != 0){
				for(RankReport rr : rankReportList){
					if("第一级".equals(rr.getGrade())){
						oneGrade++;
					}else if("第二级".equals(rr.getGrade())){
						twoGrade++;
					}else if("第三级".equals(rr.getGrade())){
						threeGrade++;
					}else if("第四级".equals(rr.getGrade())){
						fourGrade++;
					}else if("第五级".equals(rr.getGrade())){
						fiveGrade++;
					}else{			
						other++;
					}
				}
				
				json +=	"['未定级',"+ other +"],['一级',"+ oneGrade +"],['二级', "+ twoGrade +"],['三级',"+ threeGrade +"],['四级',"+  fourGrade +"],['五级',"+ fiveGrade +"]]}]}";
				
			}else{
				json +="['未定级',0]]}]}";
			}
			//System.out.println(json);
			response.getWriter().write(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}
	
	
	
	
	public List<RankReport> getRankReportList() {
		return rankReportList;
	}

	public void setRankReportList(List<RankReport> rankReportList) {
		this.rankReportList = rankReportList;
	}

	public RankReportService getRankReportService() {
		return rankReportService;
	}

	public void setRankReportService(RankReportService rankReportService) {
		this.rankReportService = rankReportService;
	}
	
	

}
