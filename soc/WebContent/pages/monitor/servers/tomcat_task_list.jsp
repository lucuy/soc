<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
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
<style type="text/css">
.workOrderCount{color: red;cursor: pointer;}
</style>
<link href="${ctx}/styles/user/user.css" rel="stylesheet"
	type="text/css">
 <link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet"
	type="text/css"> 

<link href="${ctx}/styles/jquery.popuplayer.css" rel="stylesheet"
	type="text/css">
<script type="text/javascript" src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-latest.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/jquery.tablesorter.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
<script type='text/javascript' src="${ctx}/scripts/popuplayer.js"></script>
<script language="javascript">
var rege =/^([^`~!@#$%^&*()=|{}':;, \\\[\].<>\/?~！@#￥……&*（）—|{}【】‘；：”“'。\"，、？])*$/;
//快速查找
function query(){
	if($('#keyword').val().length>50){
		alert("搜索长度不能大于50");
		$('#keyword').val('');
		$('#keyword').focus();
		return ;
	}
	if(!rege.test($('#keyword').val())){
		alert("输入的内容包含特殊字符，请重新输入");
		$('#keyword').val('');
		$('#keyword').focus();
		return;
	}
	var keyword = $("#keyword").val();
	location.href = "${ctx}/monitorTOM/queryAll.action?keyword="+encodeURI(encodeURI(keyword,"utf-8"));
}

//高级查询所得值
function higquery(){
	if($('#tmpTomName').val().length>50){
		alert("对象名称长度不能大于50");
		$('#tmpTomName').val('');
		$('#tmpTomName').focus();
		return ;
	}
	if(!rege.test($('#tmpTomName').val())){
		alert("输入的内容包含特殊字符，请重新输入");
		$('#tmpTomName').val('');
		$('#tmpTomName').focus();
		return;
	}
	var ip = $("#tmpTomIp").val();
	if (ip != "") {
		var re1 = /^([0-9]|[1-9]\d|1\d{1,2}|2\d|2[0-3]\d|24[0-7])\.(\d|[1-9]\d|1\d{1,2}|2\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d{1,2}|2\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d{1,2}|2\d|2[0-4]\d|25[0-5])$/; // 匹配IP地址的正则表达式
		var result = re1.test(ip);
		if (result) {
			if (!(RegExp.$1 < 256 && RegExp.$2 < 256 && RegExp.$3 < 256 && RegExp.$4 < 256)) {
				alert("请输入正确的ip地址");
				return false;
			}
		} else {
			alert("请输入正确的ip地址");
			return false;
		}
	}
	//var dbType = ${dbType};
		$('#tomName').val($('#tmpTomName').val());
		$('#tomIp').val(ip);
		//$('#dbType').val(dbType);
		$('#queryForm').submit();
		$(this).dialog("close");
}

//点击高级弹出框的开始
$(document).ready(function() {
// 高级-Dialog			
	$('#dialog-extQuery').dialog({
		autoOpen : false,
		width : 450,
		height : 250,
		buttons : {
			"查询[Enter]" : function() {
				higquery();
				
			},
			"取消[Esc]" : function() {
				$(this).dialog("close");
			}
		}
	});	
$('#listTable').tablesorter();
	
});
function extQueryDlg() {
	$('#workOrderName').val('');
	$('#exigencyLevel').val('');
	$('#status').val('');
	$('#dialog-extQuery').dialog('open');
	}
function deleteTOM(val){
	if (confirm("确认执行操作吗？")) {
		location.href = "${ctx}/monitorTOM/deleteTomTask.action?tomId=" + val;
	}
}
function detailTOM(val){
	location.href = "${ctx}/monitorTOM/toAddTomTask.action?tomId=" + val;
}

function stopTOM(val,online){
	stopORstart(val,0,online);
	
}
function startTOM(val,online){
	stopORstart(val,1,online);
}
function stopORstart(val,status,online){
	//var onlineHtml="";
	var statusHtml="";
	$
	.ajax({
		type : "post",
		url : "${ctx}/monitorTOM/stopORstart.action?tomId="+val+"&stopORstart="+status,
		dataType : "text",
		success : function(result) {
			if(status==0){
				statusHtml="<img src=\"${ctx}/images/monitor_start_task.gif\" alt=\"启动\" class=\"workOrderCount\""+
				"onClick=\"startTOM("+val+","+0+")\" title=\"启动\"/>";
				$("#online_"+val).html("");
			}else{
				statusHtml="<img src=\"${ctx}/images/monitor_stop_task.gif\" alt=\"停止\" class=\"workOrderCount\" "+
				"onClick=\"stopTOM("+val+","+1+")\" title=\"停止\"/>";
				onlines(val);
			}
				//$("#online_"+val).html(onlineHtml);
				$("#isStop_"+val).html(statusHtml);
		}
	});	
}
function checkOnline(){
	var tomIds='${tomIdStr}';
	var tomId=tomIds.split(",");
	for(var i=0;i<tomId.length;i++){
		if(tomId[i]!=null&&tomId[i]!=""){
			onlines(tomId[i]);
		}
	}
}
function onlines(id){
	$("#online_"+id).html("正在测试连接.......");
	$
	.ajax({
		type : "post",
		url : "${ctx}/monitorTOM/online.action?tomId="+id,
		async : true,
		dataType : "text",
		success : function(result) {
			var onlineHtml="";
			if(result=="2"){
				
			}else{
				if(result=="1"){
					onlineHtml=	"<img src=\"${ctx}/images/monitor_healthy_ok.gif\" alt=\"可用\" title=\"可用\"/>";
				}else{
					onlineHtml="<img src=\"${ctx}/images/monitor_healthy_unok.gif\" alt=\"不可用\" title=\"不可用\"/>";
				}
				//alert(onlineHtml);
				
			}
				$("#online_"+id).html(onlineHtml);
		}
	});	
}
	</script>
</head>

<body style="margin-top:2px;" onload="checkOnline();">
	<form action="queryAll.action" namespace="/monitorTOM" method="post" id="" name="">
	<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 0px">
		<tr><td>
		<div class="box">
			<div class="right">		
            <input type="button" value="添加" class="btnstyle"
					style="margin-right:5px;margin-top:-2px"
					onclick="window.location.href='${ctx}/pages/monitor/servers/tomcat_task_add.jsp'" />
					
			</div>
			<span class="kuaiju">快速搜索</span>
			<input type="text" id="keyword" value="${keyword}" name="keyword" style="height: 15px;" class="jianju" />
			<img src="${ctx}/images/search.jpg" onClick="query()"
				style="margin-left:5px"> <a href="#" class="jianju"
				onclick="extQueryDlg();">高级</a>
		</div></td></tr>
		<tr><td>
		<div class="sbox">
			<div class="cont">
				<!-- information area -->
				<div id="dataList">
					<table width="100%" border="0" cellspacing="1" cellpadding="0"
						class="tab2" id="listTable">
						<thead>
						<tr height="28" class="biaoti">
						
							<th width="10%" class="biaoti">监控对象名称</th>
							<th width="15%" class="biaoti">健康状态</th>
							<th width="10%" class="biaoti">监控对象IP</th>
							
							<th width="15%" class="biaoti">启停</th>
							<th width="15%" class="biaoti">编辑</th>
                            <th width="10%" class="biaoti">删除</th>
						</tr>
						</thead>
						<tbody>
						<s:iterator value="mttList" status="stat">
						<tr>
						<td valign="middle">
						<%--  <c:if test="${tomIsOnline==1}">
						<c:if test="${tomTaskStatus==1}">
								<a href="#" 
								onclick="location.href='${ctx}/monitorTOM/detailTOM.action?tomId=${tomId}'">
									${tomName}
								</a>
								</c:if>
								</c:if> 
								<c:if test="${(tomTaskStatus!=1)||(tomIsOnline!=1)}">
								<p href="#" style="color: #99CCFF">
									${tomName}
								</p>
								</c:if> --%>
								
								 <a href="#" 
								onclick="location.href='${ctx}/monitorTOM/detailTOM.action?tomId=${tomId}'">
									${tomName}
								</a>  
								</td>
								<td valign="middle">
								 <span id="online_${tomId}">
								 <c:if test="${tomTaskStatus==1}">
								<c:if test="${tomIsOnline ==1 }">
									<img src="${ctx}/images/monitor_healthy_ok.gif" alt="可用" title="可用"/>
									</c:if>
									<c:if test="${tomIsOnline ==0 }">
									<img src="${ctx}/images/monitor_healthy_unok.gif" alt="不可用" title="不可用"/>
									</c:if>
									</c:if>
									
								</span>  
								
									
								</td>
								<td valign="middle">
									${tomIp}
								</td>
								<td valign="middle">
								<span id="isStop_${tomId}">
								<c:if test="${tomTaskStatus == 1}">
									<img src="${ctx}/images/monitor_stop_task.gif" alt="停止" class="workOrderCount" onClick="stopTOM(${tomId},${tomIsOnline})" title="停止"/>
								</c:if>
								<c:if test="${tomTaskStatus == 0}">
									<img src="${ctx}/images/monitor_start_task.gif" alt="启动" class="workOrderCount" onClick="startTOM(${tomId},${tomIsOnline})" title="启动"/>
								</c:if>
								</span>
								</td>
								<td valign="middle">
									<img src="${ctx}/images/monitor_property.gif" alt="编辑" class="workOrderCount" title="编辑" onClick="detailTOM(${tomId})"/>
								</td>
                                	<td valign="middle">
									<img src="${ctx}/images/monitor_delete.gif" alt="删除" class="workOrderCount" title="删除" onClick="deleteTOM(${tomId})"/>
								</td>
							</tr>
						</s:iterator>
						
						</tbody>
						<tr>
										<td colspan="6" width="100%"><jsp:include
												page="../../commons/page.jsp"></jsp:include><br></td>
									</tr>
					</table>
				</div>
			</div>
		</div>
	
		</td></tr>
		</table>
	</form>

	<!-- over layer -->
	<div id="blk" style="display:none;">
		<div class="blk">
			<div class="head">
				<div class="head-right"></div>
			</div>
			<div class="main">
				<h2 id="blk_header">#header#</h2>
				<div id="blk_content">#content#</div>
			</div>
			<div class="foot">
				<div class="foot-right"></div>
			</div>
		</div>
	</div>
	
	<!-- warn query from -->
	<s:form action="queryAll.action" namespace="/monitorTOM" method="post"
		theme="simple" id="queryForm" name="queryForm">
		<s:hidden name="tomIp" id="tomIp" />
		<s:hidden name="tomName" id="tomName" />
		
	</s:form>
	
	<!-- ui-dialog -->
	<div id="dialog-extQuery" title="高级查询">
		<table width="90%" border="0" cellspacing="5" cellpadding="5"
			style="margin-left:20px;">
			<tr>
				<td width="25%">监控对象名称:</td>
				<td><input id="tmpTomName" name="tmpTomName" type="text"
					style="width:250px;" onKeyPress="" />
				</td>
			</tr>
			<tr>
				<td width="25%">监控对象IP:</td>
				<td><input id="tmpTomIp" name="tmpTomIp" type="text"
					style="width:250px;" onKeyPress="" />
				</td>
			</tr>
		</table>
	</div>
</body>
</html>
