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

<script language="javascript">
	function exportCSV() {
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
	}
	
	function updateStatus() {
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
	var rege =/^([^`~!@#$%^&*()=|{}':;, \\\[\].<>\/?~！@#￥……&*（）—|{}【】‘；：”“'。\"，、？])*$/;
	function query() {
		/* var time = $("#time").val();//最近修改用户
		var passwordPolicyStatus = $("#passwordPolicyStatus").val();//锁定
		
		var keyword = $("#keyword").val();//模糊
		var order = $("#order").val();//最近更新
		location.href = "${ctx}/passwordPolicy/query.action?keyword="
				+ encodeURIComponent(encodeURIComponent(keyword)) + "&time="
				+ time + "&passwordPolicyStatus=" + passwordPolicyStatus
				+ "&order=" + order; */
				
				 $('#keyword').val($.trim($('#keyword').val()));
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
		location.href = "${ctx}/passwordPolicy/query.action?keyword="
				+ encodeURI(encodeURI($('#keyword').val(),"utf-8"));
	}
	
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
					
				},

				"取消[Esc]" : function() {
					$('#msgs').html("");
					$(this).dialog("close");
				}
			}
		});
		
	    $('#listTable').tablesorter();
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
	
	function deletePasswordPolicy() {
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
		}

	function extQuery() {
	//去掉空格
		$('#selPasswordPolicyName').val($.trim($('#passwordPolicyName').val()));
		/* alert($('#selPasswordPolicyName').val()); */
		if($('#passwordPolicyName').val().length>50){
			alert("搜索长度不能大于50");
			$('#passwordPolicyName').val('');
			$('#passwordPolicyName').focus();
			return ;
		}
		if(!rege.test($('#passwordPolicyName').val())){
			alert("输入的内容包含特殊字符，请重新输入");
			$('#passwordPolicyName').val('');
			$('#passwordPolicyName').focus();
			return;
		}
		if($('#passwordPolicyPasswordLength').val().length>2){
			alert("密码长度不能大于2");
			$('#passwordPolicyPasswordLength').val('');
			$('#passwordPolicyPasswordLength').focus();
			return ;
		}
		var len =$('#passwordPolicyPasswordLength').val();
		var reg =/^\d$/;
		var html="";
		$('#msgs').html("");
		if(len!=null&&len!=""){
			if(reg.test(len)){
				if(len<6){
					alert("密码长度要大于6!");
				}else{
					$('#selPasswordPolicyPasswordLength').val($.trim($('#passwordPolicyPasswordLength').val()));
					$('#queryForm').submit();
				}
			}else{
				alert("请输入数字!");
			}
			$('#msgs').replaceAll("");
			$('#msgs').append(html);
			$(this).dialog("close");
		}else{
			
			$('#queryForm').submit();
			$(this).dialog("close");
		}
		
		
	}
	
</script>
</head>

<body style="margin-top:2px;">
	<s:form action="query.action" namespace="/passwordPolicy" method="post"
		theme="simple" id="empForm" name="empForm">
	 <input type="hidden" name="selPasswordPolicyName"
			value="${selPasswordPolicyName}" />
		<input type="hidden" name="selPasswordPolicyPasswordLength"
			value="${selPasswordPolicyPasswordLength}" /> 
		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 0px">
			<!-- 空行 -->
			<tr>
				<td> <s:hidden
						name="passwordPolicyStatus" id="passwordPolicyStatus" /> <s:hidden
						name="order" id="order" /></td>
			</tr>

			<tr>
				<td>
					<div class="box">

						<div class="right">

							<input type="button" value="添加" class="btnstyle"
								style="margin-right:5px;margin-top:-2px"
								onclick="location.href='${ctx}/passwordPolicy/edit.action';" />

							<input type="button" value="删除"
								style="margin-right:12px;margin-top:-2px" class="btnstyle"
								onclick="deletePasswordPolicy();" />
						</div>

						<span class="kuaiju">快速搜索</span> <input type="text" id="keyword"
							value="${keyword}" name="keyword" class="jianju" style="height: 15px;" /> <img
							src="${ctx}/images/search.jpg" onclick="query();"
							style="margin-left:5px"> <a href="#" class="jianju"
							onclick="extQueryDlg();">高级</a> <span style="margin-left:24px">操作</span>

						<select id="status" class="jianju">

							<option value="100">--更多操作--</option>

							<option value="0">锁定密码策略</option>

							<option value="1">激活密码策略</option>
						</select> <input type="button" value="执行" style="margin-left:5px"
							class="btnstyle" onclick="updateStatus()" />

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
										<th width="15%" class="biaoti">密码策略名称</th>
										<th width="15%" class="biaoti">密码策略描述</th>
										<th width="15%" class="biaoti">密码长度</th>
										<th width="10%" class="biaoti">状态</th>
										<th width="15%" class="biaoti">锁定时长</th>
									</tr>
									</thead>
									<tbody>
									<c:set value="1" var="root" />
									<c:set value="" var="resStr" />
									<s:property value="#session.XXX" />
									<s:iterator value="passwordPolicyList" status="stat">
									<%-- 	<input  name="passwordPolicyId"
											id="passwordPolicyId" value="${passwordPolicyId}" /> --%>
										<tr>
											<td class="biaocm" valign="middle"><input
												type="checkbox" name="ids" id="${passwordPolicyId}"
												value="${passwordPolicyId}" class="check-box" /></td>
											<td valign="middle"><a
												href="${ctx}/passwordPolicy/edit.action?passwordPolicyId=
												${passwordPolicyId}&passwordPolicyStatus=${passwordPolicyStatus}&order=${order}">${passwordPolicyName}</a>
											</td>
											<td valign="middle"><c:out value="${passwordPolicyMemo}"></c:out></td>
											<td valign="middle">${passwordPolicyPasswordLength}</td>
											<td valign="middle" align="center"><c:if
													test="${passwordPolicyStatus==0}">锁定</c:if> <c:if
													test="${passwordPolicyStatus==1}">激活</c:if>
											</td>
											<c:set value="0" var="root2" />
											<c:set value="" var="resStr" />

											<td valign="middle">${passwordPolicyLockRecoveTime}</td>
										</tr>
										<c:set value="${root+1}" var="root" />
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

	<!-- ext query from -->
	<s:form action="query.action" namespace="/policy" method="post"
		theme="simple" id="queryForm" name="queryForm">
		<s:hidden name="selPasswordPolicyName" id="selPasswordPolicyName" />
		<s:hidden name="selPasswordPolicyPasswordLength"
			id="selPasswordPolicyPasswordLength" />
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

	<!-- ui-dialog -->
	<div id="dialog-extQuery" title="高级查询">
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
			<tr>
				<td colspan="2"> <font color="red"> <span id="msgs"></span></font></td>
			</tr>
		</table>
	</div>
	<input type="hidden" id="actionStr" value="${actionStr }" /> 
	
</body>
</html>
