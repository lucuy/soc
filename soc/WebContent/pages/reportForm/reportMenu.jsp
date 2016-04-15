<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>

<html>
	<head>
	<link href="${ctx}/styles/left.css" rel="stylesheet" type="text/css">
		 <link href="${ctx}/styles/tree.css" rel="stylesheet" type="text/css">
		<script type='text/javascript'
			src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
		<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
		<script type="text/javascript" src="${ctx}/dwr/engine.js"></script>
<script type="text/javascript" src="${ctx}/dwr/interface/MessagePush.js"></script>
<script type='text/javascript' src="${ctx}/dwr/util.js"></script>
<script type="text/javascript" src="${ctx}/scripts/dwrManager.js"></script>
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
			function to(url){
				
				parent.mainFrame.location.href=url;
			}
		</script>
	</head>

	<body onload="dwr.engine.setActiveReverseAjax(true);dwr.engine.setNotifyServerOnPageUnload(true);onLoad();">
	<div class="titleTop">
			<div style="background-image:url(${ctx}/images/leftDh.jpg)">
				<table width="97%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="25px">
							<img src="${ctx}/images/jtTitle.jpg" alt="" width="25"
								height="28" />
						</td>
						<td class="leftDhxx">
							报表信息
						</td>
					</tr>
				</table>
			</div>
			
			<table width="99%" width="99%" border="0" cellspacing="10" cellpadding="10"
			style="line-height:15px;margin-left: 10px">
					<%
						String permissionIds = (String)session.getAttribute("SOC_LOGON_PERMISSIONS");
						if(permissionIds.indexOf(",16,") != -1)
						{
					%>
					<tr >
						<td align="left" >
								<a href="#" onClick="to('${ctx}/reportFormQuery.do?method=initPage');">&nbsp;<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span style="vertical-align:20%">报表管理</span></a>
						</td>
					</tr>
					<%
						}
						
						if(permissionIds.indexOf("17") != -1)
						{
					%>
					<tr >
						<td align="left" >
								<a href="#" onClick="to('${ctx}/queryStat.do?method=initPage');">&nbsp;<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span style="vertical-align:20%">报表统计</span></a>
						</td>
					</tr>
					<%
						}
						
						if(permissionIds.indexOf("18") != -1)
						{
					%>
					<tr >
						<td align="left" >
								<a href="#" onClick="to('${ctx}/reportTime.do?method=initPage');">&nbsp;<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span style="vertical-align:20%">定时报表</span></a>
						</td>
					</tr>
					<%
						}
						
						if(permissionIds.indexOf("19") != -1)
						{
					%>
					<tr >
						<td align="left" >
							<a href="#" onClick="to('${ctx}/reportCustom.do?method=initPage');">&nbsp;<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span style="vertical-align:20%">自定义报表管理</span></a>
						</td>
					</tr>
					<%
						}
					%>
						
		</table>
		</div>
	</body>
</html>
