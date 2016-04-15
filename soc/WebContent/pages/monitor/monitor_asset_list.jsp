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
				},

				"取消[Esc]" : function() {
					$(this).dialog("close");
				}
			}
		});
		$('#listTable').tablesorter();
	});

	//快速检索
	function query() {
		//关键字
		if($('#keyword').val().length>50){
			alert("搜索长度不能大于50");
			$('#keyword').val('');
			$('#keyword').focus();
			return ;
		}
		$('#keyword').val($.trim($('#keyword').val()));
		var key=$('#keyword').val();
		var regex=key.match(/^([^`~!@#$%^&*()=|{}':;, \\\[\]<>\/?~！@#￥……&*（）—|{}【】‘；：”“'。\"，、？])*$/);
		if(regex==null){
			alert("您的输入包含特殊字符！");
			$('#keyword').val('');
			$('#keyword').focus();
			return;
		}
		location.href = "${ctx}/monitorGroup/showAsset.action?keyword="
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
		if($('#assetName').val().length>50){
			alert("搜索长度不能大于50");
			$('#assetName').val('');
			$('#assetName').focus();
			return ;
		}
		$('#assetName').val($.trim($('#assetName').val()));
		var key=$('#assetName').val();
		var regex1=key.match(/^([^`~!@#$%^&*()=|{}':;, \\\[\].<>\/?~！@#￥……&*（）—|{}【】‘；：”“'。\"，、？])*$/);
		if(regex1==null){
			alert("您的输入包含特殊字符！");
			$('#assetName').val('');
			$('#assetName').focus();
			return;
		}
		
		var key2=$('#assetIps').val();
		if(key2!=""){
			var regex2=key2.match(/^((([01]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))[.]){3}(([0-1]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))$/);
			if(regex2==null){
				alert("您的输入的IP不合法！");
				$('#assetIps').val('');
				$('#assetIps').focus();
				return;
			}
		}
		$('#selAssetName').val($('#assetName').val());
		$('#selAssetIps').val($('#assetIps').val());
		$('#userForm').submit();
	}

/* 	function deleteUser(assetSegMent,collectorId) {
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
			location.href = "${ctx}/asset/deleteAsset.action?ids=" + ids+"&assetSegMent="+assetSegMent+"&collectorId="+collectorId;
		}
	} */
	
	function gotoInfo(assetMac,assetId,assetSupportDeviceId)
	{
	   window.open('${ctx}/monitorGroup/queryInfo.action?assetMac='+assetMac+'&assetId='+assetId+'&assetSupportDeviceId='+assetSupportDeviceId);
	}
	
	
	function overDlg(e, header, content, num) {
		$('#blk_header').html(header);
		$('#blk_content').html(content);

		createOverDiv(e, 10, 10, $('#blk').html());
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
	<s:form action="showAsset.action"  method="post"
		theme="simple" id="userForm" name="userForm">
		<s:hidden id="assetGroupId" name="assetGroupId" />
		<s:hidden id="selAssetName" name="selAssetName" />
		<s:hidden id="selAssetIps" name="selAssetIps" />
		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 0px">
			<tr><td>
			<div class="box">
			<div class="right">

				<!-- <input type="button" value="添加" class="btnstyle"
					style="margin-right:5px;margin-top:-2px"
					onclick="location.href='${ctx}/pages/workorder/workOrder_add.jsp'" />  -->
					
				<input type="button" value="删除" style="margin-right:12px;margin-top:-2px"
					class="btnstyle" onclick="deleteWarn();" />
					
			</div>
			<span class="kuaiju">快速搜索</span>
			<input type="text" id="keyword" value="${keyword}" name="keyword" style="height: 15px;" class="jianju" />
			<input type="hidden" id="manyStatus" value="${manyStatus}" name="manyStatus" class="jianju" />
			<img src="${ctx}/images/search.jpg" onclick="query();"
				style="margin-left:5px"> <a href="#" class="jianju"
				onclick="extQueryDlg();">高级</a>
		</div></td></tr>
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
									<%-- <td width="6%" class="biaoti"><input type="checkbox"
											id="chkAll" name="chkAll" class="check-box not_checked" /></td>
											--%>
										<th width="18%" class="biaoti">资产</th>
										<th width="20%" class="biaoti">ip地址</th>
										<th width="18%" class="biaoti">所属组</th>
										<th width="18%" class="biaoti">重要性</th>
										<th width="28%" class="biaoti">操作</th>
									</tr>
									</thead>
									<tbody>
									<s:iterator value="assetList" status="stat">
										<%--<input type="hidden" name="assetId" id="assetId"
											value="${assetId}" />
										--%><tr>
										<%--	<td class="biaocm" valign="middle"><input
												type="checkbox" name="ids" id="${assetId}"
												value="${assetId}" class="check-box" /></td>
												 --%>

											<td valign="middle">${assetName}</td>

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
													test="${assetImportance==2}">非常重要</c:if>
											</td>										
										
											<td valign="middle" align="center"><img
												src="${ctx}/images/asset_info.png" class="hand"
												title="查看资产信息" onclick="gotoInfo('${assetMac}','${assetId}','${assetSupportDeviceId}');" />
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
					</div>
				</td>
			</tr>
		</table>
	</s:form>
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
	
	
</body>
</html>