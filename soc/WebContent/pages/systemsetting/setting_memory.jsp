<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<%@page import="com.soc.model.user.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<%-- <script type="text/javascript" src="${ctx}/pushlet/ajax-pushlet-client.js"></script>--%>

<link href="${ctx}/styles/audit/audit.css" rel="stylesheet"
	type="text/css">
 <link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet"
	type="text/css"> 
	<%-- <link href="${ctx}/styles/jquery-ui-1.8.21.css" rel="stylesheet" type="text/css"> --%>
<link href="${ctx}/styles/jquery.popuplayer.css" rel="stylesheet"
	type="text/css">
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
<script type='text/javascript' src="${ctx}/scripts/popuplayer.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine.js"></script>
<script type="text/javascript">

</script>	
</head>
<body>
	<s:form action="memoryQuery.action" namespace="/showSysInfo"
		method="post" theme="simple" id="memoryForm" name="memoryForm">
		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 2px">
			<!-- 空行-->
			<tr>
				<td></td>
			</tr>
			<tr>
				<td>
					<div class="box">

						<span class="kuaiju">3天内 内存占用记录</span>
					</div>
				</td>
			<tr>
				<td>
					<table style="margin-top: 4px" width="100%" border="0"
						cellspacing="1" cellpadding="0" class="tab2">
						<tr height="28">
							<td>已使用内存</td>
							<td>总内存</td>
							<td>内存百分比</td>
							<td>内存剩余</td>
							<td>记录时间</td>
						</tr>
						<s:iterator value="memoryList">
							<tr align="center">
								<td>${SystemMemoryEmploy}</td>
								<td>${SystemMemoryTotal}</td>
								<td>${SystemMemoryPercent}</td>
								<td>${SystemMemoryRemain}</td>
								<td><s:date name="SystemMemoryDate" format="yyyy-MM-dd HH:mm:ss"/></td>
							</tr>
						</s:iterator>
						<tr>
							<td colspan="5" width="100%"><jsp:include
									page="../commons/page.jsp"></jsp:include></td>
						</tr>
					</table></td>
			</tr>
		</table>
	</s:form>
</body>
</html>