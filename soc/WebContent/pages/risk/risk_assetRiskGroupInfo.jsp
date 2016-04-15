<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%

String path = request.getContextPath();

	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
 
<%-- <script type="text/javascript" src="${ctx}/pushlet/ajax-pushlet-client.js"></script>--%>
 <link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet"
	type="text/css"> 
<link href="${ctx}/styles/asset/assetinfo.css" rel="stylesheet"
	type="text/css">
<link href="${ctx}/styles/validationEngine.jquery.css" rel="stylesheet"
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
	<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine-en.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine.js"></script>
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
		$('#dialog-chart').dialog({
			autoOpen : false,
			width : 400,
			height : 330,
			buttons : {
				"柱状图" : function() {
					location.href = "${ctx}/assetRiskGroup/queryAllAssets.action?falg=1";
					$(this).dialog("close");
				},
				"圆饼图" : function() {
					location.href = "${ctx}/assetRiskGroup/queryAllAssets.action?falg=2";
					
					$(this).dialog("close");
				},
				"取消[Esc]" : function() {
					$(this).dialog("close");
				}
			}
		});

     	$('#listTable').tablesorter();
	});
	var rege =/^([^`~!@#$%^&*()=|{}':;, \\\[\].<>\/?~！@#￥……&*（）—|{}【】‘；：”“'。\"，、？])*$/;
	//快速检索
	function query() {
		//关键字
		$('#keyword').val($.trim($('#keyword').val()));
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
		location.href = "${ctx}/assetRiskGroup/query.action?keyword="
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
	$("#chkAll_chart").live("click", function(event) {
		if ($("#chkAll_chart").hasClass('not_checked')) {
			$("#chkAll_chart").removeClass('not_checked');
			$(".check-box1").attr('checked', true);
		} else {
			$("#chkAll_chart").addClass('not_checked');
			$(".check-box1").attr('checked', false);
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

	function extQuery() {
	$('#selassetValue').val($('#assetValue').val());
		$('#selriskThreatValue').val($('#riskThreatValue').val());
		$('#selvAVulnerabilityValue').val($('#vAVulnerabilityValue').val());
		
	//	var ip = $("#assetIps").val();
	//	if (ip != "") {
		//	var re1 = /^([0-9]|[1-9]\d|1\d{1,2}|2\d|2[0-3]\d|24[0-7])\.(\d|[1-9]\d|1\d{1,2}|2\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d{1,2}|2\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d{1,2}|2\d|2[0-4]\d|25[0-5])$/; // 匹配IP地址的正则表达式
			//var result = re1.test(ip);
			//if (result) {
				//if (!(RegExp.$1 < 256 && RegExp.$2 < 256 && RegExp.$3 < 256 && RegExp.$4 < 256)) {
					//alert("ip地址不合法");
					//return false;
			//	}
			//} else {
				//alert("ip地址不合法");
				//return false;
			//}
		//}
			if(checkNum1()&&checkNum2()&&checkNum3()){
				$(this).dialog("close");
				$('#userForm').submit();
			}
		
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
		
	
			if(ids!=""){
				if (confirm("你是要导出选中的资产组信息...")) {
					location.href = "${ctx}/assetRiskGroup/export.action?ids=" + ids;
				} 
			}else{
				if (confirm("你是要导出全部的资产组信息...")) {
					location.href = "${ctx}/assetRiskGroup/export.action?ids=" + ids +"&keyword="
						+ encodeURI(encodeURI($('#keyword').val(), "utf-8"))+"&selvAVulnerabilityValue="+$('#selvAVulnerabilityValue').val()
						+"&selriskThreatValue="+$('#selriskThreatValue').val()+"&selassetValue="+$('#selassetValue').val();
				}
			}
			
		
		
	}
	

	function loadAssetDlg() {
		clearFile();
		$('#dialog-loadAsset').dialog('open');

	}
	function extchartDlg(){
		location.href = "${ctx}/pages/risk/risk_value_chart.jsp";
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
	var req=/^\d+(\.\d+)?$/;
	function checkNum1(){
		var assetValue=document.getElementById("assetValue").value;
		if(assetValue==""||assetValue==null){
			return true;
		}else{
		if(req.test(assetValue)){
			document.getElementById("checkNum1").innerHTML="";
			return true;
		}else{
			document.getElementById("checkNum1").innerHTML="请输入数字!";
			return false;
		}
		}
	}
	function checkNum2(){
		var riskThreatValue=document.getElementById("riskThreatValue").value;
		if(riskThreatValue==""||riskThreatValue==null){
			return true;
		}else{
		if(req.test(riskThreatValue)){
			document.getElementById("checkNum2").innerHTML="";
			return true;
		}else{
			document.getElementById("checkNum2").innerHTML="请输入数字!";
			return false;
		}
		}
	}
	function checkNum3(){
		var vAVulnerabilityValue = document.getElementById("vAVulnerabilityValue").value;
		if(vAVulnerabilityValue==""||vAVulnerabilityValue==null){
			return true;
		}else{
		if(req.test(vAVulnerabilityValue)){
			document.getElementById("checkNum3").innerHTML="";
			return true;
		}else{
			document.getElementById("checkNum3").innerHTML="请输入数字!";
			return false;
		}
		}
	}
	function loadAssetGroupDlg(){
		
	}
</script>

</head>

<body style="margin-top:2px;">
	
	<s:form action="query.action" namespace="/assetRiskGroup" method="post"
		theme="simple" id="userForm" name="userForm">
		<s:hidden name="selvAVulnerabilityValue" id="selvAVulnerabilityValue" />
		<s:hidden name="selriskThreatValue" id="selriskThreatValue" />
		<s:hidden name="selassetValue" id="selassetValue" />
		<s:hidden id="riskId" name="riskId" />
		<input type="hidden" value="${field}" id="field" name="field" />
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
							<!-- <input
								type="button" value="删除"
								style="margin-right:5px;margin-top:-2px" class="btnstyle"
								onclick="deleteUser();" />
							<input type="button" value="导入" class="btnstyle"
								style="margin-right:5px;margin-top:-2px"
								onclick="loadAssetDlg()" />-->
								<input type="button" value="图表展示"
								class="btnstyle" style="margin-right:5px"
								onclick="extchartDlg()">
												<input type="button" value="导出Excel"
								class="btnstyle" style="margin-right:5px"
								onclick="exportExcel();">
													</div> 

						<span class="kuaiju">快速搜索</span> <input type="text" id="keyword" style="height: 15px;"
							value="${keyword}" name="keyword" class="jianju" /> <img
							src="${ctx}/images/search.jpg" onclick="query();"
							style="margin-left:5px"> <a href="#" class="jianju"
							onclick="extQueryDlg();">高级</a>
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
										<th width="30%" class="biaoti">资产组名称</th>
										<th width="20%" class="biaoti">总风险平均值</th>
										<th width="10%" class="biaoti">资产风险平均值</th>
										<th width="10%" class="biaoti">威胁风险平均值</th>
										<th width="10%" class="biaoti">脆弱性风险平均值</th>
									</tr>
									</thead>
									<tbody>
									<s:iterator value="assetGroupList" status="stat">
										<input type="hidden" name="assetGroupId" id="riskId"
											value="${assetGroupId}" />
										<tr>
											 <td class="biaocm" valign="middle"><input
												type="checkbox" name="ids" id="${assetGroupId}"
												value="${assetGroupId}" class="check-box" />
											</td> 

											<td valign="middle"><a href="${ctx}/assetRiskGroup/queryByName.action?assetGroupId=${assetGroupId}">${assetGroupName}</a></td>
											<td valign="middle">
											<fmt:formatNumber value="${assetValue*riskThreatValue*vAVulnerabilityValue}" pattern="#,##0.0#"></fmt:formatNumber>
											</td>

											<td valign="middle" align="center">${assetValue}</td>

											<td valign="middle" align="center">${riskThreatValue}</td>


											<td valign="middle" align="center">${vAVulnerabilityValue}</td>
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
	<s:form action="query.action" namespace="/assetRiskGroup" method="post"
		theme="simple" id="queryForm" name="queryForm">
		
	</s:form>

	<!-- 导入form -->

	<!-- 选择文件对话框 -->
	<div id="dialog-loadAsset" title="导入风险管理对话框">
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
				<td width="40%">资产风险平均值:</td>
				<td class="padding"><input id="assetValue" name="assetValue" type="text" onblur="checkNum1();" onkeyup="this.value=this.value.replace(/[^\d]/g,'') " onafterpaste="this.value=this.value.replace(/[^\d]/g,'') " 
					style="width:200px;" onkeypress="if(event.keyCode==13)extQuery();" /><br> <font color="red"><span id="checkNum1"></span></font>
				</td>
			</tr>
			<tr>
				<td width="40%">威胁风险平均值:</td>
				<td class="padding"><input id="riskThreatValue" name="riskThreatValue" type="text" onblur="checkNum2();" onkeyup="this.value=this.value.replace(/[^\d]/g,'') " onafterpaste="this.value=this.value.replace(/[^\d]/g,'') " 
					style="width:200px;" onkeypress="if(event.keyCode==13)extQuery();" /><br> <font color="red"><span id="checkNum2"></span></font>
				</td>
			</tr>
			<tr>
				<td width="40%">脆弱性风险平均值:</td>
				<td class="padding"><input id="vAVulnerabilityValue" name="vAVulnerabilityValue" type="text" onblur="checkNum3();" onkeyup="this.value=this.value.replace(/[^\d]/g,'') " onafterpaste="this.value=this.value.replace(/[^\d]/g,'') " 
					style="width:200px;" onkeypress="if(event.keyCode==13)extQuery();" /><br> <font color="red"><span id="checkNum3"></span></font>
				</td>
			</tr>
		</table>
	</div>
<!-- 图表选择的dialog -->
	<div id="dialog-chart" title="条件选择">
		<table id="dlg-table-assets" width='96%' cellspacing='0'
			cellpadding='0' border='0' align='center'>
			<tr height="10"></tr>
			<tr>
				<td>
					<table width="100%" border="0" cellspacing="1" cellpadding="0"
						class="tab2" id="dlg-table-denyPolicy">
						<tr height="28">
							<td width="6%" align="center" class="biaoti"><input
								type="checkbox" id="chkAll_chart" name="chkAll_chart"
								class="check-box1 not_checked" /></td>
							<td width="100%" align="center" class="biaoti">选择条件</td>
						</tr>
						<tr>
						 <td class="biaocm" valign="middle"><input
												type="checkbox" name="chart" id=""
												value="1" class="check-box1" />
											</td> 
											<td>安全域</td>
						</tr>
						<tr>
						 <td class="biaocm" valign="middle"><input
												type="checkbox" name="chart" id=""
												value="2" class="check-box1" />
											</td> 
											<td>设备类型</td>
						</tr>
					<tr><td class="biaocm" valign="middle"><input
												type="checkbox" name="chart" id=""
												value="3" class="check-box1" />
											</td> 
											<td>资产值</td>
						</tr>
						<tr><td class="biaocm" valign="middle"><input
												type="checkbox" name="chart" id=""
												value="4" class="check-box1" />
											</td> 
											<td>威胁值</td>
						</tr>
						<tr><td class="biaocm" valign="middle"><input
												type="checkbox" name="chart" id=""
												value="5" class="check-box1" />
											</td> 
											<td>脆弱性值</td>
						</tr>
							<tr><td class="biaocm" valign="middle"><input
												type="checkbox" name="ids" id=""
												value="6" class="check-box1" />
											</td> 
											<td>风险值</td>
						</tr>
					</table></td>
			</tr>
		</table>
	</div>
</body>
</html>