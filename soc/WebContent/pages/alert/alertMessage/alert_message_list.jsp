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
<title>安全管理平台综合监控</title>
<link href="${ctx}/images/favicon.ico" rel="shortcut icon" />
<link href="${ctx}/images/favicon.ico" rel="Bookmark" />
<%-- <script type="text/javascript" src="${ctx}/pushlet/ajax-pushlet-client.js"></script>--%>

<link href="${ctx}/styles/audit/audit.css" rel="stylesheet"
	type="text/css">
<link href="${ctx}/styles/user/user.css" rel="stylesheet"
	type="text/css">
<link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet"
	type="text/css">
<%-- <link href="${ctx}/styles/jquery-ui-1.8.21.css" rel="stylesheet" type="text/css"> --%>

<link href="${ctx}/styles/jquery.popuplayer.css" rel="stylesheet"
	type="text/css">

<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx }/scripts/jquery-latest.js"></script>
<script type="text/javascript"
	src="${ctx }/scripts/jquery.tablesorter.js"></script>

<script type='text/javascript'
	src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
<script type='text/javascript' src="${ctx}/scripts/popuplayer.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine.js"></script>
<style type="text/css">
body {
	padding: 0px 0px 0px 0px;
	maring: 0px 0px 0px 0px;
}

.conn {
	padding: 0px 0px 0px 0px;
	margin: 2px 0px 0px 0px;
	position: absolute;
	z-index: -1;
	width: 99%;
	"
}

.box {
	margin: 0px auto;
	border: 1px solid #c1ddf1;
	background: url(/soc/images/jetsen/rightDh.jpg) repeat-x 0 0;
	height: 33px;
	line-height: 30px;
	text-align: left;
}

.sbox {
	clear: both;
	margin-bottom: 10px;
	overflow: hidden;
}

.hand {
	cursor: hand;
	background: #ccccff;
}

.back {
	background: #FFFFFF;
}
/*
.eventslist {
	background: none repeat scroll 0 0 #EBECF1;
	padding: 0px 0px 0px 0px;
	margin: 0px 0px 0px 0px;
}

.eventslist tr td {
	line-height: 28px;
	text-align: center;
}

.eventslist .biaoti {
	background: url("/soc/images/tdBg.jpg") repeat-x scroll 0 0 transparent;
	height: 28px;
	color: black;
	font-weight: bold;
	text-align: center;
}
*/
.eventslist {
	background: none repeat scroll 0 0 #EBECF1;
}

.eventslist tr td {
	line-height: 28px;
	text-align: center;
}

.eventslist .biaoti {
	background: url("/soc/images/tdBg.jpg") repeat-x scroll 0 0 transparent;
	height: 28px;
	color: black;
	font-weight: bold;
	text-align: center;
}

