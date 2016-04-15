<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8" import="java.util.*"%>
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


<link href="${ctx}/styles/left.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/tree.css" rel="stylesheet" type="text/css">
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
<script type="text/javascript" src="${ctx}/dwr/engine.js"></script>
<script type="text/javascript" src="${ctx}/dwr/interface/MessagePush.js"></script>
<script type='text/javascript' src="${ctx}/dwr/util.js"></script>
<script type="text/javascript" src="${ctx}/scripts/dwrManager.js"></script>
<style type="text/css">
html{
   overflow: hidden;
   }
body{maring:0px auto;padding:0px 0px 0px 0px;}
.common_node {
	cursor: pointer;
	line-height: 15px;
	padding-left: 5px;
}

.eventstree {
	overflow-x:auto;
	overflow-y: auto;
	white-space:nowrap;
	scrollbar-face-color: #FFFFFF;
	scrollbar-shadow-color: #D2E5F4;
	scrollbar-highlight-color: #D2E5F4;
	scrollbar-3dlight-color: #FFFFFF;
	scrollbar-darkshadow-color: #FFFFFF;
	scrollbar-track-color: #FFFFFF;
	scrollbar-arrow-color: #D2E5F4
}
.evnTitleTop { overflow-y:auto;
	padding: 0px 0px 0px 0px;
	margin: 2px 0px 0px 0px;
	border: 1px solid #c9e1f3;
	width: 180px;
	height: 550px;
	float: left;
	margin-left: 6px;
	scrollbar-face-color: #FFFFFF;
	scrollbar-shadow-color: #D2E5F4;
	scrollbar-highlight-color: #D2E5F4;
	scrollbar-3dlight-color: #FFFFFF;
	scrollbar-darkshadow-color: #FFFFFF;
	scrollbar-track-color: #FFFFFF;
	scrollbar-arrow-color: #D2E5F4
}
.eventstree ul {
	list-style: none;
	margin: 5px 0px 5px 0px;
}

