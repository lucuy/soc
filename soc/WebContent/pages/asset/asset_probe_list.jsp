<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<%@page import="com.soc.model.user.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<html>
<head>
<link href="${ctx}/styles/asset/assetinfo.css" rel="stylesheet"
	type="text/css">

 <link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet"
	type="text/css"> 
	<%-- <link href="${ctx}/styles/jquery-ui-1.8.21.css" rel="stylesheet" type="text/css"> --%>
<%-- <script type="text/javascript" src="${ctx}/pushlet/ajax-pushlet-client.js"></script>--%>

<link href="${ctx}/styles/jquery.popuplayer.css" rel="stylesheet"
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
		
		$('#dialog-lookForResult').dialog({
			
			autoOpen : false ,
			width : 600 , 
	        height : 490 ,  
			button : {
				"确定[Enter]" : function() {

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
		location.href = "${ctx}/assetProbeTask/queryName.action?keyword="+encodeURI(encodeURI($('#keyword').val(), "utf-8"));
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
		var taskName = $("#taskName").val();
		if(taskName.length>50){
			alert("搜索长度不能大于50");
			$('#taskName').val('');
			$('#taskName').focus();
			return ;
		}
		if(!rege.test(taskName)){
			alert("输入的内容包含特殊字符，请重新输入");
			$('#taskName').val('');
			$('#taskName').focus();
			return;
		}
		$("#selTaskName").val($("#taskName").val()) ; 
	    $("#selTaskState").val($("#taskState").val()) ; 
		$('#queryForm').submit();
	}

	function deleteUser(ids) {
		var ids = "";
		$("[name=ids]:checkbox:checked").each(function() {
			if (ids != "") {
				ids += "," + $(this).val();
			} else {
				ids = $(this).val();
			}
		});
		if ($(":checkbox[checked = true]").size() < 1) {
			alert("请至少选择一个资产探测任务...");
			return;
		}

		if (confirm("确认执行操作吗？")) {
			location.href = "${ctx}/assetProbeTask/deleteTask.action?ids=" + ids;
		}
	}

	//执行探测流程
	function updateStatus() { 
		var ids = "";
		$("[name=ids]:checkbox:checked").each(function() {
			if (ids != "") {
				ids += "," + $(this).val();
			} else {
				ids = $(this).val();
			}
		});
		if ($(":checkbox[checked = true][name=ids]").size() < 1) {
			alert("请至少选择一个资产信息...");
			return;
		}
		if ($(":checkbox[checked = true][name=ids]").size() > 1) {
			alert("为确保获取当前最新资产信息，探测任务只能运行一个...");
			return;
		}
		//if ($("#status").val() == 100) {
		//	alert("请选择需要执行的操作...");
		//	return;
		//}

		if (confirm("确认执行操作吗？")) {
			location.href = "${ctx}/assetProbeTask/exportXml.action?ids=" + ids;
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
	
	function searchAsset(obj){
	     var ip = $("#deviceIp_"+obj).text() ; 
	     $.post("${ctx}/asset/queryAssetByIP.action",{"selAssetIps":ip},function(data){
	    	 if(data == "have"){
	    		 alert("资产已添加过，无需重复添加...") ; 
	    		 return ; 
	    	 }else{
	    		 //发送探测到的资产的IP到资产添加页面
	    		 parent.mainFrame.location.href="${ctx}/asset/editAsset.action?asset.assetIps="+ip+"&asset.assetStatus=1";
	    	 }
	     });
	}
	
    //打开结果dialog
    function openResultDialog(obj){
    	
    	if(obj != ""){
    		$.post("${ctx}/assetProbeTask/queryProbeAssetById.action",{"taskId":obj},function(data){
    			$("#denyPolicy").html("");
    			$("#denyPolicy").append("<tr style='font-weight: bold;'><td>资产IP</td><td>资产MAC</td><td>添加</td></tr>");
    			var i = 0 ; 
    			$.each(data,function(num,probe){    				
    				$("#denyPolicy").append("<tr> <td id='deviceIp_"+i+"'>"+probe.deviceIp+"</td> <td>"+probe.deviceMac+"</td> "+
    				"<td> <a href='javascript:searchAsset("+i+");' >添加资产</a>  </td>   </tr>");
    				i++ ; 
    			});
    			$('#dialog-lookForResult').dialog({title:"探测结果信息&nbsp;&nbsp;&nbsp;本次探测到"+i+"个资产"});
    		});
    	}
    	$('#dialog-lookForResult').dialog("open");
    }
</script>

</head>
<body style="margin-top:2px;">
	<s:form action="queryTask.action" namespace="/asset"  method="post"
		theme="simple" id="userForm" name="userForm">
		<s:hidden id="assetGroupId" name="assetGroupId" />
		<s:hidden id="assetGroupName" name="asset" />
		<s:hidden name="upgradeFile" id="upgradeFile" />
		<input type="hidden" name="selAssetName" value="${selAssetName}"/>
		<input type="hidden" name="selAssetIps" value="${selAssetIps}">
		<input type="hidden" name="SelAssetImportance" value="${SelAssetImportance}">
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
							<input type="button" value="添加" class="btnstyle"
								style="margin-right:5px;margin-top:-2px"
								onclick="location.href='${ctx}/assetProbeTask/editAsset.action';" /> <input
								type="button" value="删除"
								style="margin-right:5px;margin-top:-2px" class="btnstyle"
								onclick="deleteUser('${ids}');" />
						
						</div>

						<span class="kuaiju">快速搜索</span> <input type="text" id="keyword"
							value="${keyword}" name="keyword" class="jianju" style="height: 15px;" /> <img
							src="${ctx}/images/search.jpg" onclick="query();"
							style="margin-left:5px"><a href="#" class="jianju"
							onclick="extQueryDlg();">高级</a> 
							
							<!-- 操作
							
							<span style="margin-left:24px">
							
							</span>

							<select id="status" class="jianju">

							<option value="100">--更多操作--</option>

							<option value="0">停止探测</option>

							<option value="1">启动探测</option>

						</select> -->
						
						 <input type="button" value="执行探测" style="margin-left:5px"
							class="btnstyle" onclick="updateStatus()" />
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
										<th width="17%" class="biaoti">任务名称</th>
										<th width="15%" class="biaoti">采集器</th>
										<th width="15%" class="biaoti">起始IP</th>
										<th width="15%" class="biaoti">结束IP</th>
										<th width="15%" class="biaoti">描述</th>
										<th width="8%" class="biaoti">状态</th>
										<th width="15%" class="biaoti">查看结果</th>
										
									
									</tr>
									</thead>
									<tbody>
									<s:iterator value="#session.tasklist" status="stat" id="task">
										<input type="hidden" name="ids" id="ids"
											value="${task.taskId}" />
										<tr>
											<td class="biaocm" valign="middle"><input
												type="checkbox" name="ids" id="${task.taskId}"
												value="${task.taskId}" class="check-box" />
											</td>

											<td valign="middle">${task.taskName}
											</td>

											<td valign="middle">${task.collectorName}</td>

											<td valign="middle" align="center">${task.beginIp}</td>

											<td valign="middle" align="center">${task.endIp}</td>
										 	<!-- <td valign="middle" align="center">${task.medo}</td> -->
										 	<td valign="middle" align="center"><s:property value="#task.medo" escape="true"></s:property></td>
											<td valign="middle" align="center">
	
											<c:if test="${task.result==null}">未探测</c:if> 
											<c:if test="${task.result==1}">正在探测</c:if> 
											<c:if test="${task.result==0}">探测完成</c:if> 
											
											</td>
											
											<td valign="middle" align="center">
											<c:if test="${task.result==0}">
											<input type="button" value="查看结果" onclick="openResultDialog(${task.taskId});" />
											</c:if>
											</td>

										</tr>
									</s:iterator>
									</tbody>
									<tr>
										<td colspan="8" width="100%"><jsp:include
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
	<s:form action="proQuery.action" namespace="/assetProbeTask" method="post"
		theme="simple" id="queryForm" name="queryForm">
		<s:hidden name="selTaskName" id="selTaskName" />
		<s:hidden name="selTaskState" id="selTaskState" />
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
				<td width="25%">资产任务名称:</td>
				<td><input id="taskName" name="taskName" type="text"
					style="width:250px;" onkeypress="if(event.keyCode==13)extQuery();" />
				</td>
			</tr>
			<tr>
				<td>探测状态:</td>
				<td>
					<select id="taskState" name="taskState">
						<option value="10">全部</option>
						<option value="-1">未探测</option>
						<option value="1">正在探测</option>
					    <option value="0">探测完成</option>
					</select>
				</td>
			</tr>
		</table>
	</div>
    
    <!-- 查看探测出来的结果 -->
    <div id="dialog-lookForResult" title="探测结果信息">
        <table id="dlg-table-assets" width='96%' cellspacing='0'
			cellpadding='0' border='0' align='center'>
			<tr>
				<td>
					<table width="100%" border="0" cellspacing="1" cellpadding="0"
						class="tab2" id="denyPolicy">
					<tr style="font-weight: bold;"><td>资产IP</td><td>资产MAC</td><td>添加</td></tr>	
					</table>
				
				</td>
			</tr>
		</table>
    </div>
    
</body>
</html>
