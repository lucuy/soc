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
<style type="text/css">
ul {
	list-style: none;
}
</style>

<script language="javascript">

	function rebootServer() {
		window.location.href = '${ctx}/settingShutdown/rebootServer.action';
		alert('服务器已经重启，请稍候重新访问！');
	}
	function shutdownServer() {
		window.location.href = '${ctx}/settingShutdown/shutdownServer.action';
		alert('服务器已经关闭，服务将停止，开启请与管理员联系！');
	}
</script>
</head>

<body>
	<!--  主table-->

	<s:form action="update-e.action" namespace="/setting" method="post"
		theme="simple" id="empForm" name="empForm">
		<!--  主table-->
		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 2px">
			<tr>
				<td>
				<div class="framDiv">
					<!--  左侧table-->
					<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
								<td class="r2titler"><b>关闭系统</b>&nbsp;&nbsp;&nbsp;
									<font id="message1"></font></td>
							</tr>

							<tr>
								<td align="right"><font color="red"><s:actionmessage />
								</font>
								</td>
							</tr>
						<tr>
							<td height="5px"></td>
						</tr>

						<!-- 空行 -->
						<tr>
							<td class="td_detail_separator"></td>
						</tr>
						<tr>
							<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>

										<td align="right" width="48%"><input type="button"
											value="重启服务器" class="btnyh"
											onclick="if(confirm('确认重启服务器吗？服务器重启过程中无法提供服务！'))if(confirm('请再次确认？'))rebootServer()" />
										</td>
										<td width="20"></td>
										<td align="left"><input type="button" value="关闭服务器"
											class="btnyh"
											onclick="if(confirm('确认关闭服务器吗？关闭服务器服务将被终止！'))if(confirm('请再次确认？'))shutdownServer()" />
										</td>
									</tr>
								</table></td>
						</tr>
						
						<!-- 空行 -->
						<tr>
							<td class="td_detail_separator"></td>
						</tr>
					</table>
					
					</div></td>
			</tr>
		</table>
	</s:form>
</body>
</html>