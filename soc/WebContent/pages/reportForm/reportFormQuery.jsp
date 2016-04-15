<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<%@ include file="mainoffer.inc"%>


<%

String path = request.getContextPath();

	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html lang="true">
<head>
<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
<title>组态报表模板查询</title>
<link href="${ctx}/styles/user/user.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/style.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/right.css" rel="stylesheet" type="text/css">
 <link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet"
	type="text/css"> 
	<%-- <link href="${ctx}/styles/jquery-ui-1.8.21.css" rel="stylesheet" type="text/css"> --%>
<link href="${ctx}/styles/jquery.popuplayer.css" rel="stylesheet"
	type="text/css">
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="${ctx }/scripts/jquery.tablesorter.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
<script type='text/javascript' src="${ctx}/scripts/popuplayer.js"></script>
<script language="javascript" type="text/javascript"
	src="${ctx}/scripts/My97DatePicker/WdatePicker.js"></script>
<style type="text/css">
option {
	text-align: center;
}

.tdContent {
	text-align: center;
}

button {
	text-align: center;
	vertical-align: middle;
}

.displayStyle {
	overflow: hidden;
	text-overflow: ellipsis;
	width: 90%;
	white-space: nowrap;
	text-align: center;
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		$("#keyword").keydown(function(event){ 
			if(event.keyCode==13){  
				query();
			}  
		}); 

		// Dialog			
		$('#dialog-extQuery').dialog({
			autoOpen : false,
			width : 490,
			height : 170,
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
	var rege =/^([^`~!@#$%^&*()=|{}':;, \\\[\].<>\/?~！@#￥……&*（）—|{}【】‘；：”“'。\"，、？])*$/;
	function extQueryDlg() {
		$('#selReportFormName').val("");$('#selReportFormType').val("");$('#selStartTime').val("");$('#selEndTime').val("");
		$('#dialog-extQuery').dialog('open');
	}

	function extQuery() {
		$('#reportFormName').val($('#selReportFormName').val());
		if($('#selReportFormName').val().length>50){
			alert("模板名称长度不能大于50");
			$('#selReportFormName').val('');
			$('#selReportFormName').focus();
			return ;
		}
		if(!rege.test($('#selReportFormName').val())){
			alert("输入的内容包含特殊字符，请重新输入");
			$('#selReportFormName').val('');
			$('#selReportFormName').focus();
			return;
		}
		$('#reportFormType').val($('#selReportFormType').val());
		$('#startTime').val($('#selStartTime').val());
		$('#endTime').val($('#selEndTime').val());
		if($('#selStartTime').val()>$('#selEndTime').val()){
			alert("请选择正确的时间段");
			return;
		}
		//location.href="reportFormQuery.do?method=pageSelect&reportFormName="+encodeURIComponent(encodeURIComponent($('#reportFormName').val()))+"&reportFormType="+$('#reportFormType').val()+"&startTime="+$('#startTime').val()+"&endTime="+$('#endTime').val();
		$('#reportForm').submit();
		
		$(this).dialog("close");
		
	}

	//“执行”按钮
	function excute() {
		if ($("#status").val() != "100") {
			var ids = "";
			$("[name=ids]:checkbox:checked").each(function() {
				if (ids != "")
					ids += "," + $(this).val();
				else
					ids = $(this).val();

			});
			if (ids.length < 1) {
				alert("请至少选择一个报表信息...");
				return;
			}
			if (confirm("确认执行操作吗？")) {
				if ($("#status").val() == "-1") {
					location.href = "reportFormQuery.do?method=delete&ids="
							+ ids;
				} else if ($("#status").val() == "0") {
					editmodel();
				}
			}
		} else {
			alert("请选择要执行的操作...");
			return;
		}
	}

	function submitForm() {
		var queryForm = document.getElementById("queryForm");
		var startTime = document.getElementById("startTime");
		var endTime = document.getElementById("endTime");
		if (startTime.value != "" && endTime.value == "") {
			alert("结束时间不能为空");
			endTime.focus();
			return false;
		}
		if (startTime.value == "" && endTime.value != "") {
			alert("开始时间不能为空");
			startTime.focus();
			return false;
		}
		//queryForm.action.value="/reportFormQuery.do?method=pageSelect";
		queryForm.submit();
		return true;
	}
	function initPage() {
		var typeSel = document.getElementById("selReportFormType");
		var index = 0;
		var optionValue = "${formType}";
		for (index = 0; index < typeSel.options.length; index++) {
			if (typeSel.options[index].value == optionValue) {
				typeSel.options[index].selected = true;
				break;
			}
		}
	}
	function reset() {
		var reportFormName = document.getElementById("reportFormName");
		var reportFormType = document.getElementById("reportFormType");
		var startTime = document.getElementById("startTime");
		var endTime = document.getElementById("endTime");
		reportFormName.value = "${reportFormName}";
		startTime.value = "${startTime}";
		endTime.value = "${endTime}";
		for ( var index = 0; index < reportFormType.options.length; index++) {
			if (reportFormType.options[index].value == optionValue) {
				reportFormType.options[index].selected = true;
				break;
			}
		}
	}

	function checkInteger(Object) {
		var strInteger = Object.value;
		if (strInteger.length == 0) {
			return true;
		} else {
			var pattern = /^-?\d+$/;
			if (strInteger.match(pattern) == null) {
				alert("查找的页数只能输入数字");
				Object.focus();
				return false;
			} else {
				return true;
			}
		}
	}
	function chackpage(obj) {

		var page = obj.page.value;
		if (page == "") {
			alert("要查找的页面不能为空");
			obj.page.focus();
			return false;
		}
		if (!checkInteger(obj.page)) {
			return false;
		}
		var totalpages = 0;

		<logic:notEmpty name="page">
		totalpages = <bean:write name="page" property="totalPages"/>;
		</logic:notEmpty>

		if (page > totalpages) {
			alert("要查找的页面不能大于总页数！");
			return false;
		}
		if (page <= 0) {
			alert("要查找的页面不能小于零！");
			return false;
		}
	}

	function selectAll() {
		var chkAll = document.getElementById("chkAll");
		var checks = document.getElementsByName("ids");
		for ( var i = 0; i < checks.length; i++) {
			checks[i].checked = chkAll.checked;
		}
	}

	function deleteForms() {
		var queryForm = document.getElementById("queryForm");
		var selItems = document.getElementsByName("myCheck");
		var ids = "";
		for ( var i = 0; i < selItems.length; i++) {
			if (selItems[i].checked == true) {
				ids = ids + selItems[i].value + ",";
			}
		}
		if (ids == "") {
			alert("请选择要删除的模板");
			return false;
		} else {
			var confirmFlag = confirm("确定要删除所选的模板吗？");
			if (true == confirmFlag) {
				ids = ids.substring(0, ids.lastIndexOf(","));
				queryForm.action = "reportFormQuery.do?method=delete&ids="
						+ ids;
				//alert(queryForm.action);
				queryForm.submit();
				return true;
			}
			return false;
		}
	}
	function deleteAll() {
		var queryForm = document.getElementById("queryForm");
		if (confirm("确定要删除所有的记录吗？")) {
			queryForm.action = "reportFormQuery.do?method=deleteAll";
			queryForm.submit();
			return true;
		}
		return false;
	}
	function showDetail(id) {
		//var id = document.getElementById("detailId").value;
		window
				.open(
						"reportFormQuery.do?method=getDetail&id=" + id,
						"reportInfo",
						"height=350, width=600,toolbar=no, menubar=no, scrollbars=no, resizable=yes,location=no, status=no");
	}
	function exPortReportForm() {
		window.open("reportFormQuery.do?method=exportReportForm");
	}
	function inportReportForm() {
		window
				.open(
						"${ctx}/reportFormQuery.do?method=updateReport",
						"mydialog",
						"height=200, width=400, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no");
	}
	function addmodel() {
		var queryForm = document.getElementById("queryForm");
		queryForm.action = "newreportform.do?method=creatReportFormStep1";
		queryForm.submit();
	}
	function editmodel() {
		var scriptid;
		var f = document.getElementById("queryForm");
		var j = 0;
		for (i = 0; i < f.elements.length; i++) {
			if (f.elements[i].name == "ids") {
				if (f.elements[i].checked == true) {
					j++;
					scriptid = f.elements[i].value;
				}
			}
		}
		if (j == 0) {
			alert("请选择要修改的模板！");
			return false;
		}
		if (j > 1) {
			alert("只能针对单个模板进行修改！");
			return false;
		}
		document.queryForm.action = "editreportform.do?method=editReportFormStep1&reportFormId="
				+ scriptid;
		document.queryForm.submit();
	}
	
	//快速搜索
	function query() {
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
		$('#queryForm').submit();
	}
	
	function sub() {
		document.queryForm.submit();
	}
</script>
</head>
<body onload="initPage();">
	<form action="reportFormQuery.do?method=pageSelect" method="post"
		name="queryForm" id="queryForm" onsubmit="return submitForm();">
		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 6px; margin-top: 2px;">
			<tr>
				<td>
					<div class="box">
						<div class="right">
							<input type="button" class="btnstyle" style="padding-top: 3px; padding-left: 1px;" value="添加报表"
								onclick="addmodel();" /> 
							<%--<input type="button" value="导  入" class="btnstyle" onclick="inportReportForm();"> 
						
							--%><input type="button" value="导  出"  style="margin-right:5px;"class="btnstyle" onclick="exPortReportForm();">
							
							<span style="display: none">显示</span> 
							<input style="display: none;" type="button" class="btnstyle" value="csv导出" onclick="exportCSV();" />
						 
							<input style="display: none;" type="button" class="btnstyle" value="csv导入"
								onclick="window.open('${ctx}/employee/toUpload.action','up','height=300,width=400,toolbar=no,menubar=no,scrollbars=yes,resizable=no,location=no,status=yes').focus();" />
						</div>
						
						
						<span class="kuaiju">快速搜索</span> 
						<input type="text" id="keyword" value="${keyword}" name="keyword" style="height: 15px;" class="jianju" />
						
						<img src="${ctx}/images/search.jpg" class="jianju" onclick="query();" />
						<span>
							<a href="#" onclick="extQueryDlg();" class="jianju">高级</a>
						</span>
						 
						<span style="margin-left:24px">操作</span>
						<select id="status" class="jianju">
							<option value="100">--更多操作--</option>
							<option value="0">修改报表</option>
							<option value="-1">删除报表</option>
						</select>
						<input type="button"  style="margin-left:5px" class="btnstyle" value="执 行" onclick="excute()" /> 
						
					</div> <!-- toolbar area -->
					
					<div class="sbox">
						<div class="cont">
							<!-- information area -->
							<table width="100%" border="0" cellspacing="1" cellpadding="0"
								class="tab2" id="listTable">
								<thead>
								<tr height="28" class="biaoti">
									<td width="6%" align="center" class="biaoti"><input
										type="checkbox" id="chkAll" name="chkAll"
										class="check-box not_checked" onclick="selectAll()" /></td>
									<th width="20%" align="center" class="biaoti">模板名称</th>
									<th width="20%" align="center" class="biaoti">模板类别</th>
									<th width="20%" align="center" class="biaoti">创建时间</th>
									<th width="20%" align="center" class="biaoti">模板描述</th>
								</tr>
								</thead>
								<tbody>
								<c:set value="1" var="root" />
								<logic:present name="resultList">
									<logic:notEmpty name="resultList">
										<logic:iterate id="item" name="resultList">
											<tr>
												<td align="center" class="biaocm"><label> <input
														type="checkbox" name="ids"
														value="<bean:write name='item' property='id' />">
												</label></td>
												<td align="center"
													title="<bean:write name='item' property='reportFormName'/> ">
													<a href="#" onclick="javascript:showDetail('${item.id }');">
														<bean:write name="item" property="reportFormName" /> </a></td>
												<td align="center"
													title="<bean:write name='item' property='reportType'/>">
														<logic:equal value="1" name="item"
															property="reportFormType">静态模板</logic:equal>
														<logic:equal value="0" name="item"
															property="reportFormType">动态模板</logic:equal>
												</td>
												<td align="center"
													title="<bean:write name='item' property='displayDate'/>">
														<bean:write name="item" property="displayDate" />
												</td>
												<td align="center"
													title="<bean:write name='item' property='reportFormDescription'/>">
														<bean:write name="item"  property="reportFormDescription" />
												</td>
											</tr>
											<c:set value="${root+1}" var="root" />
										</logic:iterate>
									</logic:notEmpty>
								</logic:present>
								</tbody>
							</table>
						</div>
					</div></td>
			</tr>
			<!--<tr>
					<td colspan="5">
						<input type="button" value="删除全部" class="btnstyle"
							""  onclick="return deleteAll();">
						&nbsp;&nbsp;
						
					</td>
				</tr>
			-->
		</table>
	</form>

	<s:form action="reportFormQuery.do?method=pageSelect" method="post"
		name="reportForm" id="reportForm">
		<s:hidden name="reportFormName" id="reportFormName" />
		<s:hidden name="reportFormType" id="reportFormType" />
		<s:hidden name="startTime" id="startTime" />
		<s:hidden name="endTime" id="endTime" />
	</s:form>
	<div id="pageInfoDiv">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td align="center" valign="top">
					<table width="96%" border="0" cellpadding="0" cellspacing="0">
						<tr align='right'>
							<td valign='top'><logic:notEmpty name="page">
									<logic:greaterEqual name="page" property="totalPages" value="0">
										<form name="findpage"
											action="reportFormQuery.do?method=pageSelect&reportFormName=${reportFormName }&reportFormType=${formType }&startTime=${startTime }&endTime=${endTime }"
											onsubmit="return chackpage(this);" method="post">
											<%@include file="reportFormPage.inc"%>
											<bean:message key="pager.findpage" />
											<input type="hidden" value="findPage" name="action"
												id="action" /> <input name="page" style="height: 15px;" id="page" size="1"
												maxlength="5">
											<bean:message key="pager.page" />
											<input type="submit" class="btn1" value="GO" />
										</form>
									</logic:greaterEqual>
								</logic:notEmpty></td>
						</tr>
					</table></td>
			</tr>
		</table>
	</div>
	<!-- 高级查询的dialog -->
	<div id="dialog-extQuery" title="高级查询">
		<table width="96%" border="0" cellpadding="2" cellspacing="3">
			<tr>
				<td align="right" width="20%">模板名称：</td>
				<td align="left" width="25%"><input type="text"
					id="selReportFormName" name="selReportFormName" id="reportFormName"
					maxlength="16" style="width: 140px" value="${reportFormName}">
				</td>
				<td align="right" width="20%">模板类别：</td>
				<td align="left" width="20%"><select id="selReportFormType"
					name="selReportFormType" style="width: 144px; height: 21px"
					id="reportFormType">
						<option value="all">--- 所有模板 ---</option>
						<option value="1">--- 静态模板 ---</option>
						<option value="0">--- 动态模板 ---</option>
				</select></td>
				<!--<td align="left">
						<input type="button" value="查   询" class="btnstyle"
							"" onclick="sub()">
					</td>
				-->
			</tr>
			<tr>
				<td height="4px"></td>
			</tr>
			<tr>
				<td align="right">创建时间：</td>
				<td align="left"><input type="text" id="selStartTime"
					name="selStartTime" value="${startTime }" id="startTime"
					class="Wdate"
					onfocus="var endTime=$dp.$('endTime');WdatePicker({onpicked:function(){endTime.focus();},maxDate:'#F{$dp.$D(\'endTime\')}',startDate:'%y-%M-%d %H:%m:%s',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"
					readonly="readonly" style="width: 144px; height: 22px"></td>
				<td align="right">至：</td>
				<td align="left"><input type="text" id="selEndTime"
					name="selEndTime" value="${endTime }" id="endTime" class="Wdate"
					onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',startDate:'%y-%M-%d %H:%m:%s',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"
					readonly="readonly" style="width: 144px; height: 22px"></td>
				<!--<td align="left" width="20%">
						<input type="button" value="重   置" class="btnstyle"
							""  onclick="reset();">
					</td>
				-->
			</tr>
		</table>
	</div>
</body>
</html>
