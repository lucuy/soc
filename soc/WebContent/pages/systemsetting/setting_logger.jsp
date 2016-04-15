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

<link href="${ctx}/styles/audit/audit.css" rel="stylesheet"
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
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine.js"></script>
<script language="javascript">
//sousuo
	function query() {
		$('#keyword').val($.trim($('#keyword').val()));
		var regex=$('#keyword').val().match(/^([^`~!@#$%^&*()=|{}':;,\\\[\].<>\/?~！@#￥……&*（）—|{}【】‘；：”“'。\"，、？])*$/); 
		if(regex==null){
			alert("您的输入包含特殊字符！");
			return;
		}
		location.href = "${ctx}/settingLogger/search.action?keyword="
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
				//	extQuery();
				
				window.location="${ctx}/settingLogger/search.action?collectorBasic="+$("#collectorBasicValue").val()+"&collectorStatus="+$("#collectorStatusValue").val()+"&collectNetwork="+$("#collectNetworkValue").val();
					$(this).dialog("close");
				},

				"取消[Esc]" : function() {
					$(this).dialog("close");
				}
			}
		});
		$('#sortList').tablesorter();
	});
	function deleteSelect() {
		var ids = "";
		$("[name=ids]:checkbox:checked").each(function() {
			if (ids != "")
				ids += "," + $(this).val();
			else
				ids = $(this).val();

		});
		if ($(":checkbox[checked = true]").size() < 1) {
			alert("请至少选择一个采集器!");
			return;
		}
		location.href = '${ctx}/systemAudit/delete.action?ids=' + ids;
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
	  //  $('#collectorBasicValue').val($.trim($('#collectorBasicValue').val()));
		$('#collectorBasic').val($('#collectorBasicValue').val());
		//$('#collectorStatusValue').val($.trim($('#collectorStatusValue').val()));
		$('#collectorStatus').val($('#collectorStatusValue').val());
		//$('#collectNetworkValue').val($.trim($('#collectNetworkValue').val()));
		$('#collectNetwork').val($('#collectNetworkValue').val());
		$('#queryForm').submit();
	}
	
	function addcollector() {
	window.location.href='${ctx}/settingLogger/register.action';
	}
	function delcollector(){
	var delIdVal = "";
		$("[name=collectorId]:checkbox:checked").each(function() {
			if (delIdVal != "")
				delIdVal += "," + $(this).val();
			else
				delIdVal = $(this).val();

		});
		if ($(":checkbox[checked = true]").size() < 1) {
			alert("请至少选择一个采集器!");
			return;
		}
		$.ajax({
			type : "post",
			url : "${ctx}/settingLogger/selectAsset.action",
			dataType : "text",
			data : "&delErr=" + delIdVal,
			success : function(result) {
				if (result == 'true') {
					if(confirm("确认删除吗？")){
						existflag = false;
						location.href = '${ctx}/settingLogger/delId.action?delIdVal=' + delIdVal;
					}
				} else {
					existflag = true;
					alert("您选择的采集器已被资产占用，不可以删除！");
				}
			}
		});
	}
	
	//更新状态
	function updateStatus() {
		var ids = "";
		$("[name=collectorId]:checkbox:checked").each(function() {
			if (ids != "") {
				ids += "," + $(this).val();
			} else {
				ids = $(this).val();
			}
		});
		if ($(":checkbox[checked = true]").size() < 1) {
			alert("请至少选择一个关联规则信息...");
			return;
		}

		if ($("#status").val() == 100) {
			alert("请选择需要执行的操作...");
			return;
		}

		if (confirm("确认执行操作吗？")) {
			location.href = "${ctx}/settingLogger/updateLoggerStatus.action?collectorNo="
					+ ids + "&collectorStatus=" + $("#status").val();
		}
	}
	
</script>
</head>
<body>
	<s:form namespace="/settingLogger" method="post"
		theme="simple" id="setting-localCollector" name="setting-localCollector">
		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 2px">
			<!-- 空行-->
			<tr>
				<td></td>
			</tr>
			<tr>
			<td>
				<div class="box">
				<div class="right">
				
				   <input type="button" value="添加" class="btnstyle" style="margin-right:5px;margin-top:5px"
				   onclick="addcollector();"/>
				   <input type="button" value="删除" class="btnstyle" style="margin-right:5px;margin-top:5px"
				   onclick="delcollector();"/>
				   </div>
				   <span class="kuaiju">快速搜索</span> <input type="text" id="keyword" maxlength="255"
							value="${keyword}" name="keyword" class="jianju" /> <img
							src="${ctx}/images/search.jpg" onclick="query();"
							style="margin-left:5px"> <a href="#" class="jianju"
							onclick="extQueryDlg();">高级</a>
					
				   <span class="kuaiju"></span>
				   <span>操作</span>

							<select id="status" class="jianju">

								<option value="100" style="margin-right:24px">--更多操作--</option>

								<option value="1">启用采集器</option>

								<option value="0">停用采集器</option>
							</select> <input type="button" value="执行" style="margin-left:5px"
								class="btnstyle" onclick="updateStatus()" /> <span
								style="margin-right:5px"></span>
				</div>
				</td>
			<tr>
				<td>
					<table style="margin-top: 4px" width="100%" border="0"
						cellspacing="1" cellpadding="0" class="tab2" id="sortList">
						<thead>
						<tr height="28" class="biaoti">
							<td width="3%" class="biaoti"><input type="checkbox"
								id="chkAll" name="chkAll" class="check-box not_checked" /></td>
							<th width="7%" align="center" class="biaoti">采集器名称</th>
							<th width="7%" align="center" class="biaoti">MAC地址</th>
							<th width="7%" align="center" class="biaoti">snmpWalk轮循间隔</th>
							<th width="7%" align="center" class="biaoti">范式化轮循间隔</th>
							<th width="7%" align="center" class="biaoti">采集器网络</th>
							<th width="7%" align="center" class="biaoti">采集器状态</td>
						</tr>
						</thead>
						<tbody>
						<c:set value="1" var="data" />

						<s:iterator value="collectorList">
							<tr>
								<td class="biaocm" valign="middle"><input type="checkbox"
									value="${collectorId}" name="collectorId" id="${collectorId}" class="check-box" />
								</td>
								<td valign="middle" class="td_list_data">
								<a href="${ctx}/settingLogger/queryId.action?collectorId=${collectorId}">
								${collectorBasic}</a></td>
								<td valign="middle" class="td_list_data">${collectorMac}</td>
								<td valign="middle" class="td_list_data">${collectorTime}</td>
								<td valign="middle" class="td_list_data">${collectorPattern}</td>
								<td valign="middle" class="td_list_data"><c:if test="${collectNetwork==0}">内网</c:if>
								<c:if test="${collectNetwork==1}">外网</c:if></td>
								<td valign="middle" class="td_list_data">
								<c:if test="${collectorStatus==1}">启用</c:if>
								<c:if test="${collectorStatus==0}">停用</c:if></td>

							</tr>
							<c:set value="${data+1}" var="data" />
						</s:iterator>
                        </tbody>
						<tr>
							<td colspan="7" width="100%"><jsp:include
									page="../commons/page.jsp"></jsp:include></td>
						</tr>
					</table></td>
			</tr>
		</table>
		<s:form action="" namespace="/settingLogger" method="post"
		theme="simple" id="queryForm" name="queryForm">
		<s:hidden id="collectorBasic" name="collectorBasic" />
		<s:hidden id="collectorStatus" name="collectorStatus" />
		<s:hidden id="collectNetwork" name="collectNetwork" />
	</s:form>
	</s:form>
	<!-- ui-dialog -->
	<div id="dialog-extQuery" title="高级查询">
		<table width="90%" border="0" cellspacing="5" cellpadding="5"
			style="margin-left:20px;">
			<tr>
				<td width="25%">采集器名称:</td>
				<td><input id="collectorBasicValue" name="collectorBasicValue" type="text"
					style="width:250px;" onkeypress="if(event.keyCode==13)extQuery();" />
				</td>
			</tr>
			<tr><td>采集器状态:</td>
			<td>
			<select id="collectorStatusValue" name="collectorStatusValue">

				<option value="" style="margin-right:24px">--请选择解采集器状态--</option>

				<option value="1">已启用</option>

				<option value="0">已停用</option>
			</select>
			</td></tr>
			<tr><td>采集器网络:</td>
			<td>
			<select id="collectNetworkValue" name="collectNetworkValue">

				<option value="" style="margin-right:24px">--请选择采集器网络--</option>

				<option value="0">内网</option>

				<option value="1">外网</option>
			</select>
			</td></tr>
		</table>
	</div>
</body>
</html>