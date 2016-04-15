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

<link href="${ctx}/styles/jquery.popuplayer.css" rel="stylesheet"
	type="text/css">

<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
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

	});

	//快速检索
	function query() {
		//关键字
		$('#keyword').val($.trim($('#keyword').val()));
		location.href = "${ctx}/asset/queryAsset.action?keyword="
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
	}

	function extQuery() {
		$('#selAssetName').val($('#assetName').val());
		$('#selAssetIps').val($('#assetIps').val());
		var ip = $("#assetIps").val();
		if (ip != "") {
			var re1 = /^([0-9]|[1-9]\d|1\d{1,2}|2\d|2[0-3]\d|24[0-7])\.(\d|[1-9]\d|1\d{1,2}|2\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d{1,2}|2\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d{1,2}|2\d|2[0-4]\d|25[0-5])$/; // 匹配IP地址的正则表达式
			var result = re1.test(ip);
			if (result) {
				if (!(RegExp.$1 < 256 && RegExp.$2 < 256 && RegExp.$3 < 256 && RegExp.$4 < 256)) {
					alert("ip地址不合法");
					return false;
				}
			} else {
				alert("ip地址不合法");
				return false;
			}
		}

		$('#queryForm').submit();
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
</script>

</head>

<body style="margin-top:2px;">
	<s:form action="queryByName.action" namespace="/assetRiskGroup" method="post"
		theme="simple" id="userForm" name="userForm">
		<s:hidden id="riskId" name="riskId" />
		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 0px">
			<!-- 空行 -->
			<tr>
				<td></td>
			</tr>
			<tr>
				<td>
					<div class="box">

						<!-- <div class="right">
							<input
								type="button" value="删除"
								style="margin-right:5px;margin-top:-2px" class="btnstyle"
								onclick="deleteUser();" />
							<input type="button" value="导入" class="btnstyle"
								style="margin-right:5px;margin-top:-2px"
								onclick="loadAssetDlg()" />
						</div> -->

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
									class="tab2">
									<tr height="28" class="biaoti">
										<!-- <td width="6%" class="biaoti"><input type="checkbox"
											id="chkAll" name="chkAll" class="check-box not_checked" />
										</td> -->
										<td width="30%" class="biaoti">资产名称</td>
										<td width="20%" class="biaoti">资产值</td>
										<td width="10%" class="biaoti">可用性价值</td>
										<td width="10%" class="biaoti">完整性价值</td>
										<td width="10%" class="biaoti">保密性价值</td>
									</tr>
									<s:iterator value="assetEvaluationList" status="stat">
										<input type="hidden" name="riskId" id="riskId"
											value="${riskId}" />
										<tr>
											<%-- <td class="biaocm" valign="middle"><input
												type="checkbox" name="ids" id="${riskId}"
												value="${riskId}" class="check-box" />
											</td> --%>

											<td valign="middle">${assetName}</td>
											<td valign="middle">${assetValue}</td>

											<td valign="middle" align="center">${usabilityValue}</td>

											<td valign="middle" align="center">${integrityValue}</td>


											<td valign="middle" align="center">${secretValue}</td>
										</tr>
									</s:iterator>
									<tr>
										<td colspan="5" width="100%"><jsp:include
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
	<s:form action="queryAsset.action" namespace="/asset" method="post"
		theme="simple" id="queryForm" name="queryForm">
		<s:hidden name="selAssetName" id="selAssetName" />
		<s:hidden name="selAssetIps" id="selAssetIps" />
		<s:hidden name="SelAssetImportance" id="SelAssetImportance" />
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
				<td width="25%">威胁名称:</td>
				<td><input id="assetName" name="assetName" type="text"
					style="width:250px;" onkeypress="if(event.keyCode==13)extQuery();" />
				</td>
			</tr>
			<tr>
				<td>资产组:</td>
				<td><input id="assetIps" name="assetIps" type="text"
					style="width:250px;" onkeypress="if(event.keyCode==13)extQuery();" />
				</td>
			</tr>
		</table>
	</div>

</body>
</html>