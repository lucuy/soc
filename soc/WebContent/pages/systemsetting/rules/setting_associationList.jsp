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

<link href="${ctx}/styles/audit/audit.css" rel="stylesheet"
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
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine.js"></script>
<script language="javascript">

	//sousuo
	function query() {
		$('#keyword').val($.trim($('#keyword').val()));
		var str=$('#keyword').val().match(/^([^`~!@#$%^&*()=|{}':;, \\\[\].<>\/?~！@#￥……&*（）—|{}【】‘；：”“'。\"，、？])*$/); 
		if(str==null){
			alert("关联规则名称包含特殊字符！");
			return;
		}
		location.href = "${ctx}/settingAssociationRules/AssociationRules.action?keyword="
				+ encodeURI(encodeURI($('#keyword').val(), "utf-8"));
	}
	$(document).ready(function() {
		$('#keyword').keydown(function(event) {
			if (event.keyCode == 13) {
				query();
			}
		});

		// Dialog			
		$('#dialog-extQuery').dialog({
			autoOpen : false,
			width : 450,
			height : 200,
			buttons : {
				"查询[Enter]" : function() {
					extQuery();
				},

				"取消[Esc]" : function() {
					$(this).dialog("close");
				}
			}
		});
		$('#listTable').tablesorter();
	});

	function deleteSelect() {
		var ids = "";
		$("[name=ids]:checkbox:checked").each(function() {
			if (ids != "")
				ids += "," + $(this).val();
			else
				ids = $(this).val();

		});
		if ($(":checkbox[checked = true]").size() < 1) {
			alert("最少选择一个复选框!");
			return;
		}
		location.href = '${ctx}/systemAudit/delete.action?ids=' + ids;
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

	$(document).click(function() {
		//shiftForCheckBoxFun(event);
		destroyOverDlg();
	});

	function overDlg(e, header, content, num) {

		$('#blk_header').html(header);
		$('#blk_content').html(content);

		createOverDiv(e, 10, 10, $('#blk').html());
	}

	function extQueryDlg() {
		$('#dialog-extQuery').dialog('open');
	}

	function extQuery() {
		$('#associationName').val($.trim($('#associationName').val()));
		var str=$('#associationName').val().match(/^([^`~!@#$%^&*()=|{}':;, \\\[\].<>\/?~！@#￥……&*（）—|{}【】‘；：”“'。\"，、？])*$/); 
		if(str==null){
			alert("关联规则名称包含特殊字符！");
			return;
		}
		$('#filterName').val($('#associationName').val());
		$('#associationType').val($.trim($('#associationType').val()));
		$('#filterType').val($('#associationType').val());
		$('#queryForm').submit();
	}
	//更新状态
	function updateStatus() {
		var ids = "";
		$("[name=filterId]:checkbox:checked").each(function() {
			if (ids != "") {
				ids += "," + $(this).val();
			} else {
				ids = $(this).val();
			}
		});
		if ($(":checkbox[checked = true]").size() < 1) {
			alert("请至少选择一个关联规则信息...");
			return;
		}

		if ($("#status").val() == 100) {
			alert("请选择需要执行的操作...");
			return;
		}

		if (confirm("确认执行操作吗？")) {
			location.href = "${ctx}/settingAssociationRules/updateAssociationRules.action?filterId="
					+ ids + "&associationStatus=" + $("#status").val();
		}
	}

</script>
</head>
<body>
	<s:form action="AssociationRules.action" namespace="/settingAssociationRules" method="post" theme="simple"
		id="sysForm" name="sysForm">
		<input type="hidden"  name="filterName" value="${filterName}"/>
		<input type="hidden"  name="filterType" value="${filterType}"/>
		
		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 2px">
			<!-- 空行-->
			<tr>
				<td></td>
			</tr>
			<tr>
				<td>
					<div class="box">

						<div class="right">

							<!-- <input type="button" value="导出Excel" class="btnstyle" style="margin-right:12px;margin-top:5px"
				   onclick="exportExcel();"/> -->
							<span style="display:none;"><a href="#">高级</a> </span> <span>操作</span>

							<select id="status" class="jianju">

								<option value="100" style="margin-right:24px">--更多操作--</option>

								<option value="0">启用规则</option>

								<option value="1">停用规则</option>
							</select> <input type="button" value="执行" style="margin-left:5px"
								class="btnstyle" onclick="updateStatus()" /> <span style="margin-right:5px"></span>

						</div>

						<span class="kuaiju">快速搜索</span> <input type="text" id="keyword"
							value="${keyword}" name="keyword" class="jianju" /> <img
							src="${ctx}/images/search.jpg" onclick="query();"
							style="margin-left:5px"> <a href="#" class="jianju"
							onclick="extQueryDlg();">高级</a>
					</div>
				</td>
			<tr>
				<td>
					<table style="margin-top: 4px" width="100%" border="0"
						cellspacing="1" cellpadding="0" class="tab2" id="listTable">
						<thead>
						<tr height="28" class="biaoti">
							<td width="6%" class="biaoti"><input type="checkbox"
								id="chkAll" name="chkAll" class="check-box not_checked" /></td>
							<th width="16%" align="center" class="biaoti">关联规则名称</th>
							<th width="14%" align="center" class="biaoti">状态</th>
							<th width="25%" align="center" class="biaoti">更新时间</th>
						</tr>
						</thead>
						<tbody>
						<c:set value="1" var="data" />
						<s:iterator value="associationList">
							<tr>
								<td class="biaocm" valign="middle"><input type="checkbox"
									name="filterId" id="${filterId}" value="${filterId}"
									class="check-box" />
								</td>
								<td valign="middle" class="td_list_data">${filterName}</td>
								<td valign="middle" class="td_list_data">
									<c:if test="${filterType==1}">停用</c:if>
									<c:if test="${filterType==0}">启用</c:if>
								</td>
								<td valign="middle" class="td_list_data"><s:date
										name="filterUpdateTime" format="yyyy-MM-dd HH:mm:ss" /></td>
							</tr>
						</s:iterator>
						</tbody>
						<tr>
							<td colspan="4" width="100%"><jsp:include
									page="../../commons/page.jsp"></jsp:include></td>
						</tr>
					</table></td>
			</tr>

		</table>
	</s:form>
	<!-- ext query from -->
	<s:form action="AssociationRules.action" namespace="/settingAssociationRules" method="post"
		theme="simple" id="queryForm" name="queryForm">
		<s:hidden id="filterName" name="filterName" />
		<s:hidden id="filterType" name="filterType" />
	</s:form>
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

	<!-- ui-dialog -->
	<div id="dialog-extQuery" title="高级查询">
		<table width="90%" border="0" cellspacing="5" cellpadding="5"
			style="margin-left:20px;">
			<tr>
				<td width="25%">关联规则名称:</td>
				<td><input id="associationName" name="associationName" type="text"
					style="width:250px;" onkeypress="if(event.keyCode==13)extQuery();" />
				</td>
			</tr>
			<!-- <tr>
				<td>创建时间:</td>
				<td><input id="operationIp" name="operationIp" type="text"
					style="width:250px;" onkeypress="if(event.keyCode==13)extQuery();" />
				</td>
			</tr> -->
			<!-- <tr>
				<td>创建者:</td>
				<td><input id="operationTime" name="operationTime" type="text"
					style="width:250px;" onkeypress="if(event.keyCode==13)extQuery();" />
				</td>
			</tr> -->
			<!-- <tr>
				<td>更新时间:</td>
				<td><input id="information" name="information" type="text"
					style="width:250px;" onkeypress="if(event.keyCode==13)extQuery();" />
				</td>
			</tr> -->
			<!-- <tr>
				<td>状态:</td>
				<td><input id="associationType" name="associationType"
					type="text" style="width:250px;"
					onkeypress="if(event.keyCode==13)extQuery();" />
				</td>
			</tr> -->
			<tr><td>关联规则状态:</td>
			<td>
			<select id="associationType" name="associationType">

				<option value="" style="margin-right:24px">--请选择关联规则状态--</option>

				<option value="0">启用规则</option>

				<option value="1">停用规则</option>
			</select>
			</td></tr>
		</table>
	</div>
</body>
</html>