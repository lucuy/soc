
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8" import="java.util.*"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<%@page import="com.soc.model.user.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>



<html>
<head>
<%-- <link href="${ctx}/styles/asset/asset.css" rel="stylesheet"
	type="text/css"> --%>

<link href="${ctx}/styles/left.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/tree.css" rel="stylesheet" type="text/css">
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
<script type='text/javascript' src="${ctx}/scripts/treeview.js"></script>
<script type="text/javascript" src="${ctx}/dwr/engine.js"></script>
<script type="text/javascript" src="${ctx}/dwr/interface/MessagePush.js"></script>
<script type='text/javascript' src="${ctx}/dwr/util.js"></script>
<script type="text/javascript" src="${ctx}/scripts/dwrManager.js"></script>
  <link type="text/css" href="${ctx}/styles/css/ui-lightness/jquery-ui-1.7.3.custom.css" rel="stylesheet" /> 
<style type="text/css">
.evnTitleTop {
	margin-left: 6px;
	margin-top: 2px;
	border: 1px solid #E3E6EB;
	width: 180px;
	height: 528px;
	/* background-color:#EEF1F8; */
	float: left;
	padding-bottom: 10px;
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
	overflow-x: auto;
	overflow-y: auto;
	white-space: nowrap;
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
	margin: 5px 0px 5px 0px;
}

.eventstrees ul li {
	cursor: hand;
	width: auto;
}
</style>
<script type="text/javascript">
	function onLoad() {
		var userId = '${userinfo}';
		MessagePush.pageOnLoad(userId);
	}

	function showMessage(val) {
		if (val != '-1') {
			parent.frames[0].LoadTimer();
			alert(val);
		} else {
			parent.frames[0].NewMessage();
		}

	}
	dwr.engine.setErrorHandler(function(){});
