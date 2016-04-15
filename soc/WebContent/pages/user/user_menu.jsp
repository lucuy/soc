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
       /**function showmessage(val){
  		
      	}*/

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
					<td class="leftDhxx">用户管理信息</td>
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
					onclick="parent.mainFrame.location.href='${ctx}/user/queryUser.action';">
						&nbsp; <img src="${ctx}/images/arrow_03.gif" />&nbsp;<span style="vertical-align:top">账户管理</span></a></td>
			</tr>
			<%
				}
				
				if(permissionIds.indexOf(",4,") != -1)
				{
			%>
			<tr align="left">
				<td><a href="#" class="common_node" 
					onclick="parent.mainFrame.location.href='${ctx}/role/queryRole.action';">
						&nbsp; <img src="${ctx}/images/arrow_03.gif" />&nbsp;<span style="vertical-align:top">角色管理</span> </a></td>
			</tr>
			<%
				}
			%>
			
		</table>
		<%
			if(permissionIds.indexOf(",5,") != -1)
			{
		%>
			<div class="over">
				<div style="overflow:auto;margin-top:0px;" class="eventstrees">
					<ul>
						<li id="query_2">
							<a href="javascript:action('2','img_query_assetGroup');"><img src="/soc/images/u321_normal.gif" id="img_query_assetGroup2"></a>
							<a href="javascript:action('2','img_query_assetGroup')">&nbsp;<span class="eventext" style="margin-left: -12px;"> 策略管理</span></a>
							
							
							<ul  class="disply">
								<li style="margin-top: 5px">
									<a href="#" class="common_node"
									onclick="parent.mainFrame.location.href='${ctx}/passwordPolicy/query.action?startIndex=0';">
									<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span style="vertical-align:top">密码策略</span></a>
								</li>
							</ul>
							
							
							<ul  class="disply">
								<li>
									<a href="#" class="common_node"
									onclick="parent.mainFrame.location.href='${ctx}/timePolicy/query.action';">
									<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span style="vertical-align:top">时间策略 </span></a>
								</li>
							</ul>
							
							<ul  class="disply">
								<li>
									<a href="#" class="common_node"
									onclick="parent.mainFrame.location.href='${ctx}/addressPolicy/query.action';">
									<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span style="vertical-align:top">地址策略</span></a>
								</li>
							</ul>
							
						</li>
					</ul>
				</div>
			</div>
		<%
			}
		%>
	</div>
	
</body>
</html>
