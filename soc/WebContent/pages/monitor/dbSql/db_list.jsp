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
			$("#selw").empty();
			//alert($('#selw').empty());
			$('#sele').empty();
			$('#sels').empty();
			$('#selhandle').empty();
			$('#selaudit').empty();
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
			location.href = "${ctx}/monitorDBT/queryAll.action?keyword="+encodeURI(encodeURI(keyword,"utf-8"))+"&dbType="+${dbType};
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
		//高级查询所得值
		function higquery(){
			if($('#tmpTaskName').val().length>50){
				alert("对象名称长度不能大于50");
				$('#tmpTaskName').val('');
				$('#tmpTaskName').focus();
				return ;
			}
			if(!rege.test($('#tmpTaskName').val())){
				alert("输入的内容包含特殊字符，请重新输入");
				$('#tmpTaskName').val('');
				$('#tmpTaskName').focus();
				return;
			}
			var ip = $("#tmpDbIp").val();
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
			var dbType = ${dbType};
				$('#taskName').val($('#tmpTaskName').val());
				$('#dbIp').val(ip);
				$('#dbType').val(dbType);
				$('#queryForm').submit();
				$(this).dialog("close");
		}
		function deleteDB(val,type){
			if (confirm("确认执行操作吗？")) {
				location.href = "${ctx}/monitorDBT/deleteDBTask.action?taskId=" + val+"&dbType="+type;
			}
		}
		function detailDB(val,type){
				location.href = "${ctx}/monitorDBT/toAddDBTask.action?taskId=" + val+"&dbType="+type;
		}
		function stopDB(val,online){
			stopORstart(val,0,online);
			
		}
		function startDB(val,online){
			stopORstart(val,1,online);
		}
		function stopORstart(val,status,online){
			var statusHtml="";
					
			$
			.ajax({
				type : "post",
				url : "${ctx}/monitorDBT/stopORstart.action?taskId="+val+"&stopORstart="+status,
				async : true,
				dataType : "text",
				success : function(result) {
					if(status==0){
						statusHtml="<img src=\"${ctx}/images/monitor_start_task.gif\" alt=\"启动\" class=\"workOrderCount\""+
						"onClick=\"startDB("+val+","+0+")\" title=\"启动\"/>";
						$("#online_"+val).html("");
					}else{
						statusHtml="<img src=\"${ctx}/images/monitor_stop_task.gif\" alt=\"停止\" class=\"workOrderCount\" "+
						"onClick=\"stopDB("+val+","+1+")\" title=\"停止\"/>";
						$("#online_"+val).html("连接中...");
						onlines(val);
					}
						$("#isStop_"+val).html(statusHtml);
				}
			});	
		}
		function ContextMenu(){
			
		      if (event.button==2 || event.button==3) {  
		             alert("加载数据中...");
		             return false;
		        }
		  }
		  var mack;
			 function reload() {
			   // document.onmousedown=ContextMenu;
			    mack = document.getElementById("mack");
			    mack.className="ui-widget-overlay";
			    mack.style.height=document.documentElement.clientHeight;
			    openSuccess();
		    }
		    	
		function detailMDT(dbType,taskId){
			reload();
			location.href="${ctx}/monitorDBT/detailDB.action?dbType="+dbType+"&taskId="+taskId;
		}
		function openSuccess()
	    {
	        var docHe =  ($(document).height()/2)-40;
	        var docWi =  ($(document).width()/2)-200;
	        $("#openSuccess").css({top:docHe,left:docWi});
	        $("#openSuccess").show();
	    }
	</script>
</head>

