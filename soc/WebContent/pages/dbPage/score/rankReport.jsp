<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<html>
<head>
<title>信息系统安全等级分布饼图</title>
<link href="${ctx}/styles/user/user.css" rel="stylesheet"	type="text/css"></link>
 <link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet" type="text/css"></link>
<link href="${ctx}/styles/jquery.popuplayer.css" rel="stylesheet"	type="text/css"></link>
<script type="text/javascript" src="${ctx}/scripts/report/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/report/highcharts.js"></script>
<script type="text/javascript" src="${ctx}/scripts/report/exporting.js"></script>
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx}scripts/js/sortupdatedata.js"></script>
<script type="text/javascript">
$(function () {
	var chart;
   	var data;
    $(document).ready(function() {
    	$.post("${ctx}/rankReport/queryAjax.action",function(json){
    		data = eval("("+json+")");
    		chart = new Highcharts.Chart(data);
    	});
    });
});
</script>

</head>
<body  style="width:99%">
<div id="container" style="min-width: 400px; height: 400px; margin: 0 auto"></div>
	<div style="min-width: 400px; height: 400px; margin: 0 auto; padding-top: 10px; background: #FFFFFF" >
		<center><h3 style="color: black; font-family: sans-serif;padding-bottom: 10px;">信息系统定级备案统计表</h3></center>
		<table width="100%" border="0" class="tab2" cellpadding="0" cellspacing="1" id="reporttable">
			<thead>
			<tr>
				<th class="biaoti" onclick="sortAble('reporttable', 0)" style="cursor:pointer">信息系统名称</th>
				<th class="biaoti" onclick="sortAble('reporttable', 1)" style="cursor:pointer">定级情况</th>
				<th class="biaoti" onclick="sortAble('reporttable', 2)" style="cursor:pointer">备案情况</th>
			</tr>
			</thead>
			<tbody>
			<tr style="display:none"></tr>
			<s:iterator value="rankReportList">
			<tr>
				<td align="center">${sysName}</td>
				<td align="center">
					<c:choose>    
	    				<c:when test="${!empty grade}">
	    					${grade}
	    				</c:when>
	    			</c:choose>
					
				</td>
				<td align="center">
					<c:choose>    
	    				<c:when test="${!empty docment}">已备案</c:when>
	    				<c:otherwise>未备案</c:otherwise>
	    			</c:choose>
				</td>
			</tr>
			</s:iterator>
			</tbody>
		</table>
	</div>
	
</body>
</html>
