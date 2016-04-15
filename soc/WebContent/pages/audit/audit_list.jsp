<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<%@page import="com.soc.model.user.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();

	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html>
<head>
<%-- <script type="text/javascript" src="${ctx}/pushlet/ajax-pushlet-client.js"></script>--%>

<link href="${ctx}/styles/audit/audit.css" rel="stylesheet"
	type="text/css">
 <link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet"
	type="text/css">
<%-- <link href="${ctx}/styles/jquery-ui-1.8.21.css" rel="stylesheet"
	type="text/css"> --%>
<link href="${ctx}/styles/jquery.popuplayer.css" rel="stylesheet"
	type="text/css">
<link href="${ctx}/styles/fontColor.css" rel="stylesheet"
	type="text/css">

<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx }/scripts/jquery-latest.js"></script>
<script type="text/javascript" src="${ctx }/scripts/jquery.tablesorter.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
<script type='text/javascript' src="${ctx}/scripts/popuplayer.js"></script>
<%-- <script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine.js"></script>--%>



<script language="javascript">
	//excel
	function exportExcel() {
		var ids = "";
		$("[name=ids]:checkbox:checked").each(function() {
			if (ids != "") {
				ids += "," + $(this).val();

			} else {

				ids = $(this).val();

			}

		});
		
		
			if(ids!=""){
				if (confirm("你是要导出选中的审计信息...")) {
					location.href = "${ctx}/audit/export.action?ids="
						+ ids;
				} 
			}else{
				if (confirm("你是要导出全部的审计信息...")) {
					location.href = "${ctx}/audit/export.action?ids="
						+ ids
						+ "&keyword="
						+ encodeURI(encodeURI($("#keyword").val()))+"&auditUserLoginName="+ encodeURI(encodeURI($("#auditUserLoginName").val()))
						+"&auditOperationIp="+$('#auditOperationIp').val()+"&auditInformation="+encodeURI(encodeURI($("#auditInformation").val()))+"&auditOperationProcedure="
						+encodeURI(encodeURI($("#auditOperationProcedure").val()));
				}
			}
			
		
	}
	
	
	var rege =/^([^`~!@#$%^&*()=|{}':;, \\\[\].<>\/?~！@#￥……&*（）—|{}【】‘；：”“'。\"，、？])*$/;
	//sousuo
	function query() {
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
		location.href = "${ctx}/audit/query.action?keyword="
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
			height : 220,
			buttons : {
				"查询[Enter]" : function() {
					extQuery();

				},

				"取消[Esc]" : function() {
					$(this).dialog("close");
				}
			}
		});
		
		$("#myTable").tablesorter();
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
		var regex = /^((([01]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))[.]){3}(([0-1]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))$/;
		if($('#userLoginName').val().length>50){
			alert("登录名长度不能大于50");
			$('#userLoginName').val('');
			$('#userLoginName').focus();
			return ;
		}
		if(!rege.test($('#userLoginName').val())){
			alert("输入的内容包含特殊字符，请重新输入");
			$('#userLoginName').val('');
			$('#userLoginName').focus();
			return;
		}
		if($('#information').val().length>50){
			alert("操作日志长度不能大于50");
			$('#information').val('');
			$('#information').focus();
			return ;
		}
		if(!rege.test($('#information').val())){
			alert("输入的内容包含特殊字符，请重新输入");
			$('#information').val('');
			$('#information').focus();
			return;
		}
		if($('#operationProcedure').val().length>50){
			alert("操作内容长度不能大于50");
			$('#operationProcedure').val('');
			$('#operationProcedure').focus();
			return ;
		}
		if(!rege.test($('#operationProcedure').val())){
			alert("输入的内容包含特殊字符，请重新输入");
			$('#operationProcedure').val('');
			$('#operationProcedure').focus();
			return;
		}
		if ($("#operationIp").val() == "") {
			$('#userLoginName').val($.trim($('#userLoginName').val()));
			$('#auditUserLoginName').val($('#userLoginName').val());
			$('#operationIp').val($.trim($('#operationIp').val()));
			$('#auditOperationIp').val($('#operationIp').val());
			$('#operationTime').val($.trim($('#operationTime').val()));
			$('#auditOperationTime').val($('#operationTime').val());
			$('#information').val($.trim($('#information').val()));
			$('#auditInformation').val($('#information').val());
			$('#operationProcedure')
					.val($.trim($('#operationProcedure').val()));
			$('#auditOperationProcedure').val($('#operationProcedure').val());
			$('#sysForm').submit();
			$(this).dialog("close");
		} else {
			if (regex.test($("#operationIp").val())) {
				$('#userLoginName').val($.trim($('#userLoginName').val()));
				$('#auditUserLoginName').val($('#userLoginName').val());
				$('#operationIp').val($.trim($('#operationIp').val()));
				$('#auditOperationIp').val($('#operationIp').val());
				$('#operationTime').val($.trim($('#operationTime').val()));
				$('#auditOperationTime').val($('#operationTime').val());
				$('#information').val($.trim($('#information').val()));
				$('#auditInformation').val($('#information').val());
				$('#operationProcedure').val(
						$.trim($('#operationProcedure').val()));
				$('#auditOperationProcedure').val(
						$('#operationProcedure').val());
				$('#sysForm').submit();
				$(this).dialog("close");
			} else {
				alert("请输入正确的ip");
				$('#operationIp').val('');
				$('#operationIp').focus();

			}
		}
	}
	function destory()
	{
	   destroyOverDlg();
	}
	
	function destory(){
		destroyOverDlg();
	}
	 //打印用的js
 	function pagesetup_null(){
 		try{
 		var RegWsh = new ActiveXObject("WScript.Shell")
 		hkey_key="header" 
 		RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,"")
 		hkey_key="footer"
 		RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,"")
 		}catch(e){
 		}
 		} 
 	
 	function preview(){
 		try{
 		//pagesetup_null();
 		/*var mes = "<div class=\"box\">"+
		"<div class=\"right\">"+
			"<input type=\"button\" value=\"导出Excel\" class=\"btnstyle\""+
				"style=\"margin-right:12px;margin-top:5px\""+
				"onclick=\"exportExcel();\" />"+
			"<input type=\"button\" style=\"margin-right:5px;margin-top:5px;\" value=\"打印\"  class=\"btnstyle\" onclick=\"preview();\"></div>"+
		"<span class=\"kuaiju\">快速搜索</span> <input type=\"text\" id=\"keyword\""+
			"value=\"${keyword}\" name=\"keyword\" class=\"jianju\" /> <img"+
			"src=\"${ctx}/images/search.jpg\" onclick=\"query();\""+
			"style=\"margin-left:5px\"> <a href=\"#\" class=\"jianju\""+
			"onclick=\"extQueryDlg();\">高级</a></div>";*/
	
 		//$("#mes").html("");
			document.getElementById("mes").style.display='none';
			document.getElementById("over").style.display='none';
 		newwin=window.open("", "newwin", "height="+window.screen.height+",width="+window.screen.width+",toolbar=no,scrollbars=auto,menubar=no");
 		newwin.document.body.innerHTML=$("#dataList").html();
 		
 		newwin.window.print();
 		newwin.window.close();
 		document.getElementById("mes").style.display='block';
		document.getElementById("over").style.display='block';
 		//$("#mes").html(mes);
 		pagesetup_default();
 		}catch(e){}
 		}
 	//打印用的js结束
	
</script>
</head>
<body>
	<s:form action="query.action" namespace="/audit" method="post"
		theme="simple" id="sysForm" name="sysForm">
		<%-- <input type="hidden" name="auditUserLoginName"
			value="${auditUserLoginName}" />
		<input type="hidden" name="auditOperationIp"
			value="${auditOperationIp}" />
		<input type="hidden" name="auditOperationTime"
			value="${auditOperationTime}" />
		<input type="hidden" name="auditInformation"
			value="${auditInformation}" />
		<input type="hidden" name="auditOperationProcedure"
			value="${auditOperationProcedure}" />--%>
				<s:hidden id="auditUserLoginName" name="auditUserLoginName" />
		<s:hidden id="auditOperationIp" name="auditOperationIp" />
		<s:hidden id="auditInformation" name="auditInformation" />
		<s:hidden id="auditOperationProcedure" name="auditOperationProcedure" />
		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 2px">
			<!-- 空行-->
			<tr>
				<td></td>
			</tr>
			<tr>
				<td id="mes">
					<div class="box">

						<div class="right">

							<input type="button" value="导出Excel" class="btnstyle"
								style="margin-right:12px;margin-top:5px"
								onclick="exportExcel();" />
							<input type="button" style="margin-right:5px;margin-top:5px;" value="打印"  class="btnstyle" onclick="preview();">	
						</div>

						<span class="kuaiju">快速搜索</span> <input type="text" id="keyword"
							value="${keyword}" name="keyword" class="jianju" /> <img
							src="${ctx}/images/search.jpg" onclick="query();"
							style="margin-left:5px"> <a href="#" class="jianju"
							onclick="extQueryDlg();">高级</a>
					</div></td>
			<tr>
				<td>
				<div id="dataList">
					<table style="margin-top: 4px" width="100%" border="0"
						cellspacing="1" cellpadding="0" class="tab2" id="myTable">
						
						<thead>
						<tr height="28" class="biaoti">
							<td width="6%" class="biaoti"><input type="checkbox"
								id="chkAll" name="chkAll" class="check-box not_checked" />
							</td>
							<th width="10%" align="center" class="biaoti">编号</th>
							<th width="15%" align="center" class="biaoti">登录名</th>
							<th width="15%" align="center" class="biaoti">源IP</th>
							<th width="15%" align="center" class="biaoti">时间</th>
							<th width="25%" align="center" class="biaoti">操作日志</th>
							<th width="14%" align="center" class="biaoti">操作内容</th>
						</tr>
						</thead>
						<tbody>
						<c:set value="1" var="data" />

						<s:iterator value="#request.auditList" status="stat">
							<input type="hidden" name="auditId" id="auditId"
								value="${auditId}" />
							<tr>
								<td class="biaocm" valign="middle"><input type="checkbox"
									name="ids" id="${auditId}" value="${auditId}" class="check-box" />
								</td>
								<td valign="middle" class="td_list_data">${auditId}</td>
								<td valign="middle" class="td_list_data">${auditUserLoginName}</td>
								<td valign="middle" class="td_list_data">${auditOperationIp}</td>
								<td valign="middle" class="td_list_data"><s:date
										name="auditOperationTime" format="yyyy-MM-dd HH:mm:ss" /></td>
								<td valign="middle" align="left" class="hand italic"
									onmouseover="overDlg(this,'${auditUserLoginName}的操作日志','${auditInformation}','');"
									onmouseout="destory();"
									>操作日志</td>


								<td valign="middle" class="td_list_data">${auditOperationProcedure}</td>




							</tr>
							<c:set value="${data+1}" var="data" />
						</s:iterator>
						</tbody>
						
						<tr>
							<td colspan="7" width="100%"><div id="over"><jsp:include
									page="../commons/page.jsp"></jsp:include></div></td>
						</tr>
					</table>
					</div>
				</td>
			</tr>
		</table>
	</s:form>
	<!-- ext query from -->
	<s:form action="query.action" namespace="/audit" method="post"
		theme="simple" id="queryForm" name="queryForm">
	
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
				<td width="25%">登录名:</td>
				<td><input id="userLoginName" name="userLoginName" type="text"
					style="width:250px;" onkeypress="if(event.keyCode==13)extQuery();" />
				</td>
			</tr>
			<tr>
				<td>登录IP:</td>
				<td><input id="operationIp" name="operationIp" type="text"
					style="width:250px;" onkeypress="if(event.keyCode==13)extQuery();" />
				</td>
			</tr>
			<!-- 
			<tr>
				<td>登录时间:</td>
				<td><input id="operationTime" name="operationTime" type="text"
					style="width:250px;" onkeypress="if(event.keyCode==13)extQuery();" />
				</td>
			</tr>
			 -->
			<tr>
				<td>操作日志:</td>
				<td><input id="information" name="information" type="text"
					style="width:250px;" onkeypress="if(event.keyCode==13)extQuery();" />
				</td>
			</tr>
			<tr>
				<td>操作内容:</td>
				<td><input id="operationProcedure" name="operationProcedure"
					type="text" style="width:250px;"
					onkeypress="if(event.keyCode==13)extQuery();" /> </br>
				<span id="check_operationIp_msg"></span></td>
			</tr>
		</table>
	</div>

	<!--  <s:form action="insert.action" namespace="/audit" method="post">
		<s:submit>测试</s:submit>
	</s:form>-->
	<input type="hidden" id="actionStr" value="${actionStr }">

</body>
</html>
