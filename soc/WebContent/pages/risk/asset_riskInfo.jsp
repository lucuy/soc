<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<%@page import="com.soc.model.user.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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

		$('#dialog-loadAsset').dialog({
			autoOpen : false,
			width : 380,
			height : 200,
			buttons : {
				"确定[Enter]" : function() {
					importExcel();
					$(this).dialog("close");
				},
				"取消[Esc]" : function() {
					$(this).dialog("close");
				}
			}
		});
		$('#listTable').tablesorter();
		
		var errorMessageflg='${errorMessageflg}';
		if(errorMessageflg=='1'){
			alert("对不起，操作的信息已经被删除！请重新操作！");
		}
		
  }); 


	var rege =/^([^`~!@#$%^&*()=|{}':;, \\\[\].<>\/?~！@#￥……&*（）—|{}【】‘；：”“'。\"，、？])*$/;
	//快速检索
	function query() {
		if($('#keyword').val()!=""){
			if(parseInt($('#keyword').val().length)>50){
				alert("长度不能大于50");
				$('#keyword').val('');
				$('#keyword').focus();
				return;
			}
		}
		
		if(!rege.test($('#keyword').val())){
			alert("输入的内容包含特殊字符，请重新输入");
			$('#keyword').val('');
			$('#keyword').focus();
			return;
		}
		//关键字
		$('#keyword').val($.trim($('#keyword').val()));
		location.href = "${ctx}/assetRiskValue/queryRisk.action?keyword="
				+ encodeURI(encodeURI($('#keyword').val(), "utf-8"));
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

	function overDlg(e, header, content, num) {
		$('#blk_header').html(header);
		$('#blk_content').html(content);

		createOverDiv(e, 10, 10, $('#blk').html());
	}

	function extQueryDlg() {
		$('#dialog-extQuery').dialog('open');
		$('#riskNameValue').val("");
		$('#riskResultValue').val("");
	}

	function extQuery() {
		if($('#riskNameValue').val()!=""&&$('#riskNameValue').val()!=null){
			if(parseInt($('#riskNameValue').val().length)>50){
				alert("长度不能大于50");
				return;
			}
		}
		if(!rege.test($('#riskNameValue').val())){
			alert("输入的内容包含特殊字符，请重新输入");
			return;
		}
		$('#riskName').val($('#riskNameValue').val());
		if($('#riskGroupValue').val()!=""&&$('#riskGroupValue').val()!=null){
			if(parseInt($('#riskGroupValue').val().length)>50){
				alert("长度不能大于50");
				return;
			}else{
				
			}
		}
		$('#riskGroup').val($('#riskGroupValue').val());
		var s=document.getElementById("riskAssetIdvalue").value;
		$('#riskAssetId').val(s);
		var reg=/^([+-]?)\d*\.?\d+$/;
		if($('#riskResultValue').val()!=""){
			if(!reg.test($('#riskResultValue').val())){
				alert("威胁值只能是数字");
				return;
			}else{
				if(parseInt($('#riskResultValue').val())<0 || parseInt($('#riskResultValue').val())>30 ){
					alert("威胁值只能是0~30的 数字");
					return;
				}else{
					$('#riskResult').val($('#riskResultValue').val());
				}
				
			}
		}
		
		
		$('#userForm').submit();
	}

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
			alert("请至少选择一个资产信息...");
			return;
		}

		if (confirm("确认执行操作吗？")) {
			location.href = "${ctx}/assetRiskValue/deleteRisk.action?ids=" + ids;
		}
	}

	//更新资产状态
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
			alert("请至少选择一个资产信息...");
			return;
		}

		if ($("#status").val() == 100) {
			alert("请选择需要执行的操作...");
			return;
		}

		if (confirm("确认执行操作吗？")) {
			location.href = "${ctx}/asset/updateAssetStatus.action?ids=" + ids
					+ "&status=" + $("#status").val();
		}
	}
	function gotoInfo(assetId) {
		location.href = "${ctx}/asset/queryInfo.action?assetId=" + assetId;
	}

	function exportExcel() {
		var ids = "";
		$("[name=ids]:checkbox:checked").each(function() {
			if (ids != "") {
				ids += "," + $(this).val();

			} else {

				ids = $(this).val();

			}

		});
		if ($("#chkAll").attr("checked") == true) {
			if (confirm("你是要导出选中/全部的资产...")) {
				location.href = "${ctx}/asset/export.action?ids=" + ids;
			} else {
				location.href = "${ctx}/asset/export.action";
			}
		}
		if ($("#chkAll").attr("checked") == false) {
			location.href = "${ctx}/asset/export.action?ids=" + ids;
		}
	}

	function loadAssetDlg() {
		clearFile();
		$('#dialog-loadAsset').dialog('open');

	}

	function importExcel() {

		var upgradeFile = $("#upTar").val();
		if (upgradeFile == "") {
			alert("文件未选择！");
			return;
		} else {
			var start = upgradeFile.lastIndexOf(".");
			if (upgradeFile.substr(start) != ".xls") {
				alert("文件不是xls格式！");
			}

			else {
				$("#importForm").submit();
			}
		}

	}

	function clearFile() {
		var oldFile = document.getElementById("upTar");
		var newFile = document.createElement("input");
		newFile.id = oldFile.id;
		newFile.type = "file";
		newFile.name = "upTar";
		oldFile.parentNode.replaceChild(newFile, oldFile);
	}
	
	function exportAssetDlg(){
			var ids = "";
		$("[name=ids]:checkbox:checked").each(function() {
			if (ids != "") {
				ids += "," + $(this).val();
			}else{
				ids = $(this).val();
				}

		});
		
		if(ids!=""){
			if (confirm("你是要导出选中的威胁信息...")) {
				
				location.href = "${ctx}/assetRiskValue/export.action?ids="
					+ ids;
			} 
		}else{
			if (confirm("你是要导出全部的威胁信息...")) {
				location.href = "${ctx}/assetRiskValue/export.action?ids="
					+ ids
					+ "&keyword="
					+ encodeURI(encodeURI($("#keyword").val()))+"&riskName="+encodeURI(encodeURI($.trim($('#riskName').val()),"utf-8"))+"&riskResult="+$.trim($('#riskResult').val())+"&riskAssetId="+$.trim($('#riskAssetId').val());
			}
		}
			
		
		 $("#dialog-extQuery").dialog("close");
	}	
	
	function toAddAssetRiskValue(){
		location.href = "${ctx}/assetRiskValue/queryRiskId.action";
	}
	window.onload = function(){
		var info = "${info}" ; 
		if(info != ""){
			if(info!="alert"){
				alert(info+"请仔细核对要导入的数据!");
			}else{
			alert("导入数据的格式不正确，请仔细核对数据!");
		}
		}
	}
</script>

</head>

<body style="margin-top:2px;">
	<s:form action="queryRisk.action" namespace="/assetRiskValue" method="post"
		theme="simple" id="userForm" name="userForm">
		<s:hidden id="riskAssetId" name="riskAssetId" />
		<s:hidden id="riskName" name="riskName" />
		<s:hidden id="riskResult" name="riskResult"  />
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
						<input type="button" value="新增" class="btnstyle"
								style="margin-right:5px;margin-top:-2px"
								onclick="toAddAssetRiskValue()" />	
							<input
								type="button" value="删除"
								style="margin-right:5px;margin-top:-2px" class="btnstyle"
								onclick="deleteUser();" />
							<input type="button" value="导入" class="btnstyle"
								style="margin-right:5px;margin-top:-2px"
								onclick="loadAssetDlg()" />
							<input type="button" value="导出" class="btnstyle"
								style="margin-right:5px;margin-top:-2px"
								onclick="exportAssetDlg()" />	
						</div>

						<span class="kuaiju">快速搜索</span> <input type="text" id="keyword" style="height: 15px;"
							value="${keyword}" name="keyword" class="jianju" /> <img
							src="${ctx}/images/search.jpg" onclick="query();"
							style="margin-left:5px"> <a href="#" class="jianju"
							onclick="extQueryDlg();">高级</a>
						<a href="{ctx}/assetRiskValue/exportMould.action" class="jianju" style="color:#F00">模板下载</a>
					</div>
				</td>

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
											id="chkAll" name="chkAll" class="check-box not_checked" />
										</td>
										<th width="20%" class="biaoti">资产名称</th>
										<th width="30%" class="biaoti">威胁名称</th>
										<th width="10%" class="biaoti">威胁可能性</th>
										<th width="10%" class="biaoti">威胁影响</th>
										<th width="10%" class="biaoti">威胁结果值</th>
									</tr>
									</thead>
									<tbody>
									<s:iterator value="riskValueList" status="stat">
										<input type="hidden" name="riskId" id="riskId"
											value="${riskId}" />
										<tr>
											<td class="biaocm" valign="middle"><input
												type="checkbox" name="ids" id="${riskId}"
												value="${riskId}" class="check-box" />
											</td>

											<td valign="middle">${assetName}</td>
											<td valign="middle"><a href="${ctx}/assetRiskValue/queryRiskId.action?id=${riskId}">${riskName}</a>
											</td> 
											<td valign="middle" align="center">${riskPossibility}</td>

											<td valign="middle" align="center">${riskInfluence}</td>
											<%-- ${riskThreatValue} --%>
											<td valign="middle" align="center">${riskResult}</td>
										</tr>
									</s:iterator>
									</tbody>
									<tr>
										<td colspan="6" width="100%"><jsp:include
												page="../commons/page.jsp"></jsp:include></td>
									</tr>
								</table>
							</div>
							<!-- information area -->
						</div>
					</div></td>
			</tr>
		</table>
	</s:form>

	<!-- ext query from -->
	
	<!-- 导入form -->

	<!-- 选择文件对话框 -->
	<div id="dialog-loadAsset" title="导入威胁信息对话框">
		<s:form action="importRisk" namespace="/assetRiskValue" method="post"
			theme="simple" id="importForm" name="importForm"
			enctype="multipart/form-data">
			<table width="90%" border="0" cellspacing="5" cellpadding="5"
				align="center">
				<tr>
					<td class="td_detail_separator"></td>
				</tr>
				<tr>
					<td valign="30%" width="80px">请选择文件:</td>
					<td valign="middle"><input type="file" id="upTar" name="upTar"
						style="width:170px;" />&nbsp;</td>
				</tr>
				<tr>
					<td></td>
					<td><span id="span_ip"></span></td>
				</tr>
			</table>
		</s:form>
	</div>


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
				<td width="25%">资产名称:</td>
				<td>
					<select id="riskAssetIdvalue" name="riskAssetIdvalue" style="width:250px;" onkeypress="if(event.keyCode==13)extQuery();">
					<option value="">--请选择--</option>
					<s:iterator value="assetList" status="stat"> 
					<option value="${assetId}">${assetName}</option>
					</s:iterator>
					</select>
				</td>
			</tr>
			<tr>
				<td width="25%">威胁名称:</td>
				<td><input id="riskNameValue" name="riskNameValue" type="text"
					style="width:250px;" onkeypress="if(event.keyCode==13)extQuery();" />
				</td>
			</tr>
			<tr>
				<td>威胁值:</td>
				<td><input id="riskResultValue" name="riskResultValue" type="text" onkeyup="this.value=this.value.replace(/[^\d]/g,'') " onafterpaste="this.value=this.value.replace(/[^\d]/g,'') " 
					style="width:250px;" onkeypress="if(event.keyCode==13)extQuery();" />
				</td>
			</tr>
		</table>
	</div>


</body>
</html>