.eventstree ul li {
	cursor: hand;
	width:auto;
}
</style>
<script type="text/javascript">
    
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
	jQuery(document).ready(function() {

	});
	function action(elementId, eventsType) {
		/*  if(eventsType != "img" && eventsType != 6)
		 {
		      if(eventsType == 1)
		      {
		          parent.mainFrame.location.href = '${ctx}/events/insertCustom.action';
		      }
		      else
		      {
		          linkTo(elementId,eventsType); 
		      }
		 } */
		/*  else
		 { */
		 var temp =  eventsType+elementId;
		changeIcon($("#"+eventsType+elementId+ ""));
		$("#query_" + temp + " >ul").toggle("slow");
		/*  } */
	}
	function action1(elementId, eventsType) {
		
		 var temp =  eventsType+elementId;
		changeIcon($("#"+eventsType+elementId+ ""));
		$("#query_" + temp + " >ul").toggle("slow");
		
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

	function linkTo(queryEventsId) {
		if(queryEventsId=='8'){
			parent.mainFrame.location.href = '${ctx}/monitor/customMonitorList.action';
		}else
			{
			parent.mainFrame.location.href = '${ctx}/monitor/queryMonitor.action?monitorGroupId='
				+ queryEventsId;
		
			}
		
	}
	function linkTo1(queryEventsId) {
		parent.mainFrame.location.href = '${ctx}/customTrend/queryEvents.action?trendId='+queryEventsId;
		
	}
	function showGroup(id) {
		
		if (id == '7') {
			parent.mainFrame.location.href = '${ctx}/monitorGroup/showGroups.action';
		}else if(id=='11'){
			parent.mainFrame.location.href = '${ctx}/pages/monitor/realtim-etevents.jsp';
		}
		else
		{
			action(id,"img_query_monitor");
		}
		
	}
function showGroup1(id) {
		
		 if(id == '1'){
			parent.mainFrame.location.href = '${ctx}/customTrend/query.action';
		}
		else
		{
			action(id,"img_query_monitor");
		}
		
	}
	function showMonitor(id) {
		if (id == 7) {
			parent.mainFrame.location.href = '${ctx}/monitorGroup/showGroups.action';
		} else {
			parent.mainFrame.location.href = '${ctx}/monitor/queryMonitor.action?monitorGroupId='
					+ id;
		}
	}
	
	function showAssetGroup(id)
	{
	   parent.mainFrame.location.href='${ctx}/monitorGroup/showAsset.action?assetGroupId=' + id;
	}
	/* fu nction show(url) {
		parent.mainFrame.location.href = url;
	} */
	function queryAll(val){
		 parent.mainFrame.location.href='${ctx}/monitorDBT/queryAll.action?dbType=' + val;
	}
	function showcomprehensive()
	{

	 $("#showcomprehensive").attr("href",'http://192.168.20.21:3000/authorize.html?user=admin&password=admin');
	}
	
	 function queryServers(){
		 parent.mainFrame.location.href='${ctx}/monitorTOM/queryAll.action';
		 //parent.mainFrame.location.href='${ctx}/pages/monitor/servers/tomcat_task_list.jsp';
	} 
	 function queryWindows(){
		 parent.mainFrame.location.href='${ctx}/monitorWIN/queryAll.action';
	 }

</script>
<style type="text/css">
.over {
	width: 180px;
	height: 600px;


}
</style>
</head>

<body onload="dwr.engine.setActiveReverseAjax(true);dwr.engine.setNotifyServerOnPageUnload(true);onLoad();">
	<s:hidden id="flag" name="flag" />
	<div class="evnTitleTop"  >
		<%
			String permissionIds = (String) session
					.getAttribute("SOC_LOGON_PERMISSIONS");
			if (permissionIds.indexOf(",9,") != -1) {
		%>
		 <div style="background-image:url(${ctx}/images/leftDh.jpg)">
			<table width="97%" border="0" cellspacing="0" cellpadding="0">
				<tr id="group">
					<td width="25px"><img src="${ctx}/images/jtTitle.jpg" alt=""
						width="25" height="28" />
					</td>
					<td class="leftDhxx">实时监控</td>
				</tr>
			</table>
		</div> 
			 <div style="overflow:auto" class="eventstree">
			<ul>
				<li onclick="showGroup(11)"><a href="#"><img
					src="${ctx}/images/arrow_03.gif"  /></a>&nbsp;&nbsp;<a href="#"><span
					style="vertical-align:30%" class="eventext">实时监控</span></a>
				</li>
				
				
			</ul>
		</div> 
		<div style="background-image:url(${ctx}/images/leftDh.jpg)">
			<table width="97%" border="0" cellspacing="0" cellpadding="0">
				<tr id="group">
					<td width="25px"><img src="${ctx}/images/jtTitle.jpg" alt=""
						width="25" height="28" />
					</td>
					<td class="leftDhxx">监控管理</td>
				</tr>
			</table>
		</div>
		<div class="eventstree">${htmlBuffer}</div>
		<div style="background-image:url(${ctx}/images/leftDh.jpg)">
			<table width="97%" border="0" cellspacing="0" cellpadding="0">
				<tr id="group">
					<td width="25px"><img src="${ctx}/images/jtTitle.jpg" alt=""
						width="25" height="28" />
					</td>
					<td class="leftDhxx">自定义趋势分析</td>
				</tr>
			</table>
		</div>
		<div class="eventstree">${customBuffer}</div>
<div style="background-image:url(../images/leftDh.jpg)">
			<table width="97%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="25px"><img src="${ctx}/images/jtTitle.jpg" alt=""
						width="25" height="28" />
					</td>
					<td class="leftDhxx">数据库监控</td>
				</tr>
			</table>
		</div>
		<div>
		<table width="99%"  border="0" cellspacing="10" cellpadding="10"
			style="line-height: 15px">

			<tr>
			
				<td align="left"><a href="#" class="hand" onclick="queryAll(1)"
					>
						&nbsp; <img src="${ctx}/images/icon_monitor_mysql.gif" />&nbsp;<span style="vertical-align:20%">MySql</span></a></td>
			</tr>
			<tr>
				<td align="left"><a href="#" onclick="queryAll(3)"
					>
						&nbsp; <img src="${ctx}/images/icon_monitor_oracle.gif" />&nbsp;<span style="vertical-align:20%">Oracle</span>
				</a>
				</td>
			</tr>
			<tr>
				<td align="left"><a href="#" onclick="queryAll(2)"
					>
						&nbsp; <img src="${ctx}/images/icon_monitor_sqlserver.gif" />&nbsp;<span style="vertical-align:20%">SQL Server</span>
				</a>
				</td>
			</tr>
			</table>
	</div>
	
	
	<div style="background-image:url(../images/leftDh.jpg)">
			<table width="97%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="25px"><img src="${ctx}/images/jtTitle.jpg" alt=""
						width="25" height="28" />
					</td>
					<td class="leftDhxx">中间件监控</td>
				</tr>
			</table>
		</div>
		<div>
		<table width="99%"  border="0" cellspacing="10" cellpadding="10"
			style="line-height: 15px">

			<tr>
			
				<td align="left"><a href="#" class="hand" onclick="queryServers()"
					>
						&nbsp; <img src="${ctx}/images/icon_monitor_tomcat.gif" />&nbsp;<span style="vertical-align:20%">tomcat监控</span></a></td>
			</tr>
			</table>
	</div>
	
	<!-- 主机监控 -->
	
	<div style="background-image:url(../images/leftDh.jpg)">
			<table width="97%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="25px"><img src="${ctx}/images/jtTitle.jpg" alt=""
						width="25" height="28" />
					</td>
					<td class="leftDhxx">主机</td>
				</tr>
			</table>
		</div>
		<div>
		<table width="99%"  border="0" cellspacing="10" cellpadding="10"
			style="line-height: 15px">

			<tr>
			
				<td align="left"><a href="#" class="hand" onclick="queryWindows()"
					>
						&nbsp; <img src="${ctx}/images/icon_monitor_windows.gif" />&nbsp;<span style="vertical-align:20%">Windows</span></a></td>
			</tr>
			</table>
	</div>
	
	
	<%--	<div style="background-image:url(${ctx}/images/leftDh.jpg)">
			<table width="97%" border="0" cellspacing="0" cellpadding="0"
				style="margin-top: 10px;">
				<tr id="group" style="margin-left:0px;">
					<td width="25px"><img src="${ctx}/images/jtTitle.jpg" alt=""
						width="25" height="28" />
					</td>
					<td class="leftDhxx">APM监控</td>
				</tr>
			</table>
		</div>
		 <table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="line-height: 10px">
			<tr>
				<td><div style="background-image:url(${ctx}/images/leftLine.jpg)">
						<table width="97%" border="0" cellspacing="0" cellpadding="0">
							<tr><img src="${ctx}/images/jtTitle.jpg" alt="" width="25" height="28" />
								<td width="25px"></td>
								<td class="leftDhxx">&nbsp;</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
			</table> 
		<div style="overflow:auto" class="eventstree">
			<ul>
				
				<li>${assetTreeBuffer}</li>
				
			</ul>
		</div>
		
		  <div style="background-image:url(${ctx}/images/leftDh.jpg)">
			<table width="97%" border="0" cellspacing="0" cellpadding="0">
				<tr id="group">
					<td width="25px"><img src="${ctx}/images/jtTitle.jpg" alt=""
						width="25" height="28" />
					</td>
					<td class="leftDhxx">网络流量监控</td>
				</tr>
			</table>
		</div> 
			 <div style="overflow:auto" class="eventstree">
			<ul>
				<li style="margin-left:6px">
					<a id="showcomprehensive" href="" class="hand" style="margin-left: -20px;" target="_blank" onclick="showcomprehensive();">

						&nbsp; <img src="${ctx}/images/arrow_03.gif" />&nbsp;<span style="vertical-align:20%">网络流量监控 </span></a>
				</li>
				
				
			</ul>
		</div> 
		--%>
		 	<%
			}
			//if (permissionIds.indexOf(",78,") != -1) {
		%><%--
	 <div style="background-image:url(${ctx}/images/leftDh.jpg)">
			<table width="97%" border="0" cellspacing="0" cellpadding="0"
				style="margin-top: 10px;">
				<tr id="group" style="margin-left:0px;">
					<td width="25px"><img src="${ctx}/images/jtTitle.jpg" alt=""
						width="25" height="28" />
					</td>
					<td class="leftDhxx">拓扑</td>
				</tr>
			</table>
		</div> 
	 <div style="overflow:auto" class="eventstree">
			<ul>
				<li>
					<a href="${ctx}/settingNetworkTopology/networkTopology.action" target="_blank" >
						<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span
						style="vertical-align:30%">网络拓扑</span>
					</a>
				</li>
			</ul>
		</div>   
		--%><%--<%
			}
		%>
	--%></div>
</body>
</html>
