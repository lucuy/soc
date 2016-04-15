<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>

<html>
<head>
    <base href="<%=basePath%>">
    <title>原始事件列表</title>
    <link href="${ctx}/images/favicon.ico" rel="shortcut icon"/>
    <link href="${ctx}/images/favicon.ico" rel="Bookmark"/>
    
<link href="${ctx}/styles/user/user.css" rel="stylesheet"
	type="text/css">
<%-- <link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet"
	type="text/css"> --%>
	<link href="${ctx}/styles/jquery-ui-1.8.21.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/jquery.popuplayer.css" rel="stylesheet"
	type="text/css">
<script type='text/javascrjquery-1.7.2.min.jsripts/jquery-1.4.4.min.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
<script type='text/javascript' src="${ctx}/scripts/popuplayer.js"></script>

<script language="javascript">
	/* function exportCSV() {
		var ids = "";
		$("[name=ids]:checkbox:checked").each(function() {
			if (ids != "")
				ids += "," + $(this).val();
			else
				ids = $(this).val();

		});
		if ($("#chkAll").attr("checked") == true) {
			if (confirm("你是要导出选中/全部的数据...")) {
				location.href = "${ctx}/employee/checkExport.action";
			} else {
				location.href = "${ctx}/employee/checkExport.action?ids=" + ids;
			}
		}
		if ($("#chkAll").attr("checked") == false) {
			location.href = "${ctx}/employee/checkExport.action?ids=" + ids;
		}
	} */
	
	/* function updateStatus() {
		var ids = "";
		$("[name=ids]:checkbox:checked").each(function() {
			if (ids != "") {
				ids += "," + $(this).val();
			}
			else {
				ids = $(this).val();
			}
		});
		if ($(":checkbox[checked = true]").size() < 1) {
			alert("请至少选择一个密码策略信息...");
			return;
		}
		
		if($("#status").val() == 100) {
			alert("请选择需要执行的操作...");
			return;
		}
		
		if (confirm("确认执行操作吗？")) {
			location.href = "${ctx}/passwordPolicy/updatepasswordPolicyStatus.action?ids=" + ids
			 + "&passwordPolicyStatus=" + $("#status").val();
					
		}
	}
 */
	/* function query() { */
		/* var time = $("#time").val();//最近修改用户
		var passwordPolicyStatus = $("#passwordPolicyStatus").val();//锁定
		
		var keyword = $("#keyword").val();//模糊
		var order = $("#order").val();//最近更新
		location.href = "${ctx}/passwordPolicy/query.action?keyword="
				+ encodeURIComponent(encodeURIComponent(keyword)) + "&time="
				+ time + "&passwordPolicyStatus=" + passwordPolicyStatus
	 			+ "&order=" + order; */
			/*	 $('#keyword').val($.trim($('#keyword').val()));
		location.href = "${ctx}/passwordPolicy/query.action?keyword="
				+ encodeURI(encodeURI($('#keyword').val(),"utf-8"));
	} */
	
	$(document).ready(function() {
		$("#keyword").keydown(function(event) {
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
	});

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

	function overDlg(e, header, content, num) {
		$('#blk_header').html(header);
		$('#blk_content').html(content);

		createOverDiv(e, 10, 10, $('#blk').html());
	}

	function extQueryDlg() {
		$('#dialog-extQuery').dialog('open');
	}
	
	/* function deletePasswordPolicy() {
		var ids = "";
		$("[name=ids]:checkbox:checked").each(function() {
			if (ids != "") {
				ids += "," + $(this).val();
			}
			else {
				ids = $(this).val();
			}
		});
		if ($(":checkbox[checked = true]").size() < 1) {
			alert("请至少选择一个密码策略信息...");
			return;
		}
		if (confirm("确认执行操作吗？")) {
		location.href = "location.href='${ctx}/passwordPolicy/delete.action?ids="+ids;
		}
		} */

	/* function extQuery() {
	//去掉空格
		$('#selPasswordPolicyName').val($.trim($('#passwordPolicyName').val()));
		/* alert($('#selPasswordPolicyName').val()); */
	/* 	$('#selPasswordPolicyPasswordLength').val($.trim($('#passwordPolicyPasswordLength').val()));
		$('#queryForm').submit();
	}  */
</script>
</head>

<body style="margin-top:2px;">
	<s:form  action="/events/queryOriginalEvent.action"  method="post"
		theme="simple" id="empForm" name="empForm">
	  
	     <input type="hidden" name="tableName" value="${tableName}">
	     
	     <input type="hidden" name="eventsLogIdentification" value="${eventsLogIdentification}">
<%-- 	 <input type="hidden" name="selPasswordPolicyName"
			value="${selPasswordPolicyName}" />
		<input type="hidden" name="selPasswordPolicyPasswordLength"
			value="${selPasswordPolicyPasswordLength}" />  --%>
		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 0px">
			<!-- 空行 -->
			<tr>
				<td></td>
			</tr>

			<tr>
				<td>
					<div class="box" style = "font-weigh:blod;">
		<span style = "margin-left:10px; font-weigh:blod; font-size:15px;">原始事件详情 </span>
					</div></td>
			</tr>
			<tr>
				<td>
					<div class="sbox">
						<div class="cont">
							<!-- information area -->
							<div id="dataList">
								<table width="100%" border="0" cellspacing="1" cellpadding="0"
									class="tab2">
									<tr height="28">
										<td width="85%" class="biaoti">事件详情</td>
										<td width="15%" class="biaoti">关联资产</td>
									</tr>
									<c:set value="1" var="root" />
									<c:set value="" var="resStr" />
									<s:property value="#session.XXX" />
									<s:iterator value="originalEventsList" status="stat">
										<%-- <input type="hidden" name="passwordPolicyId"
											id="passwordPolicyId" value="${passwordPolicyId}" /> --%>
										<tr>
											<%-- <td class="biaocm" valign="middle"><input
												type="checkbox" name="ids" id="${passwordPolicyId}"
												value="${passwordPolicyId}" class="check-box" /></td> --%>
											<td valign="middle">${originalContent}
											</td>	
											<td valign="middle">${relAssetName}
											</td>										
											<c:set value="0" var="root2" />
											<c:set value="" var="resStr" />

										</tr>
										<c:set value="${root+1}" var="root" />
									</s:iterator>
									<tr>
										<td colspan="2" width="100%"><jsp:include
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

	<!-- ext query from -->
	<!-- <s:form action="query.action" namespace="/policy" method="post"
		theme="simple" id="queryForm" name="queryForm">
		<s:hidden name="selPasswordPolicyName" id="selPasswordPolicyName" />
		<s:hidden name="selPasswordPolicyPasswordLength"
			id="selPasswordPolicyPasswordLength" />
	</s:form> -->

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

	<!-- ui-dialog -->
	<!-- <div id="dialog-extQuery" title="高级查询">
		<table width="90%" border="0" cellspacing="5" cellpadding="5"
			style="margin-left:20px;">
			<tr>
				<td width="25%">密码策略名:</td>
				<td><input id="passwordPolicyName" name="passwordPolicyName"
					type="text" style="width:250px;"
					onkeypress="if(event.keyCode==13)extQuery();" /></td>
			</tr>
			<tr>
				<td>密码长度:</td>
				<td><input id="passwordPolicyPasswordLength"
					name="passwordPolicyPasswordLength" type="text"
					style="width:250px;" onkeypress="if(event.keyCode==13)extQuery();" />
				</td>
			</tr>
		</table>
	</div> -->
</body>
</html>
