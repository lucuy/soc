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

<link href="${ctx}/styles/asset/assetinfo.css" rel="stylesheet"
	type="text/css">

 <link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet"
	type="text/css"> 
	<%-- <link href="${ctx}/styles/jquery-ui-1.8.21.css" rel="stylesheet" type="text/css"> --%>

<link href="${ctx}/styles/jquery.popuplayer.css" rel="stylesheet"
	type="text/css">

<link href="${ctx}/styles/fontColor.css" rel="stylesheet"
    type="text/css"> 
	<link rel="stylesheet" type="text/css" href="themes/blue/style.css">
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx }/scripts/jquery-latest.js"></script>
<script type="text/javascript"
	src="${ctx }/scripts/jquery.tablesorter.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
<script type='text/javascript' src="${ctx}/scripts/popuplayer.js"></script>
<%-- <script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine.js"></script>--%>
<script language="javascript">
	//sousuo
	function query() {
		$('#keyword').val($.trim($('#keyword').val()));
		var str=$('#keyword').val().match(/^([^`~!@#$%^&*()=|{}':;, \\\[\].<>\/?~！@#￥……&*（）—|{}【】‘；：”“'。\"，、？])*$/); 
		if(str==null){
			alert("您的输入包含特殊字符！");
			return;
		}
		location.href = "${ctx}/settingArchive/archiveList.action?keyword="
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
					var t= /^\d{0,13}$/;
					if($("#associationName").val()!=null&&$("#associationName").val()!=""){
					if(t.test($("#associationName").val())){
						window.location="/soc/settingArchive/archiveList.action?statuss="+$("#associationType").val()+"&archiveDate="+$("#associationName").val();
					$(this).dialog("close");
					}else{
					$('#checkit').html("您的输入有误！");
					
					}
					}else{
					window.location="/soc/settingArchive/archiveList.action?statuss="+$("#associationType").val()+"&archiveDate="+$("#associationName").val();
					$(this).dialog("close");
					}
					
						
					},

				"取消[Esc]" : function() {
					$(this).dialog("close");
				}
			}
		});
		$("input.check-box").change(function() {
			
			$("option").remove();
			if ($(this).attr("checked")){
				var status = this.value;
				
				if(status==1){
					installValue();
				}else{
					installValue1();
				}
			}else{
				$("#status").append("<option value='100' style='margin-right:24px'>--更多操作--</option>");
			}
		});
		
		$("#listTable").tablesorter();
		
	});
	
	function installValue(){
		$("#status").append("<option value='100' style='margin-right:24px'>--更多操作--</option><option value='0'>&nbsp;&nbsp;归档</option>");
	}
	function installValue1(){
		$("#status").append("<option value='100' style='margin-right:24px'>--更多操作--</option><option value='2'>&nbsp;&nbsp;下载</option><option value='3'>&nbsp;&nbsp;恢复</option><option value='4'>&nbsp;&nbsp;清理归档</option>");
	}
	
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
		$('#analysisNamevalue').val($.trim($('#analysisNamevalue').val()));
		$('#analysisName').val($('#analysisNamevalue').val());
		$('#analysisTypevalue').val($.trim($('#associationType').val()));
		$('#associationType').val($('#analysisTypevalue').val());
		$('#queryForm').submit();
	}
	//更新状态
	function updateStatus() {
		var ids = "";
		$("[name=archiveId]:checkbox:checked").each(function() {
			if (ids != "") {
				ids += "," + $(this).attr("id");
			} else {
				ids = $(this).attr("id");
			}
		});
		if ($(":checkbox[checked = true]").size() < 1) {
			alert("请至少选择一个归档规则信息...");
			return;
		}

		if ($("#status").val() == 100) {
			alert("请选择需要执行的操作...");
			return;
		}
		if (confirm("确认执行操作吗？")) {
			location.href = "${ctx}/settingArchive/updateArchive.action?archiveId="
					+ ids + "&archiveStatus=" + $("#status").val();
		}
		
	}
	
</script>
</head>
<body>

	<s:form action="archiveList.action"
		namespace="/settingArchive" method="post" theme="simple"
		id="sysForm" name="sysForm">
		<input type="hidden" value="${filterName}" name="filterName" />
		<input type="hidden" value="${filterType}" name="filterType" />
		
		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 2px" >
			<!-- <s:hidden id="archiveId" name="archiveId" />
			<s:hidden id="archiveStatus" name="archiveStatus" /> -->
			<!-- 空行-->
			<tr>
				<td></td>
			</tr>
			<tr>
				<td>
					<div class="box">

						<div class="right">

							<!-- <input type="button" value="导出Excel" class="btnstyle" style="margin-right:12px;margin-top:5px"
				   onclick="exportExcel();"/>  -->
							<span style="display:none;"><a href="#">高级</a> </span> <span>操作</span>
							<select id="status" class="jianju">
								<option value="100" style="margin-right:24px">--更多操作--</option>
								<option value="0">&nbsp;&nbsp;归档</option>
									<option value="2">&nbsp;&nbsp;下载</option>

										<option value="3">&nbsp;&nbsp;恢复</option>

										<option value="4">&nbsp;&nbsp;清理归档</option>

							</select>
							<%-- <c:if test="${archiveStatus==1}">
									<select id="status0" class="jianju">
										<option value="100" style="margin-right:24px">--更多操作--</option>
										<option value="0">&nbsp;&nbsp;归档</option>

									</select>
								</c:if>
								<c:if test="${archiveStatus==0}">
									<select id="status1" style="display:none" class="jianju">

										<option value="100" style="margin-right:24px">--更多操作--</option>

										<option value="2">&nbsp;&nbsp;下载</option>

										<option value="3">&nbsp;&nbsp;恢复</option>

										<option value="4">&nbsp;&nbsp;清理归档</option>

									</select>
								</c:if> --%>
							<input type="button" value="执行" style="margin-left:5px"
								class="btnstyle" onclick="updateStatus()" /> <span
								style="margin-right:5px"></span>

						</div>

						<span class="kuaiju">快速搜索</span> <input type="text" id="keyword" maxlength="255"
							value="${keyword}" name="keyword" class="jianju" /> <img
							src="${ctx}/images/search.jpg" onclick="query();"
							style="margin-left:5px"> <a href="#" class="jianju"
							onclick="extQueryDlg();">高级</a>
					</div></td>
			<tr>
				<td>
					<table style="margin-top: 4px" width="100%" border="0"
						cellspacing="1" cellpadding="0" class="tab2" id="listTable">
						<thead>
						<tr height="28" class="biaoti">
							<td width="6%" class="biaoti"><input type="checkbox"
								id="chkAll" name="chkAll" class="check-box not_checked" />
							</td>
							<th width="9%" class="biaoti">数据时间</td>
							<th width="10%" class="biaoti">状态</td>
							<th width="18%" class="biaoti">归档目录</td>
							<th width="10%" class="biaoti">已经容量</td>
							<th width="12%" class="biaoti">总共容量</td>
							<th width="10%" class="biaoti">使用率</td>
							<th width="15%" class="biaoti">告警的临界值</td>
							<th width="10%" class="biaoti">告警方式</td>
						</tr>
						</thead>
						<c:set value="1" var="data" />
						<tbody>
						<s:iterator value="archiveList">

							<tr>
								<td class="biaocm" valign="middle"><input type="checkbox"
									name="archiveId" value='${archiveStatus}' id="${archiveId}"
									class="check-box" /></td>
								<td valign="middle" class="td_list_data">${archiveName}</td>
								<td valign="middle" class="td_list_data">${filePath} <c:if
										test="${archiveStatus==0}">已归档</c:if> <c:if
										test="${archiveStatus==1}">未归档</c:if> <c:if
										test="${archiveStatus==2}">已下载</c:if> <c:if
										test="${archiveStatus==3}">已恢复</c:if> <c:if
										test="${archiveStatus==4}">已清理归档</c:if></td>
										<td valign="middle" class="td_list_data">${archivePath}</td>
										<td valign="middle" class="td_list_data">${capacity}K</td>
										<td valign="middle" class="td_list_data">${totalCapacity}k</td>
										<td valign="middle" class="td_list_data">${percent}</td>
										<td valign="middle" class="td_list_data">${thresholds}%</td>
										<td valign="middle" class="td_list_data"><c:if
										test="${alarmMode==0}">邮件</c:if> <c:if
										test="${alarmMode==1}">短信</c:if> <c:if
										test="${alarmMode==2}">电话</c:if></td>
										
							</tr>
						</s:iterator>
						</tbody>
						<tr>
							<td colspan="9" width="100%"><jsp:include
									page="../commons/page.jsp"></jsp:include></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</s:form>
	<!-- ext query from -->
	<s:form action="archiveList.action"	
		namespace="/settingArchive" method="post" theme="simple" 
		id="queryForm" name="queryForm">
		<s:hidden id="filterName" name="filterName" />
		<s:hidden id="filterType" name="filterType" />
	
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
				<td width="25%">归档数据时间:</td>
				<td><input id="associationName" name="associationName"
					type="text" style="width:150px;"
					onkeypress="if(event.keyCode==13)extQuery();" />
					<font color="red"><span id="checkit"></span></font>
					</td>
			</tr>
			<tr>
				<td>归档状态:</td>
				<td><select id="associationType" name="associationType">

						<option value="" style="margin-right:24px">--请选择归档状态--</option>

						<option value="0">已归档</option>

						<option value="1">未归档</option>

						<option value="2">已下载</option>

						<option value="3">已恢复</option>

						<option value="4">清理归档</option>
				</select></td>
			</tr>
		</table>
	</div>
	</s:form>
</body>
</html>