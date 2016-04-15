package com.compliance.webapp.action.cpManage.gapAnalysisSchedule;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.compliance.model.cpManage.summaryAnalysis.ProjectShowcase;
import com.compliance.service.cpManage.gapAnalysisSchedule.GapScheduleService;
import com.compliance.service.cpManage.summaryAnalysis.SecurityGapService;
import com.compliance.webapp.action.BaseAction;

public class GapScheduleAction extends BaseAction {
	private GapScheduleService gapScheduleService;
	private SecurityGapService securityGapService;
	List<ProjectShowcase> listproject;

	public List<ProjectShowcase> getListproject() {
		return listproject;
	}

	public void setListproject(List<ProjectShowcase> listproject) {
		this.listproject = listproject;
	}

	public SecurityGapService getSecurityGapService() {
		return securityGapService;
	}

	public void setSecurityGapService(SecurityGapService securityGapService) {
		this.securityGapService = securityGapService;
	}

	public GapScheduleService getGapScheduleService() {
		return gapScheduleService;
	}

	public void setGapScheduleService(GapScheduleService gapScheduleService) {
		this.gapScheduleService = gapScheduleService;
	}

	public String getGapSchedule() {
		DecimalFormat df1 = new DecimalFormat("00.00");
		HttpServletRequest request = super.getRequest();
		String pkca1 = request.getParameter("pkca");
		int pkca = Integer.parseInt(pkca1);
		List<ProjectShowcase> listproject2 = gapScheduleService.getJinDuProjectShowcaseByName(pkca);

		for (ProjectShowcase projectShowcase : listproject2) {
			if (projectShowcase.getCasysGrade().equals("第二级")) {
				List<Integer> list2 = gapScheduleService
						.getGapScheduleTwoLevel(pkca);
				int sum2 = list2.get(0) + list2.get(1) + list2.get(2)
						+ list2.get(3) + list2.get(4) + list2.get(5)
						+ list2.get(6);

				// double percentage2 = (double) sum2 / 30;

				double percentage22 = 30 - sum2;
				// request.setAttribute("percentage0", df1.format(percentage2));
				// double d2 = 100 - percentage2;
				// request.setAttribute("percentage1", df1.format(d2));
			}

			if (projectShowcase.getCasysGrade().equals("第三级")) {
				List<Integer> list3 = gapScheduleService
						.getGapScheduleThreeLevel(pkca);
				int sum3 = list3.get(0) + list3.get(1) + list3.get(2)
						+ list3.get(3) + list3.get(4) + list3.get(5)
						+ list3.get(6);
				// double percentage3 = (double) sum3 / 34;

				double percentage33 = 34 - sum3;

				// request.setAttribute("percentage0", df1.format(percentage3));
				// double d3 = 100 - percentage3;
				// request.setAttribute("percentage1", df1.format(d3));
			}

			if (projectShowcase.getCasysGrade().equals("第四级")) {
				List<Integer> list4 = gapScheduleService
						.getGapScheduleFourLevel(pkca);
				int sum4 = list4.get(0) + list4.get(1) + list4.get(2)
						+ list4.get(3) + list4.get(4) + list4.get(5)
						+ list4.get(6);
				// double percentage4 = (double) sum4 / 34;
				// request.setAttribute("percentage0", df1.format(percentage4));
				// double d4 = 100 - percentage4;
				// request.setAttribute("percentage1", df1.format(d4));

				double percentage44 = 34 - sum4;
			}
		}

		listproject = gapScheduleService.getProjectShowcase();
		Map<Integer, String> map = new HashMap<Integer, String>();
		for (ProjectShowcase projectShowcase : listproject) {
			map.put(projectShowcase.getPkca(), projectShowcase.getCaName());
		}

		request.setAttribute("map", map);
		request.setAttribute("pkca", pkca);

		return SUCCESS;
	}

