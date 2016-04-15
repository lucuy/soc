<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8" import="java.util.*"%>
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
<style type="text/css">
.common_node1{cursor:pointer;margin-left: 25px;}
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
       
</script>
	</head>

	<body onload="dwr.engine.setActiveReverseAjax(true);dwr.engine.setNotifyServerOnPageUnload(true);onLoad();">
	 <div class="titleTop">
	  	 <div style="background-image:url(${ctx}/images/leftDh.jpg)">
	   	  <table width="97%" border="0" cellspacing="0" cellpadding="0">
	          <tr>
	            <td width="25px"><img src="${ctx}/images/jtTitle.jpg" alt="" width="25" height="28" /></td>
	            <td class="leftDhxx">风险管理</td>
	          </tr>
	      </table>       
	    </div>
	    
		<table width="99%" border="0" border="0" cellspacing="10" cellpadding="10"
			style="line-height: 15px;margin-left:-25px;">
			<%
				String permissionIds = (String)session.getAttribute("SOC_LOGON_PERMISSIONS");
				if(permissionIds.indexOf(",25,") != -1)
				{
			%>
			<tr >
				<td  align="left">
					<a href="#" class="common_node1"
						onclick="parent.mainFrame.location.href='${ctx}/assetRiskGroup/queryAssetRiskEvaluation.action';">
						&nbsp;
						<img src="${ctx}/images/arrow_03.gif"/>
						<span style="vertical-align:2.8px">资产风险赋值</span>
					</a>
				</td>
			</tr>
			<tr >
				<td  align="left">
					<a href="#" class="common_node1"
						onclick="parent.mainFrame.location.href='${ctx}/assetRiskValue/queryRisk.action';">
						&nbsp;
						<img src="${ctx}/images/arrow_03.gif"/>
						<span style="vertical-align:2.8px">威胁信息</span>
					</a>
				</td>
			</tr>
			<tr>
				<td  align="left">
					<a href="#" class="common_node1"
						onclick="parent.mainFrame.location.href='${ctx}/vulnerabilityAssessment/query.action';">
						&nbsp;
						<img src="${ctx}/images/arrow_03.gif"/>
						<span style="vertical-align:2.8px;">风险脆弱性评估</span>
					</a>
				</td>
			</tr>
			
			<tr >
				<td  align="left">
					<a href="#"class="common_node1"
						onclick="parent.mainFrame.location.href='${ctx}/assetRiskGroup/query.action';">
						&nbsp;
						<img src="${ctx}/images/arrow_03.gif"/>
						<span style="vertical-align:2.8px;">资产风险监控</span>
					</a>
				</td>
			</tr>
			<%
				}
			%>
			</table>
			</div>
	</body>
</html>