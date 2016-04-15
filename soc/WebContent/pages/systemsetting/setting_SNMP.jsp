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

<link href="${ctx}/styles/systemsetting/systemsetting.css"
	rel="stylesheet" type="text/css">
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<link href="${ctx}/styles/template.css" rel="stylesheet" type="text/css">
<script>
	function subdata() {
		$("form").submit();
	}
</script>
</head>

<body style="margin-top: 2px">
	<s:form id="setting-checking" action="update.action"
		namespace="/setting-checking" method="post" theme="simple"
		name="setting-checking">
		<!--  左侧table-->
		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 0px">
			<tr>
				<td width="100%" valign="top">
					<div class="framDiv">
						<!--  左侧table-->
						<table width="100%" border="0" cellspacing="1" cellpadding="0">
							<tr>
								<td class="r2titler"><b>SNMP设置</b>&nbsp;&nbsp;&nbsp;
									<font id="message1"></font></td>
							</tr>

							<tr>
								<td align="right"><font color="red"><s:actionmessage />
								</font>
								</td>
							</tr>

							<tr>
								<td>
									<table width="90%" border="0" cellspacing="0" cellpadding="0"
										style="margin-left: 18px;">
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<tr>
											<td width="30%">启用SNMP服务</td>
											<td align="left" width="60%"><input type="checkbox"
												id="checkingUse" name="checkingUse" value="1"
												<c:if test="${checkingUse==1}">checked="checked"</c:if> /></td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>

									</table></td>
							</tr>
						</table>
					</div></td>
			</tr>

			<tr>
				<td class="td_detail_separator"></td>
			</tr>
			<tr align="right">
				<td colspan="2"><input type="button" class="btnyh" id="btnSave"
					value="保存设置" onclick="subdata()" /></td>
			</tr>
		</table>
	</s:form>
</body>
</html>