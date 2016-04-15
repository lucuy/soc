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
<link href="${ctx}/styles/asset/assetinfo.css" rel="stylesheet"
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
<script type='text/javascript' src="${ctx}/scripts/filterpage.js"></script>
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
			height : 180,
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
		
		$('#dialog-rules').dialog({
			autoOpen:false,
			width:450,
			height:460,
			buttons : {
				"确定[Enter]":function(){
					checkRulesToDB() ; 
					$(this).dialog("close");
				},
				"取消[Esc]" : function(){
					$(this).dialog("close");
				}
			}
		});
		
		
		
		//$('#field').val('ASSET_UPDATE_DATE_TIME');
         $('#listTable').tablesorter();
	});


	var rege =/^([^`~!@#$%^&*()=|{}':;, \\\[\]<>\/?~！@#￥……&*（）—|{}【】‘；：”“'。\"，、？])*$/;
	//快速检索
	function query() {
		//关键字
		$('#keyword').val($.trim($('#keyword').val()));
		var keyword = $("#keyword").val();
		if(keyword.length>50){
			alert("搜索长度不能大于50");
			$('#keyword').val('');
			$('#keyword').focus();
			return ;
		}
		if(!rege.test(keyword)){
			alert("输入的内容包含特殊字符，请重新输入");
			$('#keyword').val('');
			$('#keyword').focus();
			return;
		}
		location.href = "${ctx}/asset/issuedAsset.action?keyword="
				+ encodeURI(encodeURI($('#keyword').val(), "utf-8"));
	}

	//验证ip地址是否合法
	function checkAssetIp() {

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
		$('#selAssetImportance').val($('#assetImportance').val());
		$('#selAssetStatus').val($('#assetStatus').val());
		if($('#assetName').val().length>50){
			alert("搜索长度不能大于50");
			$('#assetName').val('');
			$('#assetName').focus();
			return ;
		}
		if(!rege.test($('#assetName').val())){
			alert("输入的内容包含特殊字符，请重新输入");
			$('#assetName').val('');
			$('#assetName').focus();
			return;
		}
		var ip = $("#assetIps").val();
		if (ip != "") {
			var re1 = /^([0-9]|[1-9]\d|1\d{1,2}|2\d|2[0-3]\d|24[0-7])\.(\d|[1-9]\d|1\d{1,2}|2\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d{1,2}|2\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d{1,2}|2\d|2[0-4]\d|25[0-5])$/; // 匹配IP地址的正则表达式
			var result = re1.test(ip);
			if (result) {
				if (!(RegExp.$1 < 256 && RegExp.$2 < 256 && RegExp.$3 < 256 && RegExp.$4 < 256)) {
					$("#chect_assetIps").html("<font color='red'>请输入正确的ip地址...</font>");
					return false;
				}$(this).dialog("close");
			} else {
				$("#chect_assetIps").html("<font color='red'>请输入正确的ip地址...</font>");
				return false;
			}
		}else{
		$(this).dialog("close");
		}

		$('#queryForm').submit();
	}

	function deleteUser(assetSegMent, collectorId, assetGroupId) {
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
			location.href = "${ctx}/asset/deleteAsset.action?ids=" + ids
					+ "&assetSegMent=" + assetSegMent + "&collectorId="
					+ collectorId + "&assetGroupId=" + assetGroupId;
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
			if (confirm("你是否要导出全部/选中的资产...")) {
			location.href = "${ctx}/asset/export.action?ids=" + ids;
			}
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
				//$("#upgradeFile").val(upgradeFile);

				///alert($("#upgradeFile").val());

				// $("#userForm").attr("action","${ctx}/asset/importAsset.action");

				$("#importForm").submit();
				//$("#empForm").attr("action","${ctx}/vulnerability/importVulnerability.action?");

				//$("#empForm").submit();
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
	
	function assetIssued(obj){
		var id=obj ;
		//alert(obj);
		$.post("${ctx}/asset/issued.action",{"assetId":obj},function(data){
			alert("资产下发成功！");
			//alert($("td[id="+obj+"][name='isseud_state']").attr("name"));
		    $("td[id="+obj+"][name='isseud_state']").html("已下发<a href='javascript:assetIssued("+obj+");'>(重新下发)</a>");
			//parent.mainFrame.location.reload();
		});
	}
	
	//批量下发功能
	function PiLiang(){
		 if( $(":checkbox[checked = true]").size() < 1 ){
			 alert("至少选择一个资产...") ;
			 return ;
		 }
		$(":checkbox[checked = true]").each(function(){
			var id =  $(this).attr("id");
			$.post("${ctx}/asset/issued.action",{"assetId":id},function(data){
				//alert($("td[id="+obj+"][name='isseud_state']").attr("name"));
			    $("td[id="+id+"][name='isseud_state']").html("已下发<a href='javascript:assetIssued("+id+");'>(重新下发)</a>");
				//parent.mainFrame.location.reload();
			});
		});
		alert("资产下发成功！");
	}
	
	//打开规则选择框
	function openRuleDialog(){
		$("#dialog-rules").dialog("open");
	}
	
	var assetId_pass  ; 
	function relRule(obj,assetId){
		$("#dialog-rules input[type='checkbox'][name='ids-rules']:checked").removeAttr("checked");
	    $.each(obj.split("-"),function(num,str){
	    	$("#dialog-rules input[type='checkbox'][value='"+str+"']").attr("checked","checked");
	    });
	    
		assetId_pass = assetId ; 
		openRuleDialog() ;
	}
	
	function checkCheckBox(obj){
		$("#rules-"+obj+"[type='checkbox']").attr("checked",!$("#rules-"+obj+"[type='checkbox']").attr('checked'));
	}
	
	function checkRulesToDB(){
		var str = "" ; 
		$("#dialog-rules input[type='checkbox'][name='ids-rules']:checked").each(function(){
			  var id = $(this).val();
			  if(str==""){
				  str = str + id ; 
			  }else{
				  str = str+"-"+id ; 
			  }
		});
		$.post("${ctx}/asset/updateAnalysisRules.action",{'assetId':assetId_pass,'info':str},function(data){
			    parent.mainFrame.location.href="${ctx}/asset/issuedAsset.action?sortType=ASSET_ISSUED";
		});
	}
	
	$(function(){
		goPage(1,10,'denyPolicy','eventTypeBarcon');
	});
	
	
	
</script>

</head>

<body style="margin-top:2px;">
	<s:form action="issuedAsset.action" namespace="/asset" method="post"
		theme="simple" id="userForm" name="userForm">
		<s:hidden id="assetGroupId" name="assetGroupId" />
		<s:hidden id="assetGroupName" name="asset" />
		<s:hidden name="upgradeFile" id="upgradeFile" />
		<s:hidden name="field" id="field"></s:hidden>
		<s:hidden name="collectorId" id="collectorId"></s:hidden>
		<s:hidden name="assetSegMent" id="assetSegMent"></s:hidden>
		
		<s:hidden name="sortType" value="ASSET_ISSUED"></s:hidden>
		
		<input type="hidden" name="selAssetName" value="${selAssetName}"/>
		<input type="hidden" name="selAssetIps" value="${selAssetIps}">
		<input type="hidden" name="SelAssetImportance" value="${SelAssetImportance}">
		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 6px; margin-top: 0px">
			<!-- 空行 -->
			<tr>
				<td></td>
			</tr>
			<tr>
				<td>
					<div class="box">

						<div class="right">
						    <input type="button" value="批量下发" class="btnstyle"
								style="margin-right:15px;margin-top:-2px"
								onclick="PiLiang();" />  
							<!-- <input type="button" value="添加" class="btnstyle"
								style="margin-right:5px;margin-top:-2px"
								onclick="location.href='${ctx}/asset/editAsset.action';" /> 
							<input type="button" value="导入" class="btnstyle"
								style="margin-right:5px;margin-top:-2px"
								onclick="loadAssetDlg()" /> <input type="button" value="导出"
								class="btnstyle" style="margin-right:5px"
								onclick="exportExcel();">
							 -->
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
										<th width="17%" class="biaoti">资产</th>
										<th width="17%" class="biaoti">ip地址</th>
										<th width="17%" class="biaoti">设备类型</th>
										<th width="17%" class="biaoti">厂商设备</th>
										<th width="15%"   class="biaoti">解析规则</th>
										<th width="15%" class="biaoti">下发状态</th>
									</tr>
									</thead>
									<tbody>
									<s:iterator value="assetList" status="stat">
										<input type="hidden" name="assetId_checkedBox" id="assetId"
											value="${assetId}" />
										<tr>
											<td class="biaocm" valign="middle"><input
												type="checkbox" name="ids" id="${assetId}"
												value="${assetId}" class="check-box" />
											</td>

											<td valign="middle">${assetName}</td>

											<td valign="middle">${assetIps}</td>

											<td valign="middle" align="center">${assetDeviceType}</td>

											<td valign="middle" align="center">${assetSupportDevice }</td>
											
                                            <td valign="middle" align="center"><a href="javascript:relRule('${assetDeviceRules }','${assetId }')">规则</a></td>

											<td valign="middle" align="center" name="isseud_state" id="${assetId }"><c:if
													test="${assetIssued==1}">已下发<a href="javascript:assetIssued(${assetId });">(重新下发)</a></c:if> <c:if
													test="${assetIssued==0}"><a href="javascript:assetIssued(${assetId });">未下发</a></c:if>
											</td>

										</tr>
									</s:iterator>
									</tbody>
									<tr>
										<td colspan="7" width="100%"><jsp:include
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
	<s:form action="issuedAsset.action" namespace="/asset" method="post"
		theme="simple" id="queryForm" name="queryForm">
		<s:hidden name="selAssetName" id="selAssetName" />
		<s:hidden name="selAssetIps" id="selAssetIps" />
	</s:form>

	<!-- 导入form -->

	<!-- 选择文件对话框 -->
	<div id="dialog-loadAsset" title="导入资产对话框">
		<s:form action="importAsset" namespace="/asset" method="post"
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
				<td width="25%">资产名:</td>
				<td><input id="assetName" name="assetName" type="text"
					style="width:250px;" onkeypress="if(event.keyCode==13)extQuery();" />
				</td>
			</tr>
			<tr>
				<td>资产IP:</td>
				<td><input id="assetIps" name="assetIps" type="text"
					style="width:250px;" onkeypress="if(event.keyCode==13)extQuery();" />
					<br/><span id="chect_assetIps"></span>
				</td>
			</tr>
		</table>
	</div>


     
<div id="dialog-rules" title="规则选择">
    <table id="dlg-table-assets" width='96%' cellspacing='0'
			cellpadding='0' border='0' align='center'>
			<tr>
				<td>
					<table width="100%" border="0" cellspacing="1" cellpadding="0"
						class="tab2" id="denyPolicy">
					<tr></tr>	
						<s:iterator value="rulesList" status="stat">
					<tr><td valign="middle" class="biaocm" align="center" width = "6%">
					<input type="checkbox" name="ids-rules" class="check-box-assetGroup" 
					        id="rules-${analysisId }" value="${analysisName }" 
					        onclick="event.cancelBubble=true;"></td>
					<td width = "400px" valign="middle" class="td_t"><a style="color: #555555" href="javascript:void(0);" onclick="checkCheckBox(${analysisId});">${analysisName }</a>
					</td>
					</tr>
					</s:iterator>
					</table>
				</td>
			</tr>
		</table>
		<table width="80%" >
	<tr>
		<td colspan="2" style ="text-align:right;">
			<div id="eventTypeBarcon" name="eventTypeBarcon"></div>
	</td>
</tr>
</table>
</div>





</body>
</html>