/**function showmessage(val){
   		
   	}*/
	jQuery(document).ready(function() {
		$("#group").attr("style", "display:none");
		$("#group1").attr("style", "display:none");
		$("#imgline").attr("style", "display:none");
		$("#s1").hide();
		if ('${isOnclick}' == "true") {
			$("#group").attr("style", "display:block");
			$("#group1").attr("style", "display:block");
			$("#imgline").attr("style", "display:block");
			$("#s1").show();
			$("#task").attr("style", "display:none");
		}
	});

	function showAsset(id) {
		parent.mainFrame.location.href = '${ctx}/asset/queryAsset.action?assetGroupId='
				+ id;
	}
	
	 function reload() {
	        document.onmousedown = ContextMenu;
	        $("#mack").addClass("ui-widget-overlay");
	        $("#mack").css("height",document.documentElement.clientHeight);
	    }
	 function ContextMenu() {

	        if (event.button == 2 || event.button == 3) {
	            alert("探测中无法操作");
	            return false;
	        }
	    }
	function show(url) {
		parent.mainFrame.location.href = url;
	}

	function action(elementId, eventsType) {
		var temp = eventsType + elementId;
		// alert(temp);

		changeIcon($("#" + eventsType + elementId + ""));

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

	function linkTo(type, queryid) {
		if (type == "3") {
			parent.mainFrame.location.href = '${ctx}/asset/queryAsset.action?collectorId='
					+ queryid;
		} else if (type == "1") {
			parent.mainFrame.location.href = '${ctx}/asset/queryAsset.action?assetSegMent='
					+ queryid;
		}

	}
	function showAssetGroup(id) {
		parent.mainFrame.location.href = '${ctx}/asset/queryAsset.action?assetGroupId='
				+ id;
	}
	function issuedAsset(){
		parent.mainFrame.location.href = "${ctx}/asset/issuedAsset.action?sortType=ASSET_ISSUED" ; 
	}
	
	 function cancel() {
			document.onmousedown = null;
			$("#mack").removeClass("ui-widget-overlay");
			$("#mack").css("height",0);
	}
	
</script>
</head>

<body
	onload="dwr.engine.setActiveReverseAjax(true);dwr.engine.setNotifyServerOnPageUnload(true);onLoad();">
	<div class="evnTitleTop" style="overflow:auto; overflow-x:hidden">
		<%
			String permissionIds = (String) session
					.getAttribute("SOC_LOGON_PERMISSIONS");
			if (permissionIds.indexOf(",7,") != -1) {
		%>
		<div style="background-image:url(${ctx}/images/leftDh.jpg)">
			<table width="97%" border="0" cellspacing="0" cellpadding="0">
				<tr id="group">
					<td width="25px"><img src="${ctx}/images/jtTitle.jpg" alt=""
						width="25" height="28" /></td>
					<td class="leftDhxx">资产组信息</td>
					<td style="margin-left:-15px;"><a target="mainFrame"
						href="${ctx}/assetGroup/editAssetGroup.action"><span
							class="manager">管理</span> </a></td>
				</tr>
			</table>
		</div>

		<%--<div style="overflow:auto;" id="s1">
			<table width="99%" border="0" cellspacing="0" cellpadding="0"
				style="margin-top: 10px;">
				<tr id="group1" style="margin-left:0px;">
					<td width="100%">
						<div>
							<div class="common_node"
								onclick="show('${ctx}/asset/queryAsset.action')">
								<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span
									style="vertical-align:top">所有资产</span>

							</div>
							<div class="common_node"
								onclick="show('${ctx}/asset/queryAsset.action?assetGroupId=0')">
								<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span
									style="vertical-align:top">未分组</span>
							</div>

							<!-- <div style="width:auto; margin: 0, 0, 0, 0;overflow-x:auto;"> -->

							<!-- </div> -->
						</div>
					</td>
				</tr>
				<tr>
			</table>
		</div>
		--%>
		<div style="overflow:auto;margin-top:8px;" class="eventstrees">${htmlBuffer}</div>


		<!-- 资产下发功能 -->
		<br>
		<div style="background-image:url(${ctx}/images/leftDh.jpg)">
			<table width="97%" border="0" cellspacing="0" cellpadding="0">
				<tr id="group">
					<td width="25px"><img src="${ctx}/images/jtTitle.jpg" alt=""
						width="25" height="28" /></td>
					<td class="leftDhxx">资产下发功能</td>
				</tr>
			</table>
		</div>
		
		<div style="overflow:auto;margin-top:8px;" class="eventstrees">
			<ul>
				<li><a href="javascript:issuedAsset();"> <img
						src="/soc/images/arrow_03.gif"> </a><a
					href="javascript:issuedAsset();"> <span class="eventext">
							&nbsp;资产下发 </span> </a></li>
			</ul>
		</div>
		
		<!-- 按监控域分组 
		<div style="background-image:url(${ctx}/images/leftDh.jpg)">
			<table width="97%" border="0" cellspacing="0" cellpadding="0"
				style="margin-top: 10px;">
				<tr id="group"  style="margin-left:0px;">
					<td width="25px"><img src="${ctx}/images/jtTitle.jpg" alt=""
						width="25" height="28" /></td>
					<td class="leftDhxx">采集器分组</td>
				</tr>
			</table>
		</div>
		<div class="eventstrees">${treeQuery}</div>

		
		按IP分组
		<div style="background-image:url(${ctx}/images/leftDh.jpg)">
			<table width="97%" border="0" cellspacing="0" cellpadding="0" 
				style="margin-top: 10px;">
				<tr id="group" style="margin-left:0px;">
					<td width="25px"><img src="${ctx}/images/jtTitle.jpg" alt=""
						width="25" height="28" /></td>
					<td class="leftDhxx">IP分组</td>
				</tr>
			</table>
		</div>
		<div class="eventstrees">${treeIpQuery}</div>
		 -->

	
	 <div style="background-image:url(${ctx}/images/leftDh.jpg)">
			<table width="97%" border="0" cellspacing="0" cellpadding="0">
				<tr id="group">
					<td width="25px"><img src="${ctx}/images/jtTitle.jpg" alt=""
						width="25" height="28" />
					</td>
					<td class="leftDhxx">资产探测分组</td>
				</tr>
			</table>
		</div>  
	 <div style="overflow:auto;" id="s1"> 
		<table width="99%" border="0" cellspacing="0" cellpadding="0"
				style="margin-top: 10px;">
				<tr id="group1" style="margin-left:0px;">
					<td width="100%">
						<div>
							<div class="common_node"
								onclick="show('${ctx}/assetProbeTask/queryTask.action')">
								<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span class="eventext">&nbsp;资产探测</span>
								
							</div>

							<!-- <div style="width:auto; margin: 0, 0, 0, 0;overflow-x:auto;"> -->

							<!-- </div> -->
						</div></td>
				</tr>
				<tr>
			</table> 	
		 </div>
		 
		<!--  <div style="overflow:auto;" id="s2"> 
		  <table width="99%" border="0" cellspacing="0" cellpadding="0"
				style="margin-top: 10px;">
				<tr id="group2" style="margin-left:0px;">
					<td width="100%">
						<div>
							<div class="common_node"
								onclick="show('${ctx}/asset/showTopo.action')">
								<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span class="eventext">&nbsp;拓扑发现</span>
								
							</div>

							<div style="width:auto; margin: 0, 0, 0, 0;overflow-x:auto;">

							 </div>
						</div></td>
				</tr>
				<tr>
			</table> 	
		 </div>    --> 
		<%
			}
		%>
	</div>
	<div class="ui-overlay">
        <div id="mack"></div>
     
    </div>
</body>
</html>

