<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<%@page import="com.soc.model.user.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();

	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<%-- <script type="text/javascript" src="${ctx}/pushlet/ajax-pushlet-client.js"></script>--%>


<link href="${ctx}/styles/user/user.css" rel="stylesheet"
	type="text/css">
 <link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet"
	type="text/css"> 
<%-- <link href="${ctx}/styles/jquery-ui-1.8.21.css" rel="stylesheet"
	type="text/css"> --%>
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
		$("#keyword").keydown(function(event) {
			if (event.keyCode == 13) {
				query();
			}
		});

		// Dialog			
		$('#dialog-extQuery').dialog({
			autoOpen : false,
			width : 450,
			height : 170,
			buttons : {
				"查询[Enter]" : function() {
					extQuery();
				},

				"取消[Esc]" : function() {
					$(this).dialog("close");
				}
			}
		});
		
		// 内置角色弹框			
		$('#dialog-inUserRole').dialog({
			autoOpen : false,
		});
		
		// Dialog			
		$('#dialog-copyRole').dialog({
			autoOpen : false,
			width : 390,
			height : 150,
			buttons : {
				"确定[Enter]" : function() {
				
					/** IE10 浏览器跳入一下代码中会执行else */
					/* if(/msie/i.test(navigator.userAgent))    //ie浏览器 
				 	{
					    document.getElementById('copyRoleName').onpropertychange = checkRoleName;
				 	} 
					 else 
					{
					   document.getElementById('copyRoleName').addEventListener("input",checkRoleName,false); 
					}  */
					
					checkRoleName();
					copyRole();
				},

				"取消[Esc]" : function() {
					$(this).dialog("close");
				}
			}
		});
		
		
	    $("#myTable").tablesorter();
	});
		
		function inUserRole(roleId){
			
				if(roleId==34||roleId==35||roleId==36){
					$('#dialog-inUserRole').dialog('open');
					// 内置角色弹框			
					$('#dialog-inUserRole').dialog({
						buttons : {
							"确定[Enter]" : function() {
								location.href="${ctx}/role/editRole.action?roleId="+roleId;
							}
						}
					});
					}else{
						location.href="${ctx}/role/editRole.action?roleId="+roleId;
					}
				}
		
	function deleteRole() {
		var ids = "";
		$("[name=ids]:checkbox:checked").each(function() {
			if (ids != "") {
				ids += "," + $(this).val();
			}
			else {
				ids = $(this).val();
			}
			c=$(this).val();
		});
		if ($(":checkbox[checked = true]").size() < 1) {
			$('#dialog-inUserRole').dialog('open').text("请至少选择一个用户信息...");
			// 内置用户弹框			
			$('#dialog-inUserRole').dialog({
				buttons : {
					"确定[Enter]" : function() {
						$(this).dialog("close");
					}
				}
			});
			return;
		}
		
		if(c==34||c==35||c==36){
			 $('#dialog-inUserRole').dialog('open').text("包含系统内置角色，不能删除");
				// 内置用户弹框			
				$('#dialog-inUserRole').dialog({
					buttons : {
						"确定[Enter]" : function() {
							$(this).dialog("close");
						}
					}
				});
				return;
		}
		if (1==1) {
			$('#dialog-inUserRole').dialog('open').text("确认执行操作吗？");
				// 内置用户弹框			
				$('#dialog-inUserRole').dialog({
					buttons : {
						"确定[Enter]" : function() {
					location.href = "${ctx}/role/deleteRole.action?ids=" + ids;
				},
					"取消[Esc]" : function() {
						$(this).dialog("close");
						return;
				}
					}
				});
		}
	}
	var rege =/^([^`~!@#$%^&*()=|{}':;, \\\[\].<>\/?~！@#￥……&*（）—|{}【】‘；：”“'。\"，、？])*$/;
	function query() {
		$('#keyword').val($.trim($('#keyword').val()));//模糊
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
		location.href = "${ctx}/role/queryRole.action?keyword="
				+  encodeURI(encodeURI($('#keyword').val(), "utf-8"));
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
	
	function copyRoleDlg(roleId) {
		if(roleId==34||roleId==35||roleId==36){
			 $('#dialog-inUserRole').dialog('open').text("系统内置角色，不能复制");
			 $('#dialog-inUserRole').dialog({
					buttons : {
						"确定[Enter]" : function() {
							$(this).dialog("close");
						}
					}
				});
				return;
		}else{
			$("#copyRoleId").val(roleId);
			$("#copyRoleName").val('');
			$("#check_copyrolename_msg").removeClass('spanred');
			overFlag = false;
			$('#dialog-copyRole').dialog('open');
		}
	}

	function extQuery() {
		$('#selRoleName').val($('#roleName').val());
		$('#selUserLoginName').val($('#userLoginName').val());
		if($('#roleName').val().length>50){
			alert("搜索长度不能大于50");
			$('#roleName').val('');
			$('#roleName').focus();
			return;
		}
		if(!rege.test($('#roleName').val())){
			alert("输入的内容包含特殊字符，请重新输入");
			$('#roleName').val('');
			$('#roleName').focus();
			return;
		}
		if($('#userLoginName').val().length>50){
			alert("搜索长度不能大于50");
			$('#userLoginName').val('');
			$('#userLoginName').focus();
			return;
		}
		if(!rege.test($('#userLoginName').val())){
			alert("输入的内容包含特殊字符，请重新输入");
			$('#userLoginName').val('');
			$('#userLoginName').focus();
			return;
		}
		$('#queryForm').submit();
	}
	
	var existFlag = true;
	var overFlag = false;
	//验证角色名称唯一性
	function checkRoleName() {
		var roleName = $("#copyRoleName").val();
		var msg=$("#check_copyrolename_msg").val();
		$("#check_copyrolename_msg").empty();
		$.ajax({
			type : "post",
			url : "${ctx}/role/checkRoleName.action",
			dataType : "text",
			data : "&roleName=" + roleName,
			async : false,
			success : function(result) {
				if (result == 'true') {
					$("#check_copyrolename_msg").addClass("spanred");
					$("#check_copyrolename_msg").html("<font color='red'>该名称已占用</font>");
					existFlag = true;
				} else {
					existFlag = false;
				}
				overFlag = true;
			}
		});
	}
	
	function copyRole() {
		/* if(!overFlag) {
			alert('网络不正常！');
			return;
		} */
		var regex1 = /^([^`~!@#$%^&*()=|{}':;,\\ \[\].<>\/?~！@#￥……&*（）—|{}【】‘；：”“'。，、？])*$/;
		var regex2 = /^.{0,50}$/;
		overFlag = false;
		if($("#copyRoleName").val() == '') {
			$("#check_copyrolename_msg").html("<font color='red'>请输入角色名称...</font>");
			return;
		}
		else if(!regex1.test($("#copyRoleName").val())) {
			$("#check_copyrolename_msg").html("<font color='red'>您输入的内容含有特殊字符...</font>");
			return;
		}
		else if(!regex2.test($("#copyRoleName").val())) {
			$("#check_copyrolename_msg").html("<font color='red'>请输入1~50个字符...</font>");
			return;
		}
		else if(existFlag == true) {
			return;
		} else {
			location.href = "${ctx}/role/copyRole.action?copyRoleId=" + $("#copyRoleId").val() + "&copyRoleName=" + encodeURIComponent(encodeURIComponent($("#copyRoleName").val()));
		}
	}
	
	
</script>
</head>

<body style="margin-top:2px;">
	<s:form action="queryRole.action" namespace="/role" method="post"
		theme="simple" id="RoleForm" name="RoleForm">

		<input type="hidden" name="selRoleName" value="${selRoleName}" />
		<input type="hidden" name="selRoleUserLoginName"
			value="${selUserLoginName}" />
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
								onclick="location.href='${ctx}/role/editRole.action?order=${order}';" />

							<input type="button" value="删除"
								style="margin-right:12px;margin-top:-2px" class="btnstyle"
								onclick="deleteRole();" />
						</div>

						<span class="kuaiju">快速搜索</span> <input type="text" id="keyword"
							value="${keyword}" name="keyword" class="jianju" style="height: 15px;"/> <img
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
									class="tab2" id="myTable">
									<thead>
									<tr height="28" class="biaoti">
										<td width="8%" class="biaoti"><input type="checkbox"
											id="chkAll" name="chkAll" class="check-box not_checked" />
										</td>
										<th width="25%" class="biaoti">角色名称</th>
										<th width="15%" class="biaoti">创建日期</th>
										<th width="35%" class="biaoti">描述</th>
										<th width="25%" class="biaoti">操作</th>
									</tr>
									</thead>
									<tbody>
									<s:iterator value="roleList" status="stat">
										<input type="hidden" name="roleId" id="roleId"
											value="${roleId}" />
										<tr>
											<td class="biaocm" valign="middle"><input
												type="checkbox" name="ids" id="${roleId}" value="${roleId}"
												class="check-box" />
											</td>
											<td valign="middle"><a
												href="#"id="${roleId}" onclick="inUserRole(this.id);">${roleName}</a>
											</td>
											<td valign="middle"><s:date name="roleCreateDateTime"
													format="yyyy-MM-dd" />
											</td>

											<td valign="middle" align="center"><c:out value="${roleMemo}"></c:out></td>
											<td valign="middle" align="center"><img alt="拷贝角色"
												title="拷贝角色" class="hand" src="${ctx }/images/copy.png"
												onclick="copyRoleDlg('${roleId}');">
											</td>
										</tr>
									</s:iterator>
									</tbody>
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
	<s:form action="queryRole.action" namespace="/role" method="post"
		theme="simple" id="queryForm" name="queryForm">
		<s:hidden name="selRoleName" id="selRoleName" />
		<s:hidden name="selRoleUserLoginName" id="selUserLoginName" />
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
				<td width="25%">角色名:</td>
				<td><input id="roleName" name="roleName" type="text"
					style="width:250px;" onkeypress="if(event.keyCode==13)extQuery();" />
				</td>
			</tr>
			<tr>
				<td>创建者:</td>
				<td><input id="userLoginName" name="userLoginName" type="text"
					style="width:250px;" onkeypress="if(event.keyCode==13)extQuery();" />
				</td>
			</tr>
		</table>
	</div>
	
	<!-- ui-dialog -->
	<div id="dialog-inUserRole" title="系统提示">
		<table width="90%" border="0" cellspacing="5" cellpadding="5"
			style="margin-left:20px;">
			<tr><td>内置角色不能修改  </td></tr>
		</table>
	</div>

	<!-- 拷贝角色-dialog -->
	<div id="dialog-copyRole" title="角色拷贝">
		<table width="90%" border="0" cellspacing="5" cellpadding="5"
			style="margin-left:20px;">
			<tr>
				<td width="25%">角色名:</td>
				<td width="45%"><input type="hidden" name="copyRoleId"
					id="copyRoleId" /> <input id="copyRoleName" name="copyRoleName"
					type="text" style="width:180px;" /> <br /> <span
					id="check_copyrolename_msg"></span>
				</td>
			</tr>
		</table>
	</div>
	<input type="hidden" id="actionStr" value="${actionStr }" />

</body>
</html>
