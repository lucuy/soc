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
	
		<link href="${ctx}/styles/style.css" rel="stylesheet" type="text/css">
		<link href="${ctx}/styles/right.css" rel="stylesheet" type="text/css">
		<link href="${ctx}/styles/template.css" rel="stylesheet"
			type="text/css">
		 <link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet"
			type="text/css"> 
			<%-- <link href="${ctx}/styles/jquery-ui-1.8.21.css" rel="stylesheet" type="text/css"> --%>
		<link href="${ctx}/styles/validationEngine.jquery.css"
			rel="stylesheet" type="text/css">
		<script type='text/javascript'
			src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
		<script type='text/javascript'
			src="${ctx}/scripts/jquery.validationEngine-en.js"></script>
		<script type='text/javascript'
			src="${ctx}/scripts/jquery.validationEngine.js"></script>
		<script type='text/javascript'
			src="${ctx}/scripts/My97DatePicker/WdatePicker.js"></script>
		<script type='text/javascript'
			src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
		<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
		<script type='text/javascript' src="${ctx}/scripts/popuplayer.js"></script>
		<script type='text/vbscript' src="${ctx}/scripts/ascii.vbs"></script>
		<script type='text/javascript' src="${ctx}/scripts/pinyin.js"></script>
		<script language="javascript">
		
	jQuery(document).ready(function() {
		jQuery("#empForm").validationEngine();
		
		$('#dialog-addRole').dialog({
			autoOpen: false,
			width: 300,
			height:350,
			buttons: {
				"确定[Enter]": function() {
					addOk('roleSelect','dialog-addRole');
				},
				"取消[Esc]": function() {
					$(this).dialog("close");
				}
			}
		});
		
		$('#dialog-addGroup').dialog({
			autoOpen: false,
			width: 300,
			height:350,
			buttons: {
				"确定[Enter]": function() {
					addGroupRidioOk();
				},
				"取消[Esc]": function() {
					$(this).dialog("close");
				}
			}
		});
		
		$('#dialog-allowPolicy').dialog({
			autoOpen: false,
			width: 300,
			height:350,
			buttons: {
				"确定[Enter]": function() {
					addOk('allowPolicySelect','dialog-allowPolicy');
				},
				"取消[Esc]": function() {
					$(this).dialog("close");
				}
			}
		});
		
		$('#dialog-denyPolicy').dialog({
			autoOpen: false,
			width: 300,
			height:350,
			buttons: {
				"确定[Enter]": function() {
					addOk('denyPolicySelect','dialog-denyPolicy');
				},
				"取消[Esc]": function() {
					$(this).dialog("close");
				}
			}
		});
		
		$('#dialog-passwordPolicy').dialog({
			autoOpen: false,
			width: 300,
			height:350,
			buttons: {
				"确定[Enter]": function() {
					addOk('passwordPolicySelect','dialog-passwordPolicy');
					modifyPwd();
				},
				"取消[Esc]": function() {
					$(this).dialog("close");
				}
			}
		});
		
		$('#dialog-accountPolicy').dialog({
			autoOpen: false,
			width: 300,
			height:350,
			buttons: {
				"确定[Enter]": function() {
					addOk('accountPolicySelect','dialog-accountPolicy');
				},
				"取消[Esc]": function() {
					$(this).dialog("close");
				}
			}
		});
		
		//初始化dialog
		initCheckBox('roleSelect','roleId');
		initCheckBox('allowPolicySelect','chk-allowpolicy');
		initCheckBox('denyPolicySelect','chk-denypolicy');
		initCheckBox('passwordPolicySelect','chk-passwordpolicy');
		initCheckBox('accountPolicySelect','chk-accountpolicy');
		initRadio('group_id','groupRadio');
	});
	
	//指纹注册
	function linkto() {
		var UserID = $("#empLoginName").val();
		if (UserID == "") {
			alert("请输入用户名...");
		} else {
			//window.open('${ctx}/pages/employee/fingerRegistInit.jsp?UserID='+UserID,'selectEmployee','height=500,width=700,toolbar=no,menubar=no,scrollbars=yes,resizable=no,location=no,status=yes').focus();
			document.frmTrustLink.location.href = '${ctx}/pages/employee/fingerRegistInit.jsp?UserID='
					+ encodeURIComponent(UserID);

		}
	}
	//设备sn
	function l() {
		var strTmp;
		XFPAuthenExportX.SoundTip(3, 6);
		strTmp = XDevInfoExportX.GetFPDevSN();
		if (strTmp != "") {
			var url = "${ctx}/employee/uniqueSN.action?method=uniqueSN";
			$.ajax({
				type : "post",
				url : url,
				dataType : "text",
				data : "&sn=" + strTmp,
				success : function(result) {
					if (result != "success") {
						alert("此设备已被注册,请选择其他的设备！");
					} else {
						$("#empFingerSn").val(strTmp);
					}
				}
			});
		} else {
			alert("请插入指纹设备...");
		}
	}

	//修改状态
	function updateStatus(status) {
		var empId = $("#empId").val();

		var url = "${ctx}/user/updateUserStatus.action?method=updateUserStatus";

		$.ajax({
			type : "post",
			url : url,
			dataType : "text",
			data : "&empId=" + empId + "&status=" + status,
			success : function(result) {

				var res = String($.trim(result));

				if (res = "success") {
					if (status == 1) {
						$("#status").val("激活");
						$("#unLock").attr('disabled', 'disabled');
						$("#lock").attr('disabled', '');

					} else if (status == 0) {
						$("#status").val("锁定");

						$("#lock").attr('disabled', 'disabled');
						$("#unLock").attr('disabled', '');
					}
				} else {
					alert("更新状态有异常，请您重试一下！");
				}
			}
		});
	}

	function subData() {
		//$("#userPinyin").val(pinyin($("#userLoginName").val()));
		if ($("#password").val() != "") {
			if ($("#password2").val() == "") {
				alert("两次密码不一致");
				return;
			}
		}

		//if ($.trim($("#message").html()) == "" || $("#message").html() == null) {
		//if ($("#flag").val()=="true") {
			if (existFlag) {
				alert('用户名已占用，请重新设置！');
				$('#employee.empLoginName').focus();
				return;
			}

			$('#policy').val('');
			$("#allowPolicySelect").children("option").each(
					function() {
						if ($(this).parent("select").size() > 0) {
							$('#policy').val(
									$('#policy').val() + "0" + $(this).val()
											+ ",");
						}
					});
			$("#denyPolicySelect").children("option").each(
					function() {
						if ($(this).parent("select").size() > 0) {
							$('#policy').val(
									$('#policy').val() + "0" + $(this).val()
											+ ",");
						}
					});
			$("#passwordPolicySelect").children("option").each(
					function() {
						if ($(this).parent("select").size() > 0) {
							$('#policy').val(
									$('#policy').val() + "1" + $(this).val()
											+ ",");
						}
					});
			$("#accountPolicySelect").children("option").each(
					function() {
						if ($(this).parent("select").size() > 0) {
							$('#policy').val(
									$('#policy').val() + "2" + $(this).val()
											+ ",");
						}
					});
			/* $('#role').val('');
			if(document.getElementById("roleSelect").options.length==0)
			{
				alert("用户角色不能为空");
				return;
			} */
			$("#roleSelect").children("option").each(function() {
				if ($(this).parent("select").size() > 0) {
					$('#role').val($('#role').val() + $(this).val() + ",");
				}
			});
			if ($("#li").text() != undefined) {
				if ($("#li").text() != '指纹采集成功') {
					//alert("请录入指纹信息...");
					//return;
				} else {
					$('#empLinkResult').val(1);
				}
			}
			if ($("#empFingerSn").val() != undefined) {
				if ($("#empFingerSn").val() == "") {
					alert("请获取指纹设备sn...");
					return;
				}
			}
			
			$("#userForm").submit();
		/* } else {
			$("#password").focus();
			return;
		} */

	}

	//弹出选择组对话框 
	function showAddGroupDlg(e) {
		$('#dialog-addGroup').dialog('open');
		$('#dialog-addGroup').focus();
	}
	//弹出选择角色对话框 
	function addRoleDlg() {
	
		$('#dialog-addRole').dialog('open');
		$('#dialog-addRole').focus();
	}
	//弹出选择策略对话框		
	function addPolicyDlg(type) {
		//允许策略
		if (type == 0) {
			$('#dialog-allowPolicy').dialog('open');
			$('#dialog-allowPolicy').focus();
		}
		//禁止策略
		if (type == 1) {
				$('#dialog-denyPolicy').dialog('open');
				$('#dialog-denyPolicy').focus();
			}
			//密码策略
			if (type == 2) {
				$('#dialog-passwordPolicy').dialog('open');
				$('#dialog-passwordPolicy').focus();
			}
			//锁定策略
			if (type == 3) {
				$('#dialog-accountPolicy').dialog('open');
				$('#dialog-accountPolicy').focus();
			}
		}

	function addGroupRidioOk() {

		var id = $("input:[name=groupRadio]:radio:checked").val();
		if (id == '0') {
			$('#group_id').val(0);
			$('#group_name').val('未分组');
		} else {
			$('#group_id').val(id);
			$('#group_name').val($("#groupHref_" + id).text());
		}
		$('#dialog-addGroup').dialog('close');
	}
	/*function addGroupOk(groupId, groupName) {
		if (groupId != "") {
			if (groupId == '0') {
				$('#group_id').val(0);
				$('#group_name').val('未分组');
			} else {
				$('#group_id').val(groupId);
				$('#group_name').val(groupName);
			}
		}
	}*/
	//密码策略进行检查
	function checkPolicy() {
		/*if ($("#empId").val() == "" || $("#empId").val() == null) {
			return;
		}
		var password = $("#password").val();
		if ($.trim(password) != "") {
			$
					.get(
							"checkPwdPolicy.action?employee.empId=${employee.empId}",
							{
								"newPassword" : password
							},
							function(data) {
								$("#message").empty();
								$("#icon").empty();
								if (data != "") {
									var fontNode = $("<font color='red'>"
											+ data + "</font>");
									$("#message").append(fontNode);
									return;
								} else {
									var img = $("<img src='${ctx}/images/ok.png' height='20px' width='20px'/>");
									$("#icon").append(img).show("slow");
									$("#confirmPassword").focus();
								}
							});
		} else {
			$("#message").empty();
			$("#icon").empty();
			return;
		}*/
		
		var pps = document.getElementById("passwordPolicySelect");
			var len = pps.options.length;
			var pids = "";
			for (var i=0; i<len; i++)
			{
		    	if (i==len-1) 
		    	{
		        	pids = pids + pps.options[i].value;
		    	} 
		    	else
		    	{
		    		pids = pids + pps.options[i].value + ",";
		    	}
			}
			
			var password = $("#password").val();
			if($.trim(password)!=""){
				$.get("checkPwdPolicy.action?employee.empId=${employee.empId}&pids="+pids,{"newPassword":password},function(data){
					$("#message").empty();
					$("#icon").empty();
					if(data!=""){
						//$("#password").val('');
						$("#password2").val('');
						var fontNode=$("<font color='red'>"+data+"</font>");
						$("#message").append(fontNode);
						$("#flag").val("false");
						$("#password").focus();
						return;
					}else{
						var img = $("<img src='${ctx}/images/ok.png' height='20px' width='20px'/>");
						$("#icon").append(img).show("slow");
						$("#confirmPassword").focus();
						$("#flag").val("true");
					}
				});
			}else{
				$("#message").empty();
				$("#icon").empty();
				return;
			}
	}

	var existFlag = false;
	function checkLoginName(loginName) {
		if ('${employee.empId}' == ''
				|| ('(employee.empId}' != '' && loginName != '${employee.empLoginName}')) {
			$.ajax({
				type : "post",
				url : "${ctx}/employee/checkLoginName.action",
				dataType : "text",
				data : "&loginName=" + loginName,
				success : function(result) {
					if (result == 'true') {
						$("#check_loginname_msg").addClass("spanred");
						$("#check_loginname_msg").html("该名称已占用");
						existFlag = true;
					} else {
						$("#check_loginname_msg").removeClass('spanred');
						$("#check_loginname_msg").html("<img border=0 src=\"${ctx}/images/ok.png\" />");
						existFlag = false;
					}
				}
			});
		} else {
			$("#check_loginname_msg").removeClass('spanred');
			$("#check_loginname_msg").html(
					"<img border=0 src=\"${ctx}/images/ok.png\" />");
			existFlag = false;
		}
	}

	function hs_authtype(value) {
		if (value == 0) {
			$('#tr_password_1').show();
			$('#tr_password_2').show();
			$('#tr_password_3').show();
			$('#tr_password_4').show();
			$('#tr_password_5').show();
			$('#tr_password_6').show();
		} else if (value == 2 || value == 3) {
			$('#tr_password_1').hide();
			$('#tr_password_2').hide();
			$('#tr_password_3').hide();
			$('#tr_password_4').hide();
			$('#tr_password_5').hide();
			$('#tr_password_6').hide();
		}
	}
	function limit(check) {
		if ($(check).attr("checked")) {
			$('#islimit1').show();
			$('#islimit2').show();
		} else {
			$('#islimit1').hide();
			$('#islimit2').hide();
		}
	}
	
	function modifyPwd()
	{
		$("#password").val('');
		$("#password2").val('');
		$("#message").empty();
		var data = "请修改密码";
		var fontNode=$("<font color='red'>"+data+"</font>");
		$("#message").append(fontNode);
		$("#password").focus();
		$("#flag").val("false");
	}
