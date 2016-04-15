<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@page import="com.soc.model.user.User"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html>
<head>
<meta http-equiv="Pragma" content="no-cache" />
<link href="${ctx}/styles/user/user.css" rel="stylesheet"
	type="text/css">
 <link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet" type="text/css">
<%-- <link href="${ctx}/styles/jquery-ui-1.8.21.css" rel="stylesheet"
	type="text/css"> --%>
<link href="${ctx}/styles/jquery.popuplayer.css" rel="stylesheet"
	type="text/css">
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx }/scripts/jquery-latest.js"></script>
<%-- <script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine-en.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine.js"></script>--%>
<script type="text/javascript"
	src="${ctx }/scripts/jquery.tablesorter.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
<script type='text/javascript' src="${ctx}/scripts/popuplayer.js"></script>
<%-- <script type="text/javascript" src="${ctx}/pushlet/ajax-pushlet-client.js"></script>--%>
<script type="text/javascript" src="${ctx }/scripts/My97DatePicker/WdatePicker.js"></script>
<script language="javascript" >
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
			height : 210,
			buttons : {
				"查询[Enter]" : function() {
					
					checkmail();
				},

				"取消[Esc]" : function() {
					$(this).dialog("close");
				}
			}
		});
		
			//同步账户
		$('#dialog-account').dialog({
			autoOpen : false,
			width : 400,
			height : 200,
			buttons : {
				"确定[Enter]" : function() {
					accountAjax();
				},
				"取消[Esc]" : function() {
					$(this).dialog("close");
				}
			}
		});

		$("#myTable").tablesorter();

	});


	//快速检索
	function query() {
		//关键字
		var str=$('#keyword').val().match(/^([^`~!@#$%^&*()=|{}':;, \\\[\].<>\/?~！@#￥……&*（）—|{}【】‘；：”“'。\"，、？])*$/); 
		if(str==null){
			alert("您的输入包含特殊字符！");
			return;
		}
		var keyword = $("#keyword").val();
		location.href = "${ctx}/category/queryCategory.action?keyword="
				+ encodeURI(encodeURI(keyword, "utf-8"));
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

	

	function overDlg(e, header, content, num) {
		$('#blk_header').html(header);
		$('#blk_content').html(content);

		createOverDiv(e, 10, 10, $('#blk').html());
	}

	function extQueryDlg() {
		$("#realName").val('');
		$("#loginName").val('');
		$("#creatorIP").val('');
		$("#userEmail").val('');
		$("#roleid").val('');
		$("#userState").val('');
		$("#check_creatorIP_msg").empty();
		$('#dialog-extQuery').dialog('open');
	}

	function accountShow() {
		
		$('#dialog-account').dialog('open');
		$('#d4321').blur();
	}
	function extQuery() {
		var regex = /^((([01]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))[.]){3}(([0-1]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))$/;
		if ($('#creatorIP').val() == '') {
			$('#selUserRealName').val($('#realName').val());
			$('#selUserLoginName').val($('#loginName').val());
			$('#selUserCreatorIp').val($('#creatorIP').val());
			$('#reluserEmail').val($('#userEmail').val());
			$('#relroleid').val($('#roleid').val());
			$('#reluserState').val($('#userState').val());
			
			$('#queryForm').submit();
			$(this).dialog("close");
		} else {
			if (regex.test($('#creatorIP').val())) {
				
				$('#selUserRealName').val($('#realName').val());
				$('#selUserLoginName').val($('#loginName').val());
				$('#selUserCreatorIp').val($('#creatorIP').val());
				$('#reluserEmail').val($('#userEmail').val());
				$('#relroleid').val($('#roleid').val());
				$('#reluserState').val($('#userState').val());
				
				$('#queryForm').submit();
				$(this).dialog("close");
			} else {
				$("#check_creatorIP_msg").html(
						"<font color='red'>请输入正确的ip地址...</font>");
			}
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
			alert("请至少选择一个设备信息...");
			return;
		}

		if (confirm("确认执行操作吗？")) {
			location.href = "${ctx}/category/deletecategory.action?ids=" + ids;
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
	function destory()
	{
	   destroyOverDlg();
	}
	

</script>
</head>

<body style="margin-top:2px;">
	
	<s:form action="queryCategory" namespace="/category" method="post"
		theme="simple" id="categoryForm" name="categoryForm">
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
								onclick="location.href='${ctx}/category/toEditCategory.action';" />

							<input type="button" value="删除"
								style="margin-right:12px;margin-top:-2px" class="btnstyle"
								onclick="deleteUser();" />
						</div>

 						<span class="kuaiju">快速搜索</span> <input type="text" id="keyword" maxlength="50"
							value="${keyword}" name="keyword" class="jianju" style="height: 15px;"/> <img
							src="${ctx}/images/search.jpg" onclick="query();"
							style="margin-left:5px"> 

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
									class="tab2" id="myTable">
									<thead>
										<tr height="28" class="biaoti">
											<td width="6%" class="biaoti"><input type="checkbox"
												id="chkAll" name="chkAll" class="check-box not_checked" />
											</td>
											<th width="50%" class="biaoti">
											设备名称</th>
											<th width="50%" class="biaoti">
											设备厂商 </th>
										</tr>
									</thead>
									<tbody>
										<s:iterator value="categorylist" status="stat">
											<tr>
												<td class="biaocm" valign="middle"><input
													type="checkbox" name="ids" id="${device_category_id}" value="${device_category_id}"
													class="check-box" />
												</td>
												<td valign="middle"><a
													href="${ctx}/category/toEditCategory.action?categoryId=${device_category_id}">${device_category_name} </a>
												</td>
												
												<c:set var="upsName" value="" />
												<s:iterator value="devicelist">
													<c:set var="upsName" value="${upsName}[${device_category_name}]<br/>" />
												</s:iterator>
												<td valign="middle" class="td_list_data italic hand" align="center"
													onmouseover="overDlg(this,'厂商设备列表','${upsName}','');" onmouseout="destory();">
													厂商设备列表
												</td>
											</tr>
										</s:iterator>
									</tbody>
									<tr>
										<td colspan="7" width="100%"><jsp:include
												page="../commons/page.jsp"></jsp:include><br></td>
									</tr>
								</table>
							</div>
							<!-- information area -->
						</div>
					</div></td>
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

</body>
</html>
