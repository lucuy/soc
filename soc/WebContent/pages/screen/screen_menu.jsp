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
//
	function showcomprehensive()
	{

	 $("#showcomprehensive").attr("href",'${ctx}/indexscreen/queryAll.action');
	}
	
//请求安全态势综合展现	
	function facility()
	{
	 $("#facility").attr("href",'${ctx}/comprehensive/queryAll.action');
	}
	
//设备事件分布
    function spread()
    {
     $("#spread").attr("href",'${ctx}/comprehensive/querybigfacility.action');
    }

//安全事件统计
     function statistics()
     {
      $("#statistics").attr("href",'${ctx}/comprehensive/querybigsafety.action');
     }
     
// 全局域 风险变化趋势     
     function risk()
     {
      $("#risk").attr("href",'${ctx}/comprehensive/querybigrisk.action');
     }
// 子域风险变化趋势
     function sonrisk() {
      $("#sonrisk").attr("href",'${ctx}/comprehensive/querybigsonrisk.action');
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
					<td class="leftDhxx">大屏展示</td>
				</tr>
			</table>
		</div>

		<table width="99%" border="0" cellspacing="10" cellpadding="10"
			style="margin-left:6px">
			<%
			    String permissionIds = (String)session.getAttribute("SOC_LOGON_PERMISSIONS");
			    if (permissionIds.indexOf(",184,") != -1)
			    {
			%>
			<tr>

				<td align="left"><a id="showcomprehensive" href="" class="hand"
					target="_blank" onclick="showcomprehensive();"> &nbsp; <img
						src="${ctx}/images/arrow_03.gif" />&nbsp;综合监控 </a></td>
			</tr>
			<%
			    }
			    
			    if (permissionIds.indexOf(",185,") != -1)
			    {
			%>
			<tr align="left">
				<td><a id="facility" href="" class="hand" target="_blank"
					onclick="facility();"> &nbsp; <img
						src="${ctx}/images/arrow_03.gif" />&nbsp;安全态势综合展现 </a></td>
			</tr>
			<%
			    }
			    
			    if (permissionIds.indexOf(",186,") != -1)
			    {
			%>
			<tr align="left">
				<td><a id="spread" href="" class="hand" target="_blank"
					onclick="spread();"> &nbsp; <img
						src="${ctx}/images/arrow_03.gif" />&nbsp;设备事件分布</a></td>
			</tr>
			<%
			    }
			    if (permissionIds.indexOf(",187,") != -1)
			    {
			%>
			<tr align="left">
				<td><a id="statistics" href="" class="hand" target="_blank"
					onclick="statistics();"> &nbsp; <img
						src="${ctx}/images/arrow_03.gif" />&nbsp;安全事件统计</a></td>
			</tr>
			<%
			    }
			    
			    if (permissionIds.indexOf(",188,") != -1)
			    {
			%>
			<tr align="left">
				<td><a id="risk" href="" class="hand" target="_blank"
					onclick="risk();"> &nbsp; <img src="${ctx}/images/arrow_03.gif" />&nbsp;全局域风险变化趋势
				</a></td>
			</tr>
			<%
			    }
			    if (permissionIds.indexOf(",189,") != -1)
			    {
			%>
			<tr align="left">


				<td><a id="sonrisk" href="" class="hand" target="_blank"
					onclick="sonrisk();"> &nbsp; <img
						src="${ctx}/images/arrow_03.gif" />&nbsp;子域风险变化趋势 </a></td>
			</tr>
			<%
			    }
			%>
		</table>
	</div>
</body>
</html>
