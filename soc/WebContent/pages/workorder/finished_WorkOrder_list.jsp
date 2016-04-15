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
<%-- <script type="text/javascript" src="${ctx}/pushlet/ajax-pushlet-client.js"></script>--%>

<link href="${ctx}/styles/user/user.css" rel="stylesheet"
	type="text/css">
 <link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet"
	type="text/css"> 
	<%-- <link href="${ctx}/styles/jquery-ui-1.8.21.css" rel="stylesheet" type="text/css"> --%>
<link href="${ctx}/styles/jquery.popuplayer.css" rel="stylesheet"
	type="text/css">
<script type="text/javascript" src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx }/scripts/jquery-latest.js"></script>
<script type="text/javascript"
	src="${ctx }/scripts/jquery.tablesorter.js"></script>
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
			location.href = "${ctx}/workOrder/query.action?keyword="+encodeURI(encodeURI(keyword,"utf-8"))+"&falg=2";
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
			$('#dialog-openClose').dialog({
				autoOpen : false,
				width : 450,
				height : 250,
				buttons : {
					"关闭[Close]" : function() {
						$("#closeReason").val('');
						$(this).dialog("close");
					}
				}
			});	
		$('#listTable').tablesorter();
			
		});
		function openCloseReanson(val){
			$.ajax({
				type : "POST",
				url : "${ctx}/workOrder/queryCloseReason.action?workOrderId="+val,
				data : "",
				dataType : 'text',
				success : function(msg) {
					if(msg!=null){
					$("#closeReason").val(msg);	
			$('#dialog-openClose').dialog('open');
					}
				}
			});
				
		}
		function extQueryDlg() {
		$('#workOrderName').val('');
		$('#exigencyLevel').val('');
		$('#status').val('');
		$('#dialog-extQuery').dialog('open');
		}
		//高级查询所得值
		function higquery(){
			if($('#workOrderName').val().length>50){
				alert("工单名称长度不能大于50");
				$('#workOrderName').val('');
				$('#workOrderName').focus();
				return ;
			}
			if(!rege.test($('#workOrderName').val())){
				alert("输入的内容包含特殊字符，请重新输入");
				$('#workOrderName').val('');
				$('#workOrderName').focus();
				return;
			}
			if($('#workOrderAudit').val()!=undefined){
				
				if($('#workOrderAudit').val().length>50){
					alert("责任者长度不能大于50");
					$('#workOrderAudit').val('');
					$('#workOrderAudit').focus();
					return ;
				}
				if(!rege.test($('#workOrderAudit').val())){
					alert("输入的内容包含特殊字符，请重新输入");
					$('#workOrderAudit').val('');
					$('#workOrderAudit').focus();
					return;
				}
				}
				if($('#workOrderHandle').val()!=undefined){
					
				if($('#workOrderHandle').val().length>50){
					alert("派发者长度不能大于50");
					$('#workOrderHandle').val('');
					$('#workOrderHandle').focus();
					return ;
				}
				if(!rege.test($('#workOrderHandle').val())){
					alert("输入的内容包含特殊字符，请重新输入");
					$('#workOrderHandle').val('');
					$('#workOrderHandle').focus();
					return;
				}
				}
				$('#selWorkOrderName').val($('#workOrderName').val());
				$('#selExigencyLevel').val($('#exigencyLevel').val());
				$('#selStatus').val($('#status').val());
				$('#selworkOrderHandle').val($('#workOrderHandle').val());
				$('#selworkOrderAudit').val($('#workOrderAudit').val());
				$('#queryForm').submit();
				$(this).dialog("close");
		}
		
	</script>

<title>工单管理</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>

