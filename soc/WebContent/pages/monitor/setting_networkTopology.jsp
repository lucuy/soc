<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<%@ page language="java" import="java.util.ArrayList"%>
<%@ page language="java" import="java.util.List"%>
<%@ page language="java" import="com.zhtelecom.common.snmp.*"%>
<%@ page language="java" import="com.zhtelecom.nms.framework.discover.DeviceInfo"%>
<%@ page language="java" import="com.zhtelecom.nms.framework.discover.DiscoverAPI"%>
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
	<s:form id="setting-networkTopology" action="networkTopology.action"
		namespace="/settingNetworkTopology" method="post" theme="simple"
		name="setting-networkTopology">
		<!--  左侧table-->
		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 0px">
			<tr>
				<td width="100%" valign="top">
					<div class="framDiv">
						<!--  左侧table-->
						<table width="100%" border="0" cellspacing="1" cellpadding="0">
							<tr>
								<td class="r2titler"><b>网络拓扑图</b>&nbsp;&nbsp;&nbsp; <font
									id="message1"></font></td>
							</tr>
							<tr>
								<td><applet archive="${ctx}/pages/monitor/zhtelecombasis.jar"
										code="com.NetworkTopology.class" codebase="./"
										width="100%" height="600px">
									</applet>
								</td>
							</tr>
						</table>
					</div></td>
			</tr>
		</table>
	</s:form>
</body>
</html>