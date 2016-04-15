<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<html>
<head>
<title>差距分析进度</title>
<link href="${ctx}/css/basic/css.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${ctx}/scripts/report/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/report/highcharts.js"></script>
<script type="text/javascript" src="${ctx}/scripts/report/exporting.js"></script>
<script type="text/javascript" src="${ctx}/scripts/report/data.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/report/highcharts-more.js"></script>
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
		location.href = "${ctx}/gapAnalysisSchedule/gapSchedule.action?pkca="
				+ val;
	}

	$(function() {
		var chart;
		var data;
		$(document)
				.ready(
						function() {
							//默认是login.action，当select改变时同时改变from的action属性
							//我这里直接把列表的value赋值到form的action，你可以根据需要改改
							//var selectValue=document.getElementById('mySelect').listValue;

							///var obj=document.getElementById('mySelect');    
							//var index=obj.selectedIndex; //序号，取当前选中选项的序号 
							//var val = obj.options[index].value; 
							var urlpath = "${ctx}/gapAnalysisSchedule/queryAjaxEquelsNull.action";
							$.post(urlpath, function(json) {
								data = eval("(" + json + ")");
								chart = new Highcharts.Chart(data);
							});

						});
	});
</script>


</head>
<body style="width:99.5%">

	<s:if test="#request.listss.size==0">

		<div>
			<s:select list="#request.listss" listValue="value" listKey="key"
				label="系统" id="mySelect" onchange="changeAction(this);">

			</s:select>
		</div>
		<div id="container"
			style="min-width: 400px; height: 400px; margin: 0 auto"></div>


	</s:if>





</body>
</html>