	public void queryAjax() {

		HttpServletRequest request = super.getRequest();
		String pkca1 = request.getParameter("pkca");
		int pkca = Integer.parseInt(pkca1);
		List<ProjectShowcase> listproject2 = gapScheduleService
				.getJinDuProjectShowcaseByName(pkca);

		for (ProjectShowcase projectShowcase : listproject2) {
			if (projectShowcase.getCasysGrade().equals("第二级")) {
				List<Integer> list2 = gapScheduleService
						.getGapScheduleTwoLevel(pkca);
				int sum2 = list2.get(0) + list2.get(1) + list2.get(2)
						+ list2.get(3) + list2.get(4) + list2.get(5)
						+ list2.get(6);

				// double percentage2 = (double) sum2 / 30;

				double percentage22 = 30 - sum2;
				// request.setAttribute("percentage0", df1.format(percentage2));
				// double d2 = 100 - percentage2;
				// request.setAttribute("percentage1", df1.format(d2));

				HttpServletResponse response = super.getResponse();

				response.setCharacterEncoding("UTF-8");
				response.setHeader("Pragma", "No-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);

				try {
					String json = "{chart: {renderTo: 'container', plotBackgroundColor: null, plotBorderWidth: null, plotShadow: false},title: {text: '差距分析总体进度饼图'}, tooltip: {pointFormat: '{series.name}: <b>{point.percentage}%</b>',percentageDecimals: 1},plotOptions: {pie: {allowPointSelect: true,cursor: 'pointer',dataLabels: {enabled: true,color: '#000000',connectorColor: '#000000',formatter: function() {return '<b>'+ this.point.name +'</b>('+this.y+'): '+((this.y/30)*100).toFixed(1)+' %';}},showInLegend: true}},series: [{type: 'pie',name: '占比',data: [";

					json += "['已完成'," + sum2 + "],['未完成'," + percentage22
							+ "]]}]}";
					//System.out.println(json);
					response.getWriter().write(json);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			if (projectShowcase.getCasysGrade().equals("第三级")) {
				List<Integer> list3 = gapScheduleService
						.getGapScheduleThreeLevel(pkca);
				int sum3 = list3.get(0) + list3.get(1) + list3.get(2)
						+ list3.get(3) + list3.get(4) + list3.get(5)
						+ list3.get(6);
				// double percentage3 = (double) sum3 / 34;

				double percentage33 = 34 - sum3;
				HttpServletResponse response = super.getResponse();

				response.setCharacterEncoding("UTF-8");
				response.setHeader("Pragma", "No-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);

				try {
					String json = "{chart: {renderTo: 'container', plotBackgroundColor: null, plotBorderWidth: null, plotShadow: false},title: {text: '差距分析总体进度饼图'}, tooltip: { pointFormat: '{series.name}: <b>{point.percentage}%</b>',percentageDecimals: 1},plotOptions: {pie: {allowPointSelect: true,cursor: 'pointer',dataLabels: {enabled: true,color: '#000000',connectorColor: '#000000',formatter: function() {return '<b>'+ this.point.name +'</b>('+this.y+'): '+((this.y/34)*100).toFixed(1)+' %';}},showInLegend: true}},series: [{type: 'pie',name: '占比',data: [";

					json += "['已完成'," + sum3 + "],['未完成'," + percentage33
							+ "]]}]}";
					//System.out.println(json);
					response.getWriter().write(json);
				} catch (Exception e) {
					e.printStackTrace();
				}

				// request.setAttribute("percentage0", df1.format(percentage3));
				// double d3 = 100 - percentage3;
				// request.setAttribute("percentage1", df1.format(d3));
			}

			if (projectShowcase.getCasysGrade().equals("第四级")) {
				List<Integer> list4 = gapScheduleService
						.getGapScheduleFourLevel(pkca);
				int sum4 = list4.get(0) + list4.get(1) + list4.get(2)
						+ list4.get(3) + list4.get(4) + list4.get(5)
						+ list4.get(6);
				// double percentage4 = (double) sum4 / 34;
				// request.setAttribute("percentage0", df1.format(percentage4));
				// double d4 = 100 - percentage4;
				// request.setAttribute("percentage1", df1.format(d4));

				double percentage44 = 34 - sum4;

				HttpServletResponse response = super.getResponse();

				response.setCharacterEncoding("UTF-8");
				response.setHeader("Pragma", "No-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);

				try {
					String json = "{chart: {renderTo: 'container', plotBackgroundColor: null, plotBorderWidth: null, plotShadow: false},title: {text: '差距分析总体进度饼图'}, tooltip: { pointFormat: '{series.name}: <b>{point.percentage}%</b>',percentageDecimals: 1},plotOptions: {pie: {allowPointSelect: true,cursor: 'pointer',dataLabels: {enabled: true,color: '#000000',connectorColor: '#000000',formatter: function() {return '<b>'+ this.point.name +'</b>('+this.y+'): '+((this.y/34)*100).toFixed(1)+' %';}},showInLegend: true}},series: [{type: 'pie',name: '占比',data: [";

					json += "['已完成'," + sum4 + "],['未完成'," + percentage44
							+ "]]}]}";
					//System.out.println(json);
					response.getWriter().write(json);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}
		return;

	}

	public void queryAjaxEquelsNull() {

		HttpServletResponse response = super.getResponse();

		response.setCharacterEncoding("UTF-8");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		try {
			String json = "{chart: {renderTo: 'container', plotBackgroundColor: null, plotBorderWidth: null, plotShadow: false},title: {text: '差距分析总体进度饼图'}, tooltip: {pointFormat: '{series.name}: <b>{point.percentage}%</b>',percentageDecimals: 1},plotOptions: {pie: {allowPointSelect: true,cursor: 'pointer',dataLabels: {enabled: true,color: '#000000',connectorColor: '#000000',formatter: function() {return '<b>'+ this.point.name +'</b>('+this.y+'): '+(this.y/30)*100+' %';}},showInLegend: true}},series: [{type: 'pie',name: '占比',data: [";

			json += "['已完成'," + 0 + "],['未完成'," + 0 + "]]}]}";
			//System.out.println(json);
			response.getWriter().write(json);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return;

	}

	public String getProjectShowcase() {
		String result = "";
		listproject = gapScheduleService.getProjectShowcase();
		HttpServletRequest request = super.getRequest();
		request.setAttribute("listss", listproject);

		if (listproject.size() == 0) {
			result = NONE;
		} else {
			result = SUCCESS;
		}

		return result;

	}

}
