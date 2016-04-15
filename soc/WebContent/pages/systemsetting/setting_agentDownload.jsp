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
<head>
<%-- <script type="text/javascript" src="${ctx}/pushlet/ajax-pushlet-client.js"></script>--%>

<link href="${ctx}/styles/systemsetting/systemsetting.css"
	rel="stylesheet" type="text/css">
<link href="${ctx}/styles/template.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/validationEngine.jquery.css" rel="stylesheet"
	type="text/css">
 <link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet"
	type="text/css"> 
	<%-- <link href="${ctx}/styles/jquery-ui-1.8.21.css" rel="stylesheet" type="text/css"> --%>
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine-en.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine.js"></script>
<script>
jQuery(document).ready(function() {
		jQuery("#setting_archiveAuto").validationEngine();

	});
	function subdata() {
		$("form").submit();
	}
</script>
</head>

<body style="margin-top: 2px">
	<s:form id="setting_archiveAuto" action="auto.action"
		namespace="/settingArchive" method="post" theme="simple"
		name="setting_archiveAuto">
		<!--  左侧table-->
		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 0px">
			<tr>
				<td width="100%" valign="top">
					<div class="framDiv">
						<!--  左侧table-->
						<table width="100%" border="0" cellspacing="1" cellpadding="0">
							<tr>
								<td class="r2titler"><b>Agent安装包下载</b>&nbsp;&nbsp;&nbsp;
									<font id="message1"></font></td>
							</tr>

							<tr>
								<td align="right">
								</td>
							</tr>
							
							<tr>
							   <td style="padding-left: 20px;padding-top: 10px"><b>Windows通用版下载</b></td>
							</tr>
							
							<tr>
							   <td class="td_detail_separator"></td>
							</tr>
							
							<tr>
							   <td colspan="5"><div class="xuxian"></div></td>
							</tr>

							<tr>
								<td>
									<table width="90%" border="0" cellspacing="0" cellpadding="0"
										style="margin-left: 18px;">
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										
									<!-- 	<tr>
											<td width="30%">agent_windows_setup32:</td>
											<td align="left" width="60%">点击下载</td>
										</tr>
										空行
										<tr>
											<td class="td_detail_separator"></td>
										</tr> -->
										
                                        <tr>   
											<td width="30%">agent_windows_setup:</td>
											<td align="left" width="60%">
											<a href="${ctx}/agentpackage/agent_windows_setup.exe">点击下载</a>
											</td>
										</tr>
									</table></td>
							</tr>
							<tr>
								<td align="right">
								</td>
							</tr>
							
							<tr>
							   <td style="padding-left: 20px;padding-top: 10px"><b>Linux版下载</b></td>
							</tr>
							
							<tr>
							   <td class="td_detail_separator"></td>
							</tr>
							
							<tr>
							   <td colspan="5"><div class="xuxian"></div></td>
							</tr>

							<tr>
								<td>
									<table width="90%" border="0" cellspacing="0" cellpadding="0"
										style="margin-left: 18px;">
										<!-- 空行 
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										
										<tr>
											<td width="30%">agent_linux_setup32:</td>
											<td align="left" width="60%">
											<a href="${ctx}/agentpackage/agent_linux_x86.tar.gz">点击下载</a>
											</td>
										</tr>
										-->
										<!-- 空行
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										
                                        <tr>   
											<td width="30%">agent_linux_setup64:</td>
											<td align="left" width="60%">
											<a href="${ctx}/agentpackage/agent_linux.tar.gz">点击下载</a>
											</td>
										</tr>
										 -->
										 <!-- 空行 -->
										 <tr>
											<td class="td_detail_separator"></td>
										</tr>
										
                                        <tr>   
											<td width="30%">linux_agent_install:</td>
											<td align="left" width="60%">
											<a href="${ctx}/agentpackage/linux_agent_install.tar.gz">点击下载</a>
											</td>
										</tr>
									</table></td>
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