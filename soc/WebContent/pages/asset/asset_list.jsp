<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@page import="com.soc.service.systemsetting.impl.SettingCollectorServiceImpl"%>
<%@page import="com.soc.service.systemsetting.SettingCollectorService"%>
<%@page import="com.soc.model.conf.GlobalConfig"%>
<%@page import="com.soc.model.systemsetting.Collector"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>

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

<script language="javascript">
	$(document).ready(function() {
		
		var info = "${actionMessage}";
		//alert(info);
		if(info=="0;")
		{
			alert("资源已满,添加失败")
		}else if(info=="1;")
		{
			alert("操作成功!");
		}
		
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
		
		//导入漏扫资产弹出框
		$('#dialog-loadScanAsset').dialog({
			autoOpen : false,
			width : 380,
			height : 200,
			buttons : {
				"确定[Enter]" : function() {
					importScanExcel();
					$(this).dialog("close");
				},
				"取消[Esc]" : function() {
					$(this).dialog("close");
				}
			}
		});
		
		//$('#field').val('ASSET_UPDATE_DATE_TIME');
         $('#listTable').tablesorter();
         var errorMessageflg='${errorMessageflg}';
 		if(errorMessageflg=='1'){
 			alert("对不起，操作的资产已经被删除！请重新操作！");
 		}
	});
	var rege =/^([^`~!@#$%^&*()=|{}':;, \\\[\]<>\/?~！@#￥……&*（）—|{}【】‘；：”“'。\"，、？])*$/;
	//快速检索
	function query() {
		//关键字
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
		
		location.href = "${ctx}/asset/queryAsset.action?keyword="
				+ encodeURI(encodeURI($('#keyword').val(), "utf-8"))+"&assetGroupId="+$("#assetGroupId").val();
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

	<%--function extQuery() {
		$('#selAssetName').val($('#assetName').val());
		$('#selAssetIps').val($('#assetIps').val());
		$('#selAssetGroupId').val() ;
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
		//alert($('#selAssetGroupId').val());
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
	location.href = "${ctx}/asset/queryAsset.action?selAssetName="
		+encodeURI(encodeURI($('#selAssetName').val(),"utf-8"))+"&selAssetIps="+$('#selAssetIps').val()+"&assetGroupId="+$("#assetGroupId").val();
		//$('#queryForm').submit();
	}--%>
	
	

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
			//location.href = "${ctx}/asset/updateAssetStatus.action?ids=" + ids
			//		+ "&status=" + $("#status").val();
			var stateVal = $("#status").val() ; 
			$.post("${ctx}/asset/updateAssetStatus.action",{"ids":ids,"status":stateVal},function(data){
				
				$.each(ids.split(","),function(num,dataVal){
					var str = "" ;
					if(parseInt(stateVal) == 1){
						str = "启用" ;
					}else{
						str = "停用" ;
					}
					
					$("#statue_"+dataVal).html(str) ;
				});
				//去掉修改状态后的选择状态
				var dateVals = ids.split(",");
				for ( var int = 0; int < dateVals.length; int++) {
					$("#"+dateVals[int]).attr("checked",false);
				}
			});
			
			
		}
	}
	
	function gotoInfo(assetMac,assetId,assetSupportDeviceId)
	{
	   window.open('${ctx}/monitorGroup/queryInfo.action?assetMac='+assetMac+'&assetId='+assetId+'&assetSupportDeviceId='+assetSupportDeviceId);
	}
	
	function exportExcel() {
		var groupid=$("#assetGroupId").val();
		var ids = "";
		$("[name=ids]:checkbox:checked").each(function() {
			if (ids != "") {
				ids += "," + $(this).val();

			} else {

				ids = $(this).val();

			}

		});
		
		
			if(ids!=""){
				if (confirm("你是要导出选中的资产信息...")) {
					exports(ids,groupid);
				} 
			}else{
				if (confirm("你是要导出全部的资产信息...")) {
					exports(ids,groupid);
				}
			}
		
	}
	function exports(ids,groupid){
		location.href = "${ctx}/asset/export.action?ids="
			+ ids+"&assetGroupId="+groupid
			+ "&keyword="
			+ encodeURIComponent(encodeURIComponent($("#keyword").val()))+"&selAssetName="
				+encodeURI(encodeURI($('#selAssetName').val(),"utf-8"))+"&selAssetIps="+$('#selAssetIps').val()+"&selAssetCollector="+$('#selAssetCollector').val();
	}
	function loadAssetDlg() {
		clearFile();
		if(confirm("请先下载模板，在模板内填写导入数据。（如已下载请继续）")){
		$('#dialog-loadAsset').dialog('open');
		}

	}
	
	//漏扫资产导入
	function loadAssetScanDlg() {
		clearScanFile();
		$('#dialog-loadScanAsset').dialog('open');

	}
	function clearScanFile() {
		var oldFile = document.getElementById("upTarScan");
		var newFile = document.createElement("input");
		newFile.id = oldFile.id;
		newFile.type = "file";
		newFile.name = "upTar";
		oldFile.parentNode.replaceChild(newFile, oldFile);
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

				///alert($("#").val());

				// $("#userForm").attr("action","${ctx}/asset/importAsset.action");

				$("#importForm").submit();
				//$("#empForm").attr("action","${ctx}/vulnerability/importVulnerability.action?");

				//$("#empForm").submit();
			}
		}

	}
	
	
	//导入漏扫资产
	function importScanExcel() {

		var upgradeFile = $("#upTarScan").val();
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

				$("#importScanForm").submit();
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
	
	//function paixu(field){
      //  $('#field').val(field);
	   // $('#userForm').submit();
    //} 
	function destory()
	{
	   destroyOverDlg();
	}
	function exportTEMP(){
		location.href = "${ctx}/asset/exportTEMP.action" ;
	}
	window.onload = function(){
		var info = "${info}" ; 
		if(info != ""){
			
		if(info=="alert"){
			alert("导入的xls格式不正确，请点击模版下载");
		}else if(info=="full")
		{
			alert("资源已满，只导入了部分资产");
		}
		else
		{
			alert(info+",请检查导入内容!");
		}
		}
	}
	//获取所有采集器
	$(document).ready(function(){

		$.ajax({
			type : "post",
			url : "${ctx}/asset/queryAllCollector.action",
			dataType : "text",
			data :null,
			success : function(result) {
				//alert(result);
				var jsonObj=eval("("+result+")"); 
				$.each(jsonObj, function (i, item) { 
				jQuery("#assetCollector").append("<option value="+ item.collectorId+">"+ item.collectorBasic+"</option>"); 
				}); 
			},
			error:function(){
				alert("json 接受失败");
			}
		});
	});
	//高级查询，新增按采集器名称查询
	function extQuery() {
		$('#selAssetName').val($('#assetName').val());
		$('#selAssetIps').val($('#assetIps').val());
		$('#selAssetCollector').val($('#assetCollector').val());
		$('#selAssetGroupId').val() ;
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
		var act=document.activeElement.id;
		if((act=="assetCollector")&&($('#assetCollector').val()=="")||($('#assetCollector').val()==null)){
			//alert("请选择采集器名称");
			$('#assetCollector').val('');
			$('#assetCollector').focus();
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
	location.href = "${ctx}/asset/queryAsset.action?selAssetName="
		+encodeURI(encodeURI($('#selAssetName').val(),"utf-8"))+"&selAssetIps="+$('#selAssetIps').val()+"&selAssetCollector="+$("#selAssetCollector").val()+"&assetGroupId="+$("#assetGroupId").val();
		//$('#queryForm').submit();
	}
	
</script>

</head>

<body style="margin-top:2px;">

	<s:form action="queryAsset.action" namespace="/asset" method="post"
		theme="simple" id="queryForm" name="userForm">
		<s:hidden id="assetGroupId" name="assetGroupId" />
		<s:hidden id="assetGroupName" name="asset" />
	<!-- 	<s:hidden name="upgradeFile" id="upgradeFile" /> -->
		<s:hidden name="field" id="field"></s:hidden>
		<s:hidden name="collectorId" id="collectorId"></s:hidden>
		<s:hidden name="assetSegMent" id="assetSegMent"></s:hidden>
		<input type="hidden" name="selAssetName" value="${selAssetName}"/>
		<input type="hidden" name="selAssetIps" value="${selAssetIps}">
		<input type="hidden" name="selAssetCollector" value="${selAssetCollector}">
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
							<input type="button" value="添加" class="btnstyle"
								style="margin-right:5px;margin-top:-2px"
								onclick="location.href='${ctx}/asset/editAsset.action';" /> <input
								type="button" value="删除"
								style="margin-right:5px;margin-top:-2px" class="btnstyle"
								onclick="deleteUser('${assetSegMent}', ' ${collectorId}', '${assetGroupId} ');" />
							<input type="button" value="导入" class="btnstyle"
								style="margin-right:5px;margin-top:-2px"
								onclick="loadAssetDlg()" />
								<input type="button" value="级联导入" class="btnstyle"
								style="margin-right:5px;margin-top:-2px"
								onclick="loadAssetScanDlg()" />	
								 <input type="button" value="导出"
								class="btnstyle" style="margin-right:5px"
								onclick="exportExcel();">
						</div>

						<span class="kuaiju">快速搜索</span> <input type="text" id="keyword"
							value="${keyword}" name="keyword" class="jianju" style="height: 15px;" /> <img
							src="${ctx}/images/search.jpg" onclick="query();"
							style="margin-left:5px"> <a href="#" class="jianju"
							onclick="extQueryDlg();">高级</a>
							
							 <span style="margin-left:24px">操作</span>

						<select id="status" class="jianju">

							<option value="100">--更多操作--</option>

							<option value="0">停用资产</option>

							<option value="1">启用资产</option>

						</select> <input type="button" value="执行" style="margin-left:5px"
							class="btnstyle" onclick="updateStatus()" />
							<font color="red"><a href="#" style="color: red" onclick="exportTEMP();">模板下载</a></font>
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
										<%--修改--%>
										<th width="15%" class="biaoti">资产(采集类型)</th>
										<th width="15%" class="biaoti">ip地址</th>
										<th width="15%" class="biaoti">所属组</th>
										<th width="15%" class="biaoti">重要性</th>
										<th width="20%" class="biaoti">状态</th>
										<th width="20%" class="biaoti">apm信息</th>
									</tr>
									</thead>
									<tbody>
									<s:iterator value="assetList" status="stat">
										<%-- <input type="hidden" name="assetId" id="assetId"
											value="${assetId}" /> --%>
										<tr>
											<td class="biaocm" valign="middle"><input
												type="checkbox" name="ids" id="${assetId}"
												value="${assetId}" class="check-box" />
											</td>
											<td valign="middle"><a
												href="${ctx}/asset/editAsset.action?assetId=${assetId}">${assetName}(${assetCollectType })</a>
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
											<%-- <td valign="middle" align="center" >${assetGroupName}</td> --%>

											<td valign="middle" align="center"><c:if
													test="${assetImportance==-1}">一般</c:if> <c:if
													test="${assetImportance==0}">重要</c:if> <c:if
													test="${assetImportance==1}">比较重要</c:if> <c:if
													test="${assetImportance==2}">非常重要</c:if></td>


											<td valign="middle" id="statue_${assetId }" align="center"><c:if
													test="${assetStatus==1}">启用</c:if> <c:if
													test="${assetStatus==0}">停用</c:if>
											</td>
											<td valign="middle" align="center">
										<c:if test="${isOnLine}">
											<img
												src="${ctx}/images/asset_info.png" class="hand"
												title="查看资产监控信息" onclick="gotoInfo('${assetMac}','${assetId}','${assetSupportDeviceId}');" />
											
											<%--<img src="${ctx}/images/asset_info.png" class="hand"
												title="查看资产监控信息" onclick="timeOutAlert(<%=request.getAttribute("assetTimeNote")%>);">
										--%>
										</c:if>
										<c:if test="${isOnLine==false}">
										<img
												src="${ctx}/images/notonline.png" class="hand"
												title="资产不在线"  />
											
										</c:if></td>
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
	<s:form action="queryAsset.action" namespace="/asset" method="post"
		theme="simple" id="queryForm" name="queryForm">
		<s:hidden name="selAssetName" id="selAssetName" />
		<s:hidden name="selAssetIps" id="selAssetIps" />
		<!-- 修改 -->
		<s:hidden name="selAssetCollector" id="selAssetCollector"/>
		
		<input type="hidden" id="selAssetGroupId" name="assetGroupId" />
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
   <div id="dialog-loadScanAsset" title="导入资产对话框">
		<s:form action="importScanAsset" namespace="/asset" method="post"
			theme="simple" id="importScanForm" name="importScanForm"
			enctype="multipart/form-data">
			<table width="90%" border="0" cellspacing="5" cellpadding="5"
				align="center">
				<tr>
					<td class="td_detail_separator"></td>
				</tr>
				<tr>
					<td valign="30%" width="80px">请选择文件:</td>
					<td valign="middle"><input type="file" id="upTarScan" name="upTarScan"
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
			<tr>
				<td>采集器:</td>
				<td><select  style="width:250px;" id="assetCollector" name="assetCollector" onkeypress="if(event.keyCode==13)extQuery();">
				<option value="">=采集器名称=</option>
				</select>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>