<body style="margin-top:2px;">
	<s:form action="query.action" namespace="/workOrder" method="post" id="empForm" name="empForm">
	<input type="hidden" name="field" id="field" value="${field}" />
	<input type="hidden" name="sortType" id="sortType" value="${sortType}" />
	<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 0px">
		<tr><td>
		<div class="box">
			<div class="right">

				<%--<input type="button" value="添加" class="btnstyle"
					style="margin-right:5px;margin-top:-2px"
					onclick="location.href='${ctx}/alertMessage/toAddWorkorder.action'" /> 
					
				
					
			--%></div>
			<span class="kuaiju">快速搜索</span>
			<input type="text" id="keyword" value="${keyword}" name="keyword" style="height: 15px;" class="jianju" />
			<img src="${ctx}/images/search.jpg" onclick="query();"
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
							
							<th width="10%" class="biaoti">工单编号</th>
							<th width="15%" class="biaoti">工单名称</th>
							<th width="10%" class="biaoti">紧急程度</th>
							<th width="15%" class="biaoti">派发者</th>
							<th width="15%" class="biaoti">操作者</th>
							<th width="20%" class="biaoti">状态</th>
						</tr>
						</thead>
						<tbody>
						<c:forEach items="${listWork}" var="Ls">
							<tr>
								
								<td valign="middle">
								<a href="${ctx}/workOrder/queryById.action?workOrderId=${Ls.workOrderId}">
											${Ls.workOrderId}
								</a></td>
								<td valign="middle">
									${Ls.workOrderName}
								</td>
								<td valign="middle">
									<c:if test="${Ls.exigencyLevel==0 }">紧急</c:if>
									<c:if test="${Ls.exigencyLevel==1 }">重要</c:if>
									<c:if test="${Ls.exigencyLevel==2 }">次要</c:if>
									<c:if test="${Ls.exigencyLevel==3 }">一般</c:if>
								</td>
								
								
								<td valign="middle">
									${Ls.workOrderStart}
								</td>
								<td valign="middle">
									${Ls.workOrderHandle}
								</td>
								<td valign="middle">
									<c:if test="${Ls.status==0}">未处理</c:if>
									<c:if test="${Ls.status==1}">已处理</c:if>
									<c:if test="${Ls.status==2}">已关闭&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" onclick="openCloseReanson(${Ls.workOrderId})">关闭原因</a></c:if>
									
								</td>
							</tr>
						</c:forEach>
						</tbody>
						<tr>
							<td colspan="6" width="100%"><jsp:include
									page="../commons/page.jsp"></jsp:include></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
		<input type="hidden" name="selWorkOrderName" 	 id="selw"  	  value="${selWorkOrderName}" />
		<input type="hidden" name="selExigencyLevel" 	 id="sele"  	  value="${selExigencyLevel}" />
		<input type="hidden" name="selStatus"        	 id="sels"        value="${selStatus}" />
		<input type="hidden" name="selworkOrderHandle" 	 id="selhandle"   value="${selworkOrderHandle}" />
		<input type="hidden" name="selworkOrderStart" 	 id="selaudit"    value="${selworkOrderStart}" />
		<input type="hidden" name="falg" value="2">
		</td></tr>
		</table>
	</s:form>

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
	<s:form action="query.action" namespace="/workOrder" method="post"
		theme="simple" id="queryForm" name="queryForm">
		<s:hidden name="selWorkOrderName" id="selWorkOrderName" />
		<s:hidden name="selExigencyLevel" id="selExigencyLevel" />
		<s:hidden name="selStatus" id="selStatus" />
		<s:hidden name="selworkOrderHandle" id="selworkOrderHandle" />
		<s:hidden name="selworkOrderStart" id="selworkOrderAudit" />
		<input type="hidden" name="falg" value="2">
	</s:form>
	
	<!-- ui-dialog -->
	<div id="dialog-extQuery" title="高级查询">
		<table width="90%" border="0" cellspacing="5" cellpadding="5"
			style="margin-left:20px;">
			<tr>
				<td width="25%">工单名称:</td>
				<td><input id="workOrderName" name="workOrderName" type="text"
					style="width:250px;" onkeypress="" />
				</td>
			</tr>
			<tr>
				<td>紧急程度:</td>
				<td>
				  <select id="exigencyLevel" style="width: 255px">
				  	<option value="">===全部===</option>
				  	<option value="0">紧急</option>
				  	<option value="1">重要</option>				  
				  	<option value="2">次要</option>				  
				  	<option value="3">一般</option>				  
				  </select>
				</td>
			</tr>
			<c:if test="${userId ==38 }">
			
			<tr>
				<td width="25%">操作者:</td>
				<td><input id="workOrderHandle" name="workOrderHandle" type="text"
					style="width:250px;" onkeypress="" />
				</td>
			</tr></c:if>
			<tr>
				<td width="25%">派发者:</td>
				<td><input id="workOrderAudit" name="workOrderAudit" type="text"
					style="width:250px;" onkeypress="" />
				</td>
			</tr>
		</table>
	</div>
<!-- ui-dialog -->
	<div id="dialog-openClose" title="关闭原因">
		<table width="90%" border="0" cellspacing="5" cellpadding="5"
			style="margin-left:20px;">
			<tr>
				<td height="80%">
				<textarea  rows="" cols="" style="width: 350px;height:130px;overflow: auto;margin-top: 10px;" id="closeReason">
				
				</textarea>
				
				</td>
			</tr>
		</table>
	</div>
</body>
</html>
