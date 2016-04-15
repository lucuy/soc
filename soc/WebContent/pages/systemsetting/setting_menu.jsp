<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>

<html>
<head>
<link href="${ctx}/styles/left.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/tree.css" rel="stylesheet" type="text/css">

<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
<style type="text/css">
.tree ul {
	list-style: none;
}
</style>
<link type="text/css"
    href="${ctx}/styles/css/ui-lightness/jquery-ui-1.7.3.custom.css"
    rel="stylesheet" />
<script type="text/javascript"
    src="${ctx}/scripts/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript"
    src="${ctx}/scripts/js/jquery-ui-1.7.3.custom.min.js"></script>
    <script type="text/javascript" src="${ctx}/dwr/engine.js"></script>
<script type="text/javascript" src="${ctx}/dwr/interface/MessagePush.js"></script>
<script type='text/javascript' src="${ctx}/dwr/util.js"></script>
<script type="text/javascript" src="${ctx}/scripts/dwrManager.js"></script>
<style type="text/css">
.titleTop {
	padding: 0px 0px 0px 0px;
	margin: 2px 0px 0px 0px;
	border: 1px solid #e3e6eb;
	width: 180px;
	height: 580px;
	float: left;
	margin-left: 6px;
	
}

</style>   
<script language="javascript">
     function onLoad(){
            var userId = '${userinfo}';
            MessagePush.pageOnLoad(userId);
       }
       
       function showMessage(val){
       		if(val!='-1'){
       			parent.frames[0].LoadTimer();
       			alert(val);
       		}else{
       			alert(val);
       			parent.frames[0].NewMessage();
       		}
       		
      	 	
       }
       dwr.engine.setErrorHandler(function(){});
       /**function showmessage(val){
  		
      	}*/
    function reload() {
        document.onmousedown = ContextMenu;
        $("#mack").addClass("ui-widget-overlay");
    }
    
    function cancel()
        {
            document.onmousedown=ContextMenu;
            $("#mack").removeClass("ui-widget-overlay");
        }
    
    function ContextMenu() {

        if (event.button == 2 || event.button == 3) {
            alert("升级中无法操作");
            return false;
        }
    }

	function action(elementId, eventsType) {
		if (eventsType != "img" && eventsType != 6) {
			if (eventsType == 1) {
				parent.mainFrame.location.href = '${ctx}/events/insertCustom.action';
			} else {
				linkTo(elementId, eventsType);
			}
		} else {
			changeIcon($("#img_query_" + elementId + ""));
			$("#query_" + elementId + " >ul").toggle("slow");
		}
	}
	function changeIcon(nainNode) {
		if (nainNode) {
			if (nainNode.attr("src").indexOf("u321_normal.gif") >= 0) {
				nainNode.attr("src", "${ctx}/images/u319_normal.gif");

			} else {
				nainNode.attr("src", "${ctx}/images/u321_normal.gif");
			}
		}
	}

	function linkTo(queryid) {
		parent.mainFrame.location.href = '${ctx}/settingLogger/queryId.action?queryId='
				+ queryid;
	}
</script>
</head>

