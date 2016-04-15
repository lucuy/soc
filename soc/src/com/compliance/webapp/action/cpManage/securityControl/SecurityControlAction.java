package com.compliance.webapp.action.cpManage.securityControl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.compliance.model.cpManage.securityControl.Highcharts;
import com.compliance.model.cpManage.summaryAnalysis.ProjectShowcase;
import com.compliance.service.cpManage.securityControl.SecurityControlService;
import com.compliance.service.cpManage.summaryAnalysis.SecurityGapService;
import com.compliance.webapp.action.BaseAction;

public class SecurityControlAction extends BaseAction {

	private SecurityControlService securityControlService;
	private SecurityGapService securityGapService;

	public SecurityGapService getSecurityGapService() {
		return securityGapService;
	}

	public void setSecurityGapService(SecurityGapService securityGapService) {
		this.securityGapService = securityGapService;
	}

	List<ProjectShowcase> listproject;

	public List<ProjectShowcase> getListproject() {
		return listproject;
	}

	public void setListproject(List<ProjectShowcase> listproject) {
		this.listproject = listproject;
	}

	public SecurityControlService getSecurityControlService() {
		return securityControlService;
	}

	public void setSecurityControlService(
			SecurityControlService securityControlService) {
		this.securityControlService = securityControlService;
	}

