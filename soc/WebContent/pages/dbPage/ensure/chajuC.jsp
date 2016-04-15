<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<html>
<head>
<title>差距项分析</title>
<link href="${ctx}/css/basic/css.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${ctx}/scripts/report/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/report/highcharts.js"></script>
<script type="text/javascript" src="${ctx}/scripts/report/exporting.js"></script>
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	function changeAction(elem) {
		//默认是login.action，当select改变时同时改变from的action属性
		//我这里直接把列表的value赋值到form的action，你可以根据需要改改
		var selectValue = document.getElementById('mySelect').listValue;
		var optionVal = $(elem).find("option:selected").text(); // 取到选中的listValue(Option)的值
		var obj = document.getElementById('mySelect');
		var index = obj.selectedIndex; //序号，取当前选中选项的序号 
		var val = obj.options[index].value;
		//alert(val);
		//document.forms[0].action=selectValue;
		location.href = "${ctx}/securityControl/securityControlAnalysis.action?pkca="+ val + "&caName=" + optionVal;
	}

	$(function() {
		var chart;
		var data;
		var chart2;
		var data2;
		$(document)
				.ready(
						function() {
							//默认是login.action，当select改变时同时改变from的action属性
							//我这里直接把列表的value赋值到form的action，你可以根据需要改改
							var selectValue = document
									.getElementById('mySelect').listValue;

							var obj = document.getElementById('mySelect');
							var index = obj.selectedIndex; //序号，取当前选中选项的序号 
							var val = obj.options[index].value;
							var urlpath = "${ctx}/securityControl/queryAjax.action?pkca="
									+ val;
							$.post(urlpath, function(json) {
								data = eval("(" + json + ")");
								chart = new Highcharts.Chart(data);
							});
							var urlpath = "${ctx}/securityControl/queryAjaxZhuXing.action?pkca="
									+ val;

							$.post(urlpath, function(json2) {
								data2 = eval("(" + json2 + ")");
								chart2 = new Highcharts.Chart(data2);
							});

						});

	});
</script>
</head>
<body style="width:99.5%">

	<s:if test="#request.caName==null">
		<s:select list="#request.map" listValue="value" listKey="key"
			label="系统" id="mySelect" onchange="changeAction(this);"
			value="#request.pkca">

		</s:select>
	</s:if>
	<s:else>
		<div>
			<s:select list="#request.map" listValue="value" listKey="key"
				label="系统" id="mySelect" onchange="changeAction(this);"
				value="#request.pkca">
			</s:select>
		</div>
	</s:else>

	<div id="containerChar"
		style="min-width: 400px; height: 400px; margin: 0 auto"></div>
	<div style="display:none">
		<!-- Source: http://or.water.usgs.gov/cgi-bin/grapher/graph_windrose.pl -->
		<table id="freq" border="0" cellspacing="0" cellpadding="0">
			<tr nowrap bgcolor="#CCCCFF">
				<th colspan="2" class="hdr">Table of Frequencies (percent)</th>
			</tr>
			<tr nowrap bgcolor="#CCCCFF">
				<th class="freq">Direction</th>
				<th class="freq">差距项数</th>
				<th class="freq">已整改数</th>
			</tr>
			<tr nowrap>
				<td class="dir">基础网络安全</td>
				<td class="data">${num0}</td>
				<td class="data">${tack0}</td>
			</tr>
			<tr nowrap>
				<td class="dir">边界安全</td>
				<td class="data">${num1}</td>
				<td class="data">${tack1}</td>
			</tr>
			<tr nowrap bgcolor="#DDDDDD">
				<td class="dir">终端系统安全</td>
				<td class="data">${num2}</td>
				<td class="data">${tack2}</td>
			</tr>
			<tr nowrap>
				<td class="dir">服务器端系统安全</td>
				<td class="data">${num3}</td>
				<td class="data">${tack3}</td>
			</tr>
			<tr nowrap bgcolor="#DDDDDD">
				<td class="dir">应用安全</td>
				<td class="data">${num4}</td>
				<td class="data">${tack4}</td>

			</tr>
			<tr nowrap>
				<td class="dir">数据安全与备份恢复</td>
				<td class="data">${num5}</td>
				<td class="data">${tack5}</td>
			</tr>
			<tr nowrap bgcolor="#DDDDDD">
				<td class="dir">安全管理中心</td>
				<td class="data">${num6}</td>
				<td class="data">${tack6}</td>
			</tr>
		</table>
	</div>
	<div>
	<center>
				<h1>安全控制项与任务分布</h1>
			</center>
		<table border="0" width="99.5%" align="center" class="tab2"
			cellpadding="0" cellspacing="1">
			
			<tr align="center">
				<th class="biaoti">项目名称</th>
				<th class="biaoti">基础网络安全</th>
				<th class="biaoti">边界安全</th>
				<th class="biaoti">终端系统安全</th>
				<th class="biaoti">服务器端系统安全</th>
				<th class="biaoti">应用安全</th>
				<th class="biaoti">数据安全与备份恢复</th>
				<th class="biaoti">安全管理中心</th>
			</tr>
			<tr align="center">
				<td>差距项数</td>
				<td>${num0}</td>
				<td>${num1}</td>
				<td>${num2}</td>
				<td>${num3}</td>
				<td>${num4}</td>
				<td>${num5}</td>
				<td>${num6}</td>
			</tr>
			<tr align="center">
				<td>已整改数</td>
				<td>${tack0}</td>
				<td>${tack1}</td>
				<td>${tack2}</td>
				<td>${tack3}</td>
				<td>${tack4}</td>
				<td>${tack5}</td>
				<td>${tack6}</td>
			</tr>
		</table>
	</div>
	<div id="container" style="min-width: 400px; height: 400px; margin: 0 auto"></div>
</body>
</html>
