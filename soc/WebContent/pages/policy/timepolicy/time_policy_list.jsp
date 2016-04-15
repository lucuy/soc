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

<link href="${ctx}/styles/user/user.css" rel="stylesheet"
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
	//导出东西所用
	/* function exportCSV() {
		var ids = "";
		$("[name=ids]:checkbox:checked").each(function() {
			if (ids != "")
				ids += "," + $(this).val();
			else
				ids = $(this).val();

		});
		if ($("#chkAll").attr("checked") == true) {
			if (confirm("你是要导出选中/全部的数据...")) {
				location.href = "${ctx}/employee/checkExport.action";
			} else {
				location.href = "${ctx}/employee/checkExport.action?ids=" + ids;
			}
		}
		if ($("#chkAll").attr("checked") == false) {
			location.href = "${ctx}/employee/checkExport.action?ids=" + ids;
		}
	} */

	//更新时间策略的状态
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
			alert("请至少选择一个时间策略信息...");
			return;
		}

		if ($("#status").val() == 100) {
			alert("请选择需要执行的操作...");
			return;
		}

		if (confirm("确认执行操作吗？")) {
			location.href = "${ctx}/timePolicy/updatetimePolicyStatus.action?ids="
					+ ids + "&timePolicyStatus=" + $("#status").val();
		}
	}
	var rege =/^([^`~!@#$%^&*()=|{}':;, \\\[\].<>\/?~！@#￥……&*（）—|{}【】‘；：”“'。\"，、？])*$/;
	//模糊查询
	function query() {
	    //去除输入文字的首尾空格
		var keyword = $.trim($("#keyword").val());//模糊
		/* var order = $("#order").val();//最近更新 */
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
		location.href = "${ctx}/timePolicy/query.action?keyword="
				+ encodeURIComponent(encodeURIComponent(keyword));
	}

	/* 执行标记删除的功能*/
	function deleteQuery() {
		var ids = "";
		$("[name=ids]:checkbox:checked").each(function() {
			if (ids != "") {
				ids += "," + $(this).val();
			} else {
				ids = $(this).val();
			}
		});
		if ($(":checkbox[checked = true]").size() < 1) {
			alert("请至少选择一个时间策略信息...");
			return;
		}
		if (confirm("确认执行操作吗？")) {
			location.href = "${ctx}/timePolicy/deletetimePolicy.action?ids="
					+ ids;

		}
	}

	$(document).ready(function() {
		$("#keyword").keydown(function(event) {
			if (event.keyCode == 13) {
				query();
			}
		});

		// Dialog			
		$('#dialog-extQuery').dialog({
			autoOpen : false,
			width : 450,
			height : 220,
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
	    $("#listTable").tablesorter();
	});

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
		if($('#empName').val().length>50){
			alert("策略名称长度不能大于50");
			$('#empName').val('');
			$('#empName').focus();
			return ;
		}
		if(!rege.test($('#empName').val())){
			alert("输入的内容包含特殊字符，请重新输入");
			$('#empName').val('');
			$('#empName').focus();
			return;
		}
		if($('#timeDescribe').val().length>255){
			alert("描述长度不能大于255");
			$('#timeDescribe').val('');
			$('#timeDescribe').focus();
			return ;
		}
		$('#timePolicyName').val($('#empName').val());
		$('#timePolicyMemo').val($('#timeDescribe').val());
		$('#timePolicyStatus').val($('#timePolicySates').val());
		$('#timePolicyEffectWay').val($('#timePolicyEffectWay1').val());
		/* $('#selGroupName').val($('#groupName').val());
		$('#selLoginName').val($('#loginName').val());
		$('#selCreateIP').val($('#createIP').val()); */
		$('#queryForm').submit();
	}
</script>
</head>

<body style="margin-top:2px;">
	<s:form action="query.action" namespace="/timePolicy" method="post"
		theme="simple" id="timeForm" name="timeForm">
		<input type="hidden" name="timePolicyName" value="${timePolicyName}" />
		<input type="hidden" name="timePolicyMemo" value="${timePolicyMemo}" />
		<input type="hidden" name="timePolicyStatus" value="${timePolicyStatus}" />
		<input type="hidden" name="timePolicyEffectWay" value="${timePolicyEffectWay}" />
		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 0px">
			<!-- 空行 -->
			<!-- <tr>
				<td><s:hidden name="time" id="time" /> <s:hidden
						name="passwordPolicyStatus" id="passwordPolicyStatus" /> <s:hidden
						name="order" id="order" />
				</td>

			</tr> -->

			<tr>
				<td>
				<div class="box">
				
				<div class="right">
				   
				   <input type="button" value="添加" class="btnstyle" style="margin-right:5px;margin-top:-2px"
				   onclick="location.href='${ctx}/timePolicy/edit.action';"/>
				   
				   <input type="button" value="删除" style="margin-right:12px;margin-top:-2px" class="btnstyle" onclick="deleteQuery();"/>
				   </div>
				
				   <span class="kuaiju">快速搜索</span>
				   
				   <input type="text" id="keyword" value="${keyword}" name="keyword" class="jianju" style="height: 15px;" />
				   
				   <img src="${ctx}/images/search.jpg" onclick="query();" style="margin-left:5px">
				   
				   <a href="#" class="jianju" onclick="extQueryDlg();">高级</a>
				   
				   <span style="margin-left:24px">操作</span>
				   
				   <select  id="status" class="jianju">
				   
				   <option value="100">--更多操作--</option>
				   
                   <option value="0">锁定时间策略</option>
                   
                   <option value="1">激活时间策略</option>
                   </select>
                   
				   <input type="button" value="执行" style="margin-left:5px" class="btnstyle" onclick="updateStatus()"/>
				   
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
											<th width="10%" class="biaoti">时间策略</th>
											<th width="10%" class="biaoti">生效方式</th>
											<th width="10%" class="biaoti">创建时间</th>
											<th width="10%" class="biaoti">状态</th>
											<th width="40%" class="biaoti">描述</th>
										</tr>
									</thead>
									<tbody>
										<c:set value="" var="resStr" />
										<s:property value="#session.XXX" />
										<s:iterator value="timePolicyList" status="stat">
											<input type="hidden" name="timePolicyId" id="timePolicyId"
												value="${timePolicyId}" />
											<tr>
												<td class="biaocm" valign="middle"><input
													type="checkbox" name="ids" id="${timePolicyId}"
													value="${timePolicyId}" class="check-box" /></td>
												<td valign="middle" class="td_list_data"><a
													href="${ctx}/timePolicy/edit.action?timePolicyId=${timePolicyId}">${timePolicyName}</a>
												</td>
												<td valign="middle" align="center" class="td_list_data"><c:if
														test="${timePolicyEffectWay==0}">禁止</c:if><c:if
														test="${timePolicyEffectWay==1}">允许</c:if>
												</td>
												<td valign="middle" class="td_list_data" align="center"><s:date
														name="timePolicyCreateDateTime" format="yyyy-MM-dd" />
												</td>
												<td valign="middle" align="center" class="td_list_data"><c:if
														test="${timePolicyStates==0}">锁定</c:if> <c:if
														test="${timePolicyStates==1}">激活</c:if></td>
												<td valign="middle" class="td_list_data" align="center"><c:out value="${timePolicyMemo}"></c:out></td>
	
											</tr>
										</s:iterator>
									</tbody>
									<tr>
										<td colspan="6" width="100%"><jsp:include
												page="../../commons/page.jsp"></jsp:include></td>
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
	<s:form action="query.action" namespace="/timePolicy" method="post"
		theme="simple" id="queryForm" name="queryForm">
		<!-- 高级查询用 -->
		<s:hidden name="timePolicyName" id="timePolicyName" />
		<s:hidden name="timePolicyMemo" id="timePolicyMemo" />
		<s:hidden name="timePolicyStatus" id="timePolicyStatus" />
		<s:hidden name="timePolicyEffectWay" id="timePolicyEffectWay" />
		<!-- <s:hidden name="selpasswordPolicyDESC" id="selpasswordPolicyDESC" />
		<s:hidden name="selRealName" id="selLoginName" />
		<s:hidden name="selCreatorIp" id="selCreatorIp" /> -->
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
				<td width="25%">时间策略名:</td>
				<td><input id="empName" name="empName" type="text"
					style="width:250px;" onkeypress="if(event.keyCode==13)extQuery();" />
				</td>
			</tr>
			<tr>
				<td>描述:</td>
				<td><input id="timeDescribe" name="timeDescribe" type="text"
					style="width:250px;" onkeypress="if(event.keyCode==13)extQuery();" />
				</td>
			</tr>
			<tr>
				<td>生效方式：</td>
				<td><select name="timePolicyEffectWay"
					id="timePolicyEffectWay1" style="width:93%">
						<option value="100" selected="selected">操作--</option>
						<option value="1">允许登陆</option>
						<option value="0">禁止登陆</option>
				</select>
				</td>
			</tr>
			<tr>
				<td>状态:</td>
				<td><select name="timePolicyStates" id="timePolicySates"
					style="width:93%">
						<option value="100" selected="selected">操作--</option>
						<option value="1">激活</option>
						<option value="0">锁定</option>
				</select></td>
			</tr>
		</table>
	</div>
</body>
</html>
