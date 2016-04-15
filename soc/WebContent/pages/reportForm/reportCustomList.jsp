<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="mainoffer.inc"%>

<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>


<html>
	<head>
		
		<title>Insert title here</title>
		<link href="/soc/styles/user/user.css" rel="stylesheet" type="text/css">
		<link href="${ctx}/styles/style.css" rel="stylesheet" type="text/css">
		<link href="${ctx}/styles/right.css" rel="stylesheet" type="text/css">
		 <link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet"
			type="text/css"> 
			<%-- <link href="${ctx}/styles/jquery-ui-1.8.21.css" rel="stylesheet" type="text/css"> --%>
		<link href="${ctx}/styles/jquery.popuplayer.css" rel="stylesheet"
			type="text/css">
		<script type='text/javascript'
			src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
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
	</head>
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
			width : 500,
			height : 160,
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
		$('#dialog-extQuery').dialog('open');
	}

	function extQuery() {
		if($('#selName').val().length>50){
			alert("模板名称长度不能大于50");
			$('#selName').val('');
			$('#selName').focus();
			return ;
		}
		if(!rege.test($('#selName').val())){
			alert("输入的内容包含特殊字符，请重新输入");
			$('#selName').val('');
			$('#selName').focus();
			return;
		}
		if($('#selStartTime').val()>$('#selEndTime').val()){
			alert("请选择正确的时间段");
			return;
		}
		$('#name').val($('#selName').val());
		$('#customSql').val($('#selCustomSql').val());
		$('#startTime').val($('#selStartTime').val());
		$('#endTime').val($('#selEndTime').val());
		//location.href="reportFormQuery.do?method=pageSelect&reportFormName="+encodeURIComponent(encodeURIComponent($('#reportFormName').val()))+"&reportFormType="+$('#reportFormType').val()+"&startTime="+$('#startTime').val()+"&endTime="+$('#endTime').val();
		$('#reportForm').submit();
		$(this).dialog("close");
	}
	
	//快速搜索
	function query() {
		/*alert($('#keyword').val());
		location.href="reportCustom.do?method=initPage&keyword="+encodeURIComponent(encodeURIComponent($('#keyword').val()));*/
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
		$('#queryForm').submit();
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
					location.href = "reportCustom.do?method=delete&ids=" + ids;
				} else if ($("#status").val() == "0") {
					editmodel();
				}
			}
		} else {
			alert("请选择要执行的操作...");
			return;
		}
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
		document.queryForm.action = "reportCustom.do?method=toupdate&rc.id="
				+ scriptid;
		document.queryForm.submit();
	}
	function inportReportForm() {
	
		window
				.open(
						"${ctx}/reportCustom.do?method=toinport",
						"mydialog",
						"height=200, width=400, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no");
	}
	function exPortReportForm() {
		window.open("reportCustom.do?method=exportReportCustomForm");
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
				queryForm.action = "reportCustom.do?method=delete&ids=" + ids;
				queryForm.submit();
				return true;
			}
			return false;
		}
	}
	function deleteAll() {
		var queryForm = document.getElementById("queryForm");
		if (confirm("确定要删除所有的记录吗？")) {
			queryForm.action = "reportCustom.do?method=deleteAll";
			queryForm.submit();
			return true;
		}
		return false;
	}
	function checkInteger(Object) {
		var strInteger = Object.value;
		if (strInteger.length == 0) {
			return true;
		} else {
			var pattern = /^-?\d+$/;
			if (strInteger.match(pattern) == null) {
				alert("只能输入数字");
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

	$("#chkAll").live("click", function(event) {
		if ($("#chkAll").hasClass('not_checked')) {
			$("#chkAll").removeClass('not_checked');
			$(".check-box").attr('checked', true);
		} else {
			$("#chkAll").addClass('not_checked');
			$(".check-box").attr('checked', false);
		}
	});
	function sub() {
		document.queryForm.submit();
	}
</script>
	<body>
		<form action="reportCustom.do?method=initPage" method="post"
			name="queryForm" id="queryForm">
			<table width="99%" border="0" cellspacing="0" cellpadding="0"
				style="margin-left: 6px; margin-top: 2px">
				<tr>
					<td>
						<div class="box">
						<div class="right">
							<input type="button" class="btnstyle" style="padding-top: 3px; padding-left: 1px;" value="添加报表"
								onclick="location.href='reportCustom.do?method=toadd'" /> 
							<%--<input type="button" value="导  入" class="btnstyle" onclick="inportReportForm();"> 
						
							--%><input type="button" style="margin-right:5px;" value="导  出" class="btnstyle" onclick="exPortReportForm();">
							
							<%-- <span style="display: none">显示</span> 
							<input style="display: none;" type="button" class="btnstyle" value="csv导出" onclick="exportCSV();" />
						 
							<input style="display: none;" type="button" class="btnstyle" value="csv导入"
								onclick="window.open('${ctx}/employee/toUpload.action','up','height=300,width=400,toolbar=no,menubar=no,scrollbars=yes,resizable=no,location=no,status=yes').focus();" /> --%>
						</div>
						
						
						<span class="kuaiju">快速搜索</span> 
						<input type="text" id="keyword" value="${keyword}" style="height: 15px;" name="keyword" class="jianju" />
						
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
						
					</div>
					
					</td>
				</tr>
				<tr>
					<td>
						<div class="sbox">
							<div class="cont">
								<!-- information area -->
								<table width="100%" border="0" cellspacing="1" cellpadding="0"
									class="tab2" id="listTable">
									<thead>
									<tr height="28" class="biaoti">
										<td width="6%" class="biaoti">
											<input type="checkbox" id="chkAll" name="chkAll"
												class="check-box not_checked" />
										</td>
										<th style="font-size: 12px;" width="20%" class="biaoti">
											<b>自定义报表名称</b>
										</th>
										<th style="font-size: 12px;" width="20%" class="biaoti">
											<b>描述</b>
										</th>
										<th style="font-size: 12px;" width="20%" class="biaoti">
											<b>创建时间</b>
										</th>
										<th style="font-size: 12px;" width="20%" class="biaoti">
											<b>更新时间</b>
										</th>
										<th style="font-size: 12px" class="biaoti">
											<b>操作</b>
										</th>
									</tr>
									</thead>
									<tbody>
									<c:set value="1" var="root" />
									<logic:notEmpty name="ReportCustomForm" property="list">
										<logic:iterate id="item" name="ReportCustomForm"
											property="list">
											<tr>
												<td align="center" class="biaocm">
													<input type="checkbox" class="check-box" name="ids"
														id="${item.id }" value="${item.id }">
												</td>
												<td style="font-size: 12px;" align="center">
													${item.name }
												</td>
												<td style="font-size: 12px;" align="center">
													<c:out value="${item.memo }"></c:out>
												</td>
												<td style="font-size: 12px;" align="center">
													<bean:write name="item" property="createDate"
														format="yyyy-MM-dd HH:mm:ss" />
												</td>
												<td style="font-size: 12px;" align="center">
													<bean:write name="item" property="updateDate"
														format="yyyy-MM-dd HH:mm:ss" />
												</td>
												<td style="font-size: 12px;" align="center">
													<a
														href="${ctx}/reportCustom.do?method=excute&rc.id=${item.id}"
														style="color: blue; text-decoration: none;"><img src="${ctx}/images/start.png" class="jianju" alt="执行语句"/></a>
												</td>
											</tr>
											<c:set value="${root+1}" var="root" />
										</logic:iterate>
									</logic:notEmpty>
									</tbody>
		
								</table>
							</div>
						</div>
					</td>
				</tr>
			</table>
		</form>
		
		<table width="96%" border="0" align="center" cellpadding="0"
			cellspacing="0">
				<tr height="28">
				<td style="font-size: 12px;" align="right">
					<logic:notEmpty name="page">
						<logic:greaterEqual name="page" property="totalPages" value="0">
							<form name="findpage" action="reportCustom.do?method=initPage"
								onsubmit="return chackpage(this);" method="post">
								<%@ include file="reportCustomListpage.inc"%>
								<bean:message key="pager.findpage" />
								<input name="page" id="page" size="1" maxlength="5" style="height: 15px;">
								<bean:message key="pager.page" />
								<input type="hidden" value="findPage" name="action" id="action" />
								<input type="submit" class="btn1" value="GO"/>
							</form>
						</logic:greaterEqual>
					</logic:notEmpty>
				</td>
			</tr>
		</table>

		<!-- 高级查询的dialog -->
		<div id="dialog-extQuery" title="高级查询">
			<table width="96%" border="0" cellpadding="2" cellspacing="0">
				<tr>
					<td align="right" width="20%">
						模板名称：
					</td>
					<td align="left" width="25%">
						<input type="text" name="selName" id="selName" maxlength="16"
							style="width: 142px" value="${reportFormName}">
					</td>
					<td align="right" width="20%">
						自定义sql：
					</td>
					<td align="left" width="20%">
						<input type="text" name="selCustomSql" id="selCustomSql" maxlength="16"
							style="width: 142px">
					</td>
				</tr>
				<tr>
					<td height="4px">
					</td>
				</tr>
				<tr>
					<td align="right">创建时间：</td>
					<td align="left">
					<input type="text" id="selStartTime" name="selStartTime" value="${startTime }" id="startTime"
					class="Wdate"
					onfocus="var endTime=$dp.$('endTime');WdatePicker({onpicked:function(){endTime.focus();},maxDate:'#F{$dp.$D(\'endTime\')}',startDate:'%y-%M-%d %H:%m:%s',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"
					readonly="readonly" style="width: 144px; height: 22px">
					</td>
					<td align="right">至：</td>
					<td align="left"><input type="text" id="selEndTime" name="selEndTime" value="${endTime }" id="endTime" class="Wdate"
					onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',startDate:'%y-%M-%d %H:%m:%s',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"
					readonly="readonly" style="width: 144px; height: 22px">
				</td>
				</tr>
			</table>
		</div>
		<s:form action="reportCustom.do?method=initPage" method="post"
			name="reportForm" id="reportForm">
			<s:hidden id="name" name="name"/>
			<s:hidden id="customSql" name="customSql"/>
			<s:hidden id="startTime" name="startTime"/>
			<s:hidden id="endTime" name="endTime"/>
		</s:form>	
	</body>
</html>
