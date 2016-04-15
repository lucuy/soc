<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
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
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx }/scripts/jquery-latest.js"></script>
<script type="text/javascript"
	src="${ctx }/scripts/jquery.tablesorter.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
<script type='text/javascript' src="${ctx}/scripts/popuplayer.js"></script>

<script language="javascript">
	var flag = 1;
	$(document).ready(function() {
		//parent.parent.leftFrame.location.href ="/soc/relevanceGroup/displayLeftTree.action";
		$("#keyword").keydown(function(event) {
			if (event.keyCode == 13) {
				query();
			}
		});

		// 高级-Dialog			
		$('#dialog-extQuery').dialog({
			autoOpen : false,
			width : 450,
			height : 210,
			buttons : {
				"确定[Enter]" : function() {
					addMonitorGroup();
				},

				"取消[Esc]" : function() {
					$(this).dialog("close");
				}
			}
		});
               $('#listTable').tablesorter();		
	});

	//快速检索
	function query() {
		//关键字
		var keyword = $("#keyword").val();
		location.href = "${ctx}/relevanceGroup/showGroupList.action?keyword="
				+ encodeURIComponent(encodeURIComponent(keyword));
	}
	function addMonitorDialog() {
	    $('#check_Name').empty();
		$('#realName').val("");
		$('#creatorIP').val("");
		$('#dialog-extQuery').dialog("open");
	}

	function addMonitorGroup() {
		if (flag == 1) {
			$('#groupName').val($('#realName').val());

			$('#groupMemo').val($('#creatorIP').val());

			//location.href = "${ctx}/monitorGroup/addGroup.action";
			$('#queryForm').submit();
			$('#dialog-extQuery').dialog("close");
		}
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

	$(document).click(function(event) {
		//shiftForCheckBoxFun(event);
		destroyOverDlg();
	});

	function deleteUser() {
		var ids = "";
		$("[name=ids]:checkbox:checked").each(function() {
			if (ids != "") {
				ids += "," + $(this).val();
			} else {
				ids = $(this).val();
			}
		});
		if ($(":checkbox[checked = true]").size() < 1) {
			alert("请至少选择一个组的信息...");
			return;
		}

		if (confirm("确认执行操作吗？")) {
			location.href = "${ctx}/relevanceGroup/deleteGroup.action?ids="
					+ ids;
		}
	}

	function checkName() {
	    $('#check_Name').empty();   
	    var s = $('#realName').val().replace(/(^s*)|(s*$)/g, "");
	   // alert(1+s+1);
	    if ( s=="") {
			//alert("213");
			$('#check_Name').html("名称不能为空");
			flag = 0;
		}else if(s.length>50){
			$('#check_Name').html("名称过长");
			flag = 0;
		}
		else
		{
		    flag = 1;
		}
		//alert($('#realName').val());
		//if($('#check_Name').val())
	}
	function changeStatus(id){
	
	$("#status1").val($("#"+id).val());
	//alert($("#status").val());
	}
	//更新状态
	function updateStatus() {
		var ids = "";
		$("[name=ids]:checkbox:checked").each(function() {
			if (ids != "") {
				ids += "," + $(this).val();
			} else {
				ids = $(this).val();
			}
		});
		if ($(":checkbox[checked = true]").size() < 1) {
			alert("请至少选择一个规则...");
			return;
		}

		if ($("#status").val() == 100) {
			alert("请选择需要执行的操作...");
			return;
		}

		if (confirm("确认执行操作吗？")) {
			location.href = "${ctx}/relevanceGroup/updateStatus.action?ids="
					+ ids + "&status=" + $("#status").val();
		}
	}
</script>
</head>
<body style="margin-top:2px;">

	<s:form action="showGroupList.action" method="post" namespace="/relevanceGroup"
		theme="simple" id="userForm" name="userForm">
		
		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 0px">
			<!-- 空行 -->
			<tr>
				<td></td>
			</tr>

			<tr>
				<td>
					<div class="box">
						<div class="right">
						<select id="status" class="jianju">

								<option value="100" style="margin-right:24px">--更多操作--</option>

								<option value="1">启用规则</option>

								<option value="0">停用规则</option>
							</select> 
							<input type="button" value="执行" style="margin-left:5px"
								class="btnstyle" onclick="updateStatus()" /> 
							<input type="button" value="添加" class="btnstyle"
								style="margin-right:5px;margin-top:-2px"
								onclick="addMonitorDialog();" /> <input type="button"
								value="删除" style="margin-right:12px;margin-top:-2px"
								class="btnstyle" onclick="deleteUser();" />
						</div>

						<span class="kuaiju">快速搜索</span> <input type="text" id="keyword"
							value="${keyword}" name="keyword" class="jianju" /> <img
							src="${ctx}/images/search.jpg" onclick="query();"
							style="margin-left:5px"> 
					</div></td>
			</tr>
			<tr>
				<td>
					<div class="sbox">
						<div class="cont">
							<!-- information area -->
							<div id="dataList">
								<table width="100%" border="0" cellspacing="1" cellpadding="0"
									class="tab2" id="listTable">
									<thead>
									<tr height="28" class="biaoti">
										<td width="6%" class="biaoti"><input type="checkbox"
											id="chkAll" name="chkAll" class="check-box not_checked" /></td>
										<th width="17%" class="biaoti">规则名称</th>
										<th width="20%" class="biaoti">创建时间</th>
										<th width="15%" class="biaoti">创建者</th>
										<th width="14%"  class="biaoti">状态</th>
										<th width="*" class="biaoti">描述</th>
									</tr>
									</thead>
									<tbody>
									<s:iterator value="ruleGroupList" status="stat">
										<tr>
											<td class="biaocm" valign="middle"><input
												type="checkbox" name="ids" id="${id}"
												value="${id}" class="check-box" /></td>
											<td valign="middle">${name}</td>
											<td valign="middle"><s:date
													name="time"
													format="yyyy-MM-dd HH:mm:ss" />
											</td>
											<td valign="middle">${creater}</td>
								<td valign="middle" class="td_list_data"><c:if
										test="${status==0}">停用</c:if> <c:if
										test="${status==1}">启用</c:if></td>
											<td valign="middle"><c:out value="${desc}"></c:out></td>
										</tr>
									</s:iterator>
									</tbody>
									<tr>
										<td colspan="6" width="100%"><jsp:include
												page="../../commons/page.jsp"></jsp:include></td>
									</tr>
								</table>
							</div>
							<!-- information area -->
						</div>
					</div>
				</td>
			</tr>
		</table>

	</s:form>

	<s:form action="addGroup.action" namespace="/relevanceGroup"
		method="post" theme="simple" id="queryForm" name="queryForm">
		<s:hidden name="relevanceRuleGroup.name" id="groupName" />
		<s:hidden name="relevanceRuleGroup.desc" id="groupMemo" />
		<s:hidden name="relevanceRuleGroup.status" id ="status1" value = "1" />
		<s:hidden name="relevanceRuleGroup.parentId"  value = "1"/>
	</s:form>

	<!-- ui-dialog -->
	<div id="dialog-extQuery" title="新建关联规则">
		<table width="99%" border="0" cellspacing="5" cellpadding="5"
			style="margin-left:5px;">
			<tr>
				<td width="16%" align = "right">规则名称:</td>
				<td><input id="realName" name="relevanceRuleGroup.name" type="text"
					style="width:250px;" onblur="checkName();" /> <span 
					id="check_Name"></span>
				</td>
			</tr>
			<tr>
				<td align = "right">描述:</td>
				<td><input id="creatorIP" name="relevanceRuleGroup.des" type="text"
					style="width:250px;" /></td>
			</tr>
			<tr>
				<td align = "right">是否启用:</td>
				<td align="left" class="padding"><input type="radio"
				name="relevanceRuleGroup.status" value="1" title="启用" checked="checked" id = "on" onclick="changeStatus('on')"/>启用
									
									
									
									 <input type="radio" onclick="changeStatus('off')"
									name="relevanceRuleGroup.status" value="0" id = "off" title="停用"/>停用
								</td>
								
			</tr>
		</table>
	</div>
</body>
</html>