	public String getSecurityControl() {

		DecimalFormat df1 = new DecimalFormat("00.00");
		HttpServletRequest request = super.getRequest();
		String pkca1 = request.getParameter("pkca");
		int pkca = Integer.parseInt(pkca1);

		List<ProjectShowcase> listproject2 = securityGapService
				.getProjectShowcaseByName(pkca);

		for (ProjectShowcase projectShowcase : listproject2) {
			// 三级
			if (projectShowcase.getCasysGrade().equals("第三级")) {

				List<Integer> list = securityControlService
						.getSecurityControl3(pkca);
				request.setAttribute("num0", list.get(0));
				request.setAttribute("num1", list.get(1));
				request.setAttribute("num2", list.get(2));
				request.setAttribute("num3", list.get(3));
				request.setAttribute("num4", list.get(4));
				request.setAttribute("num5", list.get(5));
				request.setAttribute("num6", list.get(6));

				double percentage0 = ((double) list.get(0) / 508) * 100;
				double percentage1 = ((double) list.get(1) / 508) * 100;
				double percentage2 = ((double) list.get(2) / 508) * 100;
				double percentage3 = ((double) list.get(3) / 508) * 100;
				double percentage4 = ((double) list.get(4) / 508) * 100;
				double percentage5 = ((double) list.get(5) / 508) * 100;
				double percentage6 = ((double) list.get(6) / 508) * 100;

				request.setAttribute("percentage0", df1.format(percentage0));
				request.setAttribute("percentage1", df1.format(percentage1));
				request.setAttribute("percentage2", df1.format(percentage2));
				request.setAttribute("percentage3", df1.format(percentage3));
				request.setAttribute("percentage4", df1.format(percentage4));
				request.setAttribute("percentage5", df1.format(percentage5));
				request.setAttribute("percentage6", df1.format(percentage6));

				List<Integer> listTack3 = securityControlService
						.getControlTask3(String.valueOf(projectShowcase
								.getPkca()));
				request.setAttribute("tack0", listTack3.get(0));
				request.setAttribute("tack1", listTack3.get(1));
				request.setAttribute("tack2", listTack3.get(2));
				request.setAttribute("tack3", listTack3.get(3));
				request.setAttribute("tack4", listTack3.get(4));
				request.setAttribute("tack5", listTack3.get(5));
				request.setAttribute("tack6", listTack3.get(6));

			}

			// 四级
			if (projectShowcase.getCasysGrade().equals("第四级")) {
				List<Integer> list = securityControlService
						.getSecurityControl4(pkca);
				request.setAttribute("num0", list.get(0));
				request.setAttribute("num1", list.get(1));
				request.setAttribute("num2", list.get(2));
				request.setAttribute("num3", list.get(3));
				request.setAttribute("num4", list.get(4));
				request.setAttribute("num5", list.get(5));
				request.setAttribute("num6", list.get(6));

				double percentage0 = ((double) list.get(0) / 508) * 100;
				double percentage1 = ((double) list.get(1) / 508) * 100;
				double percentage2 = ((double) list.get(2) / 508) * 100;
				double percentage3 = ((double) list.get(3) / 508) * 100;
				double percentage4 = ((double) list.get(4) / 508) * 100;
				double percentage5 = ((double) list.get(5) / 508) * 100;
				double percentage6 = ((double) list.get(6) / 508) * 100;

				request.setAttribute("percentage0", df1.format(percentage0));
				request.setAttribute("percentage1", df1.format(percentage1));
				request.setAttribute("percentage2", df1.format(percentage2));
				request.setAttribute("percentage3", df1.format(percentage3));
				request.setAttribute("percentage4", df1.format(percentage4));
				request.setAttribute("percentage5", df1.format(percentage5));
				request.setAttribute("percentage6", df1.format(percentage6));

				List<Integer> listTack4 = securityControlService
						.getControlTask4(String.valueOf(projectShowcase
								.getPkca()));
				request.setAttribute("tack0", listTack4.get(0));
				request.setAttribute("tack1", listTack4.get(1));
				request.setAttribute("tack2", listTack4.get(2));
				request.setAttribute("tack3", listTack4.get(3));
				request.setAttribute("tack4", listTack4.get(4));
				request.setAttribute("tack5", listTack4.get(5));
				request.setAttribute("tack6", listTack4.get(6));

			}
			// 二级
			if (projectShowcase.getCasysGrade().equals("第二级")) {
				List<Integer> list = securityControlService
						.getSecurityControl2(pkca);
				request.setAttribute("num0", list.get(0));
				request.setAttribute("num1", list.get(1));
				request.setAttribute("num2", list.get(2));
				request.setAttribute("num3", list.get(3));
				request.setAttribute("num4", list.get(4));
				request.setAttribute("num5", list.get(5));
				request.setAttribute("num6", list.get(6));

				double percentage0 = ((double) list.get(0) / 508) * 100;
				double percentage1 = ((double) list.get(1) / 508) * 100;
				double percentage2 = ((double) list.get(2) / 508) * 100;
				double percentage3 = ((double) list.get(3) / 508) * 100;
				double percentage4 = ((double) list.get(4) / 508) * 100;
				double percentage5 = ((double) list.get(5) / 508) * 100;
				double percentage6 = ((double) list.get(6) / 508) * 100;

				request.setAttribute("percentage0", df1.format(percentage0));
				request.setAttribute("percentage1", df1.format(percentage1));
				request.setAttribute("percentage2", df1.format(percentage2));
				request.setAttribute("percentage3", df1.format(percentage3));
				request.setAttribute("percentage4", df1.format(percentage4));
				request.setAttribute("percentage5", df1.format(percentage5));
				request.setAttribute("percentage6", df1.format(percentage6));

				List<Integer> listTack2 = securityControlService
						.getControlTask2(String.valueOf(projectShowcase
								.getPkca()));
				request.setAttribute("tack0", listTack2.get(0));
				request.setAttribute("tack1", listTack2.get(1));
				request.setAttribute("tack2", listTack2.get(2));
				request.setAttribute("tack3", listTack2.get(3));
				request.setAttribute("tack4", listTack2.get(4));
				request.setAttribute("tack5", listTack2.get(5));
				request.setAttribute("tack6", listTack2.get(6));
			}
		}

		listproject = securityGapService.getProjectShowcase();
		Map<Integer, String> map = new HashMap<Integer, String>();
		for (ProjectShowcase projectShowcase : listproject) {
			map.put(projectShowcase.getPkca(), projectShowcase.getCaName());
		}

		request.setAttribute("map", map);
		request.setAttribute("pkca", pkca);

		return SUCCESS;
	}

	public String getProjectShowcase() {
		String result = "";
		listproject = securityGapService.getProjectShowcase();
		HttpServletRequest request = super.getRequest();
		request.setAttribute("listss", listproject);
		if (listproject.size() == 0) {
			result = NONE;
		} else {
			result = SUCCESS;
		}

		return result;

	}