<body onload="dwr.engine.setActiveReverseAjax(true);dwr.engine.setNotifyServerOnPageUnload(true);onLoad();">
	<div class="titleTop" style="overflow:auto; overflow-x:hidden">
		<div style="background-image:url(${ctx}/images/leftDh.jpg)">
			<table width="97%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="25px"><img src="${ctx}/images/jtTitle.jpg" alt=""
						width="25" height="28" /></td>
					<td class="leftDhxx">系统信息</td>
				</tr>
			</table>
		</div>
		<table width="99%"  border="0" cellspacing="10" cellpadding="10"
			style="line-height: 15px">
			<%
			    String permissionIds = (String)session.getAttribute("SOC_LOGON_PERMISSIONS");
			    if (permissionIds.indexOf(",44,") != -1)
			    {
			%>
			<tr>
				<td align="left"><a href="#"
					onclick="parent.mainFrame.location.href='${ctx}/showSysInfo/getSysInfo.action';">
						&nbsp;<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span style="vertical-align:20%">系统状态</span>
				</a>
				</td>
			</tr>
			<%
			    }
			    if (permissionIds.indexOf(",35,") != -1)
			    {
			%>
			<tr>
				<td align="left"><a href="#"
					onclick="parent.mainFrame.location.href='${ctx}/settingNetwork/settingNetwork.action';">
						&nbsp;<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span style="vertical-align:20%">网络设置</span>
				</a></td>
			</tr><%--
			双机网络配置
			--%><%--<tr>
				<td align="left"><a href="#"
					onclick="parent.mainFrame.location.href='${ctx}/settingNetwork/queryNetSetting.action';">
						&nbsp;<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span style="vertical-align:20%">双机网络设置</span>
				</a></td>
			</tr>
			<tr>
				<td align="left"><a href="#"
					onclick="parent.mainFrame.location.href='${ctx}/settingNetwork/queryHAStart.action?type=4';">
						&nbsp;<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span style="vertical-align:20%">双机设置</span>
				</a></td>
			</tr>
			 --%><%
			    }if(permissionIds.indexOf(",67,") != -1){
			%>
			
			 
			
			<%
			    }
			    if (permissionIds.indexOf(",36,") != -1)
			    {
			%>
			<tr>
				<td align="left"><a href="#"
					onclick="parent.mainFrame.location.href='${ctx}/settingRoute/settingRoute.action';">
						&nbsp;<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span style="vertical-align:20%">路由表</span></a>
				</td>
			</tr>
			<%
			    }
			    
			    if (permissionIds.indexOf(",39,") != -1)
			    {
			%>
			<tr>
				<td align="left"><a href="#"
					onclick="parent.mainFrame.location.href='${ctx}/settingSysLog/settingSysLog.action';">
						&nbsp;<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span style="vertical-align:20%">SYSLOG</span></a></td>
			</tr>
			<%
			    }
			    if (permissionIds.indexOf(",40,") != -1)
			    {
			%>
			<tr>
				<td align="left"><a href="#"
					onclick="parent.mainFrame.location.href='${ctx}/settingSNMP/settingSNMP.action';">
						&nbsp;<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span style="vertical-align:20%">SNMP</span>
				</a>
				</td>
			</tr>
			<%
			    }
			    if (permissionIds.indexOf(",38,") != -1)
			    {
			%>
			<tr>
				<td align="left"><a href="#"
					onclick="parent.mainFrame.location.href='${ctx}/settingMail/settingMail.action';">
						&nbsp;<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span style="vertical-align:20%">邮箱设置</span>
				</a>
				</td>
			</tr>
			<%
			    }
			    if (permissionIds.indexOf(",51,") != -1)
			    {
			%>
			<tr>
				<td align="left"><a href="#"
					onclick="parent.mainFrame.location.href='${ctx}/settingLogger/logger.action';">
						&nbsp;<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span style="vertical-align:20%">采集器管理</span>
				</a></td>
			</tr>
			<%
			    }
			    if (permissionIds.indexOf(",42,") != -1)
			    {
			%>
			<tr>
				<td align="left"><a href="#"
					onclick="parent.mainFrame.location.href='${ctx}/settingStorage/storage.action';">
						&nbsp;<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span style="vertical-align:20%">配置数据源</span>
				</a>
				</td>
			</tr>
			<%
			    }
			    if (permissionIds.indexOf(",45,") != -1)
			    {
			%>
			<tr>
				<td align="left"><a href="#"
					onclick="parent.mainFrame.location.href='${ctx}/settingThreshold/thresholdEdit.action';">
						&nbsp;<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span style="vertical-align:20%">配置阀值</span>
				</a>
				</td>
			</tr>
			<%
			    }
			    if (permissionIds.indexOf(",43,") != -1)
			    {
			%>
			<tr>
				<td align="left"><a href="#"
					onclick="parent.mainFrame.location.href='${ctx}/alertSetting/queryAlert.action';">
						&nbsp;<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span style="vertical-align:20%">告警配置</span>
				</a>
				</td>
			</tr>
			 <%--   <tr>
				<td align="left"><a href="#"
					onclick="parent.mainFrame.location.href='${ctx}/SystemSettingSyncIp/querySyncIp.action';">
						&nbsp;<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span style="vertical-align:20%">同步服务器设置</span>
					</a>
				</td>
			</tr>  --%>
			
			<!-- 同步系统时间 -->
			  <tr>
				<td align="left"><a href="#"
					onclick="parent.mainFrame.location.href='${ctx}/syncDate/syncDate.action';">
						&nbsp;<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span style="vertical-align:20%">同步服务器时间</span>
					</a>
				</td>
			</tr> 
			
			<% 
			    }
			     if (permissionIds.indexOf(",54,") != -1)
			    {
			%>
			<tr>
				<td align="left"><a href="#"
					onclick="parent.mainFrame.location.href='${ctx}/category/queryCategory.action';">
						&nbsp;<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span style="vertical-align:20%">资产设备设置</span>
				</a>
				</td>
			</tr>
			
			<% 
			    }
			     if (permissionIds.indexOf(",76,") != -1)
			    {
			%>
			<tr>
				<td align="left"><a href="#"
					onclick="parent.mainFrame.location.href='${ctx}/assetSystem/queryAssetSystem.action';">
						&nbsp;<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span style="vertical-align:20%">操作系统设置</span>
					</a>
				</td>
			</tr> 
			
			<% 
			    }
			     if (permissionIds.indexOf(",71,") != -1)
			    {
			%>
		 <tr>
				<td align="left"><a href="#"
					onclick="parent.mainFrame.location.href='${ctx}/nodeGroup/editnodeGroup.action';">
						&nbsp;<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span style="vertical-align:20%">级联管理</span>
				</a>
				</td>
			</tr>
			<%--<tr>
				<td align="left"><a href="${ctx}/nodeGroup/nodeGroupNetworkTopolog.action"target="_blank">
						&nbsp;<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span style="vertical-align:20%">级联拓扑</span>
				</a>
				</td>
			</tr>
			--%><%
			    }
			%>
			
			<% 
			     if (permissionIds.indexOf(",41,") != -1)
			    {
			%>
			<tr>
				<td align="left"><a href="#"
					onclick="parent.mainFrame.location.href='${ctx}/pages/systemsetting/setting_shutdown.jsp';">
						&nbsp;<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span style="vertical-align:20%">关闭系统</span>
				</a>
				</td>
			</tr>
			<%
			    }
			%>
			
			
			
			
			</table>
			<table width="97%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td><div style="background-image:url(${ctx}/images/leftLine.jpg)">
						<table width="97%" border="0" cellspacing="0" cellpadding="0">
							<tr><%-- <img src="${ctx}/images/jtTitle.jpg" alt="" width="25" height="28" /> --%>
								<td width="25px"></td>
								<td class="leftDhxx">&nbsp;</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
			</table>
			<table  border="0" cellspacing="10" cellpadding="10"
			style="line-height: 15px">
			<%
			     if (permissionIds.indexOf(",47,") != -1)
			    {
			%>
			<tr>
				<td align="left">
					<a href="#"
					onclick="parent.mainFrame.location.href='${ctx}/settingUpgrade/display.action';">
						&nbsp;<img src="${ctx}/images/arrow_03.gif" />&nbsp<span style="vertical-align:20%">升级</span>
				</a></td>
			</tr>
				<%
			
			    }
			if(permissionIds.indexOf(",82,") != -1){
			
			
			%>
			<tr>
				<td align="left">
					<a href="#"
					onclick="parent.mainFrame.location.href='${ctx}/saveTimeSetting/edit.action';">
						&nbsp;<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span style="vertical-align:20%">数据保存时限设置</span>
				</a></td>
			</tr>
			<%
			    }
			 if (permissionIds.indexOf(",48,") != -1)
			    {
			%>
			<tr>
				<td align="left">
					<a href="#"
					onclick="parent.mainFrame.location.href='${ctx}/settingArchive/archiveList.action';">
						&nbsp;<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span style="vertical-align:20%">归档</span>
				</a></td>
			</tr>
		
			<%
			    }
			 if (permissionIds.indexOf(",48,") != -1)
			    {
			%>
			<tr>
				<td align="left">
					<a href="#"
					onclick="parent.mainFrame.location.href='${ctx}/settingArchive/auto.action';">
						&nbsp;<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span style="vertical-align:20%">自动归档设置</span>
				</a></td>
			</tr>
			<%
			    }
			if (permissionIds.indexOf(",50,") != -1)
			    {
			%>
			<tr>
				<td align="left">
					<a href="#"
					onclick="parent.mainFrame.location.href='${ctx}/settingCenterip/centerIp.action';">
						&nbsp;<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span style="vertical-align:20%">通信配置</span>
						
				</a></td>
			</tr>
			<%-- <%
			    }if (permissionIds.indexOf(",210,") != -1)
			    {
			%>
			<tr>
				<td align="left">
					<a href="#"
					onclick="parent.mainFrame.location.href='${ctx}/systemRegisterInformation/systemRegister.action';">
						&nbsp;<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span style="vertical-align:20%">系统注册信息</span>
				</a></td>
			</tr> --%>
			<%
			    }
			if (permissionIds.indexOf(",52,") != -1){
			  %>
			  <tr>
				<td align="left">
					<a href="#"
					onclick="parent.mainFrame.location.href='${ctx}/pages/systemsetting/setting_agentDownload.jsp';">
						&nbsp;<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span style="vertical-align:20%">Agent下载</span>
						
				</a></td>
			  </tr> 
			  <%
			  }          
			%>
			
		</table>
	</div>
	<div class="ui-overlay">
        <div id="mack"></div>
    </div>
</body>
</html>