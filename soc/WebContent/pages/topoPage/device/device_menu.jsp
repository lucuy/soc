<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8" import="java.util.*" %>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>

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
.evnTitleTop {
	margin-left:6px;
	  margin-top:0px;
	  border:1px solid #E3E6EB; 
	  width:180px;
	  height:528px;
	  background-color:#EEF1F8;
	  float:left;
	  padding-bottom:10px; 
}

.manager {
	padding-right: 6px;
}

.wz {
	vertical-align: 4px
}

.eventstrees {
	margin: 0px 5px 5px 25px;
	padding: 0px 0px 0px 0px;
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

.eventstrees ul {
	list-style: none;
	margin: 0px 0px 5px 0px;
}

.eventstrees ul li {
	cursor: hand;
	width:auto;
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
       			parent.frames[0].NewMessage();
       		}
       		
      	 	
       }
       dwr.engine.setErrorHandler(function(){});
/**
    打开与关闭树并改变图片
*/
	function action(elementId,eventsType)
	{
	         var temp = eventsType+elementId;
	        // alert(temp);
	        
	        changeIcon($("#"+eventsType+elementId+""));
	        
	        
            $("#query_"+elementId+" >ul").toggle("slow");
	}
    function changeIcon(nainNode)
    {
        if(nainNode)
        {
            if(nainNode.attr("src").indexOf("u321_normal.gif")>=0)
            {
               nainNode.attr("src","${ctx}/images/u319_normal.gif");
               
            }else
            {
               nainNode.attr("src","${ctx}/images/u321_normal.gif");
            }
         }
     }
        
</script>
</head>

<body onload="dwr.engine.setActiveReverseAjax(true);dwr.engine.setNotifyServerOnPageUnload(true);onLoad();">
	<div class="titleTop">
		<div style="background-image:url(${ctx}/images/leftDh.jpg)">
			<table width="97%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="25px"><img src="${ctx}/images/jtTitle.jpg" alt=""
						width="25" height="28" />
					</td>
					<td class="leftDhxx">网络拓扑</td>
				</tr>
			</table>
		</div>
		
		<table width="99%" border="0" cellspacing="5px" cellpadding="0px"
			style="margin-top:10px;margin-left:-17px;">
			
			<%
				String permissionIds = (String)session.getAttribute("SOC_LOGON_PERMISSIONS");
				if(permissionIds.indexOf(",3,") != -1)
				{
			%>
			<tr>
				<td align="left"><a href="#" class="common_node"
					onclick="parent.mainFrame.location.href='${ctx}/device/queryAllDevice.action';">
						&nbsp; <img src="${ctx}/images/arrow_03.gif" />&nbsp;<span style="vertical-align:top">设备管理</span></a></td>
			</tr>
			<%
				}
				
				if(permissionIds.indexOf(",4,") != -1)
				{
			%>
			<tr align="left">
				<td><a href="#" class="common_node" 
					onclick="parent.mainFrame.location.href='${ctx}/deviceCategory/queryAllDeviceCategory.action';">
						&nbsp; <img src="${ctx}/images/arrow_03.gif" />&nbsp;<span style="vertical-align:top">设备类型管理</span> </a></td>
			</tr>
			<%
				}
			%>
			<%-- <tr align="left">
				<td>
				<!--  
				<a href="#" class="common_node" 
					onclick="parent.mainFrame.location.href='${ctx}/flexhtml/BusinessModel.html';">
						&nbsp; <img src="${ctx}/images/arrow_03.gif" />&nbsp;<span style="vertical-align:top">网络拓扑</span> </a></td>
			-->
			<a href="${ctx}/flexhtml/BusinessModel.html" target="_blank" class="common_node">	
					&nbsp; <img src="${ctx}/images/arrow_03.gif" />&nbsp;<span style="vertical-align:top">内网拓扑</span> </a></td>
			
			</tr>
			
			<tr align="left">
				<td>
				<!--  
				<a href="#" class="common_node" 
					onclick="parent.mainFrame.location.href='${ctx}/flexhtml/BusinessModel.html';">
						&nbsp; <img src="${ctx}/images/arrow_03.gif" />&nbsp;<span style="vertical-align:top">网络拓扑</span> </a></td>
			-->
			<a href="${ctx}/flexhtml/IntranetBusinessModel.html" target="_blank" class="common_node">	
					&nbsp; <img src="${ctx}/images/arrow_03.gif" />&nbsp;<span style="vertical-align:top">外网拓扑</span> </a></td>
			
			</tr> --%>
			
			<!-- 网络拓扑导航 -->
			<tr align="left">
				<td>
					<a href="javascript:void(0)" onclick="parent.mainFrame.location='${ctx}/alertMessage/alertMessageQueryByRule.action'"
						class="common_node">
						&nbsp; <img src="${ctx}/images/arrow_03.gif" />
						<span style="vertical-align:top">网络拓扑</span>
					</a>
				</td>
			</tr>
			<tr align="left">
				<td>
					<a href="#" class="common_node" 
					onclick="parent.mainFrame.location.href='${ctx}/netBackGroundPhoto/queryAllPhoto.action';">
						&nbsp; <img src="${ctx}/images/arrow_03.gif" />&nbsp;<span style="vertical-align:top">设备拓扑背景</span> </a>
				</td>
			</tr>
		</table>
	</div>
	
</body>
</html>
