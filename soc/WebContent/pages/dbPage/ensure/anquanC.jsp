<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<html>
<head>
<title>安全域分布</title>
<link href="${ctx}/styles/user/user.css" rel="stylesheet"
	type="text/css">
 <link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet" type="text/css">
<%-- <link href="${ctx}/styles/jquery-ui-1.8.21.css" rel="stylesheet"
	type="text/css"> --%>
<link href="${ctx}/styles/jquery.popuplayer.css" rel="stylesheet"
	type="text/css">
<script type="text/javascript" src="${ctx}/scripts/report/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/report/highcharts.js"></script>
<script type="text/javascript" src="${ctx}/scripts/report/exporting.js"></script>
<script type="text/javascript" src="${ctx}/scripts/report/data.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/report/highcharts-more.js"></script>
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<style type="text/css">
     .biaoti{
           font-size: 12px ;
           font-weight: bold;
          
     }


</style>
<script type="text/javascript">
	function changeAction(elem) {
		//默认是login.action，当select改变时同时改变from的action属性
		//我这里直接把列表的value赋值到form的action，你可以根据需要改改
		//var selectValue = document.getElementById('mySelect').listValue;
		var optionVal = $(elem).find("option:selected").text(); // 取到选中的listValue(Option)的值

		var obj = document.getElementById('mySelect');
		var index = obj.selectedIndex; //序号，取当前选中选项的序号 
		var val = obj.options[index].value;
		//alert(val);
		//document.forms[0].action=selectValue;
		location.href = "${ctx}/summaryAnalysis/securityGapC.action?pkca="
				+ val + "&caName=" + optionVal;
	}

	$(function() {

		// Parse the data from an inline table using the Highcharts Data plugin
		Highcharts.data({
			table : 'freq',
			startRow : 1,
			endRow : 10,
			endColumn : 4,

			complete : function(options) {

				// Some further processing of the options
				options.series.reverse(); // to get the stacking right

				// Create the chart
				window.chart = new Highcharts.Chart(Highcharts.merge(options, {

					chart : {
						renderTo : 'container',
						polar : true,
						type : 'column'
					},

					title : {
						text : '<h1>安全差距分析</h1>',
						verticalAlign : 'top',
						x : -50,
						y : 5

					},

					subtitle : {
						text : ''
					},

					pane : {
						size : '60%'
					},

					legend : {
						reversed : true,
						align : 'right',
						verticalAlign : 'top',
						y : 20,
						layout : 'vertical'
					},

					xAxis : {
						tickmarkPlacement : 'on'
					},

					yAxis : {
						min : 0,
						endOnTick : false,
						showLastLabel : true,
						title : {
							text : 'Frequency (%)'
						},
						labels : {
							formatter : function() {
								return this.value;
							}
						}
					},

					tooltip : {
						valueSuffix : '%'
					},

					plotOptions : {
						series : {
							stacking : 'normal',
							shadow : false,
							groupPadding : 0,
							pointPlacement : 'on'
						}
					}
				}));

			}
		});
	});
</script>


