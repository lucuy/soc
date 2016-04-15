<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>

<html>
<head>
<link href="${ctx}/styles/asset/assetinfo.css" rel="stylesheet"
	type="text/css">

 <link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet"
	type="text/css">
	<%-- <link href="${ctx}/styles/jquery-ui-1.8.21.css" rel="stylesheet" type="text/css"> --%>

<link href="${ctx}/styles/jquery.popuplayer.css" rel="stylesheet"
	type="text/css">

<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx }/scripts/jquery-latest.js"></script>
<script type="text/javascript" src="${ctx }/scripts/jquery.tablesorter.js"></script>
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
		
		$('#sortList').tablesorter() ; 

	});
	//验证字符串是否包含特殊字符的正则
	var rege =/^([^`~!@#$%^&*()=|{}':;, \\\[\].<>\/?~！@#￥……&*（）—|{}【】‘；：”“'。\"，、？])*$/;
	//快速检索
	function query() {
		//关键字
		$('#keyword').val($.trim($('#keyword').val()));
		//验证字符串长度
		if($('#keyword').val().length>50){
			alert("搜索长度不能大于50");
			$('#keyword').val('');
			$('#keyword').focus();
			return ;
		}
		//验证是否包含特殊字符
		if(!rege.test($('#keyword').val())){
			alert("输入的内容包含特殊字符，请重新输入");
			$('#keyword').val('');
			$('#keyword').focus();
			return;
		}
		location.href = "${ctx}/events/queryALLAsset.action?keyword="
				+ encodeURI(encodeURI($('#keyword').val(), "utf-8"))+"&assetGroupId="+$('#assetGroupId').val();
	}

	//验证ip地址是否合法
	/* function checkAssetIp() {

	} */
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
		//验证字符串长度
		if($('#assetName').val().length>50){
			alert("搜索长度不能大于50");
			$('#assetName').val('');
			$('#assetName').focus();
			return ;
		}
		//验证是否包含特殊字符
		if(!rege.test($('#assetName').val())){
			alert("输入的内容包含特殊字符，请重新输入");
			$('#assetName').val('');
			$('#assetName').focus();
			return;
		}
		$('#selAssetIps').val($('#assetIps').val());
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

	

	/* function loadAssetDlg() {
		clearFile();
		$('#dialog-loadAsset').dialog('open');

	} */
	
	function showEventList(assetId)
	{
	  location.href ="${ctx}/events/queryByAssetEvents.action?assetId="+assetId+"&timeRange=12"; 
	}
	function destory()
	{
	   destroyOverDlg();
	}
		$(document).click(function(event) {
		//shiftForCheckBoxFun(event);
		destroyOverDlg();
	});

</script>

</head>

<body style="margin-top:2px;">
	<s:form action="queryALLAsset.action" namespace="/events" method="post"
		theme="simple" id="userForm" name="userForm">
		<!-- <s:hidden id="assetGroupId" name="assetGroupId" value="<s:property value='assetGroupId'/>"/> -->
		<s:hidden id="assetGroupName" name="asset" />
		<s:hidden name="upgradeFile" id="upgradeFile" />
		<input type="hidden" id="assetGroupId" name="assetGroupId" value="${assetGroupId }" />
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

						<%-- <div class="right">
							<input type="button" value="添加" class="btnstyle"
								style="margin-right:5px;margin-top:-2px"
								onclick="location.href='${ctx}/asset/editAsset.action';" /> <input
								type="button" value="删除"
								style="margin-right:5px;margin-top:-2px" class="btnstyle"
								onclick="deleteUser('${assetSegMent}', ' ${collectorId}', '${assetGroupId} ');" />
							<input type="button" value="导入" class="btnstyle"
								style="margin-right:5px;margin-top:-2px"
								onclick="loadAssetDlg()" /> <input type="button" value="导出"
								class="btnstyle" style="margin-right:5px"
								onclick="exportExcel();">
						</div> --%>

						<span class="kuaiju">快速搜索</span> <input type="text" id="keyword"
							value="${keyword}" name="keyword" class="jianju" style="height: 15px;"/> <img
							src="${ctx}/images/search.jpg" onclick="query();"
							style="margin-left:5px"> <a href="#" class="jianju"
							onclick="extQueryDlg();">高级</a> <!-- <span style="margin-left:24px">操作</span> -->

						<!-- <select id="status" class="jianju">

							<option value="100">--更多操作--</option>

							<option value="0">停用资产</option>

							<option value="1">启用资产</option>

						</select> <input type="button" value="执行" style="margin-left:5px"
							class="btnstyle" onclick="updateStatus()" /> -->
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
									class="tab2" id="sortList">
									<thead>
									<tr height="28" class="biaoti">
										<!-- <td width="6%" class="biaoti"><input type="checkbox"
											id="chkAll" name="chkAll" class="check-box not_checked" />
										</td> -->
										<th width="23%" class="biaoti">资产</th>
										<th width="17%" class="biaoti">ip地址</th>
										<th width="17%" class="biaoti">所属组</th>
										<th width="17%" class="biaoti">重要性</th>
										<th width="26%" class="biaoti">查看对应的事件</th>
									</tr>
									</thead>
									<tbody>
									<s:iterator value="assetList" status="stat">
										<%-- <input type="hidden" name="assetId" id="assetId"
											value="${assetId}" /> --%>
										<tr>
											<%-- <td class="biaocm" valign="middle"><input
												type="checkbox" name="ids" id="${assetId}"
												value="${assetId}" class="check-box" />
											</td> --%>

											<td valign="middle">${assetName}
											</td>

											<td valign="middle">${assetIps}</td>

											<c:set var="assetgroup" value="" />
											<s:iterator value='assetGrouplistt'>
												<c:set var="assetgroup" value="${assetgroup}[${assetGroupName }]<br/>" />
											</s:iterator>		
											<td valign="middle" class="td_list_data italic hand" align="center"
												onmouseover="overDlg(this,'关联资产组列表','${assetgroup}','');" onmouseout="destory();">
												资产组列表
												</td>

											<td valign="middle" align="center"><c:if
													test="${assetImportance==-1}">一般</c:if> <c:if
													test="${assetImportance==0}">重要</c:if> <c:if
													test="${assetImportance==1}">比较重要</c:if> <c:if
													test="${assetImportance==2}">非常重要</c:if></td>


											<td valign="middle" align="center">
											<img alt="查看对应事件"
												title="查看对应事件" class="hand" src="../images/asset_info.png"
												onclick="showEventList(${assetId});">
											</td>

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
	<s:form action="queryALLAsset.action" namespace="/event" method="post"
		theme="simple" id="queryForm" name="queryForm">
		<s:hidden name="selAssetName" id="selAssetName" />
		<s:hidden name="selAssetIps" id="selAssetIps" />
		<s:hidden name="SelAssetImportance" id="SelAssetImportance" />
		<input type="hidden" name="assetGroupId" id="assetGroupIdProQuery" value="${assetGroupId }" />
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
</body>
</html>
