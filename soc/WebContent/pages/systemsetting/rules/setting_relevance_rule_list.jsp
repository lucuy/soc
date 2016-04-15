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
<h<%-- <script type="text/javascript" src="${ctx}/pushlet/ajax-pushlet-client.js"></script>--%>.js"></script>

<link href="${ctx}/styles/audit/audit.css" rel="stylesheet"
	type="text/css">
 <link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet"
	type="text/css"> 
	<%-- <link href="${ctx}/styles/jquery-ui-1.8.21.css" rel="stylesheet" type="text/css"> --%>
<link href="${ctx}/styles/jquery.popuplayer.css" rel="stylesheet"
	type="text/css">
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
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
		location.href = "${ctx}/relevanceRules/queryRelevanceRuleList.action?keyword="
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
					$(this).dialog("close");
				},

				"取消[Esc]" : function() {
					$(this).dialog("close");
				}
			}
		});
	});

	function deleteSelect() {
		
		
		
		
		var ids = "";
		$("[name=ids]:checkbox:checked").each(function() {
			if (ids != "")
				ids += "," + $(this).val();
			else
				ids = $(this).val();
		});
		if ($(":checkbox[checked = checked]").size() < 1) {
			alert("最少选择一个复选框!");
			return;
		}
		var table =document.getElementById("tab1");//获取行数
		   var rows = table.rows.length;
		var rex  = /[初]{1}/;
		var flg = false;
		//alert(rows);
		$("[name=ids]:checkbox:checked").each(function() {
			//alert($("#eventsName"+$(this).val()).html());
			if ( rex.test($("#eventsName"+$(this).val()).html()) && rows>3){
				alert("删除初始状态之前请先删除其他状态");
				flg = true;
				return;
			}
		});
		if(flg){
			return;
		}
		if (confirm("确认执行操作吗？")) {
			// alert(ids);
				
			//var groupId = $("#groupId").val();
			location.href = "/soc/relevanceRules/deleteRelevanceRule.action?ids="+ids;

//
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

		$('#relevanceRuleName').val($('#relevanceRuleNamevalue').val());

		$('#relevanceRuleType').val($('#relevanceRuleTypevalue').val());

		$('#queryForm').submit();
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
			location.href = "${ctx}/relevanceRules/updateStatus.action?ids="
					+ ids + "&status=" + $("#status").val();
		}
	}
	function toInsertPage() {
		//alert("{ctx}/filteringRules/toInsertFilterPage.action");
		var groupId = $("#groupId").val();
		location.href = "${ctx}/relevanceRules/toInsertRelevancePage.action?groupId="+groupId;

	}
</script>
</head>
<body>
	<s:form action="queryRelevanceRuleList" namespace="/relevanceRules"
		method="post" theme="simple" id="sysForm" name="sysForm">
		<input type="hidden" name="relevanceRuleName"
			value="${relevanceRuleName}" />
		<input type="hidden" name="groupId" id = "groupId"
			value="${groupId}" />

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
							<%--<span style="display:none;"><a href="#">高级</a> </span> <span>操作</span>

							<select id="status" class="jianju">

								<option value="100" style="margin-right:24px">--更多操作--</option>

								<option value="0">启用规则</option>

								<option value="1">停用规则</option>
							</select> 
							<input type="button" value="执行" style="margin-left:5px"
								class="btnstyle" onclick="updateStatus()" /> 
								--%><input type="button" value="添加" style="margin-left:5px"
								class="btnstyle" onclick="toInsertPage()" />
								<input type="button" value="删除" style="margin-left:5px"
								class="btnstyle" onclick="deleteSelect()" /> 
							 <span
								style="margin-right:5px"></span>

						</div>

						<%--<span class="kuaiju">快速搜索</span> <input type="text" id="keyword"
							value="${keyword}" name="keyword" class="jianju" /> <img
							src="${ctx}/images/search.jpg" onclick="query();"
							style="margin-left:5px"> <a href="#" class="jianju"
							onclick="extQueryDlg();">高级</a>
					--%></div></td>
			<tr>
				<td>
					<table style="margin-top: 4px" width="100%" border="0"
						cellspacing="1" cellpadding="0" class="tab2"  id = "tab1">
						<tr height="28" class="biaoti">
							<td width="3%" class="biaoti"><input type="checkbox"
								id="chkAll" name="chkAll" class="check-box not_checked" />
							</td>
							<td width="10%" align="center" class="biaoti">规则状态</td>
							<td width="15%" align="center" class="biaoti">事件名称</td>
							<td width="7%" align="center" class="biaoti">事件等级</td>
							<td width="*" align="center" class="biaoti">事件类型</td>
							<td width="14%" align="center" class="biaoti">事件类别</td>
							<td width="10%" align="center" class="biaoti">查看详情</td>
							<td width="17%" align="center" class="biaoti">更新时间</td>
						</tr>
						<c:set value="1" var="data" />
						<s:iterator value="list" status="s">
							<tr>
								<td class="biaocm" valign="middle"><input type="checkbox"
									name="ids" id="${relevanceRuleId}" value="${relevanceRuleId}"
									class="check-box" /></td>
								<td valign="middle" class="td_list_data" id = "eventsName${relevanceRuleId}" ><c:if
										test="${initState==1}">初始状态</c:if> <c:if
										test="${initState==0}">下一状态</c:if></td>
									<td valign="middle" class="td_list_data" >${eventsName }</td>	
									<td valign="middle" class="td_list_data">
										${level}级
								</td>	
								<td valign="middle" class="td_list_data">${eventsTypeIds }</td>	
									<td valign="middle" class="td_list_data">${eventsCategoryIds }</td>	
									
									<td valign="middle" class="td_list_data"><a
									href="selectRelevanceRuleById.action?id=${relevanceRuleId}">查看详情</a></td>	
									
								<td valign="middle" class="td_list_data"><s:date
										name="relevanceRuleMenderDate" format="yyyy-MM-dd HH:mm:ss" />
								</td>
							</tr>
						</s:iterator>
						<tr>
							<td colspan="8" width="100%"><jsp:include
									page="../../commons/page.jsp"></jsp:include></td>
						</tr>
					</table>
				</td>
			</tr>

		</table>
	</s:form>
	<!-- ext query from -->
	<s:form action="queryRelevanceRuleList" namespace="/relevanceRules"
		method="post" theme="simple" id="queryForm" name="queryForm">
		<s:hidden id="relevanceRuleName" name="relevanceRuleName" />
		<input type="hidden" id="relevanceRuleType" name="relevanceRuleType" />
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
				<td><input id="relevanceRuleNamevalue"
					name="relevanceRuleNamevalue" type="text" style="width:250px;"
					onkeypress="if(event.keyCode==13)extQuery();" />
				</td>
			</tr>
			<tr>
				<td>启用状态:</td>
				<td><select id="relevanceRuleTypevalue"
					name="relevanceRuleTypevalue">

						<option value="" style="margin-right:24px">--请选择关联规则状态--</option>

						<option value="0">启用规则</option>

						<option value="1">停用规则</option>
				</select>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>