</head>
<body style="background-color: #FFFFFF">

		<div>
			<s:select list="#request.map" listValue="value" listKey="key"
				label="系统" id="mySelect" onchange="changeAction(this);"
				value="#request.pkca">

			</s:select>

		</div>

		<div id="container"
			style="width: 850px; height: 600px; margin: 0 auto;"></div>

		<div style="display:none">
			<!-- Source: http://or.water.usgs.gov/cgi-bin/grapher/graph_windrose.pl -->
			<table id="freq" border="0" cellspacing="0" cellpadding="0" style="font-size: 12px;">
				<tr nowrap bgcolor="#CCCCFF">
					<th colspan="2" class="hdr">Table of Frequencies (percent)</th>
				</tr>
				<tr nowrap bgcolor="#CCCCFF">
					<th class="freq">Direction</th>
					<th class="freq">满足</th>
					<th class="freq">部分满足</th>
					<th class="freq">不满足</th>
					<th class="freq">不适用</th>
					<th class="freq">Total</th>

				</tr>
				<tr nowrap>
					<td class="dir">基础网络安全</td>
					<td class="data">${percentage0}</td>
					<td class="data">${percentage1}</td>
					<td class="data">${percentage2}</td>
					<td class="data">${percentage3}</td>
					<td class="data">100</td>
				</tr>
				<tr nowrap>
					<td class="dir">边界安全</td>
					<td class="data">${percentage4}</td>
					<td class="data">${percentage5}</td>
					<td class="data">${percentage6}</td>
					<td class="data">${percentage7}</td>
					<td class="data">100</td>
				</tr>
				<tr nowrap bgcolor="#DDDDDD">
					<td class="dir">终端系统安全</td>
					<td class="data">${percentage8}</td>
					<td class="data">${percentage9}</td>
					<td class="data">${percentage10}</td>
					<td class="data">${percentage11}</td>
					<td class="data">100</td>
				</tr>
				<tr nowrap>
					<td class="dir">服务器端系统安全</td>
					<td class="data">${percentage12}</td>
					<td class="data">${percentage13}</td>
					<td class="data">${percentage14}</td>
					<td class="data">${percentage15}</td>
					<td class="data">100</td>
				</tr>
				<tr nowrap bgcolor="#DDDDDD">
					<td class="dir">应用安全</td>
					<td class="data">${percentage16}</td>
					<td class="data">${percentage17}</td>
					<td class="data">${percentage18}</td>
					<td class="data">${percentage19}</td>
					<td class="data">100</td>

				</tr>
				<tr nowrap>
					<td class="dir">数据安全与备份恢复</td>
					<td class="data">${percentage20}</td>
					<td class="data">${percentage21}</td>
					<td class="data">${percentage22}</td>
					<td class="data">${percentage23}</td>
					<td class="data">100</td>

				</tr>
				<tr nowrap bgcolor="#DDDDDD">
					<td class="dir">安全管理中心</td>
					<td class="data">${percentage24}</td>
					<td class="data">${percentage25}</td>
					<td class="data">${percentage26}</td>
					<td class="data">${percentage27}</td>
					<td class="data">100</td>

				</tr>

			</table>
		</div>


		<div>
			<form action="${ctx}/summaryAnalysis/securityGap.action"
				method="post">
				<table border="0" width="99.5%" align="center" class="tab2"
					cellpadding="0" cellspacing="1">
					<center>
						<h2>安全差距分析报告</h2>
					</center>
					<tr align="center">
						<th width="10%" class="biaoti">项目名称</th>
						<th class="biaoti">基础网络安全</th>
						<th class="biaoti">边界安全</th>
						<th class="biaoti">终端系统安全</th>
						<th class="biaoti">服务器端系统安全</th>
						<th class="biaoti">应用安全</th>
						<th class="biaoti">数据安全与备份恢复</th>
						<th class="biaoti">安全管理中心</th>
					</tr>
					<tr align="center">
						<td>满足</td>
						<td>${num0}</td>
						<td>${num4}</td>
						<td>${num8}</td>
						<td>${num12}</td>
						<td>${num16}</td>
						<td>${num20}</td>
						<td>${num24}</td>
					</tr>
					<tr align="center">
						<td>部分满足</td>
						<td>${num1}</td>
						<td>${num5}</td>
						<td>${num9}</td>
						<td>${num13}</td>
						<td>${num17}</td>
						<td>${num21}</td>
						<td>${num25}</td>
					</tr>
					<tr align="center">
						<td>不满足</td>
						<td>${num2}</td>
						<td>${num6}</td>
						<td>${num10}</td>
						<td>${num14}</td>
						<td>${num18}</td>
						<td>${num22}</td>
						<td>${num26}</td>
					</tr>
					<tr align="center">
						<td>不适用</td>
						<td>${num3}</td>
						<td>${num7}</td>
						<td>${num11}</td>
						<td>${num15}</td>
						<td>${num19}</td>
						<td>${num23}</td>
						<td>${num27}</td>
					</tr>
					<tr align="center">
						<td style="background-color: #F6F9FE;">总计:</td>
						<td style="background-color: #F6F9FE;">${sum0}</td>
						<td style="background-color: #F6F9FE;">${sum1}</td>
						<td style="background-color: #F6F9FE;">${sum2}</td>
						<td style="background-color: #F6F9FE;">${sum3}</td>
						<td style="background-color: #F6F9FE;">${sum4}</td>
						<td style="background-color: #F6F9FE;">${sum5}</td>
						<td style="background-color: #F6F9FE;">${sum6}</td>
					</tr>
				</table>
			</form>
		</div>
	<!--
	<div id="container" style="min-width: 400px; height: 400px; margin: 0 auto"></div>
	-->
</body>
</html>