</script>

	</head>

	<body style="margin-top: 2px">
		<s:form action="update.action" namespace="/user" method="post"
			theme="simple" id="userForm" name="userForm">
			<s:hidden name="user.userId" id="userId" />
			<!-- <s:hidden name="user.userPinyin" id="userPinyin" /> -->
			<s:hidden name="time" />
			<s:hidden name="userStatus" />
			<s:hidden name="order" id="order" />
			<s:hidden name="flag" id="flag" />
			
			<!--  主table-->
			<table width="99%" border="0" cellspacing="0" cellpadding="0"
				style="margin-left: 4px; margin-top: 0px">
				<tr>
					<!-- left area -->
					<td width="50%" valign="top">
						<!--  左侧table-->
						<div class="framDiv">
							<table width="100%" border="0" cellspacing="1" cellpadding="0">
								<!-- 用户信息 -->
								<tr>
									<td class='r2titler' colspan='2'>
										<b>用户信息</b>
									</td>
								</tr>

								<tr>
									<td>
										<!-- 用户信息内容 -->
										<table width="99%" border="0" align="center" cellspacing="0"
											cellpadding="0">

											<!-- 空行 -->
											<tr>
												<td width="25%" class="td_detail_separator">
												</td>
											</tr>

											<!-- 姓名 -->
											<tr>
												<td align="right" class="td_employee_detail">
													<span class="spanred">*</span>真实姓名：
												</td>
												<td>
													<input
														class="validate[required,custom[onlyLetterNumber]] text-input"
														name="employee.empName" value="${user.userRealName}"
														type="text" size="40" id="empName" maxlength="255"
														style="width: 270px" />
												</td>
											</tr>
											<tr>
												<td height="2px">
												</td>
											</tr>

											<!-- 空行 -->
											<tr>
												<td class="td_detail_separator">
												</td>
											</tr>

											<!-- 邮件地址 -->
											<tr>
												<td align="right" class="td_employee_detail">
													邮箱地址：
												</td>

												<td>
													<input name="user.userEmail"
														value="${user.userEmail}" type="text" size="40"
														id="useremail" class="validate[custom[email]] text-input "
														maxlength="255" style="width: 270px" />
												</td>
											</tr>


											<!-- 空行 -->
											<tr>
												<td align="right" class="td_detail_separator">
												</td>
											</tr>


											<!-- 手机号码 -->
											<tr>
												<td align="right" class="td_employee_detail">
													手机号码：
												</td>

												<td>
													<input name="user.userMobile"
														value="${user.userMobile}" type="text" size="40"
														id="userMobile" class="validate[custom[phone]] text-input "
														maxlength="255" style="width: 270px" />
												</td>
											</tr>

											<!-- 空行 -->
											<tr>
												<td class="td_detail_separator">
												</td>
											</tr>


											<!-- 固定电话 -->
											<tr>
												<td align="right" class="td_employee_detail">
													固定电话：
												</td>

												<td>
													<s:textfield name="user.userTelephone" size="40" id="usertelephone"
														cssStyle="width:270px" />
												</td>
											</tr>


											<!-- 空行 -->
											<tr>
												<td class="td_detail_separator">
												</td>
											</tr>


											<!-- 通信地址 -->
											<tr>
												<td align="right" class="td_employee_detail">
													通信地址：
												</td>

												<td>
													<input name="employee.empAddress"
														value="${employee.empAddress}" type="text" size="40"
														id="empAddress"
														class="validate[custom[onlyLetterNumber]] text-input "
														maxlength="255" style="width: 270px" />
												</td>
											</tr>


											<!-- 空行 -->
											<tr>
												<td class="td_detail_separator">
												</td>
											</tr>



											<!-- 备注 -->
											<tr>
												<td align="right" class="td_employee_detail">
													描述：
												</td>

												<td>
													<s:textarea name="user.userMemo" cols="31" rows="3"
														cssStyle="width:270px"></s:textarea>
												</td>
											</tr>

										</table>
									</td>
								</tr>


								<!-- 空行 -->
								<tr>
									<td class="td_detail_separator">
									</td>
								</tr>

								<!-- 虚线分割线 -->
								<tr>
									<td colspan="2">
										<div class="xuxian"></div>
									</td>
								</tr>

								<tr>
									<td height="5px">
									</td>
								</tr>


								<!-- 角色 -->
								<tr>
									<td>
										<!-- 角色内容 -->
										<table width="99%" border="0" cellspacing="0" cellpadding="0">
											<!-- 角色 -->
											<tr>
												<td width="25%" align="right" class="td_employee_detail">
													<span class="spanred">*</span>角色：
													<s:hidden name="role" id="role"></s:hidden>
												</td>

												<td width="75%">
													<table width="99%" border="0" cellspacing="0"
														cellpadding="0">
														<tr>
															<td>
																<select id="roleSelect" name="roleSelect"
																	style="width: 275px" size="3" multiple="multiple">
																	<c:forEach items="${user.userRoleList}" var="l">
																		<option value="${l.roleId }">
																			${l.roleName }
																		</option>
																	</c:forEach>
																</select>
															</td>
														</tr>
														<tr>
															<td height="2px">
															</td>
														</tr>
														<tr>
															<td>
																<input type="button" class="btnadd"
																	onclick="addRoleDlg();defualtSeleted('roleSelect');" />
																<input type="button" class="btndel"
																	onclick="delOption('roleSelect','roleId');" />
															</td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
									</td>
								</tr>


								<!-- 空行 -->
								<tr>
									<td class="td_detail_separator">
									</td>
								</tr>

								<!-- 虚线分割线 -->
								<tr>
									<td colspan="2">
										<div class="xuxian"></div>
									</td>
								</tr>

								<!-- 空行 -->
								<tr>
									<td height="4px">
									</td>
								</tr>

								<!-- 灰色分割线 -->
								<tr style="display: none;">
									<td colspan="2">
										<hr class="hr_g" />
									</td>
								</tr>

								<!-- 空行 -->
								<tr>
									<td height="3">
									</td>
								</tr>
								<!-- 状态 -->
								<c:if test="${user.userId==null}">
									<tr>
										<td>
											<!-- 状态内容 -->
											<table width="99%" border="0" align="center" cellspacing="0"
												cellpadding="0">
												<!-- 状态 -->
												<tr>
													<td width="25%" align="right" class="td_employee_detail">
														状态：
													</td>

													<td width="75%">
														<table width="99%" border="0" cellspacing="0"
															cellpadding="0">
															<tr>
																<td>
																	<input type="text" id="status" value="激活" size="40"
																		style="width: 270px" disabled="disabled">
																</td>
															</tr>
															<tr>
																<td height="8px">
																</td>
															</tr>
															<tr>
																<td>
																	<input id="lock" type="button"
																		class="btnyh2" style="padding-top:2px;" value="锁定"
																		disabled='disabled' />
																	<input id="unLock" type="button"
																		class="btnyh2" style="padding-top:2px;" value="激活"
																		disabled="disabled" />
																	<input type="button" class="btn_detail_add_del"
																		value="注销" style="display: none;" />
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</c:if>
								<c:if test="${user.userId!=null}">
									<tr>
										<td>
											<!-- 状态内容 -->
											<table width="99%" align="center" border="0" cellspacing="0"
												cellpadding="0">
												<!-- 状态 -->
												<tr>
													<td width="25%" align="right" class="td_employee_detail">
														状态：
													</td>

													<td width="75%">
														<table width="99%" border="0" cellspacing="0"
															cellpadding="0">
															<tr>
																<td>
																	<input type="text" id="status"
																		<c:if test="${user.userStatus==0}">value="激活"</c:if>
																		<c:if test="${user.userStatus==2}">value="注销"</c:if>
																		<c:if test="${user.userStatus==1}">value="锁定"</c:if>
																		size="40" style="width: 270px" disabled="disabled">
																</td>
															</tr>
															<tr>
																<td height="4px">
																</td>
															</tr>
															<tr>
																<td>
																	<input id="lock" type="button" class="btnyh2" style="padding-top:2px;" 
																		value="锁定" onclick="updateStatus(0)"
																		<c:if test="${user.userStatus==1}">disabled='disabled'</c:if> />
																	<input id="unLock" type="button" class="btnyh2" style="padding-top:2px;" 
																		value="激活" onclick="updateStatus(1)"
																		<c:if test="${user.userStatus==0}">disabled='disabled'</c:if> />
																	<input type="button" class="btn_detail_add_del"
																		value="注销" style="display: none;" />
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</c:if>
								<!-- 空行 -->
								<tr>
									<td class="td_detail_separator">
									</td>
								</tr>

								<!-- 空行 -->
								<tr>
									<td height="7px">
									</td>
								</tr>
							</table>
						</div>
					</td>
					<!-- 左右中间间隔 -->
					<td width="5px">
					</td>

					<!-- 右侧table -->
					<td valign="top">
						<!--  左侧table-->
						<div class="framDiv">
							<table width="100%" border="0" cellspacing="1" cellpadding="0">


								<!-- 登录信息 -->
								<tr>
									<td class='r2titler'>
										<b>登录信息</b>
									</td>
								</tr>



								<!-- 分隔符 -->
								<tr>
									<td height="3px">
									</td>
								</tr>
								<tr>
									<td>
										<!-- 用户信息内容 -->
										<table width="99%" border="0" cellspacing="0" cellpadding="0">

											<!-- 空行 -->
											<tr>
												<td width="25%">
												</td>
											</tr>
											<tr>
												<td height="5px">
												</td>
											</tr>

											<!-- 用户名 -->
											<tr>
												<td align="right" class="td_employee_detail">
													<span class="spanred">*</span>用户名：
												</td>
												<td>
													<input name="user.userLoginName"
														value="${user.userLoginName}" type="text" size="40"
														id="userLoginName"
														class="validate[required,custom[onlyLetterNumber]] text-input "
														maxlength="255" style="width: 270px"
														onblur="checkLoginName(this.value);" />
													<span id="check_loginname_msg"></span>
												</td>
											</tr>


											<!-- 空行 -->
											<tr>
												<td class="td_detail_separator">
												</td>
											</tr>


											<!-- 密码 -->
											<tr id="tr_password_1">
												<td align="right" class="td_employee_detail">
													<span class="spanred">*</span>密码：
												</td>

												<td>
													<!-- <s:password name="user.userPassword" id="password"
														size="40" onblur="checkPolicy()" cssStyle="width:270px" /> -->
													<s:password name="user.userPassword" id="password"
														size="40" cssStyle="width:270px" />
													<span id="icon"></span>
													<span class="hand" title="${requestScope.pwdPolicyInfo}"
														onclick="if('${requestScope.pwdPolicyInfo}'!=''){alert('${requestScope.pwdPolicyInfo}');}else{alert('强度无特别限制！');}"><em>密码强度</em>
													</span>
												</td>
												<td align="left">

												</td>
											</tr>

											<tr id="tr_password_2">
												<td class="td_employee_detail">
												</td>
												<td>
													<div id="message"></div>
												</td>
											</tr>
											<!-- 空行 -->
											<tr id="tr_password_3">
												<td class="td_detail_separator">
												</td>
											</tr>

											<!-- 确认密码 -->
											<tr id="tr_password_4">
												<td align="right" class="td_employee_detail">
													<span class="spanred">*</span>确认密码：
												</td>

												<td>
													<input type="password" name="password2" id="password2"
														size="40" style="width: 270px"
														class="validate[equals[password]]/>" />
												</td>
											</tr>
											<tr id="tr_password_5">
												<td height="2px">
												</td>
											</tr>
											<!-- 下次登录修改密码 -->
											<tr id="tr_password_6">
												<td class="td_employee_detail">

												</td>

												<td>
													<input type="checkbox" name="user.userChangePassword"
														value="1"
														<c:if test="${user.userChangePassword==1}">checked="checked"</c:if> />
													下次登录修改密码
												</td>
											</tr>

										</table>
									</td>
								</tr>



								<!-- 认证方式 -->
								<tr>
									<td>
										<!-- 认证方式及导入证书 -->
										<table width="99%" border="0" cellspacing="0" cellpadding="0">

											<!-- 认证方式 -->
											<tr>
												<td width="25%" align="right" class="td_employee_detail">
													认证方式：
												</td>
												<td width="75%">
													<select style="width: 270px" name="user.userAuthType"
														id="user.userAuthType"
														onchange="hs_authtype(this.value)">
														<option value="0"
															<c:if test="${user.userAuthType == 0}"> selected</c:if>>
															密码认证
														</option>
														<option value="2"
															<c:if test="${user.userAuthType == 2}"> selected</c:if>>
															Radius认证
														</option>
														<option value="3"
															<c:if test="${user.userAuthType == 3}"> selected</c:if>>
															AD域认证
														</option>
													</select>
												</td>
											</tr>
										</table>
									</td>
								</tr>



								<!-- 空行 -->
								<tr>
									<td class="td_detail_separator">
									</td>
								</tr>


								<!-- 导入证书 -->
								<tr>
									<td>
										<!-- 导入证书内容 -->
										<table width="99%" border="0" cellspacing="0" cellpadding="0">
											<!-- 导入证书 -->
											<tr>
												<td width="25%" align="right" class="td_employee_detail">
													导入证书：
												</td>

												<td width="50%">
													<s:textfield name="" size="40" />
												</td>

												<td width="25%">
													<input type="button" value="" class="btnchang" />
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td height="8px">
									</td>
								</tr>


								<tr>
									<td class='r2titler'>
										<b>策略信息</b>
									</td>
								</tr>


								<!-- 策略 -->
								<tr>
									<td>
										<!-- 策略内容 -->
										<table width="99%" border="0" cellspacing="0" cellpadding="0">

											<tr>
												<td width="25%" align="right" class="td_employee_detail">
													密码策略：
												</td>

												<td width="75%">
													<table width="99%" border="0" cellspacing="0"
														cellpadding="0">
														<tr>
															<td class="td_detail_separator">
															</td>
														</tr>
														<tr>
															<td>
																<select name="policySelect" class="policySelect"
																	id="passwordPolicySelect" style="width: 270px" size="3"
																	multiple="multiple">
																	<s:iterator value="passwordList" status="stat">
																		<option class="passwordPolicy" value="${relId}">
																			${relName}
																		</option>
																	</s:iterator>
																</select>
															</td>
														</tr>
														<tr>
															<td height="2px">
															</td>
														</tr>
														<tr>
															<td>
																<input type="button" value="" class="btnadd"
																	onclick="addPolicyDlg(0);defualtSeleted('allowPolicySelect');" />
																<input type="button" value="" class="btndel"
																	onclick="delOption('allowPolicySelect','chk-allowpolicy');" />
															</td>
														</tr>
													</table>
												</td>
											</tr>

											<tr>
												<td width="25%" align="right" class="td_employee_detail">
													时间策略：
												</td>

												<td width="75%">
													<table width="99%" border="0" cellspacing="0"
														cellpadding="0">
														<tr>
															<td class="td_detail_separator">
															</td>
														</tr>
														<tr>
															<td>
																<select name="policySelect" class="policySelect"
																	id="timePolicySelect" style="width: 270px" size="3"
																	multiple="multiple">
																	<s:iterator value="denyList" status="stat">
																		<option class="allowPolicy" value="${relId}">
																			${relName}
																		</option>
																	</s:iterator>
																</select>
															</td>
														</tr>
														<tr>
															<td height="2px">
															</td>
														</tr>
														<tr>
															<td>
																<input type="button" value="" class="btnadd"
																	onclick="addPolicyDlg(1);defualtSeleted('denyPolicySelect');" />
																<input type="button" value="" class="btndel"
																	onclick="delOption('denyPolicySelect','chk-denypolicy');" />
															</td>
														</tr>
													</table>
												</td>
											</tr>

											<td width="25%" align="right" class="td_employee_detail">
												地址策略：
											</td>

											<td width="75%">
												<table width="99%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td class="td_detail_separator">
														</td>
													</tr>
													<tr>
														<td>
															<select name="policySelect" class="policySelect"
																id="addressPolicySelect" style="width: 270px" size="3"
																multiple="multiple">
																<s:iterator value="passwordList" status="stat">
																	<option class="passwordPolicy" value="${relId}">
																		${relName}
																	</option>
																</s:iterator>
															</select>
														</td>
													</tr>
													<tr>
														<td height="2px">
														</td>
													</tr>
													<tr>
														<td>
															<input type="button" value="" class="btnadd"
																onclick="addPolicyDlg(2);defualtSeleted('passwordPolicySelect');" />
															<input type="button" value="" class="btndel"
																onclick="delOption('passwordPolicySelect','chk-passwordpolicy');modifyPwd();" />
														</td>
													</tr>
												</table>
											</td>
											</tr>

											<!-- 空行 -->
											<tr>
												<td class="td_detail_separator">
												</td>
											</tr>
										</table>
									</td>
								</tr>

							</table>

						</div>
					</td>
				</tr>
			</table>

			<table width="780px" border="0" cellspacing="0" cellpadding="0"
				style="margin-left: 4px;">
				<!-- 空行 -->
				<tr>
					<td class="td_detail_separator">
					</td>
				</tr>

				<tr>
					<td>
						<input type="button" class="btnyh" id="btnSave" value="保  存"
							onclick="subData();" />
						<input type="button" class="btnyh" id="btnCancel" value="取  消"
							onclick="window.location.href='${ctx}/user/query.action';" />
					</td>
				</tr>
			</table>
		</s:form>

		<!-- 角色选择的dialog -->
		<div id="dialog-addRole" title="角色选择">
			<table width='96%' cellspacing='0' cellpadding='0' border='0'
				align='center'>
				<s:iterator value='roleList' status='stat'>
					<tr style="line-height: 25px;">
						<td width="20%" align="center">
							<input type='checkbox' id='roleId-${roleId}' name='roleId'
								value='${roleId}' />
						</td>
						<td width="80%">
							<a style='color: #555555' href="javascript:void(0);" onclick="addOk_a('${roleName}',${roleId},'roleSelect','dialog-addRole');">${roleName}</a>
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
		
		<!-- 允许策略选择的dialog  -->
		<div id="dialog-passwordPolicy" title="密码策略选择">
			<table width='96%' cellspacing='0' cellpadding='0' border='0' align='center'>
				<s:iterator value='allPasswordList' status='stat'>
					<tr style="line-height: 25px;">
						<td width="20%" align="center">
							<input type="checkbox" name="chk-allowpolicy" id="chk-policy-${id}" value="${id}" />
						</td>
						<td width="80%">
					    	<a style='color:#555555' href="javascript:void(0);" onclick="addOk_a('${name}',${id},'allowPolicySelect','dialog-allowPolicy')" style='color:#555555';">${name}
							</a>
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
		<!-- 时间策略选择的dialog -->
		<div id="dialog-timePolicy" title="时间策略选择">
			<table width='96%' cellspacing='0' cellpadding='0' border='0'
				align='center'>
				<s:iterator value='allTimeList' status='stat'>
					<tr style="line-height: 25px;">
						<td width="20%" align="center">
							<input type="checkbox" name="chk-denypolicy" id=
								"chk-policy-${id}" value="${id}" />
						</td>
						<td width="80%">
							<a style='color: #555555' href="javascript:void(0);" onclick="addOk_a('${name}',${id},'denyPolicySelect','dialog-denyPolicy')">${name}</a>
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
		
		<!-- 地址策略选择的dialog -->
		<div id="dialog-addressPolicy" title="地址策略选择">
			<table width='96%' cellspacing='0' cellpadding='0' border='0'
				align='center'>
				<s:iterator value='allAddressPolicyList' status='stat'>
					<tr style="line-height: 25px;">
						<td width="20%" align="center">
							<input type="checkbox" name="chk-passwordpolicy" id=
								"chk-policy-${id}" value="${id}" />
						</td>
						<td width="80%">
							<a style='color: #555555' href="javascript:void(0);" onclick="addOk_a('${name}',${id},'passwordPolicySelect','dialog-passwordPolicy')">${name}</a>
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
		
	</body>
</html>
