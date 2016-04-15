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
		location.href = "${ctx}/monitorGroup/showGroups.action?keyword="
				+ encodeURIComponent(encodeURIComponent(keyword));
	}
	function addMonitorDialog() {
	    $('#check_Name').empty();
		$('#realName').val("");
		$('#creatorIP').val("");
		$('#dialog-extQuery').dialog("open");
	}
	var rege =/^([^`~!@#$%^&*()=|{}':;, \\\[\].<>\/?~！@#￥……&*（）—|{}【】‘；：”“'。\"，、？])*$/;
	function addMonitorGroup() {
		if (flag == 1) {
			if($('#realName').val().length>50){
				alert("频道名称长度不能大于50");
				$('#realName').val('');
				$('#realName').focus();
				return ;
			}
			if(!rege.test($('#realName').val())){
				alert("输入的内容包含特殊字符，请重新输入");
				$('#realName').val('');
				$('#realName').focus();
				return;
			}
			if($('#creatorIP').val().length>50){
				alert("描述长度不能大于255");
				$('#creatorIP').val('');
				$('#creatorIP').focus();
				return ;
			}
			
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
			location.href = "${ctx}/monitorGroup/deleteGroups.action?ids="
					+ ids;
		}
	}

	function checkName() {
	    $('#check_Name').empty();
		if ($('#realName').val() == "") {
			//alert("213");
			$('#check_Name').html("名称不能为空");
			flag = 0;
		}
		else
		{
		    flag = 1;
		}
		//alert($('#realName').val());
		//if($('#check_Name').val())
	}
</script>
</head>
<body style="margin-top:2px;">

	<s:form action="showGroups.action" namespace="/monitorGroup" method="post"
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
							<input type="button" value="添加" class="btnstyle"
								style="margin-right:5px;margin-top:-2px"
								onclick="addMonitorDialog();" /> <input type="button"
								value="删除" style="margin-right:12px;margin-top:-2px"
								class="btnstyle" onclick="deleteUser();" />
						</div>

						<%-- <span class="kuaiju">快速搜索</span> <input type="text" id="keyword"
							value="${keyword}" name="keyword" class="jianju" /> <img
							src="${ctx}/images/search.jpg" onclick="query();"
							style="margin-left:5px"> --%>
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
										<th width="30%" class="biaoti">频道名称</th>
										<th width="15%" class="biaoti">创建时间</th>
										<th width="15%" class="biaoti">创建者</th>
									</tr>
									</thead>
									<tbody>
									<s:iterator value="monitorGroupList" status="stat">
										<tr>
											<td class="biaocm" valign="middle"><input
												type="checkbox" name="ids" id="${monitorGroupId}"
												value="${monitorGroupId}" class="check-box" /></td>
											<td valign="middle">${monitorGroupName}</td>
											<td valign="middle"><s:date
													name="monitorGroupCreateDateTime"
													format="yyyy-MM-dd HH:mm:ss" />
											</td>
											<td valign="middle">${monitorGroupCreateUserLoginName}</td>
										</tr>
									</s:iterator>
									</tbody>
									<tr>
										<td colspan="4" width="100%"><jsp:include
												page="../commons/page.jsp"></jsp:include></td>
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

	<s:form action="addGroup.action" namespace="/monitorGroup"
		method="post" theme="simple" id="queryForm" name="queryForm">
		<s:hidden name="groupName" id="groupName" />
		<s:hidden name="groupMemo" id="groupMemo" />
	</s:form>

	<!-- ui-dialog -->
	<div id="dialog-extQuery" title="新建频道信息">
		<table width="99%" border="0" cellspacing="5" cellpadding="5"
			style="margin-left:5px;">
			<tr>
				<td width="16%">频道名称:</td>
				<td><input id="realName" name="realName" type="text"
					style="width:250px;" onblur="checkName();" /> <span
					id="check_Name"></span>
				</td>
			</tr>
			<tr>
				<td>描述:</td>
				<td><input id="creatorIP" name="creatorIP" type="text"
					style="width:250px;" /></td>
			</tr>
		</table>
	</div>
</body>
</html>