<body style="margin-top:2px;" >
<div class="ui-overlay">
        <div id="mack"></div>
     
    </div>
	<form action="queryAll.action" namespace="/monitorDBT" method="post" id="empForm" name="empForm">
	<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 0px">
		<tr><td>
		<input type="hidden" name="dbType" value="${dbType}"/>
		<div class="box">
			<div class="right">		
            <input type="button" value="添加" class="btnstyle"
					style="margin-right:5px;margin-top:-2px"
					onclick="location.href='${ctx}/monitorDBT/toAddDBTask.action?dbType=${dbType}'" />
					
			</div>
			<span class="kuaiju">快速搜索</span>
			<input type="text" id="keyword" value="${keyword}" name="keyword" style="height: 15px;" class="jianju" />
			<img src="../images/search.jpg" onClick="query();"
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
							<th width="15%" class="biaoti">可用性</th>
							<th width="10%" class="biaoti">监控对象IP</th>
							
							<th width="15%" class="biaoti">启停</th>
							<th width="15%" class="biaoti">编辑</th>
                            <th width="10%" class="biaoti">删除</th>
						</tr>
						</thead>
						<tbody>
						<s:iterator value="mdtList" status="stat">
						<tr>
						<td valign="middle">
						
								<a href="#" 
								onclick="detailMDT(${dbType},${taskId});">
									${taskName}
								</a></td>
								<td valign="middle">
								<span id="online_${taskId}">
								<c:if test="${taskStatus ==1}">
								<c:if test="${dbOnline ==1 }">
									<img src="${ctx}/images/monitor_status_normal.gif" alt="可用" title="可用"/>
									</c:if>
									<c:if test="${dbOnline ==0 }">
									<img src="${ctx}/images/monitor_status_normal1.png" alt="不可用" title="不可用"/>
									</c:if></c:if>
								</span>
									
								</td>
								<td valign="middle">
									${dbIp}
								</td>
								<td valign="middle">
								<span id="isStop_${taskId}">
								<c:if test="${taskStatus == 1}">
									<img src="${ctx}/images/monitor_stop_task.gif" alt="停止" class="workOrderCount" onClick="stopDB(${taskId},${dbOnline})" title="停止"/>
								</c:if>
								<c:if test="${taskStatus == 0}">
									<img src="${ctx}/images/monitor_start_task.gif" alt="启动" class="workOrderCount" onClick="startDB(${taskId},${dbOnline})" title="启动"/>
								</c:if>
								</span>
								</td>
								<td valign="middle">
									<img src="${ctx}/images/monitor_property.gif" alt="编辑" class="workOrderCount" title="编辑" onClick="detailDB(${taskId},${dbType})"/>
								</td>
                                	<td valign="middle">
									<img src="${ctx}/images/monitor_delete.gif" alt="删除" class="workOrderCount" title="删除" onClick="deleteDB(${taskId},${dbType})"/>
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
	<s:form action="queryAll.action" namespace="/monitorDBT" method="post"
		theme="simple" id="queryForm" name="queryForm">
		<s:hidden name="dbIp" id="dbIp" />
		<s:hidden name="taskName" id="taskName" />
		<s:hidden name="dbType" id="dbType"  />
	</s:form>
	
	<!-- ui-dialog -->
	<div id="dialog-extQuery" title="高级查询">
		<table width="90%" border="0" cellspacing="5" cellpadding="5"
			style="margin-left:20px;">
			<tr>
				<td width="25%">监控对象名称:</td>
				<td><input id="tmpTaskName" name="tmpTaskName" type="text"
					style="width:250px;" onKeyPress="" />
				</td>
			</tr>
			<tr>
				<td width="25%">监控对象IP:</td>
				<td><input id="tmpDbIp" name="tmpDbIp" type="text"
					style="width:250px;" onKeyPress="" />
				</td>
			</tr>
		</table>
	</div>
	 <div class="framDiv"  id="openSuccess" style="width:30%;display: none;position: absolute;z-index:10; background:#FFFFFF;">
            <table width="100%" border="0" cellspacing="1" cellpadding="0">
               
                <tr>
                    <td class="td_detail_separator"></td>
                </tr>
                <tr>
                    <td class="td_detail_separator"></td>
                </tr>
                <tr>
                    <td align="center"><font style="font-size:16px;"><img alt="加载中" src="${ctx}/images/reload.gif">&nbsp;&nbsp;数据加载中...</font></td>
                </tr>
                <tr>
                    <td class="td_detail_separator"></td>
                </tr>
                <tr>
                    <td class="td_detail_separator"></td>
                </tr>
                <tr>
                    <td align="right" id="button">
		                    
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                </tr>
            </table>
                   </div>
<script type="text/javascript">
checkOnline();
function checkOnline(){
	var taskIds = '${tmpID}';
	var taskids =taskIds.split(",");
	for(var i=0;i<taskids.length;i++){
		if(taskids[i]!=null&&taskids[i]!=""){
		onlines(taskids[i]);
		}
	}
}
//检测是否在线
function onlines(id){
	$
	.ajax({
		type : "post",
		url : "${ctx}/monitorDBT/checkOnline.action?taskId="+id,
		async : true,
		dataType : "text",
		success : function(result) {
	var onlineHtml="";
			if(result=="2"){
				
			}else{
				if(result=="true"){
					onlineHtml=	"<img src=\"${ctx}/images/monitor_status_normal.gif\" alt=\"可用\" title=\"可用\"/>";
				}else{
					onlineHtml="<img src=\"${ctx}/images/monitor_status_normal1.png\" alt=\"不可用\" title=\"不可用\"/>";
				}
				
			}
				$("#online_"+id).html(onlineHtml);
		}
	});	
}

</script>
</body>
</html>
