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

<link href="${ctx}/styles/systemsetting/systemsetting.css" rel="stylesheet" type="text/css">
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine-en.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine.js"></script>
<link href="${ctx}/styles/template.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/validationEngine.jquery.css" rel="stylesheet"
	type="text/css">
<script>
	jQuery(document).ready(function() {
		jQuery("#setting-terrace").validationEngine();
	});
	function subdata() {
		$("form").submit();
	}
</script>
<style type="text/css">
ul li {
	list-style: none;
}
</style>
</head>

<body style="margin-top: 2px">
	<s:form id="setting-terrace" action="update.action"
		namespace="/setting-terrace" method="post" theme="simple"
		name="setting-terrace">
		<!--  左侧table-->
		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 0px">
			<tr>
				<td width="100%" valign="top">
					<div class="framDiv">
						<!--  左侧table-->
						<table width="100%" border="0" cellspacing="1" cellpadding="0">
							<tr>
								<td class="r2titler"><b>平台配置</b>&nbsp;&nbsp;&nbsp; <font
									id="message1"></font>
								</td>
							</tr>

							<tr>
								<td height="12" align="right"><font color="red"><s:actionmessage />
								</font></td>
							</tr>

							<tr>
								<td>
									<table width="99%" border="0" cellspacing="0" cellpadding="0"
										style="margin-left: 18px;">

										<tr>
											<td>接收端口</td>
											<td><input type="text" id="terraceServerPort"
												name="terraceServerPort" value="${terraceServerPort}" class="validate[custom[settingPort]] text-input" />
											</td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
			<!-- 空行 -->
			<tr>
				<td class="td_detail_separator"></td>
			</tr>
			<tr align="right">
				<td colspan="2"><input type="button" class="btnyh" id="btnSave"
					value="保存设置" onclick="subdata()" />
				</td>
			</tr>
		</table>
	</s:form>
</body>
</html>