	public void queryAjax() {
		HttpServletRequest request = super.getRequest();
		String pkca1 = request.getParameter("pkca");
		int pkca = Integer.parseInt(pkca1);

		List<ProjectShowcase> listproject2 = securityGapService
				.getProjectShowcaseByName(pkca);

		for (ProjectShowcase projectShowcase : listproject2) {
			// 三级
			if (projectShowcase.getCasysGrade().equals("第三级")) {

				List<Integer> list = securityControlService
						.getSecurityControl3(pkca);
				List<Integer> listTack3 = securityControlService
						.getControlTask3(String.valueOf(projectShowcase
								.getPkca()));

				HttpServletResponse response = super.getResponse();

				response.setCharacterEncoding("UTF-8");
				response.setHeader("Pragma", "No-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);

				try {
					String json = "{chart: {renderTo: 'containerChar', plotBackgroundColor: null, plotBorderWidth: null, plotShadow: false},title: {text: '差距项数饼图'}, tooltip: { formatter: function() {return '<b>'+ this.point.name +'</b>('+this.y+'): '+((this.y/508)*100).toFixed(1)+' %';},percentageDecimals: 1},plotOptions: {pie: {allowPointSelect: true,cursor: 'pointer',dataLabels: {enabled: true,color: '#000000',connectorColor: '#000000',formatter: function() {return '<b>'+ this.point.name +'</b>('+this.y+'): '+((this.y/508)*100).toFixed(1)+' %';}},showInLegend: true}},series: [{type: 'pie',name: 'share',data: [";

					json += "['基础网络安全'," + list.get(0) + "],['边界安全',"
							+ list.get(1) + "],['终端系统安全', " + list.get(2)
							+ "],['服务器端系统安全'," + list.get(3) + "],['应用安全',"
							+ list.get(4) + "],['数据安全与备份恢复'," + list.get(5)
							+ "],['安全管理中心'," + list.get(6) + "]]}]}";
					//System.out.println(json);
					response.getWriter().write(json);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			// 四级
			if (projectShowcase.getCasysGrade().equals("第四级")) {
				List<Integer> list = securityControlService
						.getSecurityControl4(pkca);

				List<Integer> listTack4 = securityControlService
						.getControlTask4(String.valueOf(projectShowcase
								.getPkca()));

				HttpServletResponse response = super.getResponse();

				response.setCharacterEncoding("UTF-8");
				response.setHeader("Pragma", "No-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);

				try {
					String json = "{chart: {renderTo: 'containerChar', plotBackgroundColor: null, plotBorderWidth: null, plotShadow: false},title: {text: '差距项数饼图'}, tooltip: { formatter: function() {return '<b>'+ this.point.name +'</b>('+this.y+'): '+((this.y/508)*100).toFixed(1)+' %';},percentageDecimals: 1},plotOptions: {pie: {allowPointSelect: true,cursor: 'pointer',dataLabels: {enabled: true,color: '#000000',connectorColor: '#000000',formatter: function() {return '<b>'+ this.point.name +'</b>('+this.y+'): '+ ((this.y/508)*100).toFixed(1) +' %';}},showInLegend: true}},series: [{type: 'pie',name: 'share',data: [";

					json += "['基础网络安全'," + list.get(0).toString()+ "],['边界安全',"
							+ list.get(1) + "],['终端系统安全', " + list.get(2)
							+ "],['服务器端系统安全'," + list.get(3) + "],['应用安全',"
							+ list.get(4) + "],['数据安全与备份恢复'," + list.get(5)
							+ "],['安全管理中心'," + list.get(6) + "]]}]}";

					//System.out.println(json);
					response.getWriter().write(json);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// 二级
			if (projectShowcase.getCasysGrade().equals("第二级")) {
				List<Integer> list = securityControlService.getSecurityControl2(pkca);
				List<Integer> listTack2 = securityControlService
						.getControlTask2(String.valueOf(projectShowcase
								.getPkca()));

				HttpServletResponse response = super.getResponse();

				response.setCharacterEncoding("UTF-8");
				response.setHeader("Pragma", "No-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);

				try {
					String json = "{chart: {renderTo: 'containerChar', plotBackgroundColor: null, plotBorderWidth: null, plotShadow: false},title: {text: '差距项数饼图'}, tooltip: { formatter: function() {return '<b>'+ this.point.name +'</b>('+this.y+'): '+((this.y/508)*100).toFixed(1)+' %';},percentageDecimals: 1},plotOptions: {pie: {allowPointSelect: true,cursor: 'pointer',dataLabels: {enabled: true,color: '#000000',connectorColor: '#000000',formatter: function() {return '<b>'+ this.point.name +'</b>('+this.y+'): '+ ((this.y/508)*100).toFixed(1) +' %';}},showInLegend: true}},series: [{type: 'pie',name: 'share',data: [";

					json += "['基础网络安全'," + list.get(0) + "],['边界安全',"
							+ list.get(1) + "],['终端系统安全', " + list.get(2)
							+ "],['服务器端系统安全'," + list.get(3) + "],['应用安全',"
							+ list.get(4) + "],['数据安全与备份恢复'," + list.get(5)
							+ "],['安全管理中心'," + list.get(6) + "]]}]}";
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
			String json = "{chart: {renderTo: 'containerChar', plotBackgroundColor: null, plotBorderWidth: null, plotShadow: false},title: {text: '差距项数饼图'}, tooltip: { formatter: function() {return '<b>'+ this.point.name +'</b>('+this.y+'): '+((this.y/508)*100).toFixed(1)+' %';},percentageDecimals: 1},plotOptions: {pie: {allowPointSelect: true,cursor: 'pointer',dataLabels: {enabled: true,color: '#000000',connectorColor: '#000000',formatter: function() {return '<b>'+ this.point.name +'</b>('+this.y+'): '+((this.y/508)*100).toFixed(1)+' %';}},showInLegend: true}},series: [{type: 'pie',name: 'share',data: [";

			json += "['基础网络安全'," + 0 + "],['边界安全'," + 0 + "],['终端系统安全', " + 0
					+ "],['服务器端系统安全'," + 0 + "],['应用安全'," + 0
					+ "],['数据安全与备份恢复'," + 0 + "],['安全管理中心'," + 0 + "]]}]}";
			//System.out.println(json);
			response.getWriter().write(json);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return;
	}

	public void queryAjaxZhuXingEquelsNull() {

		HttpServletResponse response = super.getResponse();

		response.setCharacterEncoding("UTF-8");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		try {
			String json2 = "{chart: { renderTo: 'container',type: 'column'},title: {text: '安全控制-差距项数和任务分布情况'},xAxis: {categories: ['基础网络安全', '边界安全', '终端系统安全', '服务器端系统安全', '应用安全','数据安全与备份恢复','安全管理中心']},yAxis: {min: 0,title: { text: '',align: 'high'}},legend: { layout: 'vertical', backgroundColor: '#FFFFFF',align: 'left',verticalAlign: 'top',x: 100,  y: 70,floating: true, shadow: true},tooltip: {formatter: function() {return ''+this.x +': '+ this.y ;}}, plotOptions: {  column: { pointPadding: 0.2, borderWidth: 0 }}, series: [{name: '差距项数',data: [";
			json2 += 0 + "," + 0 + "," + 0 + "," + 0 + "," + 0 + "," + 0 + ","
					+ 0 + "]}, {name: '任务数',data: [";
			json2 += 0 + "," + 0 + "," + 0 + "," + 0 + "," + 0 + "," + 0 + ","
					+ 0 + "]}] }";
			response.getWriter().write(json2);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}

	public void queryAjaxZhuXing() {
		HttpServletRequest request = super.getRequest();
		String pkca1 = request.getParameter("pkca");
		int pkca = Integer.parseInt(pkca1);

		List<ProjectShowcase> listproject2 = securityGapService
				.getProjectShowcaseByName(pkca);

		for (ProjectShowcase projectShowcase : listproject2) {
			// 三级
			if (projectShowcase.getCasysGrade().equals("第三级")) {

				List<Integer> list = securityControlService
						.getSecurityControl3(pkca);
				List<Integer> listTack3 = securityControlService
						.getControlTask3(String.valueOf(projectShowcase
								.getPkca()));

				HttpServletResponse response = super.getResponse();

				response.setCharacterEncoding("UTF-8");
				response.setHeader("Pragma", "No-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);

				try {
					String json2 = "{chart: { renderTo: 'container',type: 'column'},title: {text: '安全控制-差距项数和任务分布情况'},xAxis: {categories: ['基础网络安全', '边界安全', '终端系统安全', '服务器端系统安全', '应用安全','数据安全与备份恢复','安全管理中心']},yAxis: {min: 0,title: { text: '',align: 'high'}},legend: { layout: 'vertical', backgroundColor: '#FFFFFF',align: 'left',verticalAlign: 'top',x: 100,  y: 70,floating: true, shadow: true},tooltip: {formatter: function() {return ''+this.x +': '+ this.y ;}}, plotOptions: {  column: { pointPadding: 0.2, borderWidth: 0 }}, series: [{name: '差距项数',data: [";
					json2 += list.get(0) + "," + list.get(1) + ","
							+ list.get(2) + "," + list.get(3) + ","
							+ list.get(4) + "," + list.get(5) + ","
							+ list.get(6) + "]}, {name: '已整改数',data: [";
					json2 += listTack3.get(0) + "," + listTack3.get(1) + ","
							+ listTack3.get(2) + "," + listTack3.get(3) + ","
							+ listTack3.get(4) + "," + listTack3.get(5) + ","
							+ listTack3.get(6) + "]}] }";
					response.getWriter().write(json2);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			// 四级
			if (projectShowcase.getCasysGrade().equals("第四级")) {
				List<Integer> list = securityControlService
						.getSecurityControl4(pkca);

				List<Integer> listTack4 = securityControlService
						.getControlTask4(String.valueOf(projectShowcase
								.getPkca()));

				HttpServletResponse response = super.getResponse();

				response.setCharacterEncoding("UTF-8");
				response.setHeader("Pragma", "No-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);

				try {

					String json2 = "{chart: { renderTo: 'container',type: 'column'},title: {text: '安全控制-差距项数和任务分布情况'},xAxis: {categories: ['基础网络安全', '边界安全', '终端系统安全', '服务器端系统安全', '应用安全','数据安全与备份恢复','安全管理中心']},yAxis: {min: 0,title: { text: '',align: 'high'}},legend: { layout: 'vertical', backgroundColor: '#FFFFFF',align: 'left',verticalAlign: 'top',x: 100,  y: 70,floating: true, shadow: true},tooltip: {formatter: function() {return ''+this.x +': '+ this.y ;}}, plotOptions: {  column: { pointPadding: 0.2, borderWidth: 0 }}, series: [{name: '差距项数',data: [";
					json2 += list.get(0) + "," + list.get(1) + ","
							+ list.get(2) + "," + list.get(3) + ","
							+ list.get(4) + "," + list.get(5) + ","
							+ list.get(6) + "]}, {name: '任务数',data: [";
					json2 += listTack4.get(0) + "," + listTack4.get(1) + ","
							+ listTack4.get(2) + "," + listTack4.get(3) + ","
							+ listTack4.get(4) + "," + listTack4.get(5) + ","
							+ listTack4.get(6) + "]}] }";
					response.getWriter().write(json2);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// 二级
			if (projectShowcase.getCasysGrade().equals("第二级")) {
				List<Integer> list = securityControlService
						.getSecurityControl2(pkca);
				List<Integer> listTack2 = securityControlService
						.getControlTask2(String.valueOf(projectShowcase
								.getPkca()));

				HttpServletResponse response = super.getResponse();

				response.setCharacterEncoding("UTF-8");
				response.setHeader("Pragma", "No-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);

				try {
					String json2 = "{chart: { renderTo: 'container',type: 'column'},title: {text: '安全控制-差距项数和任务分布情况'},xAxis: {categories: ['基础网络安全', '边界安全', '终端系统安全', '服务器端系统安全', '应用安全','数据安全与备份恢复','安全管理中心']},yAxis: {min: 0,title: { text: '',align: 'high'}},legend: { layout: 'vertical', backgroundColor: '#FFFFFF',align: 'left',verticalAlign: 'top',x: 100,  y: 70,floating: true, shadow: true},tooltip: {formatter: function() {return ''+this.x +': '+ this.y ;}}, plotOptions: {  column: { pointPadding: 0.2, borderWidth: 0 }}, series: [{name: '差距项数',data: [";
					json2 += list.get(0) + "," + list.get(1) + ","
							+ list.get(2) + "," + list.get(3) + ","
							+ list.get(4) + "," + list.get(5) + ","
							+ list.get(6) + "]}, {name: '任务数',data: [";
					json2 += listTack2.get(0) + "," + listTack2.get(1) + ","
							+ listTack2.get(2) + "," + listTack2.get(3) + ","
							+ listTack2.get(4) + "," + listTack2.get(5) + ","
							+ listTack2.get(6) + "]}] }";
					response.getWriter().write(json2);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return;
	}

}
