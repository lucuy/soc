<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.soc.model.user.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%

String path = request.getContextPath();

	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
 

<link href="${ctx}/styles/user/user.css" rel="stylesheet"
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
<script type="text/javascript" src="${ctx }/scripts/jquery.tablesorter.js"></script>
<script type='text/javascript' src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
<script type='text/javascript' src="${ctx}/scripts/popuplayer.js"></script>



<script type="text/javascript">

	

	
	jQuery(document).ready(function() {
		
		//初始化格式对话框
		$('#dialog-extQuery').dialog({
			autoOpen : false,
			width : 700,
			height : 200,
			buttons: {
				"取消[Esc]": function() {
					$(this).dialog("close");
				}
			}
		});
		
		$('#sortTable').tablesorter();
	});

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

	//删除信息
	function bathDelete() {
		var ids = "";
		$("[name=ids]:checkbox:checked").each(function() {
			if (ids != "") {
				ids += "," + $(this).val();
			} else {
				ids = $(this).val();
			}
		});
		if (ids == "") {
			alert("没有删除对象,请检查");
			return;
		}

		if (confirm("确认执行操作吗？")) {
			location.href = "${ctx}/message/updateStatus.action?ids="+ ids;
		}
	}

	function openwin(hre) {
		location.href = hre;

	}
	var rege =/^([^`~!@#$%^&*()=|{}':;, \\\[\].<>\/?~！@#￥……&*（）—|{}【】‘；：”“'。\"，、？])*$/;
	//快速检索
	function query() {
		//关键字
		var keyword = $("#keyword").val();
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
		location.href = "${ctx}/message/query.action?keyword="
				+ encodeURI(encodeURI(keyword, "utf-8"));
	}
	function extQueryDlg() {
			$("#messageTitle").val('');
			$("#messageIdea").val('');
			$("#messageType").val('-1');
			$("#messageRead").val('-1');
			$('#dialog-extQuery').dialog('open');
	}
	function extQuery() {
		if($('#messageTitle').val().length>50){
			alert("搜索长度不能大于50");
			$('#messageTitle').val('');
			$('#messageTitle').focus();
			return ;
		}
		if(!rege.test($('#messageTitle').val())){
			alert("输入的内容包含特殊字符，请重新输入");
			$('#messageTitle').val('');
			$('#messageTitle').focus();
			return;
		}
		if($('#messageIdea').val().length>50){
			alert("搜索长度不能大于50");
			$('#messageIdea').val('');
			$('#messageIdea').focus();
			return ;
		}
				$('#selmessageTitle').val($('#messageTitle').val());
				$('#selmessageIdea').val($('#messageIdea').val());
				$('#selmessageType').val($('#messageType').val());
				$('#selmessageRead').val($('#messageRead').val());
				$('#queryForm').submit();
				$(this).dialog("close");
			
		}
	$(document).ready(function() {
			$("#keyword").keydown(function(event) {
				if (event.keyCode == 13) {
					query();
				}
			});
	
			// 高级-Dialog			
			$('#dialog-extQuery').dialog({
				autoOpen : false,
				width : 450,
				height : 230,
				buttons : {
					"查询[Enter]" : function() {
						extQuery();
					},
	
					"取消[Esc]" : function() {
						$(this).dialog("close");
					}
				}
			});
			var str = $('#actionStr').val();
	        if (str != "query.action") {
	            var url = "sort.action?" + str;
	            $('#messageForm').attr('action', url);
	        }
			
		});
	
/* 	$(document).ready(function(){
		var str = $('#actionStr').val();
		if (str != "querySecurityBulletin.action") {
			var url = "sort.action?" + str;
			$('#knowForm').attr('action', url);
		}
	});
	
 */

	
</script>


</head>

<body style="margin-top:2px;">
	
	<s:form action="query.action" namespace="/message" method="post"
		theme="simple" id="messageForm" name="messageForm">
		<input type="hidden" name="selmessageTitle" value="${selmessageTitle}" />
		<input type="hidden" name="selmessageIdea" value="${selmessageIdea}" />
		<input type="hidden"  name="selmessageType" value="${selmessageType}" />
		<input type="hidden"  name="selmessageRead" value="${selmessageRead}" />
		
		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 0px">

			<tr>
				<td></td>
			</tr>
			<tr>
				<td>
					<div class="box">
						<div class="right">
							<input type="button" value="删除"
								style="margin-right:12px;margin-top:-2px" class="btnstyle"
								onclick="bathDelete();" />
						</div>

						<span class="kuaiju">快速搜索</span> <input type="text" id="keyword"
							value="${keyword}" name="keyword" style="height: 15px" class="jianju" /><a   onclick="query();"><img src="/soc/images/search.jpg"  style="margin-left:5px"></a>
				 		  <a href="#" class="jianju" onclick="extQueryDlg();">高级</a>
					</div>
				</td>

			</tr>
			<tr>
				<td>
					<div class="sbox">
						<div class="cont">
						
							<!-- information area -->
							<div id="dataList">
							<s:form action="query.action" method="post" namespace="/message">
							
								<table width="100%" border="0" cellspacing="1" cellpadding="0"
									class="tab2" id="sortTable">
									<thead>
									<tr align="center" class="biaoti" >
										<td width="6%" class="biaoti"><input type="checkbox"
											id="chkAll" name="chkAll" class="check-box not_checked" /></td>
										<th width="10%" class="biaoti">
											<font class="headFontColor">序号</font></th>
										<th width="25%" class="biaoti">
											<font class="headFontColor">标题</font></th>
										<th width="10%" class="biaoti">
											<font class="headFontColor">消息类型</font></th>		
							<th width="15%" class="biaoti">
								<font class="headFontColor">发布日期</font></th>
										<th width="" class="biaoti">
											<font class="headFontColor">处理意见</font></th>
										<th width="" class="biaoti">
											<font class="headFontColor">是否阅读</font></th>		
							</tr>
							        </thead>
							        <tbody>
							<s:iterator value="messagelist" >
										
								<tr align="center" >
									<td class="biaocm" valign="middle">
										<input type="checkbox" name="ids" id="${messageId}"
												value="${messageId}" class="check-box" /></td>
									<td align="left" >${messageId}</td>
									<td align="left" >&nbsp;<a href="${ctx}/message/queryMessage.action?id=${messageId}"   title="${messageTitle }"> <c:out value="${messageTitle }"></c:out></a></td>
									<td>
										<s:if test="messageType==1">预警信息</s:if>
										<s:if test="messageType==2">告警信息</s:if>
										<s:if test="messageType==3">工单信息</s:if>
									</td>
									<td align="left" title=""> <s:date name="messageCreateDate" format="yyyy-MM-dd"/> </td>
									<td>  <c:out value="${messageDealIdea}"></c:out></td>
									<td>
										<s:if test="messageRead==0">未读</s:if>
										<s:if test="messageRead==1">已读</s:if>
									</td>
								</tr>
								
								</s:iterator>
								</tbody>
									<tr>
										<td colspan="7" width="100%">
											<jsp:include page="../commons/page.jsp"></jsp:include>
											<input type="hidden" name="dualstatus" value="${dualstatus}"/>	
										</td>
												
									</tr>
								</table>
							</s:form>
							</div>
							<!-- information area -->
						</div>
					</div>
				</td>
			</tr>
		</table>
	</s:form>	
	<s:form action="query.action" namespace="/message" method="post"
		theme="simple" id="queryForm" name="queryForm">
		<s:hidden name="selmessageTitle" id="selmessageTitle" />
		<s:hidden name="selmessageIdea" id="selmessageIdea" />
		<s:hidden name="selmessageType" id="selmessageType" />
		<s:hidden name="selmessageRead" id="selmessageRead" />
	</s:form>
			<!-- ui-dialog -->
	<div id="dialog-extQuery" title="高级查询">
		<table width="90%" border="0" cellspacing="5" cellpadding="5"
			style="margin-left:20px;">
			<tr>
				<td width="25%">标题:</td>
				<td><input id="messageTitle" name="messageTitle" type="text"
					style="width:250px;" onkeypress="if(event.keyCode==13)extQuery();" />
				</td>
			</tr>
			<tr>
				<td width="25%">处理意见:</td>
				<td><input id="messageIdea" name="messageIdea" type="text"
					style="width:250px;" onkeypress="if(event.keyCode==13)extQuery();" />
				</td>
			</tr>
			<tr>
				<td>消息类型:</td>
				<td  >
					<select  id="messageType" name="messageType">
						<option value="-1">--请选择--</option>
						<option value="1">工单信息</option>
						<option value="2">预警信息</option>
						<option value="3">告警信息</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>是否阅读:</td>
				<td style="width:50px;">
					<select id="messageRead" name="messageRead" >
						<option value="-1">--请选择--</option>
						<option value="0">未读</option>
						<option value="1">已读</option>
						
					</select>
				</td>
			</tr>
		</table>
	</div>
  <input type="hidden" id="actionStr" value="${actionStr }" /> 	
	
	
	
	
	
</body>
</html>