<%--
<
排序箭头样式> --%> .eventslist thead tr .header {
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

.eventslist thead tr .headerSortDown,.eventslist thead tr .headerSortUp
	{
	background-color: #8dbdd8;
}

.hideDiv {
	padding: 0px 0px 0px 0px;
	margin: 0px 0px 0px 5px;
	background-image: url("${ctx}/images/hide.png");
	background-repeat: 0% 0%;
	background-repeat: repeat;
	background-attachment: scroll;
	/*     width:99%;
    height:100%; */
	position: absolute;
	float: left;
	z-index: 1;
	filter: progid:DXImageTransform.Microsoft.Alpha(style=1, opacity=20,
		finishOpacity=20 );
}

.conn {
	margin: 2px 0px 0px 0px;
	position: absolute;
	z-index: -1;
	width: 99%;
}

.refresh {
	text-align: center;
	line-height: 28px;
}

.dialog-enentsContent {
	overflow: auto;
	scrollbar-face-color: #FFFFFF;
	scrollbar-shadow-color: #D2E5F4;
	scrollbar-highlight-color: #D2E5F4;
	scrollbar-3dlight-color: #FFFFFF;
	scrollbar-darkshadow-color: #FFFFFF;
	scrollbar-track-color: #FFFFFF;
	scrollbar-arrow-color: #D2E5F4
}

.btnstyle {
	background: url(/soc/images/btnbg.jpg) no-repeat;
	border: none;
	width: 66px;
	height: 21px;
	cursor: pointer;
	color: #265D86;
	align: center;
}

.column {
	padding: 0px 0px 0px 0px;
	margin: 0px 0px 0px 0px;
	border-left: 1px solid #D2E8FA;
	border-top: 1px solid #D2E8FA;
	border-right: 1px solid #D2E8FA;
}

.title {
	margin: 0px 0px 0px 0px;
	padding: 0px 0px 0px 0px;
	color: #000000;
	background: url(/soc/images/tdBg.jpg) repeat-x left;
	height: 31px;
}

.title_t {
	position: relative;
	left: 10px;
	top: 5px;
}

.img_a {
	vertical-align: -5px;
	cursor: hand;
}

.tit {
	margin: 0px 0px 0px 5px;
}

.rowLT {
	border-bottom: 1px solid #D2E8FA;
	padding: 5px 5px 5px 5px;
	width: 100px;
	background: #F3F9FF;
}

.rowLV {
	border-bottom: 1px solid #D2E8FA;
	border-left: 1px solid #D2E8FA;
	border-right: 1px solid #D2E8FA;
	padding: 5px 5px 5px 5px;
	width: 250px;
}

.rowRT {
	border-bottom: 1px solid #D2E8FA;
	padding: 5px 5px 5px 5px;
	width: 100px;
	background: #F3F9FF;
}

.rowRV {
	border-bottom: 1px solid #D2E8FA;
	border-left: 1px solid #D2E8FA;
	padding: 5px 5px 5px 5px;
}

.level {
	padding: 0px 0px 0px 0px;
	margin: 2px 2px 2px 4px;
	border: 1px solid #CCCCC0;
	height: 12px;
	width: 40px;
}

.levelBa {
	height: 12px;
	margin: 0px 0px 0px 0px;
}

.img_details {
	cursor: hand;
}

.loding {
	font-size: 14px;
	border: 3px solid #D2E8FA;
	position: absolute;
	z-index: 10;
	background: #FFFFFF;
	width: 200px;
	height: 40px;
	padding-top: 25px;
	text-align: center;
	maring: 0px auto;
	display: none;
}
</style>
<script language="javascript">
//验证字符串是否包含特殊字符的正则
var rege =/^([^`~!@#$%^&*()=|{}':;, \\\[\]<>\/?~！@#￥……&*（）—|{}【】‘；：”“'。\"，、？])*$/;
var queryFlag ;
	//搜索
	function query() {

		$('#keyword').val($.trim($('#keyword').val()));
		//验证字符串长度
		if($('#keyword').val().length>50){
			alert("搜索长度不能大于50");
			$('#keyword').val('');
			$('#keyword').focus();
			return ;
		}
		//验证是否包含特殊字符
		if(!rege.test($('#keyword').val())){
			alert("输入的内容包含特殊字符，请重新输入");
			$('#keyword').val('');
			$('#keyword').focus();
			return;
		}
		location.href = "${ctx}/alertMessage/alertMessageQuery.action?keyword="
				+ encodeURI(encodeURI($('#keyword').val(), "utf-8"));
	}
	
	//全选及反选
	$("#chkAll").live("click", function(event) {
		if ($("#chkAll").hasClass('not_checked')) {
			$("#chkAll").removeClass('not_checked');
			$(".check-box").attr('checked', true);
		} else {
			$("#chkAll").addClass('not_checked');
			$(".check-box").attr('checked', false);
		}
	});
	
	$(document).ready(function() {
		$('#keyword').keydown(function(event) {
			if (event.keyCode == 13) {
				query();
			}
		});
		
		
		//导出
		$('#dialog-extQuery1').dialog({
			autoOpen : false,
			width : 480,
			height : 200,
			buttons: {
				"取消[Esc]": function() {
					$(this).dialog("close");
				}
			}
		});

		// Dialog			
		$('#dialog-extQuery').dialog({
			autoOpen : false,
			width : 450,
			height : 240,
			buttons : {
				"查询[Enter]" : function() {
					$('#keyword').val("");
					extQuery();
					$(this).dialog("close");
				},

				"取消[Esc]" : function() {
					$(this).dialog("close");
				}
			}
		});

		$('#dialog-enentsContent').dialog({
			autoOpen : false,
			width : 800,
			height : 480,
			beforeclose : function(event, ui) {
				$("#hideDiv").removeClass("hideDiv");$("#hideDiv").css("width",0);$("#hideDiv").css("height",0); 
				$("#loding").hide();
			}
		});
		
	  queryFlag = "${queryFlag}" ; 
	  //判断请求是首页点击的链接查询最新的10条告警
	  if(queryFlag == "'q'"){
		  $('#pageJsp').hide();
		  $('#exportButton').hide();
	  }
      $('#listTable').tablesorter();
	});

	function extQueryDlg() {
		$('#dialog-extQuery').dialog('open');
	}
	
	
	function extQuery() {
		//验证字符串长度
		if($('#sRuleName').val().length>50){
			alert("搜索长度不能大于50");
			$('#sRuleName').val('');
			$('#sRuleName').focus();
			return ;
		}
		//验证是否包含特殊字符
		if(!rege.test($('#sRuleName').val())){
			alert("输入的内容包含特殊字符，请重新输入");
			$('#sRuleName').val('');
			$('#sRuleName').focus();
			return;
		}
		//验证字符串长度
		if($('#ruleUpdateTime').val().length>50){
			alert("搜索长度不能大于50");
			$('#ruleUpdateTime').val('');
			$('#ruleUpdateTime').focus();
			return ;
		}
		//验证是否包含特殊字符
		if(!rege.test($('#ruleUpdateTime').val())){
			alert("输入的内容包含特殊字符，请重新输入");
			$('#ruleUpdateTime').val('');
			$('#ruleUpdateTime').focus();
			return;
		}
		$('#receiver').val($.trim($('#sRuleName').val()));
		$('#sendway').val($.trim($('#ruleUpdateTime').val()));
		$('#rel_deviceType').val($.trim($('#driverType').val()));
		$('#alertRank').val($.trim($('#ruleRank').val()));
		
		$('#alertMessage').submit();
	}

	//关闭事件详情
	function closeContent() {
		$('#dialog-enentsContent').dialog("close");
	}

	//事件详情内容
	function detailsContent(eventsLogIdentification, tableName) {
		var docHe = ($(document).height() / 2) - 40;
		var docWi = ($(document).width() / 2) - 200;
		$("#hideDiv").addClass("hideDiv");
		$("#hideDiv").css({
			width : $(document).width() - 22,
			height : $(document).height()
		});
		$("#loding").toggle("slow");
		$("#loding").css({
			top : docHe,
			left : docWi
		});

		$.ajax({
			type : "POST",
			url : "${ctx}/events/eventsDetails.action",
			async : true,
			dataType : "JSONObject",
			data : "eventsLogIdentification=" + eventsLogIdentification
					+ "&tableName=" + tableName,
			success : function(eventsResult) {
				var obj = jQuery.parseJSON(eventsResult);
				if (obj != null) {
					   $("#occurTime").text(obj.receptTimes);
                       $("#sendTime").text(obj.sendTimes);
                       $("#priority").text(obj.priority);
                       $("#type").text(obj.typeCustomd);
                       $("#count").text(obj.aggregatedCount);
                       $("#name").text(obj.nameCustomd);
                       $("#customs4").text(obj.customs4);
                       $("#msg").text(obj.msg);
                       $("#sAdd").text(obj.sAdd);
                       $("#sPort").text(obj.sPort);
                       $("#sUser").text(obj.suserName);
                       $("#tAdd").text(obj.tAdd);
                       $("#digest").text(obj.nameCustomd);
                       $("#tPort").text(obj.dPort);
                       $("#tUser").text(obj.dUserName);
                       $("#category").text(obj.cateGoryCustomd);
                       $("#devName").text(obj.customs5);
                       $("#devCategory").text(obj.devCategory);
                       $("#action").text(obj.action);
                       $("#result").text(obj.result);
                       $("#dUserName").text(obj.dUserName);
                       $("#devAddr").text(obj.devAddT);
                       $("#devVendor").text(obj.devVendor);
                       $("#devType").text(obj.devproduct);
                       $("sensorName").text(obj.sensorName);
				}
				$("#loding").toggle("slow");
				$('#dialog-enentsContent').dialog('open');
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				if (textStatus != "") {
					alert("请求错误刷新后重试");
					$("#hideDiv").removeClass("hideDiv");$("#hideDiv").css("width",0);$("#hideDiv").css("height",0); 
					$("#loding").hide();
				}
			}
		});

	}

	//导出doc的方法
	function Export(reportType){
		$("#reportType").val(reportType);
		var ids = "";
		$("[name=ids]:checkbox:checked").each(function() {
			if (ids != "")
			   {
				ids += "," + $(this).val();
				
				}
			else
				{
				
				ids = $(this).val();
				
				}

		});
		$("#checkids").val(ids);
		//document.getElementById("key").value=encodeURI(encodeURI("告警信息数据表", "utf-8"));
		if(ids == ""){
			if(confirm("你是要导出全部的告警信息...")){
				location.href = "${ctx}/alertMessage/ExportReport.action?ids="+ ids+"&alertReportId="+11+"&reportType=doc"
						+"&keyword="+ encodeURI(encodeURI($('#keyword').val(), "utf-8"))+"&alertReceiver="+$.trim($('#receiver').val())
						+"&alertSendway="+$.trim($('#sendway').val())+"&alertRank="+$.trim($('#alertRank').val())+"&rel_deviceType="+$.trim($('#rel_deviceType').val());
			}
		}else{
			if (confirm("你是要导出选中的告警信息...")) {
				location.href = "${ctx}/alertMessage/ExportReport.action?ids="+ ids+"&alertReportId="+11+"&reportType=doc";
						
			}
		}
        $("#dialog-extQuery1").dialog("close");
	}
	
	
	function action(id) {
		changeIcon($("#img_" + id));
		$("#column_" + id + " >ul").toggle("slow");

	}
	
	//导出excel的方法
	function exportExcel() {
		var ids = "";
		$("[name=ids]:checkbox:checked").each(function() {
			if (ids != "")
			   {
				ids += "," + $(this).val();
				
				}
			else
				{
				
				ids = $(this).val();
				
				}

		});
		
		//如果选中了cheAll就弹出提示，否则不弹出
		if(ids == ""){
			if(confirm("你是要导出全部的告警信息...")){
				location.href = "${ctx}/alertMessage/ExportExcel.action?ids="+ ids+"&keyword="
				+ encodeURI(encodeURI($('#keyword').val(), "utf-8"))+"&alertReceiver="+$.trim($('#receiver').val())
				+"&alertSendway="+$.trim($('#sendway').val())+"&alertRank="+$.trim($('#alertRank').val())+"&rel_deviceType="+$.trim($('#rel_deviceType').val());
			}
		}else{
				if (confirm("你是要导出选中的告警信息...")) {
					location.href = "${ctx}/alertMessage/ExportExcel.action?ids="+ ids;
				} 
			
		}
		$("#dialog-extQuery1").dialog("close");
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
	
     function test()
     {  
         var tableName = $("#otableName").val();
       
         var identifation = $("#oidentification").val();
         
         //alert('${ctx}/events/queryOriginalEvent.action?tableName='+tableName+'&identifation='+identifation);
         //parent.parent.location.href='${ctx}/events/queryOriginalEvent.action?tableName='+tableName+'&eventsLogIdentification='+identifation;
     	 $("#test").attr("href", '${ctx}/events/queryOriginalEvent.action?tableName='+tableName+'&eventsLogIdentification='+identifation);
     }
     
     function checkWorkOrder(alertId){

     	$.ajax({
     		type : "POST",
			url : "${ctx}/alertMessage/checkWorkOrder.action",
			async : false,
			dataType : "JSONObject",
			data : "alertID="+alertId,
			success : function(eventsResult) {
				var obj = jQuery.parseJSON(eventsResult);
				if(obj == null){
					alert("工单已删除！");
				}else{
					//$("#checkWorkorder").attr('href','${ctx}/alertMessage/toAddWorkorder.action?alertID="+alertId+"&type=detail');
					location.href = "${ctx}/alertMessage/queryWorkorderByAlertId.action?alertID="+alertId;
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				if (textStatus != "") {
					alert("请求错误刷新后重试");
				}
			}
     	});
     	
     }
     function toadd(alertId){
    	 if(queryFlag == "'q'"){
    		 window.open("${ctx}/alertMessage/toAddWorkorder.action?queryFlag="+queryFlag+"&alertID="+alertId+"&keyword="+ encodeURI(encodeURI($('#keyword').val(), "utf-8")),"工单添加","height=550,width=1200,top=20,left=30,toolbar=no,menubar=no,scrollbars=yes, resizable=no,location=no, status=nocenter=yes,help=no");
    	 }else{
	    	 location.href = "${ctx}/alertMessage/toAddWorkorder.action?alertID="+alertId+"&keyword="
					+ encodeURI(encodeURI($('#keyword').val(), "utf-8"));
    	 }
     }
     
     function flushMethod(){
    	 history.go(0);
     }
     function toClose(alertId){
    	 var alertHtml="已关闭";
    	
    	 $.ajax({
      		type : "POST",
 			url : "${ctx}/alertMessage/closeAlaemMessage.action",
 			async : false,
 			dataType : "text",
 			data : "alertID="+alertId,
 			success : function(eventsResult) {
 				
 				$("#alert_"+alertId).html(alertHtml);
 			},
 			error : function(XMLHttpRequest, textStatus, errorThrown) {
 				if (textStatus != "") {
 					alert("请求错误刷新后重试");
 				}
 			}
      	});
     }
</script>
</head>
<body>
	<div id="conn" class="conn">
		<s:form action="alertMessageQuery" namespace="/alertMessage"
			method="post" theme="simple" id="alertMessage" name="alertMessage">

			<s:hidden id="receiver" name="alertReceiver" />
			<s:hidden id="rel_deviceType" name="rel_deviceType" />
			<s:hidden id="sendway" name="alertSendway" />
			<s:hidden id="alertRank" name="alertRank" />
			<s:hidden id="assetIp" name="assetIp" />

			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				style="margin-left: 4px; margin-top: 2px">
				<!-- 空行-->
				<input type="hidden" id="isMonitorNetworkTopology"
					value="${isMonitorNetworkTopology}" name="isMonitorNetworkTopology" />
				<input type="hidden" id="currentCount" value="${currentCount}"
					name="currentCount" />
				<tr>
					<td></td>
				</tr>
				<tr>
					<td>

						<div class="box">

							<div class="right">
								<input id="exportButton" type="button" value="导出"
									class="btnstyle" style="margin-right:5px;margin-top:-2px"
									onclick="$('#dialog-extQuery1').dialog('open');" />
							</div>
							<c:if test="${isMonitorNetworkTopology==0}">
								<span class="kuaiju">快速搜索</span>
								<input type="text" id="keyword" value="${keyword}"
									name="keyword" style="height: 15px;" class="jianju" />
								<img src="${ctx}/images/search.jpg" onclick="query();"
									style="margin-left:5px">
								<a href="#" class="jianju" onclick="extQueryDlg();">高级</a>
							</c:if>
						</div></td>
				</tr>
				<tr>
					<td>
						<div class="sbox">
							<div class="cont">
								<div id="dataList">
									<table width="100%" border="0" cellspacing="1" cellpadding="0"
										class="eventslist" id="listTable">
										<thead>
											<tr height="28" class="biaoti">
												<td width="3%" align="center"><input type="checkbox"
													id="chkAll" name="chkAll" class="check-box not_checked" />
												</td>
												<th width="5%" class="biaoti">编号</th>
												<th width="5%" class="biaoti">事件级别</th>
												<th width="10%" class="biaoti">事件名称</th>
												<th width="10%" class="biaoti">事件类型</th>
												<th width="8%" class="biaoti">设备名称</th>
												<th width="10%" class="biaoti">接收时间</th>
												<th width="8%" class="biaoti">源IP</th>
												<th width="8%" class="biaoti">目标IP</th>
												<th width="5%" class="biaoti">源端口</th>
												<th width="5%" class="biaoti">目标端口</th>
												<th width="13%" class="biaoti">工单状态</th>
												<th width="13%" class="biaoti">告警状态</th>
											</tr>
										</thead>

										<tbody>
											<s:iterator value="#request.alertList" status="stat">
												<%-- <input type="hidden" name="ruleId" id="ruleId" value="${ruleId}" /> --%>

												<tr class="back"
													id="alertmessage_${alertLogTableName}_${relEventsIdentification}"
													onmousemove="this.className='hand'"
													onmouseout="this.className='back'"
													ondblclick="detailsContent('${relEventsIdentification}','${alertLogTableName}');">
													<td valign="middle" class="td_list_data"><input
														type="checkbox" id="ids" name="ids" value="${alertId}"
														class="check-box" /></td>
												<td valign="middle" class="td_list_data">${alertId}</td>

													<td valign="middle" class="td_list_data">
														<div class="level" style="margin:0px auto;">
															<c:if test="${alertRank <= 1}">
																<div class="levelBa"
																	style="background-color:#CCCCCC; width:${alertRank*8}px;">
																</div>
																<span style="position:relative;left:0px;top:-20px;">${alertRank}</span>
															</c:if>
															<c:if test="${alertRank > 1 && alertRank <= 3}">
																<div class="levelBa"
																	style="background-color:#ffcc33;  width:${alertRank*8}px;">
																</div>
																<span style="position:relative;left:0px;top:-20px;">${alertRank}</span>
															</c:if>
															<c:if test="${alertRank >= 4}">
																<div class="levelBa"
																	style="background-color:#ff3333; width:${alertRank*8}px;">
																</div>
																<span style="position:relative;left:0px;top:-20px;">${alertRank}</span>
															</c:if>
														</div></td>

													<td valign="middle" class="td_list_data">${alertEventName}</td>

													<td valign="middle" class="td_list_data">${alertEventType}</td>

													<td valign="middle" class="td_list_data">${alertDeviceName}</td>

													<td valign="middle" class="td_list_data">${date}</td>

													<td valign="middle" class="td_list_data">${eventsSourceAddT}</td>

													<td valign="middle" class="td_list_data">${eventsTargetAddT}</td>

													<td valign="middle" class="td_list_data">${eventsSourcePort}</td>

													<td valign="middle" class="td_list_data">${eventsTargetPort}</td>

													<td valign="middle" class="td_list_data"><c:if
															test="${workorder eq '01'}">
                                                		未派发&nbsp;&nbsp;<a
																onclick="toadd(${alertId});" href="javascript:void(0);">派发工单</a>
														</c:if> <c:if test="${workorder eq '02'}">
                                                		已派发&nbsp;&nbsp;<a
																onclick="checkWorkOrder(${alertId});"
																href="javascript:void(0);">查看工单</a>
														</c:if></td>
														<td valign="middle" class="td_list_data">
														<span id="alert_${alertId}"><c:if
															test="${isClose == 1}">
                                                		未关闭&nbsp;&nbsp;<a
																onclick="toClose(${alertId});" href="javascript:void(0);">关闭</a>
														</c:if> <c:if test="${isClose == 0}">
                                                			已关闭
														</c:if></span>
														</td>




													<%-- <td valign="middle" class="td_list_data"><img
									src="${ctx}/images/asset_info.png" title="查看详细信息" onclick="detailsContent('${relEventsIdentification}','${alertLogTableName}')" class="img_details"/></td> --%>
												</tr>

											</s:iterator>
										</tbody>
										<tr style="background-color:#FFFFFF; ">
											<td colspan="13" width="100%" id="pageJsp"><jsp:include
													page="../../commons/page.jsp"></jsp:include></td>
										</tr>
									</table>

								</div>
							</div>
						</div>
					</td>

				</tr>
			</table>
		</s:form>
	</div>
	<div id="hideDiv"></div>
	<div id="loding" class="loding">
		<font color='#69C3FF'>数据加载中...</font>
	</div>
	<!-- ext query from -->
	<!-- 
	<s:form action="alertMessageQuery.action" namespace="/alertMessage"
		method="post" theme="simple" id="queryForm" name="queryForm">
		<s:hidden id="receiver" name="alertReceiver" />
			<s:hidden id="rel_deviceType" name="rel_deviceType" />
		<s:hidden id="sendway" name="alertSendway" />
		<s:hidden id="alertRank" name="alertRank"/>
		<s:hidden id="assetIp" name="assetIp" />

	</s:form>
 -->
	<!-- 高级查询弹出框 -->
	<div id="dialog-extQuery" title="高级查询">
		<table width="90%" border="0" cellspacing="5" cellpadding="5"
			style="margin-left:20px;">
			<tr>
				<td width="25%">接收人:</td>
				<td><input id="sRuleName" name="sRuleName" type="text"
					style="width:250px;" onkeypress="if(event.keyCode==13)extQuery();" />
				</td>
			</tr>
			<tr>
				<td>告警方式:</td>
				<td><input id="ruleUpdateTime" name="ruleUpdateTime"
					type="text" style="width:250px;"
					onkeypress="if(event.keyCode==13)extQuery();" /></td>
			</tr>
			<tr>
				<td>告警等级:</td>
				<td><select id="ruleRank" name="ruleRank"
					style="width:252px;height: 20px;">
						<option value="">--请选择告警等级--</option>
						<option value="1">1级</option>
						<option value="2">2级</option>
						<option value="3">3级</option>
						<option value="4">4级</option>
						<option value="5">5级</option>
				</select></td>
			</tr>

			<tr>
				<td width="25%">设备类型:</td>
				<td><select id="driverType" name="driverType"
					style="width:252px; height: 20px;">
						<%--
                       <option value="" selected="selected">--请选择设备类型--</option>
                       <option value="Juniper 防火墙">Juniper 防火墙</option>
                       <option value="联想网御防火墙">联想网御防火墙</option>
                       <option value="cisco4506">cisco4506</option>
                       <option value="H3C-7506E交换机">H3C-7506E交换机</option>
                       <option value="Firewall">Firewall</option>
                       <option value="卫星双向系统防火墙">卫星双向系统防火墙</option>
                       <option value="IPS">IPS</option>
                       <option value="Topsec_Firewall">Topsec_Firewall</option>
                       <option value="IDS">IDS</option>
                       <option value="Linux_system">Linux_system</option>
                       <option value="Linux 服务器">Linux服务器</option>
                       <option value="Radware负载均衡">Radware负载均衡</option>
                       <option value="Windows 服务器">Windows服务器</option>
                --%>
						<option value="" selected="selected">--请选择设备类型--</option>
						<s:iterator value="categoryName" status="stat">
							<option value="${deviceid}">${devicename}</option>
						</s:iterator>
				</select></td>
			</tr>
		</table>
	</div>

	<div id="dialog-enentsContent" title="详细信息"
		class="dialog-enentsContent" style="height: 200px">
		<input type="button" value="返回列表" class="btnstyle"
			style="margin-right:5px;margin-top:-2px" onclick="closeContent();" />
		<div style="margin:5px 0px;"></div>
		<div class="column">
			<div class="title">
				<span class="title_t"><img
					src="${ctx}/images/u319_normal.gif" id="img_1" class="img_a"
					onclick="action(1)">&nbsp;<span class="title_t_t">设备信息</span>
				</span>
			</div>
			<div id="column_1">
				<ul class="display" style="list-style-type:none">
					<li>
						<table cellpadding="0px" cellspacing="0px" width="100%">
							<tr>
								<td class="rowLT">设备名称</td>
								<td class="rowLV" id="devName">&nbsp;</td>
								<td class="rowRT">ip地址</td>
								<td class="rowLV" id="devAddr">&nbsp;</td>
							</tr>
							<tr>
								<td class="rowLT">设备厂商</td>
								<td class="rowLV" id="devVendor">&nbsp;</td>
								<td class="rowRT">设备型号</td>
								<td class="rowLV" id="devType">&nbsp;</td>
							</tr>
						</table></li>
				</ul>
			</div>
		</div>

		<div style="height: 10px;"></div>
		<div class="column">
			<div class="title">
				<span class="title_t"><img
					src="${ctx}/images/u319_normal.gif" id="img_2" class="img_a"
					onclick="action(2)">&nbsp;<span class="title_t_t">时间信息</span>
				</span>
			</div>
			<div id="column_2">
				<ul class="display" style="list-style-type:none">
					<li>
						<table cellpadding="0px" cellspacing="0px" width="100%">
							<tr>
								<td class="rowLT">产生时间</td>
								<td class="rowLV" id="sendTime">&nbsp;</td>
								<td class="rowRT">接收时间</td>
								<td class="rowLV" id="occurTime">&nbsp;</td>
							</tr>
						</table></li>
				</ul>
			</div>
		</div>
		<div style="height: 10px;"></div>
		<div class="column">
			<div class="title">
				<span class="title_t"><img
					src="${ctx}/images/u319_normal.gif" id="img_3" class="img_a"
					onclick="action(3)">&nbsp;<span class="title_t_t">基本信息</span>
				</span>
			</div>
			<div id="column_3">
				<ul class="display" style="list-style-type:none">
					<li>
						<table cellpadding="0px" cellspacing="0px" width="100%">
							<tr>
								<td class="rowLT">等&nbsp;&nbsp;级</td>
								<td class="rowLV" id="priority">&nbsp;</td>
								<td class="rowRT">数量</td>
								<td class="rowLV" id="count">&nbsp;</td>
							</tr>

							<tr>
								<td class="rowLT">事件名称</td>
								<td colspan="3" class="rowRV" id="name">&nbsp;</td>
							</tr>
							
							<tr>
								   <td class="rowLT">事件类别</td>
								   <td colspan="3" class="rowRV" id="category">&nbsp;</td>
								</tr>

							<tr>
								<td class="rowLT">事件类型</td>
								<td colspan="3" class="rowRV" id="type">&nbsp;</td>
							</tr>

							<tr>
								<td class="rowLT">事件描述</td>
								<td colspan="3" class="rowRV" id="customs4"
									style="word-break:break-all">&nbsp;</td>
							</tr>
							<%--<tr>
								<td class="rowLT">摘要</td>
								<td colspan="3" class="rowRV" id="digest">&nbsp;</td>
							</tr>
						--%></table></li>
				</ul>
			</div>
		</div>

		<div style="height: 10px;"></div>
		<div class="column">
			<div class="title">
				<span class="title_t"><img
					src="${ctx}/images/u319_normal.gif" id="img_4" class="img_a"
					onclick="action(4)">&nbsp;<span class="title_t_t">来源目标信息</span>
				</span>
			</div>
			<div id="column_4">
				<ul class="display" style="list-style-type:none">
					<li>
						<table cellpadding="0px" cellspacing="0px" width="100%">
							<tr>
								<td class="rowLT">源地址</td>
								<td class="rowLV" id="sAdd">&nbsp;</td>
								<td class="rowRT">源端口</td>
								<td class="rowLV" id="sPort">&nbsp;</td>
							</tr>
							<tr>
								<td class="rowLT">目标地址</td>
								<td class="rowLV" id="tAdd">&nbsp;</td>
								<td class="rowRT">目标端口</td>
								<td class="rowLV" id="tPort">&nbsp;</td>
							</tr>
						</table></li>
				</ul>
			</div>

		</div>
		<!-- <div style="height:20px;text-align: right">
			<a id="test" href="" target="view_window" onclick="test();">查看事件原始信息</a>
		</div> -->
	</div>
	<!-- 导出的dialog -->
	<div id="dialog-extQuery1" title="选择导出格式">
		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 0px">
			<tr>
				<!-- left area -->
				<td width="100%" valign="top">
					<!--  左侧table-->
					<div class="framDiv" style="border:none;">
						<!-- 报表模板内容 -->
						<table width="70%" border="0" cellspacing="0" cellpadding="0"
							style="margin-left: 4px;" align="center">
							<!-- 空行 -->
							<tr>
								<td class="td_detail_separator"></td>
							</tr>
							<tr>
								<td class="td_detail_separator"></td>
							</tr>
							<tr>
								<td class="td_detail_separator"></td>
							</tr>

							<tr>
								<td align="center" width="20%"><img
									src="/soc/images/u21.png"> <input type="button"
									class="btnyh" id="btnSave" value="导出为DOC格式"
									onclick="Export('doc');" />
								</td>
								<td align="center" width="20%"><img
									src="/soc/images/u23.png"> <input type="button"
									class="btnyh" id="btnSave" value="导出为excel格式"
									onclick="exportExcel();" /></td>
							</tr>
							<tr>
								<td class="td_detail_separator"></td>
							</tr>
							<tr>
								<td class="td_detail_separator"></td>
							</tr>
							<tr>
								<td class="td_detail_separator"><br>
								<br>
								</td>
							</tr>
						</table>
					</div></td>
			</tr>
		</table>
	</div>

	<%--<s:form name="ExportReport" namespace="/alertMessage" action="ExportReport" id="ExportReport" method="post" theme="simple">
		<input type="hidden"  name="reportType" id="reportType" value = ""/> 
		<input type="hidden"  name="alertReportId" id="alertReportId" value = "11"/>  
		<input type="hidden"  name="checkids" id="checkids" value=""/>
	</s:form>
--%>
</body>
</html>