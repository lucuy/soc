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
<%-- <script type="text/javascript" src="${ctx}/pushlet/ajax-pushlet-client.js"></script>--%>

<link href="${ctx}/styles/audit/audit.css" rel="stylesheet" type="text/css">
 <link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet" type="text/css">
<%-- <link href="${ctx}/styles/jquery-ui-1.8.21.css" rel="stylesheet" type="text/css"> --%>
<link href="${ctx}/styles/jquery.popuplayer.css" rel="stylesheet" type="text/css">

<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="${ctx }/scripts/jquery.tablesorter.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
<script type='text/javascript' src="${ctx}/scripts/popuplayer.js"></script>
<script type='text/javascript' src="${ctx}/scripts/jquery.validationEngine.js"></script>
<script language="javascript">
//验证字符串是否包含特殊字符的正则
var rege =/^([^`~!@#$%^&*()=|{}':;, \\\[\].<>\/?~！@#￥……&*（）—|{}【】‘；：”“'。\"，、？])*$/;
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
		location.href = "${ctx}/alertRule/alertRuleQuery.action?keyword="
				+ encodeURI(encodeURI($('#keyword').val(),"utf-8"));
	}
	
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

		// Dialog			
		$('#dialog-extQuery').dialog({
			autoOpen : false,
			width : 450,
			height : 220,
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
		if (ids.length < 1) {
			alert("最少选择一个复选框!");
			return;
		}
		if(window.confirm("确定要删除？")){
		location.href = '${ctx}/alertRule/deleteRule.action?ids=' + ids;
		}
	}

	function extQueryDlg() {
		$('#dialog-extQuery').dialog('open');
	}

	function extQuery() {
		$('#ruleName').val($.trim($('#sRuleName').val()));
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
	    $('#userLoginName').val($.trim($('#userLoginName').val()));
		//$('#assetIp').val($.trim($('#assetIp').val()));
		$('#ruleUpdateTime').val($.trim($('#sRuleUpdateTime').val()));
		$('#ruleCreateTime').val($('#ruleCreateTime').val());
		$('#queryForm').submit();
	}
</script>
</head>
<body>
	<s:form action="alertRuleQuery.action" namespace="/alertRule" method="post"
		theme="simple" id="sysForm" name="sysForm">
		<input type="hidden" name="ruleName" value="${ruleName}"/>
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

							<input type="button" value="添加" class="btnstyle"
								style="margin-right:12px;margin-top:5px"
								onclick="location.href='${ctx}/alertRule/editRule.action';" />
							<input type="button" value="删除" class="btnstyle"
								style="margin-right:12px;margin-top:5px"
								onclick="deleteSelect();" />

						</div>

						<span class="kuaiju">快速搜索</span> <input type="text" id="keyword"
							value="${keyword}" name="keyword" class="jianju" /> <img
							src="${ctx}/images/search.jpg" onclick="query();"
							style="margin-left:5px"> <a href="#" class="jianju"
							onclick="extQueryDlg();">高级</a>
					</div></td>
			<tr>
				<td>
					<table style="margin-top: 4px ;table-layout:fixed;word-wrap:break-word;" width="100%" border="0"
						cellspacing="1" cellpadding="0" class="tab2" id="listTable">
						<thead>
						<tr height="28" class="biaoti">
							<td width="6%" class="biaoti"><input type="checkbox"
								id="chkAll" name="chkAll" class="check-box not_checked" />
							</td>
							<th width="22%" align="center" class="biaoti">规则名称</th>
							<th width="22%" align="center" class="biaoti">关联账号</th>
							<!-- <td width="25%" align="center" class="biaoti">关联资产</td> -->
							<th width="25%" align="center" class="biaoti">更新时间</th>
							<th width="25%" align="center" class="biaoti">创建时间</th>
						</tr>
						</thead>
						<tbody>
						<c:set value="1" var="data" />

						<s:iterator value="#request.ruleList" status="stat">
							 <%-- <input type="hidden" name="ruleId" id="ruleId" value="${ruleId}" /> --%>
								
							<tr>
								<td class="biaocm" valign="middle"><input type="checkbox"
									name="ids" id="${ruleId}" value="${ruleId}" class="check-box" />
								</td>
								<td valign="middle" class="td_list_data">
								<a href="${ctx}/alertRule/editRule.action?ruleId=${ruleId}">
								${ruleName}</a></td>
								
								<td valign="middle" class="td_list_data"><c:forEach
										items="${ruleUserList}" var="l">${l.userLoginName}&nbsp;</c:forEach>
								</td>
								
								<%-- <td valign="middle" class="td_list_data"><c:forEach
										items="${ruleAssetList}" var="l">${l.assetIp}&nbsp;</c:forEach>
								</td> --%>
								
								<td valign="middle" class="td_list_data"><s:date
										name="ruleCreateTime" format="yyyy-MM-dd HH:mm:ss" /></td>
										<td valign="middle" class="td_list_data"><s:date
										name="ruleUpdateTime" format="yyyy-MM-dd HH:mm:ss" /></td>
							</tr>
							
							<c:set value="${data+1}" var="data" />
						</s:iterator>
						</tbody>

						<tr>
							<td colspan="6" width="100%"><jsp:include
									page="../commons/page.jsp"></jsp:include></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</s:form>
	<!-- ext query from -->
	<s:form action="alertRuleQuery.action" namespace="/alertRule" method="post"
		theme="simple" id="queryForm" name="queryForm">
		<s:hidden id="ruleName" name="ruleName" />
		<s:hidden id="userLoginName" name="userLoginName" />
		<!-- <s:hidden id="assetIp" name="assetIp" /> -->
		<s:hidden id="ruleUpdateTime" name="ruleUpdateTime" />
		<s:hidden id="ruleCreateTime" name="ruleCreateTime" />
	</s:form>
	
	<!-- 高级查询弹出框 -->
	<div id="dialog-extQuery" title="高级查询">
		<table width="90%" border="0" cellspacing="5" cellpadding="5"
			style="margin-left:20px;">
			<tr>
				<td width="25%">规则名:</td>
				<td><input id="sRuleName" name="sRuleName" type="text"
					style="width:250px;" onkeypress="if(event.keyCode==13)extQuery();" />
				</td>
			</tr>
			<!-- <tr>
				<td>规则更新时间:</td>
				<td><input id="sRuleUpdateTime" name="sRuleUpdateTime" type="text" 
					style="width:250px;" onkeypress="if(event.keyCode==13)extQuery();" />
				</td>
			</tr>
			<tr>
			<td>规则创建时间:</td>
				<td><input id="sRuleCreateTime" name="sRuleCreateTime"
					type="text" style="width:250px;"
					onkeypress="if(event.keyCode==13)extQuery();" /></td>
			</tr> -->
		</table>
	</div>
</body>
</html>