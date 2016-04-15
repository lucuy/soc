<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<%@page import="com.soc.model.user.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html>
<head>
<link href="${ctx}/styles/asset/assetinfo.css" rel="stylesheet"
	type="text/css">

<link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet"
	type="text/css">
<%-- <link href="${ctx}/styles/jquery-ui-1.8.21.css" rel="stylesheet" type="text/css"> --%>

<link href="${ctx}/styles/jquery.popuplayer.css" rel="stylesheet"
	type="text/css">

<link href="${ctx}/styles/fontColor.css" rel="stylesheet"
	type="text/css">

<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx }/scripts/jquery-latest.js"></script>
<script type="text/javascript"
	src="${ctx }/scripts/jquery.tablesorter.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
<script type='text/javascript' src="${ctx}/scripts/popuplayer.js"></script>

<style type="text/css">

.eventslist {
	 background: none repeat scroll 0 0 #EBECF1;
}

.eventslist tr td {
	line-height: 28px;
	text-align: center;
}


.eventslist .biaoti {
	background: url("/soc/images/tdBg.jpg") repeat-x scroll 0 0 transparent;
    height:28px;
    color: black;
    font-weight: bold;
    text-align: center;
}



.eventslist thead tr .header{

	background-image: url(/soc/images/sortArrows_bg.gif);
	background-repeat: no-repeat;
	background-position: center right;
	cursor: pointer;
}
.eventslist thead tr .headerSortUp {
	background-image: url(/soc/images/sortArrows_asc.gif);
}
.eventslist thead tr .headerSortDown {
	background-image: url(/soc/images/sortArrows_desc.gif);
}
.eventslist thead tr .headerSortDown , .eventslist thead tr .headerSortUp{
	background-color: #8dbdd8;
}

.hand {
	cursor: pointer;
	background: #ccccff;
}

.back {
	background: #FFFFFF;
}
</style>
<script language="javascript">
	var rege = /^([^`~!@#$%^&*()=|{}':;, \\\[\]<>\/?~！@#￥……&*（）—|{}【】‘；：”“'。\"，、？])*$/;
	//快速检索
	function query() {
		//关键字
		$('#keyword').val($.trim($('#keyword').val()));

		if ($('#keyword').val().length > 50) {
			alert("搜索长度不能大于50");
			$('#keyword').val('');
			$('#keyword').focus();
			return;
		}
		if (!rege.test($('#keyword').val())) {
			alert("输入的内容包含特殊字符，请重新输入");
			$('#keyword').val('');
			$('#keyword').focus();
			return;
		}

		location.href = "${ctx}/monitorGroup/queryMonitorAlarmForList.action?startIndex="
				+ encodeURI(0, "utf-8")
				+ "&keyword="
				+ encodeURI(encodeURI($('#keyword').val(), "utf-8"));
	}

	$(document).ready(function() {
		$('#listTable').tablesorter();

		$("#keyword").keydown(function(event) {
			if (event.keyCode == 13) {
				query();
			}
		});
	});

	function gotoInfo(assetId) {
		//assetGroupIds
	//	location.href =  "${ctx}/monitorGroup/queryAssetInfo.action?assetId=" + assetId ; 
	//  window.open('${ctx}/monitorGroup/queryInfo.action?assetMac='+assetMac+'&assetId='+assetId+'&assetSupportDeviceId='+assetSupportDeviceId);
	    
		$.post("${ctx}/asset/queryAssetById.action",{"mac" : assetId},function(obj){
			
			if(obj == "" || obj == null){
				alert("资产可能已经被管理员删除 ");
				return ;
			}
			
			window.open("${ctx}/monitorGroup/queryInfo.action?assetMac="+obj.assetMac+"&assetId="+obj.assetId+"&assetSupportDeviceId="+obj.assetSupportDeviceId);
			
		});
	
		
	}
</script>
</head>

<body style="margin-top:2px;">
	<s:form action="queryMonitorAlarmForList.action"
		namespace="/monitorGroup" method="post" theme="simple" id="queryForm"
		name="monitorAlarm">
		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 6px; margin-top: 0px">

			<tr>
				<td></td>
			</tr>
			<tr>
				<td>
					<div class="box">
						<span class="kuaiju">快速搜索</span> <input type="text" id="keyword"
							value="${keyword}" name="keyword" class="jianju"
							style="height: 15px;" /> <img src="${ctx}/images/search.jpg"
							onclick="query();" style="margin-left:5px">
						<!-- 
							<a href="#" class="jianju"
							onclick="extQueryDlg();">高级</a>
							 -->
					</div></td>

			</tr>
			<tr>
				<td>
					<div class="sbox">
						<div class="cont">
							<!-- information area -->
							<div id="dataList">
								<table width="100%" border="0" cellspacing="1" cellpadding="0"
									class="eventslist" id="listTable">
									<thead>
										<tr height="28" class="biaoti">
											<th width="15%" class="biaoti">等级</th>
											<th width="15%" class="biaoti">监控指标</th>
											<th width="20%" class="biaoti">告警内容</th>
											<th width="15%" class="biaoti">当前值</th>
											<th width="15%" class="biaoti">阀值</th>
											<th width="" class="biaoti">时间</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${monitorAlarmList }" var="ma">
											<tr class="back" ondblclick="gotoInfo(${ma.monitorAlarmAssetId});"
												onmousemove="this.className='hand'"
												onmouseout="this.className='back'">
												<td valign="middle" align="center">${ma.monitorAlarmRank
													}</td>
												<td valign="middle" align="center">${ma.monitorAlarmIndex
													}</td>
												<td valign="middle" align="center">${ma.monitorAlarmContent
													}</td>
												<td valign="middle" align="center">${ma.monitorAlarmCurrentValue
													}</td>
												<td valign="middle" align="center">${ma.monitorAlarmThreshold
													}</td>
												<td valign="middle" align="center">${ma.monitorAlarmTimes
													}</td>
											</tr>
										</c:forEach>
									</tbody>
									<tr style="background-color:#FFFFFF; ">
										<td colspan="7" width="100%"><jsp:include
												page="../commons/page.jsp"></jsp:include></td>
									</tr>
								</table>

							</div>
						</div>
					</div></td>
			</tr>
		</table>
	</s:form>

</body>
</html>
