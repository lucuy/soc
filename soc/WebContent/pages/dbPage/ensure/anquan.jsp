<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<html>
<head>
<title>安全域分布</title>
<link href="${ctx}/css/basic/css.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${ctx}/scripts/report/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/report/highcharts.js"></script>
<script type="text/javascript" src="${ctx}/scripts/report/exporting.js"></script>
<script type="text/javascript" src="${ctx}/scripts/report/data.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/report/highcharts-more.js"></script>
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
					//	var selectValue = document.getElementById('mySelect').value;
						var obj = document.getElementById('mySelect');
						var index = obj.selectedIndex; //序号，取当前选中选项的序号 

							var val = obj.options[index].value;
							//alert(val);
							//document.forms[0].action=selectValue;
							location.href = "${ctx}/summaryAnalysis/securityGapC.action?pkca="
									+ val;

					});

	function changeAction() {
		//默认是login.action，当select改变时同时改变from的action属性
		///我这里直接把列表的value赋值到form的action，你可以根据需要改改
		//var selectValue = document.getElementById('mySelect').value;
		var obj = document.getElementById('mySelect');
		var index = obj.selectedIndex; //序号，取当前选中选项的序号 
		var val = obj.options[index].value;
		//alert(val);
		//document.forms[0].action=selectValue;
		location.href = "${ctx}/summaryAnalysis/securityGap.action?pkca=" + val;
	}
</script>


</head>
<body style="background-color: #FFFFFF">
		<div>

			<s:select list="#request.listss" listValue="caName" listKey="pkca"
				label="系统" id="mySelect" onchange="changeAction();">
			</s:select>
		</div>
</body>
</html